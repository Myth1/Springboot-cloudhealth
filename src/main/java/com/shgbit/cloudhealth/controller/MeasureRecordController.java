package com.shgbit.cloudhealth.controller;

import com.shgbit.cloudhealth.exception.result.Result;
import com.shgbit.cloudhealth.exception.result.ResultUtil;
import com.shgbit.cloudhealth.model.recode.*;
import com.shgbit.cloudhealth.service.MeasureRecordServiceIml;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeasureRecordController {

    @Autowired
    private MeasureRecordServiceIml measureRecordServiceIml;
    @RequestMapping(value = "/addBPMeasureRecord", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加血压测量记录", notes = "添加血压测量记录接口") // 单个接口的描述
    public Result addMeasureRecord(@RequestBody BPMeasureRecord measureRecord, String loginId) {
        measureRecordServiceIml.addMeasureRecord(measureRecord, loginId);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/addBSMeasureRecord", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加血糖测量记录", notes = "添加血糖测量记录接口") // 单个接口的描述
    public Result addMeasureRecord(@RequestBody BSMeasureRecord measureRecord, String loginId) {
        measureRecordServiceIml.addMeasureRecord(measureRecord, loginId);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/addBOMeasureRecord", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加血氧测量记录", notes = "添加血氧测量记录接口") // 单个接口的描述
    public Result addMeasureRecord(@RequestBody BOMeasureRecord measureRecord, String loginId) {
        measureRecordServiceIml.addMeasureRecord(measureRecord, loginId);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/addWeightMeasureRecord", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加体重测量记录", notes = "添加体重测量记录接口") // 单个接口的描述
    public Result addMeasureRecord(@RequestBody WeightMeasureRecord measureRecord, String loginId) {
        measureRecordServiceIml.addMeasureRecord(measureRecord, loginId);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/addBTMeasureRecord", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加体温仪测量记录", notes = "添加体温仪测量记录接口") // 单个接口的描述
    public Result addMeasureRecord(@RequestBody BTMeasureRecord measureRecord, String loginId) {
        measureRecordServiceIml.addMeasureRecord(measureRecord, loginId);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/addETMeasureRecord", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加耳温仪测量记录", notes = "添加耳温仪测量记录接口") // 单个接口的描述
    public Result addMeasureRecord(@RequestBody ETMeasureRecord measureRecord, String loginId) {
        measureRecordServiceIml.addMeasureRecord(measureRecord, loginId);
        return ResultUtil.success();
    }


    @Deprecated
    @RequestMapping(value = "/queryMeasureRecordsByDateAndType", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据日期和类型查询测量记录", notes = "根据日期和类型查询测量记录接口") // 单个接口的描述
    public Result queryMeasureRecordsByDateAndType(int type, String date, String loginId) {
        //暂时废弃
        return ResultUtil.success(measureRecordServiceIml.queryMeasureRecordsByDateAndType(type, date, loginId));
    }

    @Deprecated
    @RequestMapping(value = "/queryMeasureRecordsByDate", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据日期查询测量记录", notes = "根据日期查询测量记录接口") // 单个接口的描述
    public Result queryMeasureRecordsByDate(String date, String loginId) {
        MeasureRecord measureRecord = measureRecordServiceIml.queryMeasureRecordsByDate(date, loginId);
        return ResultUtil.success(measureRecord);
    }

    @RequestMapping(value = "/queryMeasureRecordsByDates", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据日期查询测量记录", notes = "根据日期查询测量记录接口") // 单个接口的描述
    public Result queryMeasureRecordsByDate(String startTime,String endTime, String loginId,int[] measureTypes) {
        MeasureRecordResponse measureRecordResponse = measureRecordServiceIml.queryMeasureRecordsByDate(startTime,endTime, loginId,measureTypes);
        return ResultUtil.success(measureRecordResponse);
    }

}
