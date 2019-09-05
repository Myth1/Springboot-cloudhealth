package com.shgbit.cloudhealth.model;

import io.swagger.annotations.ApiModelProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Remind  implements Comparable<Remind>{
    /*    @ApiModelProperty(value = "提醒id", notes = "如果该提醒在未来时间则不存在数据库，此id为空")
        @Id
        private String id;*/
  /*  @ApiModelProperty(value = "用户id", required = true)
    private String loginId;*/
    @ApiModelProperty(value = "计划id", required = true)
    private String planId;
    @ApiModelProperty(value = "提醒时间", required = true)
    private String remindTime;
    @ApiModelProperty(value = "提醒内容", required = true)
    private String content;
    //类型 （带有二级）：  0服药 1测量 10血压 11血氧 12血糖  13体重体脂 14体温 15耳温  16尿检 17其他   2复诊 3宣教 4问卷 5其他
    @ApiModelProperty(value = "提醒类型： 0 服药 1 测量 10 血压 11 血氧 12 血糖  13 体重体脂 14 体温 15 耳温  16 尿检 17 其他   2 复诊 3 宣教 4 问卷 5 其他", required = true)
    private String type;
    //"提醒状态：0 忽略 1 已完成 2 未完成",  "id为空，表示无状态"
    @ApiModelProperty(value = "提醒状态：0 忽略 1 已完成 2 未完成", required = true, notes = "id为空，表示无状态")
    private String remindStatusId;
    @ApiModelProperty(value = "图片地址", required = true, notes = "目前只有服药有图片")
    private String imgAddress;
    //来源  ：  0 用户自己添加  1 医生端推送
    @ApiModelProperty(value = "提醒来源：0 用户自己添加  1 医生端推送", required = true)
    private int from;
    @ApiModelProperty(value = "提醒时间 :  0 准时提醒 1 提前一分钟   2 提前两分钟 3 提前五分钟 4 提前半小时  5 提前一小时  6 提前两小时", required = true)
    private int remindTimeIndex;


    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
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

    public String getRemindStatusId() {
        return remindStatusId;
    }

    public void setRemindStatusId(String remindStatusId) {
        this.remindStatusId = remindStatusId;
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

    public int getRemindTimeIndex() {
        return remindTimeIndex;
    }

    public void setRemindTimeIndex(int remindTimeIndex) {
        this.remindTimeIndex = remindTimeIndex;
    }


    @Override
    public int compareTo(Remind remind) {
        Date remindTime = null;
        Date otherRemindTime = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try {
             otherRemindTime =simpleDateFormat.parse(remind.getRemindTime());
             remindTime = simpleDateFormat.parse(this.getRemindTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = (int) (remindTime.getTime() - otherRemindTime.getTime());
        return i;
    }
}
