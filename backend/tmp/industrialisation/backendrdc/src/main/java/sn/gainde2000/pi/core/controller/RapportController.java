/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Fichier;
import sn.gainde2000.pi.core.entity.Menu;
import sn.gainde2000.pi.core.entity.Rapport;
import sn.gainde2000.pi.core.service.IChampsRapportService;
import sn.gainde2000.pi.core.service.impl.RapportServiceImpl;

/**
 *
 * @author Sagueye
 */
@RestController
@CrossOrigin("*")
public class RapportController {

    @Autowired
    RapportServiceImpl rapportService;

    @Autowired
    IChampsRapportService champsRapportService;

    //Ajouter un rapport
    @PostMapping("rapport")
    public ServeurResponse create(HttpServletRequest request, @RequestParam("jrxmlfile") MultipartFile file) {

        ServeurResponse response = new ServeurResponse();

        Rapport rapport;
        try {
            rapport = new ObjectMapper().readValue(
                    request.getParameter("rapport"), new TypeReference<Rapport>() {
            }
            );

            rapport.setRptJrxmlFile(file.getBytes());

            /*
			MultipartFile file = new ObjectMapper().readValue(
	    			request.getParameter("jrxmlfile"), new TypeReference<MultipartFile>() { }
	    		);*/
            System.out.println("************Rapport to flat*************" + rapport.toString());
            rapport.setRptStatus(false);
            rapport.setRptValider("Modeliser");
//	        rapport.setRptJrxmlFile(rapportJrxml.getRptJrxmlFile());
            rapportService.save(rapport);
            response.setStatut(true);
            response.setDescription("Enregistrement réussi");
            response.setData(rapport);
        } catch (JsonParseException e) {
            System.out.println("**************JsonParseException***********" + e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("**************JsonMappingException***********" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("**************IOException***********" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("**************Exception***********" + e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    //Update un rapport
    @PostMapping("updaterapport")
    public ServeurResponse update(HttpServletRequest request) {

        ServeurResponse response = new ServeurResponse();

        Rapport rapport;
        try {
            rapport = new ObjectMapper().readValue(
                    request.getParameter("rapport"), new TypeReference<Rapport>() {
            }
            );

            Rapport r = rapportService.findById(rapport.getRptId());

//			r.setRptJrxmlFile(file.getBytes());
            r.setRptDescription(rapport.getRptDescription());
            r.setRptNom(rapport.getRptNom());
            rapportService.save(r);
            response.setStatut(true);
            response.setDescription("Enregistrement réussi");
            response.setData(rapport);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    //Update un rapport
    @PostMapping("updaterapportfile")
    public ServeurResponse updateFile(HttpServletRequest request, @RequestParam("jrxmlfile") MultipartFile file) {

        ServeurResponse response = new ServeurResponse();

        Rapport rapport;
        try {
            rapport = new ObjectMapper().readValue(
                    request.getParameter("rapport"), new TypeReference<Rapport>() {
            }
            );

            Rapport r = rapportService.findById(rapport.getRptId());

            r.setRptJrxmlFile(file.getBytes());

            rapportService.save(r);
            response.setStatut(true);
            response.setDescription("Enregistrement réussi");
            response.setData(rapport);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @PostMapping("validerrapport")
    public ServeurResponse valider(@RequestBody Rapport rapport) {

        ServeurResponse response = new ServeurResponse();
        rapport.setRptStatus(false);
        rapport.setRptValider("Valider");
        rapportService.save(rapport);
        response.setStatut(true);
        response.setDescription("Enregistrement réussi");
        response.setData(rapport);

        return response;
    }

    @PostMapping("modeliserrapport")
    public ServeurResponse modeliser(@RequestBody Rapport rapport) {
        ServeurResponse response = new ServeurResponse();
        Rapport r = rapportService.findById(rapport.getRptId());
        r.setRptValider("Modeliser");
        rapportService.save(r);
        response.setStatut(true);
        response.setDescription("Rapport valider");
        response.setData(r);

        return response;
    }

    //recuperation par id
    @GetMapping("rapport/{id}")
    public Rapport findById(@PathVariable Long id) {
        return rapportService.findById(id);
    }

    //Liste des rapports non generer
    @GetMapping("notgeneraterapport")
    public ServeurResponse getAllnotgenerate() {
        Iterable<Rapport> rapport = rapportService.listrapportnotgenerate();
        ServeurResponse response = new ServeurResponse();
        if (rapport == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(rapport);
            response.setDescription("Données récupérées.");
        }
        return response;
    }

    //Liste des rapports generer
    @GetMapping("generaterapport")
    public ServeurResponse getAllgenerate() {
        Iterable<Rapport> rapport = rapportService.getlistrapportgenerate();
        ServeurResponse response = new ServeurResponse();
        if (rapport == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(rapport);
            response.setDescription("Données récupérées.");
        }
        return response;
    }
    
  //Liste des rapports generer par menu
    @PostMapping("rapportparmenu")
    public ServeurResponse getAllRapportByMenu(@RequestBody Menu menu) {
        Iterable<Rapport> rapports = rapportService.getAllRapportByMenu(menu);
        ServeurResponse response = new ServeurResponse();
        if (rapports == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(rapports);
            response.setDescription("Données récupérées.");
        }
        return response;
    }

    @GetMapping("generateRapportLibre")
    public ServeurResponse AllgenerateRapportLibre() {
        Iterable<Rapport> rapport = rapportService.getlistrapportgenerateEtLibre();
        ServeurResponse response = new ServeurResponse();
        if (rapport == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(rapport);
            response.setDescription("Données récupérées.");
        }
        return response;
    }

    @RequestMapping(value = "fichierLierApp", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ServeurResponse LierFichierEtApp(HttpServletRequest request) {
        ServeurResponse response = new ServeurResponse();
        String idApp = (String) request.getParameter("idApp");
        String idFichier = (String) request.getParameter("idFichier");
        // System.out.println("appp id:" + idApp);
        // System.out.println("workflow id:" + idWorkflow);
        rapportService.lierFichier(idApp, idFichier);
        response.setStatut(true);
        response.setDescription("liaison effectué avec succès");

        return response;
    }

    @GetMapping("fichierLierApp/enlever/{idFichier}")
    public ServeurResponse EnleverLiaisonRapportEtApp(@PathVariable String idFichier) {
        ServeurResponse response = new ServeurResponse();
        // String idApp = (String) request.getParameter("idApp");
        // System.out.println("appp id:" + idApp);
        System.out.println("fichier id:" + idFichier);
        rapportService.enleverLiasonFichier(idFichier);
        response.setStatut(true);
        response.setDescription("liaison enlevé avec succès");

        return response;
    }

    @GetMapping("fichierByAppId/{id}")
    public ServeurResponse getFichierByAppId(@PathVariable Long id) {
        ServeurResponse response = new ServeurResponse();
        List<Rapport> f = rapportService.getAllFichierByIdApp(id);
        response.setData(f);
        response.setStatut(true);
        return response;
    }

    @PostMapping("supprimerrapport")
    public ServeurResponse deleteRapport(@RequestBody Rapport rapport) {
        ServeurResponse response = new ServeurResponse();
        champsRapportService.supprimerByRapport(rapport.getRptId());
        rapportService.delete(rapport);
        response.setStatut(true);
        return response;
    }
      @GetMapping("fichierByAppOuLibre/{id}")
     public ServeurResponse getRapportLibreOuSpecifique(@PathVariable Long id){
          ServeurResponse response = new ServeurResponse();
          List<Rapport> f = rapportService.getlistrapportgenerateEtLibreOuSpecifique(id);
          response.setData(f);
          response.setStatut(true);
          return response;
     } 
     
     @GetMapping("fichier/nombreFichier")
     public ServeurResponse getNombreFichier(){
          ServeurResponse response = new ServeurResponse();
          response.setData(rapportService.nombreFichier());
          response.setStatut(true);
          return response;
     }
}
