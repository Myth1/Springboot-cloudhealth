package com.shgbit.cloudhealth.model;

public class TeachingStep {
    private int stepIndex;
    private String stepDes;
    private String imageAddress;

    public int getStepIndex() {
        return stepIndex;
    }

    public void setStepIndex(int stepIndex) {
        this.stepIndex = stepIndex;
    }

    public String getStepDes() {
        return stepDes;
    }

    public void setStepDes(String stepDes) {
        this.stepDes = stepDes;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    @Override
    public String toString() {
        return "TeachingStep{" +
                "stepIndex=" + stepIndex +
                ", stepDes='" + stepDes + '\'' +
                ", imageAddress='" + imageAddress + '\'' +
                '}';
    }
}
