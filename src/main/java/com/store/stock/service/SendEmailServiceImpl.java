package com.store.stock.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImpl implements SendEmailService {

    @Autowired
	private JavaMailSender javaMailSender;
    
	public void sendEmail(String emailTo, String subject, String body) throws AddressException, MessagingException {
		
		 SimpleMailMessage msg = new SimpleMailMessage();
		 System.out.println("emailTo...."+emailTo);
	        msg.setTo(emailTo);

	        msg.setSubject("Testing from Spring Boot");
	        msg.setText("Hello World \n Spring Boot Email");
	        javaMailSender.send(msg);

			 System.out.println("Email Send Successfully...."+emailTo);
	}

}
