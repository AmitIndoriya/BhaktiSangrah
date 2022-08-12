package com.bhakti_sangrahalay.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class YearlyHoroscopeBean implements Serializable {
    @SerializedName("rashi")
    String rashi;
    @SerializedName("pub_date")
    String date;
    @SerializedName("title")
    String title;
    @SerializedName("description")
    Description description;

    public String getRashi() {
        return rashi;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public class Description {
        @SerializedName("love")
        String love;
        @SerializedName("general")
        String general;
        @SerializedName("career")
        String career;
        @SerializedName("advice")
        String advice;
        @SerializedName("family")
        String family;
        @SerializedName("finance")
        String finance;
        @SerializedName("health")
        String health;

        public String getLove() {
            return love;
        }

        public String getGeneral() {
            return general;
        }

        public String getCareer() {
            return career;
        }

        public String getAdvice() {
            return advice;
        }

        public String getFamily() {
            return family;
        }

        public String getFinance() {
            return finance;
        }

        public String getHealth() {
            return health;
        }


    }
}
