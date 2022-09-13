package sn.gainde2000.pi.paiement.controller;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Enumeration;
import java.util.Optional;
import java.util.regex.PatternSyntaxException;
import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.client.Entity.json;
import org.apache.tomcat.util.json.JSONParser;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sn.gainde2000.pi.config.AppProperties;

import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.paiement.entity.MoyenPaiement;
import sn.gainde2000.pi.paiement.entity.Payeur;
import sn.gainde2000.pi.core.service.ImoyenPaimentService;
import sn.gainde2000.pi.paiement.entity.NotificationPayeur;
import sn.gainde2000.pi.paiement.entity.Paiement;
import sn.gainde2000.pi.paiement.service.IPaiementService;

@RestController
@CrossOrigin("*")
public class MoyenPaiementController {

    @Autowired
    ImoyenPaimentService imoyenPaimentService;
    @Autowired
    IPaiementService iPaiementService;
    @Autowired
    private AppProperties app;

    //@Autowired
    //Liste des moyenPaiement
    String certLocation = "/opt/certificat/certificat.p12";
    char[] password = "orbusOrbus".toCharArray();

    public MoyenPaiementController() {
        String SE = System.getProperty("os.name").toLowerCase();
        if (SE.indexOf("nux") >= 0) {
            this.certLocation = "/opt/certificat/certificat.p12";

        } else {
            this.certLocation = System.getProperty("user.dir") + "/opt/certificat/certificat.p12";
        }
    }

    @GetMapping("moyenPaiements")
    public ServeurResponse getAllMoyenPaiement() {
        Iterable<MoyenPaiement> moyenPaiement = imoyenPaimentService.getListMoyen();
        ServeurResponse response = new ServeurResponse();
        if (moyenPaiement == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(moyenPaiement);
            response.setDescription("Données récupérées.");
        }
        return response;
    }
    //Ajouter langue

    public MoyenPaiementController(ImoyenPaimentService imoyenPaimentService) {
        this.imoyenPaimentService = imoyenPaimentService;
    }

    @PostMapping("moyenPaiement")
    public ServeurResponse create(@RequestBody MoyenPaiement moyenPaiement) {

        ServeurResponse response = new ServeurResponse();

        imoyenPaimentService.save(moyenPaiement);
        response.setStatut(true);
        response.setDescription("Enregistrement réussi");
        response.setData(moyenPaiement);

        return response;
    }
    //recuperation par id

    @GetMapping("moyenPaiement/{id}")
    public Optional<MoyenPaiement> findById(@PathVariable Long id) {
        return imoyenPaimentService.findByMoyenId(id);
    }
    //Supprimer un module

    @PostMapping("moyenPaiement/delete")
    public ServeurResponse deleteMoyenPaiement(@RequestBody MoyenPaiement moyenPaiement) {
        ServeurResponse response = new ServeurResponse();
        imoyenPaimentService.delete(moyenPaiement);
        response.setStatut(true);
        return response;
    }

    @PostMapping("moyenPaiement/crypter")
    public ServeurResponse encryptPayeur(@RequestBody Payeur objet) {
        ServeurResponse response = new ServeurResponse();
        //String certLocation = "C:\\Users\\yougadieng\\Desktop\\paiement_Bass\\certificat.p12";
        System.out.println("---------objet------" + objet);
        KeyStore ks = getCertificat(this.certLocation, password);
        //String message1 = "{\"idFacture\":\"150\",\"montantFacture\":\"100\",\"nomPayeur\":\"Dieng\",\"prenomPayeur\":\"Youga\"}";
        String message = "{"
                + "\"idFacture\":" + "\"" + objet.getIdFacture() + "\","
                + "\"montantFacture\":" + "\"" + objet.getMontantFacture() + "\","
                + "\"nomPayeur\":" + "\"" + objet.getNomPayeur() + "\","
                + "\"prenomPayeur\":" + "\"" + objet.getPrenomPayeur() + "\""
                + "}";
        System.out.println("Message = " + objet.toString());
        PublicKey publickey = getPublicKey(ks);
        String encryptedMessage = cryptMessage(publickey, message);
        System.out.println("Message crypte = " + encryptedMessage);
        //message crypté sans caractere
        String messageSansCaractere = EnleverCaractereSpeciaux(encryptedMessage);
        System.out.println("Message decrypte sans caractère = " + messageSansCaractere);

        PrivateKey privatekey = getPrivateKey(ks, password);
        String messageDechiffre = decryptMessage(privatekey, encryptedMessage);
        System.out.println("Message decrypte = " + messageDechiffre);
        Paiement p = new Paiement();
        Paiement p1 = iPaiementService.findByIdFacture(objet.getIdFacture());
        if (p1 == null) {
            p.setPaiIdFacture(objet.getIdFacture());
            p.setPaiDatePaiement(new Date());
            p.setPaiMontant(objet.getMontantFacture());
            p.setPaiPrenomPayeur(objet.getPrenomPayeur());
            p.setPaiNomPayeur(objet.getNomPayeur());
            p.setPaiReferenceClient(objet.getUsernamePayeur());
            p.setPaiStatusTransaction("en cours");
            iPaiementService.payer(p);
            response.setStatut(true);
            response.setData(app.getUrlOpay() + messageSansCaractere);
            response.setDescription("élément crypté avec succes");
        } else {
            response.setStatut(true);
            response.setData(app.getUrlOpay() + messageSansCaractere);
            response.setDescription("vérification d'un paiement");

        }

        return response;
    }

    @PostMapping("moyenPaiement/decrypter")
    public ServeurResponse decryptPayeur(@RequestBody String objet) {
        ServeurResponse response = new ServeurResponse();
        //String certLocation = "C:\\Users\\yougadieng\\Desktop\\paiement_Bass\\certificat.p12";
        System.out.println("---------objet------" + objet);
        KeyStore ks = getCertificat(this.certLocation, this.password);

        //String message = "{\"idFacture\":\"150\",\"montantFacture\":\"100\",\"nomPayeur\":\"Dieng\",\"prenomPayeur\":\"Youga\"}";
        //String message = "{\"idFacture\":\"150\",\"montantFacture\":\"100\",\"nomPayeur\":\"Dieng\",\"prenomPayeur\":\"Youga\"}";
        System.out.println("Message = " + objet);

        PublicKey publickey = getPublicKey(ks);
        //String encryptedMessage = cryptMessage(publickey, message);
        //System.out.println("Message crypte = " + encryptedMessage);
        //message crypté sans caractere

        //String messageSansCaractere=EnleverCaractereSpeciaux(encryptedMessage);
        //System.out.println("Message decrypte sans caractère = " + messageSansCaractere);
        PrivateKey privatekey = getPrivateKey(ks, this.password);
        String messageDechiffre = decryptMessage(privatekey, objet);
        System.out.println("Message decrypte = " + messageDechiffre);
        response.setStatut(true);
        response.setData(messageDechiffre);
        response.setDescription("élément décrypté avec succes");
        return response;
    }

  /*  @POST
    @Produces(MediaType.TEXT_PLAIN_VALUE)
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Path("paiement/notification")
    public String notificationPaiementHandler(String message) {
        System.out.println("Dans notification service : " + message);
        JSONObject jsonNotification;
        try {
            jsonNotification = new JSONObject(message);
            String encryptedMessage = jsonNotification.getString("val");
            if ((encryptedMessage == null) || (encryptedMessage.trim().equalsIgnoreCase(""))) {
                System.out.println("Erreur message recu!");
                return "KO!";
            } else {
                System.out.println("encryptedMessage : " + encryptedMessage);

                return "OK.";
            }
        } catch (Exception e) {
            System.out.println("Erreur traitement notification paiement! Message = " + e.getMessage());
            return "KO!";
        }
    }*/


    @PostMapping("paiement/notification")
   // public ServeurResponse notifications(@PathVariable String opay) {
      public ServeurResponse notificationPaiementHandler(@RequestBody String opayout) {
        JSONObject jsopay = new JSONObject(opayout);
        String messageCrypte = jsopay.getString("opayout");
        System.out.println("------paiement notification-----" + opayout);
        ServeurResponse response = new ServeurResponse();
        
        System.out.println("le message de notification opay"+opayout);
        System.out.println("------je teste lentree-----" + messageCrypte);
        KeyStore ks = getCertificat(this.certLocation, this.password);
        PrivateKey privatekey = getPrivateKey(ks, this.password);
        String messageDechiffre = decryptMessage(privatekey, messageCrypte);
        System.out.println("Message decrypte = " + messageDechiffre);

        JSONObject jsons = new JSONObject(messageDechiffre);
        System.out.println(jsons.toString());
        Long idFacture = jsons.getLong("idFacture");
        String numeroPaiement = jsons.getString("numeroPaiement");
        int statutPaiement = jsons.getInt("statutPaiement");

        System.out.println(idFacture);
        Paiement pa = iPaiementService.findByIdFacture(idFacture);
        if (pa == null) {
            response.setStatut(false);
            response.setDescription("Id du paiement nexiste pas");
        } else {
            String statPaiem="";
            if(statutPaiement==1){
                statPaiem="sucess";
            }else{
                 statPaiem="en cours";
            }
            pa.setPaiStatusTransaction(statPaiem);
            pa.setPaiReferencePaiement(numeroPaiement);
            iPaiementService.payer(pa);
            response.setStatut(true);
            response.setDescription("Notification paiement mis à jour");
            response.setData(messageDechiffre);
        }

      
        return response;
    }

    public static KeyStore getCertificat(String certLocation, char[] password) {
        KeyStore ks = null;
        Security.addProvider(new BouncyCastleProvider());
        try {
            ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(certLocation), password);
        } catch (Exception e) {
            System.out.println("Erreur getCertificat !");
        }
        return ks;
    }

    //Recupération de la clé publique suivant le keyStore
    public static PublicKey getPublicKey(KeyStore ks) {
        X509Certificate cert = null;
        PublicKey publickey = null;
        try {
            String alias = "";
            Enumeration en = ks.aliases();
            boolean stop = false;
            while (en.hasMoreElements() && !stop) {
                alias = (String) en.nextElement();
                if (ks.isKeyEntry(alias)) {
                    stop = true;
                } else {
                    alias = "";
                }
            }

            cert = (X509Certificate) ks.getCertificate(alias);
            publickey = ks.getCertificate(alias).getPublicKey();
        } catch (Exception e) {
            System.out.println("Erreur getPublicKey !");
        }
        return publickey;
    }

    //Recupération de la clé prive suivant le keyStore
    public static PrivateKey getPrivateKey(KeyStore ks, char[] password) {
        X509Certificate cert = null;
        PrivateKey privatekey = null;
        try {
            String alias = "";
            Enumeration en = ks.aliases();
            boolean stop = false;
            while (en.hasMoreElements() && !stop) {
                alias = (String) en.nextElement();
                if (ks.isKeyEntry(alias)) {
                    stop = true;
                } else {
                    alias = "";
                }
            }

            privatekey = (PrivateKey) ks.getKey(alias, password);
            cert = (X509Certificate) ks.getCertificate(alias);
        } catch (Exception e) {
            System.out.println("Erreur getPrivateKey !");
        }
        return privatekey;
    }

    //Crypt suivant le publickey et le message
    public static String cryptMessage(PublicKey publickey, String message) {
        String encryptedMessage = "";
        try {
            //Seul le mode ECB est possible avec RSA
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            //Publickey est la cle publique du destinataire
            cipher.init(Cipher.ENCRYPT_MODE, publickey, new SecureRandom("nyal rand".getBytes()));
            encryptedMessage = org.apache.commons.codec.binary.Base64.encodeBase64String(cipher.doFinal(message.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Erreur cryptMessage !" + e.getMessage());
            e.printStackTrace();
        }
        return encryptedMessage;
    }

    //Decrypt suivant le privatekey et le message
    public static String decryptMessage(PrivateKey privatekey, String encryptedMessage) {
        String messageDechiffre = "";
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            cipher.init(cipher.DECRYPT_MODE, privatekey);
            messageDechiffre = new String(cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(encryptedMessage)), "UTF-8");
        } catch (Exception e) {
            System.out.println("Erreur decryptMessage!");
        }
        return messageDechiffre;
    }

    public static String EnleverCaractereSpeciaux(String messageAvecCaractere) {
        String MessageSansCaractere = "";
        try {
            MessageSansCaractere = messageAvecCaractere.replaceAll("=", "%3D");//replaces all occurrences of 'a' to 'e' 
            MessageSansCaractere = MessageSansCaractere.replaceAll("/", "%2F");//replaces all occurrences of 'a' to 'e'  %2B
            MessageSansCaractere = MessageSansCaractere.replaceAll("\\+", "%2B");//replaces all occurrences of 'a' to 'e' 

        } catch (PatternSyntaxException e) {
            System.out.println(e.getMessage());
        }

        return MessageSansCaractere;
    }

}
