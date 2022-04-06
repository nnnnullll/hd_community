package com.example.demo.mapper;
import com.example.demo.enity.Case;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CaseMapper { 
    // 插入case
    @Insert("insert into `case` (company,community,household,subject,description,type,created_role,created_by) values(#{case.company},#{case.community},#{case.household},#{case.subject},#{case.description},#{case.type},#{case.created_role},#{case.created_by})")
    @Options(useGeneratedKeys=true, keyProperty="case.number")
    Integer insertCase(@Param("case") Case newcase);
    // 查case 通过case number
    @Select("SELECT * FROM `case` where `case`.number=#{number}")
    Case getCaseByNumber(Integer number);
//caselist    
    //查caselist(状态不是关闭的) 通过household number
    @Select("SELECT * FROM `case` where `case`.household=#{number} order by number asc;")
    Case[] getCaseByHouseholdNumber(Integer number);
    //查caselist(状态不是关闭的) 通过household number
    @Select("SELECT * FROM `case` where `case`.assigned_to=#{number} order by number asc;")
    Case[] getCaseByEmployeeNumber(Integer number);
    //查caselist(状态不是关闭的) 通过household number
    @Select("SELECT * FROM `case` where `case`.fix_assigned_to=#{number} order by number asc;")
    Case[] getCaseByPartnerNumber(Integer number);
//case update
    @Update("update `case` set `case`.state=1,`case`.assigned_to=#{assigned_to} where `case`.number=#{number};")
    Integer updateCaseFromNewToInProgree(Integer number,Integer assigned_to);
    @Update("update `case` set `case`.state=2 where `case`.number=#{number};")
    Integer updateCaseFromInProgreeToAwaitingInfo(Integer number);
    @Update("update `case` set `case`.state=3,`case`.fix_state=2,`case`.fix_assigned_to=#{fix_assigned_to} where `case`.number=#{number};")
    Integer updateCaseFromInProgreeToInFix(Integer number,Integer fix_assigned_to);
    @Update("update `case` set `case`.state=4,`case`.solution=#{solution} where `case`.number=#{number};")
    Integer updateCaseFromToResolved(Integer number,String solution);
    @Update("update `case` set `case`.fix_state=3 where `case`.number=#{number};")
    Integer updateCaseFromFixassignedToInfix(Integer number);
    @Update("update `case` set `case`.fix_state=1 where `case`.number=#{number};")
    Integer updateCaseFromFixassignedToAwaitingfixAssigned(Integer number);
    @Update("update `case` set `case`.fix_state=4 where `case`.number=#{number};")
    Integer updateCaseFromFixassignedToFinishfix(Integer number);

//Employee Dash  
// Integer newnumber;
// Integer inprogressnumber;
// Integer escalationnumber;
// Integer emergencynumber;
// Integer resolvednumber;  
    //查In progress case amount 通过 Assignedto
    @Select("SELECT count(*) FROM `case` where `case`.assigned_to=#{number} and `case`.state=1;")
    Integer getInProgressCaseAmountByAssignedTo(Integer number);
    //查AwaitingInfo case amount 通过 Assignedto
    @Select("SELECT count(*) FROM `case` where `case`.assigned_to=#{number} and `case`.state=2;")
    Integer getAwaitingInfoCaseAmountByAssignedTo(Integer number);
    //查InFix case amount 通过 Assignedto
    @Select("SELECT count(*) FROM `case` where `case`.assigned_to=#{number} and `case`.state=3;")
    Integer getInFixCaseAmountByAssignedTo(Integer number);
    //查Resolved case amount 通过 Assignedto
    @Select("SELECT count(*) FROM `case` where `case`.assigned_to=#{number} and `case`.state=4;")
    Integer getResoledCaseAmountByAssignedTo(Integer number);
    //查Escalation case amount 通过 Assignedto
    @Select("SELECT count(*) FROM `case` where `case`.assigned_to=#{number} and `case`.escalation=1;")
    Integer getEscalationCaseAmountByAssignedTo(Integer number);
    //查Emergency case amount 通过 Assignedto
    @Select("SELECT count(*) FROM `case` where `case`.assigned_to=#{number} and `case`.emergency=1;")
    Integer getEmergencyCaseAmountByAssignedTo(Integer number);


    //查new case amount 通过 CompanyNumber
    @Select("SELECT count(*) FROM `case` where  `case`.company=#{number} and `case`.state=0;")
    Integer getNewCaseAmountByCompanyNumber(String number);
    //查In progress case amount 通过 CompanyNumber
    @Select("SELECT count(*) FROM `case` where `case`.company=#{number} and `case`.state=1;")
    Integer getInProgressCaseAmountByCompanyNumber(String number);
    //查AwaitingInfo case amount 通过 CompanyNumber
    @Select("SELECT count(*) FROM `case` where `case`.company=#{number} and `case`.state=2;")
    Integer getAwaitingInfoCaseAmountByCompanyNumber(String number);
    //查InFix case amount 通过 CompanyNumber
    @Select("SELECT count(*) FROM `case` where `case`.company=#{number} and `case`.state=3;")
    Integer getInFixCaseAmountByCompanyNumber(String number);
    //查Resolved case amount 通过 CompanyNumber
    @Select("SELECT count(*) FROM `case` where `case`.company=#{number} and `case`.state=4;")
    Integer getResoledCaseAmountByCompanyNumber(String number);
    //查Escalation case amount 通过 CompanyNumber
    @Select("SELECT count(*) FROM `case` where `case`.company=#{number} and `case`.escalation=1;")
    Integer getEscalationCaseAmountByCompanyNumber(String number);
    //查Emergency case amount 通过 CompanyNumber
    @Select("SELECT count(*) FROM `case` where `case`.company=#{number} and `case`.emergency=1;")
    Integer getEmergencyCaseAmountByCompanyNumber(String number);

    //查type1 case amount 通过 CompanyNumber
    @Select("SELECT count(*) FROM `case` where  `case`.company=#{number} and `case`.type=1;")
    Integer getT1CaseAmountByCompanyNumber(String number);
    //查type2 case amount 通过 CompanyNumber
    @Select("SELECT count(*) FROM `case` where `case`.company=#{number} and `case`.type=2;")
    Integer getT2CaseAmountByCompanyNumber(String number);
    //查type3 case amount 通过 CompanyNumber
    @Select("SELECT count(*) FROM `case` where `case`.company=#{number} and `case`.type=3;")
    Integer getT3CaseAmountByCompanyNumber(String number);
    //查type4 case amount 通过 CompanyNumber
    @Select("SELECT count(*) FROM `case` where `case`.company=#{number} and `case`.type=4;")
    Integer getT4CaseAmountByCompanyNumber(String number);
    //查type5 case amount 通过 CompanyNumber
    @Select("SELECT count(*) FROM `case` where `case`.company=#{number} and `case`.type=5;")
    Integer getT5CaseAmountByCompanyNumber(String number);

//Household Dash
// Integer newnumber;
// Integer inprogressnumber;
// Integer escalationnumber;
// Integer emergencynumber;
// Integer resolvednumber;
    //查New case amount 通过 household
    @Select("SELECT count(*) FROM `case` where `case`.household=#{number} and `case`.state=0;")
    Integer getNewCaseAmountByHousehold(Integer number);
    //查In progress case amount 通过 household
    @Select("SELECT count(*) FROM `case` where `case`.household=#{number} and `case`.state=1;")
    Integer getInProgressCaseAmountByHousehold(Integer number);
    //查AwaitingInfo case amount 通过 household
    @Select("SELECT count(*) FROM `case` where `case`.household=#{number} and `case`.state=2;")
    Integer getAwaitingInfoCaseAmountByHousehold(Integer number);
    //查InFix case amount 通过 household
    @Select("SELECT count(*) FROM `case` where `case`.household=#{number} and `case`.state=3;")
    Integer getInFixCaseAmountByHousehold(Integer number);
    //查Resolved case amount 通过 household
    @Select("SELECT count(*) FROM `case` where `case`.household=#{number} and `case`.state=4;")
    Integer getResoledCaseAmountByHousehold(Integer number);
    //查Escalation case amount 通过 household
    @Select("SELECT count(*) FROM `case` where `case`.household=#{number} and `case`.escalation=1;")
    Integer getEscalationCaseAmountByHousehold(Integer number);
    //查Emergency case amount 通过 household
    @Select("SELECT count(*) FROM `case` where `case`.household=#{number} and `case`.emergency=1;")
    Integer getEmergencyCaseAmountByHousehold(Integer number);


    //查new case amount 通过 Community
    @Select("SELECT count(*) FROM `case` where  `case`.community=#{number} and `case`.state=0;")
    Integer getNewCaseAmountByCommunity(Integer number);
    //查In progress case amount 通过 Community
    @Select("SELECT count(*) FROM `case` where `case`.community=#{number} and `case`.state=1;")
    Integer getInProgressCaseAmountByCommunity(Integer number);
    //查AwaitingInfo case amount 通过 Community
    @Select("SELECT count(*) FROM `case` where `case`.community=#{number} and `case`.state=2;")
    Integer getAwaitingInfoCaseAmountByCommunity(Integer number);
    //查InFix case amount 通过 Community
    @Select("SELECT count(*) FROM `case` where `case`.community=#{number} and `case`.state=3;")
    Integer getInFixCaseAmountByCommunity(Integer number);
    //查Resolved case amount 通过 Community
    @Select("SELECT count(*) FROM `case` where `case`.community=#{number} and `case`.state=4;")
    Integer getResoledCaseAmountByCommunity(Integer number);
    //查Escalation case amount 通过 community
    @Select("SELECT count(*) FROM `case` where `case`.community=#{number} and `case`.escalation=1;")
    Integer getEscalationCaseAmountByCommunity(Integer number);
    //查Emergency case amount 通过 community
    @Select("SELECT count(*) FROM `case` where `case`.community=#{number} and `case`.emergency=1;")
    Integer getEmergencyCaseAmountByCommunity(Integer number);

    //查type1 case amount 通过 community
    @Select("SELECT count(*) FROM `case` where  `case`.community=#{number} and `case`.type=1;")
    Integer getT1CaseAmountByCommunity(Integer number);
    //查type2 case amount 通过 community
    @Select("SELECT count(*) FROM `case` where `case`.community=#{number} and `case`.type=2;")
    Integer getT2CaseAmountByCommunity(Integer number);
    //查type3 case amount 通过 community
    @Select("SELECT count(*) FROM `case` where `case`.community=#{number} and `case`.type=3;")
    Integer getT3CaseAmountByCommunity(Integer number);
    //查type4 case amount 通过 community
    @Select("SELECT count(*) FROM `case` where `case`.community=#{number} and `case`.type=4;")
    Integer getT4CaseAmountByCommunity(Integer number);
    //查type5 case amount 通过 community
    @Select("SELECT count(*) FROM `case` where `case`.community=#{number} and `case`.type=5;")
    Integer getT5CaseAmountByCommunity(Integer number);
//partner
// Integer fixwaite_number;
// Integer fixassigned_number;
// Integer infix_number;
// Integer fixfinish_number;
// Integer fixclose_number;
//查New case amount 通过 partner
    @Select("SELECT count(*) FROM `case` where `case`.fix_assigned_to=#{number} and `case`.state=3 and `case`.fix_state=1;")
    Integer getFixwaite_numberCaseAmountByPartner(Integer number);
    //查In progress case amount 通过 household
    @Select("SELECT count(*) FROM `case` where `case`.fix_assigned_to=#{number} and `case`.state=3 and `case`.fix_state=2;")
    Integer getFixassigned_numberCaseAmountByPartner(Integer number);
    //查AwaitingInfo case amount 通过 household
    @Select("SELECT count(*) FROM `case` where `case`.fix_assigned_to=#{number} and `case`.state=3 and `case`.fix_state=3;")
    Integer getInfix_numberCaseAmountByPartner(Integer number);
    //查InFix case amount 通过 household
    @Select("SELECT count(*) FROM `case` where `case`.fix_assigned_to=#{number} and `case`.state=3 and `case`.fix_state=4;")
    Integer getFixfinish_numberCaseAmountByPartner(Integer number);
    //查Resolved case amount 通过 household
    @Select("SELECT count(*) FROM `case` where `case`.fix_assigned_to=#{number} and `case`.state=5;")
    Integer getFixclose_numberCaseAmountByPartner(Integer number);

     //查type1 case amount 通过 partner
     @Select("SELECT count(*) FROM `case` where  `case`.fix_assigned_to=#{number} and `case`.type=1;")
     Integer getT1CaseAmountByPartner(Integer number);
     //查type2 case amount 通过 partner
     @Select("SELECT count(*) FROM `case` where `case`.fix_assigned_to=#{number} and `case`.type=2;")
     Integer getT2CaseAmountByPartner(Integer number);
     //查type3 case amount 通过 partner
     @Select("SELECT count(*) FROM `case` where `case`.fix_assigned_to=#{number} and `case`.type=3;")
     Integer getT3CaseAmountByPartner(Integer number);
     //查type4 case amount 通过 partner
     @Select("SELECT count(*) FROM `case` where `case`.fix_assigned_to=#{number} and `case`.type=4;")
     Integer getT4CaseAmountByPartner(Integer number);
     //查type5 case amount 通过 partner
     @Select("SELECT count(*) FROM `case` where `case`.fix_assigned_to=#{number} and `case`.type=5;")
     Integer getT5CaseAmountByPartner(Integer number);
}
