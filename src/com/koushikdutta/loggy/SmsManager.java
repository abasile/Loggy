package com.koushikdutta.loggy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
	    private static final String COLUMNS[] = new String[] { 
	    	MessagesConsts.TextBasedSmsColumns.PERSON, 
	    	MessagesConsts.TextBasedSmsColumns.ADDRESS, 
	    	MessagesConsts.TextBasedSmsColumns.BODY, 
	    	MessagesConsts.TextBasedSmsColumns.DATE,
	    	MessagesConsts.TextBasedSmsColumns.TYPE, 
	    	MessagesConsts.TextBasedSmsColumns.STATUS, 
	    	MessagesConsts.TextBasedSmsColumns.THREAD_ID,
	    	
	    	MessagesConsts.TextBasedSmsColumns.DATE_SENT,
	    	MessagesConsts.TextBasedSmsColumns.PERSON_ID,
	    	MessagesConsts.TextBasedSmsColumns.SUBJECT,
//	    	MessagesConsts.TextBasedSmsColumns.META_DATA,
	    	MessagesConsts.TextBasedSmsColumns.SEEN
	    	};
	    private static final String SORT_ORDER = "date DESC LIMIT 100";

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
	            	 int status = (Tools.getInt(c, "type")) ;
	            	 
	            	 HashMap<String, String> info = new HashMap<String, String>();
	            	 String[] cn = c.getColumnNames();
	            	 for(int i = 0; i<cn.length;i++){
	            		 info.put(cn[i], Tools.getString(c, cn[i]));
	            	 }
	            			 
	            	 Sms sms = new Sms(date, body, contact, status, info);
	            	 
	            	 result.add(sms);
	             }
	    	 }
	    	 return result;
	    }
	    
	    public ArrayList<SMSThread> getThreads(String where){
            String folder = MessagesConsts.Conversation.CONTENT_URI;
            Cursor c = _context.getContentResolver().query(Uri.parse(folder),null,where,null,SORT_ORDER );
	    	
			return null;
	    }
}
