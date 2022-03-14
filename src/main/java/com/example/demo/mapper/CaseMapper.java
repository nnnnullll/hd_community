package com.example.demo.mapper;
import com.example.demo.enity.Case;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CaseMapper { 
    // 插入case
    @Insert("insert into `case` (company,community,household,subject,description,type,created_role,created_by) values(#{case.company},#{case.community},#{case.household},#{case.subject},#{case.description},#{case.type},#{case.created_role},#{case.created_by})")
    @Options(useGeneratedKeys=true, keyProperty="case.number")
    Integer insertCase(@Param("case") Case newcase);
    // 查case 通过case number
    @Select("SELECT * FROM `case` where `case`.number=#{number}")
    Case getCaseByNumber(Integer number);
    //查caselist(状态不是关闭的) 通过household number
    @Select("SELECT * FROM `case` where `case`.household=#{householdnumber} order by number asc;")
    Case[] getCaseByHouseholdNumber(Integer householdnumber);

    //查new case amount 通过 Assignedto
    @Select("SELECT count(*) FROM `case` where `case`.state=0;")
    Integer getNewCaseAmountByAssignedTo(Integer number);
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
    @Select("SELECT count(*) FROM `case` where `case`.assigned_to=#{number} and `case`.escalation=0;")
    Integer getEscalationCaseAmountByAssignedTo(Integer number);
    //查Emergency case amount 通过 Assignedto
    @Select("SELECT count(*) FROM `case` where `case`.assigned_to=#{number} and `case`.emergency=0;")
    Integer getEmergencyCaseAmountByAssignedTo(Integer number);
}
