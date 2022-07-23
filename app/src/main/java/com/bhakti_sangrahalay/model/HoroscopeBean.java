package com.bhakti_sangrahalay.model;

import com.google.gson.annotations.SerializedName;

public class HoroscopeBean {
    @SerializedName("rashi")
    String rashi;
    @SerializedName("title")
    String title;
    @SerializedName("pub_date")
    String date;
    @SerializedName("desc")
    String desc;
    @SerializedName("lucky_number")
    String luckyNumber;
    @SerializedName("lucky_color")
    String luckyColor;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRashi() {
        return rashi;
    }

    public void setRashi(String rashi) {
        this.rashi = rashi;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLuckyNumber() {
        return luckyNumber;
    }

    public void setLuckyNumber(String luckyNumber) {
        this.luckyNumber = luckyNumber;
    }

    public String getLuckyColor() {
        return luckyColor;
    }

    public void setLuckyColor(String luckyColor) {
        this.luckyColor = luckyColor;
    }
}
