/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.ged.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Utilisateur;
import sn.gainde2000.pi.core.service.IUtilisateur;
import sn.gainde2000.pi.ged.entity.Document;
import sn.gainde2000.pi.ged.entity.PrivilegeSigner;
import sn.gainde2000.pi.ged.entity.SignatureDocument;
import sn.gainde2000.pi.ged.entity.StatusDocument;
import sn.gainde2000.pi.ged.service.IDocumentService;
import sn.gainde2000.pi.ged.service.IPrivilegeSignerServce;
import sn.gainde2000.pi.ged.service.ISignatureDocument;
import sn.gainde2000.pi.integration.signature.cf.client.test.SOAPClient_3;
import sn.gainde2000.pi.integration.signature.confiancefactory.wsdl_rasign.SignatureResult;

/**
 *
 * @author asow
 */
@RestController
public class DocumentController {

    @Autowired
    IDocumentService documentService;
     
    @Autowired
    IUtilisateur iUtilisateur;
    @Autowired
    ISignatureDocument isignature;
    
    @Autowired 
    IPrivilegeSignerServce iPrivilegeSignerServce;
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;

    @GetMapping("document/list")
    public ServeurResponse getAllDocument() {
        Iterable<Document> document = documentService.findByStatut();
        ServeurResponse response = new ServeurResponse();
        if (document == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(document);
            response.setDescription("Données récupérées.");
        }
        return response;
    }
    
    @GetMapping("document/listByUser/{iduser}")
    public ServeurResponse getAllDocumentByUser(@PathVariable Long iduser) {
        Iterable<Document> document = documentService.getListDocumentUtilisateur(iduser);
        ServeurResponse response = new ServeurResponse();
        if (document == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(document);
            response.setDescription("Données récupérées.");
        }
        return response;
    }
    
    @GetMapping("document/listByUserAsigner/{iduser}")
    public ServeurResponse getAllDocumentByUserAsigner(@PathVariable Long iduser) {
        Iterable<Document> document = documentService.getListDocumentUtilisateurSigner(iduser);
        ServeurResponse response = new ServeurResponse();
        if (document == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(document);
            response.setDescription("Données récupérées.");
        }
        return response;
    }
    
    @GetMapping("document/listByUserAcertifier/{iduser}")
    public ServeurResponse getAllDocumentByUserACertifier(@PathVariable Long iduser) {
        Iterable<Document> document = documentService.getListDocumentUtilisateurCertifier(iduser);
        ServeurResponse response = new ServeurResponse();
        if (document == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(document);
            response.setDescription("Données récupérées.");
        }
        return response;
    }
    @GetMapping("document/listByUserAconsulter/{iduser}")
    public ServeurResponse getAllDocumentByUserAConsulter(@PathVariable Long iduser) {
        Iterable<Document> document = documentService.getListDocumentUtilisateurConsulter(iduser);
        ServeurResponse response = new ServeurResponse();
        if (document == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(document);
            response.setDescription("Données récupérées.");
        }
        return response;
    }
    @PostMapping("document/create")
    public ServeurResponse create(HttpServletRequest request, @RequestParam("document") MultipartFile file, @RequestParam("username") String username, @RequestParam("statusDocument") Long statusDocument) {

        ServeurResponse response = new ServeurResponse();
        Utilisateur utilisateur = iUtilisateur.findByUtiUsername(username);
        Document document;
        try {
            document = new ObjectMapper().readValue(
                    request.getParameter("document"), new TypeReference<Document>() {
            }
            );

            document.setDctBlob(file.getBytes());
            document.setDctType(file.getContentType());
            document.setDctDate(new Date());
            document.setUtilisateur(utilisateur);
  
           if(statusDocument==1L) {
        	   document.setStatusDocument(StatusDocument.SIGNER);
           }
           else if(statusDocument==2L){
        	   document.setStatusDocument(StatusDocument.CERTIFIER);
           }else {
        	   document.setStatusDocument(StatusDocument.CONSULTER);
           }
            document.setStatut(true);
            documentService.saveDocument(document);
            response.setStatut(true);
            response.setDescription("Enregistrement réussi");
            response.setData(document);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @PostMapping("document/delete")
    public ServeurResponse deleteDocument(@RequestBody Document document) {
        ServeurResponse response = new ServeurResponse();
        documentService.supprimer(document);
        response.setStatut(true);
        return response;
    }

    @GetMapping("documentDelete/{id}")
    public ServeurResponse deleteDocumentParStatut(@PathVariable Long id) {
        ServeurResponse response = new ServeurResponse();
        documentService.supprimerDocument(id);
        response.setStatut(true);
        response.setDescription("Document supprimer");
        return response;

    }

    @PostMapping("document/update")
    public ServeurResponse updateDocument(HttpServletRequest request, @RequestParam(value = "document", required = false) MultipartFile file) {
        ServeurResponse response = new ServeurResponse();

        Document document;
        try {
            document = new ObjectMapper().readValue(
                    request.getParameter("document"), new TypeReference<Document>() {
            }
            );

            Document doc = documentService.findBydctId(document.getDctId());
            doc.setDctTitre(document.getDctTitre());
            doc.setDctAuteur(document.getDctAuteur());
            doc.setTypeDocuments(document.getTypeDocuments());
            doc.setDctBlob(document.getDctBlob());
            doc.setDctBlob(file.getBytes());
            documentService.saveDocument(doc);
            response.setStatut(true);
            response.setDescription("Modifier avec success!!!");
            response.setData(document);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @GetMapping("document/{id}")
    public ResponseEntity<ByteArrayResource> consultDocument(@PathVariable Long id, HttpServletResponse response) {
        Document doc = documentService.findBydctId(id);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + doc.getDctAuteur() + "\"")
                .body(new ByteArrayResource(doc.getDctBlob()));

    }

    @PostMapping("document/signer/{username}")
    public ServeurResponse signerDocument(@PathVariable String username,@RequestBody Document document) throws IOException {
    	byte[] doc_signe = null;
        ServeurResponse response = new ServeurResponse();
       Utilisateur utilisateur = iUtilisateur.findByUtiUsername(username);
	SOAPClient_3 tc= new SOAPClient_3();
    SignatureResult sr=tc.callMethd_Signer(5480583,document.getDctBlob());
    System.out.println("le statut:"+sr.getStatus());
    System.out.println(sr.getErroInfo());
    if(sr.getStatus().equals("MSIGN_SRV_STATUS_SUCCESS")) {
    	PrivilegeSigner pri= iPrivilegeSignerServce.findByPrivByDoc(document.getDctId(),utilisateur.getUtiId());
    	pri.setStatus(true);
    	iPrivilegeSignerServce.save(pri);
    	doc_signe=sr.getContent();
    	document.setDctBlob(doc_signe);
    	documentService.saveDocument(document);
    	isignature.save(new SignatureDocument(utilisateur, document));
    	System.out.println("Document sign� longueur:: "+doc_signe.length);
    	//System.out.println("Le Document:: "+doc_signe);
    	  //tc.writeBytesToFile("E://signature//signatureSigner13.pdf", doc_signe);
    	  response.setStatut(true);
          response.setData(document);
          response.setDescription("Document signé .");
   }
    else {
    	 response.setStatut(false);
         response.setDescription("Document non signé");
    }
   

   return response;
  }
}
