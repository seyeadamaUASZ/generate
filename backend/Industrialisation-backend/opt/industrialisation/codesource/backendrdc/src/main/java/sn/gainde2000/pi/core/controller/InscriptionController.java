
package sn.gainde2000.pi.core.controller;

import sn.gainde2000.pi.core.entity.Inscription;
import sn.gainde2000.pi.core.service.IInscriptionService;
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
public class InscriptionController {

 @Autowired 
IInscriptionService inscriptionService;
@GetMapping("inscription/list")
    public ServeurResponse getAllinscription() {
        Iterable<Inscription> inscription = inscriptionService.findAll();
        ServeurResponse response = new ServeurResponse();
        if (inscription == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {
            
            response.setStatut(true);
            response.setData(inscription);
           response.setDescription("Données récupérées.");
        }
        return response;
   }
 @PostMapping("inscription/create")
public ServeurResponse create(@RequestBody Inscription inscription) {

ServeurResponse response = new ServeurResponse();

inscriptionService.save(inscription);
response.setStatut(true);
response.setDescription("Enregistrement réussi");
response.setData(inscription);

return response;
}
@PostMapping("inscription/update")
     public ServeurResponse update(@RequestBody Inscription inscription) {
          ServeurResponse response = new ServeurResponse();

          inscriptionService.save(inscription);
          response.setStatut(true);
               response.setDescription("Données mis à jour");
          return response;
     }
 @PostMapping("inscription/delete")
     public ServeurResponse delete(@RequestBody Inscription inscription) {
          ServeurResponse response = new ServeurResponse();
          inscriptionService.delete(inscription);
          response.setStatut(true);
          response.setDescription("Données supprimé avec succès");
          return response;
     }
 @PostMapping("inscription/createfile")
    public ServeurResponse create(HttpServletRequest request, @RequestParam("inscription") MultipartFile file) {

        ServeurResponse response = new ServeurResponse();

        Inscription inscription;
        try {
            inscription = new ObjectMapper().readValue(
                    request.getParameter("inscription"), new TypeReference<Inscription>() {
            }
            );

            inscription.setProfile(file.getBytes());
inscription.setExtention(file.getContentType());
inscriptionService.save(inscription);
            response.setStatut(true);
            response.setDescription("Enregistrement réussi");
            response.setData(inscription);
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
 @GetMapping("inscription/downloadFile/{id}")
	public ResponseEntity<ByteArrayResource> downloadfile(@PathVariable Long id,  HttpServletResponse response){
	 
		Optional<Inscription> data = inscriptionService.findById(id);
             File file = new File(data.get().getProfile().toLowerCase());
             System.out.println(file.getName());
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename="+file.getName())
				.body(new ByteArrayResource(data.get().getProfile()));
             
            
	}}