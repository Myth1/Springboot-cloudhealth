package com.shgbit.cloudhealth.controller;

import com.shgbit.cloudhealth.exception.result.Result;
import com.shgbit.cloudhealth.exception.result.ResultEnum;
import com.shgbit.cloudhealth.exception.result.ResultUtil;
import com.shgbit.cloudhealth.model.EventRemindPlan;
import com.shgbit.cloudhealth.model.Remind;
import com.shgbit.cloudhealth.service.EventRemindServiceIml;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
public class EventRemindController {
    private final static Logger logger = LoggerFactory.getLogger(EventRemindController.class.getSimpleName());
    @Autowired
    private EventRemindServiceIml eventRemindServiceIml;

    @RequestMapping(value = "/addEventRemindPlan", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加事件的提醒计划", notes = "添加事件的提醒计划接口") // 单个接口的描述
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"), // 响应对应编码的描述
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public Result addEventRemindPlan(@RequestBody EventRemindPlan eventRemindPlan) {
        try {
            eventRemindServiceIml.insertEventRemindPlan(eventRemindPlan);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.UNKONW_ERROR);
        }
        return ResultUtil.success();
    }


    @RequestMapping(value = "/queryEventRemindPlan", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询计划", notes = "查询计划接口") // 单个接口的描述
    public Result queryEventRemindPlan(String planId) {
        EventRemindPlan eventRemind = eventRemindServiceIml.queryEventRemindPlan(planId);
        return ResultUtil.success(eventRemind);
    }

    @Deprecated//废弃
    @RequestMapping(value = "/queryAllEventRemind", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询所有事件", notes = "查询所有事件接口") // 单个接口的描述
    public Result queryAllEventRemind(String loginId) {
        List<EventRemindPlan> eventReminds = eventRemindServiceIml.queryAllEventRemind(loginId);
        return ResultUtil.success(eventReminds);
    }

    @RequestMapping(value = "/queryEventRemindByDate", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据日期查询所有事件", notes = "根据日期查询所有事件接口") // 单个接口的描述
    public Result queryEventRemindByDate(String loginId, String time) {
        List<Remind> eventReminds = eventRemindServiceIml.queryEventRemindByDate(loginId, time);
        return ResultUtil.success(eventReminds);
    }

    @RequestMapping(value = "/updateEventRemindPlan", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新计划", notes = "更新计划接口") // 单个接口的描述
    public Result updateEventRemindPlan(@RequestBody EventRemindPlan eventRemindPlan) {
        eventRemindServiceIml.updateEventRemindPlan(eventRemindPlan);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/deleteEventRemindPlan", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除计划", notes = "删除计划接口") // 单个接口的描述
    public Result deleteEventRemindPlan(String planId) {
        eventRemindServiceIml.deteleEventRemindPlan(planId);
        return ResultUtil.success();
    }

}
