package com.shgbit.cloudhealth.service;

import com.shgbit.cloudhealth.dao.EventRemindDaoIml;
import com.shgbit.cloudhealth.model.EventRemindPlan;
import com.shgbit.cloudhealth.model.PlanFrequencyInfo;
import com.shgbit.cloudhealth.model.Remind;
import com.shgbit.cloudhealth.model.TimesInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EventRemindServiceIml implements EventRemindService {
    private final static Logger logger = LoggerFactory.getLogger(EventRemindServiceIml.class.getSimpleName());
    @Autowired
    private EventRemindDaoIml eventRemindDaoIml;

    @Override
    public void insertEventRemindPlan(EventRemindPlan eventRemindPlan) throws CloneNotSupportedException {
        //插入数据库
        EventRemindPlan eventRemindClone = eventRemindPlan.clone();
        Date date = new Date();
        eventRemindClone.setCreateDate(date);
        eventRemindClone.setUpdateDate(date);
        eventRemindDaoIml.insertEventRemindPlan(eventRemindClone);
    }

    @Override
    public List<EventRemindPlan> queryAllEventRemind(String loginId) {
        return eventRemindDaoIml.queryAllEventRemind(loginId);
    }

    @Override
    public void deteleEventRemindPlan(String planId) {
        eventRemindDaoIml.deteleEventRemindPlan(planId);
    }

    @Override
    public void updateEventRemindPlan(EventRemindPlan eventRemindPlan) {
        eventRemindDaoIml.updateEventRemindPlan(eventRemindPlan);
    }


    public List<Remind> queryEventRemindByDate(String loginId, String time) {
        //1、查询出loginId下所有计划
        ArrayList<Remind> remindList = new ArrayList<>();
        Date queryDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            queryDate = simpleDateFormat.parse(time);
            List<EventRemindPlan> eventRemindPlans = eventRemindDaoIml.queryEventRemindPlanByDate(loginId);
            for (EventRemindPlan eventRemindPlan : eventRemindPlans) {
                Date startDate = simpleDateFormat.parse(eventRemindPlan.getStartTime());
                //如果是查询日期 在计划开始时间和结束日期之间
                if ((eventRemindPlan.getEndTime().isEmpty() || queryDate.getTime() < simpleDateFormat2.parse(eventRemindPlan.getEndTime()).getTime())
                        && queryDate.getTime() >= startDate.getTime()) {
                    //如果在该计划的提醒频次内
                    boolean isHavePlan = false;
                    Calendar startTimeCal = Calendar.getInstance();
                    startTimeCal.setTime(startDate);

                    Calendar queryTimeCal = Calendar.getInstance();
                    queryTimeCal.setTime(queryDate);

                    PlanFrequencyInfo planFrequencyInfo = eventRemindPlan.getPlanFrequencyInfo();
                    List<TimesInfo> times = eventRemindPlan.getPlanFrequencyInfo().getTimes();

                    //根据计划频次 判断查询日期 是否 有提醒事件
                    //提醒频次: 0 每天一次 1 每天两次 2 每天三次 3 每天四次 4 每两天一次 5 每周一次
                    // 6 每周两次 7 每周三次 8 每两周一次 9 每三周一次 10 每四周一次
                    switch (planFrequencyInfo.getFrequencyTypeIndex()) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            isHavePlan = true;
                            break;
                        case 4:
                            //根据间隔的天数 和 周期
                            int duringDate = (int) ((queryTimeCal.getTimeInMillis() - startTimeCal.getTimeInMillis()) / (24 * 60 * 60 * 1000));
                            if (duringDate % 2 == 0) {
                                isHavePlan = true;
                            }
                            break;
                        case 5:
                        case 6:
                        case 7:
                            //开始时间 命中周期中 的周几
                            for (TimesInfo timesInfo : times) {
                                int week = timesInfo.getWeek() + 1;
                                int queryTimeCalWeek = queryTimeCal.get(Calendar.DAY_OF_WEEK) - 1;
                                //logger.info("week   " +  week +"  "+queryTimeCalWeek);
                                if (week == queryTimeCalWeek) {
                                    isHavePlan = true;
                                }
                            }
                            break;
                        case 8:
                        case 9:
                        case 10:
                            TimesInfo timesInfo = times.get(0);
                            int week = timesInfo.getWeek() + 1;
                            int startTimeWeek = startTimeCal.get(Calendar.DAY_OF_WEEK) - 1;
                            if (startTimeWeek <= week) {
                                startTimeCal.add(Calendar.DATE, week - startTimeWeek);
                            } else {
                                startTimeCal.add(Calendar.DATE, week - startTimeWeek + 7);
                            }
                            int duringDate2 = (int) ((queryTimeCal.getTimeInMillis() - startTimeCal.getTimeInMillis()) / (24 * 60 * 60 * 1000));
                            if ((planFrequencyInfo.getFrequencyTypeIndex() == 8 && duringDate2 % 14 == 0) || (planFrequencyInfo.getFrequencyTypeIndex() == 9 && duringDate2 % 21 == 0) || (planFrequencyInfo.getFrequencyTypeIndex() == 10 && duringDate2 % 28 == 0)) {
                                isHavePlan = true;
                            }
                            break;
                        default:
                            break;
                    }
                    if (isHavePlan) {
                        for (TimesInfo timesInfo : times) {
                            Remind remind = new Remind();
                            remind.setContent(eventRemindPlan.getContent());
                            remind.setFrom(eventRemindPlan.getFrom());
                            remind.setImgAddress(eventRemindPlan.getImgAddress());
                            remind.setType(eventRemindPlan.getType());
                            remind.setPlanId(eventRemindPlan.getId());
                            remind.setRemindTimeIndex(eventRemindPlan.getRemindTimeIndex());
                            remind.setRemindTime(timesInfo.getTime());
                            //每周两次 每周三次比较特殊，需要判断是周几
                            if (planFrequencyInfo.getFrequencyTypeIndex() == 6 || planFrequencyInfo.getFrequencyTypeIndex() == 7) {
                                int week = timesInfo.getWeek() + 1;
                                int queryTimeCalWeek = queryTimeCal.get(Calendar.DAY_OF_WEEK) - 1;
                                if (week == queryTimeCalWeek) {
                                    remindList.add(remind);
                                }
                            } else {
                                remindList.add(remind);
                            }


                        }
                    }

                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //2、查询状态表

        Collections.sort(remindList);
        //3、根据时间 排序
        return remindList;
    }


    @Override
    public EventRemindPlan queryEventRemindPlan(String planId) {
        return eventRemindDaoIml.queryEventRemindPlan(planId);
    }
}
