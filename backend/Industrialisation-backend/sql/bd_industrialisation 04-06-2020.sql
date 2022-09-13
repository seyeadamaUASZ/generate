-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 04 juin 2020 à 19:06
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bd_industrialisation`
--

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(179);

-- --------------------------------------------------------

--
-- Structure de la table `td_application`
--

DROP TABLE IF EXISTS `td_application`;
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
  `parametre` bigint(20) DEFAULT NULL,
  `td_utilisateur` bigint(20) DEFAULT NULL,
  `app_uti_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`app_id`),
  KEY `FK93gnirnr6ysh0kigwx3sjllkj` (`td_utilisateur`),
  KEY `FKntrr02r9478rxdclls82qp30x` (`app_uti_id`),
  KEY `FK4fwrl8rd0djov1wph8yt8q2wy` (`parametre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `td_champs`
--

DROP TABLE IF EXISTS `td_champs`;
CREATE TABLE IF NOT EXISTS `td_champs` (
  `chp_id` bigint(20) NOT NULL,
  `chp_class` varchar(255) DEFAULT NULL,
  `chp_label` varchar(255) DEFAULT NULL,
  `chp_nom` varchar(255) DEFAULT NULL,
  `chp_obligatoire` bit(1) DEFAULT NULL,
  `chp_placeholder` varchar(255) DEFAULT NULL,
  `chp_taille` double DEFAULT NULL,
  `chp_type` varchar(255) DEFAULT NULL,
  `ch_frm_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`chp_id`),
  KEY `FK154f4yyc4v4twupxnwmhfg30a` (`ch_frm_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `td_historique_session`
--

DROP TABLE IF EXISTS `td_historique_session`;
CREATE TABLE IF NOT EXISTS `td_historique_session` (
  `hse_id` bigint(20) NOT NULL,
  `hse_date_connexion` datetime DEFAULT NULL,
  `hse_ip` varchar(255) DEFAULT NULL,
  `hse_login` varchar(255) DEFAULT NULL,
  `hse_uti_id` bigint(20) DEFAULT NULL,
  `hse_date_decconnexion` datetime DEFAULT NULL,
  `hse_ses_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`hse_id`),
  KEY `FKaq8adclu3qsi25qmpvabb3pqv` (`hse_uti_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `td_historique_session`
--

INSERT INTO `td_historique_session` (`hse_id`, `hse_date_connexion`, `hse_ip`, `hse_login`, `hse_uti_id`, `hse_date_decconnexion`, `hse_ses_id`) VALUES
(1, '2020-05-12 10:06:01', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(3, '2020-05-12 10:06:13', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(5, '2020-05-12 10:10:49', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(7, '2020-05-12 10:14:50', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(9, '2020-05-14 08:24:02', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(11, '2020-05-19 08:19:19', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(13, '2020-05-19 08:43:25', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(15, '2020-05-19 09:03:15', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(17, '2020-05-19 09:07:21', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(19, '2020-05-19 09:08:52', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(21, '2020-05-19 09:11:04', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(23, '2020-05-19 09:13:12', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(25, '2020-05-19 09:16:56', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(27, '2020-05-19 09:20:34', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(29, '2020-05-19 09:26:58', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(31, '2020-05-19 09:38:00', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(33, '2020-05-19 09:39:19', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(35, '2020-05-19 09:41:07', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(37, '2020-05-19 09:47:56', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(39, '2020-05-19 09:49:25', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(41, '2020-05-19 09:53:08', 'G2K-DDI-DEV5/10.3.20.127', 'maman', 1, NULL, NULL),
(44, '2020-05-19 10:39:30', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, NULL, NULL),
(46, '2020-05-19 10:57:01', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, NULL, NULL),
(48, '2020-05-19 11:03:48', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, NULL, NULL),
(2, '2020-06-02 13:38:46', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-02 13:42:37', 1),
(50, '2020-06-02 13:45:55', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, NULL, 49),
(53, '2020-06-02 14:50:13', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-02 15:49:19', 52),
(55, '2020-06-02 15:58:07', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-02 15:59:33', 54),
(57, '2020-06-02 15:59:47', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-02 16:06:04', 56),
(59, '2020-06-02 16:06:13', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, NULL, 58),
(61, '2020-06-04 09:21:39', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 09:38:17', 60),
(63, '2020-06-04 09:38:33', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 09:58:18', 62),
(66, '2020-06-04 09:58:26', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 10:36:03', 65),
(68, '2020-06-04 10:38:17', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:00:22', 67),
(71, '2020-06-04 11:00:32', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:08:24', 70),
(73, '2020-06-04 11:08:36', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:16:03', 72),
(75, '2020-06-04 11:16:09', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:21:22', 74),
(77, '2020-06-04 11:21:35', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:23:02', 76),
(79, '2020-06-04 11:23:23', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:25:59', 78),
(81, '2020-06-04 11:26:09', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:27:49', 80),
(83, '2020-06-04 11:27:58', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:31:57', 82),
(85, '2020-06-04 11:32:08', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:37:01', 84),
(87, '2020-06-04 11:37:10', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:41:53', 86),
(89, '2020-06-04 11:42:01', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:42:17', 88),
(91, '2020-06-04 11:42:26', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:43:16', 90),
(93, '2020-06-04 11:43:22', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:43:47', 92),
(96, '2020-06-04 11:43:53', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:47:37', 95),
(98, '2020-06-04 11:47:43', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 11:57:48', 97),
(100, '2020-06-04 11:57:55', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:02:07', 99),
(102, '2020-06-04 12:02:19', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:04:46', 101),
(104, '2020-06-04 12:04:52', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:06:21', 103),
(106, '2020-06-04 12:06:29', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:07:26', 105),
(108, '2020-06-04 12:07:32', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:08:56', 107),
(110, '2020-06-04 12:09:02', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:09:32', 109),
(112, '2020-06-04 12:09:38', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:13:45', 111),
(114, '2020-06-04 12:13:51', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:17:40', 113),
(116, '2020-06-04 12:17:46', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:20:26', 115),
(118, '2020-06-04 12:20:32', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:24:50', 117),
(120, '2020-06-04 12:25:04', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:25:39', 119),
(122, '2020-06-04 12:25:46', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, NULL, 121),
(124, '2020-06-04 12:40:46', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:41:36', 123),
(126, '2020-06-04 12:42:10', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:43:59', 125),
(128, '2020-06-04 12:44:10', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:44:45', 127),
(131, '2020-06-04 12:44:51', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:45:33', 130),
(134, '2020-06-04 12:45:40', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:48:18', 133),
(137, '2020-06-04 12:48:24', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:49:24', 136),
(140, '2020-06-04 12:49:31', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:58:10', 139),
(142, '2020-06-04 12:58:21', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 12:59:55', 141),
(144, '2020-06-04 13:00:03', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 13:00:33', 143),
(147, '2020-06-04 13:00:40', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 13:38:57', 146),
(149, '2020-06-04 14:15:48', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 14:29:19', 148),
(151, '2020-06-04 14:29:31', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 14:30:04', 150),
(154, '2020-06-04 14:30:10', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 14:55:53', 153),
(156, '2020-06-04 14:58:51', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, NULL, 155),
(158, '2020-06-04 16:04:32', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 16:51:21', 157),
(160, '2020-06-04 18:39:02', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 18:40:49', 159),
(162, '2020-06-04 18:44:54', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 18:46:29', 161),
(164, '2020-06-04 18:46:37', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 18:47:44', 163),
(166, '2020-06-04 18:47:52', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 18:48:54', 165),
(168, '2020-06-04 18:49:18', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 18:51:37', 167),
(170, '2020-06-04 18:51:43', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 18:51:54', 169),
(172, '2020-06-04 18:52:06', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 18:53:06', 171),
(174, '2020-06-04 18:53:20', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 18:54:33', 173),
(176, '2020-06-04 18:54:45', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, '2020-06-04 18:58:34', 175),
(178, '2020-06-04 18:58:51', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42, NULL, 177);

-- --------------------------------------------------------

--
-- Structure de la table `td_image`
--

DROP TABLE IF EXISTS `td_image`;
CREATE TABLE IF NOT EXISTS `td_image` (
  `par_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `par_logo_byte` blob,
  `par_name` varchar(255) DEFAULT NULL,
  `par_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`par_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `td_password_recover`
--

DROP TABLE IF EXISTS `td_password_recover`;
CREATE TABLE IF NOT EXISTS `td_password_recover` (
  `pwr_id` bigint(20) NOT NULL,
  `pwr_date` datetime DEFAULT NULL,
  `pwr_email` varchar(255) DEFAULT NULL,
  `pwr_reset_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pwr_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `td_session`
--

DROP TABLE IF EXISTS `td_session`;
CREATE TABLE IF NOT EXISTS `td_session` (
  `ses_id` bigint(20) NOT NULL,
  `ses_date_connexion` datetime DEFAULT NULL,
  `ses_ip` varchar(255) DEFAULT NULL,
  `ses_login` varchar(255) DEFAULT NULL,
  `ses_uti_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ses_id`),
  UNIQUE KEY `ses_uti_id` (`ses_uti_id`),
  KEY `FKlnu9noaam2e02705x5jwwmq2` (`ses_uti_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `td_session`
--

INSERT INTO `td_session` (`ses_id`, `ses_date_connexion`, `ses_ip`, `ses_login`, `ses_uti_id`) VALUES
(177, '2020-06-04 18:58:51', 'G2K-DDI-DEV5/10.3.20.127', 'pape', 42);

-- --------------------------------------------------------

--
-- Structure de la table `td_utilisateur`
--

DROP TABLE IF EXISTS `td_utilisateur`;
CREATE TABLE IF NOT EXISTS `td_utilisateur` (
  `uti_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uti_adresse` varchar(255) DEFAULT NULL,
  `uti_couleur` varchar(255) DEFAULT NULL,
  `uti_date_creation` datetime DEFAULT NULL,
  `uti_date_modification` datetime DEFAULT NULL,
  `uti_email` varchar(255) DEFAULT NULL,
  `uti_nom` varchar(255) DEFAULT NULL,
  `uti_password` varchar(255) DEFAULT NULL,
  `uti_prenom` varchar(255) DEFAULT NULL,
  `uti_telephone` varchar(255) DEFAULT NULL,
  `uti_app_id` bigint(20) DEFAULT NULL,
  `uti_username` varchar(50) NOT NULL,
  `uti_actived` bit(1) DEFAULT NULL,
  `uti_premier_connect` bit(1) DEFAULT NULL,
  `uti_pro_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uti_id`),
  KEY `FKpmirdtjefa6f08v07qpq03mqg` (`uti_app_id`),
  KEY `FKdj5vdsyttvg97kbslgvq4d1g1` (`uti_pro_id`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `td_utilisateur`
--

INSERT INTO `td_utilisateur` (`uti_id`, `uti_adresse`, `uti_couleur`, `uti_date_creation`, `uti_date_modification`, `uti_email`, `uti_nom`, `uti_password`, `uti_prenom`, `uti_telephone`, `uti_app_id`, `uti_username`, `uti_actived`, `uti_premier_connect`, `uti_pro_id`) VALUES
(42, 'pikine', NULL, NULL, NULL, '@gainde2000.sn', 'Fall', '$2a$10$YMWlyVAurfh201n8Nnrf8uD9NP.P6QXtaaJ1.pDXEszBmKFZmVwO6', 'pape', '774526548', NULL, 'pape', b'1', b'0', 1);

-- --------------------------------------------------------

--
-- Structure de la table `tp_action`
--

DROP TABLE IF EXISTS `tp_action`;
CREATE TABLE IF NOT EXISTS `tp_action` (
  `act_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `act_code` CHAR(20) DEFAULT NULL,
  `act_description` varchar(255) DEFAULT NULL,
  `act_nom` varchar(255) DEFAULT NULL,
  `act_men_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`act_id`),
  KEY `FKvisktsit802fxeyqm10cvmtq` (`act_men_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_fonctionnalite`
--

DROP TABLE IF EXISTS `tp_fonctionnalite`;
CREATE TABLE IF NOT EXISTS `tp_fonctionnalite` (
  `fon_id` bigint(20) NOT NULL,
  `fon_code` int(11) DEFAULT NULL,
  `fon_description` varchar(255) DEFAULT NULL,
  `fon_nom` varchar(255) DEFAULT NULL,
  `fon_men_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`fon_id`),
  KEY `FKqjxbnos8w3rsdx6rbofnf27vr` (`fon_men_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_formulaire`
--

DROP TABLE IF EXISTS `tp_formulaire`;
CREATE TABLE IF NOT EXISTS `tp_formulaire` (
  `frm_id` bigint(20) NOT NULL,
  `frm_description` varchar(255) DEFAULT NULL,
  `frm_nom` varchar(255) DEFAULT NULL,
  `frm_app_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`frm_id`),
  KEY `FKoaw9c7vtioiexdsm6sbj2jxhb` (`frm_app_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_langue`
--

DROP TABLE IF EXISTS `tp_langue`;
CREATE TABLE IF NOT EXISTS `tp_langue` (
  `lng_id` bigint(20) NOT NULL,
  `lng_code` varchar(255) DEFAULT NULL,
  `lng_disposition_text` varchar(255) DEFAULT NULL,
  `lng_langue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`lng_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tp_langue`
--

INSERT INTO `tp_langue` (`lng_id`, `lng_code`, `lng_disposition_text`, `lng_langue`) VALUES
(1, '221', '0', 'fr'),
(2, '222', '0', 'es'),
(3, '224', '0', 'en');

-- --------------------------------------------------------

--
-- Structure de la table `tp_menu`
--

DROP TABLE IF EXISTS `tp_menu`;
CREATE TABLE IF NOT EXISTS `tp_menu` (
  `men_id` bigint(20) NOT NULL,
  `men_icone` varchar(255) DEFAULT NULL,
  `men_id_parent` bigint(20) DEFAULT NULL,
  `men_nom` varchar(255) DEFAULT NULL,
  `men_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`men_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tp_menu`
--

INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_id_parent`, `men_nom`, `men_path`) VALUES
(1, 'portrait', NULL, 'Gestion des applications', 'utilisateur'),
(2, 'vpn_key', NULL, 'Gestion des droits d\'accès', 'utilisateur/acces'),
(3, 'supervisor_account', NULL, 'Gestion des utilisateurs', 'utilisateur'),
(4, 'calendar_today', NULL, 'Gestion des formulaires', 'formulaire'),
(5, 'compare_arrows', NULL, 'Gestion des workflows', 'workflow');

-- --------------------------------------------------------

--
-- Structure de la table `tp_module`
--

DROP TABLE IF EXISTS `tp_module`;
CREATE TABLE IF NOT EXISTS `tp_module` (
  `mod_id` bigint(20) NOT NULL,
  `mod_code` varchar(255) DEFAULT NULL,
  `mod_nom` varchar(255) DEFAULT NULL,
  `mod_app_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`mod_id`),
  KEY `FK3kjjlul4u3sva3ko7d6wu8w2s` (`mod_app_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_moyen_paiement`
--

DROP TABLE IF EXISTS `tp_moyen_paiement`;
CREATE TABLE IF NOT EXISTS `tp_moyen_paiement` (
  `mpa_id` bigint(20) NOT NULL,
  `mpa_code` varchar(255) DEFAULT NULL,
  `mpa_libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mpa_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_parametre`
--

DROP TABLE IF EXISTS `tp_parametre`;
CREATE TABLE IF NOT EXISTS `tp_parametre` (
  `param_id` int(11) NOT NULL AUTO_INCREMENT,
  `param_uti_username` varchar(250) NOT NULL,
  `param_lng_id` bigint(20) DEFAULT NULL,
  `param_thm_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`param_id`),
  KEY `FKa01bp485qcaltg0mfgk7hine0` (`param_lng_id`),
  KEY `FKkd6s4maj4fcu6wrbitut4exum` (`param_thm_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tp_parametre`
--

INSERT INTO `tp_parametre` (`param_id`, `param_uti_username`, `param_lng_id`, `param_thm_id`) VALUES
(1, 'pape', 2, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `tp_pays`
--

DROP TABLE IF EXISTS `tp_pays`;
CREATE TABLE IF NOT EXISTS `tp_pays` (
  `pys_id` bigint(20) NOT NULL,
  `pys_code` varchar(255) DEFAULT NULL,
  `pys_icone` varchar(255) DEFAULT NULL,
  `pys_nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pys_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_profil`
--

DROP TABLE IF EXISTS `tp_profil`;
CREATE TABLE IF NOT EXISTS `tp_profil` (
  `pro_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pro_description` varchar(255) DEFAULT NULL,
  `pro_libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pro_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tp_profil`
--

INSERT INTO `tp_profil` (`pro_id`, `pro_description`, `pro_libelle`) VALUES
(1, 'profile intégrateur', 'Integrateur'),
(2, 'l\'administrateur', 'Administarteur');

-- --------------------------------------------------------

--
-- Structure de la table `tp_pwd_criteria`
--

DROP TABLE IF EXISTS `tp_pwd_criteria`;
CREATE TABLE IF NOT EXISTS `tp_pwd_criteria` (
  `pwd_id` bigint(20) NOT NULL,
  `pwd_car_min` varchar(20) DEFAULT NULL,
  `pwd_dig_min` varchar(255) DEFAULT NULL,
  `pwd_maj_min` varchar(255) DEFAULT NULL,
  `pwd_spc_min` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pwd_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_theme`
--

DROP TABLE IF EXISTS `tp_theme`;
CREATE TABLE IF NOT EXISTS `tp_theme` (
  `thm_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `thm_primary` varchar(250) NOT NULL,
  `thm_accent` varchar(250) NOT NULL,
  `thm_name` varchar(250) NOT NULL,
  `thm_isDark` tinyint(1) DEFAULT NULL,
  `thm_isDefault` tinyint(1) DEFAULT NULL,
  `thm_is_dark` bit(1) DEFAULT NULL,
  `thm_is_default` bit(1) DEFAULT NULL,
  PRIMARY KEY (`thm_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_workflow`
--

DROP TABLE IF EXISTS `tp_workflow`;
CREATE TABLE IF NOT EXISTS `tp_workflow` (
  `wkf_id` bigint(20) NOT NULL,
  `wkf_artifact_id` varchar(255) DEFAULT NULL,
  `wkf_conteneur` varchar(255) DEFAULT NULL,
  `wkf_nom` varchar(255) DEFAULT NULL,
  `wkf_app_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`wkf_id`),
  KEY `FKy2k1seyrf98yjnluoeeopms5` (`wkf_app_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tr_privilege`
--

DROP TABLE IF EXISTS `tr_privilege`;
CREATE TABLE IF NOT EXISTS `tr_privilege` (
  	`pri_pro_id` BIGINT(20) NOT NULL,
	`pri_act_id` BIGINT(20) NOT NULL,
	`pri_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`pri_id`),
	INDEX `FK7n73a95fj53acddftc9ynatah` (`pri_act_id`),
	INDEX `FKysl2xd67dlurqrbpmhhgcsap` (`pri_pro_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
