package com.example.demo.mapper;

import com.example.demo.enity.partner;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PartnerMapper {
    //插partner
    @Insert("INSERT INTO `partner`(name,address,phone,email,description, password,one,two,three,four,five) VALUES(#{partner.name},#{partner.address},#{partner.phone},#{partner.email},#{partner.password},#{partner.description},#{partner.one},#{partner.two},#{partner.three},#{partner.four},#{partner.five})")
    @Options(useGeneratedKeys=true, keyProperty="partner.num")
    Integer InsertPartner(@Param("partner") partner partner);

    @Update("update `partner` set  `partner`.address = #{partner.address},`partner`.phone = #{partner.phone},`partner`.email = #{partner.email},`partner`.description = #{partner.description},`partner`.one = #{partner.one},`partner`.two = #{partner.two},`partner`.three = #{partner.three},`partner`.four = #{partner.four},`partner`.five = #{partner.five},`partner`.active = #{partner.active} where `partner`.num = #{partner.num}")
    Integer updatePartner(@Param("partner") partner partner);
    @Update("update `partner` set  `partner`.password = #{partner.password} where `partner`.num = #{partner.num}")
    Integer updatePartnerPassword(@Param("partner") partner partner);

    //查partner 通过number(唯一一条记录)
    @Select("SELECT * FROM `partner` where `partner`.num=#{num}")
    partner getPartnerByNum(Integer num);
    //查partner 通过number(唯一一条记录)
    @Select("SELECT active FROM `partner` where `partner`.num=#{num} and `partner`.active=#{num}")
    Integer getPartnerActiveByNum(Integer num);
    //查partner 所有
    @Select("SELECT * FROM `partner`")
    partner[] getAllPartner();
    //查partner 通过number(唯一一条记录)
    @Select("SELECT count(*) FROM `partner` where `partner`.phone=#{phone}")
    Integer getPartnerAmountByPhone(String phone);
    //查partner 通过number(唯一一条记录)
    @Select("SELECT count(*) FROM `partner` where `partner`.name=#{name}")
    Integer getPartnerAmountByName(String name);

     //login 核实Partner 通过number & password
    @Select("SELECT count(*) FROM `partner` where `partner`.num=#{username} and `partner`.password=#{password}")
    Integer validatePartnerByPassword(Integer username, String password);
 
}
 