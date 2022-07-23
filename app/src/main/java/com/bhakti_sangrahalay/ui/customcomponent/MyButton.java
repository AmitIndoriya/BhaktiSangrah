package com.bhakti_sangrahalay.ui.customcomponent;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

public class MyButton extends androidx.appcompat.widget.AppCompatTextView {

    Context context;
    String ttfName;

    String TAG = getClass().getName();

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            Log.i(TAG, attrs.getAttributeName(i));
            /*
             * Read value of custom attributes
             */

            this.ttfName = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "ttf_name");

            init();
        }

    }

    private void init() {
        //Typeface.createFromAsset(getAssets(), "fonts/Laila-Bold.ttf");
        Typeface font = Typeface.createFromAsset(context.getAssets(), ttfName);
        setTypeface(font);
    }

    @Override
    public void setTypeface(Typeface tf) {

        // TODO Auto-generated method stub
        super.setTypeface(tf);
    }

}
