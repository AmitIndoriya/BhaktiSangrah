package com.bhakti_sangrahalay.panchang.util;

public enum Language {
    // [Description("English")]
    en,

    // [Description("Hindi")]
    hi,

    // [Description("Tamil")]
    ta,

    // [Description("Telugu")]
    te,

    // [Description("Kannada")]
    ka,

    //[Description("Malayalam")]
    ml,

    // [Description("Gujarati")]
    gu,

    // [Description("Marathi")]
    mr,

    // [Description("Bengali")]
    bn;

    public static Language getLanguage(String lang) {
        switch (lang) {
            case "hi":
                return hi;
            case "ta":
                return ta;
            case "te":
                return te;
            case "ka":
                return ka;
            case "ml":
                return ml;
            case "gu":
                return gu;
            case "mr":
                return mr;
            case "bn":
                return bn;
            case "en":
            default:
                return en;
        }
    }
}
 
 