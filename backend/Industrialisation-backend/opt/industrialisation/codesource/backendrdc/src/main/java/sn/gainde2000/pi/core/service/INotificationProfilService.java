package sn.gainde2000.pi.core.service;

import java.util.List;
import java.util.Optional;

import sn.gainde2000.pi.core.entity.Notification;
import sn.gainde2000.pi.core.entity.NotificationProfil;
import sn.gainde2000.pi.core.entity.Profil;

/**
*
* @author sagueye
*/
public interface INotificationProfilService {
	public List<NotificationProfil> getListNotificationProfil();
	
	public List<Profil> getListProfilParNotifications(Notification notification);

    public NotificationProfil save(NotificationProfil notificationProfil);

    public Optional<NotificationProfil> findById(Long id);

	public void supprimerNotification(NotificationProfil NotificationProfil);
}
