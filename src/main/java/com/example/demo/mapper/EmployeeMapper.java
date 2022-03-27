package com.example.demo.mapper;

import com.example.demo.enity.Employee;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    //插入employee
    @Insert("insert into `employee`(name,company,id,phone,email,password,admin) values(#{employee.name},#{employee.company},#{employee.id},#{employee.phone},#{employee.email},#{employee.phone},#{employee.admin});")
    @Options(useGeneratedKeys=true, keyProperty="employee.number")
    Integer InsertEmployee(@Param("employee") Employee employee);
    //核实是否已存在-身份证
    @Select("SELECT count(*) FROM `employee` where `employee`.id=#{id}")
    Integer validateEmployeeByID(String id);
    //核实是否已存在-手机
    @Select("SELECT count(*) FROM `employee` where `employee`.phone=#{phone}")
    Integer validateEmployeeByPhone(String phone);

    //查employee 通过number(唯一一条记录)
    @Select("SELECT * FROM `employee` where `employee`.number=#{number} and `employee`.active=0")
    Employee getEmployeeByNumber(Integer number);

    //查employee 通过company(多条记录)
    @Select("SELECT * FROM `employee` where `employee`.company=#{company} and `employee`.active=0")
    Employee[] getEmployeeByCompany(String company);

    //login 核实employee 通过number & password
    @Select("SELECT count(*) FROM `employee` where `employee`.number=#{username} and `employee`.password=#{password} and `employee`.active=0")
    Integer validateEmployeeByPassword(Integer username, String password);

    //login 核实employee 通过number & password
    @Select("SELECT admin FROM `employee` where `employee`.number=#{username} and `employee`.active=0")
    Integer getEmployeeActiveByNumber(Integer username);
}
 