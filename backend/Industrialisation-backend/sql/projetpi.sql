-- --------------------------------------------------------
-- Hôte :                        localhost
-- Version du serveur:           5.7.24 - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Listage de la structure de la base pour bd_industrialisation
CREATE DATABASE IF NOT EXISTS `bd_industrialisation` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bd_industrialisation`;

-- Listage de la structure de la table bd_industrialisation. td_application
CREATE TABLE IF NOT EXISTS `td_application` (
  `app_id` bigint(20) NOT NULL,
  `app_adresse` varchar(255) DEFAULT NULL,
  `app_date_creation` datetime DEFAULT NULL,
  `app_email` varchar(255) DEFAULT NULL,
  `app_ninea` varchar(255) DEFAULT NULL,
  `app_nom` varchar(255) DEFAULT NULL,
  `app_nom_entreprise` varchar(255) DEFAULT NULL,
  `app_secteur` varchar(255) DEFAULT NULL,
  `app_telephone_fixe` varchar(255) DEFAULT NULL,
  `app_telephone_mobile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`app_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.td_application : 0 rows
/*!40000 ALTER TABLE `td_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `td_application` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. td_champs
CREATE TABLE IF NOT EXISTS `td_champs` (
  `chp_id` bigint(20) NOT NULL,
  `ch_frm_id` decimal(19,2) DEFAULT NULL,
  `chp_class` varchar(255) DEFAULT NULL,
  `chp_label` varchar(255) DEFAULT NULL,
  `chp_nom` varchar(255) DEFAULT NULL,
  `chp_obligatoire` bit(1) DEFAULT NULL,
  `chp_placeholder` varchar(255) DEFAULT NULL,
  `chp_taille` double DEFAULT NULL,
  `chp_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`chp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.td_champs : 0 rows
/*!40000 ALTER TABLE `td_champs` DISABLE KEYS */;
/*!40000 ALTER TABLE `td_champs` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. td_historique_session
CREATE TABLE IF NOT EXISTS `td_historique_session` (
  `hse_id` bigint(20) NOT NULL,
  `hse_date_connexion` datetime DEFAULT NULL,
  `hse_ip` varchar(255) DEFAULT NULL,
  `hse_login` varchar(255) DEFAULT NULL,
  `hse_uti_id` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`hse_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.td_historique_session : 0 rows
/*!40000 ALTER TABLE `td_historique_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `td_historique_session` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. td_session
CREATE TABLE IF NOT EXISTS `td_session` (
  `ses_id` bigint(20) NOT NULL,
  `ses_date_connexion` datetime DEFAULT NULL,
  `ses_ip` varchar(255) DEFAULT NULL,
  `ses_login` varchar(255) DEFAULT NULL,
  `ses_uti_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ses_id`),
  KEY `FKlnu9noaam2e02705x5jwwmq2` (`ses_uti_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.td_session : 0 rows
/*!40000 ALTER TABLE `td_session` DISABLE KEYS */;
INSERT INTO `td_session` (`ses_id`, `ses_date_connexion`, `ses_ip`, `ses_login`, `ses_uti_id`) VALUES
	(1, '2020-05-07 13:08:36', '127.0.0.1', 'maman', 1),
	(2, '2020-05-07 13:10:55', 'G2K-DDI-DEV1/10.3.20.63', 'maman', 1),
	(3, '2020-05-07 13:32:23', 'G2K-DDI-DEV1/10.3.20.63', 'maman', 1),
	(15, '2020-05-07 15:41:13', 'G2K-DDI-DEV1/10.3.20.63', 'maman', 1);
/*!40000 ALTER TABLE `td_session` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. td_utilisateur
CREATE TABLE IF NOT EXISTS `td_utilisateur` (
  `uti_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uti_adresse` varchar(255) DEFAULT NULL,
  `uti_couleur` varchar(255) DEFAULT NULL,
  `uti_date_creation` datetime DEFAULT NULL,
  `uti_date_modification` datetime DEFAULT NULL,
  `uti_email` varchar(255) DEFAULT NULL,
  `uti_langue` varchar(255) DEFAULT NULL,
  `uti_nom` varchar(255) DEFAULT NULL,
  `uti_password` varchar(255) DEFAULT NULL,
  `uti_prenom` varchar(255) DEFAULT NULL,
  `uti_telephone` varchar(255) DEFAULT NULL,
  `uti_theme` varchar(255) DEFAULT NULL,
  `uti_app_id` bigint(20) DEFAULT NULL,
  `uti_pro_id` bigint(20) DEFAULT NULL,  
  `uti_username` varchar(50) NOT NULL,
  `uti_actived` bit(1) DEFAULT NULL,
  PRIMARY KEY (`uti_id`),
  KEY `FKpmirdtjefa6f08v07qpq03mqg` (`uti_app_id`),
  KEY `FKihrvrd54cf0qiw3i89k3kbeax` (`tp_profil`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.td_utilisateur : 1 rows
/*!40000 ALTER TABLE `td_utilisateur` DISABLE KEYS */;
INSERT INTO `td_utilisateur` (`uti_id`, `uti_adresse`, `uti_couleur`, `uti_date_creation`, `uti_date_modification`, `uti_email`, `uti_langue`, `uti_nom`, `uti_password`, `uti_prenom`, `uti_telephone`, `uti_theme`, `uti_app_id`, `uti_username`, `tp_profil`, `uti_actived`) VALUES
	(1, 'Dakar', NULL, '2020-04-09 00:00:00', NULL, 'omar@gainde2000.sn', NULL, 'fall', '$2a$10$.wAGlrdl7yMx0izl7.F5gOYGxw9BgM3rVQI9DIzYj28bXM6ZGEQb.', 'Omar', '221772255452', NULL, NULL, 'maman', 1, b'1'),
	(17, 'yhhoff', NULL, '2020-05-07 15:47:37', NULL, 'test@y.com', NULL, 'Diohhp', '$2a$10$nksP9Fr2Yh.8aWcg3Pi7lOteiGVwHD.BjXilwm3xURrQY36wlI7me', 'Mohhhdou', '77777777777', NULL, NULL, 'modou92', NULL, b'1');
/*!40000 ALTER TABLE `td_utilisateur` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. tp_action
CREATE TABLE IF NOT EXISTS `tp_action` (
  `act_id` bigint(20) NOT NULL,
  `act_code` CHAR(20) DEFAULT NULL,
  `act_description` varchar(255) DEFAULT NULL,
  `act_nom` varchar(255) DEFAULT NULL,
  `act_men_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`act_id`),
  KEY `FKvisktsit802fxeyqm10cvmtq` (`act_men_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.tp_action : 0 rows
/*!40000 ALTER TABLE `tp_action` DISABLE KEYS */;
/*!40000 ALTER TABLE `tp_action` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. tp_fonctionnalite
CREATE TABLE IF NOT EXISTS `tp_fonctionnalite` (
  `fon_id` bigint(20) NOT NULL,
  `fon_code` int(11) DEFAULT NULL,
  `fon_description` varchar(255) DEFAULT NULL,
  `fon_nom` varchar(255) DEFAULT NULL,
  `fon_men_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`fon_id`),
  KEY `FKqjxbnos8w3rsdx6rbofnf27vr` (`fon_men_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.tp_fonctionnalite : 0 rows
/*!40000 ALTER TABLE `tp_fonctionnalite` DISABLE KEYS */;
/*!40000 ALTER TABLE `tp_fonctionnalite` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. tp_formulaire
CREATE TABLE IF NOT EXISTS `tp_formulaire` (
  `frm_id` bigint(20) NOT NULL,
  `frm_description` varchar(255) DEFAULT NULL,
  `frm_nom` varchar(255) DEFAULT NULL,
  `frm_app_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`frm_id`),
  KEY `FKoaw9c7vtioiexdsm6sbj2jxhb` (`frm_app_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.tp_formulaire : 0 rows
/*!40000 ALTER TABLE `tp_formulaire` DISABLE KEYS */;
/*!40000 ALTER TABLE `tp_formulaire` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. tp_langue
CREATE TABLE IF NOT EXISTS `tp_langue` (
  `lng_id` bigint(20) NOT NULL,
  `lng_code` varchar(255) DEFAULT NULL,
  `lng_disposition_text` varchar(255) DEFAULT NULL,
  `lng_langue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`lng_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.tp_langue : 0 rows
/*!40000 ALTER TABLE `tp_langue` DISABLE KEYS */;
/*!40000 ALTER TABLE `tp_langue` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. tp_menu
CREATE TABLE IF NOT EXISTS `tp_menu` (
  `men_id` bigint(20) NOT NULL,
  `men_icone` varchar(255) DEFAULT NULL,
  `men_id_parent` bigint(20) DEFAULT NULL,
  `men_nom` varchar(255) DEFAULT NULL,
  `men_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`men_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.tp_menu : 5 rows
/*!40000 ALTER TABLE `tp_menu` DISABLE KEYS */;
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_id_parent`, `men_nom`, `men_path`) VALUES
	(1, 'portrait', NULL, 'Gestion des applications', 'utilisateur'),
	(2, 'vpn_key', NULL, 'Gestion des droits d\'accès', 'utilisateur/acces'),
	(3, 'supervisor_account', NULL, 'Gestion des utilisateurs', 'utilisateur'),
	(4, 'calendar_today', NULL, 'Gestion des formulaires', 'formulaire'),
	(5, 'compare_arrows', NULL, 'Gestion des workflows', 'workflow');
/*!40000 ALTER TABLE `tp_menu` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. tp_module
CREATE TABLE IF NOT EXISTS `tp_module` (
  `mod_id` bigint(20) NOT NULL,
  `mod_code` varchar(255) DEFAULT NULL,
  `mod_nom` varchar(255) DEFAULT NULL,
  `mod_app_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`mod_id`),
  KEY `FK3kjjlul4u3sva3ko7d6wu8w2s` (`mod_app_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.tp_module : 0 rows
/*!40000 ALTER TABLE `tp_module` DISABLE KEYS */;
/*!40000 ALTER TABLE `tp_module` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. tp_moyen_paiement
CREATE TABLE IF NOT EXISTS `tp_moyen_paiement` (
  `mpa_id` bigint(20) NOT NULL,
  `mpa_code` varchar(255) DEFAULT NULL,
  `mpa_libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mpa_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.tp_moyen_paiement : 0 rows
/*!40000 ALTER TABLE `tp_moyen_paiement` DISABLE KEYS */;
/*!40000 ALTER TABLE `tp_moyen_paiement` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. tp_pays
CREATE TABLE IF NOT EXISTS `tp_pays` (
  `pys_id` bigint(20) NOT NULL,
  `pys_code` varchar(255) DEFAULT NULL,
  `pys_icone` varchar(255) DEFAULT NULL,
  `pys_nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pys_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.tp_pays : 0 rows
/*!40000 ALTER TABLE `tp_pays` DISABLE KEYS */;
/*!40000 ALTER TABLE `tp_pays` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. tp_profil
CREATE TABLE IF NOT EXISTS `tp_profil` (
  `pro_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pro_description` varchar(255) DEFAULT NULL,
  `pro_libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pro_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.tp_profil : 2 rows
/*!40000 ALTER TABLE `tp_profil` DISABLE KEYS */;
INSERT INTO `tp_profil` (`pro_id`, `pro_description`, `pro_libelle`) VALUES
	(1, 'profile intégrateur', 'Integrateur'),
	(2, 'l\'administrateur', 'Administarteur');
/*!40000 ALTER TABLE `tp_profil` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. tp_workflow
CREATE TABLE IF NOT EXISTS `tp_workflow` (
  `wkf_id` bigint(20) NOT NULL,
  `wkf_artifact_id` varchar(255) DEFAULT NULL,
  `wkf_conteneur` varchar(255) DEFAULT NULL,
  `wkf_nom` varchar(255) DEFAULT NULL,
  `wkf_app_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`wkf_id`),
  KEY `FKy2k1seyrf98yjnluoeeopms5` (`wkf_app_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.tp_workflow : 0 rows
/*!40000 ALTER TABLE `tp_workflow` DISABLE KEYS */;
/*!40000 ALTER TABLE `tp_workflow` ENABLE KEYS */;

-- Listage de la structure de la table bd_industrialisation. tr_privilege
CREATE TABLE IF NOT EXISTS `tr_privilege` (
  	`pri_pro_id` BIGINT(20) NOT NULL,
	`pri_act_id` BIGINT(20) NOT NULL,
	`pri_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`pri_id`),
	INDEX `FK7n73a95fj53acddftc9ynatah` (`pri_act_id`),
	INDEX `FKysl2xd67dlurqrbpmhhgcsap` (`pri_pro_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Listage des données de la table bd_industrialisation.tr_privilege : 0 rows
/*!40000 ALTER TABLE `tr_privilege` DISABLE KEYS */;
/*!40000 ALTER TABLE `tr_privilege` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
