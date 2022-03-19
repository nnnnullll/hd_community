package com.example.demo.mapper;

import com.example.demo.enity.Relationship;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RelationshipMapper {
    //插relationship
    @Insert("INSERT INTO `relationship`(company,partner,type) VALUES(#{company},#{partner},#{type})")
    Integer InsertRelationship(@Param("company") String company,@Param("partner") Integer partner,@Param("type") Integer type);

    //查relationship 通过partner
    @Select("SELECT distinct type  FROM `relationship` where `relationship`.partner=#{partner} and `relationship`.active=0")
    Integer[] getRelationshipTypeByPartner(@Param("partner") Integer partner);

    //查relationship 通过company
    @Select("SELECT * FROM `relationship` where `relationship`.company=#{company} and `relationship`.active=0")
    Relationship[] getRelationshipByCompany(@Param("company") String company);

    //查relationships 通过company & type
    @Select("SELECT * FROM `relationship` where `relationship`.company=#{company} and `relationship`.type=#{type} and `relationship`.active=0")
    Relationship[] getRelationshipByCompanyType(@Param("company") String company,@Param("type") Integer type);

    //查CompanyAmount 通过partner
    @Select("SELECT count(distinct company) FROM `relationship` where `relationship`.partner=#{partner} and `relationship`.active=0")
    Integer getCompanyAmountFromRelationshipByPartner(@Param("partner") Integer partner);

    //查CompanyNumber 通过partner
    @Select("SELECT distinct company FROM `relationship` where `relationship`.partner=#{partner} and `relationship`.active=0")
    String[] getCompanyNumberFromRelationshipByPartner(@Param("partner") Integer partner);

    //查PartnerAmount 通过company
    @Select("SELECT count(distinct partner) FROM `relationship` where `relationship`.company=#{company} and `relationship`.active=0")
    Integer getPartnerAmountFromRelationshipByPartner(@Param("company") String company);

    //查PartnerNumber 通过company
    @Select("SELECT distinct partner FROM `relationship` where `relationship`.company=#{company} and `relationship`.active=0")
    Integer[] getPartnerNumberFromRelationshipByPartner(@Param("company") String company);
}
