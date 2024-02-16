
PROMPT Creating API Package Body for Table 'EVALUATION'
--------------------------------------------------------------------------------
-- Name:        cg$EVALUATION
-- Description: EVALUATION table API package definitions
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY cg$EVALUATION IS

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
                   'cg$EVALUATION.raise_uk_not_updateable');
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
                   'cg$EVALUATION.raise_fk_not_transferable');
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
			IF (cg$ind.ID_EVALUATION = FALSE
      OR  cg$rec.ID_EVALUATION is NULL) THEN 
				SELECT EVE_SEQ.nextval
				INTO   cg$rec.ID_EVALUATION
				FROM   DUAL;
				cg$ind.ID_EVALUATION := TRUE;
			END IF;
    EXCEPTION WHEN others THEN
      cg$errors.push(SQLERRM, 'E', 'ORA', SQLCODE,
                     'cg$EVALUATION.up_autogen.ID_EVALUATION.OTHERS');
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
                    'cg$EVALUATION.up_autogen_columns');
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
    IF (cg$val_rec.ID_EVALUATION IS NULL) THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_MAND_COLUMN_ISNULL, cg$errors.VAL_MAND, P10ID_EVALUATION),
                       'E',
                       'API',
                       cg$errors.API_MAND_COLUMN_ISNULL,
                       loc);
    END IF;
    IF (cg$val_rec.NO_ENSEIGNANT IS NULL) THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_MAND_COLUMN_ISNULL, cg$errors.VAL_MAND, P20NO_ENSEIGNANT),
                       'E',
                       'API',
                       cg$errors.API_MAND_COLUMN_ISNULL,
                       loc);
    END IF;
    IF (cg$val_rec.CODE_FORMATION IS NULL) THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_MAND_COLUMN_ISNULL, cg$errors.VAL_MAND, P30CODE_FORMATION),
                       'E',
                       'API',
                       cg$errors.API_MAND_COLUMN_ISNULL,
                       loc);
    END IF;
    IF (cg$val_rec.ANNEE_UNIVERSITAIRE IS NULL) THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_MAND_COLUMN_ISNULL, cg$errors.VAL_MAND, P40ANNEE_UNIVERSITAIRE),
                       'E',
                       'API',
                       cg$errors.API_MAND_COLUMN_ISNULL,
                       loc);
    END IF;
    IF (cg$val_rec.CODE_UE IS NULL) THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_MAND_COLUMN_ISNULL, cg$errors.VAL_MAND, P60CODE_UE),
                       'E',
                       'API',
                       cg$errors.API_MAND_COLUMN_ISNULL,
                       loc);
    END IF;
    IF (cg$val_rec.NO_EVALUATION IS NULL) THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_MAND_COLUMN_ISNULL, cg$errors.VAL_MAND, P75NO_EVALUATION),
                       'E',
                       'API',
                       cg$errors.API_MAND_COLUMN_ISNULL,
                       loc);
    END IF;
    IF (cg$val_rec.DESIGNATION IS NULL) THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_MAND_COLUMN_ISNULL, cg$errors.VAL_MAND, P80DESIGNATION),
                       'E',
                       'API',
                       cg$errors.API_MAND_COLUMN_ISNULL,
                       loc);
    END IF;
    IF (cg$val_rec.ETAT IS NULL) THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_MAND_COLUMN_ISNULL, cg$errors.VAL_MAND, P85ETAT),
                       'E',
                       'API',
                       cg$errors.API_MAND_COLUMN_ISNULL,
                       loc);
    END IF;
    IF (cg$val_rec.DEBUT_REPONSE IS NULL) THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_MAND_COLUMN_ISNULL, cg$errors.VAL_MAND, P100DEBUT_REPONSE),
                       'E',
                       'API',
                       cg$errors.API_MAND_COLUMN_ISNULL,
                       loc);
    END IF;
    IF (cg$val_rec.FIN_REPONSE IS NULL) THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_MAND_COLUMN_ISNULL, cg$errors.VAL_MAND, P110FIN_REPONSE),
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
       SELECT    ID_EVALUATION
       ,         NO_ENSEIGNANT
       ,         CODE_FORMATION
       ,         ANNEE_UNIVERSITAIRE
       ,         CODE_UE
       ,         CODE_EC
       ,         NO_EVALUATION
       ,         DESIGNATION
       ,         ETAT
       ,         PERIODE
       ,         DEBUT_REPONSE
       ,         FIN_REPONSE
       , rowid
       INTO      cg$sel_rec.ID_EVALUATION
       ,         cg$sel_rec.NO_ENSEIGNANT
       ,         cg$sel_rec.CODE_FORMATION
       ,         cg$sel_rec.ANNEE_UNIVERSITAIRE
       ,         cg$sel_rec.CODE_UE
       ,         cg$sel_rec.CODE_EC
       ,         cg$sel_rec.NO_EVALUATION
       ,         cg$sel_rec.DESIGNATION
       ,         cg$sel_rec.ETAT
       ,         cg$sel_rec.PERIODE
       ,         cg$sel_rec.DEBUT_REPONSE
       ,         cg$sel_rec.FIN_REPONSE
       ,cg$sel_rec.the_rowid
       FROM   EVALUATION
       WHERE        ID_EVALUATION = cg$sel_rec.ID_EVALUATION;
    ELSE
       SELECT    ID_EVALUATION
       ,         NO_ENSEIGNANT
       ,         CODE_FORMATION
       ,         ANNEE_UNIVERSITAIRE
       ,         CODE_UE
       ,         CODE_EC
       ,         NO_EVALUATION
       ,         DESIGNATION
       ,         ETAT
       ,         PERIODE
       ,         DEBUT_REPONSE
       ,         FIN_REPONSE
       , rowid
       INTO      cg$sel_rec.ID_EVALUATION
       ,         cg$sel_rec.NO_ENSEIGNANT
       ,         cg$sel_rec.CODE_FORMATION
       ,         cg$sel_rec.ANNEE_UNIVERSITAIRE
       ,         cg$sel_rec.CODE_UE
       ,         cg$sel_rec.CODE_EC
       ,         cg$sel_rec.NO_EVALUATION
       ,         cg$sel_rec.DESIGNATION
       ,         cg$sel_rec.ETAT
       ,         cg$sel_rec.PERIODE
       ,         cg$sel_rec.DEBUT_REPONSE
       ,         cg$sel_rec.FIN_REPONSE
       ,cg$sel_rec.the_rowid
       FROM   EVALUATION
       WHERE  rowid = cg$sel_rec.the_rowid;
    END IF;

EXCEPTION WHEN OTHERS THEN
    cg$errors.push(SQLERRM,
                   'E',
                   'ORA',
                   SQLCODE,
                   'cg$EVALUATION.slct.others');
    cg$errors.raise_failure;

END slct;


--------------------------------------------------------------------------------
-- Name:        cascade_update
--
-- Description: Updates all child tables affected by a change to EVALUATION 
--
-- Parameters:  cg$rec     Record of EVALUATION current values
--              cg$old_rec Record of EVALUATION previous values
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
--              of each child table that references this tableEVALUATION 
--
-- Parameters:  cg$old_rec     Record of EVALUATION current values
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
--              child table that references this table EVALUATION 
--
-- Parameters:  cg$new_rec  New values for EVALUATION's domain key constraint columns 
--              cg$new_ind  Indicates changed EVALUATION's domain key constraint columns
--              cg$old_rec  Current values for EVALUATION's domain key constraint columns
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
-- Description: Delete all child tables affected by a delete to EVALUATION 
--
-- Parameters:  cg$rec     Record of EVALUATION current values
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
--              child table that references this tableEVALUATION 
--
-- Parameters:  cg$old_rec     Record of EVALUATION current values
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
--              of each child table that references this tableEVALUATION 
--
-- Parameters:  cg$old_rec     Record of EVALUATION current values
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
-- Parameters:  cg$rec     Record of EVALUATION current values
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
-- Parameters:  cg$rec     Record of EVALUATION current values
--------------------------------------------------------------------------------
PROCEDURE validate_domain(cg$rec IN OUT cg$row_type,
                          cg$ind IN cg$ind_type DEFAULT cg$ind_true)
IS
  dummy NUMBER;
  found BOOLEAN;
  no_tabview EXCEPTION;
  PRAGMA EXCEPTION_INIT(no_tabview, -942); 
BEGIN









    found := false;
    IF (cg$ind.ETAT AND cg$rec.ETAT IS NOT NULL) THEN
      FOR c IN (SELECT RV_LOW_VALUE, RV_HIGH_VALUE FROM CG_REF_CODES WHERE RV_DOMAIN = 'ETAT-EVALUATION' )
      LOOP
         IF (    c.RV_HIGH_VALUE IS NULL 
             AND cg$rec.ETAT = c.RV_LOW_VALUE )
         OR (    c.RV_HIGH_VALUE IS NOT NULL
             AND cg$rec.ETAT BETWEEN c.RV_LOW_VALUE AND c.RV_HIGH_VALUE )
         THEN
           found := true;
           EXIT;
         END IF;
      END LOOP;

      IF ( NOT found ) THEN
        cg$errors.push( cg$errors.MsgGetText(cg$errors.API_RV_LOOKUP_FAIL,
                                             cg$errors.APIMSG_RV_LOOKUP_FAIL,
                                             cg$rec.ETAT,'EVALUATION','ETAT'),
                        'E',
                        'API',
                        cg$errors.API_RV_LOOKUP_FAIL,
                        'cg$EVALUATION.v_domain.no_data_found' );
        cg$errors.raise_failure;
      END IF;
    END IF;





    NULL;

EXCEPTION
    WHEN cg$errors.cg$error THEN 
        cg$errors.raise_failure;
    WHEN no_tabview THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_RV_TAB_NOT_FOUND,
                                            cg$errors.APIMSG_RV_TAB_NOT_FOUND,
                                            'CG_REF_CODES','EVALUATION'),
                       'E',
                       'API',
                       cg$errors.API_RV_TAB_NOT_FOUND,
                       'cg$EVALUATION.v_domain.no_reftable_found');
        cg$errors.raise_failure;
    WHEN OTHERS THEN
        cg$errors.push(SQLERRM,
                       'E',
                       'ORA',
                       SQLCODE,
                       'cg$EVALUATION.v_domain.others');
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
    IF (con_name = 'EVE_PK') THEN
        cg$errors.push(nvl(EVE_PK 
                  ,cg$errors.MsgGetText(cg$errors.API_PK_CON_VIOLATED
					                 ,cg$errors.APIMSG_PK_VIOLAT
                                     ,'EVE_PK' 
                                     ,'EVALUATION')),
                       'E',
                       'API',
                       cg$errors.API_PK_CON_VIOLATED,
                       loc);
    ELSIF (con_name = 'EVE_EVE_UK') THEN
        cg$errors.push(nvl(EVE_EVE_UK 
                  ,cg$errors.MsgGetText(cg$errors.API_UQ_CON_VIOLATED
					                 ,cg$errors.APIMSG_UK_VIOLAT
                                     ,'EVE_EVE_UK' 
                                     ,'EVALUATION')),
                       'E',
                       'API',
                       cg$errors.API_UQ_CON_VIOLATED,
                       loc);

    ELSIF (con_name = 'EVE_UE_FK') THEN
        cg$errors.push(nvl(EVE_UE_FK 
                      ,cg$errors.MsgGetText(cg$errors.API_FK_CON_VIOLATED
					                 ,cg$errors.APIMSG_FK_VIOLAT
                                     ,'EVE_UE_FK' 
                                     ,'EVALUATION')),
                       'E',
                       'API',
                       cg$errors.API_FK_CON_VIOLATED,
                       loc);
    ELSIF (con_name = 'EVE_PRO_FK') THEN
        cg$errors.push(nvl(EVE_PRO_FK 
                      ,cg$errors.MsgGetText(cg$errors.API_FK_CON_VIOLATED
					                 ,cg$errors.APIMSG_FK_VIOLAT
                                     ,'EVE_PRO_FK' 
                                     ,'EVALUATION')),
                       'E',
                       'API',
                       cg$errors.API_FK_CON_VIOLATED,
                       loc);
    ELSIF (con_name = 'EVE_ENS_FK') THEN
        cg$errors.push(nvl(EVE_ENS_FK 
                      ,cg$errors.MsgGetText(cg$errors.API_FK_CON_VIOLATED
					                 ,cg$errors.APIMSG_FK_VIOLAT
                                     ,'EVE_ENS_FK' 
                                     ,'EVALUATION')),
                       'E',
                       'API',
                       cg$errors.API_FK_CON_VIOLATED,
                       loc);
    ELSIF (con_name = 'EVE_EC_FK') THEN
        cg$errors.push(nvl(EVE_EC_FK 
                      ,cg$errors.MsgGetText(cg$errors.API_FK_CON_VIOLATED
					                 ,cg$errors.APIMSG_FK_VIOLAT
                                     ,'EVE_EC_FK' 
                                     ,'EVALUATION')),
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

D85_ETAT CONSTANT EVALUATION.ETAT%TYPE := 'ELA';

BEGIN
--  Application_logic Pre-Insert <<Start>>
--  Application_logic Pre-Insert << End >>

--  Defaulted

    IF NOT (cg$ind.ETAT) THEN cg$rec.ETAT := D85_ETAT; END IF;
--  Auto-generated and uppercased columns

    up_autogen_columns(cg$rec, cg$ind, 'INS', do_ins);

    called_from_package := TRUE;

    IF (do_ins) THEN 
        validate_foreign_keys_ins(cg$rec);
        validate_arc(cg$rec);
        validate_domain(cg$rec);

        INSERT INTO EVALUATION
            (ID_EVALUATION
            ,NO_ENSEIGNANT
            ,CODE_FORMATION
            ,ANNEE_UNIVERSITAIRE
            ,CODE_UE
            ,CODE_EC
            ,NO_EVALUATION
            ,DESIGNATION
            ,ETAT
            ,PERIODE
            ,DEBUT_REPONSE
            ,FIN_REPONSE)
        VALUES
            (cg$rec.ID_EVALUATION
            ,cg$rec.NO_ENSEIGNANT
            ,cg$rec.CODE_FORMATION
            ,cg$rec.ANNEE_UNIVERSITAIRE
            ,cg$rec.CODE_UE
            ,cg$rec.CODE_EC
            ,cg$rec.NO_EVALUATION
            ,cg$rec.DESIGNATION
            ,cg$rec.ETAT
            ,cg$rec.PERIODE
            ,cg$rec.DEBUT_REPONSE
            ,cg$rec.FIN_REPONSE
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
        validate_mandatory(cg$rec, 'cg$EVALUATION.ins.mandatory_missing');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.check_violation THEN
        err_msg(SQLERRM, cg$errors.ERR_CHECK_CON, 'cg$EVALUATION.ins.check_violation');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.fk_violation THEN
        err_msg(SQLERRM, cg$errors.ERR_FOREIGN_KEY, 'cg$EVALUATION.ins.fk_violation');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.uk_violation THEN
        err_msg(SQLERRM, cg$errors.ERR_UNIQUE_KEY, 'cg$EVALUATION.ins.uk_violation');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN OTHERS THEN
        cg$errors.push(SQLERRM,
                       'E',
                       'ORA',
                       SQLCODE,
                       'cg$EVALUATION.ins.others');
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

 
    IF ( cg$pk.ID_EVALUATION IS NULL ) THEN          
      cg$upd_rec.ID_EVALUATION := cg$rec.ID_EVALUATION;
    ELSE
      cg$upd_rec.ID_EVALUATION := cg$pk.ID_EVALUATION;
    END IF;
    cg$old_rec.ID_EVALUATION := cg$upd_rec.ID_EVALUATION;

    IF ( cg$pk.the_rowid IS NULL ) THEN             
      cg$upd_rec.the_rowid := cg$rec.the_rowid;
    ELSE
      cg$upd_rec.the_rowid := cg$pk.the_rowid;
    END IF;
    cg$old_rec.the_rowid := cg$upd_rec.the_rowid;

    IF ( do_upd ) THEN

        slct(cg$upd_rec);


        --  Report error if attempt to update non updateable Primary Key EVE_PK
        IF (cg$ind.ID_EVALUATION AND cg$rec.ID_EVALUATION != cg$upd_rec.ID_EVALUATION) THEN
            raise_uk_not_updateable('EVE_PK');
        END IF;
        IF NOT (cg$ind.ID_EVALUATION) THEN
            cg$rec.ID_EVALUATION := cg$upd_rec.ID_EVALUATION;
        END IF;
        IF NOT (cg$ind.NO_ENSEIGNANT) THEN
            cg$rec.NO_ENSEIGNANT := cg$upd_rec.NO_ENSEIGNANT;
        END IF;
        IF NOT (cg$ind.CODE_FORMATION) THEN
            cg$rec.CODE_FORMATION := cg$upd_rec.CODE_FORMATION;
        END IF;
        IF NOT (cg$ind.ANNEE_UNIVERSITAIRE) THEN
            cg$rec.ANNEE_UNIVERSITAIRE := cg$upd_rec.ANNEE_UNIVERSITAIRE;
        END IF;
        IF NOT (cg$ind.CODE_UE) THEN
            cg$rec.CODE_UE := cg$upd_rec.CODE_UE;
        END IF;
        IF NOT (cg$ind.CODE_EC) THEN
            cg$rec.CODE_EC := cg$upd_rec.CODE_EC;
        END IF;
        IF NOT (cg$ind.NO_EVALUATION) THEN
            cg$rec.NO_EVALUATION := cg$upd_rec.NO_EVALUATION;
        END IF;
        IF NOT (cg$ind.DESIGNATION) THEN
            cg$rec.DESIGNATION := cg$upd_rec.DESIGNATION;
        END IF;
        IF NOT (cg$ind.ETAT) THEN
            cg$rec.ETAT := cg$upd_rec.ETAT;
        END IF;
        IF NOT (cg$ind.PERIODE) THEN
            cg$rec.PERIODE := cg$upd_rec.PERIODE;
        END IF;
        IF NOT (cg$ind.DEBUT_REPONSE) THEN
            cg$rec.DEBUT_REPONSE := cg$upd_rec.DEBUT_REPONSE;
        END IF;
        IF NOT (cg$ind.FIN_REPONSE) THEN
            cg$rec.FIN_REPONSE := cg$upd_rec.FIN_REPONSE;
        END IF;
    ELSE
	     -- Perform checks if called from a trigger
	     -- Indicators are only set on changed values
	     null;
        --  Report error if attempt to update non updateable Primary Key EVE_PK
        IF ( cg$ind.ID_EVALUATION ) THEN
          raise_uk_not_updateable('EVE_PK');
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
            UPDATE EVALUATION
            SET
              NO_ENSEIGNANT = cg$rec.NO_ENSEIGNANT
              ,CODE_FORMATION = cg$rec.CODE_FORMATION
              ,ANNEE_UNIVERSITAIRE = cg$rec.ANNEE_UNIVERSITAIRE
              ,CODE_UE = cg$rec.CODE_UE
              ,CODE_EC = cg$rec.CODE_EC
              ,NO_EVALUATION = cg$rec.NO_EVALUATION
              ,DESIGNATION = cg$rec.DESIGNATION
              ,ETAT = cg$rec.ETAT
              ,PERIODE = cg$rec.PERIODE
              ,DEBUT_REPONSE = cg$rec.DEBUT_REPONSE
              ,FIN_REPONSE = cg$rec.FIN_REPONSE
            WHERE  ID_EVALUATION = cg$rec.ID_EVALUATION;
            null;
          ELSE
            UPDATE EVALUATION
            SET
              NO_ENSEIGNANT = cg$rec.NO_ENSEIGNANT
              ,CODE_FORMATION = cg$rec.CODE_FORMATION
              ,ANNEE_UNIVERSITAIRE = cg$rec.ANNEE_UNIVERSITAIRE
              ,CODE_UE = cg$rec.CODE_UE
              ,CODE_EC = cg$rec.CODE_EC
              ,NO_EVALUATION = cg$rec.NO_EVALUATION
              ,DESIGNATION = cg$rec.DESIGNATION
              ,ETAT = cg$rec.ETAT
              ,PERIODE = cg$rec.PERIODE
              ,DEBUT_REPONSE = cg$rec.DEBUT_REPONSE
              ,FIN_REPONSE = cg$rec.FIN_REPONSE
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
        cg$table(idx).ID_EVALUATION := cg$rec.ID_EVALUATION;
        cg$tableind(idx).ID_EVALUATION := cg$ind.ID_EVALUATION;
        cg$table(idx).NO_ENSEIGNANT := cg$rec.NO_ENSEIGNANT;
        cg$tableind(idx).NO_ENSEIGNANT := cg$ind.NO_ENSEIGNANT;
        cg$table(idx).CODE_FORMATION := cg$rec.CODE_FORMATION;
        cg$tableind(idx).CODE_FORMATION := cg$ind.CODE_FORMATION;
        cg$table(idx).ANNEE_UNIVERSITAIRE := cg$rec.ANNEE_UNIVERSITAIRE;
        cg$tableind(idx).ANNEE_UNIVERSITAIRE := cg$ind.ANNEE_UNIVERSITAIRE;
        cg$table(idx).CODE_UE := cg$rec.CODE_UE;
        cg$tableind(idx).CODE_UE := cg$ind.CODE_UE;
        cg$table(idx).CODE_EC := cg$rec.CODE_EC;
        cg$tableind(idx).CODE_EC := cg$ind.CODE_EC;
        cg$table(idx).NO_EVALUATION := cg$rec.NO_EVALUATION;
        cg$tableind(idx).NO_EVALUATION := cg$ind.NO_EVALUATION;
        cg$table(idx).DESIGNATION := cg$rec.DESIGNATION;
        cg$tableind(idx).DESIGNATION := cg$ind.DESIGNATION;
        cg$table(idx).ETAT := cg$rec.ETAT;
        cg$tableind(idx).ETAT := cg$ind.ETAT;
        cg$table(idx).PERIODE := cg$rec.PERIODE;
        cg$tableind(idx).PERIODE := cg$ind.PERIODE;
        cg$table(idx).DEBUT_REPONSE := cg$rec.DEBUT_REPONSE;
        cg$tableind(idx).DEBUT_REPONSE := cg$ind.DEBUT_REPONSE;
        cg$table(idx).FIN_REPONSE := cg$rec.FIN_REPONSE;
        cg$tableind(idx).FIN_REPONSE := cg$ind.FIN_REPONSE;

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
        validate_mandatory(cg$rec, 'cg$EVALUATION.upd.upd_mandatory_null');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.check_violation THEN
        err_msg(SQLERRM, cg$errors.ERR_CHECK_CON, 'cg$EVALUATION.upd.check_violation');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.fk_violation THEN
        err_msg(SQLERRM, cg$errors.ERR_FOREIGN_KEY, 'cg$EVALUATION.upd.fk_violation');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN cg$errors.uk_violation THEN
        err_msg(SQLERRM, cg$errors.ERR_UNIQUE_KEY, 'cg$EVALUATION.upd.uk_violation');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN OTHERS THEN
        cg$errors.push(SQLERRM,
                       'E',
                       'ORA',
                       SQLCODE,
                       'cg$EVALUATION.upd.others');
        called_from_package := FALSE;
        cg$errors.raise_failure;
END upd;


----------------------------------------------------------------------------------------
-- Name:        domain_cascade_upd
--
-- Description: Update the Domain Constraint Key columns of EVALUATION when the
--              Cascade Update rule is Cascades and the domain table has been
--              updated. Called from <Domain Table pkg>.domain_cascade_update().
--
-- Parameters:  cg$rec      New values for EVALUATION's domain key constraint columns 
--              cg$ind      Indicates changed EVALUATION's domain key constraint columns
--              cg$old_rec  Current values for EVALUATION's domain key constraint columns
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
           cg$rec.ID_EVALUATION := cg$pk.ID_EVALUATION;
           slct(cg$rec);

           validate_foreign_keys_del(cg$rec);
           validate_domain_cascade_delete(cg$rec);    

           IF cg$pk.the_rowid is null THEN
              DELETE EVALUATION
              WHERE                    ID_EVALUATION = cg$pk.ID_EVALUATION;
           ELSE
              DELETE EVALUATION
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
        err_msg(SQLERRM, cg$errors.ERR_DELETE_RESTRICT, 'cg$EVALUATION.del.delete_restrict');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN no_data_found THEN
        cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_DEL, cg$errors.ROW_DEL),
                       'E',
                       'ORA',
                       SQLCODE,
                       'cg$EVALUATION.del.no_data_found');
        called_from_package := FALSE;
        cg$errors.raise_failure;
    WHEN OTHERS THEN
        cg$errors.push(SQLERRM,
                       'E',
                       'ORA',
                       SQLCODE,
                       'cg$EVALUATION.del.others');
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
               SELECT       ID_EVALUATION
               ,            NO_ENSEIGNANT
               ,            CODE_FORMATION
               ,            ANNEE_UNIVERSITAIRE
               ,            CODE_UE
               ,            CODE_EC
               ,            NO_EVALUATION
               ,            DESIGNATION
               ,            ETAT
               ,            PERIODE
               ,            DEBUT_REPONSE
               ,            FIN_REPONSE
               INTO         cg$tmp_rec.ID_EVALUATION
               ,            cg$tmp_rec.NO_ENSEIGNANT
               ,            cg$tmp_rec.CODE_FORMATION
               ,            cg$tmp_rec.ANNEE_UNIVERSITAIRE
               ,            cg$tmp_rec.CODE_UE
               ,            cg$tmp_rec.CODE_EC
               ,            cg$tmp_rec.NO_EVALUATION
               ,            cg$tmp_rec.DESIGNATION
               ,            cg$tmp_rec.ETAT
               ,            cg$tmp_rec.PERIODE
               ,            cg$tmp_rec.DEBUT_REPONSE
               ,            cg$tmp_rec.FIN_REPONSE
               FROM      EVALUATION
               WHERE              ID_EVALUATION = cg$old_rec.ID_EVALUATION
               FOR UPDATE NOWAIT;
            ELSE
               SELECT       ID_EVALUATION
               ,            NO_ENSEIGNANT
               ,            CODE_FORMATION
               ,            ANNEE_UNIVERSITAIRE
               ,            CODE_UE
               ,            CODE_EC
               ,            NO_EVALUATION
               ,            DESIGNATION
               ,            ETAT
               ,            PERIODE
               ,            DEBUT_REPONSE
               ,            FIN_REPONSE
               INTO         cg$tmp_rec.ID_EVALUATION
               ,            cg$tmp_rec.NO_ENSEIGNANT
               ,            cg$tmp_rec.CODE_FORMATION
               ,            cg$tmp_rec.ANNEE_UNIVERSITAIRE
               ,            cg$tmp_rec.CODE_UE
               ,            cg$tmp_rec.CODE_EC
               ,            cg$tmp_rec.NO_EVALUATION
               ,            cg$tmp_rec.DESIGNATION
               ,            cg$tmp_rec.ETAT
               ,            cg$tmp_rec.PERIODE
               ,            cg$tmp_rec.DEBUT_REPONSE
               ,            cg$tmp_rec.FIN_REPONSE
               FROM      EVALUATION
               WHERE rowid = cg$old_rec.the_rowid
               FOR UPDATE NOWAIT;
            END IF;
        ELSE
            IF cg$old_rec.the_rowid is null THEN
               SELECT       ID_EVALUATION
               ,            NO_ENSEIGNANT
               ,            CODE_FORMATION
               ,            ANNEE_UNIVERSITAIRE
               ,            CODE_UE
               ,            CODE_EC
               ,            NO_EVALUATION
               ,            DESIGNATION
               ,            ETAT
               ,            PERIODE
               ,            DEBUT_REPONSE
               ,            FIN_REPONSE
               INTO         cg$tmp_rec.ID_EVALUATION
               ,            cg$tmp_rec.NO_ENSEIGNANT
               ,            cg$tmp_rec.CODE_FORMATION
               ,            cg$tmp_rec.ANNEE_UNIVERSITAIRE
               ,            cg$tmp_rec.CODE_UE
               ,            cg$tmp_rec.CODE_EC
               ,            cg$tmp_rec.NO_EVALUATION
               ,            cg$tmp_rec.DESIGNATION
               ,            cg$tmp_rec.ETAT
               ,            cg$tmp_rec.PERIODE
               ,            cg$tmp_rec.DEBUT_REPONSE
               ,            cg$tmp_rec.FIN_REPONSE
               FROM      EVALUATION
               WHERE              ID_EVALUATION = cg$old_rec.ID_EVALUATION
               FOR UPDATE;
            ELSE
               SELECT       ID_EVALUATION
               ,            NO_ENSEIGNANT
               ,            CODE_FORMATION
               ,            ANNEE_UNIVERSITAIRE
               ,            CODE_UE
               ,            CODE_EC
               ,            NO_EVALUATION
               ,            DESIGNATION
               ,            ETAT
               ,            PERIODE
               ,            DEBUT_REPONSE
               ,            FIN_REPONSE
               INTO         cg$tmp_rec.ID_EVALUATION
               ,            cg$tmp_rec.NO_ENSEIGNANT
               ,            cg$tmp_rec.CODE_FORMATION
               ,            cg$tmp_rec.ANNEE_UNIVERSITAIRE
               ,            cg$tmp_rec.CODE_UE
               ,            cg$tmp_rec.CODE_EC
               ,            cg$tmp_rec.NO_EVALUATION
               ,            cg$tmp_rec.DESIGNATION
               ,            cg$tmp_rec.ETAT
               ,            cg$tmp_rec.PERIODE
               ,            cg$tmp_rec.DEBUT_REPONSE
               ,            cg$tmp_rec.FIN_REPONSE
               FROM      EVALUATION
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
                           'cg$EVALUATION.lck.resource_busy');
            cg$errors.raise_failure;
        WHEN no_data_found THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_DEL, cg$errors.ROW_DEL),
                           'E',
                           'ORA',
                           SQLCODE,
                           'cg$EVALUATION.lck.no_data_found');
            cg$errors.raise_failure;
        WHEN OTHERS THEN
            cg$errors.push(SQLERRM,
                           'E',
                           'ORA',
                           SQLCODE,
                           'cg$EVALUATION.lck.others');
            cg$errors.raise_failure;
    END;

-- Optional Columns

    IF (cg$old_ind.CODE_EC) THEN
        IF (cg$tmp_rec.CODE_EC IS NOT NULL
        AND cg$old_rec.CODE_EC IS NOT NULL) THEN
            IF (cg$tmp_rec.CODE_EC NOT LIKE cg$old_rec.CODE_EC) THEN
                cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P70CODE_EC
                    ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
                any_modified := TRUE;
            END IF;
        ELSIF (cg$tmp_rec.CODE_EC IS NOT NULL
        OR cg$old_rec.CODE_EC IS NOT NULL) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P70CODE_EC
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;
    IF (cg$old_ind.PERIODE) THEN
        IF (cg$tmp_rec.PERIODE IS NOT NULL
        AND cg$old_rec.PERIODE IS NOT NULL) THEN
            IF (cg$tmp_rec.PERIODE NOT LIKE cg$old_rec.PERIODE) THEN
                cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P90PERIODE
                    ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
                any_modified := TRUE;
            END IF;
        ELSIF (cg$tmp_rec.PERIODE IS NOT NULL
        OR cg$old_rec.PERIODE IS NOT NULL) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P90PERIODE
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;

-- Mandatory Columns

    IF (cg$old_ind.ID_EVALUATION) THEN
        IF (cg$tmp_rec.ID_EVALUATION != cg$old_rec.ID_EVALUATION) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P10ID_EVALUATION
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;
    IF (cg$old_ind.NO_ENSEIGNANT) THEN
        IF (cg$tmp_rec.NO_ENSEIGNANT != cg$old_rec.NO_ENSEIGNANT) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P20NO_ENSEIGNANT
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;
    IF (cg$old_ind.CODE_FORMATION) THEN
        IF (cg$tmp_rec.CODE_FORMATION != cg$old_rec.CODE_FORMATION) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P30CODE_FORMATION
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;
    IF (cg$old_ind.ANNEE_UNIVERSITAIRE) THEN
        IF (cg$tmp_rec.ANNEE_UNIVERSITAIRE != cg$old_rec.ANNEE_UNIVERSITAIRE) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P40ANNEE_UNIVERSITAIRE
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;
    IF (cg$old_ind.CODE_UE) THEN
        IF (cg$tmp_rec.CODE_UE != cg$old_rec.CODE_UE) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P60CODE_UE
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;
    IF (cg$old_ind.NO_EVALUATION) THEN
        IF (cg$tmp_rec.NO_EVALUATION != cg$old_rec.NO_EVALUATION) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P75NO_EVALUATION
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;
    IF (cg$old_ind.DESIGNATION) THEN
        IF (cg$tmp_rec.DESIGNATION != cg$old_rec.DESIGNATION) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P80DESIGNATION
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;
    IF (cg$old_ind.ETAT) THEN
        IF (cg$tmp_rec.ETAT != cg$old_rec.ETAT) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P85ETAT
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;
    IF (cg$old_ind.DEBUT_REPONSE) THEN
        IF (cg$tmp_rec.DEBUT_REPONSE != cg$old_rec.DEBUT_REPONSE) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P100DEBUT_REPONSE
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
            any_modified := TRUE;
        END IF;
    END IF;
    IF (cg$old_ind.FIN_REPONSE) THEN
        IF (cg$tmp_rec.FIN_REPONSE != cg$old_rec.FIN_REPONSE) THEN
            cg$errors.push(cg$errors.MsgGetText(cg$errors.API_ROW_MOD, cg$errors.ROW_MOD, P110FIN_REPONSE
                ),'E', 'API', CG$ERRORS.API_MODIFIED, 'cg$EVALUATION.lck');
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
      cg$ind_true.ID_EVALUATION := TRUE;
      cg$ind_true.NO_ENSEIGNANT := TRUE;
      cg$ind_true.CODE_FORMATION := TRUE;
      cg$ind_true.ANNEE_UNIVERSITAIRE := TRUE;
      cg$ind_true.CODE_UE := TRUE;
      cg$ind_true.CODE_EC := TRUE;
      cg$ind_true.NO_EVALUATION := TRUE;
      cg$ind_true.DESIGNATION := TRUE;
      cg$ind_true.ETAT := TRUE;
      cg$ind_true.PERIODE := TRUE;
      cg$ind_true.DEBUT_REPONSE := TRUE;
      cg$ind_true.FIN_REPONSE := TRUE;
   

END cg$EVALUATION;
/

