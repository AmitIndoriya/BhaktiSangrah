package com.bhakti_sangrahalay.panchang.model;

import java.io.Serializable;

public class PanchangBean implements Serializable {
    private String title, genre, year;

    public PanchangBean() {
    }

    public PanchangBean(String title, String genre, String year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}