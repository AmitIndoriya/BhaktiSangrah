package com.bhakti_sangrahalay.panchang.util;


public class Constants implements IConstants {
    @Override
    public String getMasas(int index) {
        // TODO Auto-generated method stub
        return masas[index];
    }

    @Override
    public String getVaras(int index) {
        // TODO Auto-generated method stub
        return varas[index];
    }

    @Override
    public String getVarasSwami(int index) {
        // TODO Auto-generated method stub
        return vaars_swami[index];
    }

    @Override
    public String getSamvats(int index) {
        // TODO Auto-generated method stub
        return samvats[index];
    }

    @Override
    public String getRitus(int index) {
        // TODO Auto-generated method stub
        return ritus[index];
    }

    @Override
    public String getKaranas(int index) {
        // TODO Auto-generated method stub
        return karanas[index];
    }

    @Override
    public String getPakshas(int index) {
        // TODO Auto-generated method stub
        return pakshas[index];
    }

    @Override
    public String getAyanas(int index) {
        // TODO Auto-generated method stub
        return ayanas[index];
    }

    @Override
    public String getDishas(int index) {
        // TODO Auto-generated method stub
        return dishas[index];
    }

    @Override
    public String getNakshatra(int index) {
        // TODO Auto-generated method stub
        return nakshatra[index];
    }

    @Override
    public String getYoga(int index) {
        // TODO Auto-generated method stub
        return yoga[index];
    }

    @Override
    public String getMoonSign(int index) {
        // TODO Auto-generated method stub
        return rashi[index];
    }

    @Override
    public String getTithi(int index) {
        // TODO Auto-generated method stub
        return tithi[index];
    }

    @Override
    public String getChandraBala(int index) {
        // TODO Auto-generated method stub
        return rashi[index];
    }

    @Override
    public String getTaraBala(int index) {
        // TODO Auto-generated method stub
        return nakshatra[index];
    }

    @Override
    public String[] getChandraBala() {
        // TODO Auto-generated method stub
        return rashi;
    }

    @Override
    public String[] getTaraBala() {
        // TODO Auto-generated method stub
        return nakshatra;
    }

    @Override
    public String getMonthName(int index) {
        // TODO Auto-generated method stub
        return monthname[index];
    }

    @Override
    public String getMonthNameSort(int index) {
        // TODO Auto-generated method stub
        return monthnameSort[index];
    }

    @Override
    public String getDayName(int index) {
        // TODO Auto-generated method stub
        return dayname[index];
    }

    @Override
    public String getChoghadiaDayName(int index) {
        return choghadiaDayName[index];
    }

    @Override
    public String getChoghadiaNightName(int index) {
        return choghadiaNightName[index];
    }

    @Override
    public String getExString(int index) {
        // TODO Auto-generated method stub
        return exstring[index];
    }

    @Override
    public String getLagna(int index) {
        return rashi2[index];
    }

    @Override
    public String getLagnaNature(int index) {
        return rashiNature[index];
    }

    @Override
    public String getRashi(int index) {
        // TODO Auto-generated method stub
        return rashi2[index];
    }

    public final String[] masas = {"चैत्र", "वैसाख", "ज्येष्ठा", "आषाढ़", "श्रवण", "भाद्रपदा", "आश्विन", "कार्तिका", "मार्गशीर्ष", "पौशा", "मघा", "फाल्गुन"};

    // base 0
    public final String[] varas = {"Ravivara", "Somavara", "Mangalavara", "Buddhavara", "Guruvara", "Shukravara", "Shanivara"};

    public final String[] vaars_swami = {"NA", "sun", "moon", "mars", "mercury", "jupiter", "venus", "saturn"};

    // base 1
    //our constant samvats array index 47(Pramādeecha) name is Pramāthi in drikpanchang
    public final String[] samvats = {"Na", "Prabhava", "Vibhava", "Shukla", "Pramoda", "Prajothpatti", "Āngirasa", "Shrīmukha", "Bhāva",
            "Yuva", "Dhāta", "Īshvara", "Bahudhānya", "Pramāthi", "Vikrama", "Vrusha", "Chitrabhānu",
            "Svabhānu", "Tārana", "Pārthiva", "Vyaya", "Sarvajit", "Sarvadhāri", "Virodhi", "Vikruti",
            "Khara", "Nandana", "Vijaya", "Jaya", "Manmatha", "Durmukhi", "Hevilambi", "Vilambi",
            "Vikāri", "Shārvari", "Plava", "Shubhakruth", "Shobhakruth", "Krodhi", "Vishvāvasu", "Parābhava",
            "Plavanga", "Kīlaka", "Saumya", "Sādhārana", "Virodhikruth", "Paridhāvi", "Pramādeecha", "Ānanda", "Rākshasa", "Nala",
            "Pingala", "Kālayukthi", "Siddhārthi", "Raudra", "Durmati", "Dundubhi", "Rudhirodgāri", "Raktākshi", "Krodhana", "Akshaya"};

    //0 base
    public final String[] ritus = {"Vasanta", "Grishma", "Varsha", "Sharad", "Hemant", "Shishir"};

    public final String[] karanas = {"NA", "Kintudhhana", "Bhav", "Baalav", "Kolav", "Tetil", "Gar", "Vanij", "Vishti", "Bhav", "Baalav", "Kolav", "Tetil", "Gar", "Vanij", "Vishti", "Bhav", "Baalav", "Kolav", "Tetil", "Gar", "Vanij", "Vishti", "Bhav", "Baalav", "Kolav", "Tetil", "Gar", "Vanij", "Vishti", "Bhav", "Baalav", "Kolav", "Tetil", "Gar", "Vanij", "Vishti", "Bhav", "Baalav", "Kolav", "Tetil", "Gar", "Vanij", "Vishti", "Bhav", "Baalav", "Kolav", "Tetil", "Gar", "Vanij", "Vishti", "Bhav", "Baalav", "Kolav", "Tetil", "Gar", "Vanij", "Vishti", "Sakuni", "Chatushpada", "Naaga"};

    public final String[] pakshas = {"शुक्ल", "कृष्ण"};

    public final String[] ayanas = {"Uttarayana", "Dakshinayana"};

    public final String[] dishas = {"East", "West", "North", "South"};

    public final String[] nakshatra = {"NA", "अश्विनी", "भरनी", "कृतिका", "रोहिणी", "मृगशीर्ष", "आर्द्र", "पुनर्वसु", "पुष्य", "अश्लेषा", "मघा", "पूर्व फाल्गुनी", "उत्तरा फाल्गुनी", "हस्ता", "चित्र", "स्वाति", "विशाखा", "अनुराधा", "ज्येष्ठा", "मूला", "पूर्व आषाढ़ा", "उत्तरा आषाढ़ा", "श्रवण", "धनिष्ठा", "सतभिषा", "पूर्व भाद्रपदा", "उत्तरा भाद्रपदा", "रेवती"};

    public final String[] yoga = {"NA", "Vishkambha", "Priti", "Ayushman", "Saubhagya", "Sobhana", "Atiganda", "Sukarma", "Dhriti", "Soola", "Ganda", "Vriddha", "Dhruva",
            "Vyagatha", "Harshana", "Vajra", "Siddhi", "Vyatipata", "Variyan", "Parigha", "Siva", "Siddha", "Sadhya", "Subha", "Shukla", "Brahma", "Indra", "Vaidhriti"};

    public final String[] tithi = {"NA", "प्रथम", "द्वितीय", "तृतीया", "चतुर्थी", "पंचमी", "षष्टी", "सप्तमी", "अष्टमी", "नवमी", "दशमी", "एकादशी", "द्वादशी", "त्रयोदशी", "चतुर्दशी", "पूर्णिमा", "प्रथम", "द्वितीय", "तृतीया", "चतुर्थी ", "पंचमी", "षष्टी", "सप्तमी", "अष्टमी", "नवमी", "दशमी", "एकादशी", "द्वादशी", "त्रयोदशी", "चतुर्दशी", "अमावस्या"};
    public final String[] rashi = {"NA", "Mesha", "Vrishabha", "Mithuna", "Karka", "Simha", "Kanya", "Tula", "Vrishchika", "Dhanu", "Makara", "Kumbha", "Meena"};

    public final String[] rashi2 = {"NA", "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};

    public final String[] rashiNature = {"NA", "Movable", "Fixed", "Dual", "Movable", "Fixed", "Dual", "Movable", "Fixed", "Dual", "Movable", "Fixed", "Dual"};

    public final String[] monthname = {"NA", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public final String[] monthnameSort = {"NA", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public final String[] dayname = {"NA", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public final String[] choghadiaDayName = {"उद्वेग", "चल", "लाभ", "अमृत", "काल", "शुभ", "रोग"};

    public final String[] choghadiaNightName = {"शुभ", "अमृत", "चल", "रोग", "काल", "लाभ", "उद्वेग"};

    public final String[] exstring = {"None", "No Moon Rise", "No Moon Set", "(Adhik)"};

}