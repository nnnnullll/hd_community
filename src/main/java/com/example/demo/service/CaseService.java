package com.example.demo.service;
import com.example.demo.mapper.*;
import com.example.demo.enity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseService {
    @Autowired
    CaseMapper caseMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    HouseholdMapper householdMapper;
    @Autowired
    PartnerMapper partnerMapper;
    @Autowired
    CommunityMapper communityMapper;
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    ActivityMapper activityMapper;

    //插入case
    public Integer insertCase(String subject,String description,Integer type,Integer created_role,Integer created_by){
        //1-employee  2-Household 
        Case newcase = new Case();
        if(created_role == 1){
            Employee employee = new Employee();
            employee = employeeMapper.getEmployeeByNumber(created_by);
            newcase.setCompany(employee.getCompany());
            // newcase.setCommunity(community);
            // newcase.setHousehold(household);
        }else{
            Household household = new Household();
            household = householdMapper.getHouseholdByNumber(created_by);
            newcase.setCommunity(household.getCommunity());
            newcase.setCompany(communityMapper.getCommunityByNumber(household.getCommunity()).getCompany());
            newcase.setHousehold(household.getNumber());
        }
        newcase.setSubject(subject);
        newcase.setDescription(description);
        newcase.setType(type);
        newcase.setCreated_role(created_role);
        newcase.setCreated_by(created_by);
        caseMapper.insertCase(newcase);
        return newcase.getNumber();
    } 
    
    // 查casedetail 通过case number
    public CaseDetail getCaseByNumber(Integer number){
        Case casetemp = new Case();
        casetemp = caseMapper.getCaseByNumber(number);
        CaseDetail caseDetail = new CaseDetail();
        caseDetail.setNumber(casetemp.getNumber());//number
        KeyValueString keyValuecompany = new KeyValueString();//company
        keyValuecompany.setNumber(casetemp.getCompany());
        keyValuecompany.setName(companyMapper.getCompanyByNumber(casetemp.getCompany()).getName());
        caseDetail.setCompany(keyValuecompany);
        KeyValue kValuecommunity = new KeyValue();//community
        kValuecommunity.setNumber(casetemp.getCommunity());
        kValuecommunity.setName(communityMapper.getCommunityByNumber(casetemp.getCommunity()).getName());
        caseDetail.setCommunity(kValuecommunity);
        KeyValue kValuehousehold = new KeyValue();//household
        kValuehousehold.setNumber(casetemp.getHousehold());
        Integer buildInteger = householdMapper.getHouseholdByNumber(casetemp.getHousehold()).getBuilding();
        String RoomInteger = householdMapper.getHouseholdByNumber(casetemp.getHousehold()).getRoom_number();
        kValuehousehold.setName(buildInteger +'楼'+ RoomInteger + '室' );
        caseDetail.setHousehold(kValuehousehold);
        caseDetail.setSubject(casetemp.getSubject());//subject
        caseDetail.setDescription(casetemp.getDescription());//description
        caseDetail.setType(casetemp.getType());//type
        KeyValue keyValueassignedto = new KeyValue();//assigned to
        keyValueassignedto.setNumber(casetemp.getAssigned_to());
        if(casetemp.getAssigned_to()!= null)
            keyValueassignedto.setName(employeeMapper.getEmployeeByNumber(casetemp.getAssigned_to()).getName());
        caseDetail.setAssigned_to(keyValueassignedto);
        caseDetail.setState(casetemp.getState());//state
        KeyValue keyValuefixassignedto = new KeyValue();//fix_assigned to
        keyValuefixassignedto.setNumber(casetemp.getFix_assigned_to());
        if(casetemp.getFix_assigned_to()!= null)
            keyValuefixassignedto.setName(partnerMapper.getPartnerByNum(casetemp.getFix_assigned_to()).getName());
        caseDetail.setFix_assigned_to(keyValuefixassignedto);
        caseDetail.setSolution(casetemp.getSolution());//solution
        caseDetail.setEscalation(casetemp.getEscalation());//escalation
        caseDetail.setEmergency(casetemp.getEscalation());//Escation
        caseDetail.setCreated_role(casetemp.getCreated_role());//created_role
        caseDetail.setCreated(casetemp.getCreated());//created
        caseDetail.setCreated_by(casetemp.getCreated_by());//crated_by
        caseDetail.setUpdated(casetemp.getUpdated());//updated
        caseDetail.setUpdated_by(casetemp.getUpdated_by());//updated_by
        caseDetail.setUpdated_role(casetemp.getUpdated_role());//updated_role
        Activity[] activities = new Activity[activityMapper.getActivityAmountByCase_number(number)];
        activities = activityMapper.getActivityByCase_number(number);
        caseDetail.setActivities(activities);
        return caseDetail;
    }
    
    //查caselist（状态不是关闭的） 通过household number
    // 1-employee 2-Customer 3-partner
    public Case[] getCaseList(Integer number,Integer type){
        if(type == 1){
            return caseMapper.getCaseByEmployeeNumber(number);
        }else if(type == 2){
            return caseMapper.getCaseByHouseholdNumber(number);
        }else{
            return caseMapper.getCaseByPartnerNumber(number);
        } 
    }

    //查caselist（状态不是关闭的） 通过household number
    // 1-assigned to(new->in progress)
    public Integer updateCaseByNumber(Integer number,Integer type,Integer assigned_to){
        if(type == 1){
            return caseMapper.updateCaseFromNewToInProgree(number,assigned_to);
        }else{
            return 0;
        }
    }
}
