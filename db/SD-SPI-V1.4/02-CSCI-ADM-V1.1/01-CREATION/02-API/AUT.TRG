
PROMPT Creating Trigger Logic for Table 'AUTHENTIFICATION'
PROMPT Creating Before Insert Statement Trigger on 'AUTHENTIFICATION'
CREATE OR REPLACE TRIGGER cg$BIS_AUTHENTIFICATION
BEFORE INSERT ON AUTHENTIFICATION
BEGIN
--  Application_logic Pre-Before-Insert-statement <<Start>>
--  Application_logic Pre-Before-Insert-statement << End >>

    cg$AUTHENTIFICATION.cg$table.DELETE;
    cg$AUTHENTIFICATION.cg$tableind.DELETE;
    cg$AUTHENTIFICATION.idx := 1;

--  Application_logic Post-Before-Insert-statement <<Start>>
--  Application_logic Post-Before-Insert-statement << End >>
END;
/
  

PROMPT Creating Before Insert Row Trigger on 'AUTHENTIFICATION'
CREATE OR REPLACE TRIGGER cg$BIR_AUTHENTIFICATION 
BEFORE INSERT ON AUTHENTIFICATION FOR EACH ROW
DECLARE
    cg$rec cg$AUTHENTIFICATION.cg$row_type;
    cg$ind cg$AUTHENTIFICATION.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Insert-row <<Start>>
--  Application_logic Pre-Before-Insert-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_CONNECTION := :new.ID_CONNECTION;
    IF (:new.ID_CONNECTION IS NULL) THEN
        cg$ind.ID_CONNECTION := FALSE;
    ELSE
        cg$ind.ID_CONNECTION := TRUE;
    END IF;
    cg$rec.ROLE := :new.ROLE;
    cg$ind.ROLE := TRUE;
    cg$rec.LOGIN_CONNECTION := :new.LOGIN_CONNECTION;
    cg$ind.LOGIN_CONNECTION := TRUE;
    cg$rec.PSEUDO_CONNECTION := :new.PSEUDO_CONNECTION;
    cg$ind.PSEUDO_CONNECTION := TRUE;
    cg$rec.MOT_PASSE := :new.MOT_PASSE;
    cg$ind.MOT_PASSE := TRUE;
    cg$rec.NO_ENSEIGNANT := :new.NO_ENSEIGNANT;
    cg$ind.NO_ENSEIGNANT := TRUE;
    cg$rec.NO_ETUDIANT := :new.NO_ETUDIANT;
    cg$ind.NO_ETUDIANT := TRUE;

    if not (cg$AUTHENTIFICATION.called_from_package) then
        cg$AUTHENTIFICATION.validate_arc(cg$rec);
        cg$AUTHENTIFICATION.validate_domain(cg$rec);

        cg$AUTHENTIFICATION.ins(cg$rec, cg$ind, FALSE);
        cg$AUTHENTIFICATION.called_from_package := FALSE;
    end if;

    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).ID_CONNECTION := cg$rec.ID_CONNECTION;
    cg$AUTHENTIFICATION.cg$tableind(cg$AUTHENTIFICATION.idx).ID_CONNECTION := cg$ind.ID_CONNECTION;

    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).ROLE := cg$rec.ROLE;
    cg$AUTHENTIFICATION.cg$tableind(cg$AUTHENTIFICATION.idx).ROLE := cg$ind.ROLE;

    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).LOGIN_CONNECTION := cg$rec.LOGIN_CONNECTION;
    cg$AUTHENTIFICATION.cg$tableind(cg$AUTHENTIFICATION.idx).LOGIN_CONNECTION := cg$ind.LOGIN_CONNECTION;

    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).PSEUDO_CONNECTION := cg$rec.PSEUDO_CONNECTION;
    cg$AUTHENTIFICATION.cg$tableind(cg$AUTHENTIFICATION.idx).PSEUDO_CONNECTION := cg$ind.PSEUDO_CONNECTION;

    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).MOT_PASSE := cg$rec.MOT_PASSE;
    cg$AUTHENTIFICATION.cg$tableind(cg$AUTHENTIFICATION.idx).MOT_PASSE := cg$ind.MOT_PASSE;

    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).NO_ENSEIGNANT := cg$rec.NO_ENSEIGNANT;
    cg$AUTHENTIFICATION.cg$tableind(cg$AUTHENTIFICATION.idx).NO_ENSEIGNANT := cg$ind.NO_ENSEIGNANT;

    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).NO_ETUDIANT := cg$rec.NO_ETUDIANT;
    cg$AUTHENTIFICATION.cg$tableind(cg$AUTHENTIFICATION.idx).NO_ETUDIANT := cg$ind.NO_ETUDIANT;


    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).action_performed := 'INS';
    cg$AUTHENTIFICATION.idx := cg$AUTHENTIFICATION.idx + 1;

    :new.ID_CONNECTION := cg$rec.ID_CONNECTION;
    :new.ROLE := cg$rec.ROLE;
    :new.LOGIN_CONNECTION := cg$rec.LOGIN_CONNECTION;
    :new.PSEUDO_CONNECTION := cg$rec.PSEUDO_CONNECTION;
    :new.MOT_PASSE := cg$rec.MOT_PASSE;
    :new.NO_ENSEIGNANT := cg$rec.NO_ENSEIGNANT;
    :new.NO_ETUDIANT := cg$rec.NO_ETUDIANT;

--  Application_logic Post-Before-Insert-row <<Start>>
--  Application_logic Post-Before-Insert-row << End >>
END;
/


-- No application logic defined for Trigger cg$AIR_AUTHENTIFICATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AIR_AUTHENTIFICATION
AFTER INSERT ON AUTHENTIFICATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AIR_AUTHENTIFICATION
/

PROMPT Creating After Insert Statement Trigger on 'AUTHENTIFICATION'
CREATE OR REPLACE TRIGGER cg$AIS_AUTHENTIFICATION 
AFTER INSERT ON AUTHENTIFICATION
DECLARE
    idx      BINARY_INTEGER := cg$AUTHENTIFICATION.cg$table.FIRST;
    cg$rec   cg$AUTHENTIFICATION.cg$row_type;
    cg$old_rec   cg$AUTHENTIFICATION.cg$row_type;
    fk_check INTEGER;
BEGIN
--  Application_logic Pre-After-Insert-statement <<Start>>
--  Application_logic Pre-After-Insert-statement << End >>


    IF NOT (cg$AUTHENTIFICATION.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

	    IF (cg$AUTHENTIFICATION.cg$table(idx).action_performed = 'INS') THEN
                cg$rec.ID_CONNECTION := cg$AUTHENTIFICATION.cg$table(idx).ID_CONNECTION;
                cg$rec.ROLE := cg$AUTHENTIFICATION.cg$table(idx).ROLE;
                cg$rec.LOGIN_CONNECTION := cg$AUTHENTIFICATION.cg$table(idx).LOGIN_CONNECTION;
                cg$rec.PSEUDO_CONNECTION := cg$AUTHENTIFICATION.cg$table(idx).PSEUDO_CONNECTION;
                cg$rec.MOT_PASSE := cg$AUTHENTIFICATION.cg$table(idx).MOT_PASSE;
                cg$rec.NO_ENSEIGNANT := cg$AUTHENTIFICATION.cg$table(idx).NO_ENSEIGNANT;
                cg$rec.NO_ETUDIANT := cg$AUTHENTIFICATION.cg$table(idx).NO_ETUDIANT;

                cg$AUTHENTIFICATION.validate_foreign_keys_ins(cg$rec);

                cg$AUTHENTIFICATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$AUTHENTIFICATION.cg$tableind(idx),
                                                    'INS'
                                                  );
            END IF;
            idx := cg$AUTHENTIFICATION.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Insert-statement <<Start>>
--  Application_logic Post-After-Insert-statement << End >>

END;
/


    

PROMPT Creating Before Update Statement Trigger on 'AUTHENTIFICATION'
CREATE OR REPLACE TRIGGER cg$BUS_AUTHENTIFICATION
BEFORE UPDATE ON AUTHENTIFICATION
BEGIN
--  Application_logic Pre-Before-Update-statement <<Start>>
--  Application_logic Pre-Before-Update-statement << End >>

    cg$AUTHENTIFICATION.cg$table.DELETE;
    cg$AUTHENTIFICATION.cg$tableind.DELETE;
    cg$AUTHENTIFICATION.idx := 1;

--  Application_logic Post-Before-Update-statement <<Start>>
--  Application_logic Post-Before-Update-statement << End >>

END;
/
  

PROMPT Creating Before Update Row Trigger on 'AUTHENTIFICATION'
CREATE OR REPLACE TRIGGER cg$BUR_AUTHENTIFICATION 
BEFORE UPDATE ON AUTHENTIFICATION FOR EACH ROW
DECLARE
    cg$rec     cg$AUTHENTIFICATION.cg$row_type;
    cg$ind     cg$AUTHENTIFICATION.cg$ind_type;
    cg$old_rec cg$AUTHENTIFICATION.cg$row_type;  
BEGIN
--  Application_logic Pre-Before-Update-row <<Start>>
--  Application_logic Pre-Before-Update-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_CONNECTION := :new.ID_CONNECTION;
    cg$ind.ID_CONNECTION :=    (:new.ID_CONNECTION IS NULL AND :old.ID_CONNECTION IS NOT NULL )
                        OR (:new.ID_CONNECTION IS NOT NULL AND :old.ID_CONNECTION IS NULL)
                        OR NOT(:new.ID_CONNECTION = :old.ID_CONNECTION) ;
    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).ID_CONNECTION := :old.ID_CONNECTION;
    cg$rec.ROLE := :new.ROLE;
    cg$ind.ROLE :=    (:new.ROLE IS NULL AND :old.ROLE IS NOT NULL )
                        OR (:new.ROLE IS NOT NULL AND :old.ROLE IS NULL)
                        OR NOT(:new.ROLE = :old.ROLE) ;
    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).ROLE := :old.ROLE;
    cg$rec.LOGIN_CONNECTION := :new.LOGIN_CONNECTION;
    cg$ind.LOGIN_CONNECTION :=    (:new.LOGIN_CONNECTION IS NULL AND :old.LOGIN_CONNECTION IS NOT NULL )
                        OR (:new.LOGIN_CONNECTION IS NOT NULL AND :old.LOGIN_CONNECTION IS NULL)
                        OR NOT(:new.LOGIN_CONNECTION = :old.LOGIN_CONNECTION) ;
    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).LOGIN_CONNECTION := :old.LOGIN_CONNECTION;
    cg$rec.PSEUDO_CONNECTION := :new.PSEUDO_CONNECTION;
    cg$ind.PSEUDO_CONNECTION :=    (:new.PSEUDO_CONNECTION IS NULL AND :old.PSEUDO_CONNECTION IS NOT NULL )
                        OR (:new.PSEUDO_CONNECTION IS NOT NULL AND :old.PSEUDO_CONNECTION IS NULL)
                        OR NOT(:new.PSEUDO_CONNECTION = :old.PSEUDO_CONNECTION) ;
    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).PSEUDO_CONNECTION := :old.PSEUDO_CONNECTION;
    cg$rec.MOT_PASSE := :new.MOT_PASSE;
    cg$ind.MOT_PASSE :=    (:new.MOT_PASSE IS NULL AND :old.MOT_PASSE IS NOT NULL )
                        OR (:new.MOT_PASSE IS NOT NULL AND :old.MOT_PASSE IS NULL)
                        OR NOT(:new.MOT_PASSE = :old.MOT_PASSE) ;
    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).MOT_PASSE := :old.MOT_PASSE;
    cg$rec.NO_ENSEIGNANT := :new.NO_ENSEIGNANT;
    cg$ind.NO_ENSEIGNANT :=    (:new.NO_ENSEIGNANT IS NULL AND :old.NO_ENSEIGNANT IS NOT NULL )
                        OR (:new.NO_ENSEIGNANT IS NOT NULL AND :old.NO_ENSEIGNANT IS NULL)
                        OR NOT(:new.NO_ENSEIGNANT = :old.NO_ENSEIGNANT) ;
    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).NO_ENSEIGNANT := :old.NO_ENSEIGNANT;
    cg$rec.NO_ETUDIANT := :new.NO_ETUDIANT;
    cg$ind.NO_ETUDIANT :=    (:new.NO_ETUDIANT IS NULL AND :old.NO_ETUDIANT IS NOT NULL )
                        OR (:new.NO_ETUDIANT IS NOT NULL AND :old.NO_ETUDIANT IS NULL)
                        OR NOT(:new.NO_ETUDIANT = :old.NO_ETUDIANT) ;
    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).NO_ETUDIANT := :old.NO_ETUDIANT;


    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).action_performed := 'UPD';     -- nkovoor
    cg$AUTHENTIFICATION.idx := cg$AUTHENTIFICATION.idx + 1;

    if not (cg$AUTHENTIFICATION.called_from_package) then
        cg$AUTHENTIFICATION.validate_arc(cg$rec);
        cg$AUTHENTIFICATION.validate_domain(cg$rec, cg$ind);
        cg$AUTHENTIFICATION.validate_domain_cascade_update(cg$old_rec);   

        cg$AUTHENTIFICATION.upd(cg$rec, cg$ind, FALSE);
        cg$AUTHENTIFICATION.called_from_package := FALSE;
    end if;

    :new.ROLE := cg$rec.ROLE;
    :new.LOGIN_CONNECTION := cg$rec.LOGIN_CONNECTION;
    :new.PSEUDO_CONNECTION := cg$rec.PSEUDO_CONNECTION;
    :new.MOT_PASSE := cg$rec.MOT_PASSE;
    :new.NO_ENSEIGNANT := cg$rec.NO_ENSEIGNANT;
    :new.NO_ETUDIANT := cg$rec.NO_ETUDIANT;
--  Application_logic Post-Before-Update-row <<Start>>
--  Application_logic Post-Before-Update-row << End >>
END;
/

-- No application logic defined for Trigger cg$AUR_AUTHENTIFICATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AUR_AUTHENTIFICATION
AFTER UPDATE ON AUTHENTIFICATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AUR_AUTHENTIFICATION
/






PROMPT Creating After Update Statement Trigger on 'AUTHENTIFICATION'
CREATE OR REPLACE TRIGGER cg$AUS_AUTHENTIFICATION 
AFTER UPDATE ON AUTHENTIFICATION
DECLARE
    idx        BINARY_INTEGER := cg$AUTHENTIFICATION.cg$table.FIRST;
    cg$old_rec cg$AUTHENTIFICATION.cg$row_type;
    cg$rec     cg$AUTHENTIFICATION.cg$row_type;
    cg$ind     cg$AUTHENTIFICATION.cg$ind_type;
BEGIN
--  Application_logic Pre-After-Update-statement <<Start>>
--  Application_logic Pre-After-Update-statement << End >>

    WHILE idx IS NOT NULL LOOP

        IF (cg$AUTHENTIFICATION.cg$table(idx).action_performed = 'UPD') THEN
            cg$old_rec.ID_CONNECTION := cg$AUTHENTIFICATION.cg$table(idx).ID_CONNECTION;
            cg$old_rec.ROLE := cg$AUTHENTIFICATION.cg$table(idx).ROLE;
            cg$old_rec.LOGIN_CONNECTION := cg$AUTHENTIFICATION.cg$table(idx).LOGIN_CONNECTION;
            cg$old_rec.PSEUDO_CONNECTION := cg$AUTHENTIFICATION.cg$table(idx).PSEUDO_CONNECTION;
            cg$old_rec.MOT_PASSE := cg$AUTHENTIFICATION.cg$table(idx).MOT_PASSE;
            cg$old_rec.NO_ENSEIGNANT := cg$AUTHENTIFICATION.cg$table(idx).NO_ENSEIGNANT;
            cg$old_rec.NO_ETUDIANT := cg$AUTHENTIFICATION.cg$table(idx).NO_ETUDIANT;

            IF NOT (cg$AUTHENTIFICATION.called_from_package) THEN
                idx := cg$AUTHENTIFICATION.cg$table.NEXT(idx);

                IF (idx IS NOT NULL) THEN
                cg$rec.ID_CONNECTION := cg$AUTHENTIFICATION.cg$table(idx).ID_CONNECTION;
                cg$ind.ID_CONNECTION := updating('ID_CONNECTION');
                cg$rec.ROLE := cg$AUTHENTIFICATION.cg$table(idx).ROLE;
                cg$ind.ROLE := updating('ROLE');
                cg$rec.LOGIN_CONNECTION := cg$AUTHENTIFICATION.cg$table(idx).LOGIN_CONNECTION;
                cg$ind.LOGIN_CONNECTION := updating('LOGIN_CONNECTION');
                cg$rec.PSEUDO_CONNECTION := cg$AUTHENTIFICATION.cg$table(idx).PSEUDO_CONNECTION;
                cg$ind.PSEUDO_CONNECTION := updating('PSEUDO_CONNECTION');
                cg$rec.MOT_PASSE := cg$AUTHENTIFICATION.cg$table(idx).MOT_PASSE;
                cg$ind.MOT_PASSE := updating('MOT_PASSE');
                cg$rec.NO_ENSEIGNANT := cg$AUTHENTIFICATION.cg$table(idx).NO_ENSEIGNANT;
                cg$ind.NO_ENSEIGNANT := updating('NO_ENSEIGNANT');
                cg$rec.NO_ETUDIANT := cg$AUTHENTIFICATION.cg$table(idx).NO_ETUDIANT;
                cg$ind.NO_ETUDIANT := updating('NO_ETUDIANT');

                cg$AUTHENTIFICATION.validate_foreign_keys_upd(cg$rec, cg$old_rec, cg$ind);

                cg$AUTHENTIFICATION.upd_denorm2( cg$rec,
                                               cg$AUTHENTIFICATION.cg$tableind(idx)
                                             );
                cg$AUTHENTIFICATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$AUTHENTIFICATION.cg$tableind(idx)
				        				                  );
                cg$AUTHENTIFICATION.cascade_update(cg$rec, cg$old_rec);
                cg$AUTHENTIFICATION.domain_cascade_update(cg$rec, cg$ind, cg$old_rec);             
        
                cg$AUTHENTIFICATION.called_from_package := FALSE;
                END IF;
            END IF; 
        END IF;
        idx := cg$AUTHENTIFICATION.cg$table.NEXT(idx);
    END LOOP;

    cg$AUTHENTIFICATION.cg$table.DELETE;

--  Application_logic Post-After-Update-statement <<Start>>
--  Application_logic Post-After-Update-statement << End >>

END;
/

PROMPT Creating Before Delete Statement Trigger on 'AUTHENTIFICATION'
CREATE OR REPLACE TRIGGER cg$BDS_AUTHENTIFICATION
BEFORE DELETE ON AUTHENTIFICATION
BEGIN
--  Application_logic Pre-Before-Delete-statement <<Start>>
--  Application_logic Pre-Before-Delete-statement << End >>

    cg$AUTHENTIFICATION.cg$table.DELETE;
    cg$AUTHENTIFICATION.cg$tableind.DELETE;
    cg$AUTHENTIFICATION.idx := 1;

--  Application_logic Post-Before-Delete-statement <<Start>>
--  Application_logic Post-Before-Delete-statement << End >>
END;
/
  

PROMPT Creating Before Delete Row Trigger on 'AUTHENTIFICATION'
CREATE OR REPLACE TRIGGER cg$BDR_AUTHENTIFICATION 
BEFORE DELETE ON AUTHENTIFICATION FOR EACH ROW
DECLARE
    cg$pk cg$AUTHENTIFICATION.cg$pk_type;
    cg$rec cg$AUTHENTIFICATION.cg$row_type;
    cg$ind cg$AUTHENTIFICATION.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Delete-row <<Start>>
--  Application_logic Pre-Before-Delete-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$pk.ID_CONNECTION  := :old.ID_CONNECTION;
    cg$rec.ID_CONNECTION := :old.ID_CONNECTION;
    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).ID_CONNECTION := :old.ID_CONNECTION;
    cg$rec.NO_ETUDIANT := :old.NO_ETUDIANT;
    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).NO_ETUDIANT := :old.NO_ETUDIANT;
    cg$rec.NO_ENSEIGNANT := :old.NO_ENSEIGNANT;
    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).NO_ENSEIGNANT := :old.NO_ENSEIGNANT;


    cg$AUTHENTIFICATION.cg$table(cg$AUTHENTIFICATION.idx).action_performed := 'DEL';
    cg$AUTHENTIFICATION.idx := cg$AUTHENTIFICATION.idx + 1;

    if not (cg$AUTHENTIFICATION.called_from_package) then
        cg$AUTHENTIFICATION.validate_domain_cascade_delete(cg$rec);   
        cg$AUTHENTIFICATION.del(cg$pk, FALSE);
        cg$AUTHENTIFICATION.called_from_package := FALSE;
    end if;

--  Application_logic Post-Before-Delete-row <<Start>>
--  Application_logic Post-Before-Delete-row << End >>
END;
/

-- No application logic defined for Trigger cg$ADR_AUTHENTIFICATION, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$ADR_AUTHENTIFICATION
AFTER DELETE ON AUTHENTIFICATION FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$ADR_AUTHENTIFICATION
/

PROMPT Creating After Delete Statement Trigger on 'AUTHENTIFICATION'
CREATE OR REPLACE TRIGGER cg$ADS_AUTHENTIFICATION 
AFTER DELETE ON AUTHENTIFICATION
DECLARE
    idx        BINARY_INTEGER := cg$AUTHENTIFICATION.cg$table.FIRST;
    cg$rec   cg$AUTHENTIFICATION.cg$row_type;
    cg$old_rec   cg$AUTHENTIFICATION.cg$row_type;
BEGIN
--  Application_logic Pre-After-Delete-statement <<Start>>
--  Application_logic Pre-After-Delete-statement << End >>

    IF NOT (cg$AUTHENTIFICATION.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

            IF (cg$AUTHENTIFICATION.cg$table(idx).action_performed = 'DEL') THEN
                cg$rec.ID_CONNECTION := cg$AUTHENTIFICATION.cg$table(idx).ID_CONNECTION;
                cg$AUTHENTIFICATION.cg$tableind(idx).ID_CONNECTION := TRUE;
                cg$rec.ROLE := cg$AUTHENTIFICATION.cg$table(idx).ROLE;
                cg$AUTHENTIFICATION.cg$tableind(idx).ROLE := TRUE;
                cg$rec.LOGIN_CONNECTION := cg$AUTHENTIFICATION.cg$table(idx).LOGIN_CONNECTION;
                cg$AUTHENTIFICATION.cg$tableind(idx).LOGIN_CONNECTION := TRUE;
                cg$rec.PSEUDO_CONNECTION := cg$AUTHENTIFICATION.cg$table(idx).PSEUDO_CONNECTION;
                cg$AUTHENTIFICATION.cg$tableind(idx).PSEUDO_CONNECTION := TRUE;
                cg$rec.MOT_PASSE := cg$AUTHENTIFICATION.cg$table(idx).MOT_PASSE;
                cg$AUTHENTIFICATION.cg$tableind(idx).MOT_PASSE := TRUE;
                cg$rec.NO_ENSEIGNANT := cg$AUTHENTIFICATION.cg$table(idx).NO_ENSEIGNANT;
                cg$AUTHENTIFICATION.cg$tableind(idx).NO_ENSEIGNANT := TRUE;
                cg$rec.NO_ETUDIANT := cg$AUTHENTIFICATION.cg$table(idx).NO_ETUDIANT;
                cg$AUTHENTIFICATION.cg$tableind(idx).NO_ETUDIANT := TRUE;

                cg$AUTHENTIFICATION.validate_foreign_keys_del(cg$rec);
                cg$AUTHENTIFICATION.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$AUTHENTIFICATION.cg$tableind(idx),
                                                    'DEL'
                                                  );
	
                cg$AUTHENTIFICATION.cascade_delete(cg$rec);
                cg$AUTHENTIFICATION.domain_cascade_delete(cg$rec);             
            END IF;
            idx := cg$AUTHENTIFICATION.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Delete-statement <<Start>>
--  Application_logic Post-After-Delete-statement << End >>

END;
/



