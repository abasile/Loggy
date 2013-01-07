package com.koushikdutta.loggy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;


public class Tools {
    public final static String LOG_TAG = "gtalksms";
    public final static String APP_NAME = "GTalkSMS";
    public final static String LineSep = System.getProperty("line.separator");
    public final static int shortenTo = 35;
    
    public final static String getFileFormat(Calendar c) {
        return 
            c.get(Calendar.YEAR) + 
            "-" + 
            String.format("%02d", (c.get(Calendar.MONTH)+ 1)) + 
            "-" + 
            String.format("%02d", c.get(Calendar.DAY_OF_MONTH)) + 
            " " + 
            String.format("%02d", c.get(Calendar.HOUR_OF_DAY)) + 
            "h" + 
            String.format("%02d", c.get(Calendar.MINUTE)) + 
            "m" + 
            String.format("%02d", c.get(Calendar.SECOND)) + 
            "s";
    }
    
    public final static String getVersionName(Context context) {

        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
            return "";
        }
    }
    
    public final static String getVersion(Context context, Class<?> cls) {

        try {
            ComponentName comp = new ComponentName(context, cls);
            PackageInfo pinfo = context.getPackageManager().getPackageInfo(comp.getPackageName(), 0);

            return pinfo.versionName;
        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
            return "";
        }
    }
    
    public final static String getVersionCode(Context context, Class<?> cls) {

        try {
            ComponentName comp = new ComponentName(context, cls);
            PackageInfo pinfo = context.getPackageManager().getPackageInfo(comp.getPackageName(), 0);

            return "" + pinfo.versionCode;
        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
            return "";
        }
    }
    
    public final static <T> List<T> getLastElements(ArrayList<T> list, int nbElems) {
        return list.subList(Math.max(list.size() - nbElems, 0), list.size());
    }
    
    public final static Long getLong(Cursor c, String col) {
        return c.getLong(c.getColumnIndex(col));
    }
    
    public final static int getInt(Cursor c, String col) {
        return c.getInt(c.getColumnIndex(col));
    }

    public final static String getString(Cursor c, String col) {
        return c.getString(c.getColumnIndex(col));
    }

    public final static boolean getBoolean(Cursor c, String col) {
        return getInt(c, col) == 1;
    }

    public final static Date getDateSeconds(Cursor c, String col) {
        return new Date(Long.parseLong(Tools.getString(c, col)) * 1000);
    }

    public final static Date getDateMilliSeconds(Cursor c, String col) {
        return new Date(Long.parseLong(Tools.getString(c, col)));
    }
    

    
    public final static boolean isInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException nfe) {}
        return false;
    }
    
    public final static Integer parseInt(String value) {
        Integer res = null;
        try { 
            res = Integer.parseInt(value); 
        } catch(Exception e) {}
        
        return res;
    }
    
    
    public final static Integer parseInt(String value, Integer defaultValue) {
        Integer res = defaultValue;
        try { 
            res = Integer.parseInt(value); 
        } catch(Exception e) {}
        
        return res;
    }
    
    public final static int getMinNonNeg(int... x) {
        int min = Integer.MAX_VALUE;
        for(int i : x) {
            if(i >= 0 && i < min)
                min = i;
        }
        return min;
    }
    
    public final static boolean isDonateAppInstalled(Context context) {
        return 0 == context.getPackageManager().checkSignatures( context.getPackageName(), "com.googlecode.gtalksmsdonate");
    }
    
    public final static boolean copyFile(File from, File to) {
        if (!from.isFile() || !to.isFile())
            return false;

        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(from);
            out = new FileOutputStream(to);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    /* Ignore */
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    /* Ignore */
                }
            }
        }
        return true;
    }
    
    public final static boolean writeFile(byte[] data, File file) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public final static String getAppBaseDir(Context ctx) {
        String filesDir = ctx.getFilesDir().toString();
        int index = filesDir.indexOf("/files");
        String res = filesDir.substring(0, index);
        return res;
    }
    
    public final static String getSharedPrefDir(Context ctx) {
        return getAppBaseDir(ctx) + "/shared_prefs";
    }
    
    public final static String shortenString(String s) {
        if (s.length() > 20) {
            return s.substring(0, 20);
        } else {
            return s;
        }
    }
    
    public final static String shortenMessage(String message) {
        String shortendMessage;
        if (message == null) {
            shortendMessage = "";
        } else if (message.length() < shortenTo) {
            shortendMessage = message.replace("\n", " ");
        } else {
            shortendMessage = message.substring(0, shortenTo).replace("\n", " ") + "...";
        }
        return shortendMessage;
    }    
    
    
    
    /**
     * Returns a String in the simple date format
     * 
     * @return the current date in dd/MM/yyyy format
     */
    public static String currentDate() {
        DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        return DATE_FORMAT.format(cal.getTime());
    }
    
    public static String ipIntToString(int ip) {
        return String.format("%d.%d.%d.%d", 
        (ip & 0xff), 
        (ip >> 8 & 0xff),
        (ip >> 16 & 0xff),
        (ip >> 24 & 0xff));
    }
}
