package sn.gainde2000.pi.core.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Action;
import sn.gainde2000.pi.core.entity.Notification;
import sn.gainde2000.pi.core.entity.NotificationDestinataire;
import sn.gainde2000.pi.core.entity.Profil;
import sn.gainde2000.pi.core.entity.Utilisateur;
import sn.gainde2000.pi.core.service.INotificationDestinataireService;
import sn.gainde2000.pi.core.service.INotificationService;
import sn.gainde2000.pi.core.service.impl.UtilisateurImpl;

/**
*
* @author sagueye
*/

@RestController
@CrossOrigin("*")
@RequestMapping("notification")
public class NotificationController {
	@Autowired
	INotificationService iNotificationService;
	@Autowired
    UtilisateurImpl userService;
	@Autowired
	INotificationDestinataireService notDesService;
	
	@GetMapping
    public ServeurResponse getNotifications() {
    	
        Iterable<Notification> notifications = iNotificationService.getListNotification();
        ServeurResponse response = new ServeurResponse();
        if (notifications == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(notifications);
            response.setDescription("Données récupérées.");
        }
        return response;
    }
	
	@PostMapping("add")
    public ServeurResponse createNotification(HttpServletRequest request, @RequestParam("audio") MultipartFile file) {
	 	
        ServeurResponse response = new ServeurResponse();
        Notification notification;
		try {
			notification = new ObjectMapper().readValue(
					request.getParameter("notification"), new TypeReference<Notification>() {
					}
			);

	        if (file != null) {
				notification.setNtfAudio(CompressDecompressBytesController.compressBytes(file.getBytes()));
	        }
	        iNotificationService.save(notification);
	        response.setStatut(true);
	        response.setData(notification);
	        response.setDescription("Enregistrement reussi");
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return response;
    }
	
	@PostMapping("/addparprofil")
    public ServeurResponse createNotificationParProfil(HttpServletRequest request) {
	 	
        ServeurResponse response = new ServeurResponse();
		try {
			Notification n = new ObjectMapper().readValue(request.getParameter("notification"), new TypeReference<Notification>() {});
	        Profil p = new ObjectMapper().readValue(request.getParameter("profil"), new TypeReference<Profil>() { });
	        List<Utilisateur> utilisateurs = userService.findUsersByUtiProfil(p);
	        iNotificationService.save(n);
	        utilisateurs.forEach(u -> {
	        	NotificationDestinataire nd = new NotificationDestinataire(n, u);
	        	notDesService.save(nd);
	        });
	        iNotificationService.save(n);
	        response.setStatut(true);
	        response.setData(n);
	        response.setDescription("Enregistrement reussi");
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
        return response;
    }
}

