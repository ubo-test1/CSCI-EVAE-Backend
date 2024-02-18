-- 
-- Script d'installation du CSCI-EVAE
-- Ph. Saliou - 17 janvier 2014
--	

-- Création des éléments DDL :  tables, vues, index, ...
@@01-CREATION\01-DDL\csci_evae_tab
@@01-CREATION\01-DDL\csci_evae_vw
@@01-CREATION\01-DDL\csci_evae_pk
@@01-CREATION\01-DDL\csci_evae_uk
@@01-CREATION\01-DDL\csci_evae_fk
@@01-CREATION\01-DDL\csci_evae_ck
@@01-CREATION\01-DDL\csci_evae_ind
@@01-CREATION\01-DDL\csci_evae_seq
@@01-CREATION\01-DDL\csci_evae_avt

-- Création de l'API de la table EVALUATION
@@01-CREATION\02-API\EVE.pks
@@01-CREATION\02-API\EVE.pkb
@@01-CREATION\02-API\EVE.trg

-- Création de l'API de la table QUALIFICATIF
@@01-CREATION\02-API\QUA.pks
@@01-CREATION\02-API\QUA.pkb
@@01-CREATION\02-API\QUA.trg

-- Création de l'API de la table QUESTION
@@01-CREATION\02-API\QUE.pks
@@01-CREATION\02-API\QUE.pkb
@@01-CREATION\02-API\QUE.trg

-- Création de l'API de la table QUESTION_EVALUATION
@@01-CREATION\02-API\QEV.pks
@@01-CREATION\02-API\QEV.pkb
@@01-CREATION\02-API\QEV.trg

-- Création de l'API de la table REPONSE_EVALUATION
@@01-CREATION\02-API\RPE.pks
@@01-CREATION\02-API\RPE.pkb
@@01-CREATION\02-API\RPE.trg

-- Création de l'API de la table RUBRIQUE
@@01-CREATION\02-API\RUB.pks
@@01-CREATION\02-API\RUB.pkb
@@01-CREATION\02-API\RUB.trg

-- Création de l'API de la table RUBRIQUE_EVALUATION
@@01-CREATION\02-API\REV.pks
@@01-CREATION\02-API\REV.pkb
@@01-CREATION\02-API\REV.trg

-- Création du jeu d'essai
@@02-JEU-ESSAI\qualificatif
@@02-JEU-ESSAI\question
@@02-JEU-ESSAI\rubrique
@@02-JEU-ESSAI\rubrique_question
@@02-JEU-ESSAI\evaluation
@@02-JEU-ESSAI\droit
@@02-JEU-ESSAI\rubrique_evaluation
@@02-JEU-ESSAI\question_evaluation
@@02-JEU-ESSAI\reponse_evaluation
@@02-JEU-ESSAI\reponse_question


commit;
