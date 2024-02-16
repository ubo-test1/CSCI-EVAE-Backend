
PROMPT Creating Trigger Logic for Table 'EVALUATION'
PROMPT Creating Before Insert Statement Trigger on 'EVALUATION'
CREATE OR REPLACE TRIGGER cg$BIS_EVALUATION
BEFORE INSERT ON EVALUATION
BEGIN
--  Application_logic Pre-Before-Insert-statement <<Start>>
--  Application_logic Pre-Before-Insert-statement << End >>

    cg$EVALUATION.cg$table.DELETE;
    cg$EVALUATION.cg$tableind.DELETE;
    cg$EVALUATION.idx := 1;

--  Application_logic Post-Before-Insert-statement <<Start>>
--  Application_logic Post-Before-Insert-statement << End >>
END;
/
  

PROMPT Creating Before Insert Row Trigger on 'EVALUATION'
CREATE OR REPLACE TRIGGER cg$BIR_EVALUATION 
BEFORE INSERT ON EVALUATION FOR EACH ROW
DECLARE
    cg$rec cg$EVALUATION.cg$row_type;
    cg$ind cg$EVALUATION.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Insert-row <<Start>>
--  Application_logic Pre-Before-Insert-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_EVALUATION := :new.ID_EVALUATION;
    IF (:new.ID_EVALUATION IS NULL) THEN
        cg$ind.ID_EVALUATION := FALSE;
    ELSE
        cg$ind.ID_EVALUATION := TRUE;
    END IF;
    cg$rec.NO_ENSEIGNANT := :new.NO_ENSEIGNANT;
    cg$ind.NO_ENSEIGNANT := TRUE;
    cg$rec.CODE_FORMATION := :new.CODE_FORMATION;
    cg$ind.CODE_FORMATION := TRUE;
    cg$rec.ANNEE_UNIVERSITAIRE := :new.ANNEE_UNIVERSITAIRE;
    cg$ind.ANNEE_UNIVERSITAIRE := TRUE;
    cg$rec.CODE_UE := :new.CODE_UE;
    cg$ind.CODE_UE := TRUE;
    cg$rec.CODE_EC := :new.CODE_EC;
    cg$ind.CODE_EC := TRUE;
    cg$rec.NO_EVALUATION := :new.NO_EVALUATION;
    cg$ind.NO_EVALUATION := TRUE;
    cg$rec.DESIGNATION := :new.DESIGNATION;
    cg$ind.DESIGNATION := TRUE;
    cg$rec.ETAT := :new.ETAT;
    cg$ind.ETAT := TRUE;
    cg$rec.PERIODE := :new.PERIODE;
    cg$ind.PERIODE := TRUE;
    cg$rec.DEBUT_REPONSE := :new.DEBUT_REPONSE;
    cg$ind.DEBUT_REPONSE := TRUE;
    cg$rec.FIN_REPONSE := :new.FIN_REPONSE;
    cg$ind.FIN_REPONSE := TRUE;

    if not (cg$EVALUATION.called_from_package) then
        cg$EVALUATION.validate_arc(cg$rec);
        cg$EVALUATION.validate_domain(cg$rec);

        cg$EVALUATION.ins(cg$rec, cg$ind, FALSE);
        cg$EVALUATION.called_from_package := FALSE;
    end if;

    cg$EVALUATION.cg$table(cg$EVALUATION.idx).ID_EVALUATION := cg$rec.ID_EVALUATION;
    cg$EVALUATION.cg$tableind(cg$EVALUATION.idx).ID_EVALUATION := cg$ind.ID_EVALUATION;

    cg$EVALUATION.cg$table(cg$EVALUATION.idx).NO_ENSEIGNANT := cg$rec.NO_ENSEIGNANT;
    cg$EVALUATION.cg$tableind(cg$EVALUATION.idx).NO_ENSEIGNANT := cg$ind.NO_ENSEIGNANT;

    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_FORMATION := cg$rec.CODE_FORMATION;
    cg$EVALUATION.cg$tableind(cg$EVALUATION.idx).CODE_FORMATION := cg$ind.CODE_FORMATION;

    cg$EVALUATION.cg$table(cg$EVALUATION.idx).ANNEE_UNIVERSITAIRE := cg$rec.ANNEE_UNIVERSITAIRE;
    cg$EVALUATION.cg$tableind(cg$EVALUATION.idx).ANNEE_UNIVERSITAIRE := cg$ind.ANNEE_UNIVERSITAIRE;

    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_UE := cg$rec.CODE_UE;
    cg$EVALUATION.cg$tableind(cg$EVALUATION.idx).CODE_UE := cg$ind.CODE_UE;

    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_EC := cg$rec.CODE_EC;
    cg$EVALUATION.cg$tableind(cg$EVALUATION.idx).CODE_EC := cg$ind.CODE_EC;

    cg$EVALUATION.cg$table(cg$EVALUATION.idx).NO_EVALUATION := cg$rec.NO_EVALUATION;
    cg$EVALUATION.cg$tableind(cg$EVALUATION.idx).NO_EVALUATION := cg$ind.NO_EVALUATION;

    cg$EVALUATION.cg$table(cg$EVALUATION.idx).DESIGNATION := cg$rec.DESIGNATION;
    cg$EVALUATION.cg$tableind(cg$EVALUATION.idx).DESIGNATION := cg$ind.DESIGNATION;

    cg$EVALUATION.cg$table(cg$EVALUATION.idx).ETAT := cg$rec.ETAT;
    cg$EVALUATION.cg$tableind(cg$EVALUATION.idx).ETAT := cg$ind.ETAT;

    cg$EVALUATION.cg$table(cg$EVALUATION.idx).PERIODE := cg$rec.PERIODE;
    cg$EVALUATION.cg$tableind(cg$EVALUATION.idx).PERIODE := cg$ind.PERIODE;

    cg$EVALUATION.cg$table(cg$EVALUATION.idx).DEBUT_REPONSE := cg$rec.DEBUT_REPONSE;
    cg$EVALUATION.cg$tableind(cg$EVALUATION.idx).DEBUT_REPONSE := cg$ind.DEBUT_REPONSE;

    cg$EVALUATION.cg$table(cg$EVALUATION.idx).FIN_REPONSE := cg$rec.FIN_REPONSE;
    cg$EVALUATION.cg$tableind(cg$EVALUATION.idx).FIN_REPONSE := cg$ind.FIN_REPONSE;


    cg$EVALUATION.cg$table(cg$EVALUATION.idx).action_performed := 'INS';
    cg$EVALUATION.idx := cg$EVALUATION.idx + 1;

    :new.ID_EVALUATION := cg$rec.ID_EVALUATION;
    :new.NO_ENSEIGNANT := cg$rec.NO_ENSEIGNANT;
    :new.CODE_FORMATION := cg$rec.CODE_FORMATION;
    :new.ANNEE_UNIVERSITAIRE := cg$rec.ANNEE_UNIVERSITAIRE;
    :new.CODE_UE := cg$rec.CODE_UE;
    :new.CODE_EC := cg$rec.CODE_EC;
    :new.NO_EVALUATION := cg$rec.NO_EVALUATION;
    :new.DESIGNATION := cg$rec.DESIGNATION;
    :new.ETAT := cg$rec.ETAT;
    :new.PERIODE := cg$rec.PERIODE;
    :new.DEBUT_REPONSE := cg$rec.DEBUT_REPONSE;
    :new.FIN_REPONSE := cg$rec.FIN_REPONSE;

--  Application_logic Post-Before-Insert-row <<Start>>
--  Application_logic Post-Before-Insert-row << End >>
END;
/


-- No application logic defined for Trigger cg$AIR_EVALUATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AIR_EVALUATION
AFTER INSERT ON EVALUATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AIR_EVALUATION
/

PROMPT Creating After Insert Statement Trigger on 'EVALUATION'
CREATE OR REPLACE TRIGGER cg$AIS_EVALUATION 
AFTER INSERT ON EVALUATION
DECLARE
    idx      BINARY_INTEGER := cg$EVALUATION.cg$table.FIRST;
    cg$rec   cg$EVALUATION.cg$row_type;
    cg$old_rec   cg$EVALUATION.cg$row_type;
    fk_check INTEGER;
BEGIN
--  Application_logic Pre-After-Insert-statement <<Start>>
--  Application_logic Pre-After-Insert-statement << End >>


    IF NOT (cg$EVALUATION.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

	    IF (cg$EVALUATION.cg$table(idx).action_performed = 'INS') THEN
                cg$rec.ID_EVALUATION := cg$EVALUATION.cg$table(idx).ID_EVALUATION;
                cg$rec.NO_ENSEIGNANT := cg$EVALUATION.cg$table(idx).NO_ENSEIGNANT;
                cg$rec.CODE_FORMATION := cg$EVALUATION.cg$table(idx).CODE_FORMATION;
                cg$rec.ANNEE_UNIVERSITAIRE := cg$EVALUATION.cg$table(idx).ANNEE_UNIVERSITAIRE;
                cg$rec.CODE_UE := cg$EVALUATION.cg$table(idx).CODE_UE;
                cg$rec.CODE_EC := cg$EVALUATION.cg$table(idx).CODE_EC;
                cg$rec.NO_EVALUATION := cg$EVALUATION.cg$table(idx).NO_EVALUATION;
                cg$rec.DESIGNATION := cg$EVALUATION.cg$table(idx).DESIGNATION;
                cg$rec.ETAT := cg$EVALUATION.cg$table(idx).ETAT;
                cg$rec.PERIODE := cg$EVALUATION.cg$table(idx).PERIODE;
                cg$rec.DEBUT_REPONSE := cg$EVALUATION.cg$table(idx).DEBUT_REPONSE;
                cg$rec.FIN_REPONSE := cg$EVALUATION.cg$table(idx).FIN_REPONSE;

                cg$EVALUATION.validate_foreign_keys_ins(cg$rec);

                cg$EVALUATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$EVALUATION.cg$tableind(idx),
                                                    'INS'
                                                  );
            END IF;
            idx := cg$EVALUATION.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Insert-statement <<Start>>
--  Application_logic Post-After-Insert-statement << End >>

END;
/


    

PROMPT Creating Before Update Statement Trigger on 'EVALUATION'
CREATE OR REPLACE TRIGGER cg$BUS_EVALUATION
BEFORE UPDATE ON EVALUATION
BEGIN
--  Application_logic Pre-Before-Update-statement <<Start>>
--  Application_logic Pre-Before-Update-statement << End >>

    cg$EVALUATION.cg$table.DELETE;
    cg$EVALUATION.cg$tableind.DELETE;
    cg$EVALUATION.idx := 1;

--  Application_logic Post-Before-Update-statement <<Start>>
--  Application_logic Post-Before-Update-statement << End >>

END;
/
  

PROMPT Creating Before Update Row Trigger on 'EVALUATION'
CREATE OR REPLACE TRIGGER cg$BUR_EVALUATION 
BEFORE UPDATE ON EVALUATION FOR EACH ROW
DECLARE
    cg$rec     cg$EVALUATION.cg$row_type;
    cg$ind     cg$EVALUATION.cg$ind_type;
    cg$old_rec cg$EVALUATION.cg$row_type;  
BEGIN
--  Application_logic Pre-Before-Update-row <<Start>>
--  Application_logic Pre-Before-Update-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_EVALUATION := :new.ID_EVALUATION;
    cg$ind.ID_EVALUATION :=    (:new.ID_EVALUATION IS NULL AND :old.ID_EVALUATION IS NOT NULL )
                        OR (:new.ID_EVALUATION IS NOT NULL AND :old.ID_EVALUATION IS NULL)
                        OR NOT(:new.ID_EVALUATION = :old.ID_EVALUATION) ;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).ID_EVALUATION := :old.ID_EVALUATION;
    cg$rec.NO_ENSEIGNANT := :new.NO_ENSEIGNANT;
    cg$ind.NO_ENSEIGNANT :=    (:new.NO_ENSEIGNANT IS NULL AND :old.NO_ENSEIGNANT IS NOT NULL )
                        OR (:new.NO_ENSEIGNANT IS NOT NULL AND :old.NO_ENSEIGNANT IS NULL)
                        OR NOT(:new.NO_ENSEIGNANT = :old.NO_ENSEIGNANT) ;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).NO_ENSEIGNANT := :old.NO_ENSEIGNANT;
    cg$rec.CODE_FORMATION := :new.CODE_FORMATION;
    cg$ind.CODE_FORMATION :=    (:new.CODE_FORMATION IS NULL AND :old.CODE_FORMATION IS NOT NULL )
                        OR (:new.CODE_FORMATION IS NOT NULL AND :old.CODE_FORMATION IS NULL)
                        OR NOT(:new.CODE_FORMATION = :old.CODE_FORMATION) ;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_FORMATION := :old.CODE_FORMATION;
    cg$rec.ANNEE_UNIVERSITAIRE := :new.ANNEE_UNIVERSITAIRE;
    cg$ind.ANNEE_UNIVERSITAIRE :=    (:new.ANNEE_UNIVERSITAIRE IS NULL AND :old.ANNEE_UNIVERSITAIRE IS NOT NULL )
                        OR (:new.ANNEE_UNIVERSITAIRE IS NOT NULL AND :old.ANNEE_UNIVERSITAIRE IS NULL)
                        OR NOT(:new.ANNEE_UNIVERSITAIRE = :old.ANNEE_UNIVERSITAIRE) ;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).ANNEE_UNIVERSITAIRE := :old.ANNEE_UNIVERSITAIRE;
    cg$rec.CODE_UE := :new.CODE_UE;
    cg$ind.CODE_UE :=    (:new.CODE_UE IS NULL AND :old.CODE_UE IS NOT NULL )
                        OR (:new.CODE_UE IS NOT NULL AND :old.CODE_UE IS NULL)
                        OR NOT(:new.CODE_UE = :old.CODE_UE) ;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_UE := :old.CODE_UE;
    cg$rec.CODE_EC := :new.CODE_EC;
    cg$ind.CODE_EC :=    (:new.CODE_EC IS NULL AND :old.CODE_EC IS NOT NULL )
                        OR (:new.CODE_EC IS NOT NULL AND :old.CODE_EC IS NULL)
                        OR NOT(:new.CODE_EC = :old.CODE_EC) ;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_EC := :old.CODE_EC;
    cg$rec.NO_EVALUATION := :new.NO_EVALUATION;
    cg$ind.NO_EVALUATION :=    (:new.NO_EVALUATION IS NULL AND :old.NO_EVALUATION IS NOT NULL )
                        OR (:new.NO_EVALUATION IS NOT NULL AND :old.NO_EVALUATION IS NULL)
                        OR NOT(:new.NO_EVALUATION = :old.NO_EVALUATION) ;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).NO_EVALUATION := :old.NO_EVALUATION;
    cg$rec.DESIGNATION := :new.DESIGNATION;
    cg$ind.DESIGNATION :=    (:new.DESIGNATION IS NULL AND :old.DESIGNATION IS NOT NULL )
                        OR (:new.DESIGNATION IS NOT NULL AND :old.DESIGNATION IS NULL)
                        OR NOT(:new.DESIGNATION = :old.DESIGNATION) ;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).DESIGNATION := :old.DESIGNATION;
    cg$rec.ETAT := :new.ETAT;
    cg$ind.ETAT :=    (:new.ETAT IS NULL AND :old.ETAT IS NOT NULL )
                        OR (:new.ETAT IS NOT NULL AND :old.ETAT IS NULL)
                        OR NOT(:new.ETAT = :old.ETAT) ;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).ETAT := :old.ETAT;
    cg$rec.PERIODE := :new.PERIODE;
    cg$ind.PERIODE :=    (:new.PERIODE IS NULL AND :old.PERIODE IS NOT NULL )
                        OR (:new.PERIODE IS NOT NULL AND :old.PERIODE IS NULL)
                        OR NOT(:new.PERIODE = :old.PERIODE) ;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).PERIODE := :old.PERIODE;
    cg$rec.DEBUT_REPONSE := :new.DEBUT_REPONSE;
    cg$ind.DEBUT_REPONSE :=    (:new.DEBUT_REPONSE IS NULL AND :old.DEBUT_REPONSE IS NOT NULL )
                        OR (:new.DEBUT_REPONSE IS NOT NULL AND :old.DEBUT_REPONSE IS NULL)
                        OR NOT(:new.DEBUT_REPONSE = :old.DEBUT_REPONSE) ;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).DEBUT_REPONSE := :old.DEBUT_REPONSE;
    cg$rec.FIN_REPONSE := :new.FIN_REPONSE;
    cg$ind.FIN_REPONSE :=    (:new.FIN_REPONSE IS NULL AND :old.FIN_REPONSE IS NOT NULL )
                        OR (:new.FIN_REPONSE IS NOT NULL AND :old.FIN_REPONSE IS NULL)
                        OR NOT(:new.FIN_REPONSE = :old.FIN_REPONSE) ;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).FIN_REPONSE := :old.FIN_REPONSE;


    cg$EVALUATION.cg$table(cg$EVALUATION.idx).action_performed := 'UPD';     -- nkovoor
    cg$EVALUATION.idx := cg$EVALUATION.idx + 1;

    if not (cg$EVALUATION.called_from_package) then
        cg$EVALUATION.validate_arc(cg$rec);
        cg$EVALUATION.validate_domain(cg$rec, cg$ind);
        cg$EVALUATION.validate_domain_cascade_update(cg$old_rec);   

        cg$EVALUATION.upd(cg$rec, cg$ind, FALSE);
        cg$EVALUATION.called_from_package := FALSE;
    end if;

    :new.NO_ENSEIGNANT := cg$rec.NO_ENSEIGNANT;
    :new.CODE_FORMATION := cg$rec.CODE_FORMATION;
    :new.ANNEE_UNIVERSITAIRE := cg$rec.ANNEE_UNIVERSITAIRE;
    :new.CODE_UE := cg$rec.CODE_UE;
    :new.CODE_EC := cg$rec.CODE_EC;
    :new.NO_EVALUATION := cg$rec.NO_EVALUATION;
    :new.DESIGNATION := cg$rec.DESIGNATION;
    :new.ETAT := cg$rec.ETAT;
    :new.PERIODE := cg$rec.PERIODE;
    :new.DEBUT_REPONSE := cg$rec.DEBUT_REPONSE;
    :new.FIN_REPONSE := cg$rec.FIN_REPONSE;
--  Application_logic Post-Before-Update-row <<Start>>
--  Application_logic Post-Before-Update-row << End >>
END;
/

-- No application logic defined for Trigger cg$AUR_EVALUATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AUR_EVALUATION
AFTER UPDATE ON EVALUATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AUR_EVALUATION
/






PROMPT Creating After Update Statement Trigger on 'EVALUATION'
CREATE OR REPLACE TRIGGER cg$AUS_EVALUATION 
AFTER UPDATE ON EVALUATION
DECLARE
    idx        BINARY_INTEGER := cg$EVALUATION.cg$table.FIRST;
    cg$old_rec cg$EVALUATION.cg$row_type;
    cg$rec     cg$EVALUATION.cg$row_type;
    cg$ind     cg$EVALUATION.cg$ind_type;
BEGIN
--  Application_logic Pre-After-Update-statement <<Start>>
--  Application_logic Pre-After-Update-statement << End >>

    WHILE idx IS NOT NULL LOOP

        IF (cg$EVALUATION.cg$table(idx).action_performed = 'UPD') THEN
            cg$old_rec.ID_EVALUATION := cg$EVALUATION.cg$table(idx).ID_EVALUATION;
            cg$old_rec.NO_ENSEIGNANT := cg$EVALUATION.cg$table(idx).NO_ENSEIGNANT;
            cg$old_rec.CODE_FORMATION := cg$EVALUATION.cg$table(idx).CODE_FORMATION;
            cg$old_rec.ANNEE_UNIVERSITAIRE := cg$EVALUATION.cg$table(idx).ANNEE_UNIVERSITAIRE;
            cg$old_rec.CODE_UE := cg$EVALUATION.cg$table(idx).CODE_UE;
            cg$old_rec.CODE_EC := cg$EVALUATION.cg$table(idx).CODE_EC;
            cg$old_rec.NO_EVALUATION := cg$EVALUATION.cg$table(idx).NO_EVALUATION;
            cg$old_rec.DESIGNATION := cg$EVALUATION.cg$table(idx).DESIGNATION;
            cg$old_rec.ETAT := cg$EVALUATION.cg$table(idx).ETAT;
            cg$old_rec.PERIODE := cg$EVALUATION.cg$table(idx).PERIODE;
            cg$old_rec.DEBUT_REPONSE := cg$EVALUATION.cg$table(idx).DEBUT_REPONSE;
            cg$old_rec.FIN_REPONSE := cg$EVALUATION.cg$table(idx).FIN_REPONSE;

            IF NOT (cg$EVALUATION.called_from_package) THEN
                idx := cg$EVALUATION.cg$table.NEXT(idx);

                IF (idx IS NOT NULL) THEN
                cg$rec.ID_EVALUATION := cg$EVALUATION.cg$table(idx).ID_EVALUATION;
                cg$ind.ID_EVALUATION := updating('ID_EVALUATION');
                cg$rec.NO_ENSEIGNANT := cg$EVALUATION.cg$table(idx).NO_ENSEIGNANT;
                cg$ind.NO_ENSEIGNANT := updating('NO_ENSEIGNANT');
                cg$rec.CODE_FORMATION := cg$EVALUATION.cg$table(idx).CODE_FORMATION;
                cg$ind.CODE_FORMATION := updating('CODE_FORMATION');
                cg$rec.ANNEE_UNIVERSITAIRE := cg$EVALUATION.cg$table(idx).ANNEE_UNIVERSITAIRE;
                cg$ind.ANNEE_UNIVERSITAIRE := updating('ANNEE_UNIVERSITAIRE');
                cg$rec.CODE_UE := cg$EVALUATION.cg$table(idx).CODE_UE;
                cg$ind.CODE_UE := updating('CODE_UE');
                cg$rec.CODE_EC := cg$EVALUATION.cg$table(idx).CODE_EC;
                cg$ind.CODE_EC := updating('CODE_EC');
                cg$rec.NO_EVALUATION := cg$EVALUATION.cg$table(idx).NO_EVALUATION;
                cg$ind.NO_EVALUATION := updating('NO_EVALUATION');
                cg$rec.DESIGNATION := cg$EVALUATION.cg$table(idx).DESIGNATION;
                cg$ind.DESIGNATION := updating('DESIGNATION');
                cg$rec.ETAT := cg$EVALUATION.cg$table(idx).ETAT;
                cg$ind.ETAT := updating('ETAT');
                cg$rec.PERIODE := cg$EVALUATION.cg$table(idx).PERIODE;
                cg$ind.PERIODE := updating('PERIODE');
                cg$rec.DEBUT_REPONSE := cg$EVALUATION.cg$table(idx).DEBUT_REPONSE;
                cg$ind.DEBUT_REPONSE := updating('DEBUT_REPONSE');
                cg$rec.FIN_REPONSE := cg$EVALUATION.cg$table(idx).FIN_REPONSE;
                cg$ind.FIN_REPONSE := updating('FIN_REPONSE');

                cg$EVALUATION.validate_foreign_keys_upd(cg$rec, cg$old_rec, cg$ind);

                cg$EVALUATION.upd_denorm2( cg$rec,
                                               cg$EVALUATION.cg$tableind(idx)
                                             );
                cg$EVALUATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$EVALUATION.cg$tableind(idx)
				        				                  );
                cg$EVALUATION.cascade_update(cg$rec, cg$old_rec);
                cg$EVALUATION.domain_cascade_update(cg$rec, cg$ind, cg$old_rec);             
        
                cg$EVALUATION.called_from_package := FALSE;
                END IF;
            END IF; 
        END IF;
        idx := cg$EVALUATION.cg$table.NEXT(idx);
    END LOOP;

    cg$EVALUATION.cg$table.DELETE;

--  Application_logic Post-After-Update-statement <<Start>>
--  Application_logic Post-After-Update-statement << End >>

END;
/

PROMPT Creating Before Delete Statement Trigger on 'EVALUATION'
CREATE OR REPLACE TRIGGER cg$BDS_EVALUATION
BEFORE DELETE ON EVALUATION
BEGIN
--  Application_logic Pre-Before-Delete-statement <<Start>>
--  Application_logic Pre-Before-Delete-statement << End >>

    cg$EVALUATION.cg$table.DELETE;
    cg$EVALUATION.cg$tableind.DELETE;
    cg$EVALUATION.idx := 1;

--  Application_logic Post-Before-Delete-statement <<Start>>
--  Application_logic Post-Before-Delete-statement << End >>
END;
/
  

PROMPT Creating Before Delete Row Trigger on 'EVALUATION'
CREATE OR REPLACE TRIGGER cg$BDR_EVALUATION 
BEFORE DELETE ON EVALUATION FOR EACH ROW
DECLARE
    cg$pk cg$EVALUATION.cg$pk_type;
    cg$rec cg$EVALUATION.cg$row_type;
    cg$ind cg$EVALUATION.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Delete-row <<Start>>
--  Application_logic Pre-Before-Delete-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$pk.ID_EVALUATION  := :old.ID_EVALUATION;
    cg$rec.ID_EVALUATION := :old.ID_EVALUATION;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).ID_EVALUATION := :old.ID_EVALUATION;
    cg$rec.ANNEE_UNIVERSITAIRE := :old.ANNEE_UNIVERSITAIRE;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).ANNEE_UNIVERSITAIRE := :old.ANNEE_UNIVERSITAIRE;
    cg$rec.NO_ENSEIGNANT := :old.NO_ENSEIGNANT;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).NO_ENSEIGNANT := :old.NO_ENSEIGNANT;
    cg$rec.NO_EVALUATION := :old.NO_EVALUATION;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).NO_EVALUATION := :old.NO_EVALUATION;
    cg$rec.CODE_FORMATION := :old.CODE_FORMATION;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_FORMATION := :old.CODE_FORMATION;
    cg$rec.CODE_UE := :old.CODE_UE;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_UE := :old.CODE_UE;
    cg$rec.CODE_FORMATION := :old.CODE_FORMATION;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_FORMATION := :old.CODE_FORMATION;
    cg$rec.CODE_UE := :old.CODE_UE;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_UE := :old.CODE_UE;
    cg$rec.CODE_FORMATION := :old.CODE_FORMATION;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_FORMATION := :old.CODE_FORMATION;
    cg$rec.ANNEE_UNIVERSITAIRE := :old.ANNEE_UNIVERSITAIRE;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).ANNEE_UNIVERSITAIRE := :old.ANNEE_UNIVERSITAIRE;
    cg$rec.NO_ENSEIGNANT := :old.NO_ENSEIGNANT;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).NO_ENSEIGNANT := :old.NO_ENSEIGNANT;
    cg$rec.CODE_FORMATION := :old.CODE_FORMATION;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_FORMATION := :old.CODE_FORMATION;
    cg$rec.CODE_UE := :old.CODE_UE;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_UE := :old.CODE_UE;
    cg$rec.CODE_EC := :old.CODE_EC;
    cg$EVALUATION.cg$table(cg$EVALUATION.idx).CODE_EC := :old.CODE_EC;


    cg$EVALUATION.cg$table(cg$EVALUATION.idx).action_performed := 'DEL';
    cg$EVALUATION.idx := cg$EVALUATION.idx + 1;

    if not (cg$EVALUATION.called_from_package) then
        cg$EVALUATION.validate_domain_cascade_delete(cg$rec);   
        cg$EVALUATION.del(cg$pk, FALSE);
        cg$EVALUATION.called_from_package := FALSE;
    end if;

--  Application_logic Post-Before-Delete-row <<Start>>
--  Application_logic Post-Before-Delete-row << End >>
END;
/

-- No application logic defined for Trigger cg$ADR_EVALUATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$ADR_EVALUATION
AFTER DELETE ON EVALUATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$ADR_EVALUATION
/

PROMPT Creating After Delete Statement Trigger on 'EVALUATION'
CREATE OR REPLACE TRIGGER cg$ADS_EVALUATION 
AFTER DELETE ON EVALUATION
DECLARE
    idx        BINARY_INTEGER := cg$EVALUATION.cg$table.FIRST;
    cg$rec   cg$EVALUATION.cg$row_type;
    cg$old_rec   cg$EVALUATION.cg$row_type;
BEGIN
--  Application_logic Pre-After-Delete-statement <<Start>>
--  Application_logic Pre-After-Delete-statement << End >>

    IF NOT (cg$EVALUATION.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

            IF (cg$EVALUATION.cg$table(idx).action_performed = 'DEL') THEN
                cg$rec.ID_EVALUATION := cg$EVALUATION.cg$table(idx).ID_EVALUATION;
                cg$EVALUATION.cg$tableind(idx).ID_EVALUATION := TRUE;
                cg$rec.NO_ENSEIGNANT := cg$EVALUATION.cg$table(idx).NO_ENSEIGNANT;
                cg$EVALUATION.cg$tableind(idx).NO_ENSEIGNANT := TRUE;
                cg$rec.CODE_FORMATION := cg$EVALUATION.cg$table(idx).CODE_FORMATION;
                cg$EVALUATION.cg$tableind(idx).CODE_FORMATION := TRUE;
                cg$rec.ANNEE_UNIVERSITAIRE := cg$EVALUATION.cg$table(idx).ANNEE_UNIVERSITAIRE;
                cg$EVALUATION.cg$tableind(idx).ANNEE_UNIVERSITAIRE := TRUE;
                cg$rec.CODE_UE := cg$EVALUATION.cg$table(idx).CODE_UE;
                cg$EVALUATION.cg$tableind(idx).CODE_UE := TRUE;
                cg$rec.CODE_EC := cg$EVALUATION.cg$table(idx).CODE_EC;
                cg$EVALUATION.cg$tableind(idx).CODE_EC := TRUE;
                cg$rec.NO_EVALUATION := cg$EVALUATION.cg$table(idx).NO_EVALUATION;
                cg$EVALUATION.cg$tableind(idx).NO_EVALUATION := TRUE;
                cg$rec.DESIGNATION := cg$EVALUATION.cg$table(idx).DESIGNATION;
                cg$EVALUATION.cg$tableind(idx).DESIGNATION := TRUE;
                cg$rec.ETAT := cg$EVALUATION.cg$table(idx).ETAT;
                cg$EVALUATION.cg$tableind(idx).ETAT := TRUE;
                cg$rec.PERIODE := cg$EVALUATION.cg$table(idx).PERIODE;
                cg$EVALUATION.cg$tableind(idx).PERIODE := TRUE;
                cg$rec.DEBUT_REPONSE := cg$EVALUATION.cg$table(idx).DEBUT_REPONSE;
                cg$EVALUATION.cg$tableind(idx).DEBUT_REPONSE := TRUE;
                cg$rec.FIN_REPONSE := cg$EVALUATION.cg$table(idx).FIN_REPONSE;
                cg$EVALUATION.cg$tableind(idx).FIN_REPONSE := TRUE;

                cg$EVALUATION.validate_foreign_keys_del(cg$rec);
                cg$EVALUATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$EVALUATION.cg$tableind(idx),
                                                    'DEL'
                                                  );
	
                cg$EVALUATION.cascade_delete(cg$rec);
                cg$EVALUATION.domain_cascade_delete(cg$rec);             
            END IF;
            idx := cg$EVALUATION.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Delete-statement <<Start>>
--  Application_logic Post-After-Delete-statement << End >>

END;
/



