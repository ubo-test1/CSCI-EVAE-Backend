
PROMPT Creating API Package Specification for Table 'CG_REF_CODES'
--------------------------------------------------------------------------------
-- Name:        cg$CG_REF_CODES
-- Description: CG_REF_CODES table API package declarations
--------------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE cg$CG_REF_CODES IS

called_from_package BOOLEAN := FALSE;

--  Repository User-Defined Error Messages
CGRC_PK CONSTANT VARCHAR2(240) := '';

--  Column default prompts. Format PSEQNO_COL
P10ID_CGRC CONSTANT VARCHAR2(240) := 'Id Cgrc';
P15RV_DOMAIN CONSTANT VARCHAR2(240) := 'Rv Domain';
P20RV_LOW_VALUE CONSTANT VARCHAR2(240) := 'Rv Low Value';
P30RV_HIGH_VALUE CONSTANT VARCHAR2(240) := 'Rv High Value';
P40RV_ABBREVIATION CONSTANT VARCHAR2(240) := 'Rv Abbreviation';
P50RV_MEANING CONSTANT VARCHAR2(240) := 'Rv Meaning';

cg$row CG_REF_CODES%ROWTYPE;

--  CG_REF_CODES row type variable 
TYPE cg$row_type IS RECORD
(ID_CGRC cg$row.ID_CGRC%TYPE
,RV_DOMAIN cg$row.RV_DOMAIN%TYPE
,RV_LOW_VALUE cg$row.RV_LOW_VALUE%TYPE
,RV_HIGH_VALUE cg$row.RV_HIGH_VALUE%TYPE
,RV_ABBREVIATION cg$row.RV_ABBREVIATION%TYPE
,RV_MEANING cg$row.RV_MEANING%TYPE
,the_rowid ROWID)
;

--  CG_REF_CODES indicator type variable
TYPE cg$ind_type IS RECORD
(ID_CGRC BOOLEAN DEFAULT FALSE
,RV_DOMAIN BOOLEAN DEFAULT FALSE
,RV_LOW_VALUE BOOLEAN DEFAULT FALSE
,RV_HIGH_VALUE BOOLEAN DEFAULT FALSE
,RV_ABBREVIATION BOOLEAN DEFAULT FALSE
,RV_MEANING BOOLEAN DEFAULT FALSE);

cg$ind_true cg$ind_type;

--  CG_REF_CODES primary key type variable
TYPE cg$pk_type IS RECORD
(ID_CGRC cg$row.ID_CGRC%TYPE
,the_rowid ROWID)
;

--  PL/SQL Table Type variable for triggers              

TYPE cg$tab_row_type IS RECORD 
(ID_CGRC CG_REF_CODES.ID_CGRC%TYPE
,RV_DOMAIN CG_REF_CODES.RV_DOMAIN%TYPE
,RV_LOW_VALUE CG_REF_CODES.RV_LOW_VALUE%TYPE
,RV_HIGH_VALUE CG_REF_CODES.RV_HIGH_VALUE%TYPE
,RV_ABBREVIATION CG_REF_CODES.RV_ABBREVIATION%TYPE
,RV_MEANING CG_REF_CODES.RV_MEANING%TYPE
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
END cg$CG_REF_CODES;
/

