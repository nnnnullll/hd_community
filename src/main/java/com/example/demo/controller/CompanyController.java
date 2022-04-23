package com.example.demo.controller;

import com.example.demo.enity.Company;
import com.example.demo.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class CompanyController {
    @Autowired
    CompanyService companyService;
    //插company
    @PostMapping("/insertcompany")
    public Integer insertCompany(
        @RequestParam(value = "number") String number,
        @RequestParam(value = "name") String name,
        @RequestParam(value = "address") String address,
        @RequestParam(value = "phone") String phone,
        @RequestParam(value = "email") String email){
        return companyService.insertCompany(number, name, address, phone, email);
    }

    //插company
    @PostMapping("/updatecompany")
    public Integer updateCompany(
        @RequestParam(value = "number") String number,
        @RequestParam(value = "address") String address,
        @RequestParam(value = "phone") String phone,
        @RequestParam(value = "email") String email){
        return companyService.updateCompany(number,address, phone, email);
    }

    //type==1 通过number找company
    //type==2 partner通过relationship查维修公司合作的物业公司们
    @PostMapping("/getcompanies")
    public Company[] getCompanies(
        @RequestParam(value = "partner") Integer partner,
        @RequestParam(value = "type") Integer type,
        @RequestParam(value = "number") String number){
        return companyService.getCompanies(partner, type, number);
    }

    // type=1 employee  type=2 household  type=3 partner
    // type=1  return 0-no admin 1-admin 2-失败
    // type=2/3 return 1-成功 2-失败
    @PostMapping("/login")
    public Integer login(
        @RequestParam(value = "username") Integer username,
        @RequestParam(value = "password") String password,
        @RequestParam(value = "type") Integer type){
        return companyService.login(username,password,type);
    }
 
}
