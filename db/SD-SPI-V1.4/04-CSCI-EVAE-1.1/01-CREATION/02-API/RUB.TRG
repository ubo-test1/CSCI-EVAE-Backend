
PROMPT Creating Trigger Logic for Table 'RUBRIQUE'
PROMPT Creating Before Insert Statement Trigger on 'RUBRIQUE'
CREATE OR REPLACE TRIGGER cg$BIS_RUBRIQUE
BEFORE INSERT ON RUBRIQUE
BEGIN
--  Application_logic Pre-Before-Insert-statement <<Start>>
--  Application_logic Pre-Before-Insert-statement << End >>

    cg$RUBRIQUE.cg$table.DELETE;
    cg$RUBRIQUE.cg$tableind.DELETE;
    cg$RUBRIQUE.idx := 1;

--  Application_logic Post-Before-Insert-statement <<Start>>
--  Application_logic Post-Before-Insert-statement << End >>
END;
/
  

PROMPT Creating Before Insert Row Trigger on 'RUBRIQUE'
CREATE OR REPLACE TRIGGER cg$BIR_RUBRIQUE 
BEFORE INSERT ON RUBRIQUE FOR EACH ROW
DECLARE
    cg$rec cg$RUBRIQUE.cg$row_type;
    cg$ind cg$RUBRIQUE.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Insert-row <<Start>>
--  Application_logic Pre-Before-Insert-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_RUBRIQUE := :new.ID_RUBRIQUE;
    IF (:new.ID_RUBRIQUE IS NULL) THEN
        cg$ind.ID_RUBRIQUE := FALSE;
    ELSE
        cg$ind.ID_RUBRIQUE := TRUE;
    END IF;
    cg$rec.TYPE := :new.TYPE;
    cg$ind.TYPE := TRUE;
    cg$rec.NO_ENSEIGNANT := :new.NO_ENSEIGNANT;
    cg$ind.NO_ENSEIGNANT := TRUE;
    cg$rec.DESIGNATION := :new.DESIGNATION;
    cg$ind.DESIGNATION := TRUE;
    cg$rec.ORDRE := :new.ORDRE;
    cg$ind.ORDRE := TRUE;

    if not (cg$RUBRIQUE.called_from_package) then
        cg$RUBRIQUE.validate_arc(cg$rec);
        cg$RUBRIQUE.validate_domain(cg$rec);

        cg$RUBRIQUE.ins(cg$rec, cg$ind, FALSE);
        cg$RUBRIQUE.called_from_package := FALSE;
    end if;

    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).ID_RUBRIQUE := cg$rec.ID_RUBRIQUE;
    cg$RUBRIQUE.cg$tableind(cg$RUBRIQUE.idx).ID_RUBRIQUE := cg$ind.ID_RUBRIQUE;

    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).TYPE := cg$rec.TYPE;
    cg$RUBRIQUE.cg$tableind(cg$RUBRIQUE.idx).TYPE := cg$ind.TYPE;

    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).NO_ENSEIGNANT := cg$rec.NO_ENSEIGNANT;
    cg$RUBRIQUE.cg$tableind(cg$RUBRIQUE.idx).NO_ENSEIGNANT := cg$ind.NO_ENSEIGNANT;

    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).DESIGNATION := cg$rec.DESIGNATION;
    cg$RUBRIQUE.cg$tableind(cg$RUBRIQUE.idx).DESIGNATION := cg$ind.DESIGNATION;

    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).ORDRE := cg$rec.ORDRE;
    cg$RUBRIQUE.cg$tableind(cg$RUBRIQUE.idx).ORDRE := cg$ind.ORDRE;


    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).action_performed := 'INS';
    cg$RUBRIQUE.idx := cg$RUBRIQUE.idx + 1;

    :new.ID_RUBRIQUE := cg$rec.ID_RUBRIQUE;
    :new.TYPE := cg$rec.TYPE;
    :new.NO_ENSEIGNANT := cg$rec.NO_ENSEIGNANT;
    :new.DESIGNATION := cg$rec.DESIGNATION;
    :new.ORDRE := cg$rec.ORDRE;

--  Application_logic Post-Before-Insert-row <<Start>>
--  Application_logic Post-Before-Insert-row << End >>
END;
/


-- No application logic defined for Trigger cg$AIR_RUBRIQUE, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AIR_RUBRIQUE
AFTER INSERT ON RUBRIQUE FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AIR_RUBRIQUE
/

PROMPT Creating After Insert Statement Trigger on 'RUBRIQUE'
CREATE OR REPLACE TRIGGER cg$AIS_RUBRIQUE 
AFTER INSERT ON RUBRIQUE
DECLARE
    idx      BINARY_INTEGER := cg$RUBRIQUE.cg$table.FIRST;
    cg$rec   cg$RUBRIQUE.cg$row_type;
    cg$old_rec   cg$RUBRIQUE.cg$row_type;
    fk_check INTEGER;
BEGIN
--  Application_logic Pre-After-Insert-statement <<Start>>
--  Application_logic Pre-After-Insert-statement << End >>


    IF NOT (cg$RUBRIQUE.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

	    IF (cg$RUBRIQUE.cg$table(idx).action_performed = 'INS') THEN
                cg$rec.ID_RUBRIQUE := cg$RUBRIQUE.cg$table(idx).ID_RUBRIQUE;
                cg$rec.TYPE := cg$RUBRIQUE.cg$table(idx).TYPE;
                cg$rec.NO_ENSEIGNANT := cg$RUBRIQUE.cg$table(idx).NO_ENSEIGNANT;
                cg$rec.DESIGNATION := cg$RUBRIQUE.cg$table(idx).DESIGNATION;
                cg$rec.ORDRE := cg$RUBRIQUE.cg$table(idx).ORDRE;

                cg$RUBRIQUE.validate_foreign_keys_ins(cg$rec);

                cg$RUBRIQUE.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$RUBRIQUE.cg$tableind(idx),
                                                    'INS'
                                                  );
            END IF;
            idx := cg$RUBRIQUE.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Insert-statement <<Start>>
--  Application_logic Post-After-Insert-statement << End >>

END;
/


    

PROMPT Creating Before Update Statement Trigger on 'RUBRIQUE'
CREATE OR REPLACE TRIGGER cg$BUS_RUBRIQUE
BEFORE UPDATE ON RUBRIQUE
BEGIN
--  Application_logic Pre-Before-Update-statement <<Start>>
--  Application_logic Pre-Before-Update-statement << End >>

    cg$RUBRIQUE.cg$table.DELETE;
    cg$RUBRIQUE.cg$tableind.DELETE;
    cg$RUBRIQUE.idx := 1;

--  Application_logic Post-Before-Update-statement <<Start>>
--  Application_logic Post-Before-Update-statement << End >>

END;
/
  

PROMPT Creating Before Update Row Trigger on 'RUBRIQUE'
CREATE OR REPLACE TRIGGER cg$BUR_RUBRIQUE 
BEFORE UPDATE ON RUBRIQUE FOR EACH ROW
DECLARE
    cg$rec     cg$RUBRIQUE.cg$row_type;
    cg$ind     cg$RUBRIQUE.cg$ind_type;
    cg$old_rec cg$RUBRIQUE.cg$row_type;  
BEGIN
--  Application_logic Pre-Before-Update-row <<Start>>
--  Application_logic Pre-Before-Update-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_RUBRIQUE := :new.ID_RUBRIQUE;
    cg$ind.ID_RUBRIQUE :=    (:new.ID_RUBRIQUE IS NULL AND :old.ID_RUBRIQUE IS NOT NULL )
                        OR (:new.ID_RUBRIQUE IS NOT NULL AND :old.ID_RUBRIQUE IS NULL)
                        OR NOT(:new.ID_RUBRIQUE = :old.ID_RUBRIQUE) ;
    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).ID_RUBRIQUE := :old.ID_RUBRIQUE;
    cg$rec.TYPE := :new.TYPE;
    cg$ind.TYPE :=    (:new.TYPE IS NULL AND :old.TYPE IS NOT NULL )
                        OR (:new.TYPE IS NOT NULL AND :old.TYPE IS NULL)
                        OR NOT(:new.TYPE = :old.TYPE) ;
    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).TYPE := :old.TYPE;
    cg$rec.NO_ENSEIGNANT := :new.NO_ENSEIGNANT;
    cg$ind.NO_ENSEIGNANT :=    (:new.NO_ENSEIGNANT IS NULL AND :old.NO_ENSEIGNANT IS NOT NULL )
                        OR (:new.NO_ENSEIGNANT IS NOT NULL AND :old.NO_ENSEIGNANT IS NULL)
                        OR NOT(:new.NO_ENSEIGNANT = :old.NO_ENSEIGNANT) ;
    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).NO_ENSEIGNANT := :old.NO_ENSEIGNANT;
    cg$rec.DESIGNATION := :new.DESIGNATION;
    cg$ind.DESIGNATION :=    (:new.DESIGNATION IS NULL AND :old.DESIGNATION IS NOT NULL )
                        OR (:new.DESIGNATION IS NOT NULL AND :old.DESIGNATION IS NULL)
                        OR NOT(:new.DESIGNATION = :old.DESIGNATION) ;
    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).DESIGNATION := :old.DESIGNATION;
    cg$rec.ORDRE := :new.ORDRE;
    cg$ind.ORDRE :=    (:new.ORDRE IS NULL AND :old.ORDRE IS NOT NULL )
                        OR (:new.ORDRE IS NOT NULL AND :old.ORDRE IS NULL)
                        OR NOT(:new.ORDRE = :old.ORDRE) ;
    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).ORDRE := :old.ORDRE;


    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).action_performed := 'UPD';     -- nkovoor
    cg$RUBRIQUE.idx := cg$RUBRIQUE.idx + 1;

    if not (cg$RUBRIQUE.called_from_package) then
        cg$RUBRIQUE.validate_arc(cg$rec);
        cg$RUBRIQUE.validate_domain(cg$rec, cg$ind);
        cg$RUBRIQUE.validate_domain_cascade_update(cg$old_rec);   

        cg$RUBRIQUE.upd(cg$rec, cg$ind, FALSE);
        cg$RUBRIQUE.called_from_package := FALSE;
    end if;

    :new.TYPE := cg$rec.TYPE;
    :new.NO_ENSEIGNANT := cg$rec.NO_ENSEIGNANT;
    :new.DESIGNATION := cg$rec.DESIGNATION;
    :new.ORDRE := cg$rec.ORDRE;
--  Application_logic Post-Before-Update-row <<Start>>
--  Application_logic Post-Before-Update-row << End >>
END;
/

-- No application logic defined for Trigger cg$AUR_RUBRIQUE, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AUR_RUBRIQUE
AFTER UPDATE ON RUBRIQUE FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AUR_RUBRIQUE
/






PROMPT Creating After Update Statement Trigger on 'RUBRIQUE'
CREATE OR REPLACE TRIGGER cg$AUS_RUBRIQUE 
AFTER UPDATE ON RUBRIQUE
DECLARE
    idx        BINARY_INTEGER := cg$RUBRIQUE.cg$table.FIRST;
    cg$old_rec cg$RUBRIQUE.cg$row_type;
    cg$rec     cg$RUBRIQUE.cg$row_type;
    cg$ind     cg$RUBRIQUE.cg$ind_type;
BEGIN
--  Application_logic Pre-After-Update-statement <<Start>>
--  Application_logic Pre-After-Update-statement << End >>

    WHILE idx IS NOT NULL LOOP

        IF (cg$RUBRIQUE.cg$table(idx).action_performed = 'UPD') THEN
            cg$old_rec.ID_RUBRIQUE := cg$RUBRIQUE.cg$table(idx).ID_RUBRIQUE;
            cg$old_rec.TYPE := cg$RUBRIQUE.cg$table(idx).TYPE;
            cg$old_rec.NO_ENSEIGNANT := cg$RUBRIQUE.cg$table(idx).NO_ENSEIGNANT;
            cg$old_rec.DESIGNATION := cg$RUBRIQUE.cg$table(idx).DESIGNATION;
            cg$old_rec.ORDRE := cg$RUBRIQUE.cg$table(idx).ORDRE;

            IF NOT (cg$RUBRIQUE.called_from_package) THEN
                idx := cg$RUBRIQUE.cg$table.NEXT(idx);

                IF (idx IS NOT NULL) THEN
                cg$rec.ID_RUBRIQUE := cg$RUBRIQUE.cg$table(idx).ID_RUBRIQUE;
                cg$ind.ID_RUBRIQUE := updating('ID_RUBRIQUE');
                cg$rec.TYPE := cg$RUBRIQUE.cg$table(idx).TYPE;
                cg$ind.TYPE := updating('TYPE');
                cg$rec.NO_ENSEIGNANT := cg$RUBRIQUE.cg$table(idx).NO_ENSEIGNANT;
                cg$ind.NO_ENSEIGNANT := updating('NO_ENSEIGNANT');
                cg$rec.DESIGNATION := cg$RUBRIQUE.cg$table(idx).DESIGNATION;
                cg$ind.DESIGNATION := updating('DESIGNATION');
                cg$rec.ORDRE := cg$RUBRIQUE.cg$table(idx).ORDRE;
                cg$ind.ORDRE := updating('ORDRE');

                cg$RUBRIQUE.validate_foreign_keys_upd(cg$rec, cg$old_rec, cg$ind);

                cg$RUBRIQUE.upd_denorm2( cg$rec,
                                               cg$RUBRIQUE.cg$tableind(idx)
                                             );
                cg$RUBRIQUE.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$RUBRIQUE.cg$tableind(idx)
				        				                  );
                cg$RUBRIQUE.cascade_update(cg$rec, cg$old_rec);
                cg$RUBRIQUE.domain_cascade_update(cg$rec, cg$ind, cg$old_rec);             
        
                cg$RUBRIQUE.called_from_package := FALSE;
                END IF;
            END IF; 
        END IF;
        idx := cg$RUBRIQUE.cg$table.NEXT(idx);
    END LOOP;

    cg$RUBRIQUE.cg$table.DELETE;

--  Application_logic Post-After-Update-statement <<Start>>
--  Application_logic Post-After-Update-statement << End >>

END;
/

PROMPT Creating Before Delete Statement Trigger on 'RUBRIQUE'
CREATE OR REPLACE TRIGGER cg$BDS_RUBRIQUE
BEFORE DELETE ON RUBRIQUE
BEGIN
--  Application_logic Pre-Before-Delete-statement <<Start>>
--  Application_logic Pre-Before-Delete-statement << End >>

    cg$RUBRIQUE.cg$table.DELETE;
    cg$RUBRIQUE.cg$tableind.DELETE;
    cg$RUBRIQUE.idx := 1;

--  Application_logic Post-Before-Delete-statement <<Start>>
--  Application_logic Post-Before-Delete-statement << End >>
END;
/
  

PROMPT Creating Before Delete Row Trigger on 'RUBRIQUE'
CREATE OR REPLACE TRIGGER cg$BDR_RUBRIQUE 
BEFORE DELETE ON RUBRIQUE FOR EACH ROW
DECLARE
    cg$pk cg$RUBRIQUE.cg$pk_type;
    cg$rec cg$RUBRIQUE.cg$row_type;
    cg$ind cg$RUBRIQUE.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Delete-row <<Start>>
--  Application_logic Pre-Before-Delete-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$pk.ID_RUBRIQUE  := :old.ID_RUBRIQUE;
    cg$rec.ID_RUBRIQUE := :old.ID_RUBRIQUE;
    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).ID_RUBRIQUE := :old.ID_RUBRIQUE;
    cg$rec.NO_ENSEIGNANT := :old.NO_ENSEIGNANT;
    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).NO_ENSEIGNANT := :old.NO_ENSEIGNANT;


    cg$RUBRIQUE.cg$table(cg$RUBRIQUE.idx).action_performed := 'DEL';
    cg$RUBRIQUE.idx := cg$RUBRIQUE.idx + 1;

    if not (cg$RUBRIQUE.called_from_package) then
        cg$RUBRIQUE.validate_domain_cascade_delete(cg$rec);   
        cg$RUBRIQUE.del(cg$pk, FALSE);
        cg$RUBRIQUE.called_from_package := FALSE;
    end if;

--  Application_logic Post-Before-Delete-row <<Start>>
--  Application_logic Post-Before-Delete-row << End >>
END;
/

-- No application logic defined for Trigger cg$ADR_RUBRIQUE, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$ADR_RUBRIQUE
AFTER DELETE ON RUBRIQUE FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$ADR_RUBRIQUE
/

PROMPT Creating After Delete Statement Trigger on 'RUBRIQUE'
CREATE OR REPLACE TRIGGER cg$ADS_RUBRIQUE 
AFTER DELETE ON RUBRIQUE
DECLARE
    idx        BINARY_INTEGER := cg$RUBRIQUE.cg$table.FIRST;
    cg$rec   cg$RUBRIQUE.cg$row_type;
    cg$old_rec   cg$RUBRIQUE.cg$row_type;
BEGIN
--  Application_logic Pre-After-Delete-statement <<Start>>
--  Application_logic Pre-After-Delete-statement << End >>

    IF NOT (cg$RUBRIQUE.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

            IF (cg$RUBRIQUE.cg$table(idx).action_performed = 'DEL') THEN
                cg$rec.ID_RUBRIQUE := cg$RUBRIQUE.cg$table(idx).ID_RUBRIQUE;
                cg$RUBRIQUE.cg$tableind(idx).ID_RUBRIQUE := TRUE;
                cg$rec.TYPE := cg$RUBRIQUE.cg$table(idx).TYPE;
                cg$RUBRIQUE.cg$tableind(idx).TYPE := TRUE;
                cg$rec.NO_ENSEIGNANT := cg$RUBRIQUE.cg$table(idx).NO_ENSEIGNANT;
                cg$RUBRIQUE.cg$tableind(idx).NO_ENSEIGNANT := TRUE;
                cg$rec.DESIGNATION := cg$RUBRIQUE.cg$table(idx).DESIGNATION;
                cg$RUBRIQUE.cg$tableind(idx).DESIGNATION := TRUE;
                cg$rec.ORDRE := cg$RUBRIQUE.cg$table(idx).ORDRE;
                cg$RUBRIQUE.cg$tableind(idx).ORDRE := TRUE;

                cg$RUBRIQUE.validate_foreign_keys_del(cg$rec);
                cg$RUBRIQUE.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$RUBRIQUE.cg$tableind(idx),
                                                    'DEL'
                                                  );
	
                cg$RUBRIQUE.cascade_delete(cg$rec);
                cg$RUBRIQUE.domain_cascade_delete(cg$rec);             
            END IF;
            idx := cg$RUBRIQUE.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Delete-statement <<Start>>
--  Application_logic Post-After-Delete-statement << End >>

END;
/



