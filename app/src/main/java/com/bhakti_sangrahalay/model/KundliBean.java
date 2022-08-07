package com.bhakti_sangrahalay.model;

import androidx.room.Entity;

import com.bhakti_sangrahalay.kundli.model.PrastharashtakvargaBean;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class KundliBean {
    @SerializedName("lagna")
    String lagna;
    @SerializedName("navmansh")
    String navmansh;
    @SerializedName("drekkana")
    String drekkana;
    @SerializedName("chaturthamansh")
    String chaturthamansh;
    @SerializedName("saptamamsha")
    String saptamamsha;
    @SerializedName("dashamamsha")
    String dashamamsha;
    @SerializedName("shashtiamsha")
    String shashtiamsha;
    @SerializedName("khavedamsha")
    String khavedamsha;
    @SerializedName("akshvedamsha")
    String akshvedamsha;
    @SerializedName("vimshamsha")
    String vimshamsha;
    @SerializedName("saptavimshamsha")
    String saptavimshamsha;
    @SerializedName("chaturvimshamsha")
    String chaturvimshamsha;
    @SerializedName("dwadashamamsha")
    String dwadashamamsha;
    @SerializedName("trimshamsha")
    String trimshamsha;
    @SerializedName("shodashamsha")
    String shodashamsha;
    @SerializedName("moon")
    String moon;
    @SerializedName("kpCusp")
    String kpCusp;
    @SerializedName("kpayan")
    String kpayan;
    @SerializedName("fortuna")
    String fortuna;
    @SerializedName("RPDayLord")
    String RPDayLord;
    @SerializedName("planetDegree")
    String planetDegree;
    @SerializedName("ayan")
    String ayan;

    @SerializedName("ashtakvargar1")
    String ashtakvargar1;
    @SerializedName("ashtakvargar2")
    String ashtakvargar2;
    @SerializedName("ashtakvargar3")
    String ashtakvargar3;
    @SerializedName("ashtakvargar4")
    String ashtakvargar4;
    @SerializedName("ashtakvargar5")
    String ashtakvargar5;
    @SerializedName("ashtakvargar6")
    String ashtakvargar6;
    @SerializedName("ashtakvargar7")
    String ashtakvargar7;
    @SerializedName("ashtakvargar8")
    String ashtakvargar8;
    @SerializedName("ashtakvargar9")
    String ashtakvargar9;
    @SerializedName("ashtakvargar10")
    String ashtakvargar10;
    @SerializedName("ashtakvargar11")
    String ashtakvargar11;
    @SerializedName("ashtakvargar12")
    String ashtakvargar12;
    @SerializedName("paksha")
    String paksha;
    @SerializedName("tithi")
    String tithi;
    @SerializedName("nakshatra")
    String nakshatra;
    @SerializedName("hinduWeekDay")
    String hinduWeekDay;
    @SerializedName("englishWeekDay")
    String englishWeekDay;
    @SerializedName("yoga")
    String yoga;
    @SerializedName("karan")
    String karan;
    @SerializedName("sunRiseTime")
    String sunRiseTime;
    @SerializedName("sunSetTime")
    String sunSetTime;
    @SerializedName("prastharashtakvarga")
    ArrayList<PrastharashtakvargaBean> prastharashtakvarga;
    @SerializedName("paya")
    String paya;
    @SerializedName("varna")
    String varna;
    @SerializedName("yoni")
    String yoni;
    @SerializedName("gana")
    String gana;
    @SerializedName("vasya")
    String vasya;
    @SerializedName("nadi")
    String nadi;
    @SerializedName("balanceOfDasha")
    String balanceOfDasha;
    @SerializedName("lagnaA")
    String lagnaA;
    @SerializedName("lagnaLord")
    String lagnaLord;
    @SerializedName("rasi")
    String rasi;
    @SerializedName("rasiLord")
    String rasiLord;
    @SerializedName("nakshatraPada")
    String nakshatraPada;
    @SerializedName("nakshatraLord")
    String nakshatraLord;
    @SerializedName("julianDay")
    String julianDay;
    @SerializedName("sunSignIndian")
    String sunSignIndian;
    @SerializedName("sunSignWestern")
    String sunSignWestern;
    @SerializedName("ayanamsa")
    String ayanamsa;
    @SerializedName("ayanamsaName")
    String ayanamsaName;
    @SerializedName("obliquity")
    String obliquity;
    @SerializedName("sideralTime")
    String sideralTime;
    @SerializedName("planetsignification1")
    String planetSignification1;
    @SerializedName("planetsignification2")
    String planetSignification2;
    @SerializedName("planetsignification3")
    String planetSignification3;
    @SerializedName("planetsignification4")
    String planetSignification4;
    @SerializedName("planetsignification5")
    String planetSignification5;
    @SerializedName("planetsignification6")
    String planetSignification6;
    @SerializedName("planetsignification7")
    String planetSignification7;
    @SerializedName("planetsignification8")
    String planetSignification8;
    @SerializedName("planetsignification9")
    String planetSignification9;

    public String getLagna() {
        return lagna;
    }

    public String getNavmansh() {
        return navmansh;
    }

    public String getDrekkana() {
        return drekkana;
    }


    public String getChaturthamansh() {
        return chaturthamansh;
    }


    public String getSaptamamsha() {
        return saptamamsha;
    }


    public String getDashamamsha() {
        return dashamamsha;
    }


    public String getShashtiamsha() {
        return shashtiamsha;
    }


    public String getKhavedamsha() {
        return khavedamsha;
    }


    public String getAkshvedamsha() {
        return akshvedamsha;
    }


    public String getVimshamsha() {
        return vimshamsha;
    }


    public String getSaptavimshamsha() {
        return saptavimshamsha;
    }


    public String getChaturvimshamsha() {
        return chaturvimshamsha;
    }


    public String getDwadashamamsha() {
        return dwadashamamsha;
    }


    public String getTrimshamsha() {
        return trimshamsha;
    }


    public String getShodashamsha() {
        return shodashamsha;
    }


    public String getMoon() {
        return moon;
    }

    public String getKpCusp() {
        return kpCusp;
    }

    public String getKpayan() {
        return kpayan;
    }

    public String getFortuna() {
        return fortuna;
    }

    public String getRPDayLord() {
        return RPDayLord;
    }

    public String getPlanetDegree() {
        return planetDegree;
    }

    public String getAyan() {
        return ayan;
    }

    public void setLagna(String lagna) {
        this.lagna = lagna;
    }

    public void setNavmansh(String navmansh) {
        this.navmansh = navmansh;
    }

    public void setDrekkana(String drekkana) {
        this.drekkana = drekkana;
    }

    public void setChaturthamansh(String chaturthamansh) {
        this.chaturthamansh = chaturthamansh;
    }

    public void setSaptamamsha(String saptamamsha) {
        this.saptamamsha = saptamamsha;
    }

    public void setDashamamsha(String dashamamsha) {
        this.dashamamsha = dashamamsha;
    }

    public void setShashtiamsha(String shashtiamsha) {
        this.shashtiamsha = shashtiamsha;
    }

    public void setKhavedamsha(String khavedamsha) {
        this.khavedamsha = khavedamsha;
    }

    public void setAkshvedamsha(String akshvedamsha) {
        this.akshvedamsha = akshvedamsha;
    }

    public void setVimshamsha(String vimshamsha) {
        this.vimshamsha = vimshamsha;
    }

    public void setSaptavimshamsha(String saptavimshamsha) {
        this.saptavimshamsha = saptavimshamsha;
    }

    public void setChaturvimshamsha(String chaturvimshamsha) {
        this.chaturvimshamsha = chaturvimshamsha;
    }

    public void setDwadashamamsha(String dwadashamamsha) {
        this.dwadashamamsha = dwadashamamsha;
    }

    public void setTrimshamsha(String trimshamsha) {
        this.trimshamsha = trimshamsha;
    }

    public void setShodashamsha(String shodashamsha) {
        this.shodashamsha = shodashamsha;
    }

    public void setMoon(String moon) {
        this.moon = moon;
    }

    public void setKpCusp(String kpCusp) {
        this.kpCusp = kpCusp;
    }

    public void setKpayan(String kpayan) {
        this.kpayan = kpayan;
    }

    public void setFortuna(String fortuna) {
        this.fortuna = fortuna;
    }

    public void setRPDayLord(String RPDayLord) {
        this.RPDayLord = RPDayLord;
    }

    public void setPlanetDegree(String planetDegree) {
        this.planetDegree = planetDegree;
    }

    public void setAyan(String ayan) {
        this.ayan = ayan;
    }

    public String getAshtakvargar1() {
        return ashtakvargar1;
    }

    public void setAshtakvargar1(String ashtakvargar1) {
        this.ashtakvargar1 = ashtakvargar1;
    }

    public String getAshtakvargar2() {
        return ashtakvargar2;
    }

    public void setAshtakvargar2(String ashtakvargar2) {
        this.ashtakvargar2 = ashtakvargar2;
    }

    public String getAshtakvargar3() {
        return ashtakvargar3;
    }

    public void setAshtakvargar3(String ashtakvargar3) {
        this.ashtakvargar3 = ashtakvargar3;
    }

    public String getAshtakvargar4() {
        return ashtakvargar4;
    }

    public void setAshtakvargar4(String ashtakvargar4) {
        this.ashtakvargar4 = ashtakvargar4;
    }

    public String getAshtakvargar5() {
        return ashtakvargar5;
    }

    public void setAshtakvargar5(String ashtakvargar5) {
        this.ashtakvargar5 = ashtakvargar5;
    }

    public String getAshtakvargar6() {
        return ashtakvargar6;
    }

    public void setAshtakvargar6(String ashtakvargar6) {
        this.ashtakvargar6 = ashtakvargar6;
    }

    public String getAshtakvargar7() {
        return ashtakvargar7;
    }

    public void setAshtakvargar7(String ashtakvargar7) {
        this.ashtakvargar7 = ashtakvargar7;
    }

    public String getAshtakvargar8() {
        return ashtakvargar8;
    }

    public void setAshtakvargar8(String ashtakvargar8) {
        this.ashtakvargar8 = ashtakvargar8;
    }

    public String getAshtakvargar9() {
        return ashtakvargar9;
    }

    public void setAshtakvargar9(String ashtakvargar9) {
        this.ashtakvargar9 = ashtakvargar9;
    }

    public String getAshtakvargar10() {
        return ashtakvargar10;
    }

    public void setAshtakvargar10(String ashtakvargar10) {
        this.ashtakvargar10 = ashtakvargar10;
    }

    public String getAshtakvargar11() {
        return ashtakvargar11;
    }

    public void setAshtakvargar11(String ashtakvargar11) {
        this.ashtakvargar11 = ashtakvargar11;
    }

    public String getAshtakvargar12() {
        return ashtakvargar12;
    }

    public void setAshtakvargar12(String ashtakvargar12) {
        this.ashtakvargar12 = ashtakvargar12;
    }

    public String getPaksha() {
        return paksha;
    }

    public void setPaksha(String paksha) {
        this.paksha = paksha;
    }

    public String getNakshatra() {
        return nakshatra;
    }

    public void setNakshatra(String nakshatra) {
        this.nakshatra = nakshatra;
    }

    public String getHinduWeekDay() {
        return hinduWeekDay;
    }

    public void setHinduWeekDay(String hinduWeekDay) {
        this.hinduWeekDay = hinduWeekDay;
    }

    public String getEnglishWeekDay() {
        return englishWeekDay;
    }

    public void setEnglishWeekDay(String englishWeekDay) {
        this.englishWeekDay = englishWeekDay;
    }

    public String getYoga() {
        return yoga;
    }

    public void setYoga(String yoga) {
        this.yoga = yoga;
    }

    public String getKaran() {
        return karan;
    }

    public void setKaran(String karan) {
        this.karan = karan;
    }

    public String getSunRiseTime() {
        return sunRiseTime;
    }

    public void setSunRiseTime(String sunRiseTime) {
        this.sunRiseTime = sunRiseTime;
    }

    public String getSunSetTime() {
        return sunSetTime;
    }

    public void setSunSetTime(String sunSetTime) {
        this.sunSetTime = sunSetTime;
    }

    public String getTithi() {
        return tithi;
    }

    public void setTithi(String tithi) {
        this.tithi = tithi;
    }

    public ArrayList<PrastharashtakvargaBean> getPrastharashtakvarga() {
        return prastharashtakvarga;
    }

    public void setPrastharashtakvarga(ArrayList<PrastharashtakvargaBean> prastharashtakvarga) {
        this.prastharashtakvarga = prastharashtakvarga;
    }

    public String getPaya() {
        return paya;
    }

    public void setPaya(String paya) {
        this.paya = paya;
    }

    public String getVarna() {
        return varna;
    }

    public void setVarna(String varna) {
        this.varna = varna;
    }

    public String getYoni() {
        return yoni;
    }

    public void setYoni(String yoni) {
        this.yoni = yoni;
    }

    public String getGana() {
        return gana;
    }

    public void setGana(String gana) {
        this.gana = gana;
    }

    public String getVasya() {
        return vasya;
    }

    public void setVasya(String vasya) {
        this.vasya = vasya;
    }

    public String getNadi() {
        return nadi;
    }

    public void setNadi(String nadi) {
        this.nadi = nadi;
    }

    public String getBalanceOfDasha() {
        return balanceOfDasha;
    }

    public void setBalanceOfDasha(String balanceOfDasha) {
        this.balanceOfDasha = balanceOfDasha;
    }

    public String getLagnaA() {
        return lagnaA;
    }

    public void setLagnaA(String lagnaA) {
        this.lagnaA = lagnaA;
    }

    public String getLagnaLord() {
        return lagnaLord;
    }

    public void setLagnaLord(String lagnaLord) {
        this.lagnaLord = lagnaLord;
    }

    public String getRasi() {
        return rasi;
    }

    public void setRasi(String rasi) {
        this.rasi = rasi;
    }

    public String getRasiLord() {
        return rasiLord;
    }

    public void setRasiLord(String rasiLord) {
        this.rasiLord = rasiLord;
    }

    public String getNakshatraPada() {
        return nakshatraPada;
    }

    public void setNakshatraPada(String nakshatraPada) {
        this.nakshatraPada = nakshatraPada;
    }

    public String getNakshatraLord() {
        return nakshatraLord;
    }

    public void setNakshatraLord(String nakshatraLord) {
        this.nakshatraLord = nakshatraLord;
    }

    public String getJulianDay() {
        return julianDay;
    }

    public void setJulianDay(String julianDay) {
        this.julianDay = julianDay;
    }

    public String getSunSignIndian() {
        return sunSignIndian;
    }

    public void setSunSignIndian(String sunSignIndian) {
        this.sunSignIndian = sunSignIndian;
    }

    public String getSunSignWestern() {
        return sunSignWestern;
    }

    public void setSunSignWestern(String sunSignWestern) {
        this.sunSignWestern = sunSignWestern;
    }

    public String getAyanamsa() {
        return ayanamsa;
    }

    public void setAyanamsa(String ayanamsa) {
        this.ayanamsa = ayanamsa;
    }

    public String getAyanamsaName() {
        return ayanamsaName;
    }

    public void setAyanamsaName(String ayanamsaName) {
        this.ayanamsaName = ayanamsaName;
    }

    public String getObliquity() {
        return obliquity;
    }

    public void setObliquity(String obliquity) {
        this.obliquity = obliquity;
    }

    public String getSideralTime() {
        return sideralTime;
    }

    public void setSideralTime(String sideralTime) {
        this.sideralTime = sideralTime;
    }

    public String getPlanetSignification1() {
        return planetSignification1;
    }

    public void setPlanetSignification1(String planetSignification1) {
        this.planetSignification1 = planetSignification1;
    }

    public String getPlanetSignification2() {
        return planetSignification2;
    }

    public void setPlanetSignification2(String planetSignification2) {
        this.planetSignification2 = planetSignification2;
    }

    public String getPlanetSignification3() {
        return planetSignification3;
    }

    public void setPlanetSignification3(String planetSignification3) {
        this.planetSignification3 = planetSignification3;
    }

    public String getPlanetSignification4() {
        return planetSignification4;
    }

    public void setPlanetSignification4(String planetSignification4) {
        this.planetSignification4 = planetSignification4;
    }

    public String getPlanetSignification5() {
        return planetSignification5;
    }

    public void setPlanetSignification5(String planetSignification5) {
        this.planetSignification5 = planetSignification5;
    }

    public String getPlanetSignification6() {
        return planetSignification6;
    }

    public void setPlanetSignification6(String planetSignification6) {
        this.planetSignification6 = planetSignification6;
    }

    public String getPlanetSignification7() {
        return planetSignification7;
    }

    public void setPlanetSignification7(String planetSignification7) {
        this.planetSignification7 = planetSignification7;
    }

    public String getPlanetSignification8() {
        return planetSignification8;
    }

    public void setPlanetSignification8(String planetSignification8) {
        this.planetSignification8 = planetSignification8;
    }

    public String getPlanetSignification9() {
        return planetSignification9;
    }

    public void setPlanetSignification9(String planetSignification9) {
        this.planetSignification9 = planetSignification9;
    }
}
