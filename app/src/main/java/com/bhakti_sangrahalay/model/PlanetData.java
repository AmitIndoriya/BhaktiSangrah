package com.bhakti_sangrahalay.model;

import android.graphics.Paint;

import java.io.Serializable;

public class PlanetData implements Serializable {
    String planetName;
    Paint paint;

    public PlanetData(String planetName, Paint paint) {
        this.planetName = planetName;
        this.paint = paint;
    }

    public String getPlanetName() {
        return planetName;
    }

    public Paint getPaint() {
        return paint;
    }
}
