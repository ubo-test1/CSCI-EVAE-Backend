
PROMPT Creating Trigger Logic for Table 'CG_REF_CODES'
PROMPT Creating Before Insert Statement Trigger on 'CG_REF_CODES'
CREATE OR REPLACE TRIGGER cg$BIS_CG_REF_CODES
BEFORE INSERT ON CG_REF_CODES
BEGIN
--  Application_logic Pre-Before-Insert-statement <<Start>>
--  Application_logic Pre-Before-Insert-statement << End >>

    cg$CG_REF_CODES.cg$table.DELETE;
    cg$CG_REF_CODES.cg$tableind.DELETE;
    cg$CG_REF_CODES.idx := 1;

--  Application_logic Post-Before-Insert-statement <<Start>>
--  Application_logic Post-Before-Insert-statement << End >>
END;
/
  

PROMPT Creating Before Insert Row Trigger on 'CG_REF_CODES'
CREATE OR REPLACE TRIGGER cg$BIR_CG_REF_CODES 
BEFORE INSERT ON CG_REF_CODES FOR EACH ROW
DECLARE
    cg$rec cg$CG_REF_CODES.cg$row_type;
    cg$ind cg$CG_REF_CODES.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Insert-row <<Start>>
--  Application_logic Pre-Before-Insert-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_CGRC := :new.ID_CGRC;
    IF (:new.ID_CGRC IS NULL) THEN
        cg$ind.ID_CGRC := FALSE;
    ELSE
        cg$ind.ID_CGRC := TRUE;
    END IF;
    cg$rec.RV_DOMAIN := :new.RV_DOMAIN;
    cg$ind.RV_DOMAIN := TRUE;
    cg$rec.RV_LOW_VALUE := :new.RV_LOW_VALUE;
    cg$ind.RV_LOW_VALUE := TRUE;
    cg$rec.RV_HIGH_VALUE := :new.RV_HIGH_VALUE;
    cg$ind.RV_HIGH_VALUE := TRUE;
    cg$rec.RV_ABBREVIATION := :new.RV_ABBREVIATION;
    cg$ind.RV_ABBREVIATION := TRUE;
    cg$rec.RV_MEANING := :new.RV_MEANING;
    cg$ind.RV_MEANING := TRUE;

    if not (cg$CG_REF_CODES.called_from_package) then
        cg$CG_REF_CODES.validate_arc(cg$rec);
        cg$CG_REF_CODES.validate_domain(cg$rec);

        cg$CG_REF_CODES.ins(cg$rec, cg$ind, FALSE);
        cg$CG_REF_CODES.called_from_package := FALSE;
    end if;

    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).ID_CGRC := cg$rec.ID_CGRC;
    cg$CG_REF_CODES.cg$tableind(cg$CG_REF_CODES.idx).ID_CGRC := cg$ind.ID_CGRC;

    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).RV_DOMAIN := cg$rec.RV_DOMAIN;
    cg$CG_REF_CODES.cg$tableind(cg$CG_REF_CODES.idx).RV_DOMAIN := cg$ind.RV_DOMAIN;

    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).RV_LOW_VALUE := cg$rec.RV_LOW_VALUE;
    cg$CG_REF_CODES.cg$tableind(cg$CG_REF_CODES.idx).RV_LOW_VALUE := cg$ind.RV_LOW_VALUE;

    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).RV_HIGH_VALUE := cg$rec.RV_HIGH_VALUE;
    cg$CG_REF_CODES.cg$tableind(cg$CG_REF_CODES.idx).RV_HIGH_VALUE := cg$ind.RV_HIGH_VALUE;

    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).RV_ABBREVIATION := cg$rec.RV_ABBREVIATION;
    cg$CG_REF_CODES.cg$tableind(cg$CG_REF_CODES.idx).RV_ABBREVIATION := cg$ind.RV_ABBREVIATION;

    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).RV_MEANING := cg$rec.RV_MEANING;
    cg$CG_REF_CODES.cg$tableind(cg$CG_REF_CODES.idx).RV_MEANING := cg$ind.RV_MEANING;


    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).action_performed := 'INS';
    cg$CG_REF_CODES.idx := cg$CG_REF_CODES.idx + 1;

    :new.ID_CGRC := cg$rec.ID_CGRC;
    :new.RV_DOMAIN := cg$rec.RV_DOMAIN;
    :new.RV_LOW_VALUE := cg$rec.RV_LOW_VALUE;
    :new.RV_HIGH_VALUE := cg$rec.RV_HIGH_VALUE;
    :new.RV_ABBREVIATION := cg$rec.RV_ABBREVIATION;
    :new.RV_MEANING := cg$rec.RV_MEANING;

--  Application_logic Post-Before-Insert-row <<Start>>
--  Application_logic Post-Before-Insert-row << End >>
END;
/


-- No application logic defined for Trigger cg$AIR_CG_REF_CODES, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AIR_CG_REF_CODES
AFTER INSERT ON CG_REF_CODES FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AIR_CG_REF_CODES
/

PROMPT Creating After Insert Statement Trigger on 'CG_REF_CODES'
CREATE OR REPLACE TRIGGER cg$AIS_CG_REF_CODES 
AFTER INSERT ON CG_REF_CODES
DECLARE
    idx      BINARY_INTEGER := cg$CG_REF_CODES.cg$table.FIRST;
    cg$rec   cg$CG_REF_CODES.cg$row_type;
    cg$old_rec   cg$CG_REF_CODES.cg$row_type;
    fk_check INTEGER;
BEGIN
--  Application_logic Pre-After-Insert-statement <<Start>>
--  Application_logic Pre-After-Insert-statement << End >>


    IF NOT (cg$CG_REF_CODES.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

	    IF (cg$CG_REF_CODES.cg$table(idx).action_performed = 'INS') THEN
                cg$rec.ID_CGRC := cg$CG_REF_CODES.cg$table(idx).ID_CGRC;
                cg$rec.RV_DOMAIN := cg$CG_REF_CODES.cg$table(idx).RV_DOMAIN;
                cg$rec.RV_LOW_VALUE := cg$CG_REF_CODES.cg$table(idx).RV_LOW_VALUE;
                cg$rec.RV_HIGH_VALUE := cg$CG_REF_CODES.cg$table(idx).RV_HIGH_VALUE;
                cg$rec.RV_ABBREVIATION := cg$CG_REF_CODES.cg$table(idx).RV_ABBREVIATION;
                cg$rec.RV_MEANING := cg$CG_REF_CODES.cg$table(idx).RV_MEANING;

                cg$CG_REF_CODES.validate_foreign_keys_ins(cg$rec);

                cg$CG_REF_CODES.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$CG_REF_CODES.cg$tableind(idx),
                                                    'INS'
                                                  );
            END IF;
            idx := cg$CG_REF_CODES.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Insert-statement <<Start>>
--  Application_logic Post-After-Insert-statement << End >>

END;
/


    

PROMPT Creating Before Update Statement Trigger on 'CG_REF_CODES'
CREATE OR REPLACE TRIGGER cg$BUS_CG_REF_CODES
BEFORE UPDATE ON CG_REF_CODES
BEGIN
--  Application_logic Pre-Before-Update-statement <<Start>>
--  Application_logic Pre-Before-Update-statement << End >>

    cg$CG_REF_CODES.cg$table.DELETE;
    cg$CG_REF_CODES.cg$tableind.DELETE;
    cg$CG_REF_CODES.idx := 1;

--  Application_logic Post-Before-Update-statement <<Start>>
--  Application_logic Post-Before-Update-statement << End >>

END;
/
  

PROMPT Creating Before Update Row Trigger on 'CG_REF_CODES'
CREATE OR REPLACE TRIGGER cg$BUR_CG_REF_CODES 
BEFORE UPDATE ON CG_REF_CODES FOR EACH ROW
DECLARE
    cg$rec     cg$CG_REF_CODES.cg$row_type;
    cg$ind     cg$CG_REF_CODES.cg$ind_type;
    cg$old_rec cg$CG_REF_CODES.cg$row_type;  
BEGIN
--  Application_logic Pre-Before-Update-row <<Start>>
--  Application_logic Pre-Before-Update-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_CGRC := :new.ID_CGRC;
    cg$ind.ID_CGRC :=    (:new.ID_CGRC IS NULL AND :old.ID_CGRC IS NOT NULL )
                        OR (:new.ID_CGRC IS NOT NULL AND :old.ID_CGRC IS NULL)
                        OR NOT(:new.ID_CGRC = :old.ID_CGRC) ;
    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).ID_CGRC := :old.ID_CGRC;
    cg$rec.RV_DOMAIN := :new.RV_DOMAIN;
    cg$ind.RV_DOMAIN :=    (:new.RV_DOMAIN IS NULL AND :old.RV_DOMAIN IS NOT NULL )
                        OR (:new.RV_DOMAIN IS NOT NULL AND :old.RV_DOMAIN IS NULL)
                        OR NOT(:new.RV_DOMAIN = :old.RV_DOMAIN) ;
    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).RV_DOMAIN := :old.RV_DOMAIN;
    cg$rec.RV_LOW_VALUE := :new.RV_LOW_VALUE;
    cg$ind.RV_LOW_VALUE :=    (:new.RV_LOW_VALUE IS NULL AND :old.RV_LOW_VALUE IS NOT NULL )
                        OR (:new.RV_LOW_VALUE IS NOT NULL AND :old.RV_LOW_VALUE IS NULL)
                        OR NOT(:new.RV_LOW_VALUE = :old.RV_LOW_VALUE) ;
    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).RV_LOW_VALUE := :old.RV_LOW_VALUE;
    cg$rec.RV_HIGH_VALUE := :new.RV_HIGH_VALUE;
    cg$ind.RV_HIGH_VALUE :=    (:new.RV_HIGH_VALUE IS NULL AND :old.RV_HIGH_VALUE IS NOT NULL )
                        OR (:new.RV_HIGH_VALUE IS NOT NULL AND :old.RV_HIGH_VALUE IS NULL)
                        OR NOT(:new.RV_HIGH_VALUE = :old.RV_HIGH_VALUE) ;
    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).RV_HIGH_VALUE := :old.RV_HIGH_VALUE;
    cg$rec.RV_ABBREVIATION := :new.RV_ABBREVIATION;
    cg$ind.RV_ABBREVIATION :=    (:new.RV_ABBREVIATION IS NULL AND :old.RV_ABBREVIATION IS NOT NULL )
                        OR (:new.RV_ABBREVIATION IS NOT NULL AND :old.RV_ABBREVIATION IS NULL)
                        OR NOT(:new.RV_ABBREVIATION = :old.RV_ABBREVIATION) ;
    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).RV_ABBREVIATION := :old.RV_ABBREVIATION;
    cg$rec.RV_MEANING := :new.RV_MEANING;
    cg$ind.RV_MEANING :=    (:new.RV_MEANING IS NULL AND :old.RV_MEANING IS NOT NULL )
                        OR (:new.RV_MEANING IS NOT NULL AND :old.RV_MEANING IS NULL)
                        OR NOT(:new.RV_MEANING = :old.RV_MEANING) ;
    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).RV_MEANING := :old.RV_MEANING;


    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).action_performed := 'UPD';     -- nkovoor
    cg$CG_REF_CODES.idx := cg$CG_REF_CODES.idx + 1;

    if not (cg$CG_REF_CODES.called_from_package) then
        cg$CG_REF_CODES.validate_arc(cg$rec);
        cg$CG_REF_CODES.validate_domain(cg$rec, cg$ind);
        cg$CG_REF_CODES.validate_domain_cascade_update(cg$old_rec);   

        cg$CG_REF_CODES.upd(cg$rec, cg$ind, FALSE);
        cg$CG_REF_CODES.called_from_package := FALSE;
    end if;

    :new.RV_DOMAIN := cg$rec.RV_DOMAIN;
    :new.RV_LOW_VALUE := cg$rec.RV_LOW_VALUE;
    :new.RV_HIGH_VALUE := cg$rec.RV_HIGH_VALUE;
    :new.RV_ABBREVIATION := cg$rec.RV_ABBREVIATION;
    :new.RV_MEANING := cg$rec.RV_MEANING;
--  Application_logic Post-Before-Update-row <<Start>>
--  Application_logic Post-Before-Update-row << End >>
END;
/

-- No application logic defined for Trigger cg$AUR_CG_REF_CODES, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AUR_CG_REF_CODES
AFTER UPDATE ON CG_REF_CODES FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AUR_CG_REF_CODES
/






PROMPT Creating After Update Statement Trigger on 'CG_REF_CODES'
CREATE OR REPLACE TRIGGER cg$AUS_CG_REF_CODES 
AFTER UPDATE ON CG_REF_CODES
DECLARE
    idx        BINARY_INTEGER := cg$CG_REF_CODES.cg$table.FIRST;
    cg$old_rec cg$CG_REF_CODES.cg$row_type;
    cg$rec     cg$CG_REF_CODES.cg$row_type;
    cg$ind     cg$CG_REF_CODES.cg$ind_type;
BEGIN
--  Application_logic Pre-After-Update-statement <<Start>>
--  Application_logic Pre-After-Update-statement << End >>

    WHILE idx IS NOT NULL LOOP

        IF (cg$CG_REF_CODES.cg$table(idx).action_performed = 'UPD') THEN
            cg$old_rec.ID_CGRC := cg$CG_REF_CODES.cg$table(idx).ID_CGRC;
            cg$old_rec.RV_DOMAIN := cg$CG_REF_CODES.cg$table(idx).RV_DOMAIN;
            cg$old_rec.RV_LOW_VALUE := cg$CG_REF_CODES.cg$table(idx).RV_LOW_VALUE;
            cg$old_rec.RV_HIGH_VALUE := cg$CG_REF_CODES.cg$table(idx).RV_HIGH_VALUE;
            cg$old_rec.RV_ABBREVIATION := cg$CG_REF_CODES.cg$table(idx).RV_ABBREVIATION;
            cg$old_rec.RV_MEANING := cg$CG_REF_CODES.cg$table(idx).RV_MEANING;

            IF NOT (cg$CG_REF_CODES.called_from_package) THEN
                idx := cg$CG_REF_CODES.cg$table.NEXT(idx);

                IF (idx IS NOT NULL) THEN
                cg$rec.ID_CGRC := cg$CG_REF_CODES.cg$table(idx).ID_CGRC;
                cg$ind.ID_CGRC := updating('ID_CGRC');
                cg$rec.RV_DOMAIN := cg$CG_REF_CODES.cg$table(idx).RV_DOMAIN;
                cg$ind.RV_DOMAIN := updating('RV_DOMAIN');
                cg$rec.RV_LOW_VALUE := cg$CG_REF_CODES.cg$table(idx).RV_LOW_VALUE;
                cg$ind.RV_LOW_VALUE := updating('RV_LOW_VALUE');
                cg$rec.RV_HIGH_VALUE := cg$CG_REF_CODES.cg$table(idx).RV_HIGH_VALUE;
                cg$ind.RV_HIGH_VALUE := updating('RV_HIGH_VALUE');
                cg$rec.RV_ABBREVIATION := cg$CG_REF_CODES.cg$table(idx).RV_ABBREVIATION;
                cg$ind.RV_ABBREVIATION := updating('RV_ABBREVIATION');
                cg$rec.RV_MEANING := cg$CG_REF_CODES.cg$table(idx).RV_MEANING;
                cg$ind.RV_MEANING := updating('RV_MEANING');

                cg$CG_REF_CODES.validate_foreign_keys_upd(cg$rec, cg$old_rec, cg$ind);

                cg$CG_REF_CODES.upd_denorm2( cg$rec,
                                               cg$CG_REF_CODES.cg$tableind(idx)
                                             );
                cg$CG_REF_CODES.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$CG_REF_CODES.cg$tableind(idx)
				        				                  );
                cg$CG_REF_CODES.cascade_update(cg$rec, cg$old_rec);
                cg$CG_REF_CODES.domain_cascade_update(cg$rec, cg$ind, cg$old_rec);             
        
                cg$CG_REF_CODES.called_from_package := FALSE;
                END IF;
            END IF; 
        END IF;
        idx := cg$CG_REF_CODES.cg$table.NEXT(idx);
    END LOOP;

    cg$CG_REF_CODES.cg$table.DELETE;

--  Application_logic Post-After-Update-statement <<Start>>
--  Application_logic Post-After-Update-statement << End >>

END;
/

PROMPT Creating Before Delete Statement Trigger on 'CG_REF_CODES'
CREATE OR REPLACE TRIGGER cg$BDS_CG_REF_CODES
BEFORE DELETE ON CG_REF_CODES
BEGIN
--  Application_logic Pre-Before-Delete-statement <<Start>>
--  Application_logic Pre-Before-Delete-statement << End >>

    cg$CG_REF_CODES.cg$table.DELETE;
    cg$CG_REF_CODES.cg$tableind.DELETE;
    cg$CG_REF_CODES.idx := 1;

--  Application_logic Post-Before-Delete-statement <<Start>>
--  Application_logic Post-Before-Delete-statement << End >>
END;
/
  

PROMPT Creating Before Delete Row Trigger on 'CG_REF_CODES'
CREATE OR REPLACE TRIGGER cg$BDR_CG_REF_CODES 
BEFORE DELETE ON CG_REF_CODES FOR EACH ROW
DECLARE
    cg$pk cg$CG_REF_CODES.cg$pk_type;
    cg$rec cg$CG_REF_CODES.cg$row_type;
    cg$ind cg$CG_REF_CODES.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Delete-row <<Start>>
--  Application_logic Pre-Before-Delete-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$pk.ID_CGRC  := :old.ID_CGRC;
    cg$rec.ID_CGRC := :old.ID_CGRC;
    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).ID_CGRC := :old.ID_CGRC;


    cg$CG_REF_CODES.cg$table(cg$CG_REF_CODES.idx).action_performed := 'DEL';
    cg$CG_REF_CODES.idx := cg$CG_REF_CODES.idx + 1;

    if not (cg$CG_REF_CODES.called_from_package) then
        cg$CG_REF_CODES.validate_domain_cascade_delete(cg$rec);   
        cg$CG_REF_CODES.del(cg$pk, FALSE);
        cg$CG_REF_CODES.called_from_package := FALSE;
    end if;

--  Application_logic Post-Before-Delete-row <<Start>>
--  Application_logic Post-Before-Delete-row << End >>
END;
/

-- No application logic defined for Trigger cg$ADR_CG_REF_CODES, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$ADR_CG_REF_CODES
AFTER DELETE ON CG_REF_CODES FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$ADR_CG_REF_CODES
/

PROMPT Creating After Delete Statement Trigger on 'CG_REF_CODES'
CREATE OR REPLACE TRIGGER cg$ADS_CG_REF_CODES 
AFTER DELETE ON CG_REF_CODES
DECLARE
    idx        BINARY_INTEGER := cg$CG_REF_CODES.cg$table.FIRST;
    cg$rec   cg$CG_REF_CODES.cg$row_type;
    cg$old_rec   cg$CG_REF_CODES.cg$row_type;
BEGIN
--  Application_logic Pre-After-Delete-statement <<Start>>
--  Application_logic Pre-After-Delete-statement << End >>

    IF NOT (cg$CG_REF_CODES.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

            IF (cg$CG_REF_CODES.cg$table(idx).action_performed = 'DEL') THEN
                cg$rec.ID_CGRC := cg$CG_REF_CODES.cg$table(idx).ID_CGRC;
                cg$CG_REF_CODES.cg$tableind(idx).ID_CGRC := TRUE;
                cg$rec.RV_DOMAIN := cg$CG_REF_CODES.cg$table(idx).RV_DOMAIN;
                cg$CG_REF_CODES.cg$tableind(idx).RV_DOMAIN := TRUE;
                cg$rec.RV_LOW_VALUE := cg$CG_REF_CODES.cg$table(idx).RV_LOW_VALUE;
                cg$CG_REF_CODES.cg$tableind(idx).RV_LOW_VALUE := TRUE;
                cg$rec.RV_HIGH_VALUE := cg$CG_REF_CODES.cg$table(idx).RV_HIGH_VALUE;
                cg$CG_REF_CODES.cg$tableind(idx).RV_HIGH_VALUE := TRUE;
                cg$rec.RV_ABBREVIATION := cg$CG_REF_CODES.cg$table(idx).RV_ABBREVIATION;
                cg$CG_REF_CODES.cg$tableind(idx).RV_ABBREVIATION := TRUE;
                cg$rec.RV_MEANING := cg$CG_REF_CODES.cg$table(idx).RV_MEANING;
                cg$CG_REF_CODES.cg$tableind(idx).RV_MEANING := TRUE;

                cg$CG_REF_CODES.validate_foreign_keys_del(cg$rec);
                cg$CG_REF_CODES.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$CG_REF_CODES.cg$tableind(idx),
                                                    'DEL'
                                                  );
	
                cg$CG_REF_CODES.cascade_delete(cg$rec);
                cg$CG_REF_CODES.domain_cascade_delete(cg$rec);             
            END IF;
            idx := cg$CG_REF_CODES.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Delete-statement <<Start>>
--  Application_logic Post-After-Delete-statement << End >>

END;
/



