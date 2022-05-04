package com.example.demo.mapper;

import com.example.demo.enity.option;
import com.example.demo.enity.partner;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PartnerMapper {
    //插partner
    @Insert("INSERT INTO `partner`(name,address,phone,email,description, password,one,two,three,four,five) VALUES(#{partner.name},#{partner.address},#{partner.phone},#{partner.email},#{partner.description},#{partner.password},#{partner.one},#{partner.two},#{partner.three},#{partner.four},#{partner.five})")
    @Options(useGeneratedKeys=true, keyProperty="partner.num")
    Integer InsertPartner(@Param("partner") partner partner);

    @Update("update `partner` set  `partner`.address = #{partner.address},`partner`.phone = #{partner.phone},`partner`.email = #{partner.email},`partner`.description = #{partner.description},`partner`.one = #{partner.one},`partner`.two = #{partner.two},`partner`.three = #{partner.three},`partner`.four = #{partner.four},`partner`.five = #{partner.five} where `partner`.num = #{partner.num}")
    Integer updatePartner(@Param("partner") partner partner);
    @Update("update `partner` set  `partner`.password = #{partner.password} where `partner`.num = #{partner.num}")
    Integer updatePartnerPassword(@Param("partner") partner partner);
    @Update("update `partner` set  `partner`.password = '123456' where `partner`.num = #{partner.num}")
    Integer updatePartnerResetPassword(Integer num);
    @Update("update `partner` set  `partner`.active = #{partner.active} where `partner`.num = #{partner.num}")
    Integer updatePartnerActive(@Param("partner") partner partner);

    //查partner 通过number(唯一一条记录)
    @Select("SELECT * FROM `partner` where `partner`.num=#{num}")
    partner getPartnerByNum(Integer num);
    @Select("SELECT count(*) FROM `partner` where `partner`.num=#{num}")
    Integer getCountPartnerByNum(Integer num);
    //查partner 通过number(唯一一条记录)
    @Select("SELECT active FROM `partner` where `partner`.num=#{num} and `partner`.active=#{num}")
    Integer getPartnerActiveByNum(Integer num);
    //查非合作的partner
    @Select("SELECT * FROM `partner` where `partner`.num not in  (SELECT `relationship`.partner FROM `relationship` where `relationship`.company=#{number})")
    partner[] getNonePartner(String number);
    @Select("SELECT count(*) FROM `partner` where `partner`.num not in  (SELECT `relationship`.partner FROM `relationship` where `relationship`.company=#{number})")
    Integer getNonePartnerAmount(String number);
    //查partner 通过number(唯一一条记录)
    @Select("SELECT count(*) FROM `partner` where `partner`.phone=#{phone}")
    Integer getPartnerAmountByPhone(String phone);
    //查partner 通过number(唯一一条记录)
    @Select("SELECT count(*) FROM `partner` where `partner`.name=#{name}")
    Integer getPartnerAmountByName(String name);
   

     //login 核实Partner 通过number & password
    @Select("SELECT count(*) FROM `partner` where `partner`.num=#{username} and `partner`.password=#{password}")
    Integer validatePartnerByPassword(Integer username, String password);
    
    //get option_fix 
    @Results(value = {
        @Result(id = true, property = "value", column = "num"),
        @Result(property = "label", column = "name")
    })
    @Select("SELECT `partner`.num,`partner`.`name` FROM `partner` WHERE `partner`.active=0 and `partner`.one=1 AND `partner`.num in (SELECT `relationship`.partner FROM `relationship` where `relationship`.active=0 and `relationship`.company=#{company})")
    option[] getFixOneOption(String company);
    @Results(value = {
        @Result(id = true, property = "value", column = "num"),
        @Result(property = "label", column = "name")
    })
    @Select("SELECT `partner`.num,`partner`.`name` FROM `partner` WHERE `partner`.active=0 and `partner`.two=1 AND `partner`.num in (SELECT `relationship`.partner FROM `relationship` where `relationship`.active=0 and `relationship`.company=#{company})")
    option[] getFixTwoOption(String company);
    @Results(value = {
        @Result(id = true, property = "value", column = "num"),
        @Result(property = "label", column = "name")
    })
    @Select("SELECT `partner`.num,`partner`.`name` FROM `partner` WHERE `partner`.active=0 and `partner`.three=1 AND `partner`.num in (SELECT `relationship`.partner FROM `relationship` where `relationship`.active=0 and `relationship`.company=#{company})")
    option[] getFixThreeOption(String company);
    @Results(value = {
        @Result(id = true, property = "value", column = "num"),
        @Result(property = "label", column = "name")
    })
    @Select("SELECT `partner`.num,`partner`.`name` FROM `partner` WHERE `partner`.active=0 and `partner`.four=1 AND `partner`.num in (SELECT `relationship`.partner FROM `relationship` where `relationship`.active=0 and `relationship`.company=#{company})")
    option[] getFixFourOption(String company);
    @Results(value = {
        @Result(id = true, property = "value", column = "num"),
        @Result(property = "label", column = "name")
    })
    @Select("SELECT `partner`.num,`partner`.`name` FROM `partner` WHERE `partner`.active=0 and `partner`.five=1 AND `partner`.num in (SELECT `relationship`.partner FROM `relationship` where `relationship`.active=0 and `relationship`.company=#{company})")
    option[] getFixFiveOption(String company);
}
 