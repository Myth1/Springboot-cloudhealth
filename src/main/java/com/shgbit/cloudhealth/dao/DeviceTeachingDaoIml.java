package com.shgbit.cloudhealth.dao;

import com.shgbit.cloudhealth.model.DeviceTeachingStep;
import com.shgbit.cloudhealth.model.TeachingMongoDoc;
import com.shgbit.cloudhealth.model.TeachingStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceTeachingDaoIml implements DeviceTeachingDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<DeviceTeachingStep> queryDeviceTeachingSteps(DeviceTeachingStep measureGuideStep) {

        return null;
    }

    public void addTeachingStep(DeviceTeachingStep deviceTeachingStep) {
        //1、先查找存不存在此设备 和 此测量类型
        TeachingMongoDoc queryTeachingMongoDoc = queryTeachingStep(deviceTeachingStep.getDevice_type(), deviceTeachingStep.getMeasure_type());
        if(queryTeachingMongoDoc == null){
            //不存在 ,新增一条Teachingdoc
            TeachingMongoDoc teachingMongoDoc = new TeachingMongoDoc();
            teachingMongoDoc.setDeviceType(deviceTeachingStep.getDevice_type());
            teachingMongoDoc.setMeasureType(deviceTeachingStep.getMeasure_type());
            ArrayList<TeachingStep> teachingSteps = new ArrayList<>();
            TeachingStep teachingStep = new TeachingStep();
            teachingStep.setImageAddress(deviceTeachingStep.getImage_address());
            teachingStep.setStepDes(deviceTeachingStep.getStep_Describe());
            teachingStep.setStepIndex(deviceTeachingStep.getStep_index());
            teachingSteps.add(teachingStep);
            teachingMongoDoc.setTeachingStep(teachingSteps);
            mongoTemplate.save(teachingMongoDoc);
        }else{
            //查找存不存在步骤 此步骤，
            TeachingMongoDoc queryTeachingMongoDoc1 = queryTeachingStep(deviceTeachingStep.getMeasure_type(), deviceTeachingStep.getDevice_type(), deviceTeachingStep.getStep_index());
            //存在则更新
            if (queryTeachingMongoDoc1 == null) {
                //对这条Teachingdoc 新增一个step
                updateTeachingStep(queryTeachingMongoDoc.getId(), deviceTeachingStep,queryTeachingMongoDoc.getTeachingStep());
            }else{
                //更新这条Teachingdoc 的step
                updateTeachingStep(queryTeachingMongoDoc1.getId(), deviceTeachingStep);

            }
        }


    }
    public TeachingMongoDoc queryTeachingStepWithSort( String deviceType,String measureType) {
        Query query = new Query();
        Criteria measureTypeCriteria = Criteria.where("measureType").is(measureType);
        Criteria deviceTypeCriteria = Criteria.where("deviceType").is(deviceType);
        Sort sort = new Sort(Sort.Direction.ASC, "stepIndex");
        query.addCriteria(measureTypeCriteria).addCriteria(deviceTypeCriteria).with(sort);
        TeachingMongoDoc teachingMongoDoc = mongoTemplate.findOne(query, TeachingMongoDoc.class);
        return teachingMongoDoc;

    }
    public TeachingMongoDoc queryTeachingStep( String deviceType,String measureType) {
        Query query = new Query();
        Criteria measureTypeCriteria = Criteria.where("measureType").is(measureType);
        Criteria deviceTypeCriteria = Criteria.where("deviceType").is(deviceType);
        query.addCriteria(measureTypeCriteria).addCriteria(deviceTypeCriteria);
        TeachingMongoDoc teachingMongoDoc = mongoTemplate.findOne(query, TeachingMongoDoc.class);
        return teachingMongoDoc;

    }

    public TeachingMongoDoc queryTeachingStep(String measureType, String deviceType, int stepIndex) {
        Query query = new Query();
        Criteria measureTypeCriteria = Criteria.where("measureType").is(measureType);
        Criteria deviceTypeCriteria = Criteria.where("deviceType").is(deviceType);
        Criteria stepCriteria = Criteria.where("teachingStep.stepIndex").is(stepIndex);
        query.addCriteria(measureTypeCriteria).addCriteria(deviceTypeCriteria).addCriteria(stepCriteria);
        TeachingMongoDoc teachingMongoDoc = mongoTemplate.findOne(query, TeachingMongoDoc.class);

        return teachingMongoDoc;

    }

    public void updateTeachingStep(String id, DeviceTeachingStep deviceTeachingStep) {
        Query query = new Query(Criteria.where("id").is(id));
        query.addCriteria(Criteria.where("teachingStep.stepIndex").is(deviceTeachingStep.getStep_index()));
        Update update = new Update()
                .set("teachingStep.$.stepDes", deviceTeachingStep.getStep_Describe())
                .set("teachingStep.$.imageAddress", deviceTeachingStep.getImage_address());
        mongoTemplate.updateFirst(query, update, TeachingMongoDoc.class);
    }

    public void updateTeachingStep(String id, DeviceTeachingStep deviceTeachingStep,List<TeachingStep> teachingSteps) {
        TeachingStep teachingStep = new TeachingStep();
        teachingStep.setStepIndex(deviceTeachingStep.getStep_index());
        teachingStep.setStepDes(deviceTeachingStep.getStep_Describe());
        teachingStep.setImageAddress(deviceTeachingStep.getImage_address());
        teachingSteps.add(teachingStep);
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update()
                .set("teachingStep", teachingSteps);
        mongoTemplate.updateFirst(query, update, TeachingMongoDoc.class);
    }
}
