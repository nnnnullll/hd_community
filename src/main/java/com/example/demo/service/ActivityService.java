package com.example.demo.service;
import com.example.demo.enity.Activity;
import com.example.demo.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    @Autowired
    ActivityMapper activityMapper;
    //插入Activity
    public Integer insertActivity(Integer case_number,Integer type,String message,String updated_name, Integer updated_role,Integer updated_by){
        return activityMapper.insertActivity(case_number, type, message,updated_name, updated_role, updated_by);
    }

    //查activity 通过case number 按照时间排序
    public Activity[] getActivityByCase_number(Integer case_number){
        return activityMapper.getActivityByCase_number(case_number);
    }
}
