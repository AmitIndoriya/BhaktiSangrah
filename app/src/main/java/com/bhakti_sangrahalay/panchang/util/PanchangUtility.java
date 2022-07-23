package com.bhakti_sangrahalay.panchang.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.bhakti_sangrahalay.panchang.model.PlaceModel;
import com.indoriya.panchang.Place;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class PanchangUtility {
    Context context;

    public PanchangUtility(Context context) {
        this.context = context;
    }

    public PanchangUtility() {
    }

    public int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public Typeface getRalewayBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(),
                "fonts/Raleway-Bold.ttf");
    }

    public Typeface getRalewayLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(),
                "fonts/Raleway-Light.ttf");
    }

    public Typeface getRalewayMedium(Context context) {
        return Typeface.createFromAsset(context.getAssets(),
                "fonts/Raleway-Medium.ttf");
    }

    public Typeface getRalewayRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(),
                "fonts/Raleway-Regular.ttf");
    }

    public Typeface getRalewaySemibold(Context context) {
        return Typeface.createFromAsset(context.getAssets(),
                "fonts/Raleway-SemiBold.ttf");
    }

    public boolean isInternetAvailable() {
        boolean _isNetAvailable = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null) {
            _isNetAvailable = wifiNetwork.isConnectedOrConnecting();
        }
        NetworkInfo mobileNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null) {
            _isNetAvailable = mobileNetwork.isConnectedOrConnecting();
        }
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            _isNetAvailable = activeNetwork.isConnectedOrConnecting();
        }
        return _isNetAvailable;
    }

  /*  public Cache.Entry getCachedData(String url) {
        Cache cache = VolleySingleton.getInstance(context).getRequestQueue().getCache();
        Cache.Entry entry = cache.get("1-" + url);
        return entry;
    }*/

    public static String FormatDMSIn2DigitStringWithSignForhora(double _fDeg) {
        String strFormattedDeg = null;
        String sDeg = null;
        String sMin = null;
        String sSec = null;
        int _min = 0, _sec = 0;
        double temp = 0;

        double fDeg = _fDeg;
        sDeg = String.valueOf((int) (fDeg));
        switch (sDeg.trim().length()) {
            case 0:
                strFormattedDeg = "00" + sDeg;
                break;
            case 1:
                strFormattedDeg = "0" + sDeg;
                break;
            default:
                strFormattedDeg = sDeg;
                break;
        }

        strFormattedDeg = strFormattedDeg + ":";
        temp = (_fDeg - (double) ((int) _fDeg));
        _min = (int) (temp * 60);
        sMin = String.valueOf(_min);

        if (sMin.trim().length() < 2) {
            strFormattedDeg = strFormattedDeg + "0" + sMin;
        } else {
            strFormattedDeg = strFormattedDeg + sMin;
        }

        strFormattedDeg = strFormattedDeg + ":";

        temp = temp * 60;
        temp = (temp - (double) ((int) temp));
        _sec = (int) (temp * 60);
        sSec = String.valueOf(_sec);

        if (sSec.trim().length() < 2)
            strFormattedDeg = strFormattedDeg + "0" + sSec;
        else
            strFormattedDeg = strFormattedDeg + sSec;

        strFormattedDeg = strFormattedDeg;

        return strFormattedDeg;
    }

    public static String getDateShowRahuKaal(Date date) {
        String inputPattern = "EEE MMM dd HH:mm:ss zzzz yyyy";
        String outputPattern = "dd-MMM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date dateNew = null;
        String str = null;

        try {
            // dateNew = inputFormat.parse(date);
            str = outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getTimeInFormate(String starthr, String startmin) {
        String time = "(" + convertTimeToAmPm(starthr + ":" + startmin) + ")";
        return time;
    }


    public String getTimeInFormate(String rawTime) {
        String time = rawTime;
        if (rawTime != null && rawTime.contains(":")) {
            String startTime[] = rawTime.split(":");
            time = convertTimeToAmPm(startTime[0] + ":" + startTime[1]);
        }
        return time;
    }

    public static String convertTimeToAmPm(String time) {

        int hr = 0, min = 0;
        String TimeWithMeradian = "";
        String[] SplitTime = time.split(":");
        try {
            hr = Integer.parseInt(SplitTime[0]);
            min = Integer.parseInt(SplitTime[1]);

            //am
            if (hr < 12) {
                TimeWithMeradian = appendZeroOnSingleDigit(hr) + ":" + appendZeroOnSingleDigit(min) + " AM";
            } else if (hr >= 12 && hr < 24) {   //pm
                hr = hr == 12 ? 12 : hr - 12;
                TimeWithMeradian = appendZeroOnSingleDigit(hr) + ":" + appendZeroOnSingleDigit(min) + " PM";
            } else if (hr >= 24) {  // more than 24
                hr = hr - 24;
                TimeWithMeradian = "+" + appendZeroOnSingleDigit(hr) + ":" + appendZeroOnSingleDigit(min) + " AM";
            }
        } catch (Exception e) {
            TimeWithMeradian = time;
        }
        return TimeWithMeradian;
    }

    public static String appendZeroOnSingleDigit(int time) {
        String rTime = String.valueOf(time);
        if (time < 10 /*&& time != 0*/) {
            rTime = "0" + time;
        }
        return rTime;
    }

    public String getDateForPanchangHeading(Date date) {
        String outputPattern = "dd MMM, yyyy";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        String str = null;
        try {
            str = outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public Place getPlaceForPanchang(PlaceModel placeBean) {
      /*  if (placeID.equals("1261481")) {
            latitude = 28.6139;
            longitude = 77.2090;
            timezone = +5.5;
            strPlace = "New Delhi";
            strCountry = "India";
            strState = "NCT";
            timezoneString = "Asia/Kolkata";
            if (objPanchangUtil.isDst(timezoneString, datePan)) {
                timezone = timezone + 1.0;
            }
            place = new Place(latitude, longitude, timezone);
            objAajKaPanchangModel.setIsPlaceValid(false);
        } else {
            if (!lat.equals("0") & !lng.equals("0")) {
                latitude = Float.valueOf(lat);
                longitude = Float.valueOf(lng);
                this.timezone = Float.valueOf(tz);
                objAajKaPanchangModel.setIsPlaceValid(true);
            } else {
                latitude = 28.6139;
                longitude = 77.2090;
                timezone = +5.5;
                strPlace = "New Delhi";
                strCountry = "India";
                strState = "NCT";
                timezoneString = "Asia/Kolkata";
                objAajKaPanchangModel.setIsPlaceValid(false);
            }
            if (objPanchangUtil.isDst(this.timezoneString, datePan))
                timezone = timezone + 1.0;

        }*/
       /* double latitude = Float.valueOf(placeBean.getLat());
        double longitude = Float.valueOf(placeBean.getLng());
        double timezone = Float.valueOf(placeBean.getTimeZoneString());*/
        double latitude = 28.6139;
        double longitude = 77.2090;
        double timezone = +5.5;
        return new Place(latitude, longitude, timezone);
    }

    public int convertDpToPx(Resources resources, int dip) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, resources.getDisplayMetrics());
        return (int) px;
    }

    public float convertPxToDp(float px) {
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public String convertDegreeInDMS(double _fDeg, String DegSign, String MinSign, String SecSign) {
        String strFormattedDeg = null;
        String sDeg = null;
        String sMin = null;
        String sSec = null;
        int _min = 0, _sec = 0;
        double temp = 0;
        double fDeg = _fDeg;
        sDeg = String.valueOf((int) (fDeg));
        switch (sDeg.trim().length()) {
            case 0:
                strFormattedDeg = "000" + sDeg;
                break;
            case 1:
                strFormattedDeg = "00" + sDeg;
                break;
            case 2:
                strFormattedDeg = "0" + sDeg;
                break;
            default:
                strFormattedDeg = sDeg;
                break;
        }

        strFormattedDeg = strFormattedDeg + DegSign;
        temp = (_fDeg - (double) ((int) _fDeg));
        _min = (int) (temp * 60);
        sMin = String.valueOf(_min);

        if (sMin.trim().length() < 2)
            strFormattedDeg = strFormattedDeg + "0" + sMin;
        else
            strFormattedDeg = strFormattedDeg + sMin;

        strFormattedDeg = strFormattedDeg + MinSign;

        temp = temp * 60;
        temp = (temp - (double) ((int) temp));
        _sec = (int) (temp * 60);

        sSec = String.valueOf(_sec);

        if (sSec.trim().length() < 2)
            strFormattedDeg = strFormattedDeg + "0" + sSec;
        else
            strFormattedDeg = strFormattedDeg + sSec;

        strFormattedDeg = strFormattedDeg + SecSign;

        return strFormattedDeg.trim();
    }

    public String getRasiNakSubSub(double d, String[] RasiLord,
                                   String[] NakLord) {
        int[] y1 = {7, 20, 6, 10, 7, 18, 16, 19, 17};
        StringBuilder sb = new StringBuilder();
        int a, b, c, f, i = 0;
        /*
         * if(d<0) d *=-1;
         */

        f = (int) (d / 30.0);
        a = (int) (d / 120.0);
        d -= a * 120.0;
        a = (int) (d * 3.0 / 40.0);
        d -= a * 40.0 / 3.0;
        d *= 9;
        for (b = 0; b < 9; b++) {
            i = a + b;
            if (i >= 9)
                i -= 9;
            if (y1[i] <= d)
                d -= y1[i];
            else
                break;
        }
        b = i;
        d = d / y1[b] * (40.0 / 3.0);
        d *= 9;
        for (c = 0; c < 9; c++) {
            i = b + c;
            if (i >= 9)
                i -= 9;
            if (y1[i] <= d)
                d -= y1[i];
            else
                break;
        }
        c = i;

        sb.append(RasiLord[f] + "-");
        sb.append(NakLord[a] + "-");
        sb.append(NakLord[b] + "-");
        sb.append(NakLord[c]);

        return sb.toString().trim();
    }

    public boolean checkTimeLiesBtnTwoTimes(String startDate, String endDate) {
        boolean boolVal = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            try {
                Date date1 = sdf.parse(startDate);
                Date date2 = sdf.parse(endDate);
                Date x = Calendar.getInstance().getTime();
                boolean isAfterStartDate = x.after(date1);
                boolean isBeforeEndDate = x.before(date2);

                if (date1.before(date2)) {
                    return true;
                } else {

                    return false;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;

          /*  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

            Date time1 = simpleDateFormat.parse(startDate);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);
            calendar1.add(Calendar.DATE, 1);

            Date time2 = simpleDateFormat.parse(endDate);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);

            Date x = Calendar.getInstance().getTime();
            boolean isAfterStartDate = x.after(calendar1.getTime());
            boolean isBeforeEndDate = x.before(calendar2.getTime());
            if (isAfterStartDate && isBeforeEndDate) {
                boolVal = true;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boolVal;
    }

    public static boolean isTimeBetweenTwoTime(String initialTime, String finalTime) {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        boolean valid = false;
        try {
            if (initialTime.matches(reg) && finalTime.matches(reg)) {

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String currentTime = sdf.format(Calendar.getInstance().getTime());
                //Start Time
                Date inTime = sdf.parse(initialTime);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(inTime);

                //Current Time

                Date checkTime = sdf.parse(currentTime);
                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTime(checkTime);

                //End Time
                Date finTime = sdf.parse(finalTime);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(finTime);

                if (finalTime.compareTo(initialTime) < 0) {
                    calendar2.add(Calendar.DATE, 1);
                    calendar3.add(Calendar.DATE, 1);
                }

                Date actualTime = calendar3.getTime();
                if ((actualTime.after(calendar1.getTime()) || actualTime.compareTo(calendar1.getTime()) == 0) && actualTime.before(calendar2.getTime())) {
                    valid = true;
                }

            }
        } catch (ParseException e) {

        }

        return valid;
    }



}

