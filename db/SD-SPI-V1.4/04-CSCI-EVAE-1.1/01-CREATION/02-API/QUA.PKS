
PROMPT Creating API Package Specification for Table 'QUALIFICATIF'
--------------------------------------------------------------------------------
-- Name:        cg$QUALIFICATIF
-- Description: QUALIFICATIF table API package declarations
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE cg$QUALIFICATIF IS

called_from_package BOOLEAN := FALSE;

--  Repository User-Defined Error Messages
QUA_PK CONSTANT VARCHAR2(240) := '';

--  Column default prompts. Format PSEQNO_COL
P1ID_QUALIFICATIF CONSTANT VARCHAR2(240) := 'Qua Id';
P2MAXIMAL CONSTANT VARCHAR2(240) := 'Maximal';
P3MINIMAL CONSTANT VARCHAR2(240) := 'Minimal';

cg$row QUALIFICATIF%ROWTYPE;

--  QUALIFICATIF row type variable 
TYPE cg$row_type IS RECORD
(ID_QUALIFICATIF cg$row.ID_QUALIFICATIF%TYPE
,MAXIMAL cg$row.MAXIMAL%TYPE
,MINIMAL cg$row.MINIMAL%TYPE
,the_rowid ROWID)
;

--  QUALIFICATIF indicator type variable
TYPE cg$ind_type IS RECORD
(ID_QUALIFICATIF BOOLEAN DEFAULT FALSE
,MAXIMAL BOOLEAN DEFAULT FALSE
,MINIMAL BOOLEAN DEFAULT FALSE);

cg$ind_true cg$ind_type;

--  QUALIFICATIF primary key type variable
TYPE cg$pk_type IS RECORD
(ID_QUALIFICATIF cg$row.ID_QUALIFICATIF%TYPE
,the_rowid ROWID)
;

--  PL/SQL Table Type variable for triggers              

TYPE cg$tab_row_type IS RECORD 
(ID_QUALIFICATIF QUALIFICATIF.ID_QUALIFICATIF%TYPE
,MAXIMAL QUALIFICATIF.MAXIMAL%TYPE
,MINIMAL QUALIFICATIF.MINIMAL%TYPE
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
END cg$QUALIFICATIF;
/

