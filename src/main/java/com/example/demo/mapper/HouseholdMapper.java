package com.example.demo.mapper;

import com.example.demo.enity.Household;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HouseholdMapper {
    //插入 household
    @Insert("INSERT INTO `household`(community,building,room_number,password) VALUES(#{community},#{building},#{room_number},#{password})")
    Integer insertHousehold(@Param("community") Integer community,@Param("building") Integer building,@Param("room_number") String room_number,@Param("password") String password);

    //查household 通过number(唯一一条记录)
    @Select("SELECT * FROM `household` where `household`.number=#{number}")
    Household getHouseholdByNumber(Integer number);

    //查household 通过community number(多条记录)
    @Select("SELECT * FROM `household` where `household`.community=#{community}")
    Household[] getHouseholdByCommunity(Integer community);

    //login核实household 通过number & password
    @Select("SELECT count(*) FROM `household` where `household`.number=#{username} and `household`.password=#{password}")
    Integer validateHouseholdByPassword(Integer username, String password);
} 
