
package sn.gainde2000.pi.core.controller;

import sn.gainde2000.pi.core.entity.Assistantclient;
import sn.gainde2000.pi.core.service.IAssistantclientService;
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
public class AssistantclientController {

 @Autowired 
IAssistantclientService assistantclientService;
@GetMapping("assistantclient/list/{owner}")
    public ServeurResponse getAllassistantclient(@PathVariable Long owner) {
        Iterable<Assistantclient> assistantclient = assistantclientService.listAssistantclientById(owner);
        ServeurResponse response = new ServeurResponse();
        if (assistantclient == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {
            
            response.setStatut(true);
            response.setData(assistantclient);
           response.setDescription("Données récupérées.");
        }
        return response;
   }

@GetMapping("assistantclient/status/{id}/{status}")
public ServeurResponse updateStatusAssistantclient(@PathVariable Long id,@PathVariable Long status) {
    Assistantclient assistantclient = assistantclientService.getOneAssistantclient(id);
    assistantclient.setStatus(status);
    assistantclient=assistantclientService.save(assistantclient);
    ServeurResponse response = new ServeurResponse();
    if (assistantclient == null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(assistantclient);
       response.setDescription("Données récupérées.");
    }
    return response;
}@GetMapping("assistantclient/task/{poowner}")
public ServeurResponse getAllTask(@PathVariable Long poowner) {
    Iterable<Assistantclient> assistantclient = assistantclientService.listAssistantclient(poowner);
    ServeurResponse response = new ServeurResponse();
    if (assistantclient== null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(assistantclient);
       response.setDescription("Données récupérées.");
    }
    return response;
}@GetMapping("assistantclient/list")
    public ServeurResponse getAllassistantclient() {
        Iterable<Assistantclient> assistantclient = assistantclientService.findAll();
        ServeurResponse response = new ServeurResponse();
   if (assistantclient == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

 } else {
      response.setStatut(true);
      response.setData(assistantclient);
         response.setDescription("Données récupérées.");
        }
        return response;
}
 @PostMapping("assistantclient/delete")
     public ServeurResponse delete(@RequestBody Assistantclient assistantclient) {
          ServeurResponse response = new ServeurResponse();
          assistantclientService.delete(assistantclient);
          response.setStatut(true);
          response.setDescription("Données supprimé avec succès");
          return response;
}
 @PostMapping("assistantclient/create",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},produces = MediaType.APPLICATION_JSON_VALUE)
   public ServeurResponse create(HttpServletRequest request) {

        ServeurResponse response = new ServeurResponse();

        Assistantclient assistantclient;
        try {
            assistantclient = new ObjectMapper().readValue(
request.getParameter("assistantclient"), new TypeReference<Assistantclient>() {
            }
            );

            assistantclientService.save(assistantclient);
            response.setStatut(true);
            response.setDescription("Enregistrement réussi");
            response.setData(assistantclient);
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
}