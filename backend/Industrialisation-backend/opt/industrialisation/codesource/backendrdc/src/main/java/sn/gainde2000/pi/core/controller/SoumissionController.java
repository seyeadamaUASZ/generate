
package sn.gainde2000.pi.core.controller;

import sn.gainde2000.pi.core.entity.Soumission;
import sn.gainde2000.pi.core.service.ISoumissionService;
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
public class SoumissionController {

 @Autowired 
ISoumissionService soumissionService;
@GetMapping("soumission/list/{owner}")
    public ServeurResponse getAllSoumission(@PathVariable Long owner) {
        Iterable<Soumission> soumission = soumissionService.listSoumissionById(owner);
        ServeurResponse response = new ServeurResponse();
        if (soumission == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {
            
            response.setStatut(true);
            response.setData(soumission);
           response.setDescription("Données récupérées.");
        }
        return response;
   }

@GetMapping("soumission/status/{id}/{status}")
public ServeurResponse updateStatusSoumission(@PathVariable Long id,@PathVariable Long status) {
    Soumission soumission = soumissionService.getOneSoumission(id);
    soumission.setStatus(status);
    soumission=soumissionService.save(soumission);
    ServeurResponse response = new ServeurResponse();
    if (soumission == null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(soumission);
       response.setDescription("Données récupérées.");
    }
    return response;
}@GetMapping("soumission/task/{poowner}")
public ServeurResponse getAllTask(@PathVariable Long poowner) {
    Iterable<Soumission> soumission = soumissionService.listSoumission(poowner);
    ServeurResponse response = new ServeurResponse();
    if (soumission== null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(soumission);
       response.setDescription("Données récupérées.");
    }
    return response;
} @PostMapping("soumission/create")
public ServeurResponse create(@RequestBody Soumission soumission) {

ServeurResponse response = new ServeurResponse();

soumissionService.save(soumission);
response.setStatut(true);
response.setDescription("Enregistrement réussi");
response.setData(soumission);

return response;
}
@PostMapping("soumission/update")
     public ServeurResponse update(@RequestBody Soumission soumission) {
          ServeurResponse response = new ServeurResponse();

          soumissionService.save(soumission);
          response.setStatut(true);
               response.setDescription("Données mis à jour");
          return response;
     }
 @PostMapping("soumission/delete")
     public ServeurResponse delete(@RequestBody Soumission soumission) {
          ServeurResponse response = new ServeurResponse();
          soumissionService.delete(soumission);
          response.setStatut(true);
          response.setDescription("Données supprimé avec succès");
          return response;
     }
 @PostMapping("soumission/createfile")
    public ServeurResponse create(HttpServletRequest request, @RequestParam("soumission") MultipartFile file) {

        ServeurResponse response = new ServeurResponse();

        Soumission soumission;
        try {
            soumission = new ObjectMapper().readValue(
                    request.getParameter("soumission"), new TypeReference<Soumission>() {
            }
            );

            soumission.setCarte grise(file.getBytes());
soumission.setExtention(file.getContentType());
soumissionService.save(soumission);
            response.setStatut(true);
            response.setDescription("Enregistrement réussi");
            response.setData(soumission);
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
 @GetMapping("soumission/downloadFile/{id}")
	public ResponseEntity<ByteArrayResource> downloadfile(@PathVariable Long id,  HttpServletResponse response){
	 
		Optional<Soumission> data = soumissionService.findById(id);
             File file = new File(data.get().getCarte grise().toLowerCase());
             System.out.println(file.getName());
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename="+file.getName())
				.body(new ByteArrayResource(data.get().getCarte grise()));
             
            
	}}