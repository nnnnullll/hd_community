package com.example.demo.mapper;

import com.example.demo.enity.Activity;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ActivityMapper {
    //插入activity
    @Insert("insert into `activity`(case_number,type,message,updated_role,updated_by) values(#{case_number},#{type},#{message},#{updated_role},#{updated_by})")
    Integer insertActivity(Integer case_number,Integer type,String message, Integer updated_role,Integer updated_by);

    //查activity 通过case number 按照时间排序
    @Select("SELECT * FROM `activity` where `activity`.case_number=#{case_number} ORDER BY `activity`.updated DESC")
    Activity[] getActivityByCase_number(Integer case_number);
}
