package sn.gainde2000.pi.core.controller;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Formulaire;
import sn.gainde2000.pi.core.repository.FormulaireRepository;
import sn.gainde2000.pi.core.service.IFormulaireService;
import sn.gainde2000.pi.core.service.impl.FormulaireServiceImpl;

@RestController
@CrossOrigin("*")
public class FormulaireController {

     @Autowired
     IFormulaireService formulaireServiceImpl;
     //FormulaireRepository formulaireRepository;
     //Liste des formulaires

     @GetMapping("formulaire/list")
     public ServeurResponse getAllFormulaire() {
          Iterable<Formulaire> formulaire = formulaireServiceImpl.getAllFormulaire();
          ServeurResponse response = new ServeurResponse();
          if (formulaire == null) {
               response.setStatut(false);
               response.setData(null);
               response.setDescription("Aucune élèment trouvé.");

          } else {

               response.setStatut(true);
               response.setData(formulaire);
               response.setDescription("Données récupérées.");
          }
          return response;
     }
     //Liste des formulaires

     @GetMapping("formulaire/notgenerateform")
     public ServeurResponse getAllnotgenerate() {
          Iterable<Formulaire> formulaire = formulaireServiceImpl.listformnotgenerate();
          ServeurResponse response = new ServeurResponse();
          if (formulaire == null) {
               response.setStatut(false);
               response.setData(null);
               response.setDescription("Aucune élèment trouvé.");

          } else {

               response.setStatut(true);
               response.setData(formulaire);
               response.setDescription("Données récupérées.");
          }
          return response;
     }
     //Liste des formulaires

     @GetMapping("formulaire/generateform")
     public ServeurResponse getAllgenerate() {
          Iterable<Formulaire> formulaire = formulaireServiceImpl.listformgenerate();
          ServeurResponse response = new ServeurResponse();
          if (formulaire == null) {
               response.setStatut(false);
               response.setData(null);
               response.setDescription("Aucune élèment trouvé.");

          } else {

               response.setStatut(true);
               response.setData(formulaire);
               response.setDescription("Données récupérées.");
          }
          return response;
     }
     //Ajouter formulaire

     @PostMapping("formulaire/create")
     public ServeurResponse create(@RequestBody Formulaire formulaire) {

          ServeurResponse response = new ServeurResponse();
          
          if(formulaireServiceImpl.findByfrmNom(formulaire.getFrmNom())==null) {
          formulaire.setFrmStatus(false);
          formulaire.setFrmValider("Modeliser");
          formulaire.setFrmModeliser(false);
          formulaireServiceImpl.save(formulaire);
          response.setStatut(true);
          response.setDescription("Enregistrement réussi");
          response.setData(formulaire);
          }else {
        	  response.setStatut(false);
              response.setDescription("le nom du formulaire existe deja");
          }
          
          return response;
     }
  

     @PostMapping("formulaire/validerfrm")
     public ServeurResponse valider(@RequestBody Formulaire formulaire) {

          ServeurResponse response = new ServeurResponse();
          formulaire.setFrmStatus(false);
          formulaire.setFrmValider("Valider");
          formulaireServiceImpl.save(formulaire);
          response.setStatut(true);
          response.setDescription("Formulaire validé");
          response.setData(formulaire);

          return response;
     }

     @PostMapping("formulaire/modeliserfrm")
     public ServeurResponse modeliser(@RequestBody Formulaire formulaire) {

          ServeurResponse response = new ServeurResponse();
          formulaire.setFrmStatus(false);
          formulaire.setFrmValider("Modeliser");
          formulaire.setFrmModeliser(true);
          formulaireServiceImpl.save(formulaire);
          response.setStatut(true);
          response.setDescription("Formulaire validé");
          response.setData(formulaire);

          return response;
     }
     //recuperation par id

     @GetMapping("formulaire/{id}")
     public Optional<Formulaire> findById(@PathVariable Long id) {
          return formulaireServiceImpl.findById(id);
     }
     //Supprimer un module

     @PostMapping("formulaire/delete")
     public ServeurResponse deleteformulaire(@RequestBody Formulaire formulaire) {
          ServeurResponse response = new ServeurResponse();
          formulaireServiceImpl.delete(formulaire);
          response.setStatut(true);
          return response;
     }
     //liste formulaire 

     @GetMapping("formulaire/formulaireApp/{id}")
     public List<Formulaire> findApp(@PathVariable Long id) {
          return formulaireServiceImpl.findByfrmAppId(id);
     }

     @GetMapping("formulaire/formulaireByApp")
     public ServeurResponse getFormulaireByApp() {
          ServeurResponse response = new ServeurResponse();
          List<Formulaire> f = formulaireServiceImpl.getAllFormulaireLibreValider();
          response.setData(f);
          response.setStatut(true);
          return response;
     }

     @RequestMapping(value = "formulaire/formulaireLierApp", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
     public ServeurResponse LierWorkflowEtApp(HttpServletRequest request) {
          ServeurResponse response = new ServeurResponse();
          String idApp = (String) request.getParameter("idApp");
          String idFormulaire = (String) request.getParameter("idFormulaire");
          // System.out.println("appp id:" + idApp);
          // System.out.println("workflow id:" + idWorkflow);
          formulaireServiceImpl.lierFormulaire(idApp, idFormulaire);
          response.setStatut(true);
          response.setDescription("liaison effectué avec succès");

          return response;
     }

     @GetMapping("formulaire/formulaireLierApp/enlever/{idFormulaire}")
     public ServeurResponse EnleverLiaisonWorkflowEtApp(@PathVariable String idFormulaire) {
          ServeurResponse response = new ServeurResponse();
          // String idApp = (String) request.getParameter("idApp");
          // System.out.println("appp id:" + idApp);
          System.out.println("formulaire id:" + idFormulaire);
          formulaireServiceImpl.enleverLiasonFormulaire(idFormulaire);
          response.setStatut(true);
          response.setDescription("liaison enlevé avec succès");

          return response;
     }

     @GetMapping("formulaire/formulaireByAppId/{id}")
     public ServeurResponse FormulaireByAppId(@PathVariable Long id) {
          ServeurResponse response = new ServeurResponse();
          // String idApp = (String) request.getParameter("idApp");
          // System.out.println("appp id:" + idApp);
          System.out.println("formulaire id:" + id);
          List<Formulaire> f = formulaireServiceImpl.listformByAppId(id);
          response.setStatut(true);
          response.setData(f);
          response.setDescription("Formulaire recupéré par id");
          return response;
     }
     
       @GetMapping("formulaire/formulaireByAppOuLibre/{id}")
     public ServeurResponse getFormulaireLibreOuSpecifique(@PathVariable Long id){
          ServeurResponse response = new ServeurResponse();
          List<Formulaire> f = formulaireServiceImpl.listformByAppIdOulibre(id);
          response.setData(f);
          response.setStatut(true);
          return response;
     } 
     
     @GetMapping("formulaire/nombreFormulaire")
     public ServeurResponse getNombreFormulaire(){
          ServeurResponse response = new ServeurResponse();
          response.setData(formulaireServiceImpl.NombreFormulaire());
          response.setStatut(true);
          return response;
     }

}
