
PROMPT Creating API Package Specification for Table 'EVALUATION'
--------------------------------------------------------------------------------
-- Name:        cg$EVALUATION
-- Description: EVALUATION table API package declarations
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE cg$EVALUATION IS

called_from_package BOOLEAN := FALSE;

--  Repository User-Defined Error Messages
EVE_PK CONSTANT VARCHAR2(240) := '';
EVE_EVE_UK CONSTANT VARCHAR2(240) := '';
EVE_UE_FK CONSTANT VARCHAR2(240) := '';
EVE_PRO_FK CONSTANT VARCHAR2(240) := '';
EVE_ENS_FK CONSTANT VARCHAR2(240) := '';
EVE_EC_FK CONSTANT VARCHAR2(240) := '';

--  Column default prompts. Format PSEQNO_COL
P10ID_EVALUATION CONSTANT VARCHAR2(240) := 'Eve Id';
P20NO_ENSEIGNANT CONSTANT VARCHAR2(240) := 'No Enseignant';
P30CODE_FORMATION CONSTANT VARCHAR2(240) := 'Frm Code Formation';
P40ANNEE_UNIVERSITAIRE CONSTANT VARCHAR2(240) := 'Annee Pro';
P60CODE_UE CONSTANT VARCHAR2(240) := 'Code Ue';
P70CODE_EC CONSTANT VARCHAR2(240) := 'Code Ec';
P75NO_EVALUATION CONSTANT VARCHAR2(240) := 'No Evaluation';
P80DESIGNATION CONSTANT VARCHAR2(240) := 'Designation';
P85ETAT CONSTANT VARCHAR2(240) := 'Etat';
P90PERIODE CONSTANT VARCHAR2(240) := 'Periode';
P100DEBUT_REPONSE CONSTANT VARCHAR2(240) := 'Debut Reponse';
P110FIN_REPONSE CONSTANT VARCHAR2(240) := 'Fin Reponse';

cg$row EVALUATION%ROWTYPE;

--  EVALUATION row type variable 
TYPE cg$row_type IS RECORD
(ID_EVALUATION cg$row.ID_EVALUATION%TYPE
,NO_ENSEIGNANT cg$row.NO_ENSEIGNANT%TYPE
,CODE_FORMATION cg$row.CODE_FORMATION%TYPE
,ANNEE_UNIVERSITAIRE cg$row.ANNEE_UNIVERSITAIRE%TYPE
,CODE_UE cg$row.CODE_UE%TYPE
,CODE_EC cg$row.CODE_EC%TYPE
,NO_EVALUATION cg$row.NO_EVALUATION%TYPE
,DESIGNATION cg$row.DESIGNATION%TYPE
,ETAT cg$row.ETAT%TYPE
,PERIODE cg$row.PERIODE%TYPE
,DEBUT_REPONSE cg$row.DEBUT_REPONSE%TYPE
,FIN_REPONSE cg$row.FIN_REPONSE%TYPE
,the_rowid ROWID)
;

--  EVALUATION indicator type variable
TYPE cg$ind_type IS RECORD
(ID_EVALUATION BOOLEAN DEFAULT FALSE
,NO_ENSEIGNANT BOOLEAN DEFAULT FALSE
,CODE_FORMATION BOOLEAN DEFAULT FALSE
,ANNEE_UNIVERSITAIRE BOOLEAN DEFAULT FALSE
,CODE_UE BOOLEAN DEFAULT FALSE
,CODE_EC BOOLEAN DEFAULT FALSE
,NO_EVALUATION BOOLEAN DEFAULT FALSE
,DESIGNATION BOOLEAN DEFAULT FALSE
,ETAT BOOLEAN DEFAULT FALSE
,PERIODE BOOLEAN DEFAULT FALSE
,DEBUT_REPONSE BOOLEAN DEFAULT FALSE
,FIN_REPONSE BOOLEAN DEFAULT FALSE);

cg$ind_true cg$ind_type;

--  EVALUATION primary key type variable
TYPE cg$pk_type IS RECORD
(ID_EVALUATION cg$row.ID_EVALUATION%TYPE
,the_rowid ROWID)
;

--  PL/SQL Table Type variable for triggers              

TYPE cg$tab_row_type IS RECORD 
(ID_EVALUATION EVALUATION.ID_EVALUATION%TYPE
,NO_ENSEIGNANT EVALUATION.NO_ENSEIGNANT%TYPE
,CODE_FORMATION EVALUATION.CODE_FORMATION%TYPE
,ANNEE_UNIVERSITAIRE EVALUATION.ANNEE_UNIVERSITAIRE%TYPE
,CODE_UE EVALUATION.CODE_UE%TYPE
,CODE_EC EVALUATION.CODE_EC%TYPE
,NO_EVALUATION EVALUATION.NO_EVALUATION%TYPE
,DESIGNATION EVALUATION.DESIGNATION%TYPE
,ETAT EVALUATION.ETAT%TYPE
,PERIODE EVALUATION.PERIODE%TYPE
,DEBUT_REPONSE EVALUATION.DEBUT_REPONSE%TYPE
,FIN_REPONSE EVALUATION.FIN_REPONSE%TYPE
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
END cg$EVALUATION;
/

