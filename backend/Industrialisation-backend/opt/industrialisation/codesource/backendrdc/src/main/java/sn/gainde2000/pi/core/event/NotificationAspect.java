/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.event;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author indiaye
 */
@Aspect
@Configuration
public class NotificationAspect {
    @Autowired
    ApplicationEventPublisher appPublisher;
    
    @Autowired
    NotificationEventPublisher publisher ;
    
    @Pointcut("execution(* sn.gainde2000.pi.core.event.NotificationChecker.*(..))")
    private void atClassNotificationChecker() {}
    
    @Pointcut("execution(* *.notificationCheckEvent(..))")
    private void isChecknotification() {}
    
<<<<<<< HEAD
    private NotificationEvent createNotificationEvent(String result) {    
        return new NotificationEvent(result);    
=======
    private NotificationEvent createNotificationEvent(NotificationEvent result) {    
        return result;    
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
    }      
        
   @AfterReturning(pointcut = "atClassNotificationChecker() && isChecknotification()", returning = "retVal")
   public void afterReturningAdvice(JoinPoint jp, Object retVal){
      if(retVal!=null){        
<<<<<<< HEAD
        publisher.publishNotificationEvent(createNotificationEvent(retVal.toString()));
=======
          NotificationEvent notifEvent = (NotificationEvent) retVal;
          publisher.publishNotificationEvent(createNotificationEvent(notifEvent));
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
      }      
   }  
}
