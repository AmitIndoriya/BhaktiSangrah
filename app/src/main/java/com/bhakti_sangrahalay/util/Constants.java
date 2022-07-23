package com.bhakti_sangrahalay.util;

import android.app.Activity;
import android.util.DisplayMetrics;

public class Constants {
    private Activity activity;
    private static Constants constants;
    private int screenHeight;
    private int screenWidth;

    private Constants(Activity activity) {
        this.activity = activity;
        init();
    }

    public static Constants getInstance(Activity activity) {
        if (constants == null) {
            constants = new Constants(activity);
        }
        return constants;
    }

    private void init() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenWidth = displaymetrics.widthPixels;
        screenHeight = displaymetrics.heightPixels;
    }

    public static Constants getConstants() {
        return constants;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }
}
