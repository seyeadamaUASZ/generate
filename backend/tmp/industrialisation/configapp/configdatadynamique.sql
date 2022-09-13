<<<<<<< HEAD
INSERT INTO `td_application` (`app_id`, `app_adresse`, `app_date_creation`, `app_email`, `app_ninea`, `app_nom`, `app_nom_entreprise`, `app_secteur`, `app_telephone_fixe`, `app_telephone_mobile`, `parametre`, `app_uti_id`, `app_etape_creation`) VALUES
	(954, 'null', NULL, 'null', 'null', 'GFRT', 'sante', 'null', 'null', 'oo', NULL, NULL, NULL);
=======
INSERT INTO `td_application` (`app_id`, `app_adresse`, `app_date_creation`, `app_email`, `app_ninea`, `app_nom`, `app_nom_entreprise`, `app_secteur`, `app_telephone_fixe`, `app_telephone_mobile`, `parametre`, `app_etape_creation`) VALUES
	(2, 'Senegal(louga ), Thiowor', NULL, 'modouxdiop@gmail.com', 'N0293999', 'Gainde2000', 'sante', '777393914', '777393914', 'oo', NULL, NULL);
>>>>>>> c4464812ca7610543937fb67f9bfb81adda56ab6

INSERT INTO `tp_langue` (`lng_id`, `lng_code`, `lng_disposition_text`, `lng_langue`)VALUES(1, '12', '0','fr');
INSERT INTO `tp_langue` (`lng_id`, `lng_code`, `lng_disposition_text`, `lng_langue`)VALUES(2, '13', '0','en');
INSERT INTO `tp_langue` (`lng_id`, `lng_code`, `lng_disposition_text`, `lng_langue`)VALUES(3, '14', '0','es');
INSERT INTO `tp_theme` (`thm_id`, `thm_accent`, `thm_primary`, `thm_is_dark`, `thm_is_default`, `thm_name`) VALUES(1, '#FFC107', '#483A85', b'0', b'0', 'deeppurple-amber');
INSERT INTO `tp_theme` (`thm_id`, `thm_accent`, `thm_primary`, `thm_is_dark`, `thm_is_default`, `thm_name`) VALUES(2, '#E91E63', '#3F51B5', b'0', b'0', 'indigo-pink');
INSERT INTO `tp_theme` (`thm_id`, `thm_accent`, `thm_primary`, `thm_is_dark`, `thm_is_default`, `thm_name`) VALUES(3, '#607D8B', '#A3584E', b'0', b'0', 'pink-grey');
INSERT INTO `tp_theme` (`thm_id`, `thm_accent`, `thm_primary`, `thm_is_dark`, `thm_is_default`, `thm_name`) VALUES(4, '#4CAF50', '#9C27B0', b'0', b'0', 'purple-green');
INSERT INTO `tp_theme` (`thm_id`, `thm_accent`, `thm_primary`, `thm_is_dark`, `thm_is_default`, `thm_name`) VALUES(5, '#54c051', '#54c051', b'0', b'0', 'gainde-green');
<<<<<<< HEAD
INSERT INTO `tp_formulaire` (`frm_id`, `frm_app_id`, `frm_description`, `frm_nom`, `frm_status`, `frm_valider`) VALUES(1032, '954', 'df', 'sd', true, 'Valider');
INSERT INTO `tp_formulaire` (`frm_id`, `frm_app_id`, `frm_description`, `frm_nom`, `frm_status`, `frm_valider`) VALUES(1598, '954', 'aly', 'Modification', false, 'Valider');
=======
INSERT INTO `tp_formulaire` (`frm_id`, `frm_app_id`, `frm_description`, `frm_nom`, `frm_status`, `frm_valider`) VALUES(853, '2', 'inscription', 'Inscription', true, 'Valider');
INSERT INTO `tp_formulaire` (`frm_id`, `frm_app_id`, `frm_description`, `frm_nom`, `frm_status`, `frm_valider`) VALUES(1029, '2', 'Commande', 'Commande', true, 'Valider');
INSERT INTO `tp_workflow` (`wkf_id`, `wkf_app_id`, `wkf_artifact_id`, `wkf_conteneur`, `wkf_nom`, `description`,`name`,`version`,`wkf_etat`, `wkf_process_id`) VALUES(1, '2', 'jjjj', 'jjjj', 'ggg', 'covid','ggg','01','jjj','jj');
INSERT INTO `tp_pwd_criteria` (`pwd_id`, `pwd_car_min`, `pwd_dig_min`, `pwd_dure`, `pwd_maj_min`, `pwd_spc_min`)  VALUES(1, '8', '2', '1', '1', '1');
>>>>>>> c4464812ca7610543937fb67f9bfb81adda56ab6
