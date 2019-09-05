package com.shgbit.cloudhealth.model.recode;

public class BPMeasureRecord extends BaseMeasureRecord{
    //舒张压
    private int  diastolicPressure;
    //收缩压
    private int systolicPressure;
    //心率
    private int heartRate;
    //测量状态: 0 2小时内未服药 ，1 2小时内已服药
    private int measureStatus;

    public int getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(int diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public int getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(int systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getMeasureStatus() {
        return measureStatus;
    }

    public void setMeasureStatus(int measureStatus) {
        this.measureStatus = measureStatus;
    }

}
