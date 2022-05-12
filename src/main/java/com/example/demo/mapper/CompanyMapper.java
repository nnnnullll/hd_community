package com.example.demo.mapper;

import com.example.demo.enity.Company;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CompanyMapper {
    //插入company
    @Insert("INSERT INTO `company` (number,name,address,phone,email) VALUES ( #{number}, #{name}, #{address},#{phone},#{email}) ")
    Integer insertCompany(@Param("number") String number,@Param("name") String name,@Param("address") String address,@Param("phone") String phone,@Param("email") String email);

    // update company
    @Update("update `company` set `company`.address= #{address},`company`.phone=#{phone},`company`.email=#{email} where `company`.number= #{number};")
    Integer updateCompany(@Param("number") String number,@Param("address") String address,@Param("phone") String phone,@Param("email") String email);

    //查cmmpany 通过number(唯一一条记录)
    @Select("SELECT * FROM `company` where `company`.number=#{number} and `company`.active=0")
    Company getCompanyByNumber(String integer);
    //查cmmpany 通过number(唯一一条记录)
    @Select("SELECT * FROM `company` where `company`.number not in  (SELECT `relationship`.company FROM `relationship` where `relationship`.partner=#{number} and `relationship`.active=0)")
    Company[] getNotPartnerCompany(Integer number);
} 
