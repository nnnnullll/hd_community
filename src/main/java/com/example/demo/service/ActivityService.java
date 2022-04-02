package com.example.demo.service;
import com.example.demo.enity.Activity;
import com.example.demo.mapper.ActivityMapper;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.mapper.HouseholdMapper;
import com.example.demo.mapper.PartnerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    HouseholdMapper householdMapper;
    @Autowired
    PartnerMapper partnerMapper;
    //插入Activity
    public Integer insertActivity(Integer case_number,String message, Integer updated_role,Integer updated_by){
        //2-Customer 1-employee 3-partner
        String updated_name;
        if(updated_role == 1){
            updated_name = employeeMapper.getEmployeeByNumber(updated_by).getName();
        }else if(updated_role == 2){
            updated_name = householdMapper.getHouseholdByNumber(updated_by).getBuilding() + "楼" + householdMapper.getHouseholdByNumber(updated_by).getRoom_number() + "室住户";
        }else{
            updated_name = partnerMapper.getPartnerByNum(updated_by).getName();
        }
        return activityMapper.insertActivity(case_number, message, updated_name, updated_role, updated_by);
    }

    //查activity 通过case number 按照时间排序
    public Activity[] getActivityByCase_number(Integer case_number){
        return activityMapper.getActivityByCase_number(case_number);
    }
}
