
package sn.gainde2000.pi.core.controller;

import sn.gainde2000.pi.core.entity.Commande;
import sn.gainde2000.pi.core.service.ICommandeService;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotNull;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
@RestController
public class CommandeController {

 @Autowired 
ICommandeService commandeService;
@GetMapping("commande/list")
    public ServeurResponse getAllCommande() {
        Iterable<Commande> commande = commandeService.findAll();
        ServeurResponse response = new ServeurResponse();
        if (commande == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {
            
            response.setStatut(true);
            response.setData(commande);
           response.setDescription("Données récupérées.");
        }
        return response;
   }
 @PostMapping("commande/create")
public ServeurResponse create(@RequestBody Commande commande) {

ServeurResponse response = new ServeurResponse();

commandeService.save(commande);
response.setStatut(true);
response.setDescription("Enregistrement réussi");
response.setData(commande);

return response;
}
@PostMapping("commande/update")
     public ServeurResponse update(@RequestBody Commande commande) {
          ServeurResponse response = new ServeurResponse();

          commandeService.save(commande);
          response.setStatut(true);
               response.setDescription("Données mis à jour");
          return response;
     }
 @PostMapping("commande/delete")
     public ServeurResponse delete(@RequestBody Commande commande) {
          ServeurResponse response = new ServeurResponse();
          commandeService.delete(commande);
          response.setStatut(true);
          response.setDescription("Données supprimé avec succès");
          return response;
     }
}