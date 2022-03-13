package com.example.demo.service;

import com.example.demo.enity.Company;
import com.example.demo.mapper.CompanyMapper;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.mapper.HouseholdMapper;
import com.example.demo.mapper.PartnerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.conditional.ElseAction;

@Service
public class CompanyService {
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    HouseholdMapper householdMapper;
    @Autowired
    PartnerMapper partnerMapper;
    //插company
    public Integer insertCompany(String number,String name,String address,String phone, String email){
        return companyMapper.insertCompany(number, name, address, phone, email);
    }
    //查company by number
    public Company getCompanyByNumbeInteger(String number){
        return companyMapper.getCompanyByNumber(number);
    }

    //type=1 employee
    //type=2 household
    //type=3 partner
    public Integer login(Integer username, String password,Integer type){
        if (type==1){
            if(employeeMapper.validateEmployeeByPassword(username, password)==1)
                return 1;
            else
                return 0;   
        }else if(type==2){
            if(householdMapper.validateHouseholdByPassword(username, password)==1)
                return 1;
            else
                return 0;
        }else if(type ==3){
            return partnerMapper.validatePartnerByPassword(username, password);
        }else{
            return 0;
        }  
    }
} 
