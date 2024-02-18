--
-- Création des index de l'article de configuration CSCI_GLOB
--

PROMPT Creating Index 'CGRC_I'
CREATE INDEX CGRC_I ON CG_REF_CODES
 (RV_DOMAIN
 ,RV_LOW_VALUE)
/


