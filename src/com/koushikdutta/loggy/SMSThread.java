package com.koushikdutta.loggy;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * represent the sms exchanged in a conversation
 *
 */
public class SMSThread {
	ArrayList<Sms> smsList;

    HashMap<String,String> info;
	public SMSThread(HashMap info) {
		this.info = info;
	}


    public HashMap<String, String> getInfo() {
        return info;
    }

    public void setInfo(HashMap<String, String> info) {
        this.info = info;
    }

    public ArrayList<Sms> getSmsList() {
        return smsList;
    }

    public void setSmsList(ArrayList<Sms> smsList) {
        this.smsList = smsList;
    }




}
