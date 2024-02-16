
PROMPT Creating API Package Specification for Table 'RUBRIQUE_EVALUATION'
--------------------------------------------------------------------------------
-- Name:        cg$RUBRIQUE_EVALUATION
-- Description: RUBRIQUE_EVALUATION table API package declarations
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE cg$RUBRIQUE_EVALUATION IS

called_from_package BOOLEAN := FALSE;

--  Repository User-Defined Error Messages
REV_PK CONSTANT VARCHAR2(240) := '';
REV_RUB_FK CONSTANT VARCHAR2(240) := '';
REV_EVE_FK CONSTANT VARCHAR2(240) := '';

--  Column default prompts. Format PSEQNO_COL
P10ID_RUBRIQUE_EVALUATION CONSTANT VARCHAR2(240) := 'Rev Id';
P20ID_EVALUATION CONSTANT VARCHAR2(240) := 'Eve Id';
P25ID_RUBRIQUE CONSTANT VARCHAR2(240) := 'Rub Id';
P30ORDRE CONSTANT VARCHAR2(240) := 'Ordre';
P40DESIGNATION CONSTANT VARCHAR2(240) := 'Designation';

cg$row RUBRIQUE_EVALUATION%ROWTYPE;

--  RUBRIQUE_EVALUATION row type variable 
TYPE cg$row_type IS RECORD
(ID_RUBRIQUE_EVALUATION cg$row.ID_RUBRIQUE_EVALUATION%TYPE
,ID_EVALUATION cg$row.ID_EVALUATION%TYPE
,ID_RUBRIQUE cg$row.ID_RUBRIQUE%TYPE
,ORDRE cg$row.ORDRE%TYPE
,DESIGNATION cg$row.DESIGNATION%TYPE
,the_rowid ROWID)
;

--  RUBRIQUE_EVALUATION indicator type variable
TYPE cg$ind_type IS RECORD
(ID_RUBRIQUE_EVALUATION BOOLEAN DEFAULT FALSE
,ID_EVALUATION BOOLEAN DEFAULT FALSE
,ID_RUBRIQUE BOOLEAN DEFAULT FALSE
,ORDRE BOOLEAN DEFAULT FALSE
,DESIGNATION BOOLEAN DEFAULT FALSE);

cg$ind_true cg$ind_type;

--  RUBRIQUE_EVALUATION primary key type variable
TYPE cg$pk_type IS RECORD
(ID_RUBRIQUE_EVALUATION cg$row.ID_RUBRIQUE_EVALUATION%TYPE
,the_rowid ROWID)
;

--  PL/SQL Table Type variable for triggers              

TYPE cg$tab_row_type IS RECORD 
(ID_RUBRIQUE_EVALUATION RUBRIQUE_EVALUATION.ID_RUBRIQUE_EVALUATION%TYPE
,ID_EVALUATION RUBRIQUE_EVALUATION.ID_EVALUATION%TYPE
,ID_RUBRIQUE RUBRIQUE_EVALUATION.ID_RUBRIQUE%TYPE
,ORDRE RUBRIQUE_EVALUATION.ORDRE%TYPE
,DESIGNATION RUBRIQUE_EVALUATION.DESIGNATION%TYPE
,action_performed VARCHAR2(4)
) ;


TYPE cg$table_type IS TABLE OF cg$tab_row_type
INDEX BY BINARY_INTEGER;

cg$table cg$table_type;

TYPE cg$tableind_type IS TABLE OF cg$ind_type
     INDEX BY BINARY_INTEGER;
cg$tableind cg$tableind_type;
idx BINARY_INTEGER := 1;

PROCEDURE   ins(cg$rec IN OUT cg$row_type,
                cg$ind IN OUT cg$ind_type,
                do_ins IN BOOLEAN DEFAULT TRUE
               );
PROCEDURE   upd(cg$rec             IN OUT cg$row_type,
                cg$ind             IN OUT cg$ind_type,
                do_upd             IN BOOLEAN     DEFAULT TRUE,
                cg$pk              IN cg$row_type DEFAULT NULL      
               );
PROCEDURE   del(cg$pk  IN cg$pk_type,
                do_del IN BOOLEAN DEFAULT TRUE
               );
PROCEDURE   lck(cg$old_rec  IN cg$row_type,
                cg$old_ind  IN cg$ind_type,
                nowait_flag IN BOOLEAN DEFAULT TRUE
               );
PROCEDURE   slct(cg$sel_rec IN OUT cg$row_type);

PROCEDURE   validate_arc(cg$rec IN OUT cg$row_type);

PROCEDURE   validate_domain(cg$rec IN OUT cg$row_type,
                            cg$ind IN cg$ind_type DEFAULT cg$ind_true);

PROCEDURE   validate_foreign_keys_ins(cg$rec IN cg$row_type);
PROCEDURE   validate_foreign_keys_upd(cg$rec IN cg$row_type, 
                                      cg$old_rec IN cg$row_type, 
                                      cg$ind IN cg$ind_type);
PROCEDURE   validate_foreign_keys_del(cg$rec IN cg$row_type);

PROCEDURE   validate_domain_cascade_delete(cg$old_rec IN cg$row_type);        
PROCEDURE   validate_domain_cascade_update(cg$old_rec IN cg$row_type);        

PROCEDURE   cascade_update(cg$new_rec IN OUT cg$row_type,
                           cg$old_rec IN cg$row_type );
PROCEDURE   domain_cascade_update(cg$new_rec IN OUT cg$row_type,              
                                  cg$new_ind IN OUT cg$ind_type,
                                  cg$old_rec IN     cg$row_type);
PROCEDURE   domain_cascade_upd( cg$rec     IN OUT cg$row_type,                
                                cg$ind     IN OUT cg$ind_type,
                                cg$old_rec IN     cg$row_type);

PROCEDURE   cascade_delete(cg$old_rec IN OUT cg$row_type);
PROCEDURE   domain_cascade_delete(cg$old_rec IN cg$row_type);

PROCEDURE   upd_denorm2( cg$rec IN cg$row_type,
                         cg$ind IN cg$ind_type );
PROCEDURE   upd_oper_denorm2( cg$rec IN cg$row_type,
                              cg$old_rec IN cg$row_type,
                              cg$ind IN cg$ind_type,
                              operation IN VARCHAR2 DEFAULT 'UPD' );
END cg$RUBRIQUE_EVALUATION;
/

