
PROMPT Creating API Package Specification for Table 'AUTHENTIFICATION'
--------------------------------------------------------------------------------
-- Name:        cg$AUTHENTIFICATION
-- Description: AUTHENTIFICATION table API package declarations
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE cg$AUTHENTIFICATION IS

called_from_package BOOLEAN := FALSE;

--  Repository User-Defined Error Messages
AUT_PK CONSTANT VARCHAR2(240) := '';
AUT_ETU_FK CONSTANT VARCHAR2(240) := '';
AUT_ENS_FK CONSTANT VARCHAR2(240) := '';

--  Column default prompts. Format PSEQNO_COL
P10ID_CONNECTION CONSTANT VARCHAR2(240) := 'Id Connection';
P12ROLE CONSTANT VARCHAR2(240) := 'Role';
P15LOGIN_CONNECTION CONSTANT VARCHAR2(240) := 'Mail Connexion';
P20PSEUDO_CONNECTION CONSTANT VARCHAR2(240) := 'Pseudo Connexion';
P30MOT_PASSE CONSTANT VARCHAR2(240) := 'Mot Passe';
P32NO_ENSEIGNANT CONSTANT VARCHAR2(240) := 'Ens No Enseignant';
P34NO_ETUDIANT CONSTANT VARCHAR2(240) := 'Etu No Etudiant';

cg$row AUTHENTIFICATION%ROWTYPE;

--  AUTHENTIFICATION row type variable 
TYPE cg$row_type IS RECORD
(ID_CONNECTION cg$row.ID_CONNECTION%TYPE
,ROLE cg$row.ROLE%TYPE
,LOGIN_CONNECTION cg$row.LOGIN_CONNECTION%TYPE
,PSEUDO_CONNECTION cg$row.PSEUDO_CONNECTION%TYPE
,MOT_PASSE cg$row.MOT_PASSE%TYPE
,NO_ENSEIGNANT cg$row.NO_ENSEIGNANT%TYPE
,NO_ETUDIANT cg$row.NO_ETUDIANT%TYPE
,the_rowid ROWID)
;

--  AUTHENTIFICATION indicator type variable
TYPE cg$ind_type IS RECORD
(ID_CONNECTION BOOLEAN DEFAULT FALSE
,ROLE BOOLEAN DEFAULT FALSE
,LOGIN_CONNECTION BOOLEAN DEFAULT FALSE
,PSEUDO_CONNECTION BOOLEAN DEFAULT FALSE
,MOT_PASSE BOOLEAN DEFAULT FALSE
,NO_ENSEIGNANT BOOLEAN DEFAULT FALSE
,NO_ETUDIANT BOOLEAN DEFAULT FALSE);

cg$ind_true cg$ind_type;

--  AUTHENTIFICATION primary key type variable
TYPE cg$pk_type IS RECORD
(ID_CONNECTION cg$row.ID_CONNECTION%TYPE
,the_rowid ROWID)
;

--  PL/SQL Table Type variable for triggers              

TYPE cg$tab_row_type IS RECORD 
(ID_CONNECTION AUTHENTIFICATION.ID_CONNECTION%TYPE
,ROLE AUTHENTIFICATION.ROLE%TYPE
,LOGIN_CONNECTION AUTHENTIFICATION.LOGIN_CONNECTION%TYPE
,PSEUDO_CONNECTION AUTHENTIFICATION.PSEUDO_CONNECTION%TYPE
,MOT_PASSE AUTHENTIFICATION.MOT_PASSE%TYPE
,NO_ENSEIGNANT AUTHENTIFICATION.NO_ENSEIGNANT%TYPE
,NO_ETUDIANT AUTHENTIFICATION.NO_ETUDIANT%TYPE
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
END cg$AUTHENTIFICATION;
/

