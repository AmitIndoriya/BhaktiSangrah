package com.bhakti_sangrahalay.ui.customcomponent;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.model.PlanetData;
import com.bhakti_sangrahalay.util.Constants;
import com.bhakti_sangrahalay.util.Utility;

import java.util.ArrayList;


public class ChartView extends View {
    Paint paint;
    Activity activity;
    public Constants constants;
    //Utility Utility;
    Resources resources;
    int rectWidth;
    int X1;
    int X2;
    int midX;
    int Y1;
    int Y2;
    int midY;
    String[] planetsName;
    int[] planetsInRashi;
    int lagna = 0;
    double[] midDegreeArray;

    public ChartView(Activity activity, Resources resources, int[] planetsInRashi, int lagna) {
        super(activity);
        paint = new Paint();
        this.activity = activity;
        constants = Constants.getInstance(activity);
        this.resources = resources;
        //Utility = new Utility(activity);
        rectWidth = constants.getScreenWidth() - Utility.convertDpToPx(resources, 26);
        planetsName = resources.getStringArray(R.array.planet_name);
        this.planetsInRashi = planetsInRashi;
        this.lagna = lagna;
    }

    public ChartView(Activity activity, Resources resources, int[] planetsInRashi, int lagna, double[] midDegreeArray) {
        this(activity, resources, planetsInRashi, lagna);
        this.midDegreeArray = midDegreeArray;
    }


    @Override
    public void onDraw(Canvas canvas) {

        drawNorthKundliDiagram(canvas);
    }

    private void drawRashiInBhav(Canvas canvas, int[] x_axis, int[] y_axis, Paint paint) {
        int lagna1 = lagna;
        for (int i = 0; i < 12; i++) {
            if (lagna1 > 12)
                lagna1 = 1;
            canvas.drawText(String.valueOf(lagna1), x_axis[i], y_axis[i], paint);
            lagna1++;
        }
    }

    private void drawRashiInBhavForChalit(Canvas canvas, int[] x_axis, int[] y_axis, Paint paint) {
        int iRasi = 0;
        for (int i = 0; i < 12; i++) {
            iRasi = (int) (midDegreeArray[i] / 30.0) + 1;
            canvas.drawText(String.valueOf(iRasi), x_axis[i], y_axis[i], paint);
        }
    }

    private void drawNorthKundliDiagram(Canvas canvas) {
        canvas.drawColor(Color.GREEN);
        X1 = Utility.convertDpToPx(resources, 6);
        X2 = rectWidth;
        midX = (rectWidth) / 2;
        Y1 = Utility.convertDpToPx(resources, 6);
        Y2 = rectWidth;
        midY = (rectWidth) / 2;
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        canvas.drawLine(X1, Y1, X2, Y1, paint);
        canvas.drawLine(X1, Y2, X2, Y2, paint);
        canvas.drawLine(X1, Y1, X1, Y2, paint);
        canvas.drawLine(X2, Y1, X2, Y2, paint);
        canvas.drawLine(X1, Y1, X2, Y2, paint);
        canvas.drawLine(X2, Y1, X1, Y2, paint);
        canvas.drawLine(midX, Y1, X1, midY, paint);
        canvas.drawLine(X1, midY, midX, Y2, paint);
        canvas.drawLine(midX, Y2, X2, midY, paint);
        canvas.drawLine(X2, midY, midX, Y1, paint);
        drawHouseNumberOnNorthKundli(canvas);
    }

    private void drawHouseNumberOnNorthKundli(Canvas canvas) {
        int[] x_axis = {
                X1 - Utility.convertDpToPx(resources, 6) + rectWidth / 2,
                X1 - Utility.convertDpToPx(resources, 6) + rectWidth / 4,
                X1 - Utility.convertDpToPx(resources, 30) + rectWidth / 4,
                X1 - Utility.convertDpToPx(resources, 6) + rectWidth / 4,
                X1 - Utility.convertDpToPx(resources, 30) + rectWidth / 4,
                X1 - Utility.convertDpToPx(resources, 6) + rectWidth / 4,
                X1 - Utility.convertDpToPx(resources, 6) + rectWidth / 2,
                X1 - Utility.convertDpToPx(resources, 6) + 3 * rectWidth / 4,
                X1 + Utility.convertDpToPx(resources, 30) + 3 * rectWidth / 4,
                X1 - Utility.convertDpToPx(resources, 6) + 3 * rectWidth / 4,
                X1 + Utility.convertDpToPx(resources, 30) + 3 * rectWidth / 4,
                X1 - Utility.convertDpToPx(resources, 6) + 3 * rectWidth / 4};
        int[] y_axis = {
                Y1 + rectWidth / 4,
                Y1 - Utility.convertDpToPx(resources, 30) + rectWidth / 4,
                Y1 + rectWidth / 4,
                Y1 + rectWidth / 2,
                Y1 + 3 * rectWidth / 4,
                Y1 + Utility.convertDpToPx(resources, 30) + 3 * rectWidth / 4,
                Y1 + 3 * rectWidth / 4,
                Y1 + Utility.convertDpToPx(resources, 30) + 3 * rectWidth / 4,
                Y1 + 3 * rectWidth / 4,
                Y1 + rectWidth / 2,
                Y1 + rectWidth / 4,
                Y1 - Utility.convertDpToPx(resources, 30) + rectWidth / 4,};
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        float textSize = activity.getResources().getDimension(R.dimen.heading_text_size);
        paint.setTextSize(textSize);
        if (midDegreeArray != null && midDegreeArray.length > 0) {
            drawRashiInBhavForChalit(canvas, x_axis, y_axis, paint);
        } else {
            drawRashiInBhav(canvas, x_axis, y_axis, paint);
        }

        printPlanetsInHouse(canvas);
    }


    private void printPlanetsInHouse(Canvas canvas) {
        int spaceX = Utility.convertDpToPx(resources, 40);
        int spaceY = Utility.convertDpToPx(resources, 20);
        float[] housePositionsX0 = {X1 + 4.5f * spaceX, X1 + 3.5f * spaceX, X1 + 5.5f * spaceX, X1 + 3.5f * spaceX, X1 + 5.5f * spaceX, X1 + 4.5f * spaceX};
        float[] housePositionsX1 = {X1 + spaceX, X1 + 2 * spaceX, X1 + 3 * spaceX, X1 + 1.5f * spaceX, X1 + 2.5f * spaceX, X1 + 1.7f * spaceX};
        float[] housePositionsX2 = {X1 + Utility.convertDpToPx(resources, 10), X1 + Utility.convertDpToPx(resources, 10), X1 + Utility.convertDpToPx(resources, 10), X1 + spaceX, X1 + spaceX, X1 + Utility.convertDpToPx(resources, 60)};
        float[] housePositionsX3 = {X1 + 2 * spaceX, X1 + spaceX, X1 + 3 * spaceX, X1 + spaceX, X1 + 3 * spaceX, X1 + 2 * spaceX};
        float[] housePositionsX4 = {X1 + Utility.convertDpToPx(resources, 10), X1 + Utility.convertDpToPx(resources, 10), X1 + Utility.convertDpToPx(resources, 10), X1 + spaceX, X1 + spaceX, X1 + Utility.convertDpToPx(resources, 60)};
        float[] housePositionsX5 = {X1 + 1.5f * spaceX, X1 + 2.5f * spaceX, X1 + 1.7f * spaceX, X1 + spaceX, X1 + 2 * spaceX, X1 + 3 * spaceX};
        float[] housePositionsX6 = {X1 + 4.5f * spaceX, X1 + 3.5f * spaceX, X1 + 5.5f * spaceX, X1 + 3.5f * spaceX, X1 + 5.5f * spaceX, X1 + 4.5f * spaceX};
        float[] housePositionsX7 = {X1 + 6.5f * spaceX, X1 + 7.5f * spaceX, X1 + 6.6f * spaceX, X1 + 6f * spaceX, X1 + 7f * spaceX, X1 + 8f * spaceX};
        float[] housePositionsX8 = {X1 + 7.8f * spaceX, X1 + 8.3f * spaceX, X1 + 8.3f * spaceX, X1 + 8.8f * spaceX, X1 + 8.8f * spaceX, X1 + 8.8f * spaceX};
        float[] housePositionsX9 = {X1 + 7f * spaceX, X1 + 6f * spaceX, X1 + 8f * spaceX, X1 + 6f * spaceX, X1 + 8f * spaceX, X1 + 7f * spaceX};
        float[] housePositionsX10 = {X1 + 7.8f * spaceX, X1 + 8.3f * spaceX, X1 + 8.3f * spaceX, X1 + 8.8f * spaceX, X1 + 8.8f * spaceX, X1 + 8.8f * spaceX};
        float[] housePositionsX11 = {X1 + 6f * spaceX, X1 + 7f * spaceX, X1 + 8f * spaceX, X1 + 6.5f * spaceX, X1 + 7.5f * spaceX, X1 + 6.6f * spaceX};

        float[] housePositionsY0 = {Y1 + 2 * spaceY, Y1 + 3.5f * spaceY, Y1 + 3.5f * spaceY, Y1 + 6 * spaceY, Y1 + 6 * spaceY, Y1 + 7.5f * spaceY};
        float[] housePositionsY1 = {Y1 + spaceY, Y1 + spaceY, Y1 + spaceY, Y1 + 2 * spaceY, Y1 + 2 * spaceY, Y1 + 3 * spaceY};
        float[] housePositionsY2 = {Y1 + 3f * spaceY, Y1 + 5f * spaceY, Y1 + 7f * spaceY, Y1 + 4f * spaceY, Y1 + 6f * spaceY, Y1 + 5.3f * spaceY};
        float[] housePositionsY3 = {Y1 + 7f * spaceY, Y1 + 8.5f * spaceY, Y1 + 8.5f * spaceY, Y1 + 11f * spaceY, Y1 + 11f * spaceY, Y1 + 12.5f * spaceY};
        float[] housePositionsY4 = {Y1 + 12.5f * spaceY, Y1 + 14.5f * spaceY, Y1 + 16.5f * spaceY, Y1 + 13.5f * spaceY, Y1 + 15.5f * spaceY, Y1 + 15f * spaceY};
        float[] housePositionsY5 = {Y1 + 16.5f * spaceY, Y1 + 17.5f * spaceY, Y1 + 17.5f * spaceY, Y1 + 18.5f * spaceY, Y1 + 18.5f * spaceY, Y1 + 18.5f * spaceY};
        float[] housePositionsY6 = {Y1 + 12f * spaceY, Y1 + 13.5f * spaceY, Y1 + 13.5f * spaceY, Y1 + 16f * spaceY, Y1 + 16f * spaceY, Y1 + 17.5f * spaceY};
        float[] housePositionsY7 = {Y1 + 16.5f * spaceY, Y1 + 17.5f * spaceY, Y1 + 17.5f * spaceY, Y1 + 18.5f * spaceY, Y1 + 18.5f * spaceY, Y1 + 18.5f * spaceY};
        float[] housePositionsY8 = {Y1 + 15f * spaceY, Y1 + 13.5f * spaceY, Y1 + 15.5f * spaceY, Y1 + 12.5f * spaceY, Y1 + 14.5f * spaceY, Y1 + 16.5f * spaceY};
        float[] housePositionsY9 = {Y1 + 7f * spaceY, Y1 + 8.5f * spaceY, Y1 + 8.5f * spaceY, Y1 + 11f * spaceY, Y1 + 11f * spaceY, Y1 + 12.5f * spaceY};
        float[] housePositionsY10 = {Y1 + 5.3f * spaceY, Y1 + 4f * spaceY, Y1 + 6f * spaceY, Y1 + 3f * spaceY, Y1 + 5f * spaceY, Y1 + 7f * spaceY};
        float[] housePositionsY11 = {Y1 + spaceY, Y1 + spaceY, Y1 + spaceY, Y1 + 2 * spaceY, Y1 + 2 * spaceY, Y1 + 3 * spaceY};


        ArrayList<ArrayList<PlanetData>> arrayLists = new ArrayList<>();
        ArrayList<float[]> xArrayLists = new ArrayList<>();
        ArrayList<float[]> yArrayLists = new ArrayList<>();
        xArrayLists.add(housePositionsX0);
        xArrayLists.add(housePositionsX1);
        xArrayLists.add(housePositionsX2);
        xArrayLists.add(housePositionsX3);
        xArrayLists.add(housePositionsX4);
        xArrayLists.add(housePositionsX5);
        xArrayLists.add(housePositionsX6);
        xArrayLists.add(housePositionsX7);
        xArrayLists.add(housePositionsX8);
        xArrayLists.add(housePositionsX9);
        xArrayLists.add(housePositionsX10);
        xArrayLists.add(housePositionsX11);

        yArrayLists.add(housePositionsY0);
        yArrayLists.add(housePositionsY1);
        yArrayLists.add(housePositionsY2);
        yArrayLists.add(housePositionsY3);
        yArrayLists.add(housePositionsY4);
        yArrayLists.add(housePositionsY5);
        yArrayLists.add(housePositionsY6);
        yArrayLists.add(housePositionsY7);
        yArrayLists.add(housePositionsY8);
        yArrayLists.add(housePositionsY9);
        yArrayLists.add(housePositionsY10);
        yArrayLists.add(housePositionsY11);
        int[] color = {Color.rgb(165, 42, 42), Color.rgb(220, 20, 60),
                Color.rgb(0, 100, 0), Color.rgb(139, 0, 139), Color.rgb(30, 144, 255),
                Color.rgb(255, 0, 255), Color.rgb(165, 42, 42), Color.rgb(220, 20, 60),
                Color.rgb(0, 100, 0), Color.rgb(139, 0, 139), Color.rgb(30, 144, 255),
                Color.rgb(255, 0, 255), Color.rgb(165, 42, 42), Color.rgb(220, 20, 60),
                Color.rgb(0, 100, 0), Color.rgb(139, 0, 139), Color.rgb(30, 144, 255),
                Color.rgb(255, 0, 255)};

        float textSize = activity.getResources().getDimension(R.dimen.heading_text_size);

        for (int i = 0; i < 12; i++) {
            arrayLists.add(new ArrayList<PlanetData>());
        }
        for (int i = 0; i < planetsInRashi.length; i++) {
            int planetBhav = getBhavOfPlant(lagna, planetsInRashi[i]);
            planetBhav--;
            if (planetBhav >= 12) {
                planetBhav = 0;
            }
            Paint paint = new Paint();
            paint.setColor(color[i]);
            paint.setTextSize(textSize);
            arrayLists.get(planetBhav).add(new PlanetData(planetsName[i], paint));
        }
        ArrayList<PlanetData> innerList;
        float[] x_axis;
        float[] y_axis;
        int coordinateIndex;
        for (int i = 0; i < arrayLists.size(); i++) {
            innerList = arrayLists.get(i);
            coordinateIndex = 0;
            if (innerList.size() > 0) {
                x_axis = xArrayLists.get(i);
                y_axis = yArrayLists.get(i);
                for (int j = 0; j < innerList.size(); j++) {
                    if (coordinateIndex > 5) {
                        coordinateIndex = 0;
                    }
                    canvas.drawText(innerList.get(j).getPlanetName(), x_axis[coordinateIndex], y_axis[coordinateIndex], innerList.get(j).getPaint());
                    coordinateIndex++;
                }
            }
        }
    }


    private int getBhavOfPlant(int lagnaRashi, int plntRashi) {
        int bhavNumber = 0;
        bhavNumber = plntRashi - lagnaRashi;
        if (bhavNumber < 0) {
            bhavNumber += 12;
        }
        bhavNumber += 1;
        return bhavNumber;
    }

}