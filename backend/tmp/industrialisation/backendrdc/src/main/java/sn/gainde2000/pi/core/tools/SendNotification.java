package sn.gainde2000.pi.core.tools;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import sn.gainde2000.pi.core.entity.Action;
import sn.gainde2000.pi.core.entity.Evenement;
import sn.gainde2000.pi.core.entity.Notification;
import sn.gainde2000.pi.core.entity.Utilisateur;
import sn.gainde2000.pi.core.service.IActionService;
import sn.gainde2000.pi.core.service.IEvenementService;
import sn.gainde2000.pi.core.service.INotificationDestinataireService;
import sn.gainde2000.pi.core.service.INotificationService;
import sn.gainde2000.pi.core.service.impl.UtilisateurImpl;

/**
*
* @author sagueye
*/
@Service
public class SendNotification {
	 @Autowired
	 IEvenementService evService;
	 @Autowired
	 UtilisateurImpl userService;
	 @Autowired
	 INotificationService iNotificationService;
	 @Autowired
	 INotificationDestinataireService notDesService;
	 @Autowired 
	 IActionService   actionService;
	 
	 @Autowired
	 Email email;
	 @Autowired
	 Sms sms;
	 
	public void checkedNotification (String action) {
	    Action a = actionService.findByCode(action);
		String typeNotificationMail = "Mail";
		String typeNotificationSms = "Sms";
		List<Evenement> evenements = evService.getListEvenementParActions(a);
		evenements.forEach(even -> {
			Notification n = even.getNotification();
			Iterable<Utilisateur> utilisateurs = notDesService.getListDestinataireParNotification(n);
			if (n.getNtfTntId().getTntNom().equals(typeNotificationMail)) {
				utilisateurs.forEach(user -> {
					try {
						String from = "no-reply@gainde2000.sn";
				        String to = user.getUtiEmail();
				        String subject = n.getNtfObjet();
				        String message = n.getNtfText();
				        email.sendMail(from, to, message, subject);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				});
			} else if (n.getNtfTntId().getTntNom().equals(typeNotificationSms)){
				utilisateurs.forEach(user -> {
					String to = user.getUtiTelephone();
					String message = n.getNtfText();
					sms.sendSms(to, message);
				});
			}
			else {
				utilisateurs.forEach(user -> {
					
				});
			}
		});
	}
}
