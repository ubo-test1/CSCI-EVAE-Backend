-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 01 fév. 2024 à 16:09
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `csa_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `avenant`
--

CREATE TABLE `avenant` (
  `no_avenant` int(11) NOT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  `date_signature_ave` datetime(6) DEFAULT NULL,
  `etat_avenant` varchar(255) NOT NULL,
  `modif_apportee` varchar(255) NOT NULL,
  `num_article` int(11) NOT NULL,
  `annee_pro` varchar(255) NOT NULL,
  `no_etudiant_nat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `candidat`
--

CREATE TABLE `candidat` (
  `no_etudiant_nat` varchar(50) NOT NULL,
  `actu_adresse` varchar(255) DEFAULT NULL,
  `actu_cp` varchar(10) DEFAULT NULL,
  `actu_pays` varchar(255) DEFAULT NULL,
  `actu_ville` varchar(255) DEFAULT NULL,
  `date_naissance` datetime(6) NOT NULL,
  `date_reponse_can` datetime(6) DEFAULT NULL,
  `date_reponse_ili` datetime(6) DEFAULT NULL,
  `dernier_diplome` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `lieu_naissance` varchar(255) NOT NULL,
  `nationalite` varchar(50) NOT NULL,
  `no_ordre` double DEFAULT NULL,
  `nom` varchar(50) NOT NULL,
  `perm_adresse` varchar(255) NOT NULL,
  `perm_cp` varchar(10) NOT NULL,
  `perm_pays` varchar(255) NOT NULL,
  `perm_ville` varchar(255) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `reponse_can` varchar(3) DEFAULT NULL,
  `reponse_ili` char(1) NOT NULL,
  `selection_courante` varchar(2) NOT NULL,
  `selection_origine` varchar(2) NOT NULL,
  `sexe` char(1) NOT NULL,
  `situation` varchar(3) NOT NULL,
  `tel_fixe` varchar(20) DEFAULT NULL,
  `tel_port` varchar(20) DEFAULT NULL,
  `universite` varchar(255) NOT NULL,
  `annee_pro` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `cg_ref_codes`
--

CREATE TABLE `cg_ref_codes` (
  `id` int(11) NOT NULL,
  `rv_abbreviation` varchar(240) DEFAULT NULL,
  `rv_domain` varchar(100) NOT NULL,
  `rv_high_value` varchar(240) DEFAULT NULL,
  `rv_low_value` varchar(240) NOT NULL,
  `rv_meaning` varchar(240) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `code_notation`
--

CREATE TABLE `code_notation` (
  `codn_id` double NOT NULL,
  `code_note` varchar(3) NOT NULL,
  `descriptif` tinytext DEFAULT NULL,
  `designation` varchar(50) NOT NULL,
  `valeur_note` double NOT NULL,
  `no_bareme` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `contact_stage`
--

CREATE TABLE `contact_stage` (
  `annee_pro` varchar(10) NOT NULL,
  `date_contact` datetime(6) NOT NULL,
  `duree` varchar(20) DEFAULT NULL,
  `heure_contact` varchar(20) DEFAULT NULL,
  `interlocuteur` varchar(3) DEFAULT NULL,
  `objet` varchar(255) NOT NULL,
  `resume` tinytext DEFAULT NULL,
  `type_contact` varchar(3) NOT NULL,
  `no_contact_ili` double DEFAULT NULL,
  `NO_ETUDIANT_NAT` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `critere_entreprise`
--

CREATE TABLE `critere_entreprise` (
  `crite_id` double NOT NULL,
  `descriptif` tinytext DEFAULT NULL,
  `designation` varchar(200) NOT NULL,
  `ordre` double NOT NULL,
  `poids` double NOT NULL,
  `no_evaluation` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `diplome`
--

CREATE TABLE `diplome` (
  `no_etudiant_nat` varchar(50) NOT NULL,
  `adresse` varchar(100) DEFAULT NULL,
  `autorisation_annuaire` char(1) NOT NULL,
  `cp` varchar(10) DEFAULT NULL,
  `email_perso` varchar(100) DEFAULT NULL,
  `mail_pro` varchar(100) DEFAULT NULL,
  `nom` varchar(50) NOT NULL,
  `portable` varchar(20) DEFAULT NULL,
  `prenom` varchar(50) NOT NULL,
  `tel_perso` varchar(20) DEFAULT NULL,
  `tel_pro` varchar(20) DEFAULT NULL,
  `type_emploi` varchar(3) DEFAULT NULL,
  `ville` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `droit`
--

CREATE TABLE `droit` (
  `consultation` char(1) NOT NULL,
  `duplication` char(1) NOT NULL,
  `ID_EVALUATION` bigint(20) NOT NULL,
  `NO_ENSEIGNANT` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `element_constitutif`
--

CREATE TABLE `element_constitutif` (
  `code_ec` varchar(8) NOT NULL,
  `code_formation` varchar(8) NOT NULL,
  `code_ue` varchar(8) NOT NULL,
  `description` varchar(240) NOT NULL,
  `designation` varchar(64) NOT NULL,
  `nbh_cm` tinyint(4) DEFAULT NULL,
  `nbh_td` tinyint(4) DEFAULT NULL,
  `nbh_tp` tinyint(4) DEFAULT NULL,
  `no_enseignant` int(11) NOT NULL,
  `unite_enseignement_code_ue` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE `employe` (
  `no_contact_ili` double NOT NULL,
  `fonction` varchar(5) DEFAULT NULL,
  `mail_pro` varchar(100) DEFAULT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `tel_pro` varchar(20) DEFAULT NULL,
  `no_entreprise` double NOT NULL,
  `no_etudiant_nat` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE `enseignant` (
  `no_enseignant` int(11) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `cp` varchar(10) NOT NULL,
  `enc_perso_email` varchar(255) DEFAULT NULL,
  `enc_perso_tel` varchar(20) DEFAULT NULL,
  `enc_ubo_email` varchar(255) DEFAULT NULL,
  `enc_ubo_tel` varchar(20) DEFAULT NULL,
  `int_fonction` varchar(50) DEFAULT NULL,
  `int_no_insee` varchar(50) DEFAULT NULL,
  `int_prof_email` varchar(255) DEFAULT NULL,
  `int_prof_tel` varchar(20) DEFAULT NULL,
  `int_soc_adresse` varchar(255) DEFAULT NULL,
  `int_soc_cp` varchar(10) DEFAULT NULL,
  `int_soc_nom` varchar(50) DEFAULT NULL,
  `int_soc_pays` varchar(255) DEFAULT NULL,
  `int_soc_ville` varchar(255) DEFAULT NULL,
  `nom` varchar(50) NOT NULL,
  `pays` varchar(255) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `sexe` varchar(1) NOT NULL,
  `tel_port` varchar(20) DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `ville` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE `entreprise` (
  `no_entreprise` double NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `cp` varchar(10) NOT NULL,
  `date_crea` datetime(6) NOT NULL,
  `date_maj` datetime(6) DEFAULT NULL,
  `date_referencement` datetime(6) DEFAULT NULL,
  `domaine_activite` varchar(5) NOT NULL,
  `login_crea` varchar(8) DEFAULT NULL,
  `login_maj` varchar(8) DEFAULT NULL,
  `nom` varchar(100) NOT NULL,
  `nom_representant` varchar(50) DEFAULT NULL,
  `offre_stage` char(1) DEFAULT NULL,
  `pays` varchar(100) NOT NULL,
  `prenom_representant` varchar(50) DEFAULT NULL,
  `referencee` char(1) DEFAULT NULL,
  `siege_social` varchar(100) NOT NULL,
  `site_internet` varchar(150) DEFAULT NULL,
  `tel` varchar(20) NOT NULL,
  `ville` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `entreprise_jn`
--

CREATE TABLE `entreprise_jn` (
  `login_maj` varchar(8) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `cp` varchar(10) DEFAULT NULL,
  `date_crea` datetime(6) DEFAULT NULL,
  `date_maj` datetime(6) DEFAULT NULL,
  `date_referencement` datetime(6) DEFAULT NULL,
  `domaine_activite` varchar(5) DEFAULT NULL,
  `jn_appln` varchar(35) DEFAULT NULL,
  `jn_datetime` datetime(6) NOT NULL,
  `jn_notes` varchar(240) DEFAULT NULL,
  `jn_operation` varchar(3) NOT NULL,
  `jn_oracle_user` varchar(30) NOT NULL,
  `jn_session` decimal(38,0) DEFAULT NULL,
  `login_crea` varchar(8) DEFAULT NULL,
  `no_entreprise` double NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `nom_representant` varchar(50) DEFAULT NULL,
  `offre_stage` char(1) DEFAULT NULL,
  `pays` varchar(100) DEFAULT NULL,
  `prenom_representant` varchar(50) DEFAULT NULL,
  `referencee` char(1) DEFAULT NULL,
  `siege_social` varchar(100) DEFAULT NULL,
  `site_internet` varchar(150) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `ville` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `no_etudiant_nat` varchar(50) NOT NULL,
  `abandon_date` datetime(6) DEFAULT NULL,
  `abandon_motif` varchar(255) DEFAULT NULL,
  `actu_adresse` varchar(255) DEFAULT NULL,
  `actu_cp` varchar(10) DEFAULT NULL,
  `actu_pays` varchar(255) DEFAULT NULL,
  `actu_ville` varchar(255) DEFAULT NULL,
  `code_com` varchar(10) DEFAULT NULL,
  `compte_cri` varchar(10) NOT NULL,
  `date_naissance` datetime(6) NOT NULL,
  `dernier_diplome` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `est_diplome` char(1) DEFAULT NULL,
  `grpe_anglais` tinyint(4) DEFAULT NULL,
  `lieu_naissance` varchar(255) NOT NULL,
  `nationalite` varchar(50) NOT NULL,
  `no_etudiant_ubo` varchar(20) DEFAULT NULL,
  `nom` varchar(50) NOT NULL,
  `perm_adresse` varchar(255) NOT NULL,
  `perm_cp` varchar(10) NOT NULL,
  `perm_pays` varchar(255) NOT NULL,
  `perm_ville` varchar(255) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `sexe` varchar(1) NOT NULL,
  `sigle_etu` varchar(3) NOT NULL,
  `situation` varchar(3) NOT NULL,
  `tel_fixe` varchar(20) DEFAULT NULL,
  `tel_port` varchar(20) DEFAULT NULL,
  `ubo_email` varchar(255) DEFAULT NULL,
  `universite` varchar(255) NOT NULL,
  `annee_pro` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

CREATE TABLE `evaluation` (
  `id_evaluation` bigint(20) NOT NULL,
  `debut_reponse` datetime(6) NOT NULL,
  `etat` varchar(3) NOT NULL,
  `fin_reponse` datetime(6) NOT NULL,
  `no_evaluation` tinyint(4) NOT NULL,
  `periode` varchar(64) DEFAULT NULL,
  `annee_pro` varchar(10) NOT NULL,
  `code_ec` varchar(8) NOT NULL,
  `code_formation` varchar(8) NOT NULL,
  `code_ue` varchar(8) NOT NULL,
  `no_enseignant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `evaluation_entreprise`
--

CREATE TABLE `evaluation_entreprise` (
  `annee_pro` varchar(255) NOT NULL,
  `no_etudiant_nat` varchar(255) NOT NULL,
  `date_maj` datetime(6) NOT NULL,
  `etat` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `code_formation` varchar(8) NOT NULL,
  `debut_habilitation` datetime(6) DEFAULT NULL,
  `diplome` varchar(3) NOT NULL,
  `double_diplome` char(1) NOT NULL,
  `fin_habilitation` datetime(6) DEFAULT NULL,
  `n0_annee` tinyint(4) NOT NULL,
  `nom_formation` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `notation_stage`
--

CREATE TABLE `notation_stage` (
  `no_bareme` double NOT NULL,
  `coeff_ent` double NOT NULL,
  `coeff_rapport` double NOT NULL,
  `coeff_soutenance` double NOT NULL,
  `date_creation` datetime(6) NOT NULL,
  `etat_bareme` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `note_entreprise`
--

CREATE TABLE `note_entreprise` (
  `annee_pro` varchar(10) NOT NULL,
  `no_etudiant_nat` varchar(50) NOT NULL,
  `commentaire` tinytext DEFAULT NULL,
  `codn_id` double NOT NULL,
  `CRITE_ID` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `offre_stage`
--

CREATE TABLE `offre_stage` (
  `no_offre` double NOT NULL,
  `date_creation` datetime(6) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `duree` varchar(30) NOT NULL,
  `etat_offre` varchar(3) NOT NULL,
  `intitule` varchar(100) NOT NULL,
  `lieu` varchar(80) NOT NULL,
  `mail_responsable` varchar(100) DEFAULT NULL,
  `niveau_requis` varchar(5) NOT NULL,
  `no_offre_entreprise` varchar(20) DEFAULT NULL,
  `nom_responsable` varchar(50) NOT NULL,
  `periode` varchar(50) NOT NULL,
  `prenom_responsable` varchar(50) NOT NULL,
  `remuneration` varchar(20) DEFAULT NULL,
  `sujet` varchar(255) NOT NULL,
  `tel_responsable` varchar(20) DEFAULT NULL,
  `annee_pro` varchar(10) NOT NULL,
  `no_entreprise` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `poste_entreprise`
--

CREATE TABLE `poste_entreprise` (
  `brut_annuel` int(11) DEFAULT NULL,
  `date_embauche` datetime(6) NOT NULL,
  `fonction` varchar(5) NOT NULL,
  `moyen_obtention` varchar(3) NOT NULL,
  `service` varchar(5) DEFAULT NULL,
  `NO_ENTREPRISE` double NOT NULL,
  `NO_ETUDIANT_NAT` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

CREATE TABLE `promotion` (
  `annee_pro` varchar(10) NOT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  `date_rentree` datetime(6) DEFAULT NULL,
  `date_reponse_lalp` datetime(6) DEFAULT NULL,
  `date_reponse_lp` datetime(6) DEFAULT NULL,
  `etat_preselection` varchar(3) NOT NULL,
  `lieu_rentree` varchar(255) DEFAULT NULL,
  `nb_etu_souhaite` smallint(6) NOT NULL,
  `processus_stage` varchar(5) DEFAULT NULL,
  `sigle_pro` varchar(5) NOT NULL,
  `code_formation` varchar(8) DEFAULT NULL,
  `no_bareme` double DEFAULT NULL,
  `no_enseignant` int(11) DEFAULT NULL,
  `no_evaluation` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `qualificatif`
--

CREATE TABLE `qualificatif` (
  `id_qualificatif` bigint(20) NOT NULL,
  `maximal` varchar(16) NOT NULL,
  `minimal` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE `question` (
  `id_question` bigint(20) NOT NULL,
  `intitulֹ` varchar(64) NOT NULL,
  `type` varchar(10) NOT NULL,
  `id_qualificatif` bigint(20) NOT NULL,
  `no_enseignant` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `question_evaluation`
--

CREATE TABLE `question_evaluation` (
  `id_question_evaluation` bigint(20) NOT NULL,
  `intitule` varchar(64) DEFAULT NULL,
  `ordre` tinyint(4) NOT NULL,
  `id_qualificatif` bigint(20) DEFAULT NULL,
  `id_question` bigint(20) DEFAULT NULL,
  `id_rubrique_evaluation` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reponse_evaluation`
--

CREATE TABLE `reponse_evaluation` (
  `id_reponse_evaluation` bigint(20) NOT NULL,
  `commentaire` varchar(512) DEFAULT NULL,
  `nom` varchar(32) DEFAULT NULL,
  `prenom` varchar(32) DEFAULT NULL,
  `id_evaluation` bigint(20) NOT NULL,
  `no_etudiant_nat` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reponse_question`
--

CREATE TABLE `reponse_question` (
  `positionnement` decimal(38,0) DEFAULT NULL,
  `ID_QUESTION_EVALUATION` bigint(20) NOT NULL,
  `ID_REPONSE_QUESTION` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` enum('ROLE_USER','ROLE_MODERATOR','ROLE_ADMIN') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `rubrique`
--

CREATE TABLE `rubrique` (
  `id_rubrique` bigint(20) NOT NULL,
  `designation` varchar(32) NOT NULL,
  `ordre` double DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `no_enseignant` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `rubrique_evaluation`
--

CREATE TABLE `rubrique_evaluation` (
  `id_rubrique_evaluation` bigint(20) NOT NULL,
  `designation` varchar(64) DEFAULT NULL,
  `ordre` tinyint(4) NOT NULL,
  `id_evaluation` bigint(20) NOT NULL,
  `id_rubrique` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `rubrique_question`
--

CREATE TABLE `rubrique_question` (
  `ordre` decimal(38,0) NOT NULL,
  `ID_QUESTION` bigint(20) NOT NULL,
  `ID_RUBRIQUE` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `soutenance`
--

CREATE TABLE `soutenance` (
  `no_session` double NOT NULL,
  `date_soutenance` datetime(6) NOT NULL,
  `plage_horaire` varchar(50) NOT NULL,
  `salle` varchar(12) NOT NULL,
  `ANNEE_PRO` varchar(255) NOT NULL,
  `no_enseignant_assesseur` int(11) NOT NULL,
  `no_enseignant_responsable` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `stage`
--

CREATE TABLE `stage` (
  `commentaire_tuteur` varchar(255) DEFAULT NULL,
  `date_deb` datetime(6) NOT NULL,
  `date_fin` datetime(6) NOT NULL,
  `date_reception_rapport` datetime(6) DEFAULT NULL,
  `date_signature_conv` datetime(6) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `etat_convention` varchar(3) DEFAULT NULL,
  `etat_stage` varchar(3) NOT NULL,
  `intitule` varchar(200) NOT NULL,
  `lieu` varchar(50) NOT NULL,
  `note_entreprise` double DEFAULT NULL,
  `note_rapport` double DEFAULT NULL,
  `note_soutenance` double DEFAULT NULL,
  `sujet` varchar(200) NOT NULL,
  `ANNEE_PRO` varchar(255) NOT NULL,
  `no_contact_ili` double NOT NULL,
  `no_enseignant` int(11) DEFAULT NULL,
  `no_entreprise` double NOT NULL,
  `NO_ETUDIANT_NAT` varchar(255) NOT NULL,
  `no_offre` double DEFAULT NULL,
  `no_session` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `structure_evaluation`
--

CREATE TABLE `structure_evaluation` (
  `no_evaluation` double NOT NULL,
  `date_creation` datetime(6) NOT NULL,
  `etat` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `unite_enseignement`
--

CREATE TABLE `unite_enseignement` (
  `code_ue` varchar(8) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `designation` varchar(64) NOT NULL,
  `nbh_cm` decimal(38,0) DEFAULT NULL,
  `nbh_td` tinyint(4) DEFAULT NULL,
  `nbh_tp` tinyint(4) DEFAULT NULL,
  `semestre` varchar(3) NOT NULL,
  `code_formation` varchar(8) NOT NULL,
  `no_enseignant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `visite_stage`
--

CREATE TABLE `visite_stage` (
  `annee_pro` varchar(255) NOT NULL,
  `no_etudiant_nat` varchar(255) NOT NULL,
  `commentaire_tuteur_ubo` tinytext DEFAULT NULL,
  `date_effective` datetime(6) DEFAULT NULL,
  `date_previsionnelle` datetime(6) NOT NULL,
  `duree` varchar(20) DEFAULT NULL,
  `heure_effective` varchar(20) DEFAULT NULL,
  `heure_previsionnelle` varchar(20) DEFAULT NULL,
  `type_contact` varchar(3) NOT NULL,
  `no_contact_ili` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `avenant`
--
ALTER TABLE `avenant`
  ADD PRIMARY KEY (`no_avenant`),
  ADD KEY `FKk5l2r5og9n2jfudl4n92tkyer` (`annee_pro`,`no_etudiant_nat`);

--
-- Index pour la table `candidat`
--
ALTER TABLE `candidat`
  ADD PRIMARY KEY (`no_etudiant_nat`),
  ADD KEY `FKt8livm25aoqk7bx7xw90yflij` (`annee_pro`);

--
-- Index pour la table `cg_ref_codes`
--
ALTER TABLE `cg_ref_codes`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `code_notation`
--
ALTER TABLE `code_notation`
  ADD PRIMARY KEY (`codn_id`),
  ADD KEY `FKh03dyj8t5fgmn6m4bvwumxwsy` (`no_bareme`);

--
-- Index pour la table `contact_stage`
--
ALTER TABLE `contact_stage`
  ADD PRIMARY KEY (`annee_pro`,`date_contact`,`NO_ETUDIANT_NAT`),
  ADD KEY `FKdf6iuu8auls4drf25ydunsfjk` (`no_contact_ili`),
  ADD KEY `FKcnpftf44vfhpw1f4qfynxkauv` (`NO_ETUDIANT_NAT`);

--
-- Index pour la table `critere_entreprise`
--
ALTER TABLE `critere_entreprise`
  ADD PRIMARY KEY (`crite_id`),
  ADD KEY `FKn377vi3glpcqjjcp63wutwk3y` (`no_evaluation`);

--
-- Index pour la table `diplome`
--
ALTER TABLE `diplome`
  ADD PRIMARY KEY (`no_etudiant_nat`);

--
-- Index pour la table `droit`
--
ALTER TABLE `droit`
  ADD PRIMARY KEY (`ID_EVALUATION`,`NO_ENSEIGNANT`),
  ADD KEY `FKcterw8cqj8v15xj36qb3ivyx4` (`NO_ENSEIGNANT`);

--
-- Index pour la table `element_constitutif`
--
ALTER TABLE `element_constitutif`
  ADD PRIMARY KEY (`code_ec`,`code_formation`,`code_ue`),
  ADD KEY `FK6v81wmehjl6qsa8vvo8ml7eq2` (`no_enseignant`),
  ADD KEY `FK7ko6kndoseuvunnqvyl5gqt0i` (`unite_enseignement_code_ue`);

--
-- Index pour la table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`no_contact_ili`),
  ADD KEY `FKayksfjovgowjxp7q4u5w40yvy` (`no_entreprise`),
  ADD KEY `FKdwvhoxwld6og3pwve57dje7uv` (`no_etudiant_nat`);

--
-- Index pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`no_enseignant`);

--
-- Index pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`no_entreprise`);

--
-- Index pour la table `entreprise_jn`
--
ALTER TABLE `entreprise_jn`
  ADD PRIMARY KEY (`login_maj`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`no_etudiant_nat`),
  ADD KEY `FKru5hakekuhbjw90gml6lqq9jb` (`annee_pro`);

--
-- Index pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD PRIMARY KEY (`id_evaluation`),
  ADD KEY `FKqkyyu36otfgith83oaf6ahchl` (`annee_pro`),
  ADD KEY `FKqnc7bmp3jrano3238wdyo51d7` (`code_ec`,`code_formation`,`code_ue`),
  ADD KEY `FKemryn3bkip9kone6srol8wwlk` (`no_enseignant`);

--
-- Index pour la table `evaluation_entreprise`
--
ALTER TABLE `evaluation_entreprise`
  ADD PRIMARY KEY (`annee_pro`,`no_etudiant_nat`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`code_formation`);

--
-- Index pour la table `notation_stage`
--
ALTER TABLE `notation_stage`
  ADD PRIMARY KEY (`no_bareme`);

--
-- Index pour la table `note_entreprise`
--
ALTER TABLE `note_entreprise`
  ADD PRIMARY KEY (`annee_pro`,`CRITE_ID`,`no_etudiant_nat`),
  ADD KEY `FKti764ha93twb0qbld3ywjs2ba` (`codn_id`),
  ADD KEY `FKtrwd7r5pba7wq94rof3gryfua` (`CRITE_ID`),
  ADD KEY `FK8mqm2at84yb7095w3uvdvk8lm` (`annee_pro`,`no_etudiant_nat`);

--
-- Index pour la table `offre_stage`
--
ALTER TABLE `offre_stage`
  ADD PRIMARY KEY (`no_offre`),
  ADD KEY `FKr0uojmcjhfintwd8xu4061vsn` (`annee_pro`),
  ADD KEY `FK6rqppjmo5ddsaec29vw51t8ws` (`no_entreprise`);

--
-- Index pour la table `poste_entreprise`
--
ALTER TABLE `poste_entreprise`
  ADD PRIMARY KEY (`NO_ENTREPRISE`,`NO_ETUDIANT_NAT`),
  ADD KEY `FKguvv2ir362cisan3ts3pd5h1k` (`NO_ETUDIANT_NAT`);

--
-- Index pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`annee_pro`),
  ADD KEY `FKbem6u73c3epx5s1fdt97uf13g` (`code_formation`),
  ADD KEY `FKq0ewfgdlid4t8qjjln5u0r80u` (`no_bareme`),
  ADD KEY `FKlcylu9f2sqrl7hrvvnko8la0t` (`no_enseignant`),
  ADD KEY `FKcryp2cgc4is7ky41koxgr9uc0` (`no_evaluation`);

--
-- Index pour la table `qualificatif`
--
ALTER TABLE `qualificatif`
  ADD PRIMARY KEY (`id_qualificatif`);

--
-- Index pour la table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id_question`),
  ADD KEY `FKl4ler5t8k2w31e88uwr9hiam3` (`id_qualificatif`),
  ADD KEY `FKenuqmo8ato75nwtmnhvqn8of` (`no_enseignant`);

--
-- Index pour la table `question_evaluation`
--
ALTER TABLE `question_evaluation`
  ADD PRIMARY KEY (`id_question_evaluation`),
  ADD KEY `FK7bvyyl9mow6stqpk9un8qnw2n` (`id_qualificatif`),
  ADD KEY `FKh584khu4508arn65wv9khiikv` (`id_question`),
  ADD KEY `FK95saw6ruhtpgwidv4n48qtr8l` (`id_rubrique_evaluation`);

--
-- Index pour la table `reponse_evaluation`
--
ALTER TABLE `reponse_evaluation`
  ADD PRIMARY KEY (`id_reponse_evaluation`),
  ADD KEY `FKlrbksy6fsgb03iak3dk0w42oo` (`id_evaluation`),
  ADD KEY `FKlk5perpahqbwartij4fmiwl05` (`no_etudiant_nat`);

--
-- Index pour la table `reponse_question`
--
ALTER TABLE `reponse_question`
  ADD PRIMARY KEY (`ID_QUESTION_EVALUATION`,`ID_REPONSE_QUESTION`),
  ADD KEY `FKeaf555xyssr4derke8cbchp3o` (`ID_REPONSE_QUESTION`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `rubrique`
--
ALTER TABLE `rubrique`
  ADD PRIMARY KEY (`id_rubrique`),
  ADD KEY `FKq2uic5c6upre2u11c867e5pli` (`no_enseignant`);

--
-- Index pour la table `rubrique_evaluation`
--
ALTER TABLE `rubrique_evaluation`
  ADD PRIMARY KEY (`id_rubrique_evaluation`),
  ADD KEY `FKlu5sx4yfycfa11noogcn0kacm` (`id_evaluation`),
  ADD KEY `FK20jvujg1dvmy0s0tg6cd0u0j9` (`id_rubrique`);

--
-- Index pour la table `rubrique_question`
--
ALTER TABLE `rubrique_question`
  ADD PRIMARY KEY (`ID_QUESTION`,`ID_RUBRIQUE`),
  ADD KEY `FKbi4l32ekwlji8t9juvdqvae20` (`ID_RUBRIQUE`);

--
-- Index pour la table `soutenance`
--
ALTER TABLE `soutenance`
  ADD PRIMARY KEY (`ANNEE_PRO`,`no_session`),
  ADD KEY `FKb7hd46taw70hcf5c6je99j4vf` (`no_enseignant_assesseur`),
  ADD KEY `FKj4qf1or5n1muqb2rhcs7jwjvd` (`no_enseignant_responsable`);

--
-- Index pour la table `stage`
--
ALTER TABLE `stage`
  ADD PRIMARY KEY (`ANNEE_PRO`,`NO_ETUDIANT_NAT`),
  ADD KEY `FKtnphgp84js5r3ipcwfg0ra36c` (`no_contact_ili`),
  ADD KEY `FKhtsrcl55dukmhir4gb9vapdrd` (`no_enseignant`),
  ADD KEY `FKkkxcii2nk50bvw9n3aglcywlp` (`no_entreprise`),
  ADD KEY `FKtjd3nhk1h6kvwhikbrgysgimv` (`NO_ETUDIANT_NAT`),
  ADD KEY `FKq732upmlkef2ysamrigd7f9dt` (`no_offre`),
  ADD KEY `FK3vsuu65gnm3xxuosuj83qk8dr` (`ANNEE_PRO`,`no_session`);

--
-- Index pour la table `structure_evaluation`
--
ALTER TABLE `structure_evaluation`
  ADD PRIMARY KEY (`no_evaluation`);

--
-- Index pour la table `unite_enseignement`
--
ALTER TABLE `unite_enseignement`
  ADD PRIMARY KEY (`code_ue`),
  ADD KEY `FKtncs5cp8xgnt0p078xa1oijlp` (`code_formation`),
  ADD KEY `FK144kbyvq4xmg673abyy35ee50` (`no_enseignant`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Index pour la table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`);

--
-- Index pour la table `visite_stage`
--
ALTER TABLE `visite_stage`
  ADD PRIMARY KEY (`annee_pro`,`no_etudiant_nat`),
  ADD KEY `FK2r8f9hr6p678t3jo1bm6dbavv` (`no_contact_ili`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `cg_ref_codes`
--
ALTER TABLE `cg_ref_codes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `avenant`
--
ALTER TABLE `avenant`
  ADD CONSTRAINT `FKk5l2r5og9n2jfudl4n92tkyer` FOREIGN KEY (`annee_pro`,`no_etudiant_nat`) REFERENCES `stage` (`ANNEE_PRO`, `NO_ETUDIANT_NAT`);

--
-- Contraintes pour la table `candidat`
--
ALTER TABLE `candidat`
  ADD CONSTRAINT `FKt8livm25aoqk7bx7xw90yflij` FOREIGN KEY (`annee_pro`) REFERENCES `promotion` (`annee_pro`);

--
-- Contraintes pour la table `code_notation`
--
ALTER TABLE `code_notation`
  ADD CONSTRAINT `FKh03dyj8t5fgmn6m4bvwumxwsy` FOREIGN KEY (`no_bareme`) REFERENCES `notation_stage` (`no_bareme`);

--
-- Contraintes pour la table `contact_stage`
--
ALTER TABLE `contact_stage`
  ADD CONSTRAINT `FKcnpftf44vfhpw1f4qfynxkauv` FOREIGN KEY (`NO_ETUDIANT_NAT`) REFERENCES `etudiant` (`no_etudiant_nat`),
  ADD CONSTRAINT `FKdf6iuu8auls4drf25ydunsfjk` FOREIGN KEY (`no_contact_ili`) REFERENCES `employe` (`no_contact_ili`);

--
-- Contraintes pour la table `critere_entreprise`
--
ALTER TABLE `critere_entreprise`
  ADD CONSTRAINT `FKn377vi3glpcqjjcp63wutwk3y` FOREIGN KEY (`no_evaluation`) REFERENCES `structure_evaluation` (`no_evaluation`);

--
-- Contraintes pour la table `diplome`
--
ALTER TABLE `diplome`
  ADD CONSTRAINT `FKdjxggoidji7e18lfh61xsnu9b` FOREIGN KEY (`no_etudiant_nat`) REFERENCES `etudiant` (`no_etudiant_nat`);

--
-- Contraintes pour la table `droit`
--
ALTER TABLE `droit`
  ADD CONSTRAINT `FKcterw8cqj8v15xj36qb3ivyx4` FOREIGN KEY (`NO_ENSEIGNANT`) REFERENCES `enseignant` (`no_enseignant`),
  ADD CONSTRAINT `FKldyrfcjwtks3wi6cc4v0rdas3` FOREIGN KEY (`ID_EVALUATION`) REFERENCES `evaluation` (`id_evaluation`);

--
-- Contraintes pour la table `element_constitutif`
--
ALTER TABLE `element_constitutif`
  ADD CONSTRAINT `FK6v81wmehjl6qsa8vvo8ml7eq2` FOREIGN KEY (`no_enseignant`) REFERENCES `enseignant` (`no_enseignant`),
  ADD CONSTRAINT `FK7ko6kndoseuvunnqvyl5gqt0i` FOREIGN KEY (`unite_enseignement_code_ue`) REFERENCES `unite_enseignement` (`code_ue`);

--
-- Contraintes pour la table `employe`
--
ALTER TABLE `employe`
  ADD CONSTRAINT `FKayksfjovgowjxp7q4u5w40yvy` FOREIGN KEY (`no_entreprise`) REFERENCES `entreprise` (`no_entreprise`),
  ADD CONSTRAINT `FKdwvhoxwld6og3pwve57dje7uv` FOREIGN KEY (`no_etudiant_nat`) REFERENCES `diplome` (`no_etudiant_nat`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `FKru5hakekuhbjw90gml6lqq9jb` FOREIGN KEY (`annee_pro`) REFERENCES `promotion` (`annee_pro`);

--
-- Contraintes pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `FKemryn3bkip9kone6srol8wwlk` FOREIGN KEY (`no_enseignant`) REFERENCES `enseignant` (`no_enseignant`),
  ADD CONSTRAINT `FKqkyyu36otfgith83oaf6ahchl` FOREIGN KEY (`annee_pro`) REFERENCES `promotion` (`annee_pro`),
  ADD CONSTRAINT `FKqnc7bmp3jrano3238wdyo51d7` FOREIGN KEY (`code_ec`,`code_formation`,`code_ue`) REFERENCES `element_constitutif` (`code_ec`, `code_formation`, `code_ue`);

--
-- Contraintes pour la table `evaluation_entreprise`
--
ALTER TABLE `evaluation_entreprise`
  ADD CONSTRAINT `FK22cshsblam949v2darcanv5m8` FOREIGN KEY (`annee_pro`,`no_etudiant_nat`) REFERENCES `stage` (`ANNEE_PRO`, `NO_ETUDIANT_NAT`);

--
-- Contraintes pour la table `note_entreprise`
--
ALTER TABLE `note_entreprise`
  ADD CONSTRAINT `FK8mqm2at84yb7095w3uvdvk8lm` FOREIGN KEY (`annee_pro`,`no_etudiant_nat`) REFERENCES `evaluation_entreprise` (`annee_pro`, `no_etudiant_nat`),
  ADD CONSTRAINT `FKti764ha93twb0qbld3ywjs2ba` FOREIGN KEY (`codn_id`) REFERENCES `code_notation` (`codn_id`),
  ADD CONSTRAINT `FKtrwd7r5pba7wq94rof3gryfua` FOREIGN KEY (`CRITE_ID`) REFERENCES `critere_entreprise` (`crite_id`);

--
-- Contraintes pour la table `offre_stage`
--
ALTER TABLE `offre_stage`
  ADD CONSTRAINT `FK6rqppjmo5ddsaec29vw51t8ws` FOREIGN KEY (`no_entreprise`) REFERENCES `entreprise` (`no_entreprise`),
  ADD CONSTRAINT `FKr0uojmcjhfintwd8xu4061vsn` FOREIGN KEY (`annee_pro`) REFERENCES `promotion` (`annee_pro`);

--
-- Contraintes pour la table `poste_entreprise`
--
ALTER TABLE `poste_entreprise`
  ADD CONSTRAINT `FKguvv2ir362cisan3ts3pd5h1k` FOREIGN KEY (`NO_ETUDIANT_NAT`) REFERENCES `diplome` (`no_etudiant_nat`),
  ADD CONSTRAINT `FKtr7mrosr5r1632vsmay6het6p` FOREIGN KEY (`NO_ENTREPRISE`) REFERENCES `entreprise` (`no_entreprise`);

--
-- Contraintes pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `FKbem6u73c3epx5s1fdt97uf13g` FOREIGN KEY (`code_formation`) REFERENCES `formation` (`code_formation`),
  ADD CONSTRAINT `FKcryp2cgc4is7ky41koxgr9uc0` FOREIGN KEY (`no_evaluation`) REFERENCES `structure_evaluation` (`no_evaluation`),
  ADD CONSTRAINT `FKlcylu9f2sqrl7hrvvnko8la0t` FOREIGN KEY (`no_enseignant`) REFERENCES `enseignant` (`no_enseignant`),
  ADD CONSTRAINT `FKq0ewfgdlid4t8qjjln5u0r80u` FOREIGN KEY (`no_bareme`) REFERENCES `notation_stage` (`no_bareme`);

--
-- Contraintes pour la table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `FKenuqmo8ato75nwtmnhvqn8of` FOREIGN KEY (`no_enseignant`) REFERENCES `enseignant` (`no_enseignant`),
  ADD CONSTRAINT `FKl4ler5t8k2w31e88uwr9hiam3` FOREIGN KEY (`id_qualificatif`) REFERENCES `qualificatif` (`id_qualificatif`);

--
-- Contraintes pour la table `question_evaluation`
--
ALTER TABLE `question_evaluation`
  ADD CONSTRAINT `FK7bvyyl9mow6stqpk9un8qnw2n` FOREIGN KEY (`id_qualificatif`) REFERENCES `qualificatif` (`id_qualificatif`),
  ADD CONSTRAINT `FK95saw6ruhtpgwidv4n48qtr8l` FOREIGN KEY (`id_rubrique_evaluation`) REFERENCES `rubrique_evaluation` (`id_rubrique_evaluation`),
  ADD CONSTRAINT `FKh584khu4508arn65wv9khiikv` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`);

--
-- Contraintes pour la table `reponse_evaluation`
--
ALTER TABLE `reponse_evaluation`
  ADD CONSTRAINT `FKlk5perpahqbwartij4fmiwl05` FOREIGN KEY (`no_etudiant_nat`) REFERENCES `etudiant` (`no_etudiant_nat`),
  ADD CONSTRAINT `FKlrbksy6fsgb03iak3dk0w42oo` FOREIGN KEY (`id_evaluation`) REFERENCES `evaluation` (`id_evaluation`);

--
-- Contraintes pour la table `reponse_question`
--
ALTER TABLE `reponse_question`
  ADD CONSTRAINT `FK9h3ucdpex2fiy0jmnjnii64y8` FOREIGN KEY (`ID_QUESTION_EVALUATION`) REFERENCES `question_evaluation` (`id_question_evaluation`),
  ADD CONSTRAINT `FKeaf555xyssr4derke8cbchp3o` FOREIGN KEY (`ID_REPONSE_QUESTION`) REFERENCES `reponse_evaluation` (`id_reponse_evaluation`);

--
-- Contraintes pour la table `rubrique`
--
ALTER TABLE `rubrique`
  ADD CONSTRAINT `FKq2uic5c6upre2u11c867e5pli` FOREIGN KEY (`no_enseignant`) REFERENCES `enseignant` (`no_enseignant`);

--
-- Contraintes pour la table `rubrique_evaluation`
--
ALTER TABLE `rubrique_evaluation`
  ADD CONSTRAINT `FK20jvujg1dvmy0s0tg6cd0u0j9` FOREIGN KEY (`id_rubrique`) REFERENCES `rubrique` (`id_rubrique`),
  ADD CONSTRAINT `FKlu5sx4yfycfa11noogcn0kacm` FOREIGN KEY (`id_evaluation`) REFERENCES `evaluation` (`id_evaluation`);

--
-- Contraintes pour la table `rubrique_question`
--
ALTER TABLE `rubrique_question`
  ADD CONSTRAINT `FK3hu52bbx0ayvbcsdmqt7k2uh5` FOREIGN KEY (`ID_QUESTION`) REFERENCES `question` (`id_question`),
  ADD CONSTRAINT `FKbi4l32ekwlji8t9juvdqvae20` FOREIGN KEY (`ID_RUBRIQUE`) REFERENCES `rubrique` (`id_rubrique`);

--
-- Contraintes pour la table `soutenance`
--
ALTER TABLE `soutenance`
  ADD CONSTRAINT `FKb7hd46taw70hcf5c6je99j4vf` FOREIGN KEY (`no_enseignant_assesseur`) REFERENCES `enseignant` (`no_enseignant`),
  ADD CONSTRAINT `FKj4qf1or5n1muqb2rhcs7jwjvd` FOREIGN KEY (`no_enseignant_responsable`) REFERENCES `enseignant` (`no_enseignant`),
  ADD CONSTRAINT `FKmr2tpq77iyktqgo0hu8jy0bo3` FOREIGN KEY (`ANNEE_PRO`) REFERENCES `promotion` (`annee_pro`);

--
-- Contraintes pour la table `stage`
--
ALTER TABLE `stage`
  ADD CONSTRAINT `FK3vsuu65gnm3xxuosuj83qk8dr` FOREIGN KEY (`ANNEE_PRO`,`no_session`) REFERENCES `soutenance` (`ANNEE_PRO`, `no_session`),
  ADD CONSTRAINT `FKa2atq8gbvbp6mlvlg1phwao8g` FOREIGN KEY (`ANNEE_PRO`) REFERENCES `promotion` (`annee_pro`),
  ADD CONSTRAINT `FKhtsrcl55dukmhir4gb9vapdrd` FOREIGN KEY (`no_enseignant`) REFERENCES `enseignant` (`no_enseignant`),
  ADD CONSTRAINT `FKkkxcii2nk50bvw9n3aglcywlp` FOREIGN KEY (`no_entreprise`) REFERENCES `entreprise` (`no_entreprise`),
  ADD CONSTRAINT `FKq732upmlkef2ysamrigd7f9dt` FOREIGN KEY (`no_offre`) REFERENCES `offre_stage` (`no_offre`),
  ADD CONSTRAINT `FKtjd3nhk1h6kvwhikbrgysgimv` FOREIGN KEY (`NO_ETUDIANT_NAT`) REFERENCES `etudiant` (`no_etudiant_nat`),
  ADD CONSTRAINT `FKtnphgp84js5r3ipcwfg0ra36c` FOREIGN KEY (`no_contact_ili`) REFERENCES `employe` (`no_contact_ili`);

--
-- Contraintes pour la table `unite_enseignement`
--
ALTER TABLE `unite_enseignement`
  ADD CONSTRAINT `FK144kbyvq4xmg673abyy35ee50` FOREIGN KEY (`no_enseignant`) REFERENCES `enseignant` (`no_enseignant`),
  ADD CONSTRAINT `FKtncs5cp8xgnt0p078xa1oijlp` FOREIGN KEY (`code_formation`) REFERENCES `formation` (`code_formation`);

--
-- Contraintes pour la table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `visite_stage`
--
ALTER TABLE `visite_stage`
  ADD CONSTRAINT `FK2r8f9hr6p678t3jo1bm6dbavv` FOREIGN KEY (`no_contact_ili`) REFERENCES `employe` (`no_contact_ili`),
  ADD CONSTRAINT `FKak1vadpjievcxhj8sd0ljy2j8` FOREIGN KEY (`annee_pro`,`no_etudiant_nat`) REFERENCES `stage` (`ANNEE_PRO`, `NO_ETUDIANT_NAT`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
