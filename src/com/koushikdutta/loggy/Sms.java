package com.koushikdutta.loggy;

import java.util.Date;

public class Sms implements Comparable<Sms> {
	public static int RECIEVED = 0;
	public static int SENT = 1;
	public static int SENDING = 2;
	public static int DRAFT = 3;

	Date date;
	String message;
	AppContact contact;
	int status;
	
	public Sms(Date date, String message, AppContact contact, int status) {
		super();
		this.date = date;
		this.message = message;
		this.contact = contact;
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AppContact getContact() {
		return contact;
	}

	public void setContact(AppContact contact) {
		this.contact = contact;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int compareTo(Sms another) {
		return this.getDate().compareTo(another.getDate());
	}
}
