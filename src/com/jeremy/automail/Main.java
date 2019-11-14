package com.jeremy.automail;

import java.io.File;
import java.io.IOException;

import com.jeremy.automail.EmailSender.EmailSendException;

public class Main {

	public static final File EMAIL_FILE = new File("D:\\Jeremy\\Programming\\Java\\Sandbox\\Email.html");

	public static final String[] TO = { "jeremy.bankes@gmail.com" };

	public static void main(String[] args) {
		try {
			EmailSender sender = new EmailSender("coldsteelcraft@gmail.com", "Coldsteel22", "smtp.gmail.com", 587);
			Email email = new Email("This is a test", EMAIL_FILE);
			for (int i = 0, len = TO.length; i < len; i++) {
				String emailAddress = TO[i].toLowerCase();
				try {
					sender.send(email, emailAddress);
					System.out.printf("Successfully sent email '%s'.%n", emailAddress);
				} catch (EmailSendException exception) {
					System.out.printf("Failed to send email to '%s'. %s%n", emailAddress, exception.getMessage());
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
