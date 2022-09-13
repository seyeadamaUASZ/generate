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
import sn.gainde2000.pi.core.entity.Action;
import sn.gainde2000.pi.core.entity.Fonctionnalite;
import sn.gainde2000.pi.core.repository.ActionRepository;
import sn.gainde2000.pi.core.repository.FonctionnaliteRepository;
import sn.gainde2000.pi.core.service.IActionService;
import sn.gainde2000.pi.core.service.impl.ActionServiceImpl;

@RestController

public class ActionController {
	 @Autowired 
	  IActionService   actionService;
	//Liste des actions

	@GetMapping("action/list")
	    public ServeurResponse getAllAction() {
	        Iterable<Action> action = actionService.getListAction();
	        ServeurResponse response = new ServeurResponse();
	        if (action == null) {
	            response.setStatut(false);
	            response.setData(null);
	            response.setDescription("Aucune élèment trouvé.");

	        } else {
	            
	            response.setStatut(true);
	            response.setData(action);
	            response.setDescription("Données récupérées.");
	        }
	        return response;
	    }
	  //Ajouter action
	    @PostMapping("action/create")
	    public ServeurResponse create(@RequestBody Action action) {
	       
	        ServeurResponse response = new ServeurResponse();
	       
	        actionService.saveAction(action);
	        response.setStatut(true);
	        response.setDescription("Enregistrement réussi");
	        response.setData(action);
	         
	        return response;
	    }
	    //recuperation par id
	    @GetMapping("action/{id}")
		public Optional<Action> findById(@PathVariable Long id) {
		  return actionService.findById(id);
		}
	  //Supprimer un fonctionnalite
	     @PostMapping("action/delete")
	        public ServeurResponse deleteformulaire(@RequestBody Action action) {
	          ServeurResponse response = new ServeurResponse();
	          actionService.supprimer(action);
	          response.setStatut(true);
	          return response;
	     }

}
