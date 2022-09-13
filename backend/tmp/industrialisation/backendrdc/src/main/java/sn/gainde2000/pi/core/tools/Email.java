/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.tools;

/**
 *
 * @author indiaye
 */
import java.util.List;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sn.gainde2000.pi.config.AppProperties;

@Component
public class Email {

    @Autowired
    AppProperties app;
    
    public static final String MAIL_HOST = "172.16.2.11";
    private static final String FROM = "no-reply@gainde2000.sn";
    private static final String PASSWORD = "";

    public void sendMail(String from, String to, String message, String subject) throws MessagingException {
        //System.out.println("----------------------AppProperties.getHostmail()-----------------------"+app.getHostmail());
        Properties props = new Properties();
        props.put("mail.smtp.host", app.getHostmail());
        Session session = Session.getInstance(props);
        MimeMessage msg = new MimeMessage(session);
        if(from!=null){
            msg.setFrom(new InternetAddress(from));
        } else {
            msg.setFrom(new InternetAddress(app.getFrommail()));
        }        
        msg.setRecipient(RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(message);
        Transport.send(msg);
    }

    public boolean sendMessage2(String subject, String text, String destinataire) {
        boolean isSend = false;
        try {
            sendMail(FROM, destinataire, text, subject);
            isSend = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return isSend;
    }

    public void sendMessage(String subject, String text, String destinataire) {
        try {
            sendMail(FROM, destinataire, text, subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendHtmlMail(String password, String from, String to, String message, String subject) throws MessagingException {

        //System.out.println("message html");
        Properties props = new Properties();
        props.put("mail.smtp.host", MAIL_HOST);

        Session session = Session.getInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(message, "utf-8", "html");
        Transport.send(msg);
    }

    public void broadcastHtmlMail(String password, String from, List<String> to, String message, String subject) throws MessagingException {

        //System.out.println("message html");

        Properties props = new Properties();
        props.put("mail.smtp.host", MAIL_HOST);

        Session session = Session.getInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        for (int i = 0; i < to.size(); i++) {
            msg.addRecipients(RecipientType.TO, InternetAddress.parse(to.get(i)));
        }
        msg.setSubject(subject);
        msg.setText(message, "utf-8", "html");
        Transport.send(msg);
    }

    public boolean sendHtmlMessage(String subject, String text, String destinataire, String link) {
        boolean isSend = false;
       
        String message = "<div style=\"width:600px;height: 609px;border-radius: 5px;border: solid 1px #0f569d;background-color: #f6f6f6;\">\n"
                + "<div style=\"width: 100%;height: 68px;border-bottom: : solid 1px #979797;background-color: #0f569d; position: relative;top:0;;background: #0f569d;\">\n"
                + "	<div style=\"align:center;padding:5px;display:block;font-family: TrebuchetMS;font-size: 21px;font-weight: normal;font-style: normal;font-stretch: normal;line-height: normal;letter-spacing: normal;text-align: center;color: #ffffff;\">Overflight / landing and stopover authorization request platform</div>\n"
                + "	\n"
                + "	</div>\n"
                + "	<div style=\"margin:25px;width: 91%;height: 80%;border: solid 0.5px #979797;background-color: #ffffff;\">\n"
                + "		<div style=\"display: block; text-align: center; margin: 5px; font-family: TrebuchetMS;font-size: 21px;font-weight: bold;font-style: normal;font-stretch: normal;line-height: normal;letter-spacing: normal;color: #0f569d;\">\n"
                + subject + "\n"
                + "		</div>\n"
                + "		<div style=\"margin: 10px;margin-top:20px;  font-family: TrebuchetMS;font-size: 21px;font-weight: normal;font-style: normal;font-stretch: normal;line-height: 1.43;letter-spacing: normal;color: rgba(0, 0, 0, 0.85);\">\n"
                + text
                + "		</div><br/><br/><br/>\n\n\n\n"
                + "		<center><a href=\""+link+"\" style=\"text-decoration:none;display:block;width: 90%;height: 20px;border: solid 1px #979797;background-color: #0f569d; color:white;margin-top: 50px;cursor: pointer;padding: 20px;\">Login</a></center>\n"
                + "	</div>\n"
                + "</div>";
        try {
            sendHtmlMail(PASSWORD, FROM, destinataire, message, subject);
            isSend = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return isSend;
    }

    public boolean broadcastHtmlMessage(String subject, String text, List<String> destinataire, String link) {
        boolean isSend = false;

        String message = "<div style=\"width:600px;height: 609px;border-radius: 5px;border: solid 1px #0f569d;background-color: #f6f6f6;\">\n"
                + "<div style=\"width: 100%;height: 68px;border-bottom: : solid 1px #979797;background-color: #0f569d; position: relative;top:0;;background: #0f569d;\">\n"
                + "	<div style=\"align:center;padding:5px;display:block;font-family: TrebuchetMS;font-size: 21px;font-weight: normal;font-style: normal;font-stretch: normal;line-height: normal;letter-spacing: normal;text-align: center;color: #ffffff;\">Overflight / landing and stopover authorization request platform</div>\n"
                + "	\n"
                + "	</div>\n"
                + "	<div style=\"margin:25px;width: 91%;height: 80%;border: solid 0.5px #979797;background-color: #ffffff;\">\n"
                + "		<div style=\"display: block; text-align: center; margin: 5px; font-family: TrebuchetMS;font-size: 21px;font-weight: bold;font-style: normal;font-stretch: normal;line-height: normal;letter-spacing: normal;color: #0f569d;\">\n"
                + subject + "\n"
                + "		</div>\n"
                + "		<div style=\"margin: 10px;margin-top:20px;  font-family: TrebuchetMS;font-size: 21px;font-weight: normal;font-style: normal;font-stretch: normal;line-height: 1.43;letter-spacing: normal;color: rgba(0, 0, 0, 0.85);\">\n"
                + text
                + "		</div>\n\n\n\n"
                + "		<center><a href=\""+link+"\" style=\"text-decoration:none;display:block;width: 90%;height: 20px;border: solid 1px #979797;background-color: #0f569d; color:white;margin-top: 50px;cursor: pointer;padding: 20px;\">Login</a></center>\n"
                + "	</div>\n"
                + "</div>";
        try {
            broadcastHtmlMail(PASSWORD, FROM, destinataire, message, subject);
            isSend = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return isSend;
    }
    
}
