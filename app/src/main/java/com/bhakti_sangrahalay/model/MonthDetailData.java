package com.bhakti_sangrahalay.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.Date;

public class MonthDetailData  implements Serializable {
    Date date;
    int thithi;
    Drawable moonPhaseImg;
    String festName;
    //Event event;

  /*  public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }*/

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFestName() {
        return festName;
    }

    public void setFestName(String festName) {
        this.festName = festName;
    }

    public int getThithi() {
        return thithi;
    }

    public void setThithi(int thithi) {
        this.thithi = thithi;
    }

    public Drawable getMoonPhaseImg() {
        return moonPhaseImg;
    }

    public void setMoonPhaseImg(Drawable moonPhaseImg) {
        this.moonPhaseImg = moonPhaseImg;
    }
}
