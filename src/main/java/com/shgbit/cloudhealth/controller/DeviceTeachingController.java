package com.shgbit.cloudhealth.controller;

import com.shgbit.cloudhealth.exception.result.Result;
import com.shgbit.cloudhealth.exception.result.ResultEnum;
import com.shgbit.cloudhealth.exception.result.ResultUtil;
import com.shgbit.cloudhealth.model.DeviceTeachingStep;
import com.shgbit.cloudhealth.model.TeachingStep;
import com.shgbit.cloudhealth.service.DeviceTeachingServiceIml;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DeviceTeachingController {
    private final static Logger logger = LoggerFactory.getLogger(DeviceTeachingController.class.getSimpleName());

    @Autowired
    private DeviceTeachingServiceIml deviceTeachingServiceIml;

    @RequestMapping(value = "/getDeciveTeachingSteps",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取设备使用教学步骤",notes = "获取设备使用教学步骤接口") // 单个接口的描述
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="deviceType",dataType="String",required=true,value="设备类型",defaultValue=""),
            @ApiImplicitParam(paramType="query",name="measureType",dataType="String",required=true,value="测量类型",defaultValue=""),
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"), // 响应对应编码的描述
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public Result<List<TeachingStep>> getDeciveTeachingSteps(String deviceType , String  measureType){
        logger.info(deviceType + "          "+measureType);
        if(deviceType.equals("") || measureType.equals("")){
            return ResultUtil.error(ResultEnum.PARAMETER_IS_NULL);
        }
        List<TeachingStep> deviceTeachingSteps = deviceTeachingServiceIml.queryDeviceTeachingSteps(deviceType, measureType);
        return ResultUtil.success(deviceTeachingSteps);
    }


    @RequestMapping(value = "/addTeachingStep",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加设备使用教学步骤",notes = "添加设备使用教学步骤接口") // 单个接口的描述
    public Result addTeachingStep(@RequestBody DeviceTeachingStep deviceTeachingStep){
        deviceTeachingServiceIml.addTeachingStep(deviceTeachingStep);
        return ResultUtil.success();
    }
}
