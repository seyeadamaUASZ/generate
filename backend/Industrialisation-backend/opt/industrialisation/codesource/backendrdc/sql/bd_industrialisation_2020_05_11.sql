-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 11 mai 2020 à 14:37
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bd_industrialisation`
--

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(11);

-- --------------------------------------------------------

--
-- Structure de la table `td_application`
--

CREATE TABLE `td_application` (
  `app_id` bigint(20) NOT NULL,
  `app_adresse` varchar(255) DEFAULT NULL,
  `app_date_creation` datetime DEFAULT NULL,
  `app_email` varchar(255) DEFAULT NULL,
  `app_ninea` varchar(255) DEFAULT NULL,
  `app_nom` varchar(255) DEFAULT NULL,
  `app_nom_entreprise` varchar(255) DEFAULT NULL,
  `app_secteur` varchar(255) DEFAULT NULL,
  `app_telephone_fixe` varchar(255) DEFAULT NULL,
  `app_telephone_mobile` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `td_champs`
--

CREATE TABLE `td_champs` (
  `chp_id` bigint(20) NOT NULL,
  `chp_class` varchar(255) DEFAULT NULL,
  `chp_label` varchar(255) DEFAULT NULL,
  `chp_nom` varchar(255) DEFAULT NULL,
  `chp_obligatoire` bit(1) DEFAULT NULL,
  `chp_placeholder` varchar(255) DEFAULT NULL,
  `chp_taille` double DEFAULT NULL,
  `chp_type` varchar(255) DEFAULT NULL,
  `ch_frm_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `td_historique_session`
--

CREATE TABLE `td_historique_session` (
  `hse_id` bigint(20) NOT NULL,
  `hse_date_connexion` datetime DEFAULT NULL,
  `hse_ip` varchar(255) DEFAULT NULL,
  `hse_login` varchar(255) DEFAULT NULL,
  `hse_uti_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `td_password_recover`
--

CREATE TABLE `td_password_recover` (
  `pwr_id` bigint(20) NOT NULL,
  `pwr_date` datetime DEFAULT NULL,
  `pwr_email` varchar(255) DEFAULT NULL,
  `pwr_reset_token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `td_password_recover`
--

INSERT INTO `td_password_recover` (`pwr_id`, `pwr_date`, `pwr_email`, `pwr_reset_token`) VALUES
(1, NULL, 'jsarr@gainde2000.sn', NULL),
(2, NULL, 'modou@gainde2000.sn', NULL),
(3, NULL, 'omar@gainde2000.sn', 'a306be252681ff1df5195787487c5ae70d672672'),
(4, NULL, 'omar@gainde2000.sn', '4a724f755444ddca0685b1043b74e70db3b95f64'),
(5, NULL, 'omar@gainde2000.sn', 'ffeff4a571b439bf1e9f041859d259393c39e802'),
(6, NULL, 'omar@gainde2000.sn', 'f7b4cdd23d366134c52c66dfe2e327e9faf26f1b'),
(7, NULL, 'omar@gainde2000.sn', 'aa2afb216e78d0b0252f7bedafa08c090b7b77d6'),
(8, NULL, 'omar@gainde2000.sn', '990bd23e5f725a0fd6917a4520f25703f1c15c9f'),
(9, NULL, 'omar@gainde2000.sn', '89142ad2dbf3a79b1560357007c8b99db4e9de47'),
(10, NULL, 'omar@gainde2000.sn', '7358b2d7cca1f5412f15157544c95e5bd0f53915');

-- --------------------------------------------------------

--
-- Structure de la table `td_session`
--

CREATE TABLE `td_session` (
  `ses_id` bigint(20) NOT NULL,
  `ses_date_connexion` datetime DEFAULT NULL,
  `ses_ip` varchar(255) DEFAULT NULL,
  `ses_login` varchar(255) DEFAULT NULL,
  `ses_uti_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `td_utilisateur`
--

CREATE TABLE `td_utilisateur` (
  `uti_id` bigint(20) NOT NULL,
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
  `uti_username` varchar(50) NOT NULL,
  `tp_profil` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `td_utilisateur`
--

INSERT INTO `td_utilisateur` (`uti_id`, `uti_adresse`, `uti_couleur`, `uti_date_creation`, `uti_date_modification`, `uti_email`, `uti_langue`, `uti_nom`, `uti_password`, `uti_prenom`, `uti_telephone`, `uti_theme`, `uti_app_id`, `uti_username`, `tp_profil`) VALUES
(1, 'Dakar', NULL, '2020-04-09 00:00:00', NULL, 'omar@gainde2000.sn', NULL, 'fall', '$2a$10$.wAGlrdl7yMx0izl7.F5gOYGxw9BgM3rVQI9DIzYj28bXM6ZGEQb.', 'Omar', '221772255452', NULL, NULL, 'maman', 1);

-- --------------------------------------------------------

--
-- Structure de la table `tp_action`
--

CREATE TABLE `tp_action` (
  `act_id` bigint(20) NOT NULL,
  `act_code` int(11) DEFAULT NULL,
  `act_description` varchar(255) DEFAULT NULL,
  `act_nom` varchar(255) DEFAULT NULL,
  `act_men_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_fonctionnalite`
--

CREATE TABLE `tp_fonctionnalite` (
  `fon_id` bigint(20) NOT NULL,
  `fon_code` int(11) DEFAULT NULL,
  `fon_description` varchar(255) DEFAULT NULL,
  `fon_nom` varchar(255) DEFAULT NULL,
  `fon_men_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_formulaire`
--

CREATE TABLE `tp_formulaire` (
  `frm_id` bigint(20) NOT NULL,
  `frm_description` varchar(255) DEFAULT NULL,
  `frm_nom` varchar(255) DEFAULT NULL,
  `frm_app_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_langue`
--

CREATE TABLE `tp_langue` (
  `lng_id` bigint(20) NOT NULL,
  `lng_code` varchar(255) DEFAULT NULL,
  `lng_disposition_text` varchar(255) DEFAULT NULL,
  `lng_langue` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_menu`
--

CREATE TABLE `tp_menu` (
  `men_id` bigint(20) NOT NULL,
  `men_icone` varchar(255) DEFAULT NULL,
  `men_id_parent` bigint(20) DEFAULT NULL,
  `men_nom` varchar(255) DEFAULT NULL,
  `men_path` varchar(255) DEFAULT NULL
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

CREATE TABLE `tp_module` (
  `mod_id` bigint(20) NOT NULL,
  `mod_code` varchar(255) DEFAULT NULL,
  `mod_nom` varchar(255) DEFAULT NULL,
  `mod_app_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_moyen_paiement`
--

CREATE TABLE `tp_moyen_paiement` (
  `mpa_id` bigint(20) NOT NULL,
  `mpa_code` varchar(255) DEFAULT NULL,
  `mpa_libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_pays`
--

CREATE TABLE `tp_pays` (
  `pys_id` bigint(20) NOT NULL,
  `pys_code` varchar(255) DEFAULT NULL,
  `pys_icone` varchar(255) DEFAULT NULL,
  `pys_nom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tp_profil`
--

CREATE TABLE `tp_profil` (
  `pro_id` bigint(20) NOT NULL,
  `pro_description` varchar(255) DEFAULT NULL,
  `pro_libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tp_profil`
--

INSERT INTO `tp_profil` (`pro_id`, `pro_description`, `pro_libelle`) VALUES
(1, 'profile intégrateur', 'Integrateur'),
(2, 'l\'administrateur', 'Administarteur');

-- --------------------------------------------------------

--
-- Structure de la table `tp_workflow`
--

CREATE TABLE `tp_workflow` (
  `wkf_id` bigint(20) NOT NULL,
  `wkf_artifact_id` varchar(255) DEFAULT NULL,
  `wkf_conteneur` varchar(255) DEFAULT NULL,
  `wkf_nom` varchar(255) DEFAULT NULL,
  `wkf_app_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tr_privilege`
--

CREATE TABLE `tr_privilege` (
  `pri_pro_id` bigint(20) NOT NULL,
  `pri_act_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `td_application`
--
ALTER TABLE `td_application`
  ADD PRIMARY KEY (`app_id`);

--
-- Index pour la table `td_champs`
--
ALTER TABLE `td_champs`
  ADD PRIMARY KEY (`chp_id`),
  ADD KEY `FK154f4yyc4v4twupxnwmhfg30a` (`ch_frm_id`);

--
-- Index pour la table `td_historique_session`
--
ALTER TABLE `td_historique_session`
  ADD PRIMARY KEY (`hse_id`),
  ADD KEY `FKaq8adclu3qsi25qmpvabb3pqv` (`hse_uti_id`);

--
-- Index pour la table `td_password_recover`
--
ALTER TABLE `td_password_recover`
  ADD PRIMARY KEY (`pwr_id`);

--
-- Index pour la table `td_session`
--
ALTER TABLE `td_session`
  ADD PRIMARY KEY (`ses_id`),
  ADD KEY `FKlnu9noaam2e02705x5jwwmq2` (`ses_uti_id`);

--
-- Index pour la table `td_utilisateur`
--
ALTER TABLE `td_utilisateur`
  ADD PRIMARY KEY (`uti_id`),
  ADD KEY `FKpmirdtjefa6f08v07qpq03mqg` (`uti_app_id`),
  ADD KEY `FKihrvrd54cf0qiw3i89k3kbeax` (`tp_profil`);

--
-- Index pour la table `tp_action`
--
ALTER TABLE `tp_action`
  ADD PRIMARY KEY (`act_id`),
  ADD KEY `FKvisktsit802fxeyqm10cvmtq` (`act_men_id`);

--
-- Index pour la table `tp_fonctionnalite`
--
ALTER TABLE `tp_fonctionnalite`
  ADD PRIMARY KEY (`fon_id`),
  ADD KEY `FKqjxbnos8w3rsdx6rbofnf27vr` (`fon_men_id`);

--
-- Index pour la table `tp_formulaire`
--
ALTER TABLE `tp_formulaire`
  ADD PRIMARY KEY (`frm_id`),
  ADD KEY `FKoaw9c7vtioiexdsm6sbj2jxhb` (`frm_app_id`);

--
-- Index pour la table `tp_langue`
--
ALTER TABLE `tp_langue`
  ADD PRIMARY KEY (`lng_id`);

--
-- Index pour la table `tp_menu`
--
ALTER TABLE `tp_menu`
  ADD PRIMARY KEY (`men_id`);

--
-- Index pour la table `tp_module`
--
ALTER TABLE `tp_module`
  ADD PRIMARY KEY (`mod_id`),
  ADD KEY `FK3kjjlul4u3sva3ko7d6wu8w2s` (`mod_app_id`);

--
-- Index pour la table `tp_moyen_paiement`
--
ALTER TABLE `tp_moyen_paiement`
  ADD PRIMARY KEY (`mpa_id`);

--
-- Index pour la table `tp_pays`
--
ALTER TABLE `tp_pays`
  ADD PRIMARY KEY (`pys_id`);

--
-- Index pour la table `tp_profil`
--
ALTER TABLE `tp_profil`
  ADD PRIMARY KEY (`pro_id`);

--
-- Index pour la table `tp_workflow`
--
ALTER TABLE `tp_workflow`
  ADD PRIMARY KEY (`wkf_id`),
  ADD KEY `FKy2k1seyrf98yjnluoeeopms5` (`wkf_app_id`);

--
-- Index pour la table `tr_privilege`
--
ALTER TABLE `tr_privilege`
  ADD KEY `FK7n73a95fj53acddftc9ynatah` (`pri_act_id`),
  ADD KEY `FKysl2xd67dlurqrbpmhhgcsap` (`pri_pro_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `td_password_recover`
--
ALTER TABLE `td_password_recover`
  MODIFY `pwr_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `td_utilisateur`
--
ALTER TABLE `td_utilisateur`
  MODIFY `uti_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `tp_profil`
--
ALTER TABLE `tp_profil`
  MODIFY `pro_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
