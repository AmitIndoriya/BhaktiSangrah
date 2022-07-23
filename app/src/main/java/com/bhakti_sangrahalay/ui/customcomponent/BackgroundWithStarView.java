package com.bhakti_sangrahalay.ui.customcomponent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.bhakti_sangrahalay.R;

public class BackgroundWithStarView extends LinearLayout {
    private Bitmap icon;
    Context context;

    public BackgroundWithStarView(Context context, AttributeSet attrs) {
        super(context, attrs);
       /* int x = 40;
        int y = 100;
        int width = 20;
        int height = 10;*/
        this.context = context;
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.star);
       /* Resources res = context.getResources();

        for (int i = 0; i < 10; i++) {
            iconNew[i] = Bitmap.createScaledBitmap(icon, 30, 30, false);
        }*/
        setWillNotDraw(false);

    }

    public BackgroundWithStarView(Context context) {
        super(context);


    }

    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(context.getResources().getColor(R.color.night_sky_color));
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 50, 50, false), 10, 10, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 30, 30, false), 90, 50, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 45, 45, false), 1, 70, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 50, 50, false), 5, 130, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 30, 30, false), 70, 100, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 30, 30, false), 150, 90, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 50, 50, false), 120, 150, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 50, 50, false), 190, 10, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 60, 60, false), 250, 3, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 60, 60, false), 390, 1, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 60, 60, false), 470, 20, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 45, 45, false), 430, 70, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 50, 50, false), 460, 130, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 50, 50, false), 560, 120, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 50, 50, false), 640, 140, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 50, 50, false), 630, 10, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 50, 50, false), 610, 70, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 50, 50, false), 240, 130, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 40, 40, false), 230, 70, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(icon, 50, 50, false), 340, 145, null);
    }
}