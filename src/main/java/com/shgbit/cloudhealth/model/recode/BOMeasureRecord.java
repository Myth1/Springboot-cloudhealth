package com.shgbit.cloudhealth.model.recode;

public class BOMeasureRecord extends BaseMeasureRecord {
    //心率
    private int heartRate;
    //血氧
    private int bloodOxygen;

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getBloodOxygen() {
        return bloodOxygen;
    }

    public void setBloodOxygen(int bloodOxygen) {
        this.bloodOxygen = bloodOxygen;
    }
}
