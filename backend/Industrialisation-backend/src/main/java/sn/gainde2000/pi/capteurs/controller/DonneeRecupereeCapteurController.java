package sn.gainde2000.pi.capteurs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sn.gainde2000.pi.capteurs.entity.DonneeRecupereeCapteur;
import sn.gainde2000.pi.capteurs.service.IDonneeRecuperee;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;

@RestController
public class DonneeRecupereeCapteurController {
	@Autowired
  private IDonneeRecuperee iDonneeRecuperee;
	
	@PostMapping("/donnee/add")
	public ServeurResponse addDonnee(@RequestBody DonneeRecupereeCapteur donnee) {
		ServeurResponse response = new ServeurResponse();
		DonneeRecupereeCapteur donneee = iDonneeRecuperee.addDonneeRecuperee(donnee);
		if(donneee !=null) {
			response.setStatut(true);
			response.setData(donneee);
			response.setDescription("Informations récupérées ");
		}
		else {
			response.setStatut(false);
			response.setData(null);
			response.setDescription("Informations non récupérées ");
		}
		return response;
	}
	
	@GetMapping("/donneesRecuperees/{id}")
	public ServeurResponse listDoneerecupereeByCapteur(@PathVariable(name="id") Long id){
		ServeurResponse response = new ServeurResponse();
		List<DonneeRecupereeCapteur> donnees= iDonneeRecuperee.listDonneeCapteurRecuperee(id);
		if(!donnees.isEmpty()) {
			response.setData(donnees);
			response.setDescription("donnees capteurs récupérées ");
			response.setStatut(true);
		}else {
			response.setData(null);
			response.setDescription("donnees capteurs non récupérées ");
			response.setStatut(false);
		}
		return response;
	}
}
