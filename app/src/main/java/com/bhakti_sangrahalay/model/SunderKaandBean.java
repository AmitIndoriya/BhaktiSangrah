package com.bhakti_sangrahalay.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;

public class SunderKaandBean implements Serializable {


    String heading;
    ArrayList<SunderKandArray> sunderKandArrayArrayList;


    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public ArrayList<SunderKandArray> getSunderKandArrayArrayList() {
        return sunderKandArrayArrayList;
    }

    public void setSunderKandArrayArrayList(ArrayList<SunderKandArray> sunderKandArrayArrayList) {
        this.sunderKandArrayArrayList = sunderKandArrayArrayList;
    }

    public class SunderKandArray implements Serializable {
        String title;
        Drawable drawable;
        ArrayList<Choupai> choupaiArrayList;
        ArrayList<Doha> dohaArrayList;
        ArrayList<Shortha> shorthaArrayList;
        ArrayList<Chhand> chhandArrayList;

        public String getTitle() {
            return title;
        }

        public Drawable getDrawable() {
            return drawable;
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ArrayList<Choupai> getChoupaiArrayList() {
            return choupaiArrayList;
        }

        public void setChoupaiArrayList(ArrayList<Choupai> choupaiArrayList) {
            this.choupaiArrayList = choupaiArrayList;
        }

        public ArrayList<Doha> getDohaArrayList() {
            return dohaArrayList;
        }

        public void setDohaArrayList(ArrayList<Doha> dohaArrayList) {
            this.dohaArrayList = dohaArrayList;
        }

        public ArrayList<Shortha> getShorthaArrayList() {
            return shorthaArrayList;
        }

        public void setShorthaArrayList(ArrayList<Shortha> shorthaArrayList) {
            this.shorthaArrayList = shorthaArrayList;
        }

        public ArrayList<Chhand> getChhandArrayList() {
            return chhandArrayList;
        }

        public void setChhandArrayList(ArrayList<Chhand> chhandArrayList) {
            this.chhandArrayList = chhandArrayList;
        }

        public class Choupai implements Serializable {
            String chopai;
            String meaning;

            public String getChopai() {
                return chopai;
            }

            public void setChopai(String chopai) {
                this.chopai = chopai;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }
        }

        public class Doha implements Serializable {
            String doha;
            String meaning;

            public String getDoha() {
                return doha;
            }

            public void setDoha(String doha) {
                this.doha = doha;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }
        }

        public class Shortha implements Serializable {
            String shortha;
            String meaning;

            public String getShortha() {
                return shortha;
            }

            public void setShortha(String shortha) {
                this.shortha = shortha;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }
        }

        public class Chhand implements Serializable {
            String chhand;
            String meaning;

            public String getChhand() {
                return chhand;
            }

            public void setChhand(String chhand) {
                this.chhand = chhand;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }
        }

    }


}

