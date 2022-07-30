package com.bhakti_sangrahalay.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.bhakti_sangrahalay.app.MyApp;
import com.bhakti_sangrahalay.model.PlaceModel;
import com.google.gson.Gson;
import com.indoriya.panchang.Place;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {

    public String readFromFile(Resources resources, int file) {
        String jsonStr = "";
        try {
            InputStream inputStream = resources.openRawResource(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString;
            StringBuilder stringBuilder = new StringBuilder();
            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }
            inputStream.close();
            jsonStr = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            Log.e("readFromFile", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("readFromFile", "Can not read file: " + e.toString());
        }
        return jsonStr;
    }

    public int[] getIntArray(Resources resources, int id) {
        final TypedArray sounds = resources.obtainTypedArray(id);
        int[] resIds = new int[sounds.length()];
        for (int i = 0; i < sounds.length(); i++) {
            resIds[i] = sounds.getResourceId(i, -1);
        }
        sounds.recycle();
        return resIds;
    }


    public static boolean checkPermission(Activity activity) {
        boolean boolVal = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                boolVal = false;
            }
        }
        return boolVal;
    }


    public static void requestPermission(final Activity activity, Fragment fragment, final int permissionRequestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Need Read external storage permission.");
                builder.setTitle("Please grant permission");
                builder.setPositiveButton("OK", (dialogInterface, i) -> ActivityCompat.requestPermissions(
                        activity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        permissionRequestCode
                ));
                builder.setNeutralButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                fragment.requestPermissions(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        permissionRequestCode
                );
            }
        } else {
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    permissionRequestCode
            );
        }

    }

    public void requestPermission(final Activity activity, final int permissionRequestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Need Read external storage permission.");
                builder.setTitle("Please grant permission");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                activity,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                permissionRequestCode
                        );
                    }
                });
                builder.setNeutralButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                activity.requestPermissions(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        permissionRequestCode
                );
            }
        } else {
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    permissionRequestCode
            );
        }
    }

    public static boolean isFileExist(String fileName, long filesize) {
        boolean val = false;
        File file = new File(getStoragePath(), fileName);
        if (file.exists()) {
            long len = file.length();
            if (len >= filesize) {
                val = true;
            } else {
                file.delete();
            }
        }
        return val;
    }

    public static boolean isConnectedWithInternet(Context context) {
        boolean _isNetAvailable = false;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null) {
            _isNetAvailable = wifiNetwork.isConnectedOrConnecting();
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null) {
            _isNetAvailable = mobileNetwork.isConnectedOrConnecting();
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            _isNetAvailable = activeNetwork.isConnectedOrConnecting();
        }
        return _isNetAvailable;
    }

    public static void printLog(String msg) {
        Log.i("LogMsg>>", msg);
    }

    public static String getStoragePath() {
        // String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "BhaktiSangrahalay";
        return MyApp.applicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "";
    }

    public static Calendar getCalenderObj() {
        return Calendar.getInstance();
    }

    public static Place getPlaceForPanchang() {

        double latitude = 28.6139;
        double longitude = 77.2090;
        double timezone = +5.5;
        return new Place(latitude, longitude, timezone);
    }

    public static Place getPlaceForPanchang(SharedPreferences preferences) {
        PlaceModel placeModel = getObejectInPreference(preferences);
        double latitude = 28.6139;
        double longitude = 77.2090;
        double timezone = +5.5;
        if (placeModel != null) {
            latitude = placeModel.getLatitude();
            longitude = placeModel.getLongitude();
            timezone = +5.5;
        }
        return new Place(latitude, longitude, timezone);
    }

    public static String convertTimeToAmPm(String time) {

        int hr, min;
        String TimeWithMeradian;
        String[] SplitTime = time.split(":");
        try {
            hr = Integer.parseInt(SplitTime[0]);
            min = Integer.parseInt(SplitTime[1]);

            //am
            if (hr < 12) {
                TimeWithMeradian = appendZeroOnSingleDigit(hr) + ":" + appendZeroOnSingleDigit(min) + " AM";
            } else if (hr < 24) {   //pm
                hr = hr == 12 ? 12 : hr - 12;
                TimeWithMeradian = appendZeroOnSingleDigit(hr) + ":" + appendZeroOnSingleDigit(min) + " PM";
            } else {  // more than 24
                hr = hr - 24;
                TimeWithMeradian = "+" + appendZeroOnSingleDigit(hr) + ":" + appendZeroOnSingleDigit(min) + " AM";
            }
        } catch (Exception e) {
            TimeWithMeradian = time;
        }
        return TimeWithMeradian;
    }

    private static String appendZeroOnSingleDigit(int time) {
        String rTime = String.valueOf(time);
        if (time < 10 /*&& time != 0*/) {
            rTime = "0" + time;
        }
        return rTime;
    }

    public static String getDateForPanchangHeading(Date date) {
        String outputPattern = "dd MMM, yyyy";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        String str = null;
        try {
            str = outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static Typeface getRegularTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Laila-Regular.ttf");
    }

    public static Typeface getMediumTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Laila-Medium.ttf");
    }

    public static Typeface getSemiBoldTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Laila-SemiBold.ttf");
    }

    public static Typeface getBoldTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Laila-Bold.ttf");
    }

    public static String[] getMonthList() {
        return new String[]{"जनवरी", "फरवरी", "मार्च", "अप्रैल", "मई", "जून", "जुलाई", "अगस्त", "सितम्बर", "अक्टूबर", "नवम्बर", "दिसम्बर"};
    }

    public static String[] getWeekList() {
        return new String[]{"रविवार", "सोमवार", "मंगलवार", "बुधवार", "गुरुवार", "शुक्रवार", "शनिवार"};
    }

    @SuppressLint("SimpleDateFormat")
    public static String getWeekDay(Calendar calendar) {
        return new SimpleDateFormat("EEEE").format(calendar.getTime());
    }

    @SuppressLint("SimpleDateFormat")
    static String getMonthName(Calendar calendar) {
        return new SimpleDateFormat("MMM").format(calendar.getTime());
    }

    public static void saveObejectInPreference(SharedPreferences sharedPreferences, PlaceModel placeModel) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(placeModel);
        prefsEditor.putString("place", json);
        prefsEditor.apply();
    }

    public static PlaceModel getObejectInPreference(SharedPreferences sharedPreferences) {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("place", null);
        return gson.fromJson(json, PlaceModel.class);
    }

    public static int convertDpToPx(Resources resources, int dip) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, resources.getDisplayMetrics());
        return (int) px;
    }

    public static String getFormattedTime(int hour, int minute, int am_pm) {
        String hourStr = String.valueOf(hour);
        String minuteStr = String.valueOf(minute);
        String am_pmStr = am_pm == 0 ? "AM" : "PM";
        if (hour > 12) {
            hour = hour - 12;
        }
        if (hour < 10) {
            hourStr = "0" + hour;
        }
        if (minute < 10) {
            minuteStr = "0" + minute;
        }
        return hourStr + ":" + minuteStr + " " + am_pmStr;
    }
}
