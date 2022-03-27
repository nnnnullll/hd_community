package com.example.demo.mapper;

import com.example.demo.enity.Community;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommunityMapper {
    //插入community
    @Insert("INSERT INTO `community` (name,region,company) VALUES (#{community.name},#{community.region},#{community.company}) ")
    @Options(useGeneratedKeys=true, keyProperty="community.number")
    Integer insertCommunity(@Param("community") Community community);

    //查community 通过community number(唯一一条记录)
    @Select("SELECT * FROM `community` where `community`.number=#{number} and `community`.active=0")
    Community getCommunityByNumber(Integer number);

    //查communities 通过company number(多条记录)
    @Select("SELECT * FROM `community` where `community`.company=#{company} and `community`.active=0")
    Community[] getCommunitiesByCompany(String company);
}
