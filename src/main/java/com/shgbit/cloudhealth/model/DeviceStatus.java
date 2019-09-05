package com.shgbit.cloudhealth.model;

public class DeviceStatus {
    //0 寻找设备中 1 设备异常 2 设备已连接 3 测量中 4 测量完成 5 取消测量
    private int statusCode;
    private String statusMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
