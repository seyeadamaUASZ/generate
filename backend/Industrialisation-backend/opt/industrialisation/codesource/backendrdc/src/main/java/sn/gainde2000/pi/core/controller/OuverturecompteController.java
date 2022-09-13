
package sn.gainde2000.pi.core.controller;

import sn.gainde2000.pi.core.entity.Ouverturecompte;
import sn.gainde2000.pi.core.service.IOuverturecompteService;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotNull;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.ws.rs.core.HttpHeaders;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.File;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
@RestController
public class OuverturecompteController {

 @Autowired 
IOuverturecompteService ouverturecompteService;
@GetMapping("ouverturecompte/list/{owner}")
    public ServeurResponse getAllouverturecompte(@PathVariable Long owner) {
        Iterable<Ouverturecompte> ouverturecompte = ouverturecompteService.listOuverturecompteById(owner);
        ServeurResponse response = new ServeurResponse();
        if (ouverturecompte == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {
            
            response.setStatut(true);
            response.setData(ouverturecompte);
           response.setDescription("Données récupérées.");
        }
        return response;
   }

@GetMapping("ouverturecompte/status/{id}/{status}")
public ServeurResponse updateStatusOuverturecompte(@PathVariable Long id,@PathVariable Long status) {
    Ouverturecompte ouverturecompte = ouverturecompteService.getOneOuverturecompte(id);
    ouverturecompte.setStatus(status);
    ouverturecompte=ouverturecompteService.save(ouverturecompte);
    ServeurResponse response = new ServeurResponse();
    if (ouverturecompte == null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(ouverturecompte);
       response.setDescription("Données récupérées.");
    }
    return response;
}@GetMapping("ouverturecompte/task/{poowner}")
public ServeurResponse getAllTask(@PathVariable Long poowner) {
    Iterable<Ouverturecompte> ouverturecompte = ouverturecompteService.listOuverturecompte(poowner);
    ServeurResponse response = new ServeurResponse();
    if (ouverturecompte== null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(ouverturecompte);
       response.setDescription("Données récupérées.");
    }
    return response;
}@GetMapping("ouverturecompte/list")
    public ServeurResponse getAllouverturecompte() {
        Iterable<Ouverturecompte> ouverturecompte = ouverturecompteService.findAll();
        ServeurResponse response = new ServeurResponse();
   if (ouverturecompte == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

 } else {
      response.setStatut(true);
      response.setData(ouverturecompte);
         response.setDescription("Données récupérées.");
        }
        return response;
}
 @PostMapping("ouverturecompte/delete")
     public ServeurResponse delete(@RequestBody Ouverturecompte ouverturecompte) {
          ServeurResponse response = new ServeurResponse();
          ouverturecompteService.delete(ouverturecompte);
          response.setStatut(true);
          response.setDescription("Données supprimé avec succès");
          return response;
}
@PostMapping(value="ouverturecompte/create",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},produces = MediaType.APPLICATION_JSON_VALUE)
public ServeurResponse createFiles(HttpServletRequest request , MultipartFile cni , MultipartFile certificationresidence){
ServeurResponse response = new ServeurResponse();
Ouverturecompte ouverturecompte;
try{
ouverturecompte = new ObjectMapper().readValue(
request.getParameter("ouverturecompte"), new TypeReference<Ouverturecompte>(){}
);}
catch(Exception ex){
 ex.printStackTrace();}
try {
ouverturecompte.setCni(cni.getBytes());
ouverturecompte.setCertificationresidence(certificationresidence.getBytes());
 ouverturecompteService.save(ouverturecompte);
            response.setStatut(true);
            response.setDescription("Enregistrement réussi");
            response.setData(ouverturecompte);
} catch (IOException e1) {
 e1.printStackTrace();
response.setStatut(false);
 }
 return response;}
 @GetMapping("ouverturecompte/downloadFile/cni/{id}")
	public ResponseEntity<ByteArrayResource> downloadfileCni(@PathVariable String cni,@PathVariable Long id,  HttpServletResponse response){
	 
		Optional<Ouverturecompte> data = ouverturecompteService.findById(id);
             File file = new File(data.get().getCni().toLowerCase());
             System.out.println(file.getName());
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename="+file.getName())
				.body(new ByteArrayResource(data.get().getCni()));
             
            
	}
 @GetMapping("ouverturecompte/downloadFile/certificationresidence/{id}")
	public ResponseEntity<ByteArrayResource> downloadfileCertificationresidence(@PathVariable String certificationresidence,@PathVariable Long id,  HttpServletResponse response){
	 
		Optional<Ouverturecompte> data = ouverturecompteService.findById(id);
             File file = new File(data.get().getCertificationresidence().toLowerCase());
             System.out.println(file.getName());
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename="+file.getName())
				.body(new ByteArrayResource(data.get().getCertificationresidence()));
             
            
	}
@GetMapping("ouverturecompte/task/traite/{poowner}/{profil}")
    public ServeurResponse getAllTaskTraite(@PathVariable Long poowner, @PathVariable String profil) {
        Iterable<Ouverturecompte> ouverturecompte = null;
        switch (profil) {
case "t1":
ouverturecompte = ouverturecompteService.listOuverturecompteTraitesAssistantclient(poowner);
break;
case "t2":
ouverturecompte = ouverturecompteService.listOuverturecompteTraitesGestionnairecompte(poowner);
break;
        }
        ServeurResponse response = new ServeurResponse();
        if (demandepermis == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(demandepermis);
            response.setDescription("Données récupérées.");
        }
        return response;
    }}