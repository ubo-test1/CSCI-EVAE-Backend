
PROMPT Creating API Package Specification for Table 'REPONSE_EVALUATION'
--------------------------------------------------------------------------------
-- Name:        cg$REPONSE_EVALUATION
-- Description: REPONSE_EVALUATION table API package declarations
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE cg$REPONSE_EVALUATION IS

called_from_package BOOLEAN := FALSE;

--  Repository User-Defined Error Messages
RPE_PK CONSTANT VARCHAR2(240) := '';
RPE_ETU_FK CONSTANT VARCHAR2(240) := '';
RPE_EVE_FK CONSTANT VARCHAR2(240) := '';

--  Column default prompts. Format PSEQNO_COL
P10ID_REPONSE_EVALUATION CONSTANT VARCHAR2(240) := 'Rpe Id';
P15ID_EVALUATION CONSTANT VARCHAR2(240) := 'Eve Id';
P20NO_ETUDIANT CONSTANT VARCHAR2(240) := 'No Etudiant Nat';
P30COMMENTAIRE CONSTANT VARCHAR2(240) := 'Commentaire';
P40NOM CONSTANT VARCHAR2(240) := 'Nom';
P50PRENOM CONSTANT VARCHAR2(240) := 'Prenom';

cg$row REPONSE_EVALUATION%ROWTYPE;

--  REPONSE_EVALUATION row type variable 
TYPE cg$row_type IS RECORD
(ID_REPONSE_EVALUATION cg$row.ID_REPONSE_EVALUATION%TYPE
,ID_EVALUATION cg$row.ID_EVALUATION%TYPE
,NO_ETUDIANT cg$row.NO_ETUDIANT%TYPE
,COMMENTAIRE cg$row.COMMENTAIRE%TYPE
,NOM cg$row.NOM%TYPE
,PRENOM cg$row.PRENOM%TYPE
,the_rowid ROWID)
;

--  REPONSE_EVALUATION indicator type variable
TYPE cg$ind_type IS RECORD
(ID_REPONSE_EVALUATION BOOLEAN DEFAULT FALSE
,ID_EVALUATION BOOLEAN DEFAULT FALSE
,NO_ETUDIANT BOOLEAN DEFAULT FALSE
,COMMENTAIRE BOOLEAN DEFAULT FALSE
,NOM BOOLEAN DEFAULT FALSE
,PRENOM BOOLEAN DEFAULT FALSE);

cg$ind_true cg$ind_type;

--  REPONSE_EVALUATION primary key type variable
TYPE cg$pk_type IS RECORD
(ID_REPONSE_EVALUATION cg$row.ID_REPONSE_EVALUATION%TYPE
,the_rowid ROWID)
;

--  PL/SQL Table Type variable for triggers              

TYPE cg$tab_row_type IS RECORD 
(ID_REPONSE_EVALUATION REPONSE_EVALUATION.ID_REPONSE_EVALUATION%TYPE
,ID_EVALUATION REPONSE_EVALUATION.ID_EVALUATION%TYPE
,NO_ETUDIANT REPONSE_EVALUATION.NO_ETUDIANT%TYPE
,COMMENTAIRE REPONSE_EVALUATION.COMMENTAIRE%TYPE
,NOM REPONSE_EVALUATION.NOM%TYPE
,PRENOM REPONSE_EVALUATION.PRENOM%TYPE
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
END cg$REPONSE_EVALUATION;
/

