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

    @PostMapping("/getcompanybynumber")
    public Company getCompany(
        @RequestParam(value = "number") String number){
        return companyService.getCompanyByNumbeInteger(number);
    }

    //type==1 partner通过relationship查维修公司合作的物业公司们
    @PostMapping("/getcompanies")
    public Company[] getCompanies(
        @RequestParam(value = "partner") Integer partner,
        @RequestParam(value = "type") Integer type){
        return companyService.getCompanies(partner, type);
    }

    //type=1 company
    //type=2 household
    //type=3 partner
    @PostMapping("/login")
    public Integer login(
        @RequestParam(value = "username") Integer username,
        @RequestParam(value = "password") String password,
        @RequestParam(value = "type") Integer type){
        return companyService.login(username,password,type);
    }
 
}
