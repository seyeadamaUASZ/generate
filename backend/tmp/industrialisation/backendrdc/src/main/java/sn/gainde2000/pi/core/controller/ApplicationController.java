package sn.gainde2000.pi.core.controller;

import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Application;
import sn.gainde2000.pi.core.repository.ApplicationRepository;
import sn.gainde2000.pi.core.service.IApplicationService;

@RestController
@CrossOrigin("*")
public class ApplicationController {

     @Autowired
     IApplicationService applicationRepository;

     //Liste des applications
     @GetMapping("application/list")
     public ServeurResponse getAllApplication() {
          Iterable<Application> application = applicationRepository.getListApplication();
          ServeurResponse response = new ServeurResponse();
          if (application == null) {
               response.setStatut(false);
               response.setData(null);
               response.setDescription("Aucune élèment trouvé.");

          } else {

               response.setStatut(true);
               response.setData(application);
               response.setDescription("Données récupérées.");
          }
          return response;
     }
     //Ajouter application

     @PostMapping("application/create")

     public ServeurResponse create(@RequestBody Application application) {
          ServeurResponse response = new ServeurResponse();
          Application app = applicationRepository.findByappNom(application.getAppNom());
          if (app == null) {
               application.setAppDateCreation(new Date());
               // application.setAppEtapeCreation(1);
               applicationRepository.save(application);
               response.setStatut(true);
               response.setDescription("Enregistrement réussi");
               response.setData(application);
          } else {
               application.setAppId(app.getAppId());
               application.setAppDateCreation(new Date());
               applicationRepository.save(application);
               response.setStatut(false);
               response.setDescription("Cette nom application a été déja crée ");
               response.setData(application);
          }

          return response;
     }
     //recuperation par id

     @GetMapping("application/{id}")
     public ServeurResponse findById(@PathVariable Long id) {
          ServeurResponse response = new ServeurResponse();
          Application app = applicationRepository.findByappId(id);
          if (app != null) {
               response.setStatut(true);
               response.setDescription("récuperation réussi");
               response.setData(app);
          } else {
               response.setStatut(false);
               response.setDescription("aucun donnee trouvee");
               response.setData(null);
          }
          return response;
     }

     @PostMapping("application/delete")
     public ServeurResponse deleteApplication(@RequestBody Application application) {
          ServeurResponse response = new ServeurResponse();
          applicationRepository.deleteJointureWorkflow(application.getAppId());
          applicationRepository.deleteJointureFormulaire(application.getAppId());
          applicationRepository.deleteJointureFichier(application.getAppId());
          applicationRepository.deleteJointureTableRelationnel(application.getAppId());
          applicationRepository.delete(application);

          response.setStatut(true);
          return response;
     }

     @RequestMapping(value = "application/updateEtape", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
     public ServeurResponse UpdateEtapeApp(HttpServletRequest request) {
          ServeurResponse response = new ServeurResponse();
          String idApp = request.getParameter("idApp");
          String etape = request.getParameter("etape");
          applicationRepository.update(etape, idApp);
          response.setStatut(true);
          response.setDescription("Application update ok");
          return response;

     }
}
