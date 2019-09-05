package com.shgbit.cloudhealth.dao;

import com.shgbit.cloudhealth.model.DeviceTeachingStep;

import java.util.List;

public interface GuideImagesDao {
    List<DeviceTeachingStep> queryGuideSteps(DeviceTeachingStep measureGuideStep);
}
