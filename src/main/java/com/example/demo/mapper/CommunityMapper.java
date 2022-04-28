package com.example.demo.mapper;

import com.example.demo.enity.Community;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CommunityMapper {
    //插入community
    @Insert("INSERT INTO `community` (name,region,address,company) VALUES (#{community.name},#{community.region},#{community.address},#{community.company}) ")
    @Options(useGeneratedKeys=true, keyProperty="community.number")
    Integer insertCommunity(@Param("community") Community community);

    //查community 通过community number(唯一一条记录)
    @Select("SELECT * FROM `community` where `community`.number=#{number}")
    Community getCommunityByNumber(Integer number);
    //查community 通过community number(唯一一条记录)
    @Select("SELECT active FROM `community` where `community`.number=#{number}")
    Integer getActiveFromCommunityByNumber(Integer number);
    //查communities 通过company number(多条记录)
    @Select("SELECT * FROM `community` where `community`.company=#{company} and `community`.active=0")
    Community[] getCommunitiesByCompany(String company);
    @Select("SELECT * FROM `community`")
    Community[] getCommunityAll();

    @Select("SELECT count(*) FROM `community` where `community`.number=#{number} and `community`.active=1")
    Integer validateInactiveCommunityByNumber(Integer number);
    @Select("SELECT count(*) FROM `community` where `community`.number=#{number} and `community`.active=0 and `community`.company=#{company} ")
    Integer validateActiveCommunityCompanyByNumber(Integer number,String company);

    @Update("update `community` set `community`.active=1,`community`.company=null where `community`.number=#{number};")
    Integer updateCommunityRemoveCompanyByNumber(Integer number);
    @Update("update `community` set `community`.active=0,`community`.company=#{company} where `community`.number=#{number};")
    Integer updateCommunityAddCompanyByNumber(String company,Integer number);
}
