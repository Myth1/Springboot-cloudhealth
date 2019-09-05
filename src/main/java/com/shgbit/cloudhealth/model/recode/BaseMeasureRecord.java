package com.shgbit.cloudhealth.model.recode;

public class BaseMeasureRecord {
    private String deviceId;
    //数据来源
    // 0 仪器检测 01 PC 02 Android 03 iOS 04 医院 05 其他
    // 1 手动输入  11 PC 12 Android 13 iOS 14 医院 15 其他
    private String dataSources;
    //数据上传的平台
    //p
    //记录时间 yyyy-MM-dd HH:mm
    private String recordTime;

    //测量类型 0 血压 1 血氧 2 血糖 3 体重体脂 4 体温 5 耳温
    private int measureType;
    //测量结果
    private String measureResult;

    public String getMeasureResult() {
        return measureResult;
    }

    public void setMeasureResult(String measureResult) {
        this.measureResult = measureResult;
    }
    public int getMeasureType() {
        return measureType;
    }

    public void setMeasureType(int measureType) {
        this.measureType = measureType;
    }

    public String getDataSources() {
        return dataSources;
    }

    public void setDataSources(String dataSources) {
        this.dataSources = dataSources;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
