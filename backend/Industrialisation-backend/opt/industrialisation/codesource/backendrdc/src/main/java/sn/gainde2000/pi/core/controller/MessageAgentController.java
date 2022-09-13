
package sn.gainde2000.pi.core.controller;

import sn.gainde2000.pi.core.entity.MessageAgent;
import sn.gainde2000.pi.core.service.IMessageAgentService;
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
public class MessageAgentController {

 @Autowired 
IMessageAgentService messageagentService;
@GetMapping("messageagent/list/{owner}")
    public ServeurResponse getAllmessageAgent(@PathVariable Long owner) {
        Iterable<MessageAgent> messageagent = messageagentService.listMessageAgentById(owner);
        ServeurResponse response = new ServeurResponse();
        if (messageagent == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {
            
            response.setStatut(true);
            response.setData(messageagent);
           response.setDescription("Données récupérées.");
        }
        return response;
   }

@GetMapping("messageagent/status/{id}/{status}")
public ServeurResponse updateStatusMessageAgent(@PathVariable Long id,@PathVariable Long status) {
    MessageAgent messageagent = messageagentService.getOneMessageAgent(id);
    messageagent.setStatus(status);
    messageagent=messageagentService.save(messageagent);
    ServeurResponse response = new ServeurResponse();
    if (messageagent == null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(messageagent);
       response.setDescription("Données récupérées.");
    }
    return response;
}@GetMapping("messageagent/task/{poowner}")
public ServeurResponse getAllTask(@PathVariable Long poowner) {
    Iterable<MessageAgent> messageagent = messageagentService.listMessageAgent(poowner);
    ServeurResponse response = new ServeurResponse();
    if (messageagent== null) {
        response.setStatut(false);
        response.setData(null);
        response.setDescription("Aucune élèment trouvé.");

    } else {
        
        response.setStatut(true);
        response.setData(messageagent);
       response.setDescription("Données récupérées.");
    }
    return response;
}@GetMapping("messageagent/list")
    public ServeurResponse getAllmessageAgent() {
        Iterable<MessageAgent> messageagent = messageagentService.findAll();
        ServeurResponse response = new ServeurResponse();
   if (messageagent == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

 } else {
      response.setStatut(true);
      response.setData(messageagent);
         response.setDescription("Données récupérées.");
        }
        return response;
}
 @PostMapping("messageagent/delete")
     public ServeurResponse delete(@RequestBody MessageAgent messageagent) {
          ServeurResponse response = new ServeurResponse();
          messageagentService.delete(messageagent);
          response.setStatut(true);
          response.setDescription("Données supprimé avec succès");
          return response;
}
}