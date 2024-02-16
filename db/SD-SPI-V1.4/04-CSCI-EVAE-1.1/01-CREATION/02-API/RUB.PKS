
PROMPT Creating API Package Specification for Table 'RUBRIQUE'
--------------------------------------------------------------------------------
-- Name:        cg$RUBRIQUE
-- Description: RUBRIQUE table API package declarations
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE cg$RUBRIQUE IS

called_from_package BOOLEAN := FALSE;

--  Repository User-Defined Error Messages
RUB_PK CONSTANT VARCHAR2(240) := '';
RUB_ENS_FK CONSTANT VARCHAR2(240) := '';

--  Column default prompts. Format PSEQNO_COL
P10ID_RUBRIQUE CONSTANT VARCHAR2(240) := 'Rub Id';
P20TYPE CONSTANT VARCHAR2(240) := 'Rub Type';
P30NO_ENSEIGNANT CONSTANT VARCHAR2(240) := 'No Enseignant';
P40DESIGNATION CONSTANT VARCHAR2(240) := 'Designation';
P50ORDRE CONSTANT VARCHAR2(240) := 'Ordre';

cg$row RUBRIQUE%ROWTYPE;

--  RUBRIQUE row type variable 
TYPE cg$row_type IS RECORD
(ID_RUBRIQUE cg$row.ID_RUBRIQUE%TYPE
,TYPE cg$row.TYPE%TYPE
,NO_ENSEIGNANT cg$row.NO_ENSEIGNANT%TYPE
,DESIGNATION cg$row.DESIGNATION%TYPE
,ORDRE cg$row.ORDRE%TYPE
,the_rowid ROWID)
;

--  RUBRIQUE indicator type variable
TYPE cg$ind_type IS RECORD
(ID_RUBRIQUE BOOLEAN DEFAULT FALSE
,TYPE BOOLEAN DEFAULT FALSE
,NO_ENSEIGNANT BOOLEAN DEFAULT FALSE
,DESIGNATION BOOLEAN DEFAULT FALSE
,ORDRE BOOLEAN DEFAULT FALSE);

cg$ind_true cg$ind_type;

--  RUBRIQUE primary key type variable
TYPE cg$pk_type IS RECORD
(ID_RUBRIQUE cg$row.ID_RUBRIQUE%TYPE
,the_rowid ROWID)
;

--  PL/SQL Table Type variable for triggers              

TYPE cg$tab_row_type IS RECORD 
(ID_RUBRIQUE RUBRIQUE.ID_RUBRIQUE%TYPE
,TYPE RUBRIQUE.TYPE%TYPE
,NO_ENSEIGNANT RUBRIQUE.NO_ENSEIGNANT%TYPE
,DESIGNATION RUBRIQUE.DESIGNATION%TYPE
,ORDRE RUBRIQUE.ORDRE%TYPE
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
END cg$RUBRIQUE;
/

