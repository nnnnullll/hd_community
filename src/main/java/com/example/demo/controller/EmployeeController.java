package com.example.demo.controller;
import com.example.demo.enity.Employee;
import com.example.demo.enity.LoginDashboard;
import com.example.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    HouseholdService householdService;
    @Autowired
    PartnerService partnerService;
    //插employee
    @PostMapping("/insertemployee")
    public Integer insertemployee(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "company") String company,
        @RequestParam(value = "id") String id,
        @RequestParam(value = "phone") String phone,
        @RequestParam(value = "email") String email,
        @RequestParam(value = "admin") Integer admin){
        return employeeService.insertEmployee(name, company, id, phone, email, admin);
    }

    @PostMapping("/updateemployee")
    public Integer updateemployee(
        @RequestParam(value = "number") Integer number,
        @RequestParam(value = "phone") String phone,
        @RequestParam(value = "email") String email,
        @RequestParam(value = "type") Integer type){
        return employeeService.updateEmployee(number, phone, email, type);
    }

    //type=1 查employee 通过number(唯一一条记录)
    //type=2 查employee 通过company number(多条记录)
    @PostMapping("/getemployee")
    public Employee[] getemployee(
        @RequestParam(value = "type") Integer type,
        @RequestParam(value = "number") Integer number,
        @RequestParam(value = "company") String company){
        return employeeService.getEmployee(type, number, company);
    }

    //type=1 查employeedashboard
    //type=2 查householddashboard
    @PostMapping("/getlogindash")
    public LoginDashboard getEmployeeDash(
        @RequestParam(value = "number") Integer number,
        @RequestParam(value = "type") Integer type){
            if(type== 1){
                return employeeService.getEmployeeDash(number);
            }
            else if(type== 2){
                return householdService.getHouseholdDash(number);
            }else{
                return partnerService.getPartnerDash(number);
            }
        
    }
} 
