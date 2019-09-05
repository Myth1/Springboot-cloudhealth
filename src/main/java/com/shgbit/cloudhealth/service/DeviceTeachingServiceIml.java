package com.shgbit.cloudhealth.service;

import com.shgbit.cloudhealth.dao.DeviceTeachingDaoIml;
import com.shgbit.cloudhealth.model.DeviceTeachingStep;
import com.shgbit.cloudhealth.model.TeachingMongoDoc;
import com.shgbit.cloudhealth.model.TeachingStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceTeachingServiceIml implements DeviceTeachingService {
    @Autowired
    private DeviceTeachingDaoIml deviceTeachingDaoIml;
    public List<TeachingStep> queryDeviceTeachingSteps(String deviceType, String measureType) {
        TeachingMongoDoc teachingMongoDoc = deviceTeachingDaoIml.queryTeachingStepWithSort(deviceType, measureType);
        return teachingMongoDoc.getTeachingStep();
    }

    public void addTeachingStep(DeviceTeachingStep deviceTeachingStep){
        deviceTeachingDaoIml.addTeachingStep(deviceTeachingStep);
    }
}
