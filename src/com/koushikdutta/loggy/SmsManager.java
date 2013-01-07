package com.koushikdutta.loggy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsMessage;

public class SmsManager {

	 private Context _context;
	 
	    
	    public static final String INBOX = "content://sms/inbox";
	    public static final String SENTBOX = "content://sms/sent";
	    public static final String DRAFTS = "content://sms/drafts";
	    public static final String ALL = "content://sms";
	    private static final String COLUMNS[] = new String[] { "person", "address", "body", "date", "type", "status", "threadID" };
	    private static final String SORT_ORDER = "date DESC";

	    public SmsManager(Context baseContext) {
	        _context = baseContext;
	    }

	    
	    public ArrayList<Sms> getsms(String folder, String where){
	    	ArrayList<Sms> result = new ArrayList<Sms>();
	    	if(folder==null){ folder = ALL; }
	    	Cursor c = _context.getContentResolver().query(Uri.parse(folder), COLUMNS, where, null, SORT_ORDER);
	    	 if (c != null) {
	             for (boolean hasData = c.moveToFirst(); hasData; hasData = c.moveToNext()) {
	            	 String body = Tools.getString(c, "body");
	            	 String address = Tools.getString(c, "address");
	            	 String person = Tools.getString(c, "person");
	            	 Date date = Tools.getDateSeconds(c, "date");
	            	 AppContact contact = new AppContact(address, person, _context);
	            	 int status = -1;
	            	 switch (Tools.getInt(c, "type")) {
					case 1:
						status = Sms.RECIEVED;
						break;
					case 2:
						status = Sms.SENT;
						break;
					case 3:
						status = Sms.DRAFT;
						break;

					default:
						break;
					}
	            	 Sms sms = new Sms(date, body, contact, status);
	            	 
	            	 result.add(sms);
	             }
	    	 }
	    	 return result;
	    }
	    
	    public ArrayList<SMSThread> getThreads(){
	    	
			return null;
	    }
}
