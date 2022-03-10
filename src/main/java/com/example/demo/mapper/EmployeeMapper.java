package com.example.demo.mapper;

import com.example.demo.enity.Employee;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    //插入employee
    @Insert("insert into `employee`(name,company,id,phone,email,password) values(#{name},#{company},#{id},#{phone},#{email},#{password});")
    Integer InsertEmployee(@Param("name") String name,@Param("company") String company,@Param("id") String id,@Param("phone") String phone,@Param("email") String email,@Param("password") String password);

    //查employee 通过number(唯一一条记录)
    @Select("SELECT * FROM `employee` where `employee`.number=#{number} and `employee`.active=0")
    Employee getEmployeeByNumber(Integer number);

    //查employee 通过company(多条记录)
    @Select("SELECT * FROM `employee` where `employee`.company=#{company} and `employee`.active=0")
    Employee[] getEmployeeByCompany(String company);

    //login 核实employee 通过number & password
    @Select("SELECT count(*) FROM `employee` where `employee`.number=#{username} and `employee`.password=#{password} and `employee`.active=0")
    Integer validateEmployeeByPassword(Integer username, String password);
}
 