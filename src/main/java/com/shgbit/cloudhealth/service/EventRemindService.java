package com.shgbit.cloudhealth.service;

import com.shgbit.cloudhealth.model.EventRemindPlan;
import com.shgbit.cloudhealth.model.Remind;

import java.util.List;

public interface EventRemindService {
    void insertEventRemindPlan(EventRemindPlan eventRemind) throws CloneNotSupportedException;

    List<EventRemindPlan> queryAllEventRemind(String loginId);

    void deteleEventRemindPlan(String planId);

    void updateEventRemindPlan(EventRemindPlan eventRemindPlan);

    List<Remind> queryEventRemindByDate(String loginId, String time);

   EventRemindPlan queryEventRemindPlan(String planId);
}
