--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- Copyright (c) Oracle Corporation 1996. All rights reserved, worldwide. 
--
-- Name:        cg$errors
-- Description: Generic error package definitions
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------

CREATE or REPLACE PACKAGE BODY cg$errors IS
  
   cg$err_msg      cg$err_msg_t;
   cg$err_error    cg$err_error_t;
   cg$err_msg_type cg$err_msg_type_t;
   cg$err_msgid    cg$err_msgid_t;
   cg$err_loc      cg$err_loc_t;

   FUNCTION GetVersion
   RETURN varchar2 IS
   BEGIN
      return CG$ERROR_PACKAGE_VERSION;
   END;
   
   -----------------------------------------------------------------------------
   -- Name:        GetErrors
   -- Description: Pops all messages off the stack and returns them in the 
   --              reverse order in which they were raised.
   -- Parameters:  none
   -- Returns:     The messages
   -----------------------------------------------------------------------------
   FUNCTION GetErrors RETURN varchar2
   IS
      maxMessageLength  constant integer := 32767;
      agregatedMessage  varchar2(32767); 
      nextMessage       varchar2(512); 
   BEGIN
     while cg$errors.pop(nextMessage)
     loop 
       if agregatedMessage IS null
       then 
          agregatedMessage := nextMessage; 
       else
         if ( (length(agregatedMessage)+length(nextMessage)+4) < maxMessageLength )  -- B2401878 : add length check
         then
           agregatedMessage := nextMessage || chr(10) || '   ' || agregatedMessage;
         end if; 
       end if; 
     end loop;
      
     return agregatedMessage;
   END;  

   -----------------------------------------------------------------------------
   -- Name:        LookErrors
   -- Description: Pops all messages off the stack and returns them in the 
   --              reverse order in which they were raised.
   -- Parameters:  none
   -- Returns:     The messages
   -----------------------------------------------------------------------------
   FUNCTION LookErrors RETURN varchar2
   IS
      maxMessageLength  constant integer := 32767;
      agregatedMessage  varchar2(32767);
      nextMessage       varchar2(512); 
   BEGIN 
     -- B2401878 : Return errors in reverse order like GetErrors
     -- for i in 1..cg$err_tab_i-1 loop
     for i in reverse 1..cg$err_tab_i-1
     loop 
       if agregatedMessage IS NULL
       then
         agregatedMessage := 'TAPI-' || to_char(cg$err_msgid(i)) || ':' || cg$err_msg(i);
       else
         if ( (  length(agregatedMessage)
               + length(cg$err_msg(i))
               + length(to_char(cg$err_msgid(i))) + 16 ) < maxMessageLength )  -- B2401878 : add length check
         then
           agregatedMessage := agregatedMessage || chr(10) ||
                               '           TAPI-' || to_char(cg$err_msgid(i)) || ':' || cg$err_msg(i);
          end if;
       end if;
     end loop;
     
     return agregatedMessage;
   END;  

   --------------------------------------------------------------------------------
   -- Name:        raise_failure
   --
   -- Description: To raise the cg$error failure exception handler
   --------------------------------------------------------------------------------
   PROCEDURE raise_failure
   IS
   BEGIN
       raise_application_error(-20999, LookErrors);
   END raise_failure;

   --------------------------------------------------------------------------------
   -- Name:        parse_constraint
   -- Description: Isolate constraint name from an Oracle error message
   -- Parameters:  msg     The actual Oracle error message
   --              type    type of constraint to find
   --                      (ERR_FOREIGN_KEY     Foreign key,
   --                       ERR_CHECK_CON       Check,
   --                       ERR_UNIQUE_KEY      Unique key,
   --                       ERR_DELETE_RESTRICT Restricted delete)
   -- Returns:     con_name Constraint found (NULL if none found)
   --------------------------------------------------------------------------------
   FUNCTION parse_constraint(msg   in varchar2
                            ,type  in integer)
   RETURN varchar2 IS
       con_name    varchar2(100) := '';
   BEGIN

       if (type = ERR_FOREIGN_KEY	or
           type = ERR_CHECK_CON	    or
           type = ERR_UNIQUE_KEY	or
           type = ERR_DELETE_RESTRICT)
       then
           con_name := substr(msg, instr(msg, '.') + 1, instr(msg, ')') - instr(msg, '.') - 1);
       end if;

       return con_name;
   END;

   --------------------------------------------------------------------------------
   -- Name:        push
   --
   -- Description: Put a message on stack with full info
   --
   -- Parameters:  msg      Text message
   --              error    ERRor or WARNing
   --              msg_type ORA, API or user TLA
   --              msg_id   Id of message
   --              loc      Location where error occured
   --------------------------------------------------------------------------------
   PROCEDURE push(msg      in varchar2
                 ,error    in varchar2  DEFAULT 'E'
                 ,msg_type in varchar2  DEFAULT NULL
                 ,msgid    in integer   DEFAULT 0
                 ,loc      in varchar2  DEFAULT NULL)
   IS
   BEGIN

       cg$err_msg(cg$err_tab_i)        := msg;
       cg$err_error(cg$err_tab_i)      := error;
       cg$err_msg_type(cg$err_tab_i)   := msg_type;
       cg$err_msgid(cg$err_tab_i)      := msgid;
       cg$err_loc(cg$err_tab_i)        := loc;
       cg$err_tab_i                    := cg$err_tab_i + 1;

   END push;

   --------------------------------------------------------------------------------
   -- Name:        pop
   -- Description: Take a message off stack
   -- Parameters:  msg     Text message
   -- Returns:     TRUE    Message popped successfully
   --              FALSE   Stack was empty
   --------------------------------------------------------------------------------
   FUNCTION pop(msg OUT varchar2)
   RETURN boolean IS
   BEGIN

       if (cg$err_tab_i > 1 and cg$err_msg(cg$err_tab_i - 1) IS NOT NULL)
       then
           cg$err_tab_i := cg$err_tab_i - 1;
           msg          := cg$err_msg(cg$err_tab_i);
           cg$err_msg(cg$err_tab_i) := '';
           return TRUE;
       else
           return FALSE;
       end if;

   END pop;

   --------------------------------------------------------------------------------
   -- Name:        pop (overload)
   -- Description: Take a message off stack with full info
   -- Parameters:  msg      Ttext message
   --              error    ERRor or WARNing
   --              msg_type ORA, API or user TLA
   --              msg_id   Id of message
   --              loc      Location where error occured
   -- Returns:     TRUE     Message popped successfully
   --              FALSE    Stack was empty
   --------------------------------------------------------------------------------
   FUNCTION pop(msg        OUT varchar2
               ,error      OUT varchar2
               ,msg_type   OUT varchar2 
               ,msgid      OUT integer 
               ,loc        OUT varchar2)
   RETURN boolean IS
   BEGIN

       if (cg$err_tab_i > 1 and cg$err_msg(cg$err_tab_i - 1) IS NOT NULL)
       then
           cg$err_tab_i := cg$err_tab_i - 1;
           msg          := cg$err_msg(cg$err_tab_i);
           cg$err_msg(cg$err_tab_i) := '';
           error        := cg$err_error(cg$err_tab_i);
           msg_type     := cg$err_msg_type(cg$err_tab_i);
           msgid        := cg$err_msgid(cg$err_tab_i);
           loc          := cg$err_loc(cg$err_tab_i);
           return TRUE;
       else
           return FALSE;
       end if;

   END pop;


   --------------------------------------------------------------------------------
   -- Name:        pop_head
   -- Description: Take a message off stack from head
   -- Parameters:  msg     Text message
   -- Returns:     TRUE    Message popped successfully
   --              FALSE   Stack was empty
   --------------------------------------------------------------------------------
   FUNCTION pop_head(msg OUT varchar2)
   RETURN boolean IS
   BEGIN

       if (cg$err_tab_i > 1 and cg$err_msg(cg$err_tab_i - 1) IS NOT NULL)
       then
           msg := cg$err_msg(1);

           for i in 1..cg$err_tab_i-2 loop
              cg$err_msg(i) := cg$err_msg(i+1);
              cg$err_error(i) := cg$err_error(i+1);
              cg$err_msg_type(i) := cg$err_msg_type(i+1);
              cg$err_msgid(i) := cg$err_msgid(i+1);
              cg$err_loc(i) := cg$err_loc(i+1);
           end loop;

           cg$err_tab_i := cg$err_tab_i - 1;
           cg$err_msg(cg$err_tab_i) := '';
           return TRUE;
       else
           return FALSE;
       end if;

   END pop_head;


   --------------------------------------------------------------------------------
   -- Name:        pop_head (overload)
   -- Description: Take a message off stack from head with full info
   -- Parameters:  msg      Ttext message
   --              error    ERRor or WARNing
   --              msg_type ORA, API or user TLA
   --              msg_id   Id of message
   --              loc      Location where error occured
   -- Returns:     TRUE     Message popped successfully
   --              FALSE    Stack was empty
   --------------------------------------------------------------------------------
   FUNCTION pop_head(msg        OUT varchar2
                     ,error      OUT varchar2
                     ,msg_type   OUT varchar2 
                     ,msgid      OUT integer 
                     ,loc        OUT varchar2)
   RETURN boolean IS
   BEGIN

       if (cg$err_tab_i > 1 and cg$err_msg(cg$err_tab_i - 1) IS NOT NULL) then
           msg          := cg$err_msg(1);
           error        := cg$err_error(1);
           msg_type     := cg$err_msg_type(1);
           msgid        := cg$err_msgid(1);
           loc          := cg$err_loc(1);

           for i in 1..cg$err_tab_i-2 loop
              cg$err_msg(i) := cg$err_msg(i+1);
              cg$err_error(i) := cg$err_error(i+1);
              cg$err_msg_type(i) := cg$err_msg_type(i+1);
              cg$err_msgid(i) := cg$err_msgid(i+1);
              cg$err_loc(i) := cg$err_loc(i+1);
           end loop;

           cg$err_tab_i := cg$err_tab_i - 1;
           cg$err_msg(cg$err_tab_i) := '';

           return TRUE;
       else
           return FALSE;
       end if;

   END pop_head;


   --------------------------------------------------------------------------------
   -- Name:        clear
   -- Description: Clears the stack
   -- Parameters:  none  
   --------------------------------------------------------------------------------
   PROCEDURE clear
   IS
   BEGIN
       cg$err_tab_i := 1;
   END clear;

   --------------------------------------------------------------------------------
   -- Name:        MsgGetText
   -- Description: Provides a mechanism for text translation.
   -- Parameters:  p_MsgNo    The Id of the message
   --              p_DfltText The Default Text
   --              p_Subst1 (to 4) Substitution strings
   --              p_LangId   The Language ID
   -- Returns:		Translated message
   --------------------------------------------------------------------------------
   FUNCTION MsgGetText(p_MsgNo in number
                      ,p_DfltText in varchar2
                      ,p_Subst1 in varchar2
                      ,p_Subst2 in varchar2
                      ,p_Subst3 in varchar2
                      ,p_Subst4 in varchar2
                      ,p_LangId in number)
   RETURN varchar2 IS
      l_temp varchar2(10000) := p_DfltText;
   BEGIN

      l_temp := replace(l_temp, '<p>',  p_Subst1);
      l_temp := replace(l_temp, '<p1>', p_Subst1);
      l_temp := replace(l_temp, '<p2>', p_Subst2);
      l_temp := replace(l_temp, '<p3>', p_Subst3);
      l_temp := replace(l_temp, '<p4>', p_Subst4);

      return l_temp;

   END MsgGetText;

END cg$errors;
/

show errors
