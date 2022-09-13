
package sn.gainde2000.pi.core.controller;

import sn.gainde2000.pi.core.entity.Gestionnairecompte;
import sn.gainde2000.pi.core.service.IGestionnairecompteService;
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
public class GestionnairecompteController {

 @Autowired 
IGestionnairecompteService gestionnairecompteService;
@GetMapping("gestionnairecompte/list/{owner}")
    public ServeurResponse getAllgestionnairecompte(@PathVariable Long owner) {
        Iterable<Gestionnairecompte> gestionnairecompte = gestionnairecompteService.listGestionnairecompteById(owner);
        ServeurResponse response = new ServeurResponse();
        if (gestionnairecompte == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {
            
            response.setStatut(true);
            response.setData(gestionnairecompte);
           response.setDescription("Données récupérées.");
        }
        return response;
   }

@GetMapping("gestionnairecompte/status/{id}/{status}")
public ServeurResponse updateStatusGestionnairecompte(@PathVariable Long id,@PathVariable Long status) {
    Gestionnairecompte gestionnairecompte = gestionnairecompteService.getOneGestionnairecompte(id);
    gestionnairecompte.setStatus(status);
    gestionnairecompte=gestionnairecompteService.save(gestionnairecompte);
    ServeurResponse response = new ServeurResponse();
    if (gestionnairecompte == null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(gestionnairecompte);
       response.setDescription("Données récupérées.");
    }
    return response;
}@GetMapping("gestionnairecompte/task/{poowner}")
public ServeurResponse getAllTask(@PathVariable Long poowner) {
    Iterable<Gestionnairecompte> gestionnairecompte = gestionnairecompteService.listGestionnairecompte(poowner);
    ServeurResponse response = new ServeurResponse();
    if (gestionnairecompte== null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(gestionnairecompte);
       response.setDescription("Données récupérées.");
    }
    return response;
}@GetMapping("gestionnairecompte/list")
    public ServeurResponse getAllgestionnairecompte() {
        Iterable<Gestionnairecompte> gestionnairecompte = gestionnairecompteService.findAll();
        ServeurResponse response = new ServeurResponse();
   if (gestionnairecompte == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

 } else {
      response.setStatut(true);
      response.setData(gestionnairecompte);
         response.setDescription("Données récupérées.");
        }
        return response;
}
 @PostMapping("gestionnairecompte/delete")
     public ServeurResponse delete(@RequestBody Gestionnairecompte gestionnairecompte) {
          ServeurResponse response = new ServeurResponse();
          gestionnairecompteService.delete(gestionnairecompte);
          response.setStatut(true);
          response.setDescription("Données supprimé avec succès");
          return response;
}
 @PostMapping("gestionnairecompte/create",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},produces = MediaType.APPLICATION_JSON_VALUE)
   public ServeurResponse create(HttpServletRequest request) {

        ServeurResponse response = new ServeurResponse();

        Gestionnairecompte gestionnairecompte;
        try {
            gestionnairecompte = new ObjectMapper().readValue(
request.getParameter("gestionnairecompte"), new TypeReference<Gestionnairecompte>() {
            }
            );

            gestionnairecompteService.save(gestionnairecompte);
            response.setStatut(true);
            response.setDescription("Enregistrement réussi");
            response.setData(gestionnairecompte);
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