package sn.gainde2000.pi.core.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Pays;
import sn.gainde2000.pi.core.repository.PaysRepository;
import sn.gainde2000.pi.core.service.IpaysService;


@RestController
@CrossOrigin("*")
public class PaysController {
	
	 @Autowired
	 IpaysService ipaysService;
	//Liste des pays
	  @GetMapping("pays")
	    public ServeurResponse getAllPays() {
	        Iterable<Pays> pays = ipaysService.getListPays();
	        ServeurResponse response = new ServeurResponse();
	        if (pays == null) {
	            response.setStatut(false);
	            response.setData(null);
	            response.setDescription("Aucune élèment trouvé.");

	        } else {
	            
	            response.setStatut(true);
	            response.setData(pays);
	            response.setDescription("Données récupérées.");
	        }
	        return response;
	    }
	  //Ajouter Pays
	    @PostMapping("Pays")
	    public ServeurResponse create(@RequestBody Pays pays) {
	       
	        ServeurResponse response = new ServeurResponse();
	       
	        ipaysService.save(pays);
	        response.setStatut(true);
	        response.setDescription("Enregistrement réussi");
	        response.setData(pays);
	         
	        return response;
	    }
	    //recuperation par id
	    @GetMapping("pays/{id}")
        public Optional<Pays> findById(@PathVariable Long id) {
          return ipaysService.findByPaysId(id);
     }
	  //Supprimer un workflow
	     @PostMapping("Pays/delete")
	        public ServeurResponse deletePays(@RequestBody Pays pays) {
	          ServeurResponse response = new ServeurResponse();
	          ipaysService.delete(pays);
	          response.setStatut(true);
	          return response;
	     }
	    
}
