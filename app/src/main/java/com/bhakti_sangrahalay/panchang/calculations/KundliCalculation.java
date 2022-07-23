package com.bhakti_sangrahalay.panchang.calculations;


import com.bhakti_sangrahalay.model.KundliBean;

import java.util.ArrayList;


public class KundliCalculation {
    ArrayList<KundliBean> arrayList;

    public KundliCalculation(ArrayList<KundliBean> arrayList) {
        this.arrayList = arrayList;
    }

    public double[] getKpDegreeArray() {
        String[] planetDegree = arrayList.get(0).getKpCusp().split(",");
        double[] degree = new double[13];
        for (int i = 0; i < planetDegree.length; i++) {
            degree[i] = Double.parseDouble(planetDegree[i]);
        }
        return degree;
    }

    private double[] getPlanetDegreeArray() {
        String[] planetDegree = arrayList.get(0).getPlanetDegree().split(",");
        double[] degree = new double[13];
        for (int i = 0; i < planetDegree.length; i++) {
            degree[i] = Double.parseDouble(planetDegree[i]);
        }
        return degree;
    }

    private double[] getKPPlanetDegreeArray() {
        double[] planetDegree = getPlanetDegreeArray();
        double[] degree = new double[13];
        double tempCalculation = 0;
        double ayanDiff = Double.parseDouble(arrayList.get(0).getAyan()) - Double.parseDouble(arrayList.get(0).getKpayan());
        for (int i = 0; i < planetDegree.length; i++) {
            tempCalculation = planetDegree[i] + ayanDiff;
            if (tempCalculation < 0) {
                tempCalculation += 360.00;
            } else if (tempCalculation >= 360) {
                tempCalculation -= 360.00;
            }
            degree[i] = tempCalculation;
        }
        return degree;
    }


    public int[] getLaganKundliArray() {
        return getIntArrayFromString(arrayList.get(0).getLagna(), 0);
    }

    public int[] getNavmanshKundliArray() {
        return getIntArrayFromString(arrayList.get(0).getNavmansh(), 0);
    }

    public int[] getChandraKundliArray() {
        return getIntArrayFromString(arrayList.get(0).getLagna(), 2);
    }

    public int[] getDrekkanaArray() {
        return getIntArrayFromString(arrayList.get(0).getDrekkana(), 0);
    }

    public int[] getChaturthamanshArray() {
        return getIntArrayFromString(arrayList.get(0).getChaturthamansh(), 0);
    }

    public int[] getSaptamamshaArray() {
        return getIntArrayFromString(arrayList.get(0).getSaptamamsha(), 0);
    }

    public int[] getDashamamshaArray() {
        return getIntArrayFromString(arrayList.get(0).getDashamamsha(), 0);
    }

    public int[] getDwadashamamshaArray() {
        return getIntArrayFromString(arrayList.get(0).getDwadashamamsha(), 0);
    }

    public int[] getShodashamshaArray() {
        return getIntArrayFromString(arrayList.get(0).getShodashamsha(), 0);
    }

    public int[] getVimshamshaArray() {
        return getIntArrayFromString(arrayList.get(0).getVimshamsha(), 0);
    }

    public int[] getSaptavimshamshaArray() {
        return getIntArrayFromString(arrayList.get(0).getSaptavimshamsha(), 0);
    }

    public int[] getChaturvimshamshaArray() {
        return getIntArrayFromString(arrayList.get(0).getChaturvimshamsha(), 0);
    }

    public int[] getTrimshamshaArray() {
        return getIntArrayFromString(arrayList.get(0).getTrimshamsha(), 0);
    }

    public int[] getKhavedamshaArray() {
        return getIntArrayFromString(arrayList.get(0).getKhavedamsha(), 0);
    }

    public int[] getAkshvedamshaArray() {
        return getIntArrayFromString(arrayList.get(0).getAkshvedamsha(), 0);
    }

    public int[] getShashtiamshaArray() {
        return getIntArrayFromString(arrayList.get(0).getShashtiamsha(), 0);
    }


    private int[] getIntArrayFromString(String planetPositionStr, int lagnaPos) {
        String[] rashiInpla = planetPositionStr.split(",");
        int[] intArray = new int[13];
        int lagna = Integer.parseInt(rashiInpla[lagnaPos]);
        for (int i = 0; i < rashiInpla.length - 1; i++) {
            intArray[i] = Integer.parseInt(rashiInpla[i + 1]);
        }
        intArray[rashiInpla.length - 1] = lagna;
        return intArray;
    }

    public int getKarakanshLagna() {
        int karkanshLagna = calculateHigestDegree(getPlanetDegreeArray());
        return getNavmanshKundliArray()[karkanshLagna];
    }

    private int calculateHigestDegree(double[] planetsDegree) {
        double higestDegreeNumber = 0.0;
        double higestValue = 0.0;
        int position = 0;
        for (int i = 1; i <= 7; i++) {
            higestDegreeNumber = planetsDegree[i] % 30;
            if (higestDegreeNumber > higestValue) {
                position = i - 1;
                higestValue = higestDegreeNumber;
            }
        }
        return position;
    }


    public int[] getChalitChartArray() {
        int lagna = 0;
        int[] planetInRashi = new int[13];
        double[] cuspsDegreeArray = getCuspsDegreeArray();
        double[] planetDegreeArray = getPlanetDegreeArray();
        double lagnaDegree = planetDegreeArray[0];
        double lagnaVal = cuspsDegreeArray[0] + 1.00;
        if (lagnaVal > 360.00)
            lagnaVal -= 360.00;
        int count;
        for (count = 0; count < planetDegreeArray.length - 1; count++) {
            planetDegreeArray[count] = planetDegreeArray[count + 1];
        }
        planetDegreeArray[count] = lagnaDegree;
        boolean isLagnaAdded = false;
        double bhav1, bhav2;
        for (int i = 0; i < 12; i++) {
            bhav1 = cuspsDegreeArray[i];
            if (i < 11) {
                bhav2 = cuspsDegreeArray[i + 1];
            } else {
                bhav2 = cuspsDegreeArray[0];
            }
            for (int j = 0; j < 12; j++) {
                if (hasInHouse(bhav2, bhav1, planetDegreeArray[j])) {
                    planetInRashi[j] = i;
                }
                if (!isLagnaAdded && hasInHouse(bhav2, bhav1, lagnaVal)) {
                    lagna = i;
                    isLagnaAdded = true;
                }
            }
        }
        planetInRashi[12] = lagna;
        return planetInRashi;
    }

    public boolean hasInHouse(double cusp2, double cusp1, double plntDegree) {
        double temp2 = cusp2;
        double temp1 = cusp1;
        String s = "";
        if ((temp2 - temp1) < 0)
            temp2 += 360.00;
        if ((temp1 < (plntDegree + 360.0)) && ((plntDegree + 360.0) < temp2)) {

            return true;
        }
        if ((temp1 < plntDegree) && (plntDegree < temp2)) {
            return true;
        }
        return false;
    }

    public double[] getCuspsDegreeArray() {

        double[] cuspDegree = new double[12];
        double[] tempDegree = new double[12];
        double diff2 = 0.0, temp1 = 0;

        cuspDegree = getCuspsMidDegreeArrayForChalit();

        // CLACULATE CUSP DEGREE
        for (int i = 0; i < 12; i++) {

            if (i == 0) {
                diff2 = cuspDegree[0] - cuspDegree[11];
                if (diff2 < 0) {
                    diff2 = 360.0 - cuspDegree[11];
                    diff2 = diff2 + cuspDegree[0];
                }
            } else {
                diff2 = cuspDegree[i] - cuspDegree[i - 1];
                if (diff2 < 0) {
                    diff2 = 360.0 - cuspDegree[i - 1];
                    diff2 = diff2 + cuspDegree[i];
                }
            }
            diff2 /= 2.0;
            temp1 = cuspDegree[i] - diff2;
            if (temp1 < 0.0)
                temp1 += 360.0;
            tempDegree[i] = temp1;
        }
        return tempDegree;
    }

    public double[] getCuspsMidDegreeArrayForChalit() {

        double[] cuspDegree = new double[12];
        double ayaDiff = 0.0;
        double diff = 0.0;

        ayaDiff = Double.parseDouble(arrayList.get(0).getKpayan()) - Double.parseDouble(arrayList.get(0).getAyan());


        //CUSP -1
        cuspDegree[0] = getKpDegreeArray()[0] + ayaDiff;
        cuspDegree[0] = checkDegree(cuspDegree[0]);

        //CUSP -10
        cuspDegree[9] = getKpDegreeArray()[9] + ayaDiff;
        cuspDegree[9] = checkDegree(cuspDegree[9]);


        //CUSP -7
        cuspDegree[6] = cuspDegree[0] + 180;
        cuspDegree[6] = checkDegree(cuspDegree[6]);

        //CUSP -4
        cuspDegree[3] = cuspDegree[9] - 180;
        cuspDegree[3] = checkDegree(cuspDegree[3]);

        //CUSP -2,3
        diff = cuspDegree[3] - cuspDegree[0];
        if (diff < 0)
            diff += 360.0;

        cuspDegree[1] = cuspDegree[0] + diff / 3;
        cuspDegree[1] = checkDegree(cuspDegree[1]);

        cuspDegree[2] = cuspDegree[1] + diff / 3;
        cuspDegree[2] = checkDegree(cuspDegree[2]);

        //CUSP -5,6
        diff = cuspDegree[6] - cuspDegree[3];
        if (diff < 0)
            diff += 360.0;

        cuspDegree[4] = cuspDegree[3] + diff / 3;
        cuspDegree[4] = checkDegree(cuspDegree[4]);

        cuspDegree[5] = cuspDegree[4] + diff / 3;
        cuspDegree[5] = checkDegree(cuspDegree[5]);


        //CUSP -8,9
        diff = cuspDegree[9] - cuspDegree[6];
        if (diff < 0)
            diff += 360.0;

        cuspDegree[7] = cuspDegree[6] + diff / 3;
        cuspDegree[7] = checkDegree(cuspDegree[7]);

        cuspDegree[8] = cuspDegree[7] + diff / 3;
        cuspDegree[8] = checkDegree(cuspDegree[8]);


        //CUSP -11,12
        diff = cuspDegree[0] - cuspDegree[9];
        if (diff < 0)
            diff += 360.0;

        cuspDegree[10] = cuspDegree[9] + diff / 3;
        cuspDegree[10] = checkDegree(cuspDegree[10]);

        cuspDegree[11] = cuspDegree[10] + diff / 3;
        cuspDegree[11] = checkDegree(cuspDegree[11]);


        return cuspDegree;
    }

    private double checkDegree(double deg) {
        double temp = deg;

        if (temp < 0)
            temp += 360.00;

        if (temp > 360.0)
            temp -= 360.00;

        return temp;
    }

}
