package com.shgbit.cloudhealth.service;

import com.shgbit.cloudhealth.dao.GuideImagesDao;
import com.shgbit.cloudhealth.model.DeviceTeachingStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesServiceIml implements ImagesService {
    @Autowired
    private GuideImagesDao guideImagesDao;
    public List<DeviceTeachingStep> queryGuideSteps(String deviceType, String measureType) {
        DeviceTeachingStep measureGuideStep = new DeviceTeachingStep(deviceType,measureType);
        return guideImagesDao.queryGuideSteps(measureGuideStep);
    }
}
