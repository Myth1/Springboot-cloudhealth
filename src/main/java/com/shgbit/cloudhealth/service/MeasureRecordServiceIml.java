package com.shgbit.cloudhealth.service;

import com.shgbit.cloudhealth.dao.MeasureRecordDaoIml;
import com.shgbit.cloudhealth.model.recode.MeasureRecord;
import com.shgbit.cloudhealth.model.recode.MeasureRecordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeasureRecordServiceIml implements MeasureRecordService {
    @Autowired
    private MeasureRecordDaoIml measureRecordDaoIml;


    @Override
    public <T> void addMeasureRecord(T t, String loginId) {
        measureRecordDaoIml.addMeasureRecord(t,loginId);
    }

    @Override
    public <T> List<T> queryMeasureRecordsByDateAndType(int type,String date, String loginId) {
        List<T> list = measureRecordDaoIml.queryMeasureRecordsByDateAndType(type,date, loginId);
        if(list == null){
            return new ArrayList();
        }
        return list;
    }

    @Override
    public MeasureRecord queryMeasureRecordsByDate(String date, String loginId) {
        return measureRecordDaoIml.queryMeasureRecordsByDate(date,loginId);
    }
    @Override
    public MeasureRecordResponse queryMeasureRecordsByDate(String startTime, String endTime, String loginId, int... measureTypes) {
        return measureRecordDaoIml.queryMeasureRecordsByDate(startTime,endTime,loginId,measureTypes);
    }

}
