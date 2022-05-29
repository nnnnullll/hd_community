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
    public Integer insertCase(String subject,String description,Integer type,Integer created_role,Integer created_by, Integer householdnumber){
        //1-employee  2-Household 
        Case newcase = new Case();
        if(created_role == 1){
            Employee employee = new Employee();
            employee = employeeMapper.getEmployeeByNumber(created_by);
            newcase.setCompany(employee.getCompany());
            Household household1 = new Household();
            household1 = householdMapper.getHouseholdByNumber(householdnumber);
            newcase.setCommunity(household1.getCommunity());
            newcase.setCompany(communityMapper.getCommunityByNumber(household1.getCommunity()).getCompany());
            newcase.setHousehold(household1.getNumber());
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

    public option[] getCommunityOption(String company){
        return communityMapper.getCommunityOption(company);
    }  
    public option[] getHouseholdOption(Integer community){
        return householdMapper.getHouseholdOption(community);
    }    
    // 查casedetail 通过case number
    public CaseDetail getCaseByNumber(Integer number,Integer usertype){
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
        kValuehousehold.setName(Integer.toString(buildInteger) +"楼"+ RoomInteger + "室" );
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
        caseDetail.setFix_state(casetemp.getFix_state());
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
        Activity[] activities = new Activity[activityMapper.getActivityAmountByCase_number(number)];
        activities = activityMapper.getActivityByCase_number(number);
        caseDetail.setActivities(activities);
        if(usertype==1){
            if(casetemp.getType()==1){
                caseDetail.setOptions_fix(partnerMapper.getFixOneOption(casetemp.getCompany()));
            }else if(casetemp.getType()==2){
                caseDetail.setOptions_fix(partnerMapper.getFixTwoOption(casetemp.getCompany()));
            }else if(casetemp.getType()==3){
                caseDetail.setOptions_fix(partnerMapper.getFixThreeOption(casetemp.getCompany()));
            }else if(casetemp.getType()==4){
                caseDetail.setOptions_fix(partnerMapper.getFixFourOption(casetemp.getCompany()));
            }else if(casetemp.getType()==5){
                caseDetail.setOptions_fix(partnerMapper.getFixFiveOption(casetemp.getCompany()));
            }
            caseDetail.setOptions_agent(employeeMapper.getAgentOption(casetemp.getCompany()));
        }
        return caseDetail;
    }
    
    //查caselist（状态不是关闭的） 通过household number
    // 1-employee 4-all case  5-escalarion & emergency(admin) 6-closecase-e 9-new case
    // 2-Customer 7-closecase-c
    // 3-partner  8-closecase-h
    public Case[] getCaseList(Integer number,Integer type, String company){
        if(type == 1){
            Integer n = caseMapper.getAmountCaseByEmployeeNumber(number);
            Case[] caset = new Case[n];
            caset = caseMapper.getCaseByEmployeeNumber(number);
            for(Integer i =0 ; i<n ; i++){
                caset[i].setCommunity_n(communityMapper.getCommunityByNumber(caset[i].getCommunity()).getName());
                caset[i].setCompany_n(companyMapper.getCompanyByNumber(caset[i].getCompany()).getName());
                if(caset[i].getAssigned_to()!=null)
                    caset[i].setAssigned_to_n(employeeMapper.getEmployeeByNumber(caset[i].getAssigned_to()).getName());
                if(caset[i].getFix_assigned_to()!=null)
                    caset[i].setFix_assigned_to_n(partnerMapper.getPartnerByNum(caset[i].getFix_assigned_to()).getName());
            }
            return caset;
        }else if(type == 2){
            Integer n = caseMapper.getAmountCaseByHouseholdNumber(number);
            Case[] caset = new Case[n];
            caset = caseMapper.getCaseByHouseholdNumber(number);
            for(Integer i =0 ; i<n ; i++){
                caset[i].setCommunity_n(communityMapper.getCommunityByNumber(caset[i].getCommunity()).getName());
                caset[i].setCompany_n(companyMapper.getCompanyByNumber(caset[i].getCompany()).getName());
                if(caset[i].getAssigned_to()!=null)
                    caset[i].setAssigned_to_n(employeeMapper.getEmployeeByNumber(caset[i].getAssigned_to()).getName());
                if(caset[i].getFix_assigned_to()!=null)
                    caset[i].setFix_assigned_to_n(partnerMapper.getPartnerByNum(caset[i].getFix_assigned_to()).getName());
            }
            return caset;
        }else if(type==3){
            Integer n = caseMapper.getAmountCaseByPartnerNumber(number);
            Case[] caset = new Case[n];
            caset = caseMapper.getCaseByPartnerNumber(number);
            for(Integer i =0 ; i<n ; i++){
                caset[i].setCommunity_n(communityMapper.getCommunityByNumber(caset[i].getCommunity()).getName());
                caset[i].setCompany_n(companyMapper.getCompanyByNumber(caset[i].getCompany()).getName());
                if(caset[i].getAssigned_to()!=null)
                    caset[i].setAssigned_to_n(employeeMapper.getEmployeeByNumber(caset[i].getAssigned_to()).getName());
                if(caset[i].getFix_assigned_to()!=null)
                    caset[i].setFix_assigned_to_n(partnerMapper.getPartnerByNum(caset[i].getFix_assigned_to()).getName());
            }
            return caset;
        }else if(type==4){
            Integer n = caseMapper.getCaseAmountByCompanyNumber(company);
            Case[] caset = new Case[n];
            caset = caseMapper.getCaseByCompanyNumber(company);
            for(Integer i =0 ; i<n ; i++){
                caset[i].setCommunity_n(communityMapper.getCommunityByNumber(caset[i].getCommunity()).getName());
                if(caset[i].getAssigned_to()!=null)
                    caset[i].setAssigned_to_n(employeeMapper.getEmployeeByNumber(caset[i].getAssigned_to()).getName());
                if(caset[i].getFix_assigned_to()!=null)
                    caset[i].setFix_assigned_to_n(partnerMapper.getPartnerByNum(caset[i].getFix_assigned_to()).getName());
            }
            return caset;
        }else if(type==5){
            Integer n = caseMapper.getAmountEmergencyAndEscalationCaseByCompanyNumber(company);
            Case[] caset = new Case[n];
            caset = caseMapper.getEmergencyAndEscalationCaseByCompanyNumber(company);
            for(Integer i =0 ; i<n ; i++){
                caset[i].setCommunity_n(communityMapper.getCommunityByNumber(caset[i].getCommunity()).getName());
                caset[i].setCompany_n(companyMapper.getCompanyByNumber(caset[i].getCompany()).getName());
                if(caset[i].getAssigned_to()!=null)
                    caset[i].setAssigned_to_n(employeeMapper.getEmployeeByNumber(caset[i].getAssigned_to()).getName());
                if(caset[i].getFix_assigned_to()!=null)
                    caset[i].setFix_assigned_to_n(partnerMapper.getPartnerByNum(caset[i].getFix_assigned_to()).getName());
            }
            return caset;
        }else if(type==6){
            Integer n = caseMapper.getCloseCaseAmountByEmployeeNumber(number);
            Case[] caset = new Case[n];
            caset = caseMapper.getCloseCaseByEmployeeNumber(number);
            for(Integer i =0 ; i<n ; i++){
                caset[i].setCommunity_n(communityMapper.getCommunityByNumber(caset[i].getCommunity()).getName());
                caset[i].setCompany_n(companyMapper.getCompanyByNumber(caset[i].getCompany()).getName());
                if(caset[i].getAssigned_to()!=null)
                    caset[i].setAssigned_to_n(employeeMapper.getEmployeeByNumber(caset[i].getAssigned_to()).getName());
                if(caset[i].getFix_assigned_to()!=null)
                    caset[i].setFix_assigned_to_n(partnerMapper.getPartnerByNum(caset[i].getFix_assigned_to()).getName());
            }
            return caset;
        }else if(type==7){
            Integer n = caseMapper.getCloseCaseAmountByHouseholdNumber(number);
            Case[] caset = new Case[n];
            caset = caseMapper.getCloseCaseByHouseholdNumber(number);
            for(Integer i =0 ; i<n ; i++){
                caset[i].setCommunity_n(communityMapper.getCommunityByNumber(caset[i].getCommunity()).getName());
                caset[i].setCompany_n(companyMapper.getCompanyByNumber(caset[i].getCompany()).getName());
                if(caset[i].getAssigned_to()!=null)
                    caset[i].setAssigned_to_n(employeeMapper.getEmployeeByNumber(caset[i].getAssigned_to()).getName());
                if(caset[i].getFix_assigned_to()!=null)
                    caset[i].setFix_assigned_to_n(partnerMapper.getPartnerByNum(caset[i].getFix_assigned_to()).getName());
            }
            return caset;
        }else if(type==8){
            Integer n = caseMapper.getCloseCaseAmountByPartnerNumber(number);;
            Case[] caset = new Case[n];
            caset = caseMapper.getCloseCaseByPartnerNumber(number);
            for(Integer i =0 ; i<n ; i++){
                caset[i].setCommunity_n(communityMapper.getCommunityByNumber(caset[i].getCommunity()).getName());
                caset[i].setCompany_n(companyMapper.getCompanyByNumber(caset[i].getCompany()).getName());
                if(caset[i].getAssigned_to()!=null)
                    caset[i].setAssigned_to_n(employeeMapper.getEmployeeByNumber(caset[i].getAssigned_to()).getName());
                if(caset[i].getFix_assigned_to()!=null)
                    caset[i].setFix_assigned_to_n(partnerMapper.getPartnerByNum(caset[i].getFix_assigned_to()).getName());
            }
            return caset;
        }else{
            Integer n = caseMapper.getAmountNewCaseByCompanyNumber(company);;
            Case[] caset = new Case[n];
            caset = caseMapper.getNewCaseByCompanyNumber(company);
            for(Integer i =0 ; i<n ; i++){
                caset[i].setCommunity_n(communityMapper.getCommunityByNumber(caset[i].getCommunity()).getName());
                caset[i].setCompany_n(companyMapper.getCompanyByNumber(caset[i].getCompany()).getName());
                if(caset[i].getAssigned_to()!=null)
                    caset[i].setAssigned_to_n(employeeMapper.getEmployeeByNumber(caset[i].getAssigned_to()).getName());
                if(caset[i].getFix_assigned_to()!=null)
                    caset[i].setFix_assigned_to_n(partnerMapper.getPartnerByNum(caset[i].getFix_assigned_to()).getName());
            }
            return caset;
        }
    }

    //update case  state  button list
    public Integer updateCaseByNumber(Integer number,Integer type,Integer assigned_to,String message,Integer updateduser){
        Case casetemp = new Case();
        casetemp = caseMapper.getCaseByNumber(number);
        // 1-employee方
        if(type==1||type==2||type==3||type==4||type==8){
            Employee employee = new Employee();
            employee = employeeMapper.getEmployeeByNumber(updateduser);
            // type=1-assigned to(new->in progress)
            if(type == 1&&caseMapper.validateCaseisNew(number)==1){
                Employee emp = new Employee();
                emp = employeeMapper.getEmployeeByNumber(assigned_to);
                if(casetemp.getState()==0){
                    if(caseMapper.updateCaseFromNewToInProgree(number,assigned_to)==1){
                        return activityMapper.insertActivity(number, "投诉单分配给员工: "+ emp.getName() +"; 状态：受理中 ← 新建" , employee.getName(), 1 , employee.getNumber());
                    }else{
                        return 0;
                    }
                }else{
                    if(caseMapper.updateCaseAssignedTo(number,assigned_to)==1){
                        return activityMapper.insertActivity(number, "投诉单分配给员工: "+ emp.getName()  , employee.getName(), 1 , employee.getNumber());
                    }else{
                        return 0;
                    }
                }
            }
            // type=2-awaiting info(in progress->awaiting info)
            else if(type == 2&&caseMapper.validateCaseisInProgress(number)==1){
                if(caseMapper.updateCaseFromInProgreeToAwaitingInfo(number)==1){
                    return activityMapper.insertActivity(number, "状态：待补充 ← 受理中；留言：" + message , employee.getName(), 1 , employee.getNumber());
                }else{
                    return 0;
                }
            }
            // type=3 维修中 state: in progress->in fix, fix_state:已分配 ← 空/待分配/已解决; casenumber&type&message&updated_usernumber
            //fix_state: 0-default(when state=3) 1-待分配 2-已分配 3-维修中 4-已解决
            else if(type == 3&&caseMapper.validateCaseisInProgress(number)==1){
                if(caseMapper.updateCaseFromInProgreeToInFix(number, assigned_to)==1){
                    partner partner = new partner();
                    partner = partnerMapper.getPartnerByNum(assigned_to);
                    if(casetemp.getFix_state()==null){
                        return activityMapper.insertActivity(number, "状态：维修中 ← 受理中；维修状态：已分配 ← 空；维修方：" + partner.getName() , employee.getName(), 1 , employee.getNumber());
                    }else if(casetemp.getFix_state()==1){
                        return activityMapper.insertActivity(number, "维修状态：已分配 ← 待分配；维修方：" + partner.getName() , employee.getName(), 1 , employee.getNumber());
                    }
                    else if(casetemp.getFix_state()==4){
                        return activityMapper.insertActivity(number, "维修状态：已分配 ← 已解决；维修方：" + partner.getName() , employee.getName(), 1 , employee.getNumber());
                    }else{
                        return 0;
                    }
                }else{
                    return 0;
                }
            }
            // type=4 提供解决方案 resolved; in progress/in fix->resolved
            else if(type == 4&&(caseMapper.validateCaseisInFix(number)==1||caseMapper.validateCaseisInProgress(number)==1)){
                if(caseMapper.updateCaseFromToResolved(number, message)==1){
                    if(casetemp.getState()==1){
                        return activityMapper.insertActivity(number, "状态：已解决 ← 受理中；解决方案：" + message, employee.getName(), 1 , employee.getNumber());
                    }else if(casetemp.getState()==3){
                        return activityMapper.insertActivity(number, "状态：已解决 ← 维修中；解决方案：" + message , employee.getName(), 1 , employee.getNumber());
                    }else{
                        return 0;
                    }
                }else{
                    return 0;
                }
            }
            // type=8 留言
            else if(type == 8&&caseMapper.validateCaseisClosed(number)==0){
                return activityMapper.insertActivity(number, "留言：" + message, employee.getName(), 1 , employee.getNumber());
            }
            // type=11 留言
            else{
                return 10;
            }
        //3- partner
        }else if(type==5||type==6||type==7||type==10){
            partner partner = new partner();
            partner = partnerMapper.getPartnerByNum(updateduser);
            // type=5 接收维修单 维修状态：维修中 ← 已分配
            if(type==5&&caseMapper.validateCaseisInFix(number)==1){
                if(caseMapper.updateCaseFromFixassignedToInfix(number)==1){
                    return activityMapper.insertActivity(number, "接受该维修任务。维修状态：维修中 ← 已分配" , partner.getName(), 3, partner.getNum());
                }else{
                    return 0;
                }
            }
            // type=6 拒绝维修单 维修状态：待分配 ← 已分配
            else if(type==6&&caseMapper.validateCaseisInFix(number)==1){
                if(caseMapper.updateCaseFromFixassignedToAwaitingfixAssigned(number)==1){
                    return activityMapper.insertActivity(number, "拒绝该维修任务。维修状态：待分配 ← 已分配" , partner.getName(), 3, partner.getNum());
                }else{
                    return 0;
                }
            }
            //  维修结束 维修状态：已解决 ← 维修中
            else if(type==7&&caseMapper.validateCaseisInFix(number)==1){
                if(caseMapper.updateCaseFromFixassignedToFinishfix(number)==1){
                    return activityMapper.insertActivity(number, "解决该维修任务。状态：受理中 ← 维修中;维修状态：已解决 ← 维修中; 留言: "+ message , partner.getName(), 3, partner.getNum());
                }else{
                    return 0;
                }
            }
            // type=8 留言
            else if(type == 10&&caseMapper.validateCaseisClosed(number)==0){
                return activityMapper.insertActivity(number, "留言：" + message, partner.getName(), 3 , partner.getNum());
            }
            else{
                return 10;
            }
        }
        // 2-household
        else{
            Household household = new Household();
            household = householdMapper.getHouseholdByNumber(updateduser);
            if(type==9&&caseMapper.validateCaseisClosed(number)==0){
                // 待补充 状态：受理中 ← 待补充 ；留言：xxxx
                if(casetemp.getState()==2){    
                    if(caseMapper.updateCaseToInProgree(number)==1){
                        return activityMapper.insertActivity(number, "状态：受理中 ← 待补充; 留言: " + message, household.getBuilding()+"楼"+household.getRoom_number()+"室住户", 2, household.getNumber());
                    }else{
                        return 0;
                    }
                }
                //  状态：受理中 ← 已解决 ；留言：xxxx
                else if(casetemp.getState()== 4){    
                    if(caseMapper.updateCaseToInProgree(number)==1){
                        return activityMapper.insertActivity(number, "状态：受理中 ← 已解决; 留言: " + message, household.getBuilding()+"楼"+household.getRoom_number()+"室住户", 2, household.getNumber());
                    }else{
                        return 0;
                    }
                // 留言
                }else{
                    return activityMapper.insertActivity(number, "留言: " + message, household.getBuilding()+"楼"+household.getRoom_number()+"室住户", 2, household.getNumber());
                }
            }else if(type == 11&&caseMapper.validateCaseisClosed(number)==0){
                if(caseMapper.getFixStateByNumber(number) == null){
                    if(caseMapper.updateCaseFromClosed(number)==1){
                        return activityMapper.insertActivity(number, "住户关闭该投诉单", household.getBuilding()+"楼"+household.getRoom_number()+"室住户", 3, household.getNumber());
                    }else{
                        return 0;
                    }
                }else{
                    if(caseMapper.updateCaseFromClosedAndFixClosed(number)==1){
                        return activityMapper.insertActivity(number, "住户关闭该投诉单", household.getBuilding()+"楼"+household.getRoom_number()+"室住户", 3, household.getNumber());
                    }else{
                        return 0;
                    }
                }
            }else{
                return 10;
            } 
        }       
    }
}
