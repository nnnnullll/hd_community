package com.example.demo.service;

import com.example.demo.enity.Company;
import com.example.demo.mapper.*;
import com.example.demo.mapper.PartnerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    CommunityMapper communityMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    HouseholdMapper householdMapper;
    @Autowired
    PartnerMapper partnerMapper;
    @Autowired
    RelationshipMapper relationshipMapper;
    //插company
    public Integer insertCompany(String number,String name,String address,String phone, String email){
        return companyMapper.insertCompany(number, name, address, phone, email);
    }

    //插company
    public Integer updateCompany(String number,String address,String phone, String email){
        System.out.println(address);
        System.out.println(phone);
        System.out.println(email);
        return companyMapper.updateCompany(number,address, phone, email);
    }
   
    //查company by partner
    public Company[] getCompanies(Integer partner,Integer type , String number){
        if( type == 1 ){
            Company[] companies = new Company[1];
            companies[0] = companyMapper.getCompanyByNumber(number); 
            return companies;
        }else if(type == 2 ){
            Integer amount = relationshipMapper.getActiveCompanyAmountFromRelationshipByPartner(partner);
            String[] companiesnumber =new String[amount];
            companiesnumber = relationshipMapper.getActiveCompanyNumberFromRelationshipByPartner(partner);
            Company[] companies = new Company[amount];
            for(Integer i=0;i<amount;i++){
                companies[i]=companyMapper.getCompanyByNumber(companiesnumber[i]);
            }
            return companies;
        }else{
            return companyMapper.getNotPartnerCompany(partner);
        }
        
    }

    // type=1 employee  type=2 household  type=3 partner
    // type=1  return 0-no admin 1-admin 2-失败 3-已离职
    // type=2 return 1-成功 2-失败 3-停用
    // type=3 return 1-成功 2-失败
    public Integer login(Integer username, String password,Integer type){
        if (type==1){
            if(employeeMapper.getCountEmployeeByNumber(username)==1){
                if(employeeMapper.getEmployeeActiveByNumber(username) == 1 ){
                    return 3;
                }else{
                    if(employeeMapper.validateEmployeeByPassword(username, password)==1)
                        return employeeMapper.getEmployeeAdminByNumber(username);
                    else
                        return 2; 
                }  
            }else{
                return 4;
            }
        }else if(type==2){
            if(householdMapper.getCountHouseholdByNumber(username)==1){
                if(communityMapper.getActiveFromCommunityByNumber(householdMapper.getCommunityByNumber(username))==1){
                    return 3;
                }else{
                    if(householdMapper.validateHouseholdByPassword(username, password)==1)
                        return 1;
                    else
                        return 2;
                } 
            }else{
                return 4;
            }
        }else if(type ==3){
            if(partnerMapper.getCountPartnerByNum(username)==1){
                if(partnerMapper.validatePartnerByPassword(username, password) == 1)
                    return 1;
                else
                    return 2;
            }else{
                return 4;
            } 
        }else{
            return 2;
        }  
    }
} 
