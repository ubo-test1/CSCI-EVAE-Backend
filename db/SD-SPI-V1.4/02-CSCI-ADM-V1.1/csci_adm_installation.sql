-- 
-- Script d'installation de l'article de configuration CSCI-ADM
--	

-- Création des éléments DDL :  tables, vues, index, ...
@@01-CREATION\01-DDL\csci_adm_tab
@@01-CREATION\01-DDL\csci_adm_jn
@@01-CREATION\01-DDL\csci_adm_vw
@@01-CREATION\01-DDL\csci_adm_pk
@@01-CREATION\01-DDL\csci_adm_fk
@@01-CREATION\01-DDL\csci_adm_ck
@@01-CREATION\01-DDL\csci_adm_ind
@@01-CREATION\01-DDL\csci_adm_avt
@@01-CREATION\01-DDL\csci_adm_seq
@@01-CREATION\01-DDL\csci_adm_glob_fk

-- Création de l'API de la table AUTHENTIFICATION
@@01-CREATION\02-API\AUT.pks
@@01-CREATION\02-API\AUT.pkb
@@01-CREATION\02-API\AUT.trg

-- Création du jeu d'essai
@@02-JEU-ESSAI\formation
@@02-JEU-ESSAI\enseignant
@@02-JEU-ESSAI\unite_enseignement
@@02-JEU-ESSAI\element_constitutif
@@02-JEU-ESSAI\promotion
@@02-JEU-ESSAI\candidat
@@02-JEU-ESSAI\etudiant
@@02-JEU-ESSAI\authentification

commit;





