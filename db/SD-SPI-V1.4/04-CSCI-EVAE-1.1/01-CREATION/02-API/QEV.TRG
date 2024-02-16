
PROMPT Creating Trigger Logic for Table 'QUESTION_EVALUATION'
PROMPT Creating Before Insert Statement Trigger on 'QUESTION_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BIS_QUESTION_EVALUATION
BEFORE INSERT ON QUESTION_EVALUATION
BEGIN
--  Application_logic Pre-Before-Insert-statement <<Start>>
--  Application_logic Pre-Before-Insert-statement << End >>

    cg$QUESTION_EVALUATION.cg$table.DELETE;
    cg$QUESTION_EVALUATION.cg$tableind.DELETE;
    cg$QUESTION_EVALUATION.idx := 1;

--  Application_logic Post-Before-Insert-statement <<Start>>
--  Application_logic Post-Before-Insert-statement << End >>
END;
/
  

PROMPT Creating Before Insert Row Trigger on 'QUESTION_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BIR_QUESTION_EVALUATION 
BEFORE INSERT ON QUESTION_EVALUATION FOR EACH ROW
DECLARE
    cg$rec cg$QUESTION_EVALUATION.cg$row_type;
    cg$ind cg$QUESTION_EVALUATION.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Insert-row <<Start>>
--  Application_logic Pre-Before-Insert-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_QUESTION_EVALUATION := :new.ID_QUESTION_EVALUATION;
    cg$ind.ID_QUESTION_EVALUATION := TRUE;
    cg$rec.ID_RUBRIQUE_EVALUATION := :new.ID_RUBRIQUE_EVALUATION;
    cg$ind.ID_RUBRIQUE_EVALUATION := TRUE;
    cg$rec.ID_QUESTION := :new.ID_QUESTION;
    cg$ind.ID_QUESTION := TRUE;
    cg$rec.ID_QUALIFICATIF := :new.ID_QUALIFICATIF;
    cg$ind.ID_QUALIFICATIF := TRUE;
    cg$rec.ORDRE := :new.ORDRE;
    cg$ind.ORDRE := TRUE;
    cg$rec.INTITULE := :new.INTITULE;
    cg$ind.INTITULE := TRUE;

    if not (cg$QUESTION_EVALUATION.called_from_package) then
        cg$QUESTION_EVALUATION.validate_arc(cg$rec);
        cg$QUESTION_EVALUATION.validate_domain(cg$rec);

        cg$QUESTION_EVALUATION.ins(cg$rec, cg$ind, FALSE);
        cg$QUESTION_EVALUATION.called_from_package := FALSE;
    end if;

    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ID_QUESTION_EVALUATION := cg$rec.ID_QUESTION_EVALUATION;
    cg$QUESTION_EVALUATION.cg$tableind(cg$QUESTION_EVALUATION.idx).ID_QUESTION_EVALUATION := cg$ind.ID_QUESTION_EVALUATION;

    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ID_RUBRIQUE_EVALUATION := cg$rec.ID_RUBRIQUE_EVALUATION;
    cg$QUESTION_EVALUATION.cg$tableind(cg$QUESTION_EVALUATION.idx).ID_RUBRIQUE_EVALUATION := cg$ind.ID_RUBRIQUE_EVALUATION;

    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ID_QUESTION := cg$rec.ID_QUESTION;
    cg$QUESTION_EVALUATION.cg$tableind(cg$QUESTION_EVALUATION.idx).ID_QUESTION := cg$ind.ID_QUESTION;

    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ID_QUALIFICATIF := cg$rec.ID_QUALIFICATIF;
    cg$QUESTION_EVALUATION.cg$tableind(cg$QUESTION_EVALUATION.idx).ID_QUALIFICATIF := cg$ind.ID_QUALIFICATIF;

    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ORDRE := cg$rec.ORDRE;
    cg$QUESTION_EVALUATION.cg$tableind(cg$QUESTION_EVALUATION.idx).ORDRE := cg$ind.ORDRE;

    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).INTITULE := cg$rec.INTITULE;
    cg$QUESTION_EVALUATION.cg$tableind(cg$QUESTION_EVALUATION.idx).INTITULE := cg$ind.INTITULE;


    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).action_performed := 'INS';
    cg$QUESTION_EVALUATION.idx := cg$QUESTION_EVALUATION.idx + 1;

    :new.ID_QUESTION_EVALUATION := cg$rec.ID_QUESTION_EVALUATION;
    :new.ID_RUBRIQUE_EVALUATION := cg$rec.ID_RUBRIQUE_EVALUATION;
    :new.ID_QUESTION := cg$rec.ID_QUESTION;
    :new.ID_QUALIFICATIF := cg$rec.ID_QUALIFICATIF;
    :new.ORDRE := cg$rec.ORDRE;
    :new.INTITULE := cg$rec.INTITULE;

--  Application_logic Post-Before-Insert-row <<Start>>
--  Application_logic Post-Before-Insert-row << End >>
END;
/


-- No application logic defined for Trigger cg$AIR_QUESTION_EVALUATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AIR_QUESTION_EVALUATION
AFTER INSERT ON QUESTION_EVALUATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AIR_QUESTION_EVALUATION
/

PROMPT Creating After Insert Statement Trigger on 'QUESTION_EVALUATION'
CREATE OR REPLACE TRIGGER cg$AIS_QUESTION_EVALUATION 
AFTER INSERT ON QUESTION_EVALUATION
DECLARE
    idx      BINARY_INTEGER := cg$QUESTION_EVALUATION.cg$table.FIRST;
    cg$rec   cg$QUESTION_EVALUATION.cg$row_type;
    cg$old_rec   cg$QUESTION_EVALUATION.cg$row_type;
    fk_check INTEGER;
BEGIN
--  Application_logic Pre-After-Insert-statement <<Start>>
--  Application_logic Pre-After-Insert-statement << End >>


    IF NOT (cg$QUESTION_EVALUATION.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

	    IF (cg$QUESTION_EVALUATION.cg$table(idx).action_performed = 'INS') THEN
                cg$rec.ID_QUESTION_EVALUATION := cg$QUESTION_EVALUATION.cg$table(idx).ID_QUESTION_EVALUATION;
                cg$rec.ID_RUBRIQUE_EVALUATION := cg$QUESTION_EVALUATION.cg$table(idx).ID_RUBRIQUE_EVALUATION;
                cg$rec.ID_QUESTION := cg$QUESTION_EVALUATION.cg$table(idx).ID_QUESTION;
                cg$rec.ID_QUALIFICATIF := cg$QUESTION_EVALUATION.cg$table(idx).ID_QUALIFICATIF;
                cg$rec.ORDRE := cg$QUESTION_EVALUATION.cg$table(idx).ORDRE;
                cg$rec.INTITULE := cg$QUESTION_EVALUATION.cg$table(idx).INTITULE;

                cg$QUESTION_EVALUATION.validate_foreign_keys_ins(cg$rec);

                cg$QUESTION_EVALUATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$QUESTION_EVALUATION.cg$tableind(idx),
                                                    'INS'
                                                  );
            END IF;
            idx := cg$QUESTION_EVALUATION.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Insert-statement <<Start>>
--  Application_logic Post-After-Insert-statement << End >>

END;
/


    

PROMPT Creating Before Update Statement Trigger on 'QUESTION_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BUS_QUESTION_EVALUATION
BEFORE UPDATE ON QUESTION_EVALUATION
BEGIN
--  Application_logic Pre-Before-Update-statement <<Start>>
--  Application_logic Pre-Before-Update-statement << End >>

    cg$QUESTION_EVALUATION.cg$table.DELETE;
    cg$QUESTION_EVALUATION.cg$tableind.DELETE;
    cg$QUESTION_EVALUATION.idx := 1;

--  Application_logic Post-Before-Update-statement <<Start>>
--  Application_logic Post-Before-Update-statement << End >>

END;
/
  

PROMPT Creating Before Update Row Trigger on 'QUESTION_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BUR_QUESTION_EVALUATION 
BEFORE UPDATE ON QUESTION_EVALUATION FOR EACH ROW
DECLARE
    cg$rec     cg$QUESTION_EVALUATION.cg$row_type;
    cg$ind     cg$QUESTION_EVALUATION.cg$ind_type;
    cg$old_rec cg$QUESTION_EVALUATION.cg$row_type;  
BEGIN
--  Application_logic Pre-Before-Update-row <<Start>>
--  Application_logic Pre-Before-Update-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_QUESTION_EVALUATION := :new.ID_QUESTION_EVALUATION;
    cg$ind.ID_QUESTION_EVALUATION :=    (:new.ID_QUESTION_EVALUATION IS NULL AND :old.ID_QUESTION_EVALUATION IS NOT NULL )
                        OR (:new.ID_QUESTION_EVALUATION IS NOT NULL AND :old.ID_QUESTION_EVALUATION IS NULL)
                        OR NOT(:new.ID_QUESTION_EVALUATION = :old.ID_QUESTION_EVALUATION) ;
    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ID_QUESTION_EVALUATION := :old.ID_QUESTION_EVALUATION;
    cg$rec.ID_RUBRIQUE_EVALUATION := :new.ID_RUBRIQUE_EVALUATION;
    cg$ind.ID_RUBRIQUE_EVALUATION :=    (:new.ID_RUBRIQUE_EVALUATION IS NULL AND :old.ID_RUBRIQUE_EVALUATION IS NOT NULL )
                        OR (:new.ID_RUBRIQUE_EVALUATION IS NOT NULL AND :old.ID_RUBRIQUE_EVALUATION IS NULL)
                        OR NOT(:new.ID_RUBRIQUE_EVALUATION = :old.ID_RUBRIQUE_EVALUATION) ;
    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ID_RUBRIQUE_EVALUATION := :old.ID_RUBRIQUE_EVALUATION;
    cg$rec.ID_QUESTION := :new.ID_QUESTION;
    cg$ind.ID_QUESTION :=    (:new.ID_QUESTION IS NULL AND :old.ID_QUESTION IS NOT NULL )
                        OR (:new.ID_QUESTION IS NOT NULL AND :old.ID_QUESTION IS NULL)
                        OR NOT(:new.ID_QUESTION = :old.ID_QUESTION) ;
    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ID_QUESTION := :old.ID_QUESTION;
    cg$rec.ID_QUALIFICATIF := :new.ID_QUALIFICATIF;
    cg$ind.ID_QUALIFICATIF :=    (:new.ID_QUALIFICATIF IS NULL AND :old.ID_QUALIFICATIF IS NOT NULL )
                        OR (:new.ID_QUALIFICATIF IS NOT NULL AND :old.ID_QUALIFICATIF IS NULL)
                        OR NOT(:new.ID_QUALIFICATIF = :old.ID_QUALIFICATIF) ;
    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ID_QUALIFICATIF := :old.ID_QUALIFICATIF;
    cg$rec.ORDRE := :new.ORDRE;
    cg$ind.ORDRE :=    (:new.ORDRE IS NULL AND :old.ORDRE IS NOT NULL )
                        OR (:new.ORDRE IS NOT NULL AND :old.ORDRE IS NULL)
                        OR NOT(:new.ORDRE = :old.ORDRE) ;
    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ORDRE := :old.ORDRE;
    cg$rec.INTITULE := :new.INTITULE;
    cg$ind.INTITULE :=    (:new.INTITULE IS NULL AND :old.INTITULE IS NOT NULL )
                        OR (:new.INTITULE IS NOT NULL AND :old.INTITULE IS NULL)
                        OR NOT(:new.INTITULE = :old.INTITULE) ;
    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).INTITULE := :old.INTITULE;


    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).action_performed := 'UPD';     -- nkovoor
    cg$QUESTION_EVALUATION.idx := cg$QUESTION_EVALUATION.idx + 1;

    if not (cg$QUESTION_EVALUATION.called_from_package) then
        cg$QUESTION_EVALUATION.validate_arc(cg$rec);
        cg$QUESTION_EVALUATION.validate_domain(cg$rec, cg$ind);
        cg$QUESTION_EVALUATION.validate_domain_cascade_update(cg$old_rec);   

        cg$QUESTION_EVALUATION.upd(cg$rec, cg$ind, FALSE);
        cg$QUESTION_EVALUATION.called_from_package := FALSE;
    end if;

    :new.ID_RUBRIQUE_EVALUATION := cg$rec.ID_RUBRIQUE_EVALUATION;
    :new.ID_QUESTION := cg$rec.ID_QUESTION;
    :new.ID_QUALIFICATIF := cg$rec.ID_QUALIFICATIF;
    :new.ORDRE := cg$rec.ORDRE;
    :new.INTITULE := cg$rec.INTITULE;
--  Application_logic Post-Before-Update-row <<Start>>
--  Application_logic Post-Before-Update-row << End >>
END;
/

-- No application logic defined for Trigger cg$AUR_QUESTION_EVALUATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AUR_QUESTION_EVALUATION
AFTER UPDATE ON QUESTION_EVALUATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AUR_QUESTION_EVALUATION
/






PROMPT Creating After Update Statement Trigger on 'QUESTION_EVALUATION'
CREATE OR REPLACE TRIGGER cg$AUS_QUESTION_EVALUATION 
AFTER UPDATE ON QUESTION_EVALUATION
DECLARE
    idx        BINARY_INTEGER := cg$QUESTION_EVALUATION.cg$table.FIRST;
    cg$old_rec cg$QUESTION_EVALUATION.cg$row_type;
    cg$rec     cg$QUESTION_EVALUATION.cg$row_type;
    cg$ind     cg$QUESTION_EVALUATION.cg$ind_type;
BEGIN
--  Application_logic Pre-After-Update-statement <<Start>>
--  Application_logic Pre-After-Update-statement << End >>

    WHILE idx IS NOT NULL LOOP

        IF (cg$QUESTION_EVALUATION.cg$table(idx).action_performed = 'UPD') THEN
            cg$old_rec.ID_QUESTION_EVALUATION := cg$QUESTION_EVALUATION.cg$table(idx).ID_QUESTION_EVALUATION;
            cg$old_rec.ID_RUBRIQUE_EVALUATION := cg$QUESTION_EVALUATION.cg$table(idx).ID_RUBRIQUE_EVALUATION;
            cg$old_rec.ID_QUESTION := cg$QUESTION_EVALUATION.cg$table(idx).ID_QUESTION;
            cg$old_rec.ID_QUALIFICATIF := cg$QUESTION_EVALUATION.cg$table(idx).ID_QUALIFICATIF;
            cg$old_rec.ORDRE := cg$QUESTION_EVALUATION.cg$table(idx).ORDRE;
            cg$old_rec.INTITULE := cg$QUESTION_EVALUATION.cg$table(idx).INTITULE;

            IF NOT (cg$QUESTION_EVALUATION.called_from_package) THEN
                idx := cg$QUESTION_EVALUATION.cg$table.NEXT(idx);

                IF (idx IS NOT NULL) THEN
                cg$rec.ID_QUESTION_EVALUATION := cg$QUESTION_EVALUATION.cg$table(idx).ID_QUESTION_EVALUATION;
                cg$ind.ID_QUESTION_EVALUATION := updating('ID_QUESTION_EVALUATION');
                cg$rec.ID_RUBRIQUE_EVALUATION := cg$QUESTION_EVALUATION.cg$table(idx).ID_RUBRIQUE_EVALUATION;
                cg$ind.ID_RUBRIQUE_EVALUATION := updating('ID_RUBRIQUE_EVALUATION');
                cg$rec.ID_QUESTION := cg$QUESTION_EVALUATION.cg$table(idx).ID_QUESTION;
                cg$ind.ID_QUESTION := updating('ID_QUESTION');
                cg$rec.ID_QUALIFICATIF := cg$QUESTION_EVALUATION.cg$table(idx).ID_QUALIFICATIF;
                cg$ind.ID_QUALIFICATIF := updating('ID_QUALIFICATIF');
                cg$rec.ORDRE := cg$QUESTION_EVALUATION.cg$table(idx).ORDRE;
                cg$ind.ORDRE := updating('ORDRE');
                cg$rec.INTITULE := cg$QUESTION_EVALUATION.cg$table(idx).INTITULE;
                cg$ind.INTITULE := updating('INTITULE');

                cg$QUESTION_EVALUATION.validate_foreign_keys_upd(cg$rec, cg$old_rec, cg$ind);

                cg$QUESTION_EVALUATION.upd_denorm2( cg$rec,
                                               cg$QUESTION_EVALUATION.cg$tableind(idx)
                                             );
                cg$QUESTION_EVALUATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$QUESTION_EVALUATION.cg$tableind(idx)
				        				                  );
                cg$QUESTION_EVALUATION.cascade_update(cg$rec, cg$old_rec);
                cg$QUESTION_EVALUATION.domain_cascade_update(cg$rec, cg$ind, cg$old_rec);             
        
                cg$QUESTION_EVALUATION.called_from_package := FALSE;
                END IF;
            END IF; 
        END IF;
        idx := cg$QUESTION_EVALUATION.cg$table.NEXT(idx);
    END LOOP;

    cg$QUESTION_EVALUATION.cg$table.DELETE;

--  Application_logic Post-After-Update-statement <<Start>>
--  Application_logic Post-After-Update-statement << End >>

END;
/

PROMPT Creating Before Delete Statement Trigger on 'QUESTION_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BDS_QUESTION_EVALUATION
BEFORE DELETE ON QUESTION_EVALUATION
BEGIN
--  Application_logic Pre-Before-Delete-statement <<Start>>
--  Application_logic Pre-Before-Delete-statement << End >>

    cg$QUESTION_EVALUATION.cg$table.DELETE;
    cg$QUESTION_EVALUATION.cg$tableind.DELETE;
    cg$QUESTION_EVALUATION.idx := 1;

--  Application_logic Post-Before-Delete-statement <<Start>>
--  Application_logic Post-Before-Delete-statement << End >>
END;
/
  

PROMPT Creating Before Delete Row Trigger on 'QUESTION_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BDR_QUESTION_EVALUATION 
BEFORE DELETE ON QUESTION_EVALUATION FOR EACH ROW
DECLARE
    cg$pk cg$QUESTION_EVALUATION.cg$pk_type;
    cg$rec cg$QUESTION_EVALUATION.cg$row_type;
    cg$ind cg$QUESTION_EVALUATION.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Delete-row <<Start>>
--  Application_logic Pre-Before-Delete-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$pk.ID_QUESTION_EVALUATION  := :old.ID_QUESTION_EVALUATION;
    cg$rec.ID_QUESTION_EVALUATION := :old.ID_QUESTION_EVALUATION;
    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ID_QUESTION_EVALUATION := :old.ID_QUESTION_EVALUATION;
    cg$rec.ID_QUALIFICATIF := :old.ID_QUALIFICATIF;
    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ID_QUALIFICATIF := :old.ID_QUALIFICATIF;
    cg$rec.ID_QUESTION := :old.ID_QUESTION;
    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ID_QUESTION := :old.ID_QUESTION;
    cg$rec.ID_RUBRIQUE_EVALUATION := :old.ID_RUBRIQUE_EVALUATION;
    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).ID_RUBRIQUE_EVALUATION := :old.ID_RUBRIQUE_EVALUATION;


    cg$QUESTION_EVALUATION.cg$table(cg$QUESTION_EVALUATION.idx).action_performed := 'DEL';
    cg$QUESTION_EVALUATION.idx := cg$QUESTION_EVALUATION.idx + 1;

    if not (cg$QUESTION_EVALUATION.called_from_package) then
        cg$QUESTION_EVALUATION.validate_domain_cascade_delete(cg$rec);   
        cg$QUESTION_EVALUATION.del(cg$pk, FALSE);
        cg$QUESTION_EVALUATION.called_from_package := FALSE;
    end if;

--  Application_logic Post-Before-Delete-row <<Start>>
--  Application_logic Post-Before-Delete-row << End >>
END;
/

-- No application logic defined for Trigger cg$ADR_QUESTION_EVALUATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$ADR_QUESTION_EVALUATION
AFTER DELETE ON QUESTION_EVALUATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$ADR_QUESTION_EVALUATION
/

PROMPT Creating After Delete Statement Trigger on 'QUESTION_EVALUATION'
CREATE OR REPLACE TRIGGER cg$ADS_QUESTION_EVALUATION 
AFTER DELETE ON QUESTION_EVALUATION
DECLARE
    idx        BINARY_INTEGER := cg$QUESTION_EVALUATION.cg$table.FIRST;
    cg$rec   cg$QUESTION_EVALUATION.cg$row_type;
    cg$old_rec   cg$QUESTION_EVALUATION.cg$row_type;
BEGIN
--  Application_logic Pre-After-Delete-statement <<Start>>
--  Application_logic Pre-After-Delete-statement << End >>

    IF NOT (cg$QUESTION_EVALUATION.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

            IF (cg$QUESTION_EVALUATION.cg$table(idx).action_performed = 'DEL') THEN
                cg$rec.ID_QUESTION_EVALUATION := cg$QUESTION_EVALUATION.cg$table(idx).ID_QUESTION_EVALUATION;
                cg$QUESTION_EVALUATION.cg$tableind(idx).ID_QUESTION_EVALUATION := TRUE;
                cg$rec.ID_RUBRIQUE_EVALUATION := cg$QUESTION_EVALUATION.cg$table(idx).ID_RUBRIQUE_EVALUATION;
                cg$QUESTION_EVALUATION.cg$tableind(idx).ID_RUBRIQUE_EVALUATION := TRUE;
                cg$rec.ID_QUESTION := cg$QUESTION_EVALUATION.cg$table(idx).ID_QUESTION;
                cg$QUESTION_EVALUATION.cg$tableind(idx).ID_QUESTION := TRUE;
                cg$rec.ID_QUALIFICATIF := cg$QUESTION_EVALUATION.cg$table(idx).ID_QUALIFICATIF;
                cg$QUESTION_EVALUATION.cg$tableind(idx).ID_QUALIFICATIF := TRUE;
                cg$rec.ORDRE := cg$QUESTION_EVALUATION.cg$table(idx).ORDRE;
                cg$QUESTION_EVALUATION.cg$tableind(idx).ORDRE := TRUE;
                cg$rec.INTITULE := cg$QUESTION_EVALUATION.cg$table(idx).INTITULE;
                cg$QUESTION_EVALUATION.cg$tableind(idx).INTITULE := TRUE;

                cg$QUESTION_EVALUATION.validate_foreign_keys_del(cg$rec);
                cg$QUESTION_EVALUATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$QUESTION_EVALUATION.cg$tableind(idx),
                                                    'DEL'
                                                  );
	
                cg$QUESTION_EVALUATION.cascade_delete(cg$rec);
                cg$QUESTION_EVALUATION.domain_cascade_delete(cg$rec);             
            END IF;
            idx := cg$QUESTION_EVALUATION.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Delete-statement <<Start>>
--  Application_logic Post-After-Delete-statement << End >>

END;
/



