/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.gainde2000.pi.core.repository.UtilisateurRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Image;
import sn.gainde2000.pi.core.entity.Parametre;
import sn.gainde2000.pi.core.entity.Utilisateur;
import sn.gainde2000.pi.core.repository.ImageRepository;
import sn.gainde2000.pi.core.repository.ParametreRepository;
import sn.gainde2000.pi.core.service.IparametreService;

/**
 *
 * @author yougadieng
 */
@RestController
@CrossOrigin("*")
@RequestMapping("parametre")
public class ParametreController {
    @Autowired
    IparametreService iparametreService;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    UtilisateurRepository useRepo;

    @PostMapping("update")
    public ServeurResponse updateParametre(@RequestBody Parametre parametre) {
        ServeurResponse response = new ServeurResponse();
         /*System.out.println("user1 check:" + parametre.toString());
        System.out.println("user1 check:" + parametre.getParamUtiUsername());
        System.out.println("user1 check:" + parametre.getParam_lng_id());
        System.out.println("user1 check:" + parametre.getParam_thm_id());
        System.out.println("user1 check:" + parametre.getParam_img_id());*/
        //parametre.setParam_lng_id(parametre.getParam_lng_id());
      try{  iparametreService.save(parametre);
        response.setStatut(true);
        response.setDescription("Paramètres changés avec succès");}
      catch(Exception ex){
      }
        return response;

    }


    /*
    @GetMapping("get/{username}")
    public ServeurResponse getParametre(@PathVariable String username) {
        System.out.println("user1 check:  getParametre" );
        ServeurResponse response = new ServeurResponse();
        Utilisateur user = paramRepo.verifyLangueEtTheme(username);
        if (user != null) {
            System.out.println("les données du user"+user);
            response.setStatut(true);
            response.setDescription("Langue et theme du user récupéré avec succès");
            response.setData(user);
        } else {
            response.setStatut(true);
            response.setDescription("Echec récupération");
            response.setData(null);

        }
        return response;

    }*/
 
    @GetMapping
    public ServeurResponse getParametreUser() {
        System.out.println("user1 check:  getParametre" );
        ServeurResponse response = new ServeurResponse();
        Parametre param = iparametreService.selectParam();
        if (param != null) {
            response.setStatut(true);
            response.setDescription("Liste des parametres récupéré avec succès");
            response.setData(param);
        } else {
            response.setStatut(true);
            response.setDescription("Echec récupération");
            response.setData(null);

        }
        return response;
    }

}
