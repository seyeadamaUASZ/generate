package sn.gainde2000.pi.ged.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Utilisateur;
import sn.gainde2000.pi.core.service.IUtilisateur;
import sn.gainde2000.pi.ged.entity.Document;
import sn.gainde2000.pi.ged.entity.PrivilegeSigner;
import sn.gainde2000.pi.ged.entity.StatusDocument;
import sn.gainde2000.pi.ged.service.IDocumentService;
import sn.gainde2000.pi.ged.service.IPrivilegeSignerServce;

@RestController
public class PrivilegeSignerController {
  @Autowired
	IPrivilegeSignerServce iPrivilegeSignerServce;
 @Autowired
  IUtilisateur iutilisateur;
 @Autowired
 IDocumentService idoc;

    @PostMapping("privilegeSigner/create")
    public PrivilegeSigner signer(HttpServletRequest request,@RequestParam("id")Long id,@RequestParam("document") Long  document){

    	PrivilegeSigner p = new PrivilegeSigner();
    
       Utilisateur utilisateur= iutilisateur.getUtilisateur(id);
       p.setUtilisateur(utilisateur);
        Document doc = idoc.findBydctId(document);
       p.setDocument(doc);
       p.setStatusDocument(StatusDocument.SIGNER);
       p.setStatus(false);
       return iPrivilegeSignerServce.save(p);
    } 
    
    @PostMapping("privilegeCertifier/create")
    public PrivilegeSigner certifier(HttpServletRequest request,@RequestParam("id")Long id,@RequestParam("document") Long  document){

    	PrivilegeSigner p = new PrivilegeSigner();
    
       Utilisateur utilisateur= iutilisateur.getUtilisateur(id);
       p.setUtilisateur(utilisateur);
        Document doc = idoc.findBydctId(document);
       p.setDocument(doc);
       p.setStatusDocument(StatusDocument.CERTIFIER);
       p.setStatus(false);
       return iPrivilegeSignerServce.save(p);
    } 
    @PostMapping("privilegeConsulter/create")
    public PrivilegeSigner consulter(HttpServletRequest request,@RequestParam("id")Long id,@RequestParam("document") Long  document){

    	PrivilegeSigner p = new PrivilegeSigner();
    
       Utilisateur utilisateur= iutilisateur.getUtilisateur(id);
       p.setUtilisateur(utilisateur);
        Document doc = idoc.findBydctId(document);
       p.setDocument(doc);
       p.setStatusDocument(StatusDocument.CONSULTER);
       p.setStatus(false);
       return iPrivilegeSignerServce.save(p);
    } 
    
    @GetMapping("privilegeSigner/list/{iduser}")
    public ServeurResponse getAllDocumentByUser(@PathVariable Long iduser) {
        Iterable<Document> document = iPrivilegeSignerServce.listDocumentSigner(iduser);
        ServeurResponse response = new ServeurResponse();
        if (document == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune ??l??ment trouv??.");

        } else {

            response.setStatut(true);
            response.setData(document);
            response.setDescription("Donn??es r??cup??r??es.");
        }
        return response;
    }
    
    @GetMapping("privilegeConsulter/list/{iduser}")
    public ServeurResponse getAllDocumentConsulter(@PathVariable Long iduser) {
        Iterable<Document> document = iPrivilegeSignerServce.listDocumentConsulter(iduser);
        ServeurResponse response = new ServeurResponse();
        if (document == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune ??l??ment trouv??.");

        } else {

            response.setStatut(true);
            response.setData(document);
            response.setDescription("Donn??es r??cup??r??es.");
        }
        return response;
    }
    
    @GetMapping("privilegeCertifier/list/{iduser}")
    public ServeurResponse getAllDocumentCertifier(@PathVariable Long iduser) {
        Iterable<Document> document = iPrivilegeSignerServce.listDocumentCertifier(iduser);
        ServeurResponse response = new ServeurResponse();
        if (document == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune ??l??ment trouv??.");

        } else {

            response.setStatut(true);
            response.setData(document);
            response.setDescription("Donn??es r??cup??r??es.");
        }
        return response;
    }
}
