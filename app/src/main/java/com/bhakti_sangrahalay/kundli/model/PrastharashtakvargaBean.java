package com.bhakti_sangrahalay.kundli.model;

import com.google.gson.annotations.SerializedName;

public class PrastharashtakvargaBean {
    @SerializedName("SU")
    String su;
    @SerializedName("MO")
    String mo;
    @SerializedName("MA")
    String ma;
    @SerializedName("ME")
    String me;
    @SerializedName("JU")
    String ju;
    @SerializedName("VE")
    String ve;
    @SerializedName("SA")
    String sa;
    @SerializedName("AS")
    String asc;

    public String getSu() {
        return su;
    }

    public void setSu(String su) {
        this.su = su;
    }

    public String getMo() {
        return mo;
    }

    public void setMo(String mo) {
        this.mo = mo;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public String getJu() {
        return ju;
    }

    public void setJu(String ju) {
        this.ju = ju;
    }

    public String getVe() {
        return ve;
    }

    public void setVe(String ve) {
        this.ve = ve;
    }

    public String getSa() {
        return sa;
    }

    public void setSa(String sa) {
        this.sa = sa;
    }

    public String getAsc() {
        return asc;
    }

    public void setAsc(String as) {
        this.asc = as;
    }
}