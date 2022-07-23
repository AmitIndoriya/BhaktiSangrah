package com.bhakti_sangrahalay.panchang.generator;

import android.content.Context;
import android.content.res.AssetManager;

import com.indoriya.horolib.dhoro.DesktopHoro;
import com.indoriya.horolib.util.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class GenerateKundliData {
    public static String getPlanets(Context context) {
        JSONArray jsonArray = new JSONArray();
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("Properties/ConstantsEnglish.properties");
            Constants ObjConst = new Constants();
            ObjConst.setInputStream(inputStream);

            String name = "Amit";
            String sex = "M";
            String day = "23";
            String month = "11";
            String year = "2020";
            String hrs = "18";
            String min = "30";
            String sec = "0";
            String dst = "0";
            String place = "Agra";
            String LongDeg = "078";
            String LongMin = "00";
            String LongEW = "E";
            String LatDeg = "027";
            String LatMin = "09";
            String LatNS = "N";
            String timeZone = "5.5";
            String ayanamsa = "0";
            String charting = "0";
            String kphn = "0";
            String button1 = "Get+Kundali";
            String languageCode = "0";


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
            args1.getTotalAshtakVargaValue();
            String lagnaPlanetArray = "";
            String navmanshPlanetArray = "";
            String drekkanaStr = "";
            String chaturthamanshStr = "";
            String saptamamshaStr = "";
            String dashamamshaStr = "";
            String dwadashamamshaStr = "";
            String shodashamshaStr = "";
            String vimshamshaStr = "";
            String saptavimshamshaStr = "";
            String chaturvimshamshaStr = "";
            String trimshamshaStr = "";
            String khavedamshaStr = "";
            String akshvedamshaStr = "";
            String shashtiamshaStr = "";
            String planetDegreeStr = "";
            String kpCuspDegreeStr = "";
            String kpPlanetSignificationStr1 = "";
            String kpPlanetSignificationStr2 = "";
            String kpPlanetSignificationStr3 = "";
            String kpPlanetSignificationStr4 = "";
            String kpPlanetSignificationStr5 = "";
            String kpPlanetSignificationStr6 = "";
            String kpPlanetSignificationStr7 = "";
            String kpPlanetSignificationStr8 = "";

            for (int i = 0; i < planetArray.length; i++) {
                if (i == planetArray.length - 1) {
                    lagnaPlanetArray = lagnaPlanetArray + planetArray[i];
                    navmanshPlanetArray = navmanshPlanetArray + navmanshArray[i];
                    drekkanaStr = drekkanaStr + drekkanaArray[i];
                    chaturthamanshStr = chaturthamanshStr + chaturthamanshArray[i];
                    saptamamshaStr = saptamamshaStr + saptamamshaArray[i];
                    dashamamshaStr = dashamamshaStr + dashamamshaArray[i];

                    dwadashamamshaStr = dwadashamamshaStr + dwadashamamshaArray[i];
                    shodashamshaStr = shodashamshaStr + shodashamshaArray[i];
                    vimshamshaStr = vimshamshaStr + vimshamshaArray[i];
                    saptavimshamshaStr = saptavimshamshaStr + saptavimshamshaArray[i];
                    chaturvimshamshaStr = chaturvimshamshaStr + chaturvimshamshaArray[i];
                    trimshamshaStr = trimshamshaStr + trimshamshaArray[i];
                    khavedamshaStr = khavedamshaStr + khavedamshaArray[i];
                    akshvedamshaStr = akshvedamshaStr + akshvedamshaArray[i];
                    shashtiamshaStr = shashtiamshaStr + shashtiamshaArray[i];
                    planetDegreeStr = planetDegreeStr + planetDegreeArray[i];

                } else {
                    lagnaPlanetArray = lagnaPlanetArray + planetArray[i] + ",";
                    navmanshPlanetArray = navmanshPlanetArray + navmanshArray[i] + ",";
                    drekkanaStr = drekkanaStr + drekkanaArray[i] + ",";
                    chaturthamanshStr = chaturthamanshStr + chaturthamanshArray[i] + ",";
                    saptamamshaStr = saptamamshaStr + saptamamshaArray[i] + ",";
                    dashamamshaStr = dashamamshaStr + dashamamshaArray[i] + ",";
                    dwadashamamshaStr = dwadashamamshaStr + dwadashamamshaArray[i] + ",";
                    shodashamshaStr = shodashamshaStr + shodashamshaArray[i] + ",";
                    vimshamshaStr = vimshamshaStr + vimshamshaArray[i] + ",";
                    saptavimshamshaStr = saptavimshamshaStr + saptavimshamshaArray[i] + ",";
                    chaturvimshamshaStr = chaturvimshamshaStr + chaturvimshamshaArray[i] + ",";
                    trimshamshaStr = trimshamshaStr + trimshamshaArray[i] + ",";
                    khavedamshaStr = khavedamshaStr + khavedamshaArray[i] + ",";
                    akshvedamshaStr = akshvedamshaStr + akshvedamshaArray[i] + ",";
                    shashtiamshaStr = shashtiamshaStr + shashtiamshaArray[i] + ",";
                    planetDegreeStr = planetDegreeStr + planetDegreeArray[i] + ",";
                }
            }
            for (int i = 0; i < kpCuspDegreeArray.length; i++) {
                if (i == kpCuspDegreeArray.length - 1) {
                    kpCuspDegreeStr = kpCuspDegreeStr + kpCuspDegreeArray[i];
                } else {
                    kpCuspDegreeStr = kpCuspDegreeStr + kpCuspDegreeArray[i] + ",";
                }
            }
            for (int i = 0; i < kpPlanetSignificationArray1.length; i++) {
                if (i == kpPlanetSignificationArray1.length - 1) {
                    kpPlanetSignificationStr1 = kpPlanetSignificationStr1 + kpPlanetSignificationArray1[i];
                    kpPlanetSignificationStr2 = kpPlanetSignificationStr2 + kpPlanetSignificationArray2[i];
                    kpPlanetSignificationStr3 = kpPlanetSignificationStr3 + kpPlanetSignificationArray3[i];
                    kpPlanetSignificationStr4 = kpPlanetSignificationStr4 + kpPlanetSignificationArray4[i];
                    kpPlanetSignificationStr5 = kpPlanetSignificationStr5 + kpPlanetSignificationArray5[i];
                    kpPlanetSignificationStr6 = kpPlanetSignificationStr6 + kpPlanetSignificationArray6[i];
                    kpPlanetSignificationStr7 = kpPlanetSignificationStr7 + kpPlanetSignificationArray7[i];
                    kpPlanetSignificationStr8 = kpPlanetSignificationStr8 + kpPlanetSignificationArray8[i];
                } else {
                    kpPlanetSignificationStr1 = kpPlanetSignificationStr1 + kpPlanetSignificationArray1[i] + ",";
                    kpPlanetSignificationStr2 = kpPlanetSignificationStr2 + kpPlanetSignificationArray2[i] + ",";
                    kpPlanetSignificationStr3 = kpPlanetSignificationStr3 + kpPlanetSignificationArray3[i] + ",";
                    kpPlanetSignificationStr4 = kpPlanetSignificationStr4 + kpPlanetSignificationArray4[i] + ",";
                    kpPlanetSignificationStr5 = kpPlanetSignificationStr5 + kpPlanetSignificationArray5[i] + ",";
                    kpPlanetSignificationStr6 = kpPlanetSignificationStr6 + kpPlanetSignificationArray6[i] + ",";
                    kpPlanetSignificationStr7 = kpPlanetSignificationStr7 + kpPlanetSignificationArray7[i] + ",";
                    kpPlanetSignificationStr8 = kpPlanetSignificationStr8 + kpPlanetSignificationArray8[i] + ",";

                }
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("lagna", lagnaPlanetArray);
            jsonObject.put("navmansh", navmanshPlanetArray);
            jsonObject.put("drekkana", drekkanaStr);
            jsonObject.put("chaturthamansh", chaturthamanshStr);
            jsonObject.put("saptamamsha", saptamamshaStr);
            jsonObject.put("dashamamsha", dashamamshaStr);
            jsonObject.put("dwadashamamsha", dwadashamamshaStr);
            jsonObject.put("shodashamsha", shodashamshaStr);
            jsonObject.put("vimshamsha", vimshamshaStr);
            jsonObject.put("saptavimshamsha", saptavimshamshaStr);
            jsonObject.put("chaturvimshamsha", chaturvimshamshaStr);
            jsonObject.put("trimshamsha", trimshamshaStr);
            jsonObject.put("khavedamsha", khavedamshaStr);
            jsonObject.put("akshvedamsha", akshvedamshaStr);
            jsonObject.put("shashtiamsha", shashtiamshaStr);
            jsonObject.put("planetDegree", planetDegreeStr);
            jsonObject.put("kpCusp", kpCuspDegreeStr);
            jsonObject.put("kpayan", args1.getKPAyanamsaLongitude());
            jsonObject.put("fortuna", args1.getKPFortunaLongitude());
            jsonObject.put("RPDayLord", args1.getKPDayLordName());
            jsonObject.put("planetsignification1", kpPlanetSignificationStr1);
            jsonObject.put("planetsignification2", kpPlanetSignificationStr2);
            jsonObject.put("planetsignification3", kpPlanetSignificationStr3);
            jsonObject.put("planetsignification4", kpPlanetSignificationStr4);
            jsonObject.put("planetsignification5", kpPlanetSignificationStr5);
            jsonObject.put("planetsignification6", kpPlanetSignificationStr6);
            jsonObject.put("planetsignification7", kpPlanetSignificationStr7);
            jsonObject.put("planetsignification8", kpPlanetSignificationStr8);
            jsonObject.put("ayan", args1.getAyanamsa());


            jsonArray.put(jsonObject);

        } catch (Exception var1) {
            var1.printStackTrace();
        }
        return jsonArray.toString();
    }
}
