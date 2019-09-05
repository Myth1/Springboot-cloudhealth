package com.shgbit.cloudhealth.test;

import com.shgbit.cloudhealth.controller.MeasureRecordController;
import com.shgbit.cloudhealth.model.recode.BOMeasureRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasureRecordTest {
    @Autowired
    private MeasureRecordController measureRecordController;
    @Test
    public void addMeasureRecodTest(){
      /*  BPMeasureRecord bpMeasureRecord = new BPMeasureRecord();
        bpMeasureRecord.setDiastolicPressure(90);
        bpMeasureRecord.setSystolicPressure(120);
        bpMeasureRecord.setHeartRate(99);
        bpMeasureRecord.setDataSources(0);
        bpMeasureRecord.setMeasureStatus(2);
        bpMeasureRecord.setMeasureType(0);
        bpMeasureRecord.setMeasureTime("2019-05-20 08:00");
        measureRecordController.addMeasureRecord(bpMeasureRecord,"123");*/

        BOMeasureRecord boMeasureRecord = new BOMeasureRecord();
        boMeasureRecord.setBloodOxygen(99);
        boMeasureRecord.setHeartRate(99);
        boMeasureRecord.setDataSources("0");
        boMeasureRecord.setMeasureType(1);
        boMeasureRecord.setRecordTime("2019-05-20 09:00");
        measureRecordController.addMeasureRecord(boMeasureRecord,"123");
    }
}
