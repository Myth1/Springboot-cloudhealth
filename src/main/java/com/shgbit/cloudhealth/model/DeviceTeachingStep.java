package com.shgbit.cloudhealth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("设备仪器教学步骤")
public class DeviceTeachingStep {
    //  仪器类型 测量类型 index 图片地址  文字描述
    @ApiModelProperty("测量类型")
    private String measure_type;
    @ApiModelProperty("仪器类型")
    private String device_type;
    @ApiModelProperty("第几步")
    private int step_index;
    @ApiModelProperty("步骤示意图")
    private String image_address;
    @ApiModelProperty("步骤文字描述")
    private String step_Describe;

    public DeviceTeachingStep() {
    }

    public DeviceTeachingStep(String device_type, String measure_type) {
        this.measure_type = measure_type;
        this.device_type = device_type;
    }

    public String getMeasure_type() {
        return measure_type;
    }

    public void setMeasure_type(String measure_type) {
        this.measure_type = measure_type;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public int getStep_index() {
        return step_index;
    }

    public void setStep_index(int step_index) {
        this.step_index = step_index;
    }

    public String getImage_address() {
        return image_address;
    }

    public void setImage_address(String images_address) {
        this.image_address = images_address;
    }

    public String getStep_Describe() {
        return step_Describe;
    }

    public void setStep_Describe(String step_Describe) {
        this.step_Describe = step_Describe;
    }
}
