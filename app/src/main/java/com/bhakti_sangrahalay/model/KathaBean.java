package com.bhakti_sangrahalay.model;

import java.io.Serializable;

public class KathaBean implements Serializable {
    String title;
    String desc;

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
}
