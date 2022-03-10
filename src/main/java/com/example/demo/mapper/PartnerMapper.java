package com.example.demo.mapper;

import com.example.demo.enity.partner;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PartnerMapper {
    //插partner
    @Insert("INSERT INTO `partner`(number,name,address,phone,email, password) VALUES(#{number},#{name},#{address},#{phone},#{email},#{password})")
    Integer InsertPartner(@Param("number") String number,@Param("name") String name,@Param("address") String address,@Param("phone") String phone,@Param("email") String email,@Param("password") String password);

     //查partner 通过number(唯一一条记录)
     @Select("SELECT * FROM `partner` where `partner`.num=#{num}")
     partner getPartnerByNum(Integer num);

     //login 核实Partner 通过number & password
    @Select("SELECT count(*) FROM `partner` where `partner`.num=#{username} and `partner`.password=#{password}")
    Integer validatePartnerByPassword(Integer username, String password);
 
}
 