
package sn.gainde2000.pi.core.controller;

import sn.gainde2000.pi.core.entity.Traitement;
import sn.gainde2000.pi.core.service.ITraitementService;
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
public class TraitementController {

 @Autowired 
ITraitementService traitementService;
@GetMapping("traitement/list/{owner}")
    public ServeurResponse getAllTraitement(@PathVariable Long owner) {
        Iterable<Traitement> traitement = traitementService.listTraitementById(owner);
        ServeurResponse response = new ServeurResponse();
        if (traitement == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {
            
            response.setStatut(true);
            response.setData(traitement);
           response.setDescription("Données récupérées.");
        }
        return response;
   }

@GetMapping("traitement/status/{id}/{status}")
public ServeurResponse updateStatusTraitement(@PathVariable Long id,@PathVariable Long status) {
    Traitement traitement = traitementService.getOneTraitement(id);
    traitement.setStatus(status);
    traitement=traitementService.save(traitement);
    ServeurResponse response = new ServeurResponse();
    if (traitement == null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(traitement);
       response.setDescription("Données récupérées.");
    }
    return response;
}@GetMapping("traitement/task/{poowner}")
public ServeurResponse getAllTask(@PathVariable Long poowner) {
    Iterable<Traitement> traitement = traitementService.listTraitement(poowner);
    ServeurResponse response = new ServeurResponse();
    if (traitement== null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(traitement);
       response.setDescription("Données récupérées.");
    }
    return response;
} @PostMapping("traitement/create")
public ServeurResponse create(@RequestBody Traitement traitement) {

ServeurResponse response = new ServeurResponse();

traitementService.save(traitement);
response.setStatut(true);
response.setDescription("Enregistrement réussi");
response.setData(traitement);

return response;
}
@PostMapping("traitement/update")
     public ServeurResponse update(@RequestBody Traitement traitement) {
          ServeurResponse response = new ServeurResponse();

          traitementService.save(traitement);
          response.setStatut(true);
               response.setDescription("Données mis à jour");
          return response;
     }
 @PostMapping("traitement/delete")
     public ServeurResponse delete(@RequestBody Traitement traitement) {
          ServeurResponse response = new ServeurResponse();
          traitementService.delete(traitement);
          response.setStatut(true);
          response.setDescription("Données supprimé avec succès");
          return response;
     }
 @PostMapping("traitement/createfile")
    public ServeurResponse create(HttpServletRequest request, @RequestParam("traitement") MultipartFile file) {

        ServeurResponse response = new ServeurResponse();

        Traitement traitement;
        try {
            traitement = new ObjectMapper().readValue(
                    request.getParameter("traitement"), new TypeReference<Traitement>() {
            }
            );

            traitement.setFichier(file.getBytes());
traitement.setExtention(file.getContentType());
traitementService.save(traitement);
            response.setStatut(true);
            response.setDescription("Enregistrement réussi");
            response.setData(traitement);
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
 @GetMapping("traitement/downloadFile/{id}")
	public ResponseEntity<ByteArrayResource> downloadfile(@PathVariable Long id,  HttpServletResponse response){
	 
		Optional<Traitement> data = traitementService.findById(id);
             File file = new File(data.get().getFichier().toLowerCase());
             System.out.println(file.getName());
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename="+file.getName())
				.body(new ByteArrayResource(data.get().getFichier()));
             
            
	}}