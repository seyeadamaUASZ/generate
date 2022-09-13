
package sn.gainde2000.pi.core.controller;

import sn.gainde2000.pi.core.entity.Validation;
import sn.gainde2000.pi.core.service.IValidationService;
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
public class ValidationController {

 @Autowired 
IValidationService validationService;
@GetMapping("validation/list/{owner}")
    public ServeurResponse getAllvalidation(@PathVariable Long owner) {
        Iterable<Validation> validation = validationService.listValidationById(owner);
        ServeurResponse response = new ServeurResponse();
        if (validation == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {
            
            response.setStatut(true);
            response.setData(validation);
           response.setDescription("Données récupérées.");
        }
        return response;
   }

@GetMapping("validation/status/{id}/{status}")
public ServeurResponse updateStatusValidation(@PathVariable Long id,@PathVariable Long status) {
    Validation validation = validationService.getOneValidation(id);
    validation.setStatus(status);
    validation=validationService.save(validation);
    ServeurResponse response = new ServeurResponse();
    if (validation == null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(validation);
       response.setDescription("Données récupérées.");
    }
    return response;
}@GetMapping("validation/task/{poowner}")
public ServeurResponse getAllTask(@PathVariable Long poowner) {
    Iterable<Validation> validation = validationService.listValidation(poowner);
    ServeurResponse response = new ServeurResponse();
    if (validation== null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(validation);
       response.setDescription("Données récupérées.");
    }
    return response;
} @PostMapping("validation/create")
public ServeurResponse create(@RequestBody Validation validation) {

ServeurResponse response = new ServeurResponse();

validationService.save(validation);
response.setStatut(true);
response.setDescription("Enregistrement réussi");
response.setData(validation);

return response;
}
@PostMapping("validation/update")
     public ServeurResponse update(@RequestBody Validation validation) {
          ServeurResponse response = new ServeurResponse();

          validationService.save(validation);
          response.setStatut(true);
               response.setDescription("Données mis à jour");
          return response;
     }
 @PostMapping("validation/delete")
     public ServeurResponse delete(@RequestBody Validation validation) {
          ServeurResponse response = new ServeurResponse();
          validationService.delete(validation);
          response.setStatut(true);
          response.setDescription("Données supprimé avec succès");
          return response;
     }
}