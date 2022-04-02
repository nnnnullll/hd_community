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
   // @RequestParam(value = "type") Integer type,
    @RequestParam(value = "message") String message,
    @RequestParam(value = "updated_role") Integer updated_role,
    @RequestParam(value = "updated_by") Integer updated_by){
        return activityService.insertActivity(case_number, message, updated_role, updated_by);
    }
}
