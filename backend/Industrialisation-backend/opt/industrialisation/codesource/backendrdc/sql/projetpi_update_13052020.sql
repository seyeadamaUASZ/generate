ALTER TABLE `tp_action`
	CHANGE COLUMN `act_code` `act_code` CHAR(20) NULL DEFAULT NULL AFTER `act_id`;
ALTER TABLE tp_action ADD CONSTRAINT fk_menu_action FOREIGN KEY (act_men_id) 
        REFERENCES tp_menu(menu_id);

ALTER TABLE `tr_privilege`
	ADD PRIMARY KEY (`pri_id`);		

ALTER TABLE `tr_privilege`
	CHANGE COLUMN `pri_id` `pri_id` BIGINT(20) NOT NULL AUTO_INCREMENT AFTER `pri_act_id`;	

INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES (1, 'portrait', 'warn', NULL, 'application', '/application');
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES (2, 'vpn_key', 'secondary', NULL, 'acces', '/utilisateur/acces');
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES (3, 'supervisor_account', 'primary', NULL, 'utilisateur', '/utilisateur');
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES (4, 'calendar_today', 'secondary', NULL, 'formulaire', '/formulaire');
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES (5, 'compare_arrows', 'accent', NULL, 'workflow', '/workflow');
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES (6, 'settings', 'accent', NULL, 'parametrage', '/parametrage');
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES (7, 'file_copy', 'accent', NULL, 'fichier', '/fichier');
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES (8, 'file_copy', 'primary', NULL, 'rapport', '/fichier/rapport');
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES  (9, 'compare_arrows', NULL, NULL, 'procedures', 'procedures');
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES (10, 'menu', 'accent', 6, 'Gestions des menus', 'parametrage/menu');


INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('1', 'create_app', 'Créer une application', '1'); 
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('2', 'view_app', 'Consulter une applications', '1'); 
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('3', 'edit_app', 'Modifier une application', '1');
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('4', 'inst_mod', 'Installer les modules', '1');



INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('5', 'create_profil', 'Créer un profil', '3');
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('6', 'edit_profil', 'Modifier un profil', '3');
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('7', 'delete_profil', 'Supprimer un profil', '3');
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('8', 'create_user', 'Créer un utilisateur', '3');
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('9', 'edit_user', 'Modifier un utilisateur', '3');
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('10', 'delete_user', 'Supprimer un utilisateur', '3');
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('11', 'enable_user', 'Activer/desactiver un utilisateur', '3');
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('12', 'set_acces', 'Définir les droits d\''acces', '3');
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('13', 'edit_acces', 'Modifier les droits d\''acces', '3');

INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('14', 'create_form', 'Créer un formulaire', '4');
 INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('15', 'edit_form', 'Modifier un formulaire', '4'); 
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('16', 'deploy_form', 'Déployer un formulaire', '4');

INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('17', 'create_wkfl', 'Créer un workflow', '5');
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('18', 'edit_wkfl', 'Modifier un workflow', '5'); 
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('19', 'deploy_wkfl', 'Déployer un workflow', '5');

INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('20', 'create_widget', 'Créer un Widget', '6'); 
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('21', 'view_widget', 'Consulter un Widget', '6'); 
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('22', 'edit_widget', 'Modifier un Widget', '6');


INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('23', 'create_fichier', 'Créer un fichier', '7'); 
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('24', 'view_fichier', 'Consulter un fichier', '7'); 
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('25', 'edit_fichier', 'Modifier un fichier', '7');

INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('26', 'gen_fichier_pdf', 'Générer un fichier pdf', '8'); 
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('27', 'gen_fichier_excel', 'Générer un fichier excel', '8'); 
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('28', 'view_rapport', 'Consulter un fichier modéliser', '8');
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('29', 'view_rapport_form', 'Consulter le formulaire de generation du rapport', '8');
INSERT INTO `tp_action` (`act_id`, `act_code`, `act_description`, `act_men_id`, `act_nom`) VALUES ('40', 'execute_wkfl', NULL, '9', 'Executer un workflow');
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('41', 'ajouter_menu', 'Ajouter un menu', '10');
INSERT INTO `bd_industrialisation`.`tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('42', 'modif_menu', 'Modifier un menu', '10');

INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 1);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 2);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 3);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 4);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 5);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 6);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 7);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 8);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 9);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 10);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 11);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 12);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 13);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 14);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 15);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (1, 16);

INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 17);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 32);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 33);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 34);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 35);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 31);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 29);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 30);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 28);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 20);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 22);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 22);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 23);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 24);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 25);
INSERT INTO `tr_privilege` (`pri_pro_id`, `pri_act_id`) VALUES (2, 16);


	






ALTER TABLE `td_historique_session` ADD COLUMN `hse_date_decconnexion` datetime DEFAULT NULL;

 ALTER TABLE `td_historique_session` ADD COLUMN `hse_ses_id` BIGINT NULL AFTER `hse_date_decconnexion`;
ALTER TABLE td_historique_session ADD CONSTRAINT fk_session_id FOREIGN KEY (hse_ses_id) REFERENCES session(ses_id);
CREATE TABLE `tp_pwd_criteria` (
	`pwd_id` BIGINT(20) NOT NULL,
	`pwd_car_min` INT(11) NULL DEFAULT '0' COMMENT 'Nombre de caracters minimum',
	`pwd_maj_min` INT(11) NULL DEFAULT '0' COMMENT 'Nomnbre de majuscules minimum',
	`pwd_dig_min` INT(11) NULL DEFAULT '0' COMMENT 'Nombre de chiffres minimum',
	`pwd_spc_min` INT(11) NULL DEFAULT '0' COMMENT 'Nombre de caracteres speciaux minimum',
	PRIMARY KEY (`pwd_id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=MyISAM;

INSERT INTO `tp_pwd_criteria` (`pwd_id`, `pwd_car_min`, `pwd_dig_min`, `pwd_maj_min`, `pwd_spc_min`, `pwd_dure`) VALUES
(1, '2', '4', '2', '4', '22');
COMMIT;

/*
INSERT INTO `tp_rapport` (`rpt_id`, `rpt_description`, `rpt_nom`, `rpt_status`, `rpt_valider`) VALUES
(596, 'Demande de Congé', 'Demande Conge', b'0', 'Valider'),
(597, 'demande extrait de naissance', 'Extrait de naissance', b'0', 'Valider'),
(619, 'Contrat salarial', 'Contrat salarial', b'0', 'Modeliser'),
(620, 'contrat de travail', 'contrat de travail', b'0', 'Modeliser');
COMMIT;

INSERT INTO `td_champs_rapport` (`crt_id`, `crt_class`, `crt_icon`, `crt_label`, `crt_nom`, `crt_obligatoire`, `crt_placeholder`, `crt_regex`, `crt_rpt_id`, `crt_taille`, `crt_type`) VALUES
(613, 'form-control', 'fa-font', 'Text', 'text-1593692224432', b'1', 'Enter your name', '', 596, NULL, 'text'),
(616, 'form-control', 'fa-envelope', 'Email', 'email-1593694248145', b'1', 'Enter your email', '^([a-zA-Z0-9_.-]+)@([a-zA-Z0-9_.-]+).([a-zA-Z]{2,5})$', 596, NULL, 'email'),
(617, 'form-control', 'fa-calendar', 'Date', 'date-1593695927794', NULL, 'Date', NULL, 597, NULL, 'date'),
(618, 'form-control', 'fa-envelope', 'Email', 'email-1593695945634', b'1', 'Enter your email', '^([a-zA-Z0-9_.-]+)@([a-zA-Z0-9_.-]+).([a-zA-Z]{2,5})$', 597, NULL, 'email'),
(621, 'form-control', 'fa-font', 'Text', 'text-1593696293048', b'1', 'Enter your name', '', 619, NULL, 'text'),
(622, 'form-control', 'fa-envelope', 'Email', 'email-1593696295608', b'1', 'Enter your email', '^([a-zA-Z0-9_.-]+)@([a-zA-Z0-9_.-]+).([a-zA-Z]{2,5})$', 619, NULL, 'email');
COMMIT;*/





----------------------------------------------------------------
-- Structure de la table `tp_widget`
--

DROP TABLE IF EXISTS `tp_widget`;
CREATE TABLE IF NOT EXISTS `tp_widget` (
  `wdg_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `wkf_app_id` decimal(19,2) DEFAULT NULL,
  `wdg_description` varchar(255) DEFAULT NULL,
  `wdg_nom` varchar(255) DEFAULT NULL,
  `wdg_code` varchar(255) DEFAULT NULL,
  `wdg_ordre` varchar(255) DEFAULT NULL,
  `wdg_longueur` int(11) DEFAULT NULL,
  `wdg_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wdg_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tp_widget`
--

INSERT INTO `tp_widget` (`wdg_id`,  `wdg_description`, `wdg_nom`, `wdg_code`, `wdg_ordre`, `wdg_longueur`, `wdg_type`) VALUES
(2,  'Le nombre d\'app ,modules..généré', 'Statistique Interne', 'nbr_by_mdl', 'nbr_by_mdl', 12, NULL),
(3,  'Liste form et workflow', 'Chart en courbe', 'lne_app_wkf_fml', 'lne_app_wkf_fml', 6, NULL),
(4,  'Nombre user connecté', 'Chart en barre', 'bar_uti_con', 'bar_uti_con', 6, NULL),
(5,  'Nombre de composant client', 'Statistique client', 'stats_costumer', NULL, 12, NULL);
COMMIT;


INSERT INTO `tp_icone` (`ico_id`, `ico_description`, `ico_nom`) VALUES
	(1, 'account circle icon', 'account_circle'),
	(2, 'calendar today icon', 'calendar_today'),
	(3, 'email', 'email'),
	(4, 'phone', 'phone'),
	(5, 'person', 'perm_identity'),
	(6, 'domain', 'domain'),
	(7, 'house', 'house'),
	(8, 'work', 'work'),
	(9, 'text_format', 'text_format'),
	(10, 'alternate_email', 'alternate_email');