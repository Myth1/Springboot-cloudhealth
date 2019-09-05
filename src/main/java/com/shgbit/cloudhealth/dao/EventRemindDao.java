package com.shgbit.cloudhealth.dao;

import com.shgbit.cloudhealth.model.EventRemindPlan;

import java.util.List;

public interface EventRemindDao {
    void insertEventRemindPlan(EventRemindPlan eventRemind) ;

    List<EventRemindPlan> queryAllEventRemind(String loginId);
    EventRemindPlan queryEventRemindPlan(String planId);
    List<EventRemindPlan> queryEventRemindPlanByDate(String loginId);

    void deteleEventRemindPlan(String planId);

    void updateEventRemindPlan(EventRemindPlan eventRemindPlan);
}
