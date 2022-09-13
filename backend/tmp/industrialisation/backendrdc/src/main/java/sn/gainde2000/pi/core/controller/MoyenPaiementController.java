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
import sn.gainde2000.pi.core.entity.MoyenPaiement;
import sn.gainde2000.pi.core.repository.MoyenPaiementRepository;
import sn.gainde2000.pi.core.service.ImoyenPaimentService;

@RestController
@CrossOrigin("*")
public class MoyenPaiementController {
	
	 @Autowired
	 ImoyenPaimentService imoyenPaimentService;
	//Liste des moyenPaiement
	  @GetMapping("moyenPaiements")
	    public ServeurResponse getAllMoyenPaiement() {
	        Iterable<MoyenPaiement> moyenPaiement = imoyenPaimentService.getListMoyen();
	        ServeurResponse response = new ServeurResponse();
	        if (moyenPaiement == null) {
	            response.setStatut(false);
	            response.setData(null);
	            response.setDescription("Aucune élèment trouvé.");

	        } else {
	            
	            response.setStatut(true);
	            response.setData(moyenPaiement);
	            response.setDescription("Données récupérées.");
	        }
	        return response;
	    }
	  //Ajouter langue
	    @PostMapping("moyenPaiement")
	    public ServeurResponse create(@RequestBody MoyenPaiement moyenPaiement) {
	       
	        ServeurResponse response = new ServeurResponse();

			imoyenPaimentService.save(moyenPaiement);
	        response.setStatut(true);
	        response.setDescription("Enregistrement réussi");
	        response.setData(moyenPaiement);
	         
	        return response;
	    }
	    //recuperation par id
	    @GetMapping("moyenPaiement/{id}")
		public Optional<MoyenPaiement> findById(@PathVariable Long id) {
		  return imoyenPaimentService.findByMoyenId(id);
		}
	  //Supprimer un module
	     @PostMapping("moyenPaiement/delete")
	        public ServeurResponse deleteMoyenPaiement(@RequestBody MoyenPaiement moyenPaiement) {
	          ServeurResponse response = new ServeurResponse();
			 imoyenPaimentService.delete(moyenPaiement);
	          response.setStatut(true);
	          return response;
	     }

}
