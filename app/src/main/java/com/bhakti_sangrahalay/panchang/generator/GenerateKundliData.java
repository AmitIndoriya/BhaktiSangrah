package com.bhakti_sangrahalay.panchang.generator;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.bhakti_sangrahalay.kundli.model.BirthDetailBean;
import com.indoriya.horolib.dhoro.DesktopHoro;
import com.indoriya.horolib.util.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class GenerateKundliData {
    public static String getPlanets(AssetManager assetManager, BirthDetailBean birthDetailBean) {
        JSONArray jsonArray = new JSONArray();
        try {
            // AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("Properties/ConstantsEnglish.properties");
            Constants ObjConst = new Constants();
            ObjConst.setInputStream(inputStream);

            String name = birthDetailBean.getName();
            String sex = birthDetailBean.getSex();
            String day = birthDetailBean.getDateTimeBean().getDay();
            String month = birthDetailBean.getDateTimeBean().getMonth();
            String year = birthDetailBean.getDateTimeBean().getYear();
            String hrs = birthDetailBean.getDateTimeBean().getHrs();
            String min = birthDetailBean.getDateTimeBean().getMin();
            String sec = birthDetailBean.getDateTimeBean().getSec();
            String dst = birthDetailBean.getDst();
            String place = birthDetailBean.getPlaceBean().getPlace();
            String LongDeg = birthDetailBean.getPlaceBean().getLongDeg();
            String LongMin = birthDetailBean.getPlaceBean().getLongMin();
            String LongEW = birthDetailBean.getPlaceBean().getLongEW();
            String LatDeg = birthDetailBean.getPlaceBean().getLatDeg();
            String LatMin = birthDetailBean.getPlaceBean().getLatMin();
            String LatNS = birthDetailBean.getPlaceBean().getLatNS();
            String timeZone = birthDetailBean.getPlaceBean().getTimeZone();
            String ayanamsa = birthDetailBean.getAyanamsa();
            String charting = birthDetailBean.getCharting();
            String kphn = birthDetailBean.getKphn();
            String button1 = birthDetailBean.getButton1();
            String languageCode = birthDetailBean.getLanguageCode();


            DesktopHoro args1 = new DesktopHoro();

            args1.setCompanyName("CSL SOFTWARE LTD., G-4A, Green park Extn., New Delhi-110 016");
            args1.setCompanyAddLine1(
                    "Phone:011-6867329, 9811356741, E-Mail: info@astrocamp.com http://www.astrocamp.com");
            args1.setName(name);
            args1.setPlace(place);
            args1.setTimeZone(timeZone);
            args1.setMaleFemale(sex);
            args1.setSecondOfBirth(sec);
            args1.setMinuteOfBirth(min);
            args1.setHourOfBirth(hrs);
            args1.setDayOfBirth(day);
            args1.setMonthOfBirth(month);
            args1.setYearOfBirth(year);
            args1.setDegreeOfLattitude(LatDeg);
            args1.setMinuteOfLattitude(LatMin);
            args1.setSecondOfLattitude("00");
            args1.setDegreeOfLongitude(LongDeg);
            args1.setMinuteOfLongitude(LongMin);
            args1.setSecondOfLongitude("00");
            args1.setEastWest(LongEW);
            args1.setNorthSouth(LatNS);
            args1.setLanguageCode(languageCode);
            args1.setDST(dst);
            args1.setAyanamsaType(0);
            args1.initialize();
            inputStream.close();
            int[] planetArray = args1.getPositionForShodasvarg(0);
            int[] drekkanaArray = args1.getPositionForShodasvarg(2);
            int[] chaturthamanshArray = args1.getPositionForShodasvarg(3);
            int[] saptamamshaArray = args1.getPositionForShodasvarg(4);
            int[] navmanshArray = args1.getPositionForShodasvarg(5);
            int[] dashamamshaArray = args1.getPositionForShodasvarg(6);
            int[] dwadashamamshaArray = args1.getPositionForShodasvarg(7);
            int[] shodashamshaArray = args1.getPositionForShodasvarg(8);
            int[] vimshamshaArray = args1.getPositionForShodasvarg(9);
            int[] saptavimshamshaArray = args1.getPositionForShodasvarg(10);
            int[] chaturvimshamshaArray = args1.getPositionForShodasvarg(11);
            int[] trimshamshaArray = args1.getPositionForShodasvarg(12);
            int[] khavedamshaArray = args1.getPositionForShodasvarg(13);
            int[] akshvedamshaArray = args1.getPositionForShodasvarg(14);
            int[] shashtiamshaArray = args1.getPositionForShodasvarg(15);
            double[] planetDegreeArray = {args1.getAsc(), args1.getSun(), args1.getMoon(), args1.getMars(),
                    args1.getMercury(), args1.getJupitor(), args1.getVenus(), args1.getSaturn(), args1.getRahu(),
                    args1.getKetu(), args1.getUranus(), args1.getNeptune(), args1.getPluto()};
            double[] kpCuspDegreeArray = {args1.getKPCuspLongitude(1), args1.getKPCuspLongitude(2),
                    args1.getKPCuspLongitude(3), args1.getKPCuspLongitude(4), args1.getKPCuspLongitude(5), args1.getKPCuspLongitude(6), args1.getKPCuspLongitude(7),
                    args1.getKPCuspLongitude(8), args1.getKPCuspLongitude(9), args1.getKPCuspLongitude(10), args1.getKPCuspLongitude(11), args1.getKPCuspLongitude(12)};

            int[] kpPlanetSignificationArray1 = args1.getKPPlanetSignification(1);
            int[] kpPlanetSignificationArray2 = args1.getKPPlanetSignification(2);
            int[] kpPlanetSignificationArray3 = args1.getKPPlanetSignification(3);
            int[] kpPlanetSignificationArray4 = args1.getKPPlanetSignification(4);
            int[] kpPlanetSignificationArray5 = args1.getKPPlanetSignification(5);
            int[] kpPlanetSignificationArray6 = args1.getKPPlanetSignification(6);
            int[] kpPlanetSignificationArray7 = args1.getKPPlanetSignification(7);
            int[] kpPlanetSignificationArray8 = args1.getKPPlanetSignification(8);
            int[] kpPlanetSignificationArray9 = args1.getKPPlanetSignification(9);
            args1.getTotalAshtakVargaValue();
            StringBuilder lagnaPlanetArray = new StringBuilder();
            StringBuilder navmanshPlanetArray = new StringBuilder();
            StringBuilder drekkanaStr = new StringBuilder();
            StringBuilder chaturthamanshStr = new StringBuilder();
            StringBuilder saptamamshaStr = new StringBuilder();
            StringBuilder dashamamshaStr = new StringBuilder();
            StringBuilder dwadashamamshaStr = new StringBuilder();
            StringBuilder shodashamshaStr = new StringBuilder();
            StringBuilder vimshamshaStr = new StringBuilder();
            StringBuilder saptavimshamshaStr = new StringBuilder();
            StringBuilder chaturvimshamshaStr = new StringBuilder();
            StringBuilder trimshamshaStr = new StringBuilder();
            StringBuilder khavedamshaStr = new StringBuilder();
            StringBuilder akshvedamshaStr = new StringBuilder();
            StringBuilder shashtiamshaStr = new StringBuilder();
            StringBuilder planetDegreeStr = new StringBuilder();
            StringBuilder kpCuspDegreeStr = new StringBuilder();
            StringBuilder kpPlanetSignificationStr1 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr2 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr3 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr4 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr5 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr6 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr7 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr8 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr9 = new StringBuilder();
            String[] ashtakvargaRashi = new String[12];

            for (int i = 0; i < planetArray.length; i++) {
                if (i == planetArray.length - 1) {
                    lagnaPlanetArray.append(planetArray[i]);
                    navmanshPlanetArray.append(navmanshArray[i]);
                    drekkanaStr.append(drekkanaArray[i]);
                    chaturthamanshStr.append(chaturthamanshArray[i]);
                    saptamamshaStr.append(saptamamshaArray[i]);
                    dashamamshaStr.append(dashamamshaArray[i]);

                    dwadashamamshaStr.append(dwadashamamshaArray[i]);
                    shodashamshaStr.append(shodashamshaArray[i]);
                    vimshamshaStr.append(vimshamshaArray[i]);
                    saptavimshamshaStr.append(saptavimshamshaArray[i]);
                    chaturvimshamshaStr.append(chaturvimshamshaArray[i]);
                    trimshamshaStr.append(trimshamshaArray[i]);
                    khavedamshaStr.append(khavedamshaArray[i]);
                    akshvedamshaStr.append(akshvedamshaArray[i]);
                    shashtiamshaStr.append(shashtiamshaArray[i]);
                    planetDegreeStr.append(planetDegreeArray[i]);

                } else {
                    lagnaPlanetArray.append(planetArray[i]).append(",");
                    navmanshPlanetArray.append(navmanshArray[i]).append(",");
                    drekkanaStr.append(drekkanaArray[i]).append(",");
                    chaturthamanshStr.append(chaturthamanshArray[i]).append(",");
                    saptamamshaStr.append(saptamamshaArray[i]).append(",");
                    dashamamshaStr.append(dashamamshaArray[i]).append(",");
                    dwadashamamshaStr.append(dwadashamamshaArray[i]).append(",");
                    shodashamshaStr.append(shodashamshaArray[i]).append(",");
                    vimshamshaStr.append(vimshamshaArray[i]).append(",");
                    saptavimshamshaStr.append(saptavimshamshaArray[i]).append(",");
                    chaturvimshamshaStr.append(chaturvimshamshaArray[i]).append(",");
                    trimshamshaStr.append(trimshamshaArray[i]).append(",");
                    khavedamshaStr.append(khavedamshaArray[i]).append(",");
                    akshvedamshaStr.append(akshvedamshaArray[i]).append(",");
                    shashtiamshaStr.append(shashtiamshaArray[i]).append(",");
                    planetDegreeStr.append(planetDegreeArray[i]).append(",");
                }
            }
            for (int i = 0; i < kpCuspDegreeArray.length; i++) {
                if (i == kpCuspDegreeArray.length - 1) {
                    kpCuspDegreeStr.append(kpCuspDegreeArray[i]);
                } else {
                    kpCuspDegreeStr.append(kpCuspDegreeArray[i]).append(",");
                }
            }
            for (int i = 0; i < kpPlanetSignificationArray1.length; i++) {
                if (i == kpPlanetSignificationArray1.length - 1) {
                    kpPlanetSignificationStr1.append(kpPlanetSignificationArray1[i]);
                    kpPlanetSignificationStr2.append(kpPlanetSignificationArray2[i]);
                    kpPlanetSignificationStr3.append(kpPlanetSignificationArray3[i]);
                    kpPlanetSignificationStr4.append(kpPlanetSignificationArray4[i]);
                    kpPlanetSignificationStr5.append(kpPlanetSignificationArray5[i]);
                    kpPlanetSignificationStr6.append(kpPlanetSignificationArray6[i]);
                    kpPlanetSignificationStr7.append(kpPlanetSignificationArray7[i]);
                    kpPlanetSignificationStr8.append(kpPlanetSignificationArray8[i]);
                    kpPlanetSignificationStr9.append(kpPlanetSignificationArray9[i]);
                } else {
                    kpPlanetSignificationStr1.append(kpPlanetSignificationArray1[i]).append(",");
                    kpPlanetSignificationStr2.append(kpPlanetSignificationArray2[i]).append(",");
                    kpPlanetSignificationStr3.append(kpPlanetSignificationArray3[i]).append(",");
                    kpPlanetSignificationStr4.append(kpPlanetSignificationArray4[i]).append(",");
                    kpPlanetSignificationStr5.append(kpPlanetSignificationArray5[i]).append(",");
                    kpPlanetSignificationStr6.append(kpPlanetSignificationArray6[i]).append(",");
                    kpPlanetSignificationStr7.append(kpPlanetSignificationArray7[i]).append(",");
                    kpPlanetSignificationStr8.append(kpPlanetSignificationArray8[i]).append(",");
                    kpPlanetSignificationStr9.append(kpPlanetSignificationArray9[i]).append(",");

                }
            }
            for (int i = 0; i < 12; i++) {
                StringBuilder ashatakVarga = new StringBuilder();
                for (int j = 0; j < 7; j++) {
                    ashatakVarga.append(args1.getAshtakvargaBinduForSignAndPlanet(j, i)).append(",");
                }
                ashtakvargaRashi[i] = ashatakVarga.toString();
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("lagna", lagnaPlanetArray.toString());
            jsonObject.put("navmansh", navmanshPlanetArray.toString());
            jsonObject.put("drekkana", drekkanaStr.toString());
            jsonObject.put("chaturthamansh", chaturthamanshStr.toString());
            jsonObject.put("saptamamsha", saptamamshaStr.toString());
            jsonObject.put("dashamamsha", dashamamshaStr.toString());
            jsonObject.put("dwadashamamsha", dwadashamamshaStr.toString());
            jsonObject.put("shodashamsha", shodashamshaStr.toString());
            jsonObject.put("vimshamsha", vimshamshaStr.toString());
            jsonObject.put("saptavimshamsha", saptavimshamshaStr.toString());
            jsonObject.put("chaturvimshamsha", chaturvimshamshaStr.toString());
            jsonObject.put("trimshamsha", trimshamshaStr.toString());
            jsonObject.put("khavedamsha", khavedamshaStr.toString());
            jsonObject.put("akshvedamsha", akshvedamshaStr.toString());
            jsonObject.put("shashtiamsha", shashtiamshaStr.toString());
            jsonObject.put("planetDegree", planetDegreeStr.toString());
            jsonObject.put("kpCusp", kpCuspDegreeStr.toString());
            jsonObject.put("kpayan", args1.getKPAyanamsaLongitude());
            jsonObject.put("fortuna", args1.getKPFortunaLongitude());
            jsonObject.put("RPDayLord", args1.getKPDayLordName());
            jsonObject.put("planetsignification1", kpPlanetSignificationStr1.toString());
            jsonObject.put("planetsignification2", kpPlanetSignificationStr2.toString());
            jsonObject.put("planetsignification3", kpPlanetSignificationStr3.toString());
            jsonObject.put("planetsignification4", kpPlanetSignificationStr4.toString());
            jsonObject.put("planetsignification5", kpPlanetSignificationStr5.toString());
            jsonObject.put("planetsignification6", kpPlanetSignificationStr6.toString());
            jsonObject.put("planetsignification7", kpPlanetSignificationStr7.toString());
            jsonObject.put("planetsignification8", kpPlanetSignificationStr8.toString());
            jsonObject.put("planetsignification9", kpPlanetSignificationStr9.toString());
            jsonObject.put("ashtakvargar1", ashtakvargaRashi[0]);
            jsonObject.put("ashtakvargar2", ashtakvargaRashi[1]);
            jsonObject.put("ashtakvargar3", ashtakvargaRashi[2]);
            jsonObject.put("ashtakvargar4", ashtakvargaRashi[3]);
            jsonObject.put("ashtakvargar5", ashtakvargaRashi[4]);
            jsonObject.put("ashtakvargar6", ashtakvargaRashi[5]);
            jsonObject.put("ashtakvargar7", ashtakvargaRashi[6]);
            jsonObject.put("ashtakvargar8", ashtakvargaRashi[7]);
            jsonObject.put("ashtakvargar9", ashtakvargaRashi[8]);
            jsonObject.put("ashtakvargar10", ashtakvargaRashi[9]);
            jsonObject.put("ashtakvargar11", ashtakvargaRashi[10]);
            jsonObject.put("ashtakvargar12", ashtakvargaRashi[11]);
            jsonObject.put("ayan", args1.getAyanamsa());


            jsonObject.put("paksha", args1.getPakshaName());
            jsonObject.put("tithi", args1.getTithiName());
            jsonObject.put("nakshatra", args1.getNakshatraName());
            jsonObject.put("hinduWeekDay", args1.getHinduWeekdayName());
            jsonObject.put("englishWeekDay", args1.getHinduWeekdayName());
            jsonObject.put("yoga", args1.getYoganame());
            jsonObject.put("karan", args1.getKaranName());
            jsonObject.put("sunRiseTime", args1.getSunRiseTime());
            jsonObject.put("sunSetTime", args1.getSunSetTime());
            JSONArray prastharashtakvargaJsonArray = new JSONArray();
            String[] bindu = {"SU", "MO", "MA", "ME", "JU", "VE", "SA", "AS"};
            String[] plaNo = {"SUN", "MOON", "MARS", "MERCURY", "JUPITER", "VENUS", "SATURN", "RAHU"};
            for (int i = 1; i <= 8; i++) {
                JSONObject jsonObject1 = new JSONObject();
                for (int j = 1; j <= bindu.length; j++) {
                    StringBuilder str = new StringBuilder();
                    for (int k = 1; k < 13; k++) {
                        str.append(args1.getPrastharashtakvargaTables(i, j, k)).append(",");
                    }
                    jsonObject1.put(bindu[j - 1], str);
                }
                prastharashtakvargaJsonArray.put(jsonObject1);
            }
            jsonObject.put("prastharashtakvarga", prastharashtakvargaJsonArray);

            jsonObject.put("paya", args1.getPayaName());
            jsonObject.put("varna", args1.getVarnaName());
            jsonObject.put("yoni", args1.getYoniName());
            jsonObject.put("gana", args1.getGanaName() + args1.getGana());
            jsonObject.put("vasya", args1.getVasyaName());
            jsonObject.put("nadi", args1.getNadiName());
            jsonObject.put("balanceOfDasha", args1.getBalanceOfDasaString());
            jsonObject.put("lagnaA", args1.getLagnaSign());
            jsonObject.put("lagnaLord", args1.getLagnaLordName());
            jsonObject.put("rasi", args1.getRasiName());
            jsonObject.put("rasiLord", args1.getRasiLordName());
            jsonObject.put("nakshatraPada", args1.getNakshatraName());
            jsonObject.put("nakshatraLord", args1.getNakshatraLordName());
            jsonObject.put("julianDay", args1.getJulianDayValue());
            jsonObject.put("sunSignIndian", args1.getIndianSunSignName());
            jsonObject.put("sunSignWestern", args1.getSunSignName());
            jsonObject.put("ayanamsa", args1.getAyanamsaDms());
            jsonObject.put("ayanamsaName", args1.getAyanamsaType());
            jsonObject.put("obliquity", args1.getObliquityDms());
            jsonObject.put("sideralTime", args1.getSiderealTimeHms());
            Log.i("VimsottariDasa2", "fg" + args1.getMahaDasa(1));
           /* Log.i("VimsottariDasa3", args1.getVimsottariDasaThreeLevelString());
            Log.i("VimsottariDasa4", args1.getVimsottariDasaFourLevelString());*/
            jsonArray.put(jsonObject);

        } catch (Exception var1) {
            var1.printStackTrace();
        }
        return jsonArray.toString();
    }
}
