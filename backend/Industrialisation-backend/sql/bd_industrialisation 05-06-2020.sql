-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 05 juin 2020 à 14:36
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
(434);

-- --------------------------------------------------------

--
-- Structure de la table `td_application`
--

DROP TABLE IF EXISTS `td_application`;
CREATE TABLE IF NOT EXISTS `td_application` (
  `app_id` bigint(20) NOT NULL AUTO_INCREMENT,
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
  `tp_utilisateur` bigint(20) DEFAULT NULL,
  `app_uti_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`app_id`),
  KEY `FKee04gxa557ml5eqwamraxlcfn` (`tp_utilisateur`),
  KEY `FKntrr02r9478rxdclls82qp30x` (`app_uti_id`),
  KEY `FK4fwrl8rd0djov1wph8yt8q2wy` (`parametre`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `td_application`
--

INSERT INTO `td_application` (`app_id`, `app_adresse`, `app_date_creation`, `app_email`, `app_ninea`, `app_nom`, `app_nom_entreprise`, `app_secteur`, `app_telephone_fixe`, `app_telephone_mobile`, `parametre`, `tp_utilisateur`, `app_uti_id`) VALUES
(1, 'Dakar', '2020-06-10 00:00:00', '@gainde2000.sn', '12236373434', 'gestion des échanges maritmes', 'flux tech', 'maritime', '221335468765', '2217756480998', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `td_champs`
--

DROP TABLE IF EXISTS `td_champs`;
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

--
-- Déchargement des données de la table `td_champs`
--

INSERT INTO `td_champs` (`chp_id`, `ch_frm_id`, `chp_class`, `chp_label`, `chp_nom`, `chp_obligatoire`, `chp_placeholder`, `chp_taille`, `chp_type`) VALUES
(354, NULL, 'form-control', 'Text', 'text-1591311403099', b'1', 'Enter your name', NULL, 'text'),
(350, NULL, 'form-control', 'Phone', 'phone-1591311475810', b'1', 'Enter your phone', NULL, 'phone'),
(353, NULL, 'form-control', 'Text', 'text-1591311471715', b'1', 'Enter your name', NULL, 'text'),
(355, NULL, 'form-control', 'Email', 'email-1591311413402', b'1', 'Enter your email', NULL, 'email'),
(352, NULL, 'form-control', 'Phone', 'phone-1591311420053', b'1', 'Enter your phone', NULL, 'phone'),
(351, NULL, 'form-control', 'Select', 'autocomplete-1591311495367', NULL, 'Select', NULL, 'autocomplete');

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
  `hse_uti_id` decimal(19,2) DEFAULT NULL,
  `hse_date_decconnexion` datetime DEFAULT NULL,
  `hse_ses_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`hse_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `td_historique_session`
--

INSERT INTO `td_historique_session` (`hse_id`, `hse_date_connexion`, `hse_ip`, `hse_login`, `hse_uti_id`, `hse_date_decconnexion`, `hse_ses_id`) VALUES
(286, '2020-06-01 21:40:10', 'pc/192.168.1.6', 'pape', '2.00', '2020-06-01 21:40:52', 285),
(284, '2020-06-01 21:36:23', 'pc/192.168.1.6', 'pape', '2.00', '2020-06-01 21:39:55', 283),
(282, '2020-06-01 21:13:35', 'pc/192.168.1.24', 'pape', '2.00', '2020-06-01 21:35:57', 281),
(280, '2020-06-01 20:44:25', 'pc/192.168.1.23', 'pape', '2.00', '2020-06-01 21:11:51', 279),
(278, '2020-06-01 03:14:24', 'pc/192.168.1.29', 'pape', '2.00', '2020-06-01 04:20:31', 277),
(276, '2020-06-01 02:29:43', 'pc/192.168.1.27', 'pape', '2.00', NULL, 275),
(274, '2020-05-31 23:36:57', 'pc/192.168.1.4', 'pape', '2.00', '2020-06-01 02:16:35', 273),
(272, '2020-05-31 21:25:09', 'pc/192.168.1.34', 'pape', '2.00', '2020-05-31 21:27:14', 271),
(270, '2020-05-31 21:24:11', 'pc/192.168.1.34', 'pape', '2.00', '2020-05-31 21:24:27', 269),
(268, '2020-05-31 21:23:42', 'pc/192.168.1.34', 'pape', '2.00', '2020-05-31 21:23:49', 267),
(266, '2020-05-31 21:19:33', 'pc/192.168.1.34', 'pape', '2.00', '2020-05-31 21:23:07', 265),
(264, '2020-05-31 21:14:06', 'pc/192.168.1.34', 'pape', '2.00', '2020-05-31 21:19:33', 263),
(250, '2020-05-31 19:27:55', 'pc/192.168.1.30', 'pape', '2.00', '2020-05-31 19:28:05', 249),
(245, '2020-05-31 12:58:14', 'pc/192.168.1.31', 'pape', '2.00', '2020-05-31 13:01:00', 244),
(243, '2020-05-31 12:56:57', 'pc/192.168.1.31', 'pape', '2.00', '2020-05-31 12:57:58', 242),
(241, '2020-05-31 12:54:46', 'pc/192.168.1.31', 'pape', '2.00', '2020-05-31 12:56:42', 240),
(239, '2020-05-31 12:45:25', 'pc/192.168.1.31', 'pape', '2.00', '2020-05-31 12:54:12', 238),
(237, '2020-05-31 12:44:16', 'pc/192.168.1.31', 'pape', '2.00', '2020-05-31 12:45:12', 236),
(288, '2020-06-01 21:41:13', 'pc/192.168.1.6', 'pape', '2.00', '2020-06-01 21:46:39', 287),
(290, '2020-06-01 21:46:51', 'pc/192.168.1.6', 'pape', '2.00', '2020-06-01 21:51:22', 289),
(292, '2020-06-01 21:51:33', 'pc/192.168.1.6', 'pape', '2.00', '2020-06-01 22:07:09', 291),
(294, '2020-06-01 22:07:20', 'pc/192.168.1.6', 'pape', '2.00', '2020-06-01 22:21:35', 293),
(296, '2020-06-01 22:21:45', 'pc/127.0.0.1', 'pape', '2.00', '2020-06-01 22:41:40', 295),
(298, '2020-06-03 10:02:02', 'pc/192.168.1.36', 'pape', '2.00', '2020-06-03 10:03:26', 297),
(300, '2020-06-03 10:03:42', 'pc/192.168.1.36', 'pape', '2.00', '2020-06-03 10:34:02', 299),
(302, '2020-06-03 15:00:52', 'pc/192.168.1.12', 'pape', '2.00', NULL, 301),
(304, '2020-06-03 16:31:22', 'pc/192.168.1.33', 'pape', '2.00', '2020-06-03 17:00:16', 303),
(306, '2020-06-03 17:18:12', 'pc/192.168.1.38', 'pape', '2.00', '2020-06-03 17:42:42', 305),
(309, '2020-06-03 17:49:42', 'pc/192.168.1.38', 'pape', '2.00', NULL, 308),
(329, '2020-06-03 20:44:59', 'pc/192.168.1.57', 'pape', '2.00', '2020-06-03 20:45:39', 328),
(331, '2020-06-03 20:45:54', 'pc/192.168.1.57', 'pape', '2.00', '2020-06-03 20:50:06', 330),
(333, '2020-06-03 21:02:53', 'pc/192.168.1.59', 'pape', '2.00', NULL, 332),
(335, '2020-06-03 21:17:45', 'pc/192.168.1.59', 'pape', '2.00', NULL, 334),
(337, '2020-06-03 22:21:39', 'pc/192.168.1.65', 'pape', '2.00', NULL, 336),
(339, '2020-06-03 22:23:35', 'pc/192.168.1.65', 'pape', '2.00', '2020-06-03 22:26:01', 338),
(341, '2020-06-03 22:26:14', 'pc/192.168.1.65', 'pape', '2.00', '2020-06-03 22:29:30', 340),
(343, '2020-06-03 22:29:56', 'pc/192.168.1.67', 'pape', '2.00', '2020-06-03 22:30:19', 342),
(345, '2020-06-04 22:46:36', 'pc/192.168.1.95', 'pape', '2.00', '2020-06-04 22:48:50', 344),
(347, '2020-06-04 22:49:07', 'pc/192.168.1.95', 'pape', '2.00', '2020-06-04 22:53:18', 346),
(349, '2020-06-04 22:53:29', 'pc/192.168.1.95', 'pape', '2.00', '2020-06-04 23:00:25', 348),
(357, '2020-06-04 23:00:37', 'pc/192.168.1.95', 'pape', '2.00', '2020-06-04 23:18:46', 356),
(359, '2020-06-04 23:23:37', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-04 23:55:09', 358),
(361, '2020-06-04 23:55:24', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-04 23:59:10', 360),
(363, '2020-06-04 23:59:35', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 00:05:41', 362),
(365, '2020-06-05 00:05:51', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 00:18:42', 364),
(367, '2020-06-05 00:18:56', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 00:20:13', 366),
(369, '2020-06-05 00:20:23', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 00:22:48', 368),
(371, '2020-06-05 00:23:09', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 00:23:42', 370),
(373, '2020-06-05 00:24:02', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 00:26:00', 372),
(375, '2020-06-05 00:26:58', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 00:29:20', 374),
(377, '2020-06-05 00:29:39', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 00:33:23', 376),
(379, '2020-06-05 00:33:50', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 00:35:38', 378),
(381, '2020-06-05 00:35:48', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 00:36:26', 380),
(383, '2020-06-05 00:36:38', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 01:58:56', 382),
(385, '2020-06-05 01:59:35', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 02:01:07', 384),
(387, '2020-06-05 02:01:18', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 02:02:23', 386),
(389, '2020-06-05 02:02:35', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 02:06:28', 388),
(391, '2020-06-05 02:06:44', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 02:10:04', 390),
(393, '2020-06-05 02:10:15', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 02:12:16', 392),
(395, '2020-06-05 02:12:27', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 02:16:49', 394),
(397, '2020-06-05 02:17:02', 'pc/192.168.1.98', 'pape', '2.00', NULL, 396),
(399, '2020-06-05 11:06:26', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 11:08:08', 398),
(401, '2020-06-05 11:08:23', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 11:14:52', 400),
(403, '2020-06-05 11:15:23', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 11:21:23', 402),
(405, '2020-06-05 11:22:25', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 11:23:14', 404),
(407, '2020-06-05 11:23:27', 'pc/192.168.1.98', 'pape', '2.00', '2020-06-05 11:30:19', 406),
(409, '2020-06-05 11:30:34', 'pc/192.168.1.101', 'pape', '2.00', '2020-06-05 11:34:00', 408),
(411, '2020-06-05 11:34:10', 'pc/192.168.1.101', 'pape', '2.00', '2020-06-05 11:34:54', 410),
(413, '2020-06-05 11:35:05', 'pc/192.168.1.101', 'pape', '2.00', '2020-06-05 11:35:45', 412),
(415, '2020-06-05 11:35:55', 'pc/192.168.1.101', 'pape', '2.00', '2020-06-05 11:36:11', 414),
(417, '2020-06-05 11:36:30', 'pc/192.168.1.101', 'pape', '2.00', '2020-06-05 11:48:11', 416),
(419, '2020-06-05 11:48:22', 'pc/192.168.1.105', 'pape', '2.00', '2020-06-05 11:49:06', 418),
(421, '2020-06-05 11:49:20', 'pc/192.168.1.105', 'pape', '2.00', '2020-06-05 11:49:43', 420),
(423, '2020-06-05 11:54:56', 'pc/192.168.1.105', 'pape', '2.00', '2020-06-05 11:57:35', 422),
(425, '2020-06-05 12:00:10', 'pc/192.168.1.106', 'pape', '2.00', '2020-06-05 12:02:38', 424),
(427, '2020-06-05 12:03:02', 'pc/192.168.1.106', 'pape', '2.00', '2020-06-05 12:05:05', 426),
(429, '2020-06-05 12:05:21', 'pc/192.168.1.107', 'pape', '2.00', '2020-06-05 12:06:54', 428),
(431, '2020-06-05 12:07:06', 'pc/192.168.1.107', 'pape', '2.00', NULL, 430),
(433, '2020-06-05 12:32:35', 'pc/192.168.1.109', 'pape', '2.00', '2020-06-05 13:25:06', 432);

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
  `ses_uti_id` decimal(19,2) NOT NULL,
  PRIMARY KEY (`ses_id`),
  UNIQUE KEY `ses_uti_id` (`ses_uti_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `td_utilisateur`
--

DROP TABLE IF EXISTS `td_utilisateur`;
CREATE TABLE IF NOT EXISTS `td_utilisateur` (
  `uti_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uti_adresse` varchar(255) DEFAULT NULL,
  `uti_app_id` decimal(19,2) DEFAULT NULL,
  `uti_date_creation` datetime DEFAULT NULL,
  `uti_date_modification` datetime DEFAULT NULL,
  `uti_email` varchar(255) DEFAULT NULL,
  `uti_nom` varchar(255) DEFAULT NULL,
  `uti_password` varchar(255) DEFAULT NULL,
  `uti_prenom` varchar(255) DEFAULT NULL,
  `uti_telephone` varchar(255) DEFAULT NULL,
  `uti_username` varchar(50) DEFAULT NULL,
  `uti_actived` bit(1) DEFAULT NULL,
  `uti_premier_connect` bit(1) DEFAULT NULL,
  `uti_pro_id` bigint(20) DEFAULT NULL,
  `uti_couleur` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uti_id`),
  KEY `FKdj5vdsyttvg97kbslgvq4d1g1` (`uti_pro_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `td_utilisateur`
--

INSERT INTO `td_utilisateur` (`uti_id`, `uti_adresse`, `uti_app_id`, `uti_date_creation`, `uti_date_modification`, `uti_email`, `uti_nom`, `uti_password`, `uti_prenom`, `uti_telephone`, `uti_username`, `uti_actived`, `uti_premier_connect`, `uti_pro_id`, `uti_couleur`) VALUES
(2, 'pikine', NULL, '2020-05-21 00:00:00', '2020-06-03 17:50:23', '@gainde2000', '', '$2a$10$q/kuau7WLiaYidFNF3QtEO5o860/RKJPayVyNV5qv6RNMvNNvc7ZK', 'pape', '772344323', 'pape', b'1', b'0', 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `tp_action`
--

DROP TABLE IF EXISTS `tp_action`;
CREATE TABLE IF NOT EXISTS `tp_action` (
  `act_id` bigint(20) NOT NULL,
  `act_code` int(11) DEFAULT NULL,
  `act_description` varchar(255) DEFAULT NULL,
  `act_men_id` decimal(19,2) DEFAULT NULL,
  `act_nom` varchar(255) DEFAULT NULL,
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
  `fon_men_id` decimal(19,2) DEFAULT NULL,
  `fon_nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fon_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_formulaire`
--

DROP TABLE IF EXISTS `tp_formulaire`;
CREATE TABLE IF NOT EXISTS `tp_formulaire` (
  `frm_id` bigint(20) NOT NULL,
  `frm_app_id` decimal(19,2) DEFAULT NULL,
  `frm_description` varchar(255) DEFAULT NULL,
  `frm_nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`frm_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_langue`
--

DROP TABLE IF EXISTS `tp_langue`;
CREATE TABLE IF NOT EXISTS `tp_langue` (
  `lng_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lng_code` varchar(255) DEFAULT NULL,
  `lng_disposition_text` varchar(255) DEFAULT NULL,
  `lng_langue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`lng_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tp_langue`
--

INSERT INTO `tp_langue` (`lng_id`, `lng_code`, `lng_disposition_text`, `lng_langue`) VALUES
(1, '12', '0', 'fr'),
(2, '13', '0', 'en'),
(3, '14', '0', 'es');

-- --------------------------------------------------------

--
-- Structure de la table `tp_menu`
--

DROP TABLE IF EXISTS `tp_menu`;
CREATE TABLE IF NOT EXISTS `tp_menu` (
  `men_id` bigint(20) NOT NULL,
  `men_icone` varchar(255) DEFAULT NULL,
  `men_id_parent` decimal(19,2) DEFAULT NULL,
  `men_nom` varchar(255) DEFAULT NULL,
  `men_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`men_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tp_menu`
--

INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_id_parent`, `men_nom`, `men_path`) VALUES
(1, 'supervisor_account', NULL, 'utilisateur', 'utilisateur'),
(2, 'portrait', NULL, 'application', 'application'),
(3, 'compare_arrows', NULL, 'workflow', 'workflow'),
(4, 'vpn_key', NULL, 'acces', 'acces'),
(5, 'calendar_today', NULL, 'formulaire', 'formulaire');

-- --------------------------------------------------------

--
-- Structure de la table `tp_module`
--

DROP TABLE IF EXISTS `tp_module`;
CREATE TABLE IF NOT EXISTS `tp_module` (
  `mod_id` bigint(20) NOT NULL,
  `mod_app_id` decimal(19,2) DEFAULT NULL,
  `mod_code` varchar(255) DEFAULT NULL,
  `mod_nom` varchar(255) DEFAULT NULL,
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
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tp_parametre`
--

INSERT INTO `tp_parametre` (`param_id`, `param_uti_username`, `param_lng_id`, `param_thm_id`) VALUES
(1, 'pape', 3, NULL),
(2, 'pape', 2, NULL),
(3, 'pape', 3, 3),
(4, 'pape', NULL, 5),
(5, 'pape', NULL, 2),
(6, 'pape', 3, 5),
(7, 'pape', 2, 5),
(8, 'pape', 1, 1),
(9, 'pape', 1, 3),
(10, 'pape', 2, 1),
(11, 'pape', 1, 5),
(12, 'pape', 3, 5),
(13, 'pape', NULL, NULL);

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
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tp_profil`
--

INSERT INTO `tp_profil` (`pro_id`, `pro_description`, `pro_libelle`) VALUES
(1, 'Chargé de l \' integration', 'integrateur');

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
  `thm_id` int(11) NOT NULL AUTO_INCREMENT,
  `thm_accent` varchar(250) NOT NULL,
  `thm_primary` varchar(250) NOT NULL,
  `thm_is_dark` bit(1) DEFAULT NULL,
  `thm_is_default` bit(1) DEFAULT NULL,
  `thm_name` varchar(250) NOT NULL,
  PRIMARY KEY (`thm_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tp_theme`
--

INSERT INTO `tp_theme` (`thm_id`, `thm_accent`, `thm_primary`, `thm_is_dark`, `thm_is_default`, `thm_name`) VALUES
(2, '#E91E63', '#3F51B5', b'0', b'0', 'indigo-pink'),
(1, '#FFC107', '#483A85', b'0', b'1', 'deeppurple-amber'),
(3, '#607D8B', '#A3584E', b'0', b'0', 'pink-grey'),
(4, '#4CAF50', '#9C27B0', b'0', b'0', 'purple-green'),
(5, '#54c051', '#54c051', b'0', b'0', 'gainde-green');

-- --------------------------------------------------------

--
-- Structure de la table `tp_workflow`
--

DROP TABLE IF EXISTS `tp_workflow`;
CREATE TABLE IF NOT EXISTS `tp_workflow` (
  `wkf_id` bigint(20) NOT NULL,
  `wkf_app_id` decimal(19,2) DEFAULT NULL,
  `wkf_artifact_id` varchar(255) DEFAULT NULL,
  `wkf_conteneur` varchar(255) DEFAULT NULL,
  `wkf_nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wkf_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tr_privilege`
--

DROP TABLE IF EXISTS `tr_privilege`;
CREATE TABLE IF NOT EXISTS `tr_privilege` (
  `pri_id` bigint(20) NOT NULL,
  `pri_act_id` bigint(20) DEFAULT NULL,
  `pri_pro_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pri_id`),
  KEY `FK7n73a95fj53acddftc9ynatah` (`pri_act_id`),
  KEY `FKysl2xd67dlurqrbpmhhgcsap` (`pri_pro_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
