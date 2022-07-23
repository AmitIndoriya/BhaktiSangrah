package com.bhakti_sangrahalay.panchang.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PanchangUtil {
    public static double fract(double x) {
        long i;
        double y;
        i = (long) x;
        y = x - (double) i;
        return y;
    }

    public static String makelength(String x, int y) {
        int diff = y - x.length();
        for (int i = 0; i < diff; i++)
            x = "0" + x;
        return x;
    }

    public String dms(double x) {
        String parts[] = new String[3];
        double temp;
        String sdms;
        int deg = (int) x;
        parts[0] = makelength(String.valueOf(deg), 2);
        temp = (x - (double) ((int) x));
        int min = (int) (temp * 60);
        parts[1] = makelength(String.valueOf(min), 2);
        temp = temp * 60;
        temp = (temp - (double) ((int) temp));
        int sec = (int) (temp * 60);
        //System.out.println("temp " + temp + " sec " + sec);
        parts[2] = makelength(String.valueOf(sec), 2);
        sdms = parts[0] + getDashString(1) + parts[1] + getDashString(1) + parts[2];
        return sdms;
    }

    public String getDashString(int noOfDash) {
        String DASH = "";
        if (getLanguageCode().equalsIgnoreCase("0")) {
            DASH = ":";
        } else if (getLanguageCode().equalsIgnoreCase("1")) {
            DASH = "&";
        }
        String dash = "";
        for (int i = 0; i < noOfDash; i++) {
            dash = dash + DASH;
        }
        return dash;
    }

    public String getLanguageCode() {
        return "0";
    }

    //*********** Increment in Date Days ***************
    public Date getAddDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    // ***************** fetch DST or not from Time_Zone_String ***************
    public boolean isDst(String timezoneString, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
       /* cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);*/
        Date dateTime = cal.getTime();
        boolean inDs;
        try {
            TimeZone tz = TimeZone.getTimeZone(timezoneString);
            inDs = tz.inDaylightTime(dateTime);
        } catch (Exception ex) {
            inDs = false;
        }
        return inDs;
    }

    //********* Get Constant Class Object **********
    public IConstants getIConstantsObj() {
        return (new ConstantsHi());
    }


}
