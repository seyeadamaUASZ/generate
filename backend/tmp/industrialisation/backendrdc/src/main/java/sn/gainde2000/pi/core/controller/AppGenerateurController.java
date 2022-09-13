package sn.gainde2000.pi.core.controller;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Application;
import sn.gainde2000.pi.core.entity.Champs;
import sn.gainde2000.pi.core.entity.Formulaire;
import sn.gainde2000.pi.core.entity.Utilisateur;
import sn.gainde2000.pi.core.repository.ApplicationRepository;
import sn.gainde2000.pi.core.repository.ChampsRepository;
import sn.gainde2000.pi.core.repository.FormulaireRepository;
import sn.gainde2000.pi.core.service.Crud;
import sn.gainde2000.pi.core.tools.StringProcess;

import javax.persistence.Column;
import javax.swing.text.html.parser.Entity;
import javax.validation.constraints.Size;

@RestController
@CrossOrigin("*")
@RequestMapping("generate")
public class AppGenerateurController {

    @Autowired
    Environment environment;
    @Autowired
    FormulaireRepository formulaireRepository;
    @Autowired
    ChampsRepository champsRepository;
    ApplicationRepository applicationRepository;
    //Liste des Champs


    @RequestMapping(value = "/recupforms/{id}", method = RequestMethod.GET)

    public Formulaire getAllChamps(@PathVariable Long id) throws IOException {
    	Crud crud = new Crud();
        ServeurResponse response = new ServeurResponse();
        Formulaire form = formulaireRepository.findByfrmId(id);
       
        if (form == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {
        	 form.setFrmStatus(true);
             formulaireRepository.save(form);
            String nomform = form.getFrmNom();
             // crud.generationFileModelBack(nomform, id);
              crud.generationFileControllerBack(nomform);
              crud.generationFileServiceBack(nomform);
              crud.generationFileRepositoryBack(nomform);
            response.setStatut(true);
            response.setData(form);
            response.setDescription("Données récupérées.");
        }
        return form;
    }
}
