
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES (2, 'settings', 'accent', NULL, 'parametrage', '/parametrage');
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES (3, 'file_copy', 'accent', NULL, 'fichier', '/fichier');
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES (4, 'file_copy', 'primary', NULL, 'rapport', '/fichier/rapport');
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_id_parent`, `men_nom`, `men_path`, `men_icone_color`) VALUES  (5, 'compare_arrows', NULL, 'procedures', '/procedures', NULL);
INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_id_parent`, `men_nom`, `men_path`, `men_icone_color`) VALUES (6, 'menu', 2, 'Gestions des menus', '/parametrage/menu', 'accent');


INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('13', 'create_widget', 'Créer un Widget', '2');
INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('14', 'view_widget', 'Consulter un Widget', '2');
INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('15', 'edit_widget', 'Modifier un Widget', '2');


INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('16', 'create_fichier', 'Créer un fichier', '3');
INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('17', 'view_fichier', 'Consulter un fichier', '3');
INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('18', 'edit_fichier', 'Modifier un fichier', '3');

INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('19', 'gen_fichier_pdf', 'Générer un fichier pdf', '4');
INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('20', 'gen_fichier_excel', 'Générer un fichier excel', '4');
INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('21', 'view_rapport', 'Consulter un fichier modéliser', '4');
INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('22', 'view_rapport_form', 'Consulter le formulaire de generation du rapport', '4');
INSERT INTO `tp_action` (`act_id`, `act_code`, `act_description`, `act_men_id`, `act_nom`) VALUES ('23', 'execute_wkfl', NULL, '5', 'Executer un workflow');
INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('24', 'ajouter_menu', 'Ajouter un menu', '6');
INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('25', 'modif_menu', 'Modifier un menu', '6');
