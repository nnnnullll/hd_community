package com.example.demo.mapper;

import com.example.demo.enity.Building;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BuildingMapper {
    
    @Insert("insert into building (community,building_number,height,household_amount) values(#{community},#{building_number},#{height},#{household_amount})")
    Integer insertBuilding(@Param("community") Integer community,@Param("building_number") Integer building_number,@Param("height") Integer height,@Param("household_amount") Integer household_amount);

    //查building 通过community number 按照building_number排序
    @Select("SELECT * FROM `building` where `building`.community=#{community} ORDER BY `building`.building_number ASC")
    Building[] getBuilding_ByCommunity_OrderBuilding_number(Integer case_number);
}
