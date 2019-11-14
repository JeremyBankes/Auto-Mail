package com.jeremy.automail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Email {

	private String subject;
	private StringBuffer message;

	public Email(String subject, String message) {
		this.subject = subject;
		this.message = new StringBuffer(message);
	}

	public Email(String subject) {
		this(subject, "");
	}

	public Email(String subject, InputStream inputStream) throws IOException {
		this(subject);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while ((line = reader.readLine()) != null) {
			add(line);
		}
		reader.close();
	}

	public Email(String subject, File file) throws IOException {
		this(subject, new FileInputStream(file));
	}

	public void add(String message) {
		this.message.append(message);
	}

	public void addLine(String message) {
		add(message);
		add("<br>");
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message.toString();
	}

}
