
PROMPT Creating API Package Body for Table 'RUBRIQUE_EVALUATION'
--------------------------------------------------------------------------------
-- Name:        cg$RUBRIQUE_EVALUATION
-- Description: RUBRIQUE_EVALUATION table API package definitions
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY cg$RUBRIQUE_EVALUATION IS

PROCEDURE   validate_mandatory(cg$val_rec IN cg$row_type,
                               loc        IN VARCHAR2 DEFAULT '');
PROCEDURE   up_autogen_columns(cg$rec    IN OUT cg$row_type,
                               cg$ind    IN OUT cg$ind_type,
                               operation IN VARCHAR2 DEFAULT 'INS',
                               do_denorm IN BOOLEAN DEFAULT TRUE);
PROCEDURE   err_msg(msg  IN VARCHAR2,
                    type IN INTEGER,
                    loc  IN VARCHAR2 DEFAULT '');

--------------------------------------------------------------------------------
-- Name:        raise_uk_not_updateable
--
-- Description: Raise appropriate error when unique key updated
--
-- Parameters:  none
--------------------------------------------------------------------------------
PROCEDURE raise_uk_not_updateable(uk IN VARCHAR2) IS   
BEGIN
    cg$errors.push(cg$errors.MsgGetText(cg$errors.API_UNIQUE_KEY_UPDATE, cg$errors.ERR_UK_UPDATE, uk),
                   'E',
                   'API',
                   cg$errors.API_UNIQUE_KEY_UPDATE,
                   'cg$RUBRIQUE_EVALUATION.raise_uk_not_updateable');
                   cg$errors.raise_failure;
END raise_uk_not_updateable;


--------------------------------------------------------------------------------
-- Name:        raise_fk_not_transferable
--
-- Description: Raise appropriate error when foreign key updated
--
-- Parameters:  none
--------------------------------------------------------------------------------
PROCEDURE raise_fk_not_transferable(fk IN VARCHAR2) IS 
BEGIN
    cg$errors.push(cg$errors.MsgGetText(cg$errors.API_FOREIGN_KEY_TRANS, cg$errors.ERR_FK_TRANS, fk),
                   'E',
                   'API',
                   cg$errors.API_FOREIGN_KEY_TRANS,
                   'cg$RUBRIQUE_EVALUATION.raise_fk_not_transferable');
    cg$errors.raise_failure;
END raise_fk_not_transferable;


--------------------------------------------------------------------------------
-- Name:        up_autogen_columns
--
-- Description: Specific autogeneration of column values and conversion to 
--              uppercase
--
-- Parameters:  cg$rec    Record of row to be manipulated
--              cg$ind    Indicators for row
--              operation Procedure where this procedure was called
--------------------------------------------------------------------------------
PROCEDURE up_autogen_columns(cg$rec IN OUT cg$row_type,
                             cg$ind IN OUT cg$ind_type,
                             operation IN VARCHAR2 DEFAULT 'INS',
                             do_denorm IN BOOLEAN DEFAULT TRUE) IS
BEGIN
  IF (operation = 'INS') THEN
    BEGIN
			IF (cg$ind.ID_RUBRIQUE_EVALUATION = FALSE
      OR  cg$rec.ID_RUBRIQUE_EVALUATION is NULL) THEN 
				SELECT REV_SEQ.nextval
				INTO   cg$rec.ID_RUBRIQUE_EVALUATION
				FROM   DUAL;
				cg$ind.ID_RUBRIQUE_EVALUATION := TRUE;
			END IF;
    EXCEPTION WHEN others THEN
      cg$errors.push(SQLERRM, 'E', 'ORA', SQLCODE,
                     'cg$RUBRIQUE_EVALUATION.up_autogen.ID_RUBRIQUE_EVALUATION.OTHERS');
      cg$errors.raise_failure;
    END;
    NULL;
  ELSE      -- (operation = 'UPD')
    NULL;
  END IF;   -- (operation = 'INS') ELSE (operation = 'UPD')

  -- Statements executed for both 'INS' and 'UPD'


EXCEPTION
  WHEN no_data_found THEN
    NULL;
  WHEN others THEN
    cg$errors.push( SQLERRM, 'E', 'ORA', SQLCODE, 
                    'cg$RUBRIQUE_EVALUATION.up_autogen_columns');
    cg$errors.raise_failure;
END up_autogen_columns;


--------------------------------------------------------------------------------
-- Name:        validate_mandatory
--
-- Description: Checks all mandatory columns are not null and raises appropriate
--              error if not satisfied
--
-- Parameters:  cg$val_rec Record of row to be checked
--              loc        Place where this procedure was called for error 
--                         trapping
--------------------------------------------------------------------------------
PROCEDURE validate_mandatory(cg$val_rec IN cg$row_type,
                             loc        IN VARCHAR2 DEFAULT '') IS
BEGIN
    IF (cg$val_rec.ID_RUBRIQUE_EVALUATION IS NULL) THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_MAND_COLUMN_ISNULL, cg$errors.VAL_MAND, P10ID_RUBRIQUE_EVALUATION),
                       'E',
                       'API',
                       cg$errors.API_MAND_COLUMN_ISNULL,
                       loc);
    END IF;
    IF (cg$val_rec.ID_EVALUATION IS NULL) THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_MAND_COLUMN_ISNULL, cg$errors.VAL_MAND, P20ID_EVALUATION),
                       'E',
                       'API',
                       cg$errors.API_MAND_COLUMN_ISNULL,
                       loc);
    END IF;
    IF (cg$val_rec.ORDRE IS NULL) THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_MAND_COLUMN_ISNULL, cg$errors.VAL_MAND, P30ORDRE),
                       'E',
                       'API',
                       cg$errors.API_MAND_COLUMN_ISNULL,
                       loc);
    END IF;
    NULL;
END validate_mandatory;


--------------------------------------------------------------------------------
-- Name:        validate_foreign_keys
--
-- Description: Checks all mandatory columns are not null and raises appropriate
--              error if not satisfied
--
-- Parameters:  cg$rec Record of row to be checked
--------------------------------------------------------------------------------
PROCEDURE validate_foreign_keys_ins(cg$rec IN cg$row_type) IS
    fk_check INTEGER;
BEGIN
NULL;
END;

PROCEDURE validate_foreign_keys_upd( cg$rec IN cg$row_type, 
                                     cg$old_rec IN cg$row_type, 
                                     cg$ind IN cg$ind_type) IS
    fk_check INTEGER;
BEGIN
NULL;
END;

PROCEDURE validate_foreign_keys_del(cg$rec IN cg$row_type) IS
    fk_check INTEGER;
BEGIN
NULL;
END;


--------------------------------------------------------------------------------
-- Name:        slct
--
-- Description: Selects into the given parameter all the attributes for the row
--              given by the primary key
--
-- Parameters:  cg$sel_rec  Record of row to be selected into using its PK
--------------------------------------------------------------------------------
PROCEDURE slct(cg$sel_rec IN OUT cg$row_type) IS

BEGIN

    IF cg$sel_rec.the_rowid is null THEN
       SELECT    ID_RUBRIQUE_EVALUATION
       ,         ID_EVALUATION
       ,         ID_RUBRIQUE
       ,         ORDRE
       ,         DESIGNATION
       , rowid
       INTO      cg$sel_rec.ID_RUBRIQUE_EVALUATION
       ,         cg$sel_rec.ID_EVALUATION
       ,         cg$sel_rec.ID_RUBRIQUE
       ,         cg$sel_rec.ORDRE
       ,         cg$sel_rec.DESIGNATION
       ,cg$sel_rec.the_rowid
       FROM   RUBRIQUE_EVALUATION
       WHERE        ID_RUBRIQUE_EVALUATION = cg$sel_rec.ID_RUBRIQUE_EVALUATION;
    ELSE
       SELECT    ID_RUBRIQUE_EVALUATION
       ,         ID_EVALUATION
       ,         ID_RUBRIQUE
       ,         ORDRE
       ,         DESIGNATION
       , rowid
       INTO      cg$sel_rec.ID_RUBRIQUE_EVALUATION
       ,         cg$sel_rec.ID_EVALUATION
       ,         cg$sel_rec.ID_RUBRIQUE
       ,         cg$sel_rec.ORDRE
       ,         cg$sel_rec.DESIGNATION
       ,cg$sel_rec.the_rowid
       FROM   RUBRIQUE_EVALUATION
       WHERE  rowid = cg$sel_rec.the_rowid;
    END IF;

EXCEPTION WHEN OTHERS THEN
    cg$errors.push(SQLERRM,
                   'E',
                   'ORA',
                   SQLCODE,
                   'cg$RUBRIQUE_EVALUATION.slct.others');
    cg$errors.raise_failure;

END slct;


--------------------------------------------------------------------------------
-- Name:        cascade_update
--
-- Description: Updates all child tables affected by a change to RUBRIQUE_EVALUATION 
--
-- Parameters:  cg$rec     Record of RUBRIQUE_EVALUATION current values
--              cg$old_rec Record of RUBRIQUE_EVALUATION previous values
--------------------------------------------------------------------------------
PROCEDURE cascade_update(cg$new_rec IN OUT cg$row_type,
                         cg$old_rec IN     cg$row_type) IS
BEGIN
  NULL;
END cascade_update;


--------------------------------------------------------------------------------
-- Name:        validate_domain_cascade_update
--
-- Description: Implement the Domain Key Constraint Cascade Updates Resticts rule
--              of each child table that references this tableRUBRIQUE_EVALUATION 
--
-- Parameters:  cg$old_rec     Record of RUBRIQUE_EVALUATION current values
--------------------------------------------------------------------------------
PROCEDURE validate_domain_cascade_update( cg$old_rec IN cg$row_type ) IS
  dk_check INTEGER;
BEGIN
  NULL;
END validate_domain_cascade_update;


-----------------------------------------------------------------------------------------
-- Name:        domain_cascade_update
--
-- Description: Implement the Domain Key Constraint Cascade Updates rules of each
--              child table that references this table RUBRIQUE_EVALUATION 
--
-- Parameters:  cg$new_rec  New values for RUBRIQUE_EVALUATION's domain key constraint columns 
--              cg$new_ind  Indicates changed RUBRIQUE_EVALUATION's domain key constraint columns
--              cg$old_rec  Current values for RUBRIQUE_EVALUATION's domain key constraint columns
-----------------------------------------------------------------------------------------
PROCEDURE domain_cascade_update(cg$new_rec IN OUT cg$row_type,
                                cg$new_ind IN OUT cg$ind_type,
                                cg$old_rec IN     cg$row_type) IS
BEGIN
  NULL;
END domain_cascade_update;


--------------------------------------------------------------------------------
-- Name:        cascade_delete
--
-- Description: Delete all child tables affected by a delete to RUBRIQUE_EVALUATION 
--
-- Parameters:  cg$rec     Record of RUBRIQUE_EVALUATION current values
--------------------------------------------------------------------------------
PROCEDURE cascade_delete(cg$old_rec IN OUT cg$row_type)
IS
BEGIN
  NULL;
END cascade_delete;

--------------------------------------------------------------------------------
-- Name:        domain_cascade_delete
--
-- Description: Implement the Domain Key Constraint Cascade Delete rules of each
--              child table that references this tableRUBRIQUE_EVALUATION 
--
-- Parameters:  cg$old_rec     Record of RUBRIQUE_EVALUATION current values
--------------------------------------------------------------------------------
PROCEDURE domain_cascade_delete( cg$old_rec IN cg$row_type )
IS
BEGIN
  NULL;
END domain_cascade_delete;


--------------------------------------------------------------------------------
-- Name:        validate_domain_cascade_delete
--
-- Description: Implement the Domain Key Constraint Cascade Delete Restricts rule
--              of each child table that references this tableRUBRIQUE_EVALUATION 
--
-- Parameters:  cg$old_rec     Record of RUBRIQUE_EVALUATION current values
--------------------------------------------------------------------------------
PROCEDURE validate_domain_cascade_delete(cg$old_rec IN cg$row_type)
IS
    dk_check INTEGER;
BEGIN
  NULL;
END validate_domain_cascade_delete;



--------------------------------------------------------------------------------
-- Name:        validate_arc
--
-- Description: Checks for adherence to arc relationship 
--
-- Parameters:  cg$rec     Record of RUBRIQUE_EVALUATION current values
--------------------------------------------------------------------------------
PROCEDURE validate_arc(cg$rec IN OUT cg$row_type) IS
i NUMBER;
BEGIN
    NULL;
END validate_arc;


--------------------------------------------------------------------------------
-- Name:        validate_domain
--
-- Description: Checks against reference table for values lying in a domain 
--
-- Parameters:  cg$rec     Record of RUBRIQUE_EVALUATION current values
--------------------------------------------------------------------------------
PROCEDURE validate_domain(cg$rec IN OUT cg$row_type,
                          cg$ind IN cg$ind_type DEFAULT cg$ind_true)
IS
  dummy NUMBER;
  found BOOLEAN;
  no_tabview EXCEPTION;
  PRAGMA EXCEPTION_INIT(no_tabview, -942); 
BEGIN







    NULL;

EXCEPTION
    WHEN cg$errors.cg$error THEN 
        cg$errors.raise_failure;
    WHEN no_tabview THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_RV_TAB_NOT_FOUND,
                                            cg$errors.APIMSG_RV_TAB_NOT_FOUND,
                                            'CG_REF_CODES','RUBRIQUE_EVALUATION'),
                       'E',
                       'API',
                       cg$errors.API_RV_TAB_NOT_FOUND,
                       'cg$RUBRIQUE_EVALUATION.v_domain.no_reftable_found');
        cg$errors.raise_failure;
    WHEN OTHERS THEN
        cg$errors.push(SQLERRM,
                       'E',
                       'ORA',
                       SQLCODE,
                       'cg$RUBRIQUE_EVALUATION.v_domain.others');
        cg$errors.raise_failure;
END validate_domain;


--------------------------------------------------------------------------------
-- Name:        err_msg
--
-- Description: Pushes onto stack appropriate user defined error message
--              depending on the rule violated
--
-- Parameters:  msg     Oracle error message
--              type    Type of violation e.g. check_constraint: ERR_CHECK_CON
--              loc     Place where this procedure was called for error 
--                      trapping
--------------------------------------------------------------------------------
PROCEDURE err_msg(msg   IN VARCHAR2,
                  type  IN INTEGER,
                  loc   IN VARCHAR2 DEFAULT '') IS
con_name VARCHAR2(240);
BEGIN
    con_name := cg$errors.parse_constraint(msg, type);
    IF (con_name = 'REV_PK') THEN
        cg$errors.push(nvl(REV_PK 
                  ,cg$errors.MsgGetText(cg$errors.API_PK_CON_VIOLATED
					                 ,cg$errors.APIMSG_PK_VIOLAT
                                     ,'REV_PK' 
                                     ,'RUBRIQUE_EVALUATION')),
                       'E',
                       'API',
                       cg$errors.API_PK_CON_VIOLATED,
                       loc);

    ELSIF (con_name = 'REV_RUB_FK') THEN
        cg$errors.push(nvl(REV_RUB_FK 
                      ,cg$errors.MsgGetText(cg$errors.API_FK_CON_VIOLATED
					                 ,cg$errors.APIMSG_FK_VIOLAT
                                     ,'REV_RUB_FK' 
                                     ,'RUBRIQUE_EVALUATION')),
                       'E',
                       'API',
                       cg$errors.API_FK_CON_VIOLATED,
                       loc);
    ELSIF (con_name = 'REV_EVE_FK') THEN
        cg$errors.push(nvl(REV_EVE_FK 
                      ,cg$errors.MsgGetText(cg$errors.API_FK_CON_VIOLATED
					                 ,cg$errors.APIMSG_FK_VIOLAT
                                     ,'REV_EVE_FK' 
                                     ,'RUBRIQUE_EVALUATION')),
                       'E',
                       'API',
                       cg$errors.API_FK_CON_VIOLATED,
                       loc);
    ELSE
        cg$errors.push(SQLERRM,
                       'E',
                       'ORA',
                       SQLCODE,
                       loc);
    END IF;
END err_msg;




--------------------------------------------------------------------------------
-- Name:        doLobs
--
-- Description: This function is updating lob columns
--
-- Parameters:  cg$rec  Record of row to be inserted
--              cg$ind  Record of columns specifically set
--------------------------------------------------------------------------------
PROCEDURE doLobs(cg$rec IN OUT cg$row_type,
                 cg$ind IN OUT cg$ind_type) IS
BEGIN
   NULL;
END doLobs;


--------------------------------------------------------------------------------
-- Name:        ins
--
-- Description: API insert procedure
--
-- Parameters:  cg$rec  Record of row to be inserted
--              cg$ind  Record of columns specifically set
--              do_ins  Whether we want the actual INSERT to occur
--------------------------------------------------------------------------------
PROCEDURE ins(cg$rec IN OUT cg$row_type,
              cg$ind IN OUT cg$ind_type,
              do_ins IN BOOLEAN DEFAULT TRUE) IS
cg$tmp_rec cg$row_type;

--  Constant default values


BEGIN
--  Application_logic Pre-Insert <<Start>>
--  Application_logic Pre-Insert << End >>

--  Defaulted

--  Auto-generated and uppercased columns

    up_autogen_columns(cg$rec, cg$ind, 'INS', do_ins);

    called_from_package := TRUE;

    IF (do_ins) THEN 
        validate_foreign_keys_ins(cg$rec);
        validate_arc(cg$rec);
        validate_domain(cg$rec);

        INSERT INTO RUBRIQUE_EVALUATION
            (ID_RUBRIQUE_EVALUATION
            ,ID_EVALUATION
            ,ID_RUBRIQUE
            ,ORDRE
            ,DESIGNATION)
        VALUES
            (cg$rec.ID_RUBRIQUE_EVALUATION
            ,cg$rec.ID_EVALUATION
            ,cg$rec.ID_RUBRIQUE
            ,cg$rec.ORDRE
            ,cg$rec.DESIGNATION
);
        doLobs(cg$rec, cg$ind);
        slct(cg$rec);

        upd_oper_denorm2(cg$rec, cg$tmp_rec, cg$ind, 'INS');
    END IF;

    called_from_package := FALSE;



--  Application logic Post-Insert <<Start>>
--  Application logic Post-Insert << End >>

EXCEPTION
    WHEN cg$errors.cg$error THEN 
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.mandatory_missing THEN
        validate_mandatory(cg$rec, 'cg$RUBRIQUE_EVALUATION.ins.mandatory_missing');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.check_violation THEN
        err_msg(SQLERRM, cg$errors.ERR_CHECK_CON, 'cg$RUBRIQUE_EVALUATION.ins.check_violation');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.fk_violation THEN
        err_msg(SQLERRM, cg$errors.ERR_FOREIGN_KEY, 'cg$RUBRIQUE_EVALUATION.ins.fk_violation');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.uk_violation THEN
        err_msg(SQLERRM, cg$errors.ERR_UNIQUE_KEY, 'cg$RUBRIQUE_EVALUATION.ins.uk_violation');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN OTHERS THEN
        cg$errors.push(SQLERRM,
                       'E',
                       'ORA',
                       SQLCODE,
                       'cg$RUBRIQUE_EVALUATION.ins.others');
        called_from_package := FALSE;
        cg$errors.raise_failure;
END ins;


--------------------------------------------------------------------------------
-- Name:        upd
--
-- Description: API update procedure
--
-- Parameters:  cg$rec  Record of row to be updated
--              cg$ind  Record of columns specifically set
--              do_upd  Whether we want the actual UPDATE to occur
--------------------------------------------------------------------------------
PROCEDURE upd(cg$rec             IN OUT cg$row_type,
              cg$ind             IN OUT cg$ind_type,
              do_upd             IN BOOLEAN DEFAULT TRUE,
              cg$pk              IN cg$row_type DEFAULT NULL )    
IS
  cg$upd_rec    cg$row_type;
  cg$old_rec    cg$row_type;
  RECORD_LOGGED BOOLEAN := FALSE;
BEGIN
--  Application_logic Pre-Update <<Start>>
--  Application_logic Pre-Update << End >>

 
    IF ( cg$pk.ID_RUBRIQUE_EVALUATION IS NULL ) THEN          
      cg$upd_rec.ID_RUBRIQUE_EVALUATION := cg$rec.ID_RUBRIQUE_EVALUATION;
    ELSE
      cg$upd_rec.ID_RUBRIQUE_EVALUATION := cg$pk.ID_RUBRIQUE_EVALUATION;
    END IF;
    cg$old_rec.ID_RUBRIQUE_EVALUATION := cg$upd_rec.ID_RUBRIQUE_EVALUATION;

    IF ( cg$pk.the_rowid IS NULL ) THEN             
      cg$upd_rec.the_rowid := cg$rec.the_rowid;
    ELSE
      cg$upd_rec.the_rowid := cg$pk.the_rowid;
    END IF;
    cg$old_rec.the_rowid := cg$upd_rec.the_rowid;

    IF ( do_upd ) THEN

        slct(cg$upd_rec);


        --  Report error if attempt to update non updateable Primary Key REV_PK
        IF (cg$ind.ID_RUBRIQUE_EVALUATION AND cg$rec.ID_RUBRIQUE_EVALUATION != cg$upd_rec.ID_RUBRIQUE_EVALUATION) THEN
            raise_uk_not_updateable('REV_PK');
        END IF;
        IF NOT (cg$ind.ID_RUBRIQUE_EVALUATION) THEN
            cg$rec.ID_RUBRIQUE_EVALUATION := cg$upd_rec.ID_RUBRIQUE_EVALUATION;
        END IF;
        IF NOT (cg$ind.ID_EVALUATION) THEN
            cg$rec.ID_EVALUATION := cg$upd_rec.ID_EVALUATION;
        END IF;
        IF NOT (cg$ind.ID_RUBRIQUE) THEN
            cg$rec.ID_RUBRIQUE := cg$upd_rec.ID_RUBRIQUE;
        END IF;
        IF NOT (cg$ind.ORDRE) THEN
            cg$rec.ORDRE := cg$upd_rec.ORDRE;
        END IF;
        IF NOT (cg$ind.DESIGNATION) THEN
            cg$rec.DESIGNATION := cg$upd_rec.DESIGNATION;
        END IF;
    ELSE
	     -- Perform checks if called from a trigger
	     -- Indicators are only set on changed values
	     null;
        --  Report error if attempt to update non updateable Primary Key REV_PK
        IF ( cg$ind.ID_RUBRIQUE_EVALUATION ) THEN
          raise_uk_not_updateable('REV_PK');
        END IF;
    END IF;

    up_autogen_columns(cg$rec, cg$ind, 'UPD', do_upd);  --  Auto-generated and uppercased columns

--  Now do update if updateable columns exist
    IF (do_upd) THEN
        DECLARE
            called_from BOOLEAN := called_from_package;
        BEGIN
          called_from_package := TRUE;

          slct(cg$old_rec);                          
          validate_foreign_keys_upd(cg$rec, cg$old_rec, cg$ind);
          validate_arc(cg$rec);
          validate_domain(cg$rec, cg$ind);
          validate_domain_cascade_update(cg$old_rec);

          IF cg$rec.the_rowid is null THEN
            UPDATE RUBRIQUE_EVALUATION
            SET
              ID_EVALUATION = cg$rec.ID_EVALUATION
              ,ID_RUBRIQUE = cg$rec.ID_RUBRIQUE
              ,ORDRE = cg$rec.ORDRE
              ,DESIGNATION = cg$rec.DESIGNATION
            WHERE  ID_RUBRIQUE_EVALUATION = cg$rec.ID_RUBRIQUE_EVALUATION;
            null;
          ELSE
            UPDATE RUBRIQUE_EVALUATION
            SET
              ID_EVALUATION = cg$rec.ID_EVALUATION
              ,ID_RUBRIQUE = cg$rec.ID_RUBRIQUE
              ,ORDRE = cg$rec.ORDRE
              ,DESIGNATION = cg$rec.DESIGNATION
            WHERE rowid = cg$rec.the_rowid;

            null;
          END IF;

          slct(cg$rec);

          upd_denorm2(cg$rec, cg$ind);
          upd_oper_denorm2(cg$rec, cg$old_rec, cg$ind, 'UPD');
          cascade_update(cg$rec, cg$old_rec);
          domain_cascade_update(cg$rec, cg$ind, cg$old_rec);             
          called_from_package := called_from;
        END;
    END IF;



    IF NOT (do_upd) THEN
        cg$table(idx).ID_RUBRIQUE_EVALUATION := cg$rec.ID_RUBRIQUE_EVALUATION;
        cg$tableind(idx).ID_RUBRIQUE_EVALUATION := cg$ind.ID_RUBRIQUE_EVALUATION;
        cg$table(idx).ID_EVALUATION := cg$rec.ID_EVALUATION;
        cg$tableind(idx).ID_EVALUATION := cg$ind.ID_EVALUATION;
        cg$table(idx).ID_RUBRIQUE := cg$rec.ID_RUBRIQUE;
        cg$tableind(idx).ID_RUBRIQUE := cg$ind.ID_RUBRIQUE;
        cg$table(idx).ORDRE := cg$rec.ORDRE;
        cg$tableind(idx).ORDRE := cg$ind.ORDRE;
        cg$table(idx).DESIGNATION := cg$rec.DESIGNATION;
        cg$tableind(idx).DESIGNATION := cg$ind.DESIGNATION;

        cg$table(idx).action_performed := 'UPD';
        idx := idx + 1;
    END IF;

--  Application_logic Post-Update <<Start>>
--  Application_logic Post-Update << End >>
            
EXCEPTION
    WHEN cg$errors.cg$error THEN 
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.upd_mandatory_null THEN
        validate_mandatory(cg$rec, 'cg$RUBRIQUE_EVALUATION.upd.upd_mandatory_null');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.check_violation THEN
        err_msg(SQLERRM, cg$errors.ERR_CHECK_CON, 'cg$RUBRIQUE_EVALUATION.upd.check_violation');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.fk_violation THEN
        err_msg(SQLERRM, cg$errors.ERR_FOREIGN_KEY, 'cg$RUBRIQUE_EVALUATION.upd.fk_violation');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.uk_violation THEN
        err_msg(SQLERRM, cg$errors.ERR_UNIQUE_KEY, 'cg$RUBRIQUE_EVALUATION.upd.uk_violation');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN OTHERS THEN
        cg$errors.push(SQLERRM,
                       'E',
                       'ORA',
                       SQLCODE,
                       'cg$RUBRIQUE_EVALUATION.upd.others');
        called_from_package := FALSE;
        cg$errors.raise_failure;
END upd;


----------------------------------------------------------------------------------------
-- Name:        domain_cascade_upd
--
-- Description: Update the Domain Constraint Key columns of RUBRIQUE_EVALUATION when the
--              Cascade Update rule is Cascades and the domain table has been
--              updated. Called from <Domain Table pkg>.domain_cascade_update().
--
-- Parameters:  cg$rec      New values for RUBRIQUE_EVALUATION's domain key constraint columns 
--              cg$ind      Indicates changed RUBRIQUE_EVALUATION's domain key constraint columns
--              cg$old_rec  Current values for RUBRIQUE_EVALUATION's domain key constraint columns
----------------------------------------------------------------------------------------
PROCEDURE   domain_cascade_upd( cg$rec     IN OUT cg$row_type,
                                cg$ind     IN OUT cg$ind_type,
                                cg$old_rec IN     cg$row_type )
IS
  called_from BOOLEAN := called_from_package;
BEGIN

  null;
END domain_cascade_upd;


--------------------------------------------------------------------------------
-- Name:        upd_denorm
--
-- Description: API procedure for simple denormalization
--
-- Parameters:  cg$rec  Record of row to be updated
--              cg$ind  Record of columns specifically set
--              do_upd  Whether we want the actual UPDATE to occur
--------------------------------------------------------------------------------
PROCEDURE upd_denorm2( cg$rec IN cg$row_type,
                       cg$ind IN cg$ind_type ) IS
BEGIN
  NULL;
END upd_denorm2;


--------------------------------------------------------------------------------
-- Name:        upd_oper_denorm
--
-- Description: API procedure for operation denormalization
--
-- Parameters:  cg$rec  Record of row to be updated
--              cg$ind  Record of columns specifically set
--              do_upd  Whether we want the actual UPDATE to occur
--------------------------------------------------------------------------------
PROCEDURE upd_oper_denorm2( cg$rec IN cg$row_type,
                            cg$old_rec IN cg$row_type,
                            cg$ind IN cg$ind_type,
                            operation IN VARCHAR2 DEFAULT 'UPD'
					           )
IS
BEGIN





NULL;
END upd_oper_denorm2;

--------------------------------------------------------------------------------
-- Name:        del
--
-- Description: API delete procedure
--
-- Parameters:  cg$pk  Primary key record of row to be deleted
--------------------------------------------------------------------------------
PROCEDURE del(cg$pk IN cg$pk_type,
              do_del IN BOOLEAN DEFAULT TRUE) IS
BEGIN
--  Application_logic Pre-Delete <<Start>>
--  Application_logic Pre-Delete << End >>

--  Delete the record

    called_from_package := TRUE;

    IF (do_del) THEN
        DECLARE
           cg$rec cg$row_type;
           cg$old_rec cg$row_type;
           cg$ind cg$ind_type;
        BEGIN
           cg$rec.ID_RUBRIQUE_EVALUATION := cg$pk.ID_RUBRIQUE_EVALUATION;
           slct(cg$rec);

           validate_foreign_keys_del(cg$rec);
           validate_domain_cascade_delete(cg$rec);    

           IF cg$pk.the_rowid is null THEN
              DELETE RUBRIQUE_EVALUATION
              WHERE                    ID_RUBRIQUE_EVALUATION = cg$pk.ID_RUBRIQUE_EVALUATION;
           ELSE
              DELETE RUBRIQUE_EVALUATION
              WHERE  rowid = cg$pk.the_rowid;
           END IF;

           upd_oper_denorm2(cg$rec, cg$old_rec, cg$ind, 'DEL');
           cascade_delete(cg$rec);
           domain_cascade_delete(cg$rec);             
        END;
    END IF;

    called_from_package := FALSE;


--  Application_logic Post-Delete <<Start>>
--  Application_logic Post-Delete << End >>

EXCEPTION
    WHEN cg$errors.cg$error THEN 
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.delete_restrict THEN
        err_msg(SQLERRM, cg$errors.ERR_DELETE_RESTRICT, 'cg$RUBRIQUE_EVALUATION.del.delete_restrict');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN no_data_found THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_DEL, cg$errors.ROW_DEL),
                       'E',
                       'ORA',
                       SQLCODE,
                       'cg$RUBRIQUE_EVALUATION.del.no_data_found');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN OTHERS THEN
        cg$errors.push(SQLERRM,
                       'E',
                       'ORA',
                       SQLCODE,
                       'cg$RUBRIQUE_EVALUATION.del.others');
        called_from_package := FALSE;
        cg$errors.raise_failure;
END del;


--------------------------------------------------------------------------------
-- Name:        lck
--
-- Description: API lock procedure
--
-- Parameters:  cg$old_rec  Calling apps view of record of row to be locked
--              cg$old_ind  Record of columns to raise error if modified
--              nowait_flag TRUE lock with NOWAIT, FALSE don't fail if busy
--------------------------------------------------------------------------------
PROCEDURE lck(cg$old_rec IN cg$row_type,
              cg$old_ind IN cg$ind_type,
              nowait_flag IN BOOLEAN DEFAULT TRUE) IS
cg$tmp_rec cg$row_type;
any_modified BOOLEAN := FALSE;

BEGIN
--  Application_logic Pre-Lock <<Start>>
--  Application_logic Pre-Lock << End >>

--  Do the row lock

    BEGIN
        IF (nowait_flag) THEN
            IF cg$old_rec.the_rowid is null THEN
               SELECT       ID_RUBRIQUE_EVALUATION
               ,            ID_EVALUATION
               ,            ID_RUBRIQUE
               ,            ORDRE
               ,            DESIGNATION
               INTO         cg$tmp_rec.ID_RUBRIQUE_EVALUATION
               ,            cg$tmp_rec.ID_EVALUATION
               ,            cg$tmp_rec.ID_RUBRIQUE
               ,            cg$tmp_rec.ORDRE
               ,            cg$tmp_rec.DESIGNATION
               FROM      RUBRIQUE_EVALUATION
               WHERE              ID_RUBRIQUE_EVALUATION = cg$old_rec.ID_RUBRIQUE_EVALUATION
               FOR UPDATE NOWAIT;
            ELSE
               SELECT       ID_RUBRIQUE_EVALUATION
               ,            ID_EVALUATION
               ,            ID_RUBRIQUE
               ,            ORDRE
               ,            DESIGNATION
               INTO         cg$tmp_rec.ID_RUBRIQUE_EVALUATION
               ,            cg$tmp_rec.ID_EVALUATION
               ,            cg$tmp_rec.ID_RUBRIQUE
               ,            cg$tmp_rec.ORDRE
               ,            cg$tmp_rec.DESIGNATION
               FROM      RUBRIQUE_EVALUATION
               WHERE rowid = cg$old_rec.the_rowid
               FOR UPDATE NOWAIT;
            END IF;
        ELSE
            IF cg$old_rec.the_rowid is null THEN
               SELECT       ID_RUBRIQUE_EVALUATION
               ,            ID_EVALUATION
               ,            ID_RUBRIQUE
               ,            ORDRE
               ,            DESIGNATION
               INTO         cg$tmp_rec.ID_RUBRIQUE_EVALUATION
               ,            cg$tmp_rec.ID_EVALUATION
               ,            cg$tmp_rec.ID_RUBRIQUE
               ,            cg$tmp_rec.ORDRE
               ,            cg$tmp_rec.DESIGNATION
               FROM      RUBRIQUE_EVALUATION
               WHERE              ID_RUBRIQUE_EVALUATION = cg$old_rec.ID_RUBRIQUE_EVALUATION
               FOR UPDATE;
            ELSE
               SELECT       ID_RUBRIQUE_EVALUATION
               ,            ID_EVALUATION
               ,            ID_RUBRIQUE
               ,            ORDRE
               ,            DESIGNATION
               INTO         cg$tmp_rec.ID_RUBRIQUE_EVALUATION
               ,            cg$tmp_rec.ID_EVALUATION
               ,            cg$tmp_rec.ID_RUBRIQUE
               ,            cg$tmp_rec.ORDRE
               ,            cg$tmp_rec.DESIGNATION
               FROM      RUBRIQUE_EVALUATION
               WHERE rowid = cg$old_rec.the_rowid
               FOR UPDATE;
            END IF;
        END IF;

    EXCEPTION 
        WHEN cg$errors.cg$error THEN 
            cg$errors.raise_failure;
        WHEN cg$errors.resource_busy THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_LCK, cg$errors.ROW_LCK),
                           'E',
                           'ORA',
                           SQLCODE,
                           'cg$RUBRIQUE_EVALUATION.lck.resource_busy');
            cg$errors.raise_failure;
        WHEN no_data_found THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_DEL, cg$errors.ROW_DEL),
                           'E',
                           'ORA',
                           SQLCODE,
                           'cg$RUBRIQUE_EVALUATION.lck.no_data_found');
            cg$errors.raise_failure;
        WHEN OTHERS THEN
            cg$errors.push(SQLERRM,
                           'E',
                           'ORA',
                           SQLCODE,
                           'cg$RUBRIQUE_EVALUATION.lck.others');
            cg$errors.raise_failure;
    END;

-- Optional Columns

    IF (cg$old_ind.ID_RUBRIQUE) THEN
        IF (cg$tmp_rec.ID_RUBRIQUE IS NOT NULL
        AND cg$old_rec.ID_RUBRIQUE IS NOT NULL) THEN
            IF (cg$tmp_rec.ID_RUBRIQUE NOT LIKE cg$old_rec.ID_RUBRIQUE) THEN
                cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P25ID_RUBRIQUE
                    ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$RUBRIQUE_EVALUATION.lck');
                any_modified := TRUE;
            END IF;
        ELSIF (cg$tmp_rec.ID_RUBRIQUE IS NOT NULL
        OR cg$old_rec.ID_RUBRIQUE IS NOT NULL) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P25ID_RUBRIQUE
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$RUBRIQUE_EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;
    IF (cg$old_ind.DESIGNATION) THEN
        IF (cg$tmp_rec.DESIGNATION IS NOT NULL
        AND cg$old_rec.DESIGNATION IS NOT NULL) THEN
            IF (cg$tmp_rec.DESIGNATION NOT LIKE cg$old_rec.DESIGNATION) THEN
                cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P40DESIGNATION
                    ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$RUBRIQUE_EVALUATION.lck');
                any_modified := TRUE;
            END IF;
        ELSIF (cg$tmp_rec.DESIGNATION IS NOT NULL
        OR cg$old_rec.DESIGNATION IS NOT NULL) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P40DESIGNATION
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$RUBRIQUE_EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;

-- Mandatory Columns

    IF (cg$old_ind.ID_RUBRIQUE_EVALUATION) THEN
        IF (cg$tmp_rec.ID_RUBRIQUE_EVALUATION != cg$old_rec.ID_RUBRIQUE_EVALUATION) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P10ID_RUBRIQUE_EVALUATION
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$RUBRIQUE_EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;
    IF (cg$old_ind.ID_EVALUATION) THEN
        IF (cg$tmp_rec.ID_EVALUATION != cg$old_rec.ID_EVALUATION) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P20ID_EVALUATION
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$RUBRIQUE_EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;
    IF (cg$old_ind.ORDRE) THEN
        IF (cg$tmp_rec.ORDRE != cg$old_rec.ORDRE) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P30ORDRE
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$RUBRIQUE_EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;

    IF (any_modified) THEN
        cg$errors.raise_failure;
    END IF;

--  Application_logic Post-Lock <<Start>>
--  Application_logic Post-Lock << End >>

END lck;


BEGIN
      cg$ind_true.ID_RUBRIQUE_EVALUATION := TRUE;
      cg$ind_true.ID_EVALUATION := TRUE;
      cg$ind_true.ID_RUBRIQUE := TRUE;
      cg$ind_true.ORDRE := TRUE;
      cg$ind_true.DESIGNATION := TRUE;
   

END cg$RUBRIQUE_EVALUATION;
/

