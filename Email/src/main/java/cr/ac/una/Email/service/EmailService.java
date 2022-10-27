/*
 */
package cr.ac.una.Email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author BiblioPZ UNA
 */
@Service("emailService")
public class EmailService {
    
    private JavaMailSender javaMail;
    
    @Autowired 
    public EmailService(JavaMailSender javaMail){
        this.javaMail = javaMail;
    }
    
    @Async
    public void sendEmail(SimpleMailMessage email){
        javaMail.send(email);
    }
    
}

//Información del @autowired https://www.arquitecturajava.com/spring-autowired-y-la-inyeccion-de-dependencias/ - Diego
