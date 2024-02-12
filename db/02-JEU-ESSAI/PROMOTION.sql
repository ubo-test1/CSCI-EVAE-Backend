-- 
-- Jeu d'essai de la table PROMOTION
---

Insert into "PROMOTION" ("ANNEE_PRO","CODE_FORMATION","NO_ENSEIGNANT","SIGLE_PRO",
			"NB_ETU_SOUHAITE","ETAT_PRESELECTION","DATE_RENTREE","LIEU_RENTREE",
			"DATE_REPONSE_LP","COMMENTAIRE","DATE_REPONSE_LALP","PROCESSUS_STAGE") 
		values  ('2006-2007',NULL,'1','RSOFT',
			12,'ENC',to_date('2006-09-18','yyyy-mm-dd'),'LC117A',
			to_date('2006-07-14','yyyy-mm-dd'),null,null,null);

Insert into "PROMOTION" ("ANNEE_PRO","CODE_FORMATION","NO_ENSEIGNANT","SIGLE_PRO",
			"NB_ETU_SOUHAITE","ETAT_PRESELECTION","DATE_RENTREE","LIEU_RENTREE",
			"DATE_REPONSE_LP","COMMENTAIRE","DATE_REPONSE_LALP","PROCESSUS_STAGE") 
		values  ('2004-2005',NULL,'1','CILI3',
			10,'TER',to_date('2004-09-13','yyyy-mm-dd'),'LC117A',
			to_date('2004-07-23','yyyy-mm-dd'),null,to_date('2004-08-13','yyyy-mm-dd'),'RECH');

Insert into "PROMOTION" ("ANNEE_PRO","CODE_FORMATION","NO_ENSEIGNANT","SIGLE_PRO",
			"NB_ETU_SOUHAITE","ETAT_PRESELECTION","DATE_RENTREE","LIEU_RENTREE",
			"DATE_REPONSE_LP","COMMENTAIRE","DATE_REPONSE_LALP","PROCESSUS_STAGE") 
		values  ('2005-2006',NULL,'1','CILI4',
			12,'TER',to_date('2005-09-19','yyyy-mm-dd'),'LC117A',
			to_date('2005-07-14','yyyy-mm-dd'),null,to_date('2005-08-13','yyyy-mm-dd'),'EC');
