
PROMPT Creating Trigger Logic for Table 'RUBRIQUE_EVALUATION'
PROMPT Creating Before Insert Statement Trigger on 'RUBRIQUE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BIS_RUBRIQUE_EVALUATION
BEFORE INSERT ON RUBRIQUE_EVALUATION
BEGIN
--  Application_logic Pre-Before-Insert-statement <<Start>>
--  Application_logic Pre-Before-Insert-statement << End >>

    cg$RUBRIQUE_EVALUATION.cg$table.DELETE;
    cg$RUBRIQUE_EVALUATION.cg$tableind.DELETE;
    cg$RUBRIQUE_EVALUATION.idx := 1;

--  Application_logic Post-Before-Insert-statement <<Start>>
--  Application_logic Post-Before-Insert-statement << End >>
END;
/
  

PROMPT Creating Before Insert Row Trigger on 'RUBRIQUE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BIR_RUBRIQUE_EVALUATION 
BEFORE INSERT ON RUBRIQUE_EVALUATION FOR EACH ROW
DECLARE
    cg$rec cg$RUBRIQUE_EVALUATION.cg$row_type;
    cg$ind cg$RUBRIQUE_EVALUATION.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Insert-row <<Start>>
--  Application_logic Pre-Before-Insert-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_RUBRIQUE_EVALUATION := :new.ID_RUBRIQUE_EVALUATION;
    IF (:new.ID_RUBRIQUE_EVALUATION IS NULL) THEN
        cg$ind.ID_RUBRIQUE_EVALUATION := FALSE;
    ELSE
        cg$ind.ID_RUBRIQUE_EVALUATION := TRUE;
    END IF;
    cg$rec.ID_EVALUATION := :new.ID_EVALUATION;
    cg$ind.ID_EVALUATION := TRUE;
    cg$rec.ID_RUBRIQUE := :new.ID_RUBRIQUE;
    cg$ind.ID_RUBRIQUE := TRUE;
    cg$rec.ORDRE := :new.ORDRE;
    cg$ind.ORDRE := TRUE;
    cg$rec.DESIGNATION := :new.DESIGNATION;
    cg$ind.DESIGNATION := TRUE;

    if not (cg$RUBRIQUE_EVALUATION.called_from_package) then
        cg$RUBRIQUE_EVALUATION.validate_arc(cg$rec);
        cg$RUBRIQUE_EVALUATION.validate_domain(cg$rec);

        cg$RUBRIQUE_EVALUATION.ins(cg$rec, cg$ind, FALSE);
        cg$RUBRIQUE_EVALUATION.called_from_package := FALSE;
    end if;

    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).ID_RUBRIQUE_EVALUATION := cg$rec.ID_RUBRIQUE_EVALUATION;
    cg$RUBRIQUE_EVALUATION.cg$tableind(cg$RUBRIQUE_EVALUATION.idx).ID_RUBRIQUE_EVALUATION := cg$ind.ID_RUBRIQUE_EVALUATION;

    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).ID_EVALUATION := cg$rec.ID_EVALUATION;
    cg$RUBRIQUE_EVALUATION.cg$tableind(cg$RUBRIQUE_EVALUATION.idx).ID_EVALUATION := cg$ind.ID_EVALUATION;

    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).ID_RUBRIQUE := cg$rec.ID_RUBRIQUE;
    cg$RUBRIQUE_EVALUATION.cg$tableind(cg$RUBRIQUE_EVALUATION.idx).ID_RUBRIQUE := cg$ind.ID_RUBRIQUE;

    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).ORDRE := cg$rec.ORDRE;
    cg$RUBRIQUE_EVALUATION.cg$tableind(cg$RUBRIQUE_EVALUATION.idx).ORDRE := cg$ind.ORDRE;

    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).DESIGNATION := cg$rec.DESIGNATION;
    cg$RUBRIQUE_EVALUATION.cg$tableind(cg$RUBRIQUE_EVALUATION.idx).DESIGNATION := cg$ind.DESIGNATION;


    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).action_performed := 'INS';
    cg$RUBRIQUE_EVALUATION.idx := cg$RUBRIQUE_EVALUATION.idx + 1;

    :new.ID_RUBRIQUE_EVALUATION := cg$rec.ID_RUBRIQUE_EVALUATION;
    :new.ID_EVALUATION := cg$rec.ID_EVALUATION;
    :new.ID_RUBRIQUE := cg$rec.ID_RUBRIQUE;
    :new.ORDRE := cg$rec.ORDRE;
    :new.DESIGNATION := cg$rec.DESIGNATION;

--  Application_logic Post-Before-Insert-row <<Start>>
--  Application_logic Post-Before-Insert-row << End >>
END;
/


-- No application logic defined for Trigger cg$AIR_RUBRIQUE_EVALUATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AIR_RUBRIQUE_EVALUATION
AFTER INSERT ON RUBRIQUE_EVALUATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AIR_RUBRIQUE_EVALUATION
/

PROMPT Creating After Insert Statement Trigger on 'RUBRIQUE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$AIS_RUBRIQUE_EVALUATION 
AFTER INSERT ON RUBRIQUE_EVALUATION
DECLARE
    idx      BINARY_INTEGER := cg$RUBRIQUE_EVALUATION.cg$table.FIRST;
    cg$rec   cg$RUBRIQUE_EVALUATION.cg$row_type;
    cg$old_rec   cg$RUBRIQUE_EVALUATION.cg$row_type;
    fk_check INTEGER;
BEGIN
--  Application_logic Pre-After-Insert-statement <<Start>>
--  Application_logic Pre-After-Insert-statement << End >>


    IF NOT (cg$RUBRIQUE_EVALUATION.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

	    IF (cg$RUBRIQUE_EVALUATION.cg$table(idx).action_performed = 'INS') THEN
                cg$rec.ID_RUBRIQUE_EVALUATION := cg$RUBRIQUE_EVALUATION.cg$table(idx).ID_RUBRIQUE_EVALUATION;
                cg$rec.ID_EVALUATION := cg$RUBRIQUE_EVALUATION.cg$table(idx).ID_EVALUATION;
                cg$rec.ID_RUBRIQUE := cg$RUBRIQUE_EVALUATION.cg$table(idx).ID_RUBRIQUE;
                cg$rec.ORDRE := cg$RUBRIQUE_EVALUATION.cg$table(idx).ORDRE;
                cg$rec.DESIGNATION := cg$RUBRIQUE_EVALUATION.cg$table(idx).DESIGNATION;

                cg$RUBRIQUE_EVALUATION.validate_foreign_keys_ins(cg$rec);

                cg$RUBRIQUE_EVALUATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$RUBRIQUE_EVALUATION.cg$tableind(idx),
                                                    'INS'
                                                  );
            END IF;
            idx := cg$RUBRIQUE_EVALUATION.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Insert-statement <<Start>>
--  Application_logic Post-After-Insert-statement << End >>

END;
/


    

PROMPT Creating Before Update Statement Trigger on 'RUBRIQUE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BUS_RUBRIQUE_EVALUATION
BEFORE UPDATE ON RUBRIQUE_EVALUATION
BEGIN
--  Application_logic Pre-Before-Update-statement <<Start>>
--  Application_logic Pre-Before-Update-statement << End >>

    cg$RUBRIQUE_EVALUATION.cg$table.DELETE;
    cg$RUBRIQUE_EVALUATION.cg$tableind.DELETE;
    cg$RUBRIQUE_EVALUATION.idx := 1;

--  Application_logic Post-Before-Update-statement <<Start>>
--  Application_logic Post-Before-Update-statement << End >>

END;
/
  

PROMPT Creating Before Update Row Trigger on 'RUBRIQUE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BUR_RUBRIQUE_EVALUATION 
BEFORE UPDATE ON RUBRIQUE_EVALUATION FOR EACH ROW
DECLARE
    cg$rec     cg$RUBRIQUE_EVALUATION.cg$row_type;
    cg$ind     cg$RUBRIQUE_EVALUATION.cg$ind_type;
    cg$old_rec cg$RUBRIQUE_EVALUATION.cg$row_type;  
BEGIN
--  Application_logic Pre-Before-Update-row <<Start>>
--  Application_logic Pre-Before-Update-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_RUBRIQUE_EVALUATION := :new.ID_RUBRIQUE_EVALUATION;
    cg$ind.ID_RUBRIQUE_EVALUATION :=    (:new.ID_RUBRIQUE_EVALUATION IS NULL AND :old.ID_RUBRIQUE_EVALUATION IS NOT NULL )
                        OR (:new.ID_RUBRIQUE_EVALUATION IS NOT NULL AND :old.ID_RUBRIQUE_EVALUATION IS NULL)
                        OR NOT(:new.ID_RUBRIQUE_EVALUATION = :old.ID_RUBRIQUE_EVALUATION) ;
    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).ID_RUBRIQUE_EVALUATION := :old.ID_RUBRIQUE_EVALUATION;
    cg$rec.ID_EVALUATION := :new.ID_EVALUATION;
    cg$ind.ID_EVALUATION :=    (:new.ID_EVALUATION IS NULL AND :old.ID_EVALUATION IS NOT NULL )
                        OR (:new.ID_EVALUATION IS NOT NULL AND :old.ID_EVALUATION IS NULL)
                        OR NOT(:new.ID_EVALUATION = :old.ID_EVALUATION) ;
    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).ID_EVALUATION := :old.ID_EVALUATION;
    cg$rec.ID_RUBRIQUE := :new.ID_RUBRIQUE;
    cg$ind.ID_RUBRIQUE :=    (:new.ID_RUBRIQUE IS NULL AND :old.ID_RUBRIQUE IS NOT NULL )
                        OR (:new.ID_RUBRIQUE IS NOT NULL AND :old.ID_RUBRIQUE IS NULL)
                        OR NOT(:new.ID_RUBRIQUE = :old.ID_RUBRIQUE) ;
    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).ID_RUBRIQUE := :old.ID_RUBRIQUE;
    cg$rec.ORDRE := :new.ORDRE;
    cg$ind.ORDRE :=    (:new.ORDRE IS NULL AND :old.ORDRE IS NOT NULL )
                        OR (:new.ORDRE IS NOT NULL AND :old.ORDRE IS NULL)
                        OR NOT(:new.ORDRE = :old.ORDRE) ;
    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).ORDRE := :old.ORDRE;
    cg$rec.DESIGNATION := :new.DESIGNATION;
    cg$ind.DESIGNATION :=    (:new.DESIGNATION IS NULL AND :old.DESIGNATION IS NOT NULL )
                        OR (:new.DESIGNATION IS NOT NULL AND :old.DESIGNATION IS NULL)
                        OR NOT(:new.DESIGNATION = :old.DESIGNATION) ;
    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).DESIGNATION := :old.DESIGNATION;


    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).action_performed := 'UPD';     -- nkovoor
    cg$RUBRIQUE_EVALUATION.idx := cg$RUBRIQUE_EVALUATION.idx + 1;

    if not (cg$RUBRIQUE_EVALUATION.called_from_package) then
        cg$RUBRIQUE_EVALUATION.validate_arc(cg$rec);
        cg$RUBRIQUE_EVALUATION.validate_domain(cg$rec, cg$ind);
        cg$RUBRIQUE_EVALUATION.validate_domain_cascade_update(cg$old_rec);   

        cg$RUBRIQUE_EVALUATION.upd(cg$rec, cg$ind, FALSE);
        cg$RUBRIQUE_EVALUATION.called_from_package := FALSE;
    end if;

    :new.ID_EVALUATION := cg$rec.ID_EVALUATION;
    :new.ID_RUBRIQUE := cg$rec.ID_RUBRIQUE;
    :new.ORDRE := cg$rec.ORDRE;
    :new.DESIGNATION := cg$rec.DESIGNATION;
--  Application_logic Post-Before-Update-row <<Start>>
--  Application_logic Post-Before-Update-row << End >>
END;
/

-- No application logic defined for Trigger cg$AUR_RUBRIQUE_EVALUATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AUR_RUBRIQUE_EVALUATION
AFTER UPDATE ON RUBRIQUE_EVALUATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AUR_RUBRIQUE_EVALUATION
/






PROMPT Creating After Update Statement Trigger on 'RUBRIQUE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$AUS_RUBRIQUE_EVALUATION 
AFTER UPDATE ON RUBRIQUE_EVALUATION
DECLARE
    idx        BINARY_INTEGER := cg$RUBRIQUE_EVALUATION.cg$table.FIRST;
    cg$old_rec cg$RUBRIQUE_EVALUATION.cg$row_type;
    cg$rec     cg$RUBRIQUE_EVALUATION.cg$row_type;
    cg$ind     cg$RUBRIQUE_EVALUATION.cg$ind_type;
BEGIN
--  Application_logic Pre-After-Update-statement <<Start>>
--  Application_logic Pre-After-Update-statement << End >>

    WHILE idx IS NOT NULL LOOP

        IF (cg$RUBRIQUE_EVALUATION.cg$table(idx).action_performed = 'UPD') THEN
            cg$old_rec.ID_RUBRIQUE_EVALUATION := cg$RUBRIQUE_EVALUATION.cg$table(idx).ID_RUBRIQUE_EVALUATION;
            cg$old_rec.ID_EVALUATION := cg$RUBRIQUE_EVALUATION.cg$table(idx).ID_EVALUATION;
            cg$old_rec.ID_RUBRIQUE := cg$RUBRIQUE_EVALUATION.cg$table(idx).ID_RUBRIQUE;
            cg$old_rec.ORDRE := cg$RUBRIQUE_EVALUATION.cg$table(idx).ORDRE;
            cg$old_rec.DESIGNATION := cg$RUBRIQUE_EVALUATION.cg$table(idx).DESIGNATION;

            IF NOT (cg$RUBRIQUE_EVALUATION.called_from_package) THEN
                idx := cg$RUBRIQUE_EVALUATION.cg$table.NEXT(idx);

                IF (idx IS NOT NULL) THEN
                cg$rec.ID_RUBRIQUE_EVALUATION := cg$RUBRIQUE_EVALUATION.cg$table(idx).ID_RUBRIQUE_EVALUATION;
                cg$ind.ID_RUBRIQUE_EVALUATION := updating('ID_RUBRIQUE_EVALUATION');
                cg$rec.ID_EVALUATION := cg$RUBRIQUE_EVALUATION.cg$table(idx).ID_EVALUATION;
                cg$ind.ID_EVALUATION := updating('ID_EVALUATION');
                cg$rec.ID_RUBRIQUE := cg$RUBRIQUE_EVALUATION.cg$table(idx).ID_RUBRIQUE;
                cg$ind.ID_RUBRIQUE := updating('ID_RUBRIQUE');
                cg$rec.ORDRE := cg$RUBRIQUE_EVALUATION.cg$table(idx).ORDRE;
                cg$ind.ORDRE := updating('ORDRE');
                cg$rec.DESIGNATION := cg$RUBRIQUE_EVALUATION.cg$table(idx).DESIGNATION;
                cg$ind.DESIGNATION := updating('DESIGNATION');

                cg$RUBRIQUE_EVALUATION.validate_foreign_keys_upd(cg$rec, cg$old_rec, cg$ind);

                cg$RUBRIQUE_EVALUATION.upd_denorm2( cg$rec,
                                               cg$RUBRIQUE_EVALUATION.cg$tableind(idx)
                                             );
                cg$RUBRIQUE_EVALUATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$RUBRIQUE_EVALUATION.cg$tableind(idx)
				        				                  );
                cg$RUBRIQUE_EVALUATION.cascade_update(cg$rec, cg$old_rec);
                cg$RUBRIQUE_EVALUATION.domain_cascade_update(cg$rec, cg$ind, cg$old_rec);             
        
                cg$RUBRIQUE_EVALUATION.called_from_package := FALSE;
                END IF;
            END IF; 
        END IF;
        idx := cg$RUBRIQUE_EVALUATION.cg$table.NEXT(idx);
    END LOOP;

    cg$RUBRIQUE_EVALUATION.cg$table.DELETE;

--  Application_logic Post-After-Update-statement <<Start>>
--  Application_logic Post-After-Update-statement << End >>

END;
/

PROMPT Creating Before Delete Statement Trigger on 'RUBRIQUE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BDS_RUBRIQUE_EVALUATION
BEFORE DELETE ON RUBRIQUE_EVALUATION
BEGIN
--  Application_logic Pre-Before-Delete-statement <<Start>>
--  Application_logic Pre-Before-Delete-statement << End >>

    cg$RUBRIQUE_EVALUATION.cg$table.DELETE;
    cg$RUBRIQUE_EVALUATION.cg$tableind.DELETE;
    cg$RUBRIQUE_EVALUATION.idx := 1;

--  Application_logic Post-Before-Delete-statement <<Start>>
--  Application_logic Post-Before-Delete-statement << End >>
END;
/
  

PROMPT Creating Before Delete Row Trigger on 'RUBRIQUE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BDR_RUBRIQUE_EVALUATION 
BEFORE DELETE ON RUBRIQUE_EVALUATION FOR EACH ROW
DECLARE
    cg$pk cg$RUBRIQUE_EVALUATION.cg$pk_type;
    cg$rec cg$RUBRIQUE_EVALUATION.cg$row_type;
    cg$ind cg$RUBRIQUE_EVALUATION.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Delete-row <<Start>>
--  Application_logic Pre-Before-Delete-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$pk.ID_RUBRIQUE_EVALUATION  := :old.ID_RUBRIQUE_EVALUATION;
    cg$rec.ID_RUBRIQUE_EVALUATION := :old.ID_RUBRIQUE_EVALUATION;
    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).ID_RUBRIQUE_EVALUATION := :old.ID_RUBRIQUE_EVALUATION;
    cg$rec.ID_RUBRIQUE := :old.ID_RUBRIQUE;
    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).ID_RUBRIQUE := :old.ID_RUBRIQUE;
    cg$rec.ID_EVALUATION := :old.ID_EVALUATION;
    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).ID_EVALUATION := :old.ID_EVALUATION;


    cg$RUBRIQUE_EVALUATION.cg$table(cg$RUBRIQUE_EVALUATION.idx).action_performed := 'DEL';
    cg$RUBRIQUE_EVALUATION.idx := cg$RUBRIQUE_EVALUATION.idx + 1;

    if not (cg$RUBRIQUE_EVALUATION.called_from_package) then
        cg$RUBRIQUE_EVALUATION.validate_domain_cascade_delete(cg$rec);   
        cg$RUBRIQUE_EVALUATION.del(cg$pk, FALSE);
        cg$RUBRIQUE_EVALUATION.called_from_package := FALSE;
    end if;

--  Application_logic Post-Before-Delete-row <<Start>>
--  Application_logic Post-Before-Delete-row << End >>
END;
/

-- No application logic defined for Trigger cg$ADR_RUBRIQUE_EVALUATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$ADR_RUBRIQUE_EVALUATION
AFTER DELETE ON RUBRIQUE_EVALUATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$ADR_RUBRIQUE_EVALUATION
/

PROMPT Creating After Delete Statement Trigger on 'RUBRIQUE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$ADS_RUBRIQUE_EVALUATION 
AFTER DELETE ON RUBRIQUE_EVALUATION
DECLARE
    idx        BINARY_INTEGER := cg$RUBRIQUE_EVALUATION.cg$table.FIRST;
    cg$rec   cg$RUBRIQUE_EVALUATION.cg$row_type;
    cg$old_rec   cg$RUBRIQUE_EVALUATION.cg$row_type;
BEGIN
--  Application_logic Pre-After-Delete-statement <<Start>>
--  Application_logic Pre-After-Delete-statement << End >>

    IF NOT (cg$RUBRIQUE_EVALUATION.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

            IF (cg$RUBRIQUE_EVALUATION.cg$table(idx).action_performed = 'DEL') THEN
                cg$rec.ID_RUBRIQUE_EVALUATION := cg$RUBRIQUE_EVALUATION.cg$table(idx).ID_RUBRIQUE_EVALUATION;
                cg$RUBRIQUE_EVALUATION.cg$tableind(idx).ID_RUBRIQUE_EVALUATION := TRUE;
                cg$rec.ID_EVALUATION := cg$RUBRIQUE_EVALUATION.cg$table(idx).ID_EVALUATION;
                cg$RUBRIQUE_EVALUATION.cg$tableind(idx).ID_EVALUATION := TRUE;
                cg$rec.ID_RUBRIQUE := cg$RUBRIQUE_EVALUATION.cg$table(idx).ID_RUBRIQUE;
                cg$RUBRIQUE_EVALUATION.cg$tableind(idx).ID_RUBRIQUE := TRUE;
                cg$rec.ORDRE := cg$RUBRIQUE_EVALUATION.cg$table(idx).ORDRE;
                cg$RUBRIQUE_EVALUATION.cg$tableind(idx).ORDRE := TRUE;
                cg$rec.DESIGNATION := cg$RUBRIQUE_EVALUATION.cg$table(idx).DESIGNATION;
                cg$RUBRIQUE_EVALUATION.cg$tableind(idx).DESIGNATION := TRUE;

                cg$RUBRIQUE_EVALUATION.validate_foreign_keys_del(cg$rec);
                cg$RUBRIQUE_EVALUATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$RUBRIQUE_EVALUATION.cg$tableind(idx),
                                                    'DEL'
                                                  );
	
                cg$RUBRIQUE_EVALUATION.cascade_delete(cg$rec);
                cg$RUBRIQUE_EVALUATION.domain_cascade_delete(cg$rec);             
            END IF;
            idx := cg$RUBRIQUE_EVALUATION.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Delete-statement <<Start>>
--  Application_logic Post-After-Delete-statement << End >>

END;
/



