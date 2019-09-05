package com.shgbit.cloudhealth.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class TeachingMongoDoc {
    @Id
    private String id;
    private String deviceType;
    private String measureType;
    private List<TeachingStep> teachingStep;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }

    public List<TeachingStep> getTeachingStep() {
        return teachingStep;
    }

    public void setTeachingStep(List<TeachingStep> teachingStep) {
        this.teachingStep = teachingStep;
    }

    @Override
    public String toString() {
        return "TeachingMongoDoc{" +
                "deviceType='" + deviceType + '\'' +
                ", measureType='" + measureType + '\'' +
                ", teachingStep=" + teachingStep +
                '}';
    }
}
