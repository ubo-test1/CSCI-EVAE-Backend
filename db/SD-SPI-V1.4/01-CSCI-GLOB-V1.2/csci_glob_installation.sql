-- 
-- Script d'installation de l'article de configuration CSCI-GLOB
--	

-- Création des éléments DDL :  tables, vues, index, ...
@@01-CREATION\01-DDL\csci_glob_tab
@@01-CREATION\01-DDL\csci_glob_pk
@@01-CREATION\01-DDL\csci_glob_seq
@@01-CREATION\01-DDL\csci_glob_ind

-- Création du package CG$ERROS nécessaire aux API de tabme
@@01-CREATION\03-PLSQL\cdsaper.PKS
@@01-CREATION\03-PLSQL\cdsaper.PKB

-- Création de l'API de la table CG_REF_CODES
@@01-CREATION\02-API\CGRC.pks
@@01-CREATION\02-API\CGRC.pkb
@@01-CREATION\02-API\CGRC.trg


-- Création des domaines dynamiques
@@01-CREATION\01-DDL\csci_glob_avt
