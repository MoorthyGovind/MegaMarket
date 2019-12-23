package com.store.stock.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

@FunctionalInterface
public interface SendEmailService {

	public void sendEmail(String emailTo, String subject, String body) throws AddressException, MessagingException;
}
