package com.koushikdutta.loggy;

import java.util.Date;
import java.util.HashMap;

public class Sms implements Comparable<Sms> {

	Date date;
	String message;
	AppContact contact;
	int status;
	HashMap<String, String> info;
	
	public Sms(Date date, String message, AppContact contact, int status, HashMap<String, String> info) {
		super();
		this.date = date;
		this.message = message;
		this.contact = contact;
		this.status = status;
		this.info = (info == null ? new HashMap<String, String>() : info);
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

	public HashMap<String, String> getInfo() {
		return info;
	}

	public void setInfo(HashMap<String, String> info) {
		this.info = info;
	}
}
