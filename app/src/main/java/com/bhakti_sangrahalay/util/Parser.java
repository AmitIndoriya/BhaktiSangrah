package com.bhakti_sangrahalay.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.bhakti_sangrahalay.contansts.GlobalVariables;
import com.bhakti_sangrahalay.model.AartiBean3;
import com.bhakti_sangrahalay.model.FestivalModel;
import com.bhakti_sangrahalay.model.HoroscopeBean;
import com.bhakti_sangrahalay.model.KathaBean;
import com.bhakti_sangrahalay.model.KundliBean;
import com.bhakti_sangrahalay.model.MantraBean;
import com.bhakti_sangrahalay.model.MonthFestivalModel;
import com.bhakti_sangrahalay.model.PlaceModel;
import com.bhakti_sangrahalay.model.SunderKaandBean;
import com.bhakti_sangrahalay.model.SunderKandHomeBean;
import com.bhakti_sangrahalay.model.WeeklyHoroscopeBean;
import com.bhakti_sangrahalay.model.YearFestivalModel;
import com.bhakti_sangrahalay.model.YearlyHoroscopeBean;
import com.bhakti_sangrahalay.panchang.model.PanchakBean;
import com.bhakti_sangrahalay.panchang.model.PanchakTimeBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Parser {


    public SunderKaandBean sunderKandParser(Context context, Resources resources, String response) {
        SunderKaandBean sunderKaandBean = new SunderKaandBean();
        try {
            JSONObject mainJsonObject = new JSONObject(response);

            ArrayList<SunderKaandBean.SunderKandArray> sunderKandArrayArrayList = new ArrayList<>();
            String heading = mainJsonObject.getString("heading");

            JSONArray SunderKandArray = mainJsonObject.getJSONArray("SunderKandArray");

            for (int i = 0; i < SunderKandArray.length(); i++) {

                JSONObject outerJsonObject = SunderKandArray.getJSONObject(i);

                SunderKaandBean.SunderKandArray sunderKandArray = sunderKaandBean.new SunderKandArray();
                String title = outerJsonObject.getString("title");
                String image = outerJsonObject.getString("image");
                final int resourceId = resources.getIdentifier(image, "drawable", context.getPackageName());

                Drawable drawable = resources.getDrawable(resourceId);
                ArrayList<SunderKaandBean.SunderKandArray.Choupai> choupaiArrayList = new ArrayList<>();
                ArrayList<SunderKaandBean.SunderKandArray.Doha> dohaArrayList = new ArrayList<>();
                ArrayList<SunderKaandBean.SunderKandArray.Chhand> chhandArrayList = new ArrayList<>();
                ArrayList<SunderKaandBean.SunderKandArray.Shortha> shorthaArrayList = new ArrayList<>();
                if (outerJsonObject.has("Choupai")) {
                    JSONArray chopaiJsonArray = outerJsonObject.getJSONArray("Choupai");
                    for (int j = 0; j < chopaiJsonArray.length(); j++) {
                        SunderKaandBean.SunderKandArray.Choupai choupai = sunderKandArray.new Choupai();
                        JSONObject innerJsonObject = chopaiJsonArray.getJSONObject(j);
                        String chopai = innerJsonObject.getString("chopai");
                        String meaning = innerJsonObject.getString("meaning");
                        choupai.setChopai(chopai);
                        choupai.setMeaning(meaning);
                        choupaiArrayList.add(choupai);
                    }
                }
                if (outerJsonObject.has("Doha")) {
                    JSONArray dohaJsonArray = outerJsonObject.getJSONArray("Doha");
                    for (int k = 0; k < dohaJsonArray.length(); k++) {
                        SunderKaandBean.SunderKandArray.Doha doha = sunderKandArray.new Doha();
                        JSONObject innerJsonObject = dohaJsonArray.getJSONObject(k);
                        String dohaStr = innerJsonObject.getString("doha");
                        String meaning = innerJsonObject.getString("meaning");
                        doha.setDoha(dohaStr);
                        doha.setMeaning(meaning);
                        dohaArrayList.add(doha);
                    }
                }
                if (outerJsonObject.has("Chhand")) {
                    JSONArray chhandJsonArray = outerJsonObject.getJSONArray("Chhand");
                    for (int k = 0; k < chhandJsonArray.length(); k++) {
                        SunderKaandBean.SunderKandArray.Chhand chhand = sunderKandArray.new Chhand();
                        JSONObject innerJsonObject = chhandJsonArray.getJSONObject(k);
                        String chhandStr = innerJsonObject.getString("chhand");
                        String meaning = innerJsonObject.getString("meaning");
                        chhand.setChhand(chhandStr);
                        chhand.setMeaning(meaning);
                        chhandArrayList.add(chhand);
                    }
                }
                if (outerJsonObject.has("Sortha")) {
                    JSONArray shorthaJsonArray = outerJsonObject.getJSONArray("Sortha");
                    for (int k = 0; k < shorthaJsonArray.length(); k++) {
                        SunderKaandBean.SunderKandArray.Shortha shortha = sunderKandArray.new Shortha();
                        JSONObject innerJsonObject = shorthaJsonArray.getJSONObject(k);
                        String sorthaStr = innerJsonObject.getString("sortha");
                        String meaning = innerJsonObject.getString("meaning");
                        shortha.setShortha(sorthaStr);
                        shortha.setMeaning(meaning);
                        shorthaArrayList.add(shortha);
                    }
                }


                sunderKandArray.setTitle(title);
                sunderKandArray.setDrawable(drawable);
                sunderKandArray.setChoupaiArrayList(choupaiArrayList);
                sunderKandArray.setDohaArrayList(dohaArrayList);
                sunderKandArray.setChhandArrayList(chhandArrayList);
                sunderKandArray.setShorthaArrayList(shorthaArrayList);
                sunderKandArrayArrayList.add(sunderKandArray);
            }

            sunderKaandBean.setHeading(heading);
            sunderKaandBean.setSunderKandArrayArrayList(sunderKandArrayArrayList);
        } catch (Exception e) {
            Log.i("Exception", Objects.requireNonNull(e.getMessage()));
        }
        return sunderKaandBean;
    }


    public ArrayList<AartiBean3> aartiListParserNew(String response) {
        ArrayList<AartiBean3> arrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            AartiBean3 aartiBean;
            JSONArray jsonArray = jsonObject.getJSONArray("array");
            for (int i = 0; i < jsonArray.length(); i++) {
                aartiBean = new AartiBean3();
                aartiBean.setId(jsonArray.getJSONObject(i).getInt("id"));
                aartiBean.setTitle(jsonArray.getJSONObject(i).getString("title"));
                aartiBean.setUrl(jsonArray.getJSONObject(i).getString("url"));
                aartiBean.setSinger(jsonArray.getJSONObject(i).getString("singer"));
                aartiBean.setDuration(jsonArray.getJSONObject(i).getString("duration"));
                aartiBean.setAudiofilesize(jsonArray.getJSONObject(i).getString("audiofilesize"));
                aartiBean.setAudiofilename(jsonArray.getJSONObject(i).getString("audiofilename"));
                aartiBean.setDesc(jsonArray.getJSONObject(i).getString("desc"));
                aartiBean.setLocalSaveUrl(GlobalVariables.storagePath + "/" + jsonArray.getJSONObject(i).getString("audiofilename"));
                aartiBean.setDownLoading(false);
                aartiBean.setProgressStatus(0);
                aartiBean.setDownLoaded(Utility.isFileExist(aartiBean.getAudiofilename(), Long.parseLong(aartiBean.getAudiofilesize())));
                arrayList.add(aartiBean);
            }

        } catch (Exception e) {
            Log.e("e", Objects.requireNonNull(e.getMessage()));
        }
        return arrayList;
    }


    public static ArrayList<KathaBean> kathaListParser(String response) {
        ArrayList<KathaBean> arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            KathaBean kathaBean;
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                kathaBean = new KathaBean();
                jsonObject = jsonArray.getJSONObject(i);
                kathaBean.setTitle(jsonObject.getString("title"));
                kathaBean.setDesc(jsonObject.getString("desc"));
                arrayList.add(kathaBean);
            }

        } catch (Exception ignored) {

        }
        return arrayList;
    }

    public ArrayList<MantraBean> mantraListParser(String response) {
        ArrayList<MantraBean> arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            MantraBean mantraBean;
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                mantraBean = new MantraBean();
                jsonObject = jsonArray.getJSONObject(i);
                mantraBean.setTitle(jsonObject.getString("title"));
                mantraBean.setContent(jsonObject.getString("desc"));
                arrayList.add(mantraBean);
            }

        } catch (Exception ignored) {

        }
        return arrayList;
    }

    public ArrayList<SunderKandHomeBean> sunderKandSlok(String response) {
        ArrayList<SunderKandHomeBean> arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            SunderKandHomeBean sunderKandHomeBean;
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                sunderKandHomeBean = new SunderKandHomeBean();
                jsonObject = jsonArray.getJSONObject(i);
                sunderKandHomeBean.setDoha(jsonObject.getString("doha"));
                sunderKandHomeBean.setMeaning(jsonObject.getString("meaning"));
                arrayList.add(sunderKandHomeBean);
            }

        } catch (Exception ignored) {

        }
        return arrayList;
    }

    public ArrayList<PanchakBean> getPanchakList(String response) {
        ArrayList<PanchakBean> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject;
            JSONArray innerJsonArray;
            String month;
            String year;
            ArrayList<PanchakTimeBean> panchakTimeBeanArrayList;
            JSONObject innerJsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                month = jsonObject.getString("month");
                year = jsonObject.getString("year");
                innerJsonArray = jsonObject.getJSONArray("panchak_List");
                panchakTimeBeanArrayList = new ArrayList<>();

                for (int j = 0; j < innerJsonArray.length(); j++) {
                    innerJsonObject = innerJsonArray.getJSONObject(j);
                    panchakTimeBeanArrayList.add(new PanchakTimeBean(innerJsonObject.getString("startDate"),
                            innerJsonObject.getString("endDate"),
                            innerJsonObject.getString("startDay"),
                            innerJsonObject.getString("endDay"),
                            innerJsonObject.getString("startTime"),
                            innerJsonObject.getString("endTime"),
                            innerJsonObject.getString("monthStart"),
                            innerJsonObject.getString("monthEnd")));
                }
                list.add(new PanchakBean(month, year, panchakTimeBeanArrayList));
            }
        } catch (Exception e) {
            Log.i("", Objects.requireNonNull(e.getMessage()));
        }
        return list;
    }

    public YearFestivalModel getFestivalList(String response) {
        YearFestivalModel yearFestivalModel = null;
        ArrayList<MonthFestivalModel> monthFestList = new ArrayList<>();
        ArrayList<FestivalModel> festvalModelList;
        try {
            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject;
            int month;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                Calendar calendar = Calendar.getInstance();
                month = jsonObject.getInt("festval_month");
                JSONArray innerJsonArray = jsonObject.getJSONArray("festivals");
                calendar.set(Calendar.MONTH, month);
                JSONObject innerJSObject;
                festvalModelList = new ArrayList<>();
                for (int j = 0; j < innerJsonArray.length(); j++) {
                    innerJSObject = innerJsonArray.getJSONObject(j);
                    String festName = innerJSObject.getString("festval_name");
                    int festDate = innerJSObject.getInt("festval_date");
                    calendar.set(Calendar.DATE, festDate);
                    String festDateStr = festDate + ", " + Utility.getWeekList()[calendar.get(Calendar.DAY_OF_WEEK) - 1];
                    festvalModelList.add(new FestivalModel(festName, festDateStr));
                }
                monthFestList.add(new MonthFestivalModel(Utility.getMonthName(calendar), festvalModelList));
            }
            yearFestivalModel = new YearFestivalModel(monthFestList);
        } catch (Exception e) {
            Log.i("", Objects.requireNonNull(e.getMessage()));
        }
        return yearFestivalModel;
    }

    public ArrayList<PlaceModel> getPlaceList(String response) {
        ArrayList<PlaceModel> placeList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String city = jsonObject.getString("city");
                String state = jsonObject.getString("state");
                double latitude = jsonObject.getDouble("latitude");
                double longitude = jsonObject.getDouble("longitude");
                placeList.add(new PlaceModel(id, city, state, latitude, longitude, "India", "+5.5"));
            }
        } catch (Exception e) {
            Log.i("", Objects.requireNonNull(e.getMessage()));
        }
        return placeList;
    }

    public ArrayList<HoroscopeBean> parseHoroscopeData(String response) {
        ArrayList<HoroscopeBean> arrayList = new ArrayList<>();
        try {
            arrayList = new Gson().fromJson(response, new TypeToken<ArrayList<HoroscopeBean>>() {
            }.getType());

        } catch (Exception ex) {
            //
        }
        return arrayList;
    }

    public ArrayList<WeeklyHoroscopeBean> parseWeeklyHoroscopeData(String response) {
        ArrayList<WeeklyHoroscopeBean> arrayList = new ArrayList<>();
        try {
            arrayList = new Gson().fromJson(response, new TypeToken<ArrayList<WeeklyHoroscopeBean>>() {
            }.getType());

        } catch (Exception ex) {
            //
        }
        return arrayList;
    }

    public ArrayList<YearlyHoroscopeBean> parseYearlyHoroscopeData(String response) {
        ArrayList<YearlyHoroscopeBean> arrayList = new ArrayList<>();
        try {
            arrayList = new Gson().fromJson(response, new TypeToken<ArrayList<YearlyHoroscopeBean>>() {
            }.getType());

        } catch (Exception ex) {
            //
        }
        return arrayList;
    }

    public ArrayList<KundliBean> parseKundliData(String response) {
        ArrayList<KundliBean> arrayList = new ArrayList<>();
        try {
            arrayList = new Gson().fromJson(response, new TypeToken<ArrayList<KundliBean>>() {
            }.getType());

        } catch (Exception ex) {
            Log.i("Exception", ex.getMessage());
        }
        return arrayList;
    }

}
