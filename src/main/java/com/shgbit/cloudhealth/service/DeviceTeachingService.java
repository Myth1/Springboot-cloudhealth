package com.shgbit.cloudhealth.service;

import com.shgbit.cloudhealth.model.TeachingStep;

import java.util.List;

public interface DeviceTeachingService {
    List<TeachingStep> queryDeviceTeachingSteps(String deviceType, String measureType);
}
