package com.bhakti_sangrahalay.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeeklyHoroscopeBean implements Serializable {
    @SerializedName("rashi")
    String rashi;
    @SerializedName("title")
    String title;
    @SerializedName("desc")
    String desc;
    @SerializedName("pub_date")
    String date;

    public String getRashi() {
        return rashi;
    }

    public void setRashi(String rashi) {
        this.rashi = rashi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
