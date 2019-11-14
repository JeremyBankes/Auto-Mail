package com.jeremy.automail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	private String emailAddress;
	private Session session;

	public EmailSender(String emailAddress, String password, String host, int port) {
		this.emailAddress = emailAddress;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", String.valueOf(port));

		// Get the Session object.
		session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailAddress, password);
			}
		});
	}

	public void send(Email email, String emailAddress) throws EmailSendException {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(this.emailAddress));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
			message.setSubject(email.getSubject());
			message.setContent(email.getMessage(), "text/html");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new EmailSendException(e.getMessage());
		}
	}

	public static class EmailSendException extends Exception {

		private static final long serialVersionUID = 1L;

		public EmailSendException(String message) {
			super(message);
		}

	}

}
