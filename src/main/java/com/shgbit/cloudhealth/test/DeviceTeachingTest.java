package com.shgbit.cloudhealth.test;

import com.shgbit.cloudhealth.dao.DeviceTeachingDaoIml;
import com.shgbit.cloudhealth.model.DeviceTeachingStep;
import com.shgbit.cloudhealth.model.TeachingMongoDoc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceTeachingTest {
    @Autowired
    private DeviceTeachingDaoIml deviceTeachingDaoIml;

    @Test
    public void testAddDeviceTeachingStep(){
        DeviceTeachingStep deviceTeachingStep = new DeviceTeachingStep();
        deviceTeachingStep.setStep_index(3);
        deviceTeachingStep.setStep_Describe("haha");
        deviceTeachingStep.setImage_address("321");
        deviceTeachingStep.setDevice_type("心云");
        deviceTeachingStep.setMeasure_type("血氧");
        deviceTeachingDaoIml.addTeachingStep(deviceTeachingStep);
    }

    @Test
    public void testqueryDeviceTeachingStep(){
        TeachingMongoDoc teachingMongoDoc = deviceTeachingDaoIml.queryTeachingStep("心云", "血压");
        System.out.println(teachingMongoDoc.toString());
    }
}
