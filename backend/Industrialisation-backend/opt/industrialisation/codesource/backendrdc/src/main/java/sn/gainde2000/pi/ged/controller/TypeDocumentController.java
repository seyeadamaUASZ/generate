package sn.gainde2000.pi.ged.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Champs;
import sn.gainde2000.pi.core.entity.ValueChamps;
import sn.gainde2000.pi.ged.entity.TypeDocuments;
import sn.gainde2000.pi.ged.service.ITypeDocumentService;

@RestController
public class TypeDocumentController {

	
    @Autowired
    ITypeDocumentService typedocumentService;

    @GetMapping("typedocument/list")
    public ServeurResponse getAllDocument() {
        Iterable<TypeDocuments> typedocument = typedocumentService.getListTypeDocument();
        ServeurResponse response = new ServeurResponse();
        if (typedocument == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(typedocument);
            response.setDescription("Données récupérées.");
        }
        return response;
    }
    
    @GetMapping("typedocument/profile/{id}")
    public ServeurResponse getListTypeDocumentProfil(@PathVariable Long id) {
        Iterable<TypeDocuments> typedocument = typedocumentService.getListTypeDocumentProfil(id);
        ServeurResponse response = new ServeurResponse();
        if (typedocument == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(typedocument);
            response.setDescription("Données récupérées.");
        }
        return response;
    }
    
    
    @PostMapping("typedocument/create")
    public ServeurResponse create(@RequestBody TypeDocuments typeDocument) {
    	
        ServeurResponse response = new ServeurResponse();
       
        typedocumentService.saveTypeDocuments(typeDocument);
        response.setStatut(true);
        response.setDescription("Enregistrement réussi");
        response.setData(typeDocument);

        return response;
    }
    
    @PostMapping("typedocument/delete")
    public ServeurResponse deletePays(@RequestBody TypeDocuments typeDocument) {
    	
        ServeurResponse response = new ServeurResponse();
        typedocumentService.supprimer(typeDocument);
        response.setStatut(true);
        return response;
    }
}
