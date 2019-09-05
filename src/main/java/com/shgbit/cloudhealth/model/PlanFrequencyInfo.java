package com.shgbit.cloudhealth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
@ApiModel("PlanFrequencyInfo")
public class PlanFrequencyInfo {
    // 0 每天一次 1 每天两次 2 每天三次 3 每天四次 4 每两天一次 5 每周一次 6 每周两次 7 每周三次 8 每两周一次 9 每三周一次 10 每四周一次
    @ApiModelProperty(value = "提醒频次: 0 每天一次 1 每天两次 2 每天三次 3 每天四次 4 每两天一次 5 每周一次 6 每周两次 7 每周三次 8 每两周一次 9 每三周一次 10 每四周一次", required = true)
    private int frequencyTypeIndex;

    public int getFrequencyTypeIndex() {
        return frequencyTypeIndex;
    }

    public void setFrequencyTypeIndex(int frequencyTypeIndex) {
        this.frequencyTypeIndex = frequencyTypeIndex;
    }

    private List<TimesInfo> times;

    public List<TimesInfo> getTimes() {
        return times;
    }

    public void setTimes(List<TimesInfo> times) {
        this.times = times;
    }


}
