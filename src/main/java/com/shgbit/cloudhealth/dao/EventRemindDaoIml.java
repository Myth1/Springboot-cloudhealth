package com.shgbit.cloudhealth.dao;

import com.shgbit.cloudhealth.model.EventRemindPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventRemindDaoIml implements EventRemindDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertEventRemindPlan(EventRemindPlan eventRemind) {
        mongoTemplate.save(eventRemind);
    }

    @Override
    public List<EventRemindPlan> queryAllEventRemind(String loginId) {
        Query query = new Query(Criteria.where("loginId").is(loginId));
        List<EventRemindPlan> eventReminds = mongoTemplate.find(query, EventRemindPlan.class);
        return eventReminds;
    }

    @Override
    public EventRemindPlan queryEventRemindPlan(String planId) {
      /*  Date date = DateUtils.getDate(time);
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarStart.setTime(date);
        calendarEnd.setTime(date);
        Query query = new Query(Criteria.where("loginId").is(loginId));
        calendarEnd.add(Calendar.HOUR, 24);
        Sort sort = new Sort(Sort.Direction.ASC, "time");
        query.addCriteria(Criteria.where("time").gte(calendarStart.getTime()).lt(calendarEnd.getTime())).with(sort);
        List<EventRemindPlan> eventRemindPlans = mongoTemplate.find(query, EventRemindPlan.class);*/
        Query query = new Query(Criteria.where("id").is(planId));
        EventRemindPlan eventRemindPlans = mongoTemplate.findOne(query, EventRemindPlan.class);
        return eventRemindPlans;
    }

    @Override
    public List<EventRemindPlan> queryEventRemindPlanByDate(String loginId) {
        Query query = new Query(Criteria.where("loginId").is(loginId));
        List<EventRemindPlan> eventRemindPlans = mongoTemplate.find(query, EventRemindPlan.class);
        return eventRemindPlans;
    }

    @Override
    public void deteleEventRemindPlan(String planId) {
        Query query = new Query(Criteria.where("id").is(planId));
        mongoTemplate.remove(query, EventRemindPlan.class);
    }

    @Override
    public void updateEventRemindPlan(EventRemindPlan eventRemindPlan) {
        Query query = new Query(Criteria.where("id").is(eventRemindPlan.getId()));
        Update update = new Update()
                .set("startTime", eventRemindPlan.getStartTime())
                .set("endTime", eventRemindPlan.getEndTime())
                .set("remindTimeIndex", eventRemindPlan.getRemindTimeIndex())
                .set("planFrequencyInfo", eventRemindPlan.getPlanFrequencyInfo())
                .set("content", eventRemindPlan.getContent())
                .set("type", eventRemindPlan.getType())
                .set("planStatus", eventRemindPlan.getPlanStatus())
                .set("imgAddress", eventRemindPlan.getImgAddress())
                .set("from", eventRemindPlan.getFrom())
                .set("updateDate", new Date());

        mongoTemplate.updateFirst(query, update, EventRemindPlan.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);

    }
}
