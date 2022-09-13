/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.controller;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.JbpmUserMapping;
import sn.gainde2000.pi.core.entity.PasswordRecover;
import sn.gainde2000.pi.core.entity.Profil;
import sn.gainde2000.pi.core.entity.Utilisateur;
import sn.gainde2000.pi.core.repository.*;
import sn.gainde2000.pi.core.service.impl.HistoriqueSessionImpl;
import sn.gainde2000.pi.core.service.impl.JbpmUserServiceImpl;
import sn.gainde2000.pi.core.service.impl.SessionImpl;
import sn.gainde2000.pi.core.service.impl.UtilisateurImpl;
import sn.gainde2000.pi.core.tools.Email;
import sn.gainde2000.pi.core.tools.PasswordValidator;
//import sn.gainde2000.pi.core.tools.Sms;

/**
 *
 * @author yougadieng
 */
@RestController
@CrossOrigin("*")
@RequestMapping("utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurImpl userService;
    @Autowired
    SessionImpl sessionService;
    @Autowired
    HistoriqueSessionImpl histSessionService;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    PassRecovRepository recovRepository;
    @Autowired
    JbpmUserRepository jbpmUserRepository;

    @Autowired
    JbpmUserServiceImpl jbpmUserService;
    
    @Autowired
    Email email;
    
    /*@Autowired
    Sms sms;*/

    @GetMapping("list")
    public ServeurResponse getAllUsers() {
        // This returns a JSON or XML with the users
    	
    	
        Iterable<Utilisateur> users = userService.listUtilisateur();
        ServeurResponse response = new ServeurResponse();
        if (users == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(users);
            response.setDescription("Données récupérées.");
        }
        return response;
    }
    
    @PostMapping("listUsersbyprofil")
    public ServeurResponse getUsersByProfil(@RequestBody Profil profil) {
    	Iterable<Utilisateur> users = userService.findUsersByUtiProfil(profil);
        ServeurResponse response = new ServeurResponse();
        if (users == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(users);
            response.setDescription("Données récupérées.");
        }
        return response;
    }

    @RequestMapping(value = "/infos", method = RequestMethod.GET)
    @ResponseBody
    public Utilisateur currentUserDetail(Authentication authentication) {
        ServeurResponse response = new ServeurResponse();
        System.out.println(authentication.getName());
        Utilisateur user = userService.findByUtiUsername(authentication.getName());
        if (userService.findByUtiUsername(authentication.getName()) == null) {
            response.setStatut(false);
            response.setDescription("Cet utilisatueur n'existe pas.");
            response.setData(null);
        } else {
        	try {
        		if(user.getUtiLogo() != null) {
        			user.setUtiLogo(decompressBytes(user.getUtiLogo()));
        		}
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
            response.setStatut(true);
            response.setData(user);

        }
        return user;
    }

    @PostMapping("updateinfos")
    public ServeurResponse updateinfosUserDetail(Authentication authentication, @Valid @RequestBody Utilisateur userDetails) {
        ServeurResponse response = new ServeurResponse();
        //System.out.println("-----------------updateinfosUserDetail-----------------"+authentication.getName());
        Utilisateur user = userService.findByUtiUsername(authentication.getName());
        if (userService.findByUtiUsername(authentication.getName()) == null) {
            response.setStatut(false);
            response.setDescription("Cet utilisatueur n'existe pas.");
            response.setData(null);
        } else {
            user.setUtiDateModification(new Date());
            user.setUtiNom(userDetails.getUtiNom());
            user.setUtiPrenom(userDetails.getUtiPrenom());
            user.setUtiAdresse(userDetails.getUtiAdresse());
            user.setUtiTelephone(userDetails.getUtiTelephone());
            user.setUtiEmail(userDetails.getUtiEmail());
            user.setUti_lng_id(userDetails.getUti_lng_id());
            user.setUti_thm_id(userDetails.getUti_thm_id());
            this.userService.addUtilisateur(user);
            try {
                if(user.getUtiLogo()!=null)
                    user.setUtiLogo(decompressBytes(user.getUtiLogo()));
            } catch (DataFormatException e) {
            	e.printStackTrace();
	    }
            response.setStatut(true);
            response.setData(user);
            response.setDescription("infos mis ajour.");            
            /*sms.sendSms("775449216", "infos utilisaeur mis ajour");*/
        }

        return response;
    }
    
    //Ajouter logo
    @PostMapping("updatelogo")
    public ServeurResponse uplaodLogo(Authentication authentication, @RequestParam("imageFile") MultipartFile file) throws IOException {

        ServeurResponse response = new ServeurResponse();
        
        Utilisateur user = userService.findByUtiUsername(authentication.getName());
        byte[] logo = compressBytes(file.getBytes());
        try{
	        user.setUtiLogo(logo);
	        userService.addUtilisateur(user);
	        response.setStatut(true);
	        response.setDescription("Enregistrement reussi!");
	        response.setData(user);
        }
        catch(Exception e ){
	        response.setStatut(false);
	        response.setDescription("erreure serveur");
	        response.setData(user);
        }
       
        return response;
    }
    
    //Get Logo user
    @GetMapping("getlogouser")
    public ServeurResponse getLogoUser(Authentication authentication) throws IOException {

        ServeurResponse response = new ServeurResponse();
        
        Utilisateur user = userService.findByUtiUsername(authentication.getName());
        try {
        	user.setUtiLogo(decompressBytes(user.getUtiLogo()));
            response.setStatut(true);
            response.setDescription("Enregistrement reussi!");
			response.setData(user);
		} catch (DataFormatException e) {
			response.setStatut(false);
	        response.setDescription("erreure serveur");
	        response.setData(null);
		}
        return response;
    }
    
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressé la taille de l'image- " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    
    // decompressé la taille de l'image avant de le retourner sur application angular 
    public static byte[] decompressBytes(byte[] data) throws DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        }
        return outputStream.toByteArray();
    }

    //Avoire le detail du compte de l'utilisateur
    @PostMapping(value = "detail", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ServeurResponse getUser(HttpServletRequest request) {
        String username = (String) request.getParameter("username");
        System.out.println("username " + username);
        Utilisateur user = userService.findByUtiUsername(username);
        ServeurResponse response = new ServeurResponse();
        if (user == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("L'utilisateur '" + username + "' n'a pas été trouvé.");
        } else {
            response.setStatut(true);
            user.setUtiPassword("permission denied");
            response.setData(user);
            response.setDescription("L'utilisateur '" + username + "' a été trouvé.");
        }
        return response;
    }

//Cration de compte
    @PostMapping("create")
    public ServeurResponse create(@RequestBody Utilisateur user) throws IOException, MessagingException {
        // user.setId(LocalDateTime.now().toString() + new Random().hashCode());
        //user.setUtiId("gainde2000" + new Random().hashCode());
        System.out.println("+++++++++++++++++++++++++++++++++++++"+user.getUtiUsername());
        user.setUtiPassword("Gainde2000");
        user.setUtiPassword(encoder().encode(user.getUtiPassword()));
        user.setUtiActived(true);
        user.setUtiDateCreation(new Date());
        user.setUtiPremierConnect(true);
        ServeurResponse response = new ServeurResponse();
        if (userService.findUsersByUtiEmail(user.getUtiEmail()) == 0 ) {
            if (userService.findByUtiUsername(user.getUtiUsername()) == null) {
            	userService.addUtilisateur(user);
            	//System.out.println("+++++++++++++++++++++++++++++++++++++"+user.getUtiUsername());
            	if(jbpmUserService.findByIndusUser(user.getUtiUsername()) == null) {
            	this.InstertToJbpmMapping(new JbpmUserMapping(user.getUtiUsername(),user.getUtiUsername(),user.getUtiUsername()) );
            	}
                email.sendMail(null, user.getUtiEmail(), "Bienvenue dans votre plateforme industrialisation ,vos identifiants de connexion : \n Username: "+user.getUtiUsername()+"\n Password: Gainde2000", "Création de compte utilisateur");
                response.setStatut(true);
                response.setDescription("Enregistrement réussi");
                response.setData(user);
            } else {
                response.setStatut(false);
                response.setDescription("Le nom d'utilisateur est déjà  attribué.");
                response.setData(null);
            }
        } else {
            response.setStatut(false);
            response.setDescription("L'email est déjà  attribué.");
            response.setData(null);
        }

        return response;
    }


    public JbpmUserMapping InstertToJbpmMapping(JbpmUserMapping jbpmUserMapping) {

        return jbpmUserService.addUserJbpm(jbpmUserMapping);
    }


    //Mise ajour compte utilisateur
    @PostMapping("update")
    public ServeurResponse update(@RequestBody Utilisateur user) {
        ServeurResponse response = new ServeurResponse();
        Utilisateur findUser = userService.findByUtiUsername(user.getUtiUsername());
        if (findUser != null) {
            findUser.setUtiDateModification(new Date());
            findUser.setUtiPrenom(user.getUtiPrenom());
            findUser.setUtiNom(user.getUtiNom());
            findUser.setUtiTelephone(user.getUtiTelephone());
            findUser.setUtiEmail(user.getUtiEmail());
            findUser.setUtiAdresse(user.getUtiAdresse());
            findUser.setUti_pro_id(user.getUti_pro_id());
            this.userService.addUtilisateur(findUser);
            response.setStatut(true);
            response.setDescription("Enregistrement rÃ©ussi");
            response.setData(findUser);
        } else {
            response.setStatut(false);
            response.setDescription("Echec d'enregistrement");
        }

        return response;
    }

    @GetMapping("/{id}")
    public Optional<Utilisateur> findByid(@PathVariable Long id) {
        return userService.findById(id);
    }
//Service suppression de compte

    @PostMapping("delete")
    public ServeurResponse delete(@RequestBody Utilisateur user1) {
    	userService.deleteUtilisateur(user1);
        ServeurResponse response = new ServeurResponse();
        Utilisateur user = userService.findByUtiUsername(user1.getUtiUsername());
        if (user == null) {
            response.setStatut(true);
            response.setDescription("Suppression rÃ©ussie.");
            response.setData(null);
        } else {
            response.setStatut(false);
            response.setDescription("Echec suppression.");
            response.setData(user);
        }
        return response;
    }

    @GetMapping("get/{username}")
    public ServeurResponse getParametre(@PathVariable String username) {
        //System.out.println("user1 check:  getParametre");
        ServeurResponse response = new ServeurResponse();
        Utilisateur user = userService.verifyLangueEtTheme(username);
        if (user != null) {
            //System.out.println("les données du user" + user);
            response.setStatut(true);
            response.setDescription("Langue et theme du user récupéré avec succès");
            response.setData(user);
        } else {
            response.setStatut(true);
            response.setDescription("Echec récupération");
            response.setData(null);

        }
        return response;
    }
//Changement de mot de pass

    @RequestMapping(value = "changerpwd", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ServeurResponse changerPwd(HttpServletRequest request) {
        ServeurResponse response = new ServeurResponse();
        String username = (String) request.getParameter("username");
        String oldpwd = (String) request.getParameter("oldpwd");
        String vrfpwd = (String) request.getParameter("newpwd");
        String newpwd = (String) request.getParameter("newpwd");
        //oldpwd = encoder().encode(oldpwd);
        newpwd = encoder().encode(newpwd);
        System.out.print("+++++++++++++++++++++++++++++++++++++++++++" + newpwd);
        Utilisateur user = userService.findByUtiUsername(username);
        PasswordValidator passwordValidator = new PasswordValidator();
        Boolean rep = passwordValidator.validate(vrfpwd);
        if (user != null) {
            if (!encoder().matches(oldpwd, user.getUtiPassword())) {
                response.setStatut(false);
                response.setDescription("L'ancien mot de passe est incorrect. Veuillez rÃ©essayer.");
            } else {
                if (rep != false) {
                    user.setUtiPassword(newpwd);
                    user.setUtiPremierConnect(false);
                    userService.addUtilisateur(user);
                    response.setStatut(true);
                    response.setDescription("Le mot de passe est modifiÃ©.");
                } else {
                    response.setStatut(false);
                    response.setDescription("Le mot de passe doit comporter entre 8 et 20 caractÃ¨res dont au moins une minuscule, une majuscule et un chiffre!");

                }

            }
        } else {
            response.setStatut(false);
            response.setDescription("Aucun utilisateur n'a Ã©tÃ© trouvÃ©");
        }

        return response;
    }

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /* @GetMapping("agentsconnected")
    private ServeurResponse getAgentConnected(){
        ServeurResponse response = new ServeurResponse();
        Iterable<AgentConnected> agents = agentConnectedRepository.getAgentConnected();
        response.setStatut(true);
        response.setData(agents); 
        return response;
    }
     */
    @PostMapping("reinitialisation")
    private ServeurResponse reinit(@RequestBody Utilisateur user) {
        ServeurResponse response = new ServeurResponse();
        user.setUtiPassword("Gainde2000");
        user.setUtiPassword(encoder().encode(user.getUtiPassword()));
        userService.addUtilisateur(user);
        if (user != null) {
            response.setStatut(true);
            response.setDescription("Réinitialisation réussie");
        } 
        return response;
    }

    @RequestMapping(value = "/veriftoken/{token}", method = RequestMethod.GET)
    public ServeurResponse getTkn(@PathVariable String token) throws NoSuchAlgorithmException, MessagingException {
        ServeurResponse response = new ServeurResponse();

        String tkn = recovRepository.verifTkn(token, new Date());

        if (tkn == "") {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Le token est expiré .");
        } else {
            response.setStatut(true);
            String email1 = recovRepository.recupEmail(token);
            Utilisateur utilisateur = userService.findByUtiEmail(email1);
            utilisateur.setUtiPremierConnect(true);            
            utilisateur.setUtiPassword(encoder().encode("Gainde2000"));
            userService.addUtilisateur(utilisateur);
            String from = "no-reply@gainde2000.sn";
            String to = email1;
            //String to = "jsarr@gainde2000.sn";
            String subject = "Reinitialisation du mot de pass";
            String message = "Votre mot de passe par defaut est Gainde2000";
            //Email email = new Email();
            email.sendMail(from, to, message, subject);
            response.setDescription("Un Email contenant votre mot de passe par defaut vous a  été envoyer");
        }
        return response;
    }

    @RequestMapping(value = "/recover", method = RequestMethod.POST)
    public ServeurResponse addEmailToken(@RequestBody PasswordRecover passwordRecover) throws NoSuchAlgorithmException, MessagingException, javax.mail.MessagingException {
        System.out.println("recover addEmailToken");
        ServeurResponse response = new ServeurResponse();
        System.out.println(passwordRecover.getPwrEmail());
        if (userService.findByUtiEmail(passwordRecover.getPwrEmail()) == null) {
            response.setStatut(false);
            response.setDescription("Ce n'existe pas.");
            response.setData(null);
        } else {
            byte[] salt = getSalt();
            String securePassword = get_SHA_1_SecurePassword(passwordRecover.getPwrEmail(), salt);
            System.out.println(securePassword);

            passwordRecover.setPwrDateCreation(new Date());
            passwordRecover.setPwrEmail(passwordRecover.getPwrEmail());
            passwordRecover.setPwrResetToken(securePassword);
            recovRepository.save(passwordRecover);
            String userName = passwordRecover.getPwrEmail();
            String from = "no-reply@gainde2000.sn";
            String to = passwordRecover.getPwrEmail();
            //String to = "jsarr@gainde2000.sn";
            String subject = "Reinitialisation du mot de pass";
            String message = "Veuillez cliquer sur ce lien pour finaliser votre requete http://localhost:4300/newPwd/" + securePassword;
            Email email = new Email();
            email.sendMail(from, to, message, subject);
            /* String from = passwordRecover.getPwrEmail();
            String to = "@gainde2000.sn";
            String subject = "Reinitialisation du mot de pass";
            String body = "http://localhost:9090/reinitialisation/reset?token="+securePassword;

            mailSender.sendMail(from, to, subject, body);*/
            response.setStatut(true);
            response.setDescription("Un email de verification vous a Ã©tÃ© envoyer ");
            response.setData(passwordRecover);

        }
        return response;

    }

    @RequestMapping(value = "resetpwd", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ServeurResponse resetPwd(HttpServletRequest request) {
        ServeurResponse response = new ServeurResponse();
        String email = (String) request.getParameter("email");
        String oldpwd = (String) request.getParameter("oldpwd");
        String newpwd = (String) request.getParameter("newpwd");
        //oldpwd = encoder().encode(oldpwd);
        newpwd = encoder().encode(newpwd);
        System.out.print("+++++++++++++++++++++++++++++++++++++++++++" + newpwd);
        Utilisateur user = userService.findByUtiUsername(email);
        if (user != null) {
            if (!encoder().matches(oldpwd, user.getUtiPassword())) {
                response.setStatut(false);
                response.setDescription("L'ancien mot de passe est incorrect. Veuillez rÃ©essayer.");
            } else {
                user.setUtiPassword(newpwd);
                user.setUtiPremierConnect(false);
                userService.addUtilisateur(user);
                response.setStatut(true);
                response.setDescription("Le mot de passe est modifiÃ©.");
            }
        } else {
            response.setStatut(false);
            response.setDescription("Aucun utilisateur n'a Ã©tÃ© trouvÃ©");
        }

        return response;
    }

    //Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String get_SHA_1_SecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    //Nombre utilisateur
    @GetMapping("nombreUser")
    private ServeurResponse nombreUser() {
        ServeurResponse response = new ServeurResponse();
        response.setStatut(true);
        response.setDescription("Nombre utilisateur");
        response.setData(userService.nombreUtilisateur());
        return response;
    }
    //Nombre utilisateur connecte

    @GetMapping("nombreUserConnect")
    private ServeurResponse nombreUserConnectee() {
        ServeurResponse response = new ServeurResponse();
        response.setStatut(true);
        response.setDescription("Nombre utilisateur connecte");
        response.setData(sessionService.nombreUserConnecter());
        return response;
    }
    //Nombre utilisateur applicaion

    @GetMapping("nombreApplication")
    private ServeurResponse nombreApplication() {
        ServeurResponse response = new ServeurResponse();
        response.setStatut(true);
        response.setDescription("Nombre application");
        response.setData(applicationRepository.nombreAplication());
        return response;
    }
    //nombre module

    @GetMapping("nombreModule")
    private ServeurResponse nombreModule() {
        ServeurResponse response = new ServeurResponse();
        response.setStatut(true);
        response.setDescription("Nombre application");
        response.setData(moduleRepository.nombreModule());
        return response;
    }
    //nombre integrateur

    @GetMapping("nombreIntegrateur")
    private ServeurResponse nombreIntegrateur() {
        ServeurResponse response = new ServeurResponse();
        response.setStatut(true);
        response.setDescription("Nombre integrateur");
        response.setData(userService.nombreUtilisateurByProfil("Integrateur"));
        return response;
    }
    //nombre administrateur

    @GetMapping("nombreAdministrateur")
    private ServeurResponse nombreAdministrateur() {
        ServeurResponse response = new ServeurResponse();
        response.setStatut(true);
        response.setDescription("Nombre administrateur");
        response.setData(userService.nombreUtilisateurByProfil("Administrateur"));
        return response;
    }
    //nombre Commerciaux

    @GetMapping("nombreCommerciaux")
    private ServeurResponse nombreCommerciaux() {
        ServeurResponse response = new ServeurResponse();
        response.setStatut(true);
        response.setDescription("Nombre Commerciaux");
        response.setData(userService.nombreUtilisateurByProfil("Commerciaux"));
        return response;
    }
    //activee compte

    @GetMapping("actived/{id}")
    public ServeurResponse activer(@PathVariable Long id) {
        Utilisateur user = new Utilisateur();
        user = userService.listByUtid(id);
        user.setUtiActived(true);
        userService.addUtilisateur(user);
        ServeurResponse response = new ServeurResponse();
        userService.addUtilisateur(user);
        response.setStatut(true);
        response.setDescription("Votre compte a été activé");
        response.setData(user);
        return response;
    }
    //desactivee compte

    @GetMapping("desactived/{id}")
    public ServeurResponse desactiver(@PathVariable Long id) {
        Utilisateur user = new Utilisateur();
        user = userService.listByUtid(id);
        user.setUtiActived(false);
        userService.addUtilisateur(user);
        ServeurResponse response = new ServeurResponse();
        //userRepository.save(user);
        response.setStatut(true);
        response.setDescription("Votre compte a été desactivé");
        response.setData(user);
        return response;
    }

    @GetMapping("verifySession/{username}")
    public ServeurResponse verifySession(@PathVariable String username) {
        ServeurResponse response = new ServeurResponse();
        int n = sessionService.VerifierSession(username);
        if (n >= 1) {
            response.setStatut(true);
            response.setDescription("Déja connecté");
            response.setData(n);
        } else {
            response.setStatut(false);
            response.setDescription("Pas connecté");
            response.setData(n);
        }
        return response;
    }

    @PostMapping("/upload")
    public ServeurResponse insertExcel(@RequestParam("excelFile") MultipartFile file) {
        ArrayList<String> values = new ArrayList<>();
        ServeurResponse response = new ServeurResponse();
        //int count;
        //System.out.println("fileUpload" + file);
        try {
            InputStream input = new ByteArrayInputStream(file.getBytes());
            //POIFSFileSystem fs = new POIFSFileSystem(input);
            XSSFWorkbook wb = new XSSFWorkbook(input);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator rows = sheet.rowIterator();

            //to iterate
            while (rows.hasNext()) {
                values.clear();
                XSSFRow row = (XSSFRow) rows.next();
                System.out.println("getSheet(1)" + row.toString());
                Iterator cells = row.cellIterator();
                //to iterate cell
                while (cells.hasNext()) {
                    XSSFCell cell = (XSSFCell) cells.next();
                    if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                        values.add(Integer.toString((int) cell.getNumericCellValue()));
                    } else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                        values.add(cell.getStringCellValue());
                    }
                }
                //insert data to utilisateur
                Utilisateur utilisateur = new Utilisateur();
                //utilisateur.setUtiId(Long.valueOf(0));
                utilisateur.setUtiPrenom(values.get(0));
                utilisateur.setUtiNom(values.get(1));
                utilisateur.setUtiUsername(values.get(2));
                utilisateur.setUtiTelephone(values.get(3));
                utilisateur.setUtiEmail(values.get(4));
                utilisateur.setUtiAdresse(values.get(5));
                System.out.println("getSheet(1)" + utilisateur.getUtiPrenom());
                if (userService.findUsersByUtiEmail(utilisateur.getUtiEmail()) < 1) {
                    if (userService.findUsersByUtiUsername(utilisateur.getUtiUsername()) < 1) {
                    	userService.addUtilisateur(utilisateur);
                        response.setStatut(true);
                        response.setDescription("Enregistrement réussi");
                        response.setData(utilisateur);
                    } else {
                        response.setStatut(false);
                        response.setDescription("Le nom d'utilisateur est déjà attribué.");
                        response.setData(null);
                    }
                } else {
                    response.setStatut(false);
                    response.setDescription("L'email est déjà attribué.");
                    response.setData(null);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //insert data

        return response;
    }

    @PostMapping("updateLangue")
    public ServeurResponse updateLangueUser(@RequestBody Utilisateur user) {

        ServeurResponse response = new ServeurResponse();

        Utilisateur user1 = userService.findByUtiUsername(user.getUtiUsername());
        System.out.println("user1 check:" + user.getUtiUsername());
        if ((user1 != null)) {
            user1.setUti_lng_id(user.getUti_lng_id());
            this.userService.addUtilisateur(user1);
            response.setStatut(true);
            response.setDescription("Langue user changé avec succès");
            return response;
        } else {
            response.setStatut(false);
            response.setDescription("Langue user non changés");
            return response;
        }

    }

    @PostMapping("updateTheme")
    public ServeurResponse updateThemeUser(@RequestBody Utilisateur user) {

        ServeurResponse response = new ServeurResponse();
        Utilisateur user1 = userService.findByUtiUsername(user.getUtiUsername());
        System.out.println("user1 check:" + user.getUtiUsername());
        if ((user1 != null)) {
            user1.setUti_thm_id(user.getUti_thm_id());
            this.userService.addUtilisateur(user1);
            response.setStatut(true);
            response.setDescription("Theme user changé avec succès");
            return response;
        } else {
            response.setStatut(false);
            response.setDescription("Theme user non changés");
            return response;
        }
    }
}
