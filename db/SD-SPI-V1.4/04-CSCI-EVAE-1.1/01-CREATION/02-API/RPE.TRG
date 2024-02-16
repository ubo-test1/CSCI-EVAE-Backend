
PROMPT Creating Trigger Logic for Table 'REPONSE_EVALUATION'
PROMPT Creating Before Insert Statement Trigger on 'REPONSE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BIS_REPONSE_EVALUATION
BEFORE INSERT ON REPONSE_EVALUATION
BEGIN
--  Application_logic Pre-Before-Insert-statement <<Start>>
--  Application_logic Pre-Before-Insert-statement << End >>

    cg$REPONSE_EVALUATION.cg$table.DELETE;
    cg$REPONSE_EVALUATION.cg$tableind.DELETE;
    cg$REPONSE_EVALUATION.idx := 1;

--  Application_logic Post-Before-Insert-statement <<Start>>
--  Application_logic Post-Before-Insert-statement << End >>
END;
/
  

PROMPT Creating Before Insert Row Trigger on 'REPONSE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BIR_REPONSE_EVALUATION 
BEFORE INSERT ON REPONSE_EVALUATION FOR EACH ROW
DECLARE
    cg$rec cg$REPONSE_EVALUATION.cg$row_type;
    cg$ind cg$REPONSE_EVALUATION.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Insert-row <<Start>>
--  Application_logic Pre-Before-Insert-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_REPONSE_EVALUATION := :new.ID_REPONSE_EVALUATION;
    IF (:new.ID_REPONSE_EVALUATION IS NULL) THEN
        cg$ind.ID_REPONSE_EVALUATION := FALSE;
    ELSE
        cg$ind.ID_REPONSE_EVALUATION := TRUE;
    END IF;
    cg$rec.ID_EVALUATION := :new.ID_EVALUATION;
    cg$ind.ID_EVALUATION := TRUE;
    cg$rec.NO_ETUDIANT := :new.NO_ETUDIANT;
    cg$ind.NO_ETUDIANT := TRUE;
    cg$rec.COMMENTAIRE := :new.COMMENTAIRE;
    cg$ind.COMMENTAIRE := TRUE;
    cg$rec.NOM := :new.NOM;
    cg$ind.NOM := TRUE;
    cg$rec.PRENOM := :new.PRENOM;
    cg$ind.PRENOM := TRUE;

    if not (cg$REPONSE_EVALUATION.called_from_package) then
        cg$REPONSE_EVALUATION.validate_arc(cg$rec);
        cg$REPONSE_EVALUATION.validate_domain(cg$rec);

        cg$REPONSE_EVALUATION.ins(cg$rec, cg$ind, FALSE);
        cg$REPONSE_EVALUATION.called_from_package := FALSE;
    end if;

    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).ID_REPONSE_EVALUATION := cg$rec.ID_REPONSE_EVALUATION;
    cg$REPONSE_EVALUATION.cg$tableind(cg$REPONSE_EVALUATION.idx).ID_REPONSE_EVALUATION := cg$ind.ID_REPONSE_EVALUATION;

    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).ID_EVALUATION := cg$rec.ID_EVALUATION;
    cg$REPONSE_EVALUATION.cg$tableind(cg$REPONSE_EVALUATION.idx).ID_EVALUATION := cg$ind.ID_EVALUATION;

    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).NO_ETUDIANT := cg$rec.NO_ETUDIANT;
    cg$REPONSE_EVALUATION.cg$tableind(cg$REPONSE_EVALUATION.idx).NO_ETUDIANT := cg$ind.NO_ETUDIANT;

    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).COMMENTAIRE := cg$rec.COMMENTAIRE;
    cg$REPONSE_EVALUATION.cg$tableind(cg$REPONSE_EVALUATION.idx).COMMENTAIRE := cg$ind.COMMENTAIRE;

    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).NOM := cg$rec.NOM;
    cg$REPONSE_EVALUATION.cg$tableind(cg$REPONSE_EVALUATION.idx).NOM := cg$ind.NOM;

    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).PRENOM := cg$rec.PRENOM;
    cg$REPONSE_EVALUATION.cg$tableind(cg$REPONSE_EVALUATION.idx).PRENOM := cg$ind.PRENOM;


    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).action_performed := 'INS';
    cg$REPONSE_EVALUATION.idx := cg$REPONSE_EVALUATION.idx + 1;

    :new.ID_REPONSE_EVALUATION := cg$rec.ID_REPONSE_EVALUATION;
    :new.ID_EVALUATION := cg$rec.ID_EVALUATION;
    :new.NO_ETUDIANT := cg$rec.NO_ETUDIANT;
    :new.COMMENTAIRE := cg$rec.COMMENTAIRE;
    :new.NOM := cg$rec.NOM;
    :new.PRENOM := cg$rec.PRENOM;

--  Application_logic Post-Before-Insert-row <<Start>>
--  Application_logic Post-Before-Insert-row << End >>
END;
/


-- No application logic defined for Trigger cg$AIR_REPONSE_EVALUATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AIR_REPONSE_EVALUATION
AFTER INSERT ON REPONSE_EVALUATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AIR_REPONSE_EVALUATION
/

PROMPT Creating After Insert Statement Trigger on 'REPONSE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$AIS_REPONSE_EVALUATION 
AFTER INSERT ON REPONSE_EVALUATION
DECLARE
    idx      BINARY_INTEGER := cg$REPONSE_EVALUATION.cg$table.FIRST;
    cg$rec   cg$REPONSE_EVALUATION.cg$row_type;
    cg$old_rec   cg$REPONSE_EVALUATION.cg$row_type;
    fk_check INTEGER;
BEGIN
--  Application_logic Pre-After-Insert-statement <<Start>>
--  Application_logic Pre-After-Insert-statement << End >>


    IF NOT (cg$REPONSE_EVALUATION.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

	    IF (cg$REPONSE_EVALUATION.cg$table(idx).action_performed = 'INS') THEN
                cg$rec.ID_REPONSE_EVALUATION := cg$REPONSE_EVALUATION.cg$table(idx).ID_REPONSE_EVALUATION;
                cg$rec.ID_EVALUATION := cg$REPONSE_EVALUATION.cg$table(idx).ID_EVALUATION;
                cg$rec.NO_ETUDIANT := cg$REPONSE_EVALUATION.cg$table(idx).NO_ETUDIANT;
                cg$rec.COMMENTAIRE := cg$REPONSE_EVALUATION.cg$table(idx).COMMENTAIRE;
                cg$rec.NOM := cg$REPONSE_EVALUATION.cg$table(idx).NOM;
                cg$rec.PRENOM := cg$REPONSE_EVALUATION.cg$table(idx).PRENOM;

                cg$REPONSE_EVALUATION.validate_foreign_keys_ins(cg$rec);

                cg$REPONSE_EVALUATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$REPONSE_EVALUATION.cg$tableind(idx),
                                                    'INS'
                                                  );
            END IF;
            idx := cg$REPONSE_EVALUATION.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Insert-statement <<Start>>
--  Application_logic Post-After-Insert-statement << End >>

END;
/


    

PROMPT Creating Before Update Statement Trigger on 'REPONSE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BUS_REPONSE_EVALUATION
BEFORE UPDATE ON REPONSE_EVALUATION
BEGIN
--  Application_logic Pre-Before-Update-statement <<Start>>
--  Application_logic Pre-Before-Update-statement << End >>

    cg$REPONSE_EVALUATION.cg$table.DELETE;
    cg$REPONSE_EVALUATION.cg$tableind.DELETE;
    cg$REPONSE_EVALUATION.idx := 1;

--  Application_logic Post-Before-Update-statement <<Start>>
--  Application_logic Post-Before-Update-statement << End >>

END;
/
  

PROMPT Creating Before Update Row Trigger on 'REPONSE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BUR_REPONSE_EVALUATION 
BEFORE UPDATE ON REPONSE_EVALUATION FOR EACH ROW
DECLARE
    cg$rec     cg$REPONSE_EVALUATION.cg$row_type;
    cg$ind     cg$REPONSE_EVALUATION.cg$ind_type;
    cg$old_rec cg$REPONSE_EVALUATION.cg$row_type;  
BEGIN
--  Application_logic Pre-Before-Update-row <<Start>>
--  Application_logic Pre-Before-Update-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_REPONSE_EVALUATION := :new.ID_REPONSE_EVALUATION;
    cg$ind.ID_REPONSE_EVALUATION :=    (:new.ID_REPONSE_EVALUATION IS NULL AND :old.ID_REPONSE_EVALUATION IS NOT NULL )
                        OR (:new.ID_REPONSE_EVALUATION IS NOT NULL AND :old.ID_REPONSE_EVALUATION IS NULL)
                        OR NOT(:new.ID_REPONSE_EVALUATION = :old.ID_REPONSE_EVALUATION) ;
    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).ID_REPONSE_EVALUATION := :old.ID_REPONSE_EVALUATION;
    cg$rec.ID_EVALUATION := :new.ID_EVALUATION;
    cg$ind.ID_EVALUATION :=    (:new.ID_EVALUATION IS NULL AND :old.ID_EVALUATION IS NOT NULL )
                        OR (:new.ID_EVALUATION IS NOT NULL AND :old.ID_EVALUATION IS NULL)
                        OR NOT(:new.ID_EVALUATION = :old.ID_EVALUATION) ;
    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).ID_EVALUATION := :old.ID_EVALUATION;
    cg$rec.NO_ETUDIANT := :new.NO_ETUDIANT;
    cg$ind.NO_ETUDIANT :=    (:new.NO_ETUDIANT IS NULL AND :old.NO_ETUDIANT IS NOT NULL )
                        OR (:new.NO_ETUDIANT IS NOT NULL AND :old.NO_ETUDIANT IS NULL)
                        OR NOT(:new.NO_ETUDIANT = :old.NO_ETUDIANT) ;
    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).NO_ETUDIANT := :old.NO_ETUDIANT;
    cg$rec.COMMENTAIRE := :new.COMMENTAIRE;
    cg$ind.COMMENTAIRE :=    (:new.COMMENTAIRE IS NULL AND :old.COMMENTAIRE IS NOT NULL )
                        OR (:new.COMMENTAIRE IS NOT NULL AND :old.COMMENTAIRE IS NULL)
                        OR NOT(:new.COMMENTAIRE = :old.COMMENTAIRE) ;
    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).COMMENTAIRE := :old.COMMENTAIRE;
    cg$rec.NOM := :new.NOM;
    cg$ind.NOM :=    (:new.NOM IS NULL AND :old.NOM IS NOT NULL )
                        OR (:new.NOM IS NOT NULL AND :old.NOM IS NULL)
                        OR NOT(:new.NOM = :old.NOM) ;
    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).NOM := :old.NOM;
    cg$rec.PRENOM := :new.PRENOM;
    cg$ind.PRENOM :=    (:new.PRENOM IS NULL AND :old.PRENOM IS NOT NULL )
                        OR (:new.PRENOM IS NOT NULL AND :old.PRENOM IS NULL)
                        OR NOT(:new.PRENOM = :old.PRENOM) ;
    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).PRENOM := :old.PRENOM;


    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).action_performed := 'UPD';     -- nkovoor
    cg$REPONSE_EVALUATION.idx := cg$REPONSE_EVALUATION.idx + 1;

    if not (cg$REPONSE_EVALUATION.called_from_package) then
        cg$REPONSE_EVALUATION.validate_arc(cg$rec);
        cg$REPONSE_EVALUATION.validate_domain(cg$rec, cg$ind);
        cg$REPONSE_EVALUATION.validate_domain_cascade_update(cg$old_rec);   

        cg$REPONSE_EVALUATION.upd(cg$rec, cg$ind, FALSE);
        cg$REPONSE_EVALUATION.called_from_package := FALSE;
    end if;

    :new.ID_EVALUATION := cg$rec.ID_EVALUATION;
    :new.NO_ETUDIANT := cg$rec.NO_ETUDIANT;
    :new.COMMENTAIRE := cg$rec.COMMENTAIRE;
    :new.NOM := cg$rec.NOM;
    :new.PRENOM := cg$rec.PRENOM;
--  Application_logic Post-Before-Update-row <<Start>>
--  Application_logic Post-Before-Update-row << End >>
END;
/

-- No application logic defined for Trigger cg$AUR_REPONSE_EVALUATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AUR_REPONSE_EVALUATION
AFTER UPDATE ON REPONSE_EVALUATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AUR_REPONSE_EVALUATION
/






PROMPT Creating After Update Statement Trigger on 'REPONSE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$AUS_REPONSE_EVALUATION 
AFTER UPDATE ON REPONSE_EVALUATION
DECLARE
    idx        BINARY_INTEGER := cg$REPONSE_EVALUATION.cg$table.FIRST;
    cg$old_rec cg$REPONSE_EVALUATION.cg$row_type;
    cg$rec     cg$REPONSE_EVALUATION.cg$row_type;
    cg$ind     cg$REPONSE_EVALUATION.cg$ind_type;
BEGIN
--  Application_logic Pre-After-Update-statement <<Start>>
--  Application_logic Pre-After-Update-statement << End >>

    WHILE idx IS NOT NULL LOOP

        IF (cg$REPONSE_EVALUATION.cg$table(idx).action_performed = 'UPD') THEN
            cg$old_rec.ID_REPONSE_EVALUATION := cg$REPONSE_EVALUATION.cg$table(idx).ID_REPONSE_EVALUATION;
            cg$old_rec.ID_EVALUATION := cg$REPONSE_EVALUATION.cg$table(idx).ID_EVALUATION;
            cg$old_rec.NO_ETUDIANT := cg$REPONSE_EVALUATION.cg$table(idx).NO_ETUDIANT;
            cg$old_rec.COMMENTAIRE := cg$REPONSE_EVALUATION.cg$table(idx).COMMENTAIRE;
            cg$old_rec.NOM := cg$REPONSE_EVALUATION.cg$table(idx).NOM;
            cg$old_rec.PRENOM := cg$REPONSE_EVALUATION.cg$table(idx).PRENOM;

            IF NOT (cg$REPONSE_EVALUATION.called_from_package) THEN
                idx := cg$REPONSE_EVALUATION.cg$table.NEXT(idx);

                IF (idx IS NOT NULL) THEN
                cg$rec.ID_REPONSE_EVALUATION := cg$REPONSE_EVALUATION.cg$table(idx).ID_REPONSE_EVALUATION;
                cg$ind.ID_REPONSE_EVALUATION := updating('ID_REPONSE_EVALUATION');
                cg$rec.ID_EVALUATION := cg$REPONSE_EVALUATION.cg$table(idx).ID_EVALUATION;
                cg$ind.ID_EVALUATION := updating('ID_EVALUATION');
                cg$rec.NO_ETUDIANT := cg$REPONSE_EVALUATION.cg$table(idx).NO_ETUDIANT;
                cg$ind.NO_ETUDIANT := updating('NO_ETUDIANT');
                cg$rec.COMMENTAIRE := cg$REPONSE_EVALUATION.cg$table(idx).COMMENTAIRE;
                cg$ind.COMMENTAIRE := updating('COMMENTAIRE');
                cg$rec.NOM := cg$REPONSE_EVALUATION.cg$table(idx).NOM;
                cg$ind.NOM := updating('NOM');
                cg$rec.PRENOM := cg$REPONSE_EVALUATION.cg$table(idx).PRENOM;
                cg$ind.PRENOM := updating('PRENOM');

                cg$REPONSE_EVALUATION.validate_foreign_keys_upd(cg$rec, cg$old_rec, cg$ind);

                cg$REPONSE_EVALUATION.upd_denorm2( cg$rec,
                                               cg$REPONSE_EVALUATION.cg$tableind(idx)
                                             );
                cg$REPONSE_EVALUATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$REPONSE_EVALUATION.cg$tableind(idx)
				        				                  );
                cg$REPONSE_EVALUATION.cascade_update(cg$rec, cg$old_rec);
                cg$REPONSE_EVALUATION.domain_cascade_update(cg$rec, cg$ind, cg$old_rec);             
        
                cg$REPONSE_EVALUATION.called_from_package := FALSE;
                END IF;
            END IF; 
        END IF;
        idx := cg$REPONSE_EVALUATION.cg$table.NEXT(idx);
    END LOOP;

    cg$REPONSE_EVALUATION.cg$table.DELETE;

--  Application_logic Post-After-Update-statement <<Start>>
--  Application_logic Post-After-Update-statement << End >>

END;
/

PROMPT Creating Before Delete Statement Trigger on 'REPONSE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BDS_REPONSE_EVALUATION
BEFORE DELETE ON REPONSE_EVALUATION
BEGIN
--  Application_logic Pre-Before-Delete-statement <<Start>>
--  Application_logic Pre-Before-Delete-statement << End >>

    cg$REPONSE_EVALUATION.cg$table.DELETE;
    cg$REPONSE_EVALUATION.cg$tableind.DELETE;
    cg$REPONSE_EVALUATION.idx := 1;

--  Application_logic Post-Before-Delete-statement <<Start>>
--  Application_logic Post-Before-Delete-statement << End >>
END;
/
  

PROMPT Creating Before Delete Row Trigger on 'REPONSE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$BDR_REPONSE_EVALUATION 
BEFORE DELETE ON REPONSE_EVALUATION FOR EACH ROW
DECLARE
    cg$pk cg$REPONSE_EVALUATION.cg$pk_type;
    cg$rec cg$REPONSE_EVALUATION.cg$row_type;
    cg$ind cg$REPONSE_EVALUATION.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Delete-row <<Start>>
--  Application_logic Pre-Before-Delete-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$pk.ID_REPONSE_EVALUATION  := :old.ID_REPONSE_EVALUATION;
    cg$rec.ID_REPONSE_EVALUATION := :old.ID_REPONSE_EVALUATION;
    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).ID_REPONSE_EVALUATION := :old.ID_REPONSE_EVALUATION;
    cg$rec.NO_ETUDIANT := :old.NO_ETUDIANT;
    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).NO_ETUDIANT := :old.NO_ETUDIANT;
    cg$rec.ID_EVALUATION := :old.ID_EVALUATION;
    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).ID_EVALUATION := :old.ID_EVALUATION;


    cg$REPONSE_EVALUATION.cg$table(cg$REPONSE_EVALUATION.idx).action_performed := 'DEL';
    cg$REPONSE_EVALUATION.idx := cg$REPONSE_EVALUATION.idx + 1;

    if not (cg$REPONSE_EVALUATION.called_from_package) then
        cg$REPONSE_EVALUATION.validate_domain_cascade_delete(cg$rec);   
        cg$REPONSE_EVALUATION.del(cg$pk, FALSE);
        cg$REPONSE_EVALUATION.called_from_package := FALSE;
    end if;

--  Application_logic Post-Before-Delete-row <<Start>>
--  Application_logic Post-Before-Delete-row << End >>
END;
/

-- No application logic defined for Trigger cg$ADR_REPONSE_EVALUATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$ADR_REPONSE_EVALUATION
AFTER DELETE ON REPONSE_EVALUATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$ADR_REPONSE_EVALUATION
/

PROMPT Creating After Delete Statement Trigger on 'REPONSE_EVALUATION'
CREATE OR REPLACE TRIGGER cg$ADS_REPONSE_EVALUATION 
AFTER DELETE ON REPONSE_EVALUATION
DECLARE
    idx        BINARY_INTEGER := cg$REPONSE_EVALUATION.cg$table.FIRST;
    cg$rec   cg$REPONSE_EVALUATION.cg$row_type;
    cg$old_rec   cg$REPONSE_EVALUATION.cg$row_type;
BEGIN
--  Application_logic Pre-After-Delete-statement <<Start>>
--  Application_logic Pre-After-Delete-statement << End >>

    IF NOT (cg$REPONSE_EVALUATION.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

            IF (cg$REPONSE_EVALUATION.cg$table(idx).action_performed = 'DEL') THEN
                cg$rec.ID_REPONSE_EVALUATION := cg$REPONSE_EVALUATION.cg$table(idx).ID_REPONSE_EVALUATION;
                cg$REPONSE_EVALUATION.cg$tableind(idx).ID_REPONSE_EVALUATION := TRUE;
                cg$rec.ID_EVALUATION := cg$REPONSE_EVALUATION.cg$table(idx).ID_EVALUATION;
                cg$REPONSE_EVALUATION.cg$tableind(idx).ID_EVALUATION := TRUE;
                cg$rec.NO_ETUDIANT := cg$REPONSE_EVALUATION.cg$table(idx).NO_ETUDIANT;
                cg$REPONSE_EVALUATION.cg$tableind(idx).NO_ETUDIANT := TRUE;
                cg$rec.COMMENTAIRE := cg$REPONSE_EVALUATION.cg$table(idx).COMMENTAIRE;
                cg$REPONSE_EVALUATION.cg$tableind(idx).COMMENTAIRE := TRUE;
                cg$rec.NOM := cg$REPONSE_EVALUATION.cg$table(idx).NOM;
                cg$REPONSE_EVALUATION.cg$tableind(idx).NOM := TRUE;
                cg$rec.PRENOM := cg$REPONSE_EVALUATION.cg$table(idx).PRENOM;
                cg$REPONSE_EVALUATION.cg$tableind(idx).PRENOM := TRUE;

                cg$REPONSE_EVALUATION.validate_foreign_keys_del(cg$rec);
                cg$REPONSE_EVALUATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$REPONSE_EVALUATION.cg$tableind(idx),
                                                    'DEL'
                                                  );
	
                cg$REPONSE_EVALUATION.cascade_delete(cg$rec);
                cg$REPONSE_EVALUATION.domain_cascade_delete(cg$rec);             
            END IF;
            idx := cg$REPONSE_EVALUATION.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Delete-statement <<Start>>
--  Application_logic Post-After-Delete-statement << End >>

END;
/



