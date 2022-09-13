package sn.gainde2000.pi.core.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Application;
import sn.gainde2000.pi.core.entity.Langue;
import sn.gainde2000.pi.core.repository.ApplicationRepository;
import sn.gainde2000.pi.core.repository.UtilisateurRepository;
import sn.gainde2000.pi.core.service.IApplicationService;
import sn.gainde2000.pi.core.service.IUtilisateur;
import sn.gainde2000.pi.core.service.IlangueService;

@RestController
@CrossOrigin("*")
@RequestMapping("langue")
public class LangueController {

     @Autowired
     IlangueService ilangueService;
     //Liste des Langue
     @Autowired
     IUtilisateur userRepo;
     @Autowired
     IApplicationService appRepo;

     @GetMapping
     public ServeurResponse getAllLangue() {
          Iterable<Langue> langue = ilangueService.getListLang();
          ServeurResponse response = new ServeurResponse();
          if (langue == null) {
               response.setStatut(false);
               response.setData(null);
               response.setDescription("Aucune élèment trouvé.");

          } else {

               response.setStatut(true);
               response.setData(langue);
               response.setDescription("Données récupérées.");
          }
          return response;
     }
     //Ajouter langue

     @PostMapping()
     public ServeurResponse create(@RequestBody Langue langue) {

          ServeurResponse response = new ServeurResponse();
          ilangueService.save(langue);
          response.setStatut(true);
          response.setDescription("Enregistrement réussi");
          response.setData(langue);

          return response;
     }
     //recuperation par id

     @GetMapping("{id}")
     public Optional<Langue> findById(@PathVariable Long id) {
          return ilangueService.findByLangId(id);
     }
     //Supprimer un module


     @PostMapping("delete")
     public ServeurResponse deleteLangue(@RequestBody Langue langue) {
          ServeurResponse response = new ServeurResponse();
          ilangueService.delete(langue);
          response.setStatut(true);
          return response;
     }
     
     @PostMapping("update")
     public ServeurResponse updateLangue(@RequestBody Application application) {
         
          ServeurResponse response = new ServeurResponse();
         /* Application app = appRepo.findByAppEmail(application.getAppEmail());
          System.out.println("user1 check:"+app.getAppEmail());
          if ((app != null)) {
               app.setParam_app_id(param_app_id);
               this.appRepo.save(app);
<<<<<<< HEAD
=======
          Utilisateur user1 = userRepo.findByUtiUsername(user.getUtiUsername());
          System.out.println("user1 check:"+user.getUtiUsername());
          if ((user1 != null)) {
              // user1.setUti_lng_id(user.getUti_lng_id());
               this.userRepo.save(user1);
>>>>>>> 4666bd60c6ae73eaaadc94aebf072209c7846fcd
               response.setStatut(true);
               response.setDescription("Langue changé avec succès");
               return response;
          } else {
               response.setStatut(false);
               response.setDescription("Langue non changés");
               return response;
          }*/
                         return response;

     }


    

}
