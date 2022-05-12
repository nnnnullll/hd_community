package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RelationshipMapper {
    //插relationship
    @Insert("INSERT INTO `relationship`(company,partner) VALUES(#{company},#{partner})")
    Integer InsertRelationship(@Param("company") String company,@Param("partner") Integer partner);

    //查ative
    @Select("SELECT count(*)  FROM `relationship` where `relationship`.partner=#{partner} and `relationship`.company=#{company} and `relationship`.active=0")
    Integer getActiveByPartnerAndCompany(@Param("partner") Integer partner,@Param("company") String company);
    //查amount
    @Select("SELECT count(*)  FROM `relationship` where `relationship`.partner=#{partner} and `relationship`.company=#{company}")
    Integer getAmountByPartnerAndCompany(@Param("partner") Integer partner,@Param("company") String company);
    @Update("update `relationship` set `relationship`.active=0 where `relationship`.partner=#{partner} and `relationship`.company=#{company}")
    Integer updateActiveByPartnerAndCompany(@Param("partner") Integer partner,@Param("company") String company);
    @Update("update `relationship` set `relationship`.active=1 where `relationship`.partner=#{partner} and `relationship`.company=#{company}")
    Integer updateInActiveByPartnerAndCompany(@Param("partner") Integer partner,@Param("company") String company);
    @Update("update `relationship` set `relationship`.active=1 where `relationship`.partner=#{partner}")
    Integer updateInActiveByPartner(@Param("partner") Integer partner);
 

    // partner看合作的物业公司
    @Select("SELECT count(distinct company) FROM `relationship` where `relationship`.partner=#{partner} and `relationship`.active=0")
    Integer getActiveCompanyAmountFromRelationshipByPartner(@Param("partner") Integer partner);
    @Select("SELECT distinct company FROM `relationship` where `relationship`.partner=#{partner}  and `relationship`.active=0")
    String[] getActiveCompanyNumberFromRelationshipByPartner(@Param("partner") Integer partner);


    //company看合作的维修公司
    @Select("SELECT distinct partner FROM `relationship` where `relationship`.company=#{company} and `relationship`.active=0")
    Integer[] getPartnerNumberFromRelationshipByCompany(@Param("company") String company);
    //查PartnerAmount 通过company
    @Select("SELECT count(distinct partner) FROM `relationship` where `relationship`.company=#{company} and `relationship`.active=0")
    Integer getPartnerAmountFromRelationshipByCompany(@Param("company") String company);

    //company看非合作的维修公司
    @Select("SELECT distinct partner FROM `relationship` where `relationship`.company=#{company} and `relationship`.active=1")
    Integer[] getNotPartnerNumberFromRelationshipByCompany(@Param("company") String company);
    //查PartnerAmount 通过company
    @Select("SELECT count(distinct partner) FROM `relationship` where `relationship`.company=#{company} and `relationship`.active=1")
    Integer getNotPartnerAmountFromRelationshipByCompany(@Param("company") String company);
}
