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

    //查relationship 通过company
    @Select("SELECT * FROM `relationship` where `relationship`.company=#{company} and `relationship`.active=0")
    Relationship[] getRelationshipByCompany(@Param("company") String company);

    //查relationship 通过company & type
    @Select("SELECT * FROM `relationship` where `relationship`.company=#{company} and `relationship`.type=#{type} and `relationship`.active=0")
    Relationship[] getRelationshipByCompanyType(@Param("company") String company,@Param("type") Integer type);
}
