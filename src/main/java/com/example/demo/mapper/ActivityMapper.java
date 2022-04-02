package com.example.demo.mapper;

import com.example.demo.enity.Activity;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ActivityMapper {
    //插入activity
    @Insert("insert into `activity`(case_number,message,updated_name,updated_role,updated_by) values(#{case_number},#{message},#{updated_name},#{updated_role},#{updated_by})")
    Integer insertActivity(Integer case_number, String message, String updated_name,Integer updated_role,Integer updated_by);

    //查activity 通过case number 按照时间排序
    @Select("SELECT count(*) FROM `activity` where `activity`.case_number=#{case_number}")
    Integer getActivityAmountByCase_number(Integer case_number);

    //查activity 通过case number 按照时间排序
    @Select("SELECT * FROM `activity` where `activity`.case_number=#{case_number} ORDER BY `activity`.updated DESC")
    Activity[] getActivityByCase_number(Integer case_number);
}
