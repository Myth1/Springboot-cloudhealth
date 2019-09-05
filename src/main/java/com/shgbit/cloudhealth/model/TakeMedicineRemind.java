package com.shgbit.cloudhealth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("TakeMedicineRemind")
public class TakeMedicineRemind {
    //MultipartFile file, String firstTimeTakeMedicine, String medicineName, boolean appNotify,
    // String frequencyTakeMedicine, int imgWidth, int imgHeight) {
    @ApiModelProperty(value = "用户id", required = true)
    private String loginId;
    @ApiModelProperty(value = "图片地址", required = true)
    private String imgAddress;
    @ApiModelProperty(value = "首次服药时间", required = true)
    private String firstTimeTakeMedicine;
    @ApiModelProperty(value = "药品名称", required = true)
    private String medicineName;
    @ApiModelProperty(value = "是否推送通知", required = true)
    private boolean appNotify;
    @ApiModelProperty(value = "服药频率", required = true, notes = "每天一次，每天两次")
    private String frequencyTakeMedicine;



    private int id;
    @ApiModelProperty(value = "服药提醒Id  在添加服药提醒接口中不需要", required = false)
    private String takeMedicineRemindId;
    @ApiModelProperty(value = "服药提醒是否结束", required = false,hidden = true)
    private boolean finished;
    @ApiModelProperty(value = "服药提醒创建时间", required = false,hidden = true)
    private String createTime;
    @ApiModelProperty(value = "服药提醒更新时间", required = false,hidden = true)
    private String updateTime;

    public boolean isFinished() {
        return finished;
    }

    public String getTakeMedicineRemindId() {
        return takeMedicineRemindId;
    }

    public TakeMedicineRemind setTakeMedicineRemindId(String takeMedicineRemindId) {
        this.takeMedicineRemindId = takeMedicineRemindId;
        return this;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public TakeMedicineRemind setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
        return this;
    }

    public String getFirstTimeTakeMedicine() {
        return firstTimeTakeMedicine;
    }

    public TakeMedicineRemind setFirstTimeTakeMedicine(String firstTimeTakeMedicine) {
        this.firstTimeTakeMedicine = firstTimeTakeMedicine;
        return this;
    }

    public TakeMedicineRemind setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public TakeMedicineRemind setMedicineName(String medicineName) {
        this.medicineName = medicineName;
        return this;
    }

    public boolean isAppNotify() {
        return appNotify;
    }

    public TakeMedicineRemind setAppNotify(boolean appNotify) {
        this.appNotify = appNotify;
        return this;
    }

    public String getFrequencyTakeMedicine() {
        return frequencyTakeMedicine;
    }

    public TakeMedicineRemind setFrequencyTakeMedicine(String frequencyTakeMedicine) {
        this.frequencyTakeMedicine = frequencyTakeMedicine;
        return this;
    }




    public String getLoginId() {
        return loginId;
    }

    public TakeMedicineRemind setLoginId(String loginId) {
        this.loginId = loginId;
        return this;
    }


    public TakeMedicineRemind setFinished(boolean finished) {
        this.finished = finished;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public TakeMedicineRemind setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUpdateTime() {
        return updateTime;
    }
}
