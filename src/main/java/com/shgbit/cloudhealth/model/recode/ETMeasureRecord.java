package com.shgbit.cloudhealth.model.recode;

public class ETMeasureRecord extends BaseMeasureRecord {
    private float earTemperatrue;
    //身体部位 0 口腔 1 耳朵 2 腋下 3 肛门
    private int bodyPart;
    public float getEarTemperatrue() {
        return earTemperatrue;
    }

    public void setEarTemperatrue(float earTemperatrue) {
        this.earTemperatrue = earTemperatrue;
    }

    public int getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(int bodyPart) {
        this.bodyPart = bodyPart;
    }
}
