
package sn.gainde2000.pi.core.controller;

import sn.gainde2000.pi.core.entity.Verification;
import sn.gainde2000.pi.core.service.IVerificationService;
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
public class VerificationController {

 @Autowired 
IVerificationService verificationService;
@GetMapping("verification/list/{owner}")
    public ServeurResponse getAllVerification(@PathVariable Long owner) {
        Iterable<Verification> verification = verificationService.listVerificationById(owner);
        ServeurResponse response = new ServeurResponse();
        if (verification == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {
            
            response.setStatut(true);
            response.setData(verification);
           response.setDescription("Données récupérées.");
        }
        return response;
   }

@GetMapping("verification/status/{id}/{status}")
public ServeurResponse updateStatusVerification(@PathVariable Long id,@PathVariable Long status) {
    Verification verification = verificationService.getOneVerification(id);
    verification.setStatus(status);
    verification=verificationService.save(verification);
    ServeurResponse response = new ServeurResponse();
    if (verification == null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(verification);
       response.setDescription("Données récupérées.");
    }
    return response;
}@GetMapping("verification/task/{poowner}")
public ServeurResponse getAllTask(@PathVariable Long poowner) {
    Iterable<Verification> verification = verificationService.listVerification(poowner);
    ServeurResponse response = new ServeurResponse();
    if (verification== null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(verification);
       response.setDescription("Données récupérées.");
    }
    return response;
} @PostMapping("verification/create")
public ServeurResponse create(@RequestBody Verification verification) {

ServeurResponse response = new ServeurResponse();

verificationService.save(verification);
response.setStatut(true);
response.setDescription("Enregistrement réussi");
response.setData(verification);

return response;
}
@PostMapping("verification/update")
     public ServeurResponse update(@RequestBody Verification verification) {
          ServeurResponse response = new ServeurResponse();

          verificationService.save(verification);
          response.setStatut(true);
               response.setDescription("Données mis à jour");
          return response;
     }
 @PostMapping("verification/delete")
     public ServeurResponse delete(@RequestBody Verification verification) {
          ServeurResponse response = new ServeurResponse();
          verificationService.delete(verification);
          response.setStatut(true);
          response.setDescription("Données supprimé avec succès");
          return response;
     }
}