/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.InscriptionActive;
import sn.gainde2000.pi.core.service.IInscriptionActiveService;

/**
 *
 * @author asow
 */
@RestController
@CrossOrigin("*")
public class InscriptionActiveController {

    @Autowired
    IInscriptionActiveService inscriptionActiveservice;

    @GetMapping("inscriptionactive/list")
    public ServeurResponse findAll() {
        ServeurResponse response = new ServeurResponse();
        List<InscriptionActive> insc = inscriptionActiveservice.getAll();
        if (insc != null) {
            response.setData(insc);
            response.setStatut(true);
            response.setDescription("Information recuperée!");
        } else {
            response.setData(null);
            response.setStatut(false);
            response.setDescription("Aucune information recuperée!");
        }

        return response;
    }

    @PostMapping("inscriptionactive/update")
    public ServeurResponse createTransition(@RequestBody InscriptionActive inscription) {
        ServeurResponse response = new ServeurResponse();

        InscriptionActive type = inscriptionActiveservice.findByInaId(inscription.getInaId());
        if (type.getInaIsActive()) {
            inscription.setInaIsActive(false);
            response.setDescription("Bouton inscription desmasquée!!");

        } else {
            inscription.setInaIsActive(true);
            response.setDescription("Bouton inscription masquée!!");

        }
        inscriptionActiveservice.update(inscription);
        response.setStatut(true);
        return response;
    }

}
