package com.shgbit.cloudhealth.dao;

import com.shgbit.cloudhealth.model.recode.MeasureRecord;
import com.shgbit.cloudhealth.model.recode.MeasureRecordResponse;

import java.util.List;

public interface MeasureRecordDao {
    <T> void addMeasureRecord(T t, String loginId);

    <T> MeasureRecord queryMeasureRecord(T t, String loginId);

//    <T> void updateMeasureRecord(List list, T t, String loginId);

   <T> List<T> queryMeasureRecordsByDateAndType(int type, String date, String loginId);

    MeasureRecord queryMeasureRecordsByDate(String date, String loginId);

    MeasureRecordResponse queryMeasureRecordsByDate(String startTime, String endTime, String loginId, int... measureTypes);
}
