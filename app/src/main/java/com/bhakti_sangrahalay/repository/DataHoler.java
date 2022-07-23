package com.bhakti_sangrahalay.repository;

import androidx.lifecycle.MutableLiveData;

import com.bhakti_sangrahalay.contansts.Constants;
import com.bhakti_sangrahalay.model.AartiBean3;

import java.util.ArrayList;
import java.util.HashMap;

public class DataHoler {

    public int aartiListSize = 0;
    public int chalishaListSize = 0;
    HashMap<Integer, AartiBean3> hashMap = new HashMap<>();
    private MutableLiveData<HashMap<Integer, AartiBean3>> hashMapMutableLiveData = new MutableLiveData<HashMap<Integer, AartiBean3>>();
    public ArrayList<Integer> idList = new ArrayList<>();

    public void setAartiArrayList(ArrayList<AartiBean3> aartiArrayList, int type) {
        if (type == Constants.AARTI_TYPE) {
            aartiListSize = aartiArrayList.size();
        } else if (type == Constants.CHALISHA_TYPE) {
            chalishaListSize = aartiArrayList.size();
        }
        populateHashMap(aartiArrayList);
    }

    private void populateHashMap(ArrayList<AartiBean3> list) {
        for (int i = 0; i < list.size(); i++) {
            hashMap.put(list.get(i).getId(), list.get(i));
            idList.add(list.get(i).getId());
        }
        hashMapMutableLiveData.setValue(hashMap);
    }

    public HashMap<Integer, AartiBean3> getHashMap() {
        return hashMap;
    }

    public void updateHashMap(int id, AartiBean3 aartiBean) {
        hashMap.put(id, aartiBean);
    }

}
