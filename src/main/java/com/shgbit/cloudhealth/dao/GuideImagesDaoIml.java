package com.shgbit.cloudhealth.dao;

import com.shgbit.cloudhealth.model.DeviceTeachingStep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideImagesDaoIml implements GuideImagesDao {

    @Deprecated
    @Override
    public List<DeviceTeachingStep> queryGuideSteps(DeviceTeachingStep measureGuideStep) {
        //改用mondodb
        return null;
    }
}
