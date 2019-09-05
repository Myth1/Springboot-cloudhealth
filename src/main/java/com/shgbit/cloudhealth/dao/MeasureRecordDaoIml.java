package com.shgbit.cloudhealth.dao;

import com.shgbit.cloudhealth.model.recode.*;
import com.shgbit.cloudhealth.util.InfluxDBConnection;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasureRecordDaoIml implements MeasureRecordDao {

    /*class InfluxDbConfig{
        public static final String time = "time";
        public static final String bloodOxygen = "bloodOxygen";
        public static final String bloodSugar = "bloodSugar";
        public static final String bodyPart = "bodyPart";
        public static final String bodyTemperatrue = "bodyTemperatrue";
        public static final String dataSources = "dataSources";
        public static final String dataType = "dataType";
        public static final String deviceId = "deviceId";
        public static final String diastolicPressure = "diastolicPressure";
        public static final String earTemperatrue = "earTemperatrue";
        public static final String heartRate = "heartRate";
        public static final String measureResult = "measureResult";
        public static final String measureStatus = "measureStatus";
        public static final String measureType = "measureType";
        public static final String systolicPressure = "systolicPressure";
        public static final String userName = "userName";
        public static final String weight = "weight";
    }*/
    @Autowired
    private MongoTemplate mongoTemplate;
    private String influxdbURL = "http://172.16.18.164:8086";
    private String influxdbName = "IOTDATA";
    private InfluxDBConnection influxDBConnection;


    @Override
    public <T> void addMeasureRecord(T t, String loginId) {
        //查询存不存在 这天的记录
        MeasureRecord measureRecord = queryMeasureRecord(t, loginId);
        MeasureDateAndType measureDateAndType = getRecordDateAndType(t);
        String updateField = "";
        if (measureRecord == null) {
            //不存在就直接插入
            MeasureRecord measureRecordSave = new MeasureRecord();
            measureRecordSave.setLoginId(loginId);
            measureRecordSave.setRecordDate(measureDateAndType.getMeasureDate());
            ArrayList data = new ArrayList();
            data.add(t);
            switch (measureDateAndType.getType()) {
                case 0:
                    measureRecordSave.setbPMeasureRecordList(data);
                    break;
                case 1:
                    measureRecordSave.setbOMeasureRecordList(data);
                    break;
                case 2:
                    measureRecordSave.setbSMeasureRecordList(data);
                    break;
                case 3:
                    measureRecordSave.setWeightMeasureRecordList(data);
                    break;
                case 4:
                    measureRecordSave.setbTMeasureRecordList(data);
                    break;
                case 5:
                    measureRecordSave.seteTMeasureRecordList(data);
                    break;
            }
            mongoTemplate.save(measureRecordSave);
        } else {
            //存在  具体类型就更新
            List list = null;

            switch (measureDateAndType.getType()) {
                case 0:
                    list = measureRecord.getbPMeasureRecordList();
                    updateField = "bPMeasureRecordList";
                    break;
                case 1:
                    list = measureRecord.getbOMeasureRecordList();
                    updateField = "bOMeasureRecordList";
                    break;
                case 2:
                    list = measureRecord.getbSMeasureRecordList();
                    updateField = "bSMeasureRecordList";
                    break;
                case 3:
                    list = measureRecord.getWeightMeasureRecordList();
                    updateField = "weightMeasureRecordList";
                    break;
                case 4:
                    list = measureRecord.getbTMeasureRecordList();
                    updateField = "bTMeasureRecordList";
                    break;
                case 5:
                    list = measureRecord.geteTMeasureRecordList();
                    updateField = "eTMeasureRecordList";
                    break;
            }
            if (list == null) {
                list = new ArrayList<>();
            }
            updateMeasureRecord(updateField, list, t, loginId);
        }

    }

    @Override
    public <T> List<T> queryMeasureRecordsByDateAndType(int type, String date, String loginId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("loginId").is(loginId)).addCriteria(Criteria.where("recordDate").is(date));
        MeasureRecord measureRecord = mongoTemplate.findOne(query, MeasureRecord.class);
        List list = null;
        switch (type) {
            case 0:
                list = measureRecord.getbPMeasureRecordList();
                break;
            case 1:
                list = measureRecord.getbPMeasureRecordList();
                break;
            case 2:
                list = measureRecord.getbPMeasureRecordList();
                break;
            case 3:
                list = measureRecord.getbPMeasureRecordList();
                break;
            case 4:
                list = measureRecord.getbPMeasureRecordList();
                break;
            case 5:
                list = measureRecord.getbPMeasureRecordList();
                break;
        }
        return list;
    }

    @Override
    public MeasureRecord queryMeasureRecordsByDate(String date, String loginId) {
        Query query = new Query();
        Criteria recordDateCriteria = Criteria.where("recordDate").is(date);
        Criteria loginIdCriteria = Criteria.where("loginId").is(loginId);
        query.addCriteria(recordDateCriteria).addCriteria(loginIdCriteria);
        MeasureRecord measureRecord = mongoTemplate.findOne(query, MeasureRecord.class);
        return measureRecord;
    }

    /**
     * @param startTime
     * @param endTime
     * @param loginId
     * @param measureTypes 0 血压 1 血氧 2 血糖 3 体重体脂 4 体温 5 耳温
     * @return
     */
    @Override
    public MeasureRecordResponse queryMeasureRecordsByDate(String startTime, String endTime, String loginId, int... measureTypes) {
        //不用mongodb 改用 influxdb
        MeasureRecordResponse measureRecordResponse = new MeasureRecordResponse();
        for (int measureType : measureTypes) {
            queryMeasureRecordFromInfluxDb(startTime, endTime, loginId, measureType, measureRecordResponse);
        }
        return measureRecordResponse;
    }

    private void queryMeasureRecordFromInfluxDb(String startTime, String endTime, String loginId, int measureType, MeasureRecordResponse measureRecordResponse) {
        String[] tableName = {"BloodPressure","BloodOxygen", "BloodSugar",  "Weight", "Thermometer", "EarThermometer","Ecg"};
        InfluxDBConnection influxDBConnection = new InfluxDBConnection("admin", "", influxdbURL, influxdbName, "");
        //SELECT * FROM "IOTDATA" WHERE measureType=1 AND time >='2019-09-03 00:00:00' AND time <='2019-09-04 00:00:00' order by time desc
        QueryResult results = influxDBConnection
                .query("SELECT * FROM " + tableName[measureType] + " WHERE userName='" + loginId + "' AND time >='" + startTime + "' AND time <='" + endTime + "' order by time desc");
        //results.getResults()是同时查询多条SQL语句的返回值，此处我们只有一条SQL，所以只取第一个结果集即可。
        QueryResult.Result oneResult = results.getResults().get(0);
        if (oneResult.getSeries() != null) {
            List<List<Object>> valueList = oneResult.getSeries().stream().map(QueryResult.Series::getValues)
                    .collect(Collectors.toList()).get(0);
            /*List<String> columnsList = oneResult.getSeries().stream().map(QueryResult.Series::getColumns)
                    .collect(Collectors.toList()).get(0);*/
            //查询数据条数
            if (valueList != null && valueList.size() > 0) {
                //单条数据封装数据
                switch (measureType) {
                    case 0:
                        List<BPMeasureRecord> bpMeasureRecords = new ArrayList<>();
                        for (List<Object> value : valueList) {
                            BPMeasureRecord bpMeasureRecord = new BPMeasureRecord();
                            bpMeasureRecord.setRecordTime(value.get(0).toString());
                            bpMeasureRecord.setDataSources(value.get(1).toString());
                            bpMeasureRecord.setDeviceId(value.get(2).toString());
                            bpMeasureRecord.setDiastolicPressure((int) (Double.valueOf(value.get(3).toString()).doubleValue()));
                            bpMeasureRecord.setHeartRate((int) (Double.valueOf(value.get(4).toString()).doubleValue()));
                            bpMeasureRecord.setMeasureResult(value.get(5).toString());
                            bpMeasureRecord.setMeasureStatus((int) (Double.valueOf(value.get(6).toString()).doubleValue()));
                            bpMeasureRecord.setMeasureType((int) (Double.valueOf(value.get(7).toString()).doubleValue()));
                            bpMeasureRecord.setSystolicPressure((int) (Double.valueOf(value.get(8).toString()).doubleValue()));
                            measureRecordResponse.setUserName(value.get(9).toString());
                            bpMeasureRecords.add(bpMeasureRecord);
                        }
                        measureRecordResponse.setbPMeasureRecordList(bpMeasureRecords);
                        break;
                    case 1:
                        List<BOMeasureRecord> boMeasureRecords = new ArrayList<>();
                        for (List<Object> value : valueList) {
                            BOMeasureRecord boMeasureRecord = new BOMeasureRecord();
                            boMeasureRecord.setRecordTime(value.get(0).toString());
                            boMeasureRecord.setBloodOxygen((int) (Double.valueOf(value.get(1).toString()).doubleValue()));
                            boMeasureRecord.setDataSources(value.get(2).toString());
                            boMeasureRecord.setDeviceId(value.get(3).toString());
                            boMeasureRecord.setHeartRate((int) (Double.valueOf(value.get(4).toString()).doubleValue()));
                            boMeasureRecord.setMeasureResult(value.get(5).toString());
                            boMeasureRecord.setMeasureType((int) (Double.valueOf(value.get(6).toString()).doubleValue()));
                            measureRecordResponse.setUserName(value.get(7).toString());
                            boMeasureRecords.add(boMeasureRecord);
                        }
                        measureRecordResponse.setbOMeasureRecordList(boMeasureRecords);
                        break;
                    case 2:
                        List<BSMeasureRecord> bsMeasureRecords = new ArrayList<>();
                        for (List<Object> value : valueList) {
                            BSMeasureRecord bsMeasureRecord = new BSMeasureRecord();
                            bsMeasureRecord.setRecordTime(value.get(0).toString());
                            bsMeasureRecord.setBloodSugar((float) (Double.valueOf(value.get(1).toString()).doubleValue()));
                            bsMeasureRecord.setDataSources(value.get(2).toString());
                            bsMeasureRecord.setDeviceId(value.get(3).toString());
                            bsMeasureRecord.setMeasureResult(value.get(4).toString());
                            bsMeasureRecord.setMeasureStatus((int) (Double.valueOf(value.get(5).toString()).doubleValue()));
                            bsMeasureRecord.setMeasureType((int) (Double.valueOf(value.get(6).toString()).doubleValue()));
                            measureRecordResponse.setUserName(value.get(7).toString());
                            bsMeasureRecords.add(bsMeasureRecord);
                        }
                        measureRecordResponse.setbSMeasureRecordList(bsMeasureRecords);
                        break;
                    case 3:
                        List<WeightMeasureRecord> weightMeasureRecords = new ArrayList<>();
                        for (List<Object> value : valueList) {
                            WeightMeasureRecord weightMeasureRecord = new WeightMeasureRecord();
                            weightMeasureRecord.setRecordTime(value.get(0).toString());
                            weightMeasureRecord.setDataSources(value.get(1).toString());
                            weightMeasureRecord.setDeviceId(value.get(2).toString());
                            weightMeasureRecord.setMeasureResult(value.get(3).toString());
                            weightMeasureRecord.setMeasureType((int) (Double.valueOf(value.get(4).toString()).doubleValue()));
                            measureRecordResponse.setUserName(value.get(5).toString());
                            weightMeasureRecord.setWeight((float) (Double.valueOf(value.get(6).toString()).doubleValue()));
                            weightMeasureRecords.add(weightMeasureRecord);
                        }
                        measureRecordResponse.setWeightMeasureRecordList(weightMeasureRecords);
                        break;
                    case 4:
                        List<BTMeasureRecord> btMeasureRecords = new ArrayList<>();
                        for (List<Object> value : valueList) {
                            BTMeasureRecord btMeasureRecord = new BTMeasureRecord();
                            btMeasureRecord.setRecordTime(value.get(0).toString());
                            btMeasureRecord.setBodyTemperatrue((float) (Double.valueOf(value.get(1).toString()).doubleValue()));
                            btMeasureRecord.setDataSources(value.get(2).toString());
                            btMeasureRecord.setDeviceId(value.get(3).toString());
                            btMeasureRecord.setMeasureResult(value.get(4).toString());
                            btMeasureRecord.setMeasureType((int) (Double.valueOf(value.get(5).toString()).doubleValue()));
                            measureRecordResponse.setUserName(value.get(6).toString());
                            btMeasureRecords.add(btMeasureRecord);
                        }
                        measureRecordResponse.setbTMeasureRecordList(btMeasureRecords);
                        break;
                    case 5:
                        List<ETMeasureRecord> etMeasureRecords = new ArrayList<>();
                        for (List<Object> value : valueList) {
                            ETMeasureRecord etMeasureRecord = new ETMeasureRecord();
                            etMeasureRecord.setRecordTime(value.get(0).toString());
                            etMeasureRecord.setBodyPart((int) (Double.valueOf(value.get(1).toString()).doubleValue()));
                            etMeasureRecord.setDataSources(value.get(2).toString());
                            etMeasureRecord.setDeviceId(value.get(3).toString());
                            etMeasureRecord.setEarTemperatrue((float) (Double.valueOf(value.get(4).toString()).doubleValue()));
                            etMeasureRecord.setMeasureResult(value.get(5).toString());
                            etMeasureRecord.setMeasureType((int) (Double.valueOf(value.get(6).toString()).doubleValue()));
                            measureRecordResponse.setUserName(value.get(7).toString());
                            etMeasureRecords.add(etMeasureRecord);
                        }
                        measureRecordResponse.seteTMeasureRecordList(etMeasureRecords);
                        break;
                }
            }
        }
    }

    @Override
    public <T> MeasureRecord queryMeasureRecord(T t, String loginId) {
        Query query = getQuery(t, loginId);
        MeasureRecord measureRecord = mongoTemplate.findOne(query, MeasureRecord.class);
        return measureRecord;
    }

    public <T> void updateMeasureRecord(String updateField, List list, T t, String loginId) {
        Query query = getQuery(t, loginId);
        list.add(t);
        Update update = new Update()
                .set(updateField, list);
        mongoTemplate.updateFirst(query, update, MeasureRecord.class);
    }

    private <T> MeasureDateAndType getRecordDateAndType(T t) {
        MeasureDateAndType measureDateAndType = new MeasureDateAndType();
        if (t instanceof BaseMeasureRecord) {
            BaseMeasureRecord baseMeasureRecord = (BaseMeasureRecord) t;
            measureDateAndType.setMeasureDate(baseMeasureRecord.getRecordTime().split(" ")[0]);
            measureDateAndType.setType(baseMeasureRecord.getMeasureType());
        }
        return measureDateAndType;
    }

    private <T> Query getQuery(T t, String loginId) {
        Query query = new Query();
        MeasureDateAndType measureDateAndType = getRecordDateAndType(t);
        Criteria recordDateCriteria = Criteria.where("recordDate").is(measureDateAndType.getMeasureDate());
        Criteria loginIdCriteria = Criteria.where("loginId").is(loginId);
        query.addCriteria(recordDateCriteria).addCriteria(loginIdCriteria);
        return query;
    }


}
