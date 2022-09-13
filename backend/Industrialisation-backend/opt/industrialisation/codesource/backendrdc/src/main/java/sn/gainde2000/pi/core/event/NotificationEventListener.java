/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import sn.gainde2000.pi.core.tools.SendNotification;

/**
 *
 * @author indiaye
 */
@EnableAsync
@Component
public class NotificationEventListener {
    @Autowired
    SendNotification sendNotification;
        
    @Async
    @EventListener
    public void notificationEventListener(NotificationEvent event) {        
<<<<<<< HEAD
        if(event.getAction() != null){
            sendNotification.checkedNotification(event.getAction());
=======
        if(event.getAction() != null){           
            sendNotification.checkedNotification(event.getAction(), event.getNotifinfos());
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
        }      
    }
    
}
