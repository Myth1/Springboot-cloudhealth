package com.shgbit.cloudhealth.service;


import com.shgbit.cloudhealth.model.recode.MeasureRecord;
import com.shgbit.cloudhealth.model.recode.MeasureRecordResponse;

import java.util.List;

public interface MeasureRecordService {
    <T> void addMeasureRecord(T t, String loginId);
    <T> List<T> queryMeasureRecordsByDateAndType(int type,String date, String loginId);
    MeasureRecord queryMeasureRecordsByDate(String date, String loginId);

    MeasureRecordResponse queryMeasureRecordsByDate(String startTime, String endTime, String loginId, int... measureTypes);
}
