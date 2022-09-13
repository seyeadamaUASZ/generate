package sn.gainde2000.pi.capteurs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sn.gainde2000.pi.capteurs.entity.Capteur;
import sn.gainde2000.pi.capteurs.service.ICapteur;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;


@RestController
public class CapteurController {
	
  private final ICapteur icapteur;
	
  @Autowired
  public CapteurController(ICapteur icapteur) {
		super();
		this.icapteur = icapteur;
	}

  @PostMapping("/capteur/add")
  public ServeurResponse addCapteur(@RequestBody Capteur capteur) {
	  ServeurResponse response = new ServeurResponse();
	  Capteur capteur2 = icapteur.addCapteur(capteur);
	  if(capteur2!=null) {
		  response.setStatut(true);
		  response.setData(capteur2);
		  response.setDescription("Capteur bien ajouté !!!");
	  }else {
		  response.setStatut(false);
		  response.setData(null);
		  response.setDescription("Capteur non ajouté !!!");
	  }
	  return response;
  }
  
  @GetMapping("/capteur/list")
  public ServeurResponse listCapteurs() {
	  ServeurResponse response=new ServeurResponse();
	  List<Capteur> capteurs = icapteur.listCapteur();
	  if(!capteurs.isEmpty()) {
		  response.setStatut(true);
		  response.setData(capteurs);
		  response.setDescription("liste des capteurs ");
	  }else {
		  response.setStatut(false);
		  response.setData(null);
		  response.setDescription("liste des capteurs non récupérés!!!");
	  }
	  return response;
  }
  
  @GetMapping("/capteurs/check/{id}")
  public ServeurResponse checkCapteur(@PathVariable(name="id")Long id) {
	  ServeurResponse response = new ServeurResponse();
	  Capteur capteur = icapteur.getCapteur(id);
	  if(capteur!=null) {
		  response.setData(capteur);
		  response.setDescription("capteur récupéré !!!");
		  response.setStatut(true);
	  }else {
		  response.setData(null);
		  response.setDescription("capteur non récupéré !!!");
		  response.setStatut(false);
	  }
	  return response;
  }
  
  
  @PostMapping("capteurs/update/{id}")
	public ServeurResponse updateParametre(@PathVariable(name="id")Long id, @RequestBody Capteur capteur) {
		ServeurResponse response=new ServeurResponse();
		Capteur capteur1= icapteur.getCapteur(id);
		if(capteur1!=null) {
			capteur1.setLibelle(capteur.getLibelle());
			capteur1.setDescription(capteur.getDescription());
			icapteur.addCapteur(capteur1);
			response.setStatut(true);
			response.setData(capteur1);
			response.setDescription("capteur modifié avec succés!!");	
		}else {
			response.setStatut(false);
			response.setData(null);
			response.setDescription("capteur non modifié!!");
		}
		
		return response;
	}
  
  
  @GetMapping("capteur/deletecapteur/{id}")
	public ServeurResponse deleteParametreOtp(@PathVariable(name="id")Long id) {
		ServeurResponse response = new ServeurResponse();
		icapteur.deleteCapteur(id);
		response.setStatut(true);
      response.setDescription("capteur supprimé!!");
      return response;
		
	}
  
}
