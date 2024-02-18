
PROMPT Creating API Package Specification for Table 'QUESTION_EVALUATION'
--------------------------------------------------------------------------------
-- Name:        cg$QUESTION_EVALUATION
-- Description: QUESTION_EVALUATION table API package declarations
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE cg$QUESTION_EVALUATION IS

called_from_package BOOLEAN := FALSE;

--  Repository User-Defined Error Messages
QEV_PK CONSTANT VARCHAR2(240) := '';
QEV_QUA_FK CONSTANT VARCHAR2(240) := '';
QEV_QUE_FK CONSTANT VARCHAR2(240) := '';
QEV_REV_FK CONSTANT VARCHAR2(240) := '';

--  Column default prompts. Format PSEQNO_COL
P10ID_QUESTION_EVALUATION CONSTANT VARCHAR2(240) := 'Qev Id';
P20ID_RUBRIQUE_EVALUATION CONSTANT VARCHAR2(240) := 'Rev Id';
P50ID_QUESTION CONSTANT VARCHAR2(240) := 'Que Id';
P60ID_QUALIFICATIF CONSTANT VARCHAR2(240) := 'Qua Id';
P70ORDRE CONSTANT VARCHAR2(240) := 'Ordre';
P80INTITULE CONSTANT VARCHAR2(240) := 'Intitule';

cg$row QUESTION_EVALUATION%ROWTYPE;

--  QUESTION_EVALUATION row type variable 
TYPE cg$row_type IS RECORD
(ID_QUESTION_EVALUATION cg$row.ID_QUESTION_EVALUATION%TYPE
,ID_RUBRIQUE_EVALUATION cg$row.ID_RUBRIQUE_EVALUATION%TYPE
,ID_QUESTION cg$row.ID_QUESTION%TYPE
,ID_QUALIFICATIF cg$row.ID_QUALIFICATIF%TYPE
,ORDRE cg$row.ORDRE%TYPE
,INTITULE cg$row.INTITULE%TYPE
,the_rowid ROWID)
;

--  QUESTION_EVALUATION indicator type variable
TYPE cg$ind_type IS RECORD
(ID_QUESTION_EVALUATION BOOLEAN DEFAULT FALSE
,ID_RUBRIQUE_EVALUATION BOOLEAN DEFAULT FALSE
,ID_QUESTION BOOLEAN DEFAULT FALSE
,ID_QUALIFICATIF BOOLEAN DEFAULT FALSE
,ORDRE BOOLEAN DEFAULT FALSE
,INTITULE BOOLEAN DEFAULT FALSE);

cg$ind_true cg$ind_type;

--  QUESTION_EVALUATION primary key type variable
TYPE cg$pk_type IS RECORD
(ID_QUESTION_EVALUATION cg$row.ID_QUESTION_EVALUATION%TYPE
,the_rowid ROWID)
;

--  PL/SQL Table Type variable for triggers              

TYPE cg$tab_row_type IS RECORD 
(ID_QUESTION_EVALUATION QUESTION_EVALUATION.ID_QUESTION_EVALUATION%TYPE
,ID_RUBRIQUE_EVALUATION QUESTION_EVALUATION.ID_RUBRIQUE_EVALUATION%TYPE
,ID_QUESTION QUESTION_EVALUATION.ID_QUESTION%TYPE
,ID_QUALIFICATIF QUESTION_EVALUATION.ID_QUALIFICATIF%TYPE
,ORDRE QUESTION_EVALUATION.ORDRE%TYPE
,INTITULE QUESTION_EVALUATION.INTITULE%TYPE
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
END cg$QUESTION_EVALUATION;
/

