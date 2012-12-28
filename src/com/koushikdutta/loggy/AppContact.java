package com.koushikdutta.loggy;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

public class AppContact {
	String number;
	String contactID;
	String name;
	
	public AppContact(String number, String contactID, Context context) {
		super();
		this.number = number;
		this.contactID = contactID;
		this.name = "unkonwn";
		if(isExistingContact()){
			this.name = getNameFromId(context, contactID);
		}
		
	}
	
	
	public boolean isExistingContact(){
		return contactID!=null && contactID!="";
	}
	

	
	public static String getNameFromId(Context context, String id){
		String name = "unknown id";
		Cursor c = context.getContentResolver()
				.query(ContactsContract.Contacts.CONTENT_URI,
						new String[]{
							ContactsContract.Contacts.DISPLAY_NAME,
							ContactsContract.Contacts._ID
						}, 
						ContactsContract.Contacts._ID +" = "+id,
						null, 
						null);
		if (c!=null){
			for (boolean hasData = c.moveToFirst(); hasData; hasData = c.moveToNext()) {
				name = Tools.getString(c, ContactsContract.Contacts.DISPLAY_NAME);
			}	
		}
		return name;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getContactID() {
		return contactID;
	}


	public void setContactID(String contactID) {
		this.contactID = contactID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
}
