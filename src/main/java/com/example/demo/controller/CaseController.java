package com.example.demo.controller;
import com.example.demo.service.*;
import com.example.demo.enity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CaseController {
    @Autowired
    CaseService caseService;
    //插入case
    @PostMapping("/insertcase") 
    public Integer insertcase(
        @RequestParam(value = "subject") String subject,
        @RequestParam(value = "description") String description,
        @RequestParam(value = "type") Integer type,
        @RequestParam(value = "created_role") Integer created_role,
        @RequestParam(value = "created_by") Integer created_by,
        @RequestParam(value = "household") Integer household){;
        return caseService.insertCase(subject, description, type, created_role, created_by, household);
    }

    // 查case 通过case number
    @PostMapping("/getcasebynumber")
    public CaseDetail getCaseByNumber(
        @RequestParam(value = "number") Integer number,
        @RequestParam(value = "usertype") Integer type) {
        return caseService.getCaseByNumber(number,type);
    }
    @PostMapping("/getcommunityoptionbycompany")
    public option[] getCommunityOptionByCompany(
        @RequestParam(value = "company") String company) {
        return caseService.getCommunityOption(company);
    }
    @PostMapping("/gethouseholdoptionbycommunity")
    public option[] getHouseholdOptionByCommunity(
        @RequestParam(value = "community") Integer community) {
        return caseService.getHouseholdOption(community);
    }

    // 更新case 通过case number
    @PostMapping("/updatecasebynumber")
    public Integer updateCaseByNumber(
        @RequestParam(value = "number") Integer number,
        @RequestParam(value = "assigned_to") Integer assigned_to,
        @RequestParam(value = "message") String message,
        @RequestParam(value = "updateduser") Integer updateduser,
        @RequestParam(value = "type") Integer type) {
        return caseService.updateCaseByNumber(number,type,assigned_to,message,updateduser);
    }

    // 查caselist（状态不是关闭的） 通过household number
     // 1-employee 2-Customer 3-partner
    @PostMapping("/getcaselist")
    public Case[] getCaseList(
        @RequestParam(value = "type") Integer type,
        @RequestParam(value = "number") Integer number,
        @RequestParam(value = "company") String company) {
        return caseService.getCaseList(number,type,company);
    }
}
