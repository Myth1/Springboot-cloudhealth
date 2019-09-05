package com.shgbit.cloudhealth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("TimesInfo")
    public class TimesInfo {
        // 0 周一 1 周二 2 周三 3 周四 4 周五 5 周六 6 周日
        @ApiModelProperty(value = "提醒周次:  0 周一 1 周二 2 周三 3 周四 4 周五 5 周六 6 周日", required = true)
        private int week;
        // HH:mm
        @ApiModelProperty(value = "提醒时间: HH:mm", required = true)
        private String time;

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
