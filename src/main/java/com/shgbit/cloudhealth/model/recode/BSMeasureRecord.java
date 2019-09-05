package com.shgbit.cloudhealth.model.recode;


public class BSMeasureRecord extends BaseMeasureRecord{

    //血糖
    private float bloodSugar;

    //测量状态: 0 凌晨 ，1 空腹 , 2 早餐前 3 早餐后  4 午餐前 5 午餐后  6 晚餐前  7  晚餐后 8  睡前
    private int measureStatus;

    public float getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(float bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public int getMeasureStatus() {
        return measureStatus;
    }

    public void setMeasureStatus(int measureStatus) {
        this.measureStatus = measureStatus;
    }
}
