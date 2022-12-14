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

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

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

    //String certLocation = "/opt/certificat/certificat.p12";
    String certLocation = "";
    //String url = app.getUrlOpay();
    char[] password = "orbusOrbus".toCharArray();

    public MoyenPaiementController() {
        String SE = System.getProperty("os.name").toLowerCase();
        if (SE.indexOf("nux") >= 0) {
            this.certLocation = "/opt/certificat/certificat.p12";
            //this.certLocation = url;

        } else {
            this.certLocation = System.getProperty("user.dir") + "/opt/certificat/certificat.p12";
            //this.certLocation = System.getProperty("user.dir") + url;

        }
    }

    //fonction de r??cup??ration des moyens de paiement
    @GetMapping("moyenPaiements")
    public ServeurResponse getAllMoyenPaiement() {
        Iterable<MoyenPaiement> moyenPaiement = imoyenPaimentService.getListMoyen();
        ServeurResponse response = new ServeurResponse();
        if (moyenPaiement == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune ??l??ment trouv??.");

        } else {

            response.setStatut(true);
            response.setData(moyenPaiement);
            response.setDescription("Donn??es r??cup??r??es.");
        }
        return response;
    }

    public MoyenPaiementController(ImoyenPaimentService imoyenPaimentService) {
        this.imoyenPaimentService = imoyenPaimentService;
    }

    //Cr??er un moyen de paiement
    @PostMapping("moyenPaiement")
    public ServeurResponse create(@RequestBody MoyenPaiement moyenPaiement) {

        ServeurResponse response = new ServeurResponse();

        imoyenPaimentService.save(moyenPaiement);
        response.setStatut(true);
        response.setDescription("Enregistrement r??ussi");
        response.setData(moyenPaiement);

        return response;
    }

    //recuperation par id des moyens de paiement
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

    //fonction de cryptage du message re??u envoy?? ?? opay
    @PostMapping("moyenPaiement/crypter")
    public ServeurResponse encryptPayeur(@RequestBody Payeur objet) {
        ServeurResponse response = new ServeurResponse();
        System.out.println("---------objet------" + objet);
        KeyStore ks = getCertificat(this.certLocation, password);
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
        String messageSansCaractere = EnleverCaractereSpeciaux(encryptedMessage);
        System.out.println("Message decrypte sans caract??re = " + messageSansCaractere);

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
            response.setDescription("??l??ment crypt?? avec succes");
        } else {
            response.setStatut(true);
            response.setData(app.getUrlOpay() + messageSansCaractere);
            response.setDescription("v??rification d'un paiement");

        }

        return response;
    }
    //fonction de d??cryptage du message encod?? re??u opay

    @PostMapping("moyenPaiement/decrypter")
    public ServeurResponse decryptPayeur(@RequestBody String objet) {
        ServeurResponse response = new ServeurResponse();
        System.out.println("---------objet------" + objet);
        KeyStore ks = getCertificat(this.certLocation, this.password);

        System.out.println("Message = " + objet);

        PublicKey publickey = getPublicKey(ks);

        PrivateKey privatekey = getPrivateKey(ks, this.password);
        String messageDechiffre = decryptMessage(privatekey, objet);
        System.out.println("Message decrypte = " + messageDechiffre);
        response.setStatut(true);
        response.setData(messageDechiffre);
        response.setDescription("??l??ment d??crypt?? avec succes");
        return response;
    }

    //D??crypte le message re??u de opay et envoie une notification au payeur pour informer le statut de la transaction
    @PostMapping("paiement/notification")
    public ServeurResponse notificationPaiementHandler(@RequestBody String opayout) {
        JSONObject jsopay = new JSONObject(opayout);
        String messageCrypte = jsopay.getString("opayout");
        ServeurResponse response = new ServeurResponse();
        KeyStore ks = getCertificat(this.certLocation, this.password);
        PrivateKey privatekey = getPrivateKey(ks, this.password);
        String messageDechiffre = decryptMessage(privatekey, messageCrypte);
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
            String statPaiem = "";
            if (statutPaiement == 1) {
                statPaiem = "sucess";
            } else {
                statPaiem = "en cours";
            }
            pa.setPaiStatusTransaction(statPaiem);
            pa.setPaiReferencePaiement(numeroPaiement);
            iPaiementService.payer(pa);
            response.setStatut(true);
            response.setDescription("Notification paiement mis ?? jour");
            response.setData(messageDechiffre);
        }

        return response;
    }
    //fonction de r??cup??ration des certificats

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

    //Recup??ration de la cl?? publique suivant le keyStore
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

    //Recup??ration de la cl?? prive suivant le keyStore
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

    //enl??ve les caract??res sp??ciaux sur les donn??es encod??es
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
