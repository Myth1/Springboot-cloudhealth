package com.shgbit.cloudhealth.model.recode;

import org.springframework.data.annotation.Id;

import java.util.List;

public class MeasureRecord<T>  {
    @Id
    private String id;
    private String loginId;

    //yyyy-MM-dd
    private String recordDate;
    private List<BPMeasureRecord> bPMeasureRecordList;
    private List<BOMeasureRecord> bOMeasureRecordList;
    private List<BSMeasureRecord> bSMeasureRecordList;
    private List<WeightMeasureRecord> weightMeasureRecordList;
    private List<BTMeasureRecord> bTMeasureRecordList;
    private List<ETMeasureRecord> eTMeasureRecordList;

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


    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public List<BPMeasureRecord> getbPMeasureRecordList() {
        return bPMeasureRecordList;
    }

    public void setbPMeasureRecordList(List<BPMeasureRecord> bPMeasureRecordList) {
        this.bPMeasureRecordList = bPMeasureRecordList;
    }

    public List<BOMeasureRecord> getbOMeasureRecordList() {
        return bOMeasureRecordList;
    }

    public void setbOMeasureRecordList(List<BOMeasureRecord> bOMeasureRecordList) {
        this.bOMeasureRecordList = bOMeasureRecordList;
    }

    public List<BSMeasureRecord> getbSMeasureRecordList() {
        return bSMeasureRecordList;
    }

    public void setbSMeasureRecordList(List<BSMeasureRecord> bSMeasureRecordList) {
        this.bSMeasureRecordList = bSMeasureRecordList;
    }

    public List<WeightMeasureRecord> getWeightMeasureRecordList() {
        return weightMeasureRecordList;
    }

    public void setWeightMeasureRecordList(List<WeightMeasureRecord> weightMeasureRecordList) {
        this.weightMeasureRecordList = weightMeasureRecordList;
    }

    public List<BTMeasureRecord> getbTMeasureRecordList() {
        return bTMeasureRecordList;
    }

    public void setbTMeasureRecordList(List<BTMeasureRecord> bTMeasureRecordList) {
        this.bTMeasureRecordList = bTMeasureRecordList;
    }

    public List<ETMeasureRecord> geteTMeasureRecordList() {
        return eTMeasureRecordList;
    }

    public void seteTMeasureRecordList(List<ETMeasureRecord> eTMeasureRecordList) {
        this.eTMeasureRecordList = eTMeasureRecordList;
    }
}
