package com.example.demo.controller;
import com.example.demo.enity.Activity;
import com.example.demo.service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ActivityController {
    @Autowired
    ActivityService activityService;
    //插activity
    @PostMapping("/insertactivity")
    public Integer insertCompany(
    @RequestParam(value = "case_number") Integer case_number,
    @RequestParam(value = "type") Integer type,
    @RequestParam(value = "message") String message,
    @RequestParam(value = "updated_name") String updated_name,
    @RequestParam(value = "updated_role") Integer updated_role,
    @RequestParam(value = "updated_by") Integer updated_by){
        return activityService.insertActivity(case_number, type, message, updated_name, updated_role, updated_by);
    }

    // 查activity 通过case_number时间排序
    @PostMapping("/getactivitybycase_number")
    public Activity[] getnotet(
        @RequestParam(value = "case_number") Integer case_number) {
        return activityService.getActivityByCase_number(case_number);
    }
}
