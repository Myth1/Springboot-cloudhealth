package com.shgbit.cloudhealth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.util.Date;

@ApiModel("EventRemind")
public class EventRemindPlan implements Cloneable{
    @ApiModelProperty(value = "计划id")
    @Id
    private String id;
    @ApiModelProperty(value = "用户id", required = true)
    private String loginId;
    @ApiModelProperty(value = "计划开始日期 格式:yyyy-MM-dd", required = true)
    private String startTime;
    @ApiModelProperty(value = "计划结束日期 格式:yyyy-MM-dd HH:mm", required = true)
    private String endTime;
    @ApiModelProperty(value = "提醒时间 :  0 准时提醒 1 提前一分钟   2 提前两分钟 3 提前五分钟 4 提前半小时  5 提前一小时  6 提前两小时", required = true)
    private int remindTimeIndex;
    @ApiModelProperty(value = "计划提醒频率信息", required = true)
    private PlanFrequencyInfo planFrequencyInfo;
    @ApiModelProperty(value = "计划内容", required = true)
    private String content;
    //类型 （带有二级）：  0服药 1测量 10血压 11血氧 12血糖  13体重体脂 14体温 15耳温  16尿检 17其他   2复诊 3宣教 4问卷 5其他
    @ApiModelProperty(value = "计划类型： 0 服药 1 测量 10 血压 11 血氧 12 血糖  13 体重体脂 14 体温 15 耳温  16 尿检 17 其他   2 复诊 3 宣教 4 问卷 5 其他", required = true)
    private String type;
    //状态：0 未读 1 已完成 2 未完成
    @ApiModelProperty(value = "计划状态：0 正在进行 1 已删除",required = true)
    private int planStatus;
    @ApiModelProperty(value = "图片地址")
    private String imgAddress ;
    //url链接
    @ApiModelProperty(value = "附带链接")
    private String link;
    //来源  ：  0 用户自己添加  1 医生端推送
    @ApiModelProperty(value = "计划来源：0 用户自己添加  1 医生端推送", required = true)
    private int from;

    @ApiModelProperty(value = "计划创建时间", hidden = true)
    private Date createDate;
    @ApiModelProperty(value = "计划更新时间", hidden = true)
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(int planStatus) {
        this.planStatus = planStatus;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getRemindTimeIndex() {
        return remindTimeIndex;
    }

    public void setRemindTimeIndex(int remindTimeIndex) {
        this.remindTimeIndex = remindTimeIndex;
    }

    public PlanFrequencyInfo getPlanFrequencyInfo() {
        return planFrequencyInfo;
    }

    public void setPlanFrequencyInfo(PlanFrequencyInfo planFrequencyInfo) {
        this.planFrequencyInfo = planFrequencyInfo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }


    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public EventRemindPlan clone() throws CloneNotSupportedException {
        EventRemindPlan clone = (EventRemindPlan) super.clone();
        return clone;
    }
}
