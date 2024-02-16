
PROMPT Creating Trigger Logic for Table 'QUALIFICATIF'
PROMPT Creating Before Insert Statement Trigger on 'QUALIFICATIF'
CREATE OR REPLACE TRIGGER cg$BIS_QUALIFICATIF
BEFORE INSERT ON QUALIFICATIF
BEGIN
--  Application_logic Pre-Before-Insert-statement <<Start>>
--  Application_logic Pre-Before-Insert-statement << End >>

    cg$QUALIFICATIF.cg$table.DELETE;
    cg$QUALIFICATIF.cg$tableind.DELETE;
    cg$QUALIFICATIF.idx := 1;

--  Application_logic Post-Before-Insert-statement <<Start>>
--  Application_logic Post-Before-Insert-statement << End >>
END;
/
  

PROMPT Creating Before Insert Row Trigger on 'QUALIFICATIF'
CREATE OR REPLACE TRIGGER cg$BIR_QUALIFICATIF 
BEFORE INSERT ON QUALIFICATIF FOR EACH ROW
DECLARE
    cg$rec cg$QUALIFICATIF.cg$row_type;
    cg$ind cg$QUALIFICATIF.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Insert-row <<Start>>
--  Application_logic Pre-Before-Insert-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_QUALIFICATIF := :new.ID_QUALIFICATIF;
    IF (:new.ID_QUALIFICATIF IS NULL) THEN
        cg$ind.ID_QUALIFICATIF := FALSE;
    ELSE
        cg$ind.ID_QUALIFICATIF := TRUE;
    END IF;
    cg$rec.MAXIMAL := :new.MAXIMAL;
    cg$ind.MAXIMAL := TRUE;
    cg$rec.MINIMAL := :new.MINIMAL;
    cg$ind.MINIMAL := TRUE;

    if not (cg$QUALIFICATIF.called_from_package) then
        cg$QUALIFICATIF.validate_arc(cg$rec);
        cg$QUALIFICATIF.validate_domain(cg$rec);

        cg$QUALIFICATIF.ins(cg$rec, cg$ind, FALSE);
        cg$QUALIFICATIF.called_from_package := FALSE;
    end if;

    cg$QUALIFICATIF.cg$table(cg$QUALIFICATIF.idx).ID_QUALIFICATIF := cg$rec.ID_QUALIFICATIF;
    cg$QUALIFICATIF.cg$tableind(cg$QUALIFICATIF.idx).ID_QUALIFICATIF := cg$ind.ID_QUALIFICATIF;

    cg$QUALIFICATIF.cg$table(cg$QUALIFICATIF.idx).MAXIMAL := cg$rec.MAXIMAL;
    cg$QUALIFICATIF.cg$tableind(cg$QUALIFICATIF.idx).MAXIMAL := cg$ind.MAXIMAL;

    cg$QUALIFICATIF.cg$table(cg$QUALIFICATIF.idx).MINIMAL := cg$rec.MINIMAL;
    cg$QUALIFICATIF.cg$tableind(cg$QUALIFICATIF.idx).MINIMAL := cg$ind.MINIMAL;


    cg$QUALIFICATIF.cg$table(cg$QUALIFICATIF.idx).action_performed := 'INS';
    cg$QUALIFICATIF.idx := cg$QUALIFICATIF.idx + 1;

    :new.ID_QUALIFICATIF := cg$rec.ID_QUALIFICATIF;
    :new.MAXIMAL := cg$rec.MAXIMAL;
    :new.MINIMAL := cg$rec.MINIMAL;

--  Application_logic Post-Before-Insert-row <<Start>>
--  Application_logic Post-Before-Insert-row << End >>
END;
/


-- No application logic defined for Trigger cg$AIR_QUALIFICATIF, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AIR_QUALIFICATIF
AFTER INSERT ON QUALIFICATIF FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AIR_QUALIFICATIF
/

PROMPT Creating After Insert Statement Trigger on 'QUALIFICATIF'
CREATE OR REPLACE TRIGGER cg$AIS_QUALIFICATIF 
AFTER INSERT ON QUALIFICATIF
DECLARE
    idx      BINARY_INTEGER := cg$QUALIFICATIF.cg$table.FIRST;
    cg$rec   cg$QUALIFICATIF.cg$row_type;
    cg$old_rec   cg$QUALIFICATIF.cg$row_type;
    fk_check INTEGER;
BEGIN
--  Application_logic Pre-After-Insert-statement <<Start>>
--  Application_logic Pre-After-Insert-statement << End >>


    IF NOT (cg$QUALIFICATIF.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

	    IF (cg$QUALIFICATIF.cg$table(idx).action_performed = 'INS') THEN
                cg$rec.ID_QUALIFICATIF := cg$QUALIFICATIF.cg$table(idx).ID_QUALIFICATIF;
                cg$rec.MAXIMAL := cg$QUALIFICATIF.cg$table(idx).MAXIMAL;
                cg$rec.MINIMAL := cg$QUALIFICATIF.cg$table(idx).MINIMAL;

                cg$QUALIFICATIF.validate_foreign_keys_ins(cg$rec);

                cg$QUALIFICATIF.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$QUALIFICATIF.cg$tableind(idx),
                                                    'INS'
                                                  );
            END IF;
            idx := cg$QUALIFICATIF.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Insert-statement <<Start>>
--  Application_logic Post-After-Insert-statement << End >>

END;
/


    

PROMPT Creating Before Update Statement Trigger on 'QUALIFICATIF'
CREATE OR REPLACE TRIGGER cg$BUS_QUALIFICATIF
BEFORE UPDATE ON QUALIFICATIF
BEGIN
--  Application_logic Pre-Before-Update-statement <<Start>>
--  Application_logic Pre-Before-Update-statement << End >>

    cg$QUALIFICATIF.cg$table.DELETE;
    cg$QUALIFICATIF.cg$tableind.DELETE;
    cg$QUALIFICATIF.idx := 1;

--  Application_logic Post-Before-Update-statement <<Start>>
--  Application_logic Post-Before-Update-statement << End >>

END;
/
  

PROMPT Creating Before Update Row Trigger on 'QUALIFICATIF'
CREATE OR REPLACE TRIGGER cg$BUR_QUALIFICATIF 
BEFORE UPDATE ON QUALIFICATIF FOR EACH ROW
DECLARE
    cg$rec     cg$QUALIFICATIF.cg$row_type;
    cg$ind     cg$QUALIFICATIF.cg$ind_type;
    cg$old_rec cg$QUALIFICATIF.cg$row_type;  
BEGIN
--  Application_logic Pre-Before-Update-row <<Start>>
--  Application_logic Pre-Before-Update-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$rec.ID_QUALIFICATIF := :new.ID_QUALIFICATIF;
    cg$ind.ID_QUALIFICATIF :=    (:new.ID_QUALIFICATIF IS NULL AND :old.ID_QUALIFICATIF IS NOT NULL )
                        OR (:new.ID_QUALIFICATIF IS NOT NULL AND :old.ID_QUALIFICATIF IS NULL)
                        OR NOT(:new.ID_QUALIFICATIF = :old.ID_QUALIFICATIF) ;
    cg$QUALIFICATIF.cg$table(cg$QUALIFICATIF.idx).ID_QUALIFICATIF := :old.ID_QUALIFICATIF;
    cg$rec.MAXIMAL := :new.MAXIMAL;
    cg$ind.MAXIMAL :=    (:new.MAXIMAL IS NULL AND :old.MAXIMAL IS NOT NULL )
                        OR (:new.MAXIMAL IS NOT NULL AND :old.MAXIMAL IS NULL)
                        OR NOT(:new.MAXIMAL = :old.MAXIMAL) ;
    cg$QUALIFICATIF.cg$table(cg$QUALIFICATIF.idx).MAXIMAL := :old.MAXIMAL;
    cg$rec.MINIMAL := :new.MINIMAL;
    cg$ind.MINIMAL :=    (:new.MINIMAL IS NULL AND :old.MINIMAL IS NOT NULL )
                        OR (:new.MINIMAL IS NOT NULL AND :old.MINIMAL IS NULL)
                        OR NOT(:new.MINIMAL = :old.MINIMAL) ;
    cg$QUALIFICATIF.cg$table(cg$QUALIFICATIF.idx).MINIMAL := :old.MINIMAL;


    cg$QUALIFICATIF.cg$table(cg$QUALIFICATIF.idx).action_performed := 'UPD';     -- nkovoor
    cg$QUALIFICATIF.idx := cg$QUALIFICATIF.idx + 1;

    if not (cg$QUALIFICATIF.called_from_package) then
        cg$QUALIFICATIF.validate_arc(cg$rec);
        cg$QUALIFICATIF.validate_domain(cg$rec, cg$ind);
        cg$QUALIFICATIF.validate_domain_cascade_update(cg$old_rec);   

        cg$QUALIFICATIF.upd(cg$rec, cg$ind, FALSE);
        cg$QUALIFICATIF.called_from_package := FALSE;
    end if;

    :new.MAXIMAL := cg$rec.MAXIMAL;
    :new.MINIMAL := cg$rec.MINIMAL;
--  Application_logic Post-Before-Update-row <<Start>>
--  Application_logic Post-Before-Update-row << End >>
END;
/

-- No application logic defined for Trigger cg$AUR_QUALIFICATIF, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$AUR_QUALIFICATIF
AFTER UPDATE ON QUALIFICATIF FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$AUR_QUALIFICATIF
/






PROMPT Creating After Update Statement Trigger on 'QUALIFICATIF'
CREATE OR REPLACE TRIGGER cg$AUS_QUALIFICATIF 
AFTER UPDATE ON QUALIFICATIF
DECLARE
    idx        BINARY_INTEGER := cg$QUALIFICATIF.cg$table.FIRST;
    cg$old_rec cg$QUALIFICATIF.cg$row_type;
    cg$rec     cg$QUALIFICATIF.cg$row_type;
    cg$ind     cg$QUALIFICATIF.cg$ind_type;
BEGIN
--  Application_logic Pre-After-Update-statement <<Start>>
--  Application_logic Pre-After-Update-statement << End >>

    WHILE idx IS NOT NULL LOOP

        IF (cg$QUALIFICATIF.cg$table(idx).action_performed = 'UPD') THEN
            cg$old_rec.ID_QUALIFICATIF := cg$QUALIFICATIF.cg$table(idx).ID_QUALIFICATIF;
            cg$old_rec.MAXIMAL := cg$QUALIFICATIF.cg$table(idx).MAXIMAL;
            cg$old_rec.MINIMAL := cg$QUALIFICATIF.cg$table(idx).MINIMAL;

            IF NOT (cg$QUALIFICATIF.called_from_package) THEN
                idx := cg$QUALIFICATIF.cg$table.NEXT(idx);

                IF (idx IS NOT NULL) THEN
                cg$rec.ID_QUALIFICATIF := cg$QUALIFICATIF.cg$table(idx).ID_QUALIFICATIF;
                cg$ind.ID_QUALIFICATIF := updating('ID_QUALIFICATIF');
                cg$rec.MAXIMAL := cg$QUALIFICATIF.cg$table(idx).MAXIMAL;
                cg$ind.MAXIMAL := updating('MAXIMAL');
                cg$rec.MINIMAL := cg$QUALIFICATIF.cg$table(idx).MINIMAL;
                cg$ind.MINIMAL := updating('MINIMAL');

                cg$QUALIFICATIF.validate_foreign_keys_upd(cg$rec, cg$old_rec, cg$ind);

                cg$QUALIFICATIF.upd_denorm2( cg$rec,
                                               cg$QUALIFICATIF.cg$tableind(idx)
                                             );
                cg$QUALIFICATIF.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$QUALIFICATIF.cg$tableind(idx)
				        				                  );
                cg$QUALIFICATIF.cascade_update(cg$rec, cg$old_rec);
                cg$QUALIFICATIF.domain_cascade_update(cg$rec, cg$ind, cg$old_rec);             
        
                cg$QUALIFICATIF.called_from_package := FALSE;
                END IF;
            END IF; 
        END IF;
        idx := cg$QUALIFICATIF.cg$table.NEXT(idx);
    END LOOP;

    cg$QUALIFICATIF.cg$table.DELETE;

--  Application_logic Post-After-Update-statement <<Start>>
--  Application_logic Post-After-Update-statement << End >>

END;
/

PROMPT Creating Before Delete Statement Trigger on 'QUALIFICATIF'
CREATE OR REPLACE TRIGGER cg$BDS_QUALIFICATIF
BEFORE DELETE ON QUALIFICATIF
BEGIN
--  Application_logic Pre-Before-Delete-statement <<Start>>
--  Application_logic Pre-Before-Delete-statement << End >>

    cg$QUALIFICATIF.cg$table.DELETE;
    cg$QUALIFICATIF.cg$tableind.DELETE;
    cg$QUALIFICATIF.idx := 1;

--  Application_logic Post-Before-Delete-statement <<Start>>
--  Application_logic Post-Before-Delete-statement << End >>
END;
/
  

PROMPT Creating Before Delete Row Trigger on 'QUALIFICATIF'
CREATE OR REPLACE TRIGGER cg$BDR_QUALIFICATIF 
BEFORE DELETE ON QUALIFICATIF FOR EACH ROW
DECLARE
    cg$pk cg$QUALIFICATIF.cg$pk_type;
    cg$rec cg$QUALIFICATIF.cg$row_type;
    cg$ind cg$QUALIFICATIF.cg$ind_type;
BEGIN
--  Application_logic Pre-Before-Delete-row <<Start>>
--  Application_logic Pre-Before-Delete-row << End >>

--  Load cg$rec/cg$ind values from new

    cg$pk.ID_QUALIFICATIF  := :old.ID_QUALIFICATIF;
    cg$rec.ID_QUALIFICATIF := :old.ID_QUALIFICATIF;
    cg$QUALIFICATIF.cg$table(cg$QUALIFICATIF.idx).ID_QUALIFICATIF := :old.ID_QUALIFICATIF;


    cg$QUALIFICATIF.cg$table(cg$QUALIFICATIF.idx).action_performed := 'DEL';
    cg$QUALIFICATIF.idx := cg$QUALIFICATIF.idx + 1;

    if not (cg$QUALIFICATIF.called_from_package) then
        cg$QUALIFICATIF.validate_domain_cascade_delete(cg$rec);   
        cg$QUALIFICATIF.del(cg$pk, FALSE);
        cg$QUALIFICATIF.called_from_package := FALSE;
    end if;

--  Application_logic Post-Before-Delete-row <<Start>>
--  Application_logic Post-Before-Delete-row << End >>
END;
/

-- No application logic defined for Trigger cg$ADR_QUALIFICATIF, so drop it.
-- To avoid an error if there isn't one, create or replace it, and then drop it
CREATE OR REPLACE TRIGGER cg$ADR_QUALIFICATIF
AFTER DELETE ON QUALIFICATIF FOR EACH ROW
BEGIN
 null;
END;
/
drop trigger cg$ADR_QUALIFICATIF
/

PROMPT Creating After Delete Statement Trigger on 'QUALIFICATIF'
CREATE OR REPLACE TRIGGER cg$ADS_QUALIFICATIF 
AFTER DELETE ON QUALIFICATIF
DECLARE
    idx        BINARY_INTEGER := cg$QUALIFICATIF.cg$table.FIRST;
    cg$rec   cg$QUALIFICATIF.cg$row_type;
    cg$old_rec   cg$QUALIFICATIF.cg$row_type;
BEGIN
--  Application_logic Pre-After-Delete-statement <<Start>>
--  Application_logic Pre-After-Delete-statement << End >>

    IF NOT (cg$QUALIFICATIF.called_from_package) THEN
        WHILE idx IS NOT NULL LOOP

            IF (cg$QUALIFICATIF.cg$table(idx).action_performed = 'DEL') THEN
                cg$rec.ID_QUALIFICATIF := cg$QUALIFICATIF.cg$table(idx).ID_QUALIFICATIF;
                cg$QUALIFICATIF.cg$tableind(idx).ID_QUALIFICATIF := TRUE;
                cg$rec.MAXIMAL := cg$QUALIFICATIF.cg$table(idx).MAXIMAL;
                cg$QUALIFICATIF.cg$tableind(idx).MAXIMAL := TRUE;
                cg$rec.MINIMAL := cg$QUALIFICATIF.cg$table(idx).MINIMAL;
                cg$QUALIFICATIF.cg$tableind(idx).MINIMAL := TRUE;

                cg$QUALIFICATIF.validate_foreign_keys_del(cg$rec);
                cg$QUALIFICATIF.upd_oper_denorm2( cg$rec,
                                                    cg$old_rec,
                                                    cg$QUALIFICATIF.cg$tableind(idx),
                                                    'DEL'
                                                  );
	
                cg$QUALIFICATIF.cascade_delete(cg$rec);
                cg$QUALIFICATIF.domain_cascade_delete(cg$rec);             
            END IF;
            idx := cg$QUALIFICATIF.cg$table.NEXT(idx);
        END LOOP;
    END IF;

--  Application_logic Post-After-Delete-statement <<Start>>
--  Application_logic Post-After-Delete-statement << End >>

END;
/



