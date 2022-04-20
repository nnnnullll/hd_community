package com.example.demo.mapper;

import com.example.demo.enity.Employee;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {
    //插入employee
    @Insert("insert into `employee`(name,company,id,phone,email,password,admin) values(#{employee.name},#{employee.company},#{employee.id},#{employee.phone},#{employee.email},#{employee.phone},#{employee.admin});")
    @Options(useGeneratedKeys=true, keyProperty="employee.number")
    Integer InsertEmployee(@Param("employee") Employee employee);
    //插入employee
    @Update("update `employee` set `employee`.phone = #{employee.phone},`employee`.email = #{employee.email} where `employee`.number = #{employee.number};")
    Integer updateEmployee(@Param("employee") Employee employee);
    @Update("update `employee` set `employee`.active = 0 where `employee`.number = #{number};")
    Integer updateEmployeeActive(Integer number);
    @Update("update `employee` set `employee`.active = 1 where `employee`.number = #{number};")
    Integer updateEmployeeInActive(Integer number);
    @Update("update `employee` set `employee`.admin = 0 where `employee`.number = #{number};")
    Integer updateEmployeeInAdmin(Integer number);
    @Update("update `employee` set `employee`.admin = 1 where `employee`.number = #{number};")
    Integer updateEmployeeAdmin(Integer number);
    //核实是否已存在-身份证
    @Select("SELECT count(*) FROM `employee` where `employee`.id=#{id} and `employee`.company=#{company}")
    Integer validateEmployeeByID(String id, String company);
    //核实是否已存在-手机
    @Select("SELECT count(*) FROM `employee` where `employee`.phone=#{phone} and `employee`.company=#{company}")
    Integer validateEmployeeByPhone(String phone, String company);
    //核实是否已存在-email
    @Select("SELECT count(*) FROM `employee` where `employee`.email=#{email} and `employee`.company=#{company}")
    Integer validateEmployeeByEmail(String email, String company);

    //查employee 通过number(唯一一条记录)
    @Select("SELECT * FROM `employee` where `employee`.number=#{number}")
    Employee getEmployeeByNumber(Integer number);

    //查employee 通过company(多条记录)
    @Select("SELECT * FROM `employee` where `employee`.company=#{company}")
    Employee[] getEmployeeByCompany(String company);

    //login 核实employee 通过number & password
    @Select("SELECT count(*) FROM `employee` where `employee`.number=#{username} and `employee`.password=#{password} and `employee`.active=0")
    Integer validateEmployeeByPassword(Integer username, String password);

    //login 核实employee 通过number & password
    @Select("SELECT admin FROM `employee` where `employee`.number=#{username} and `employee`.active=0")
    Integer getEmployeeAdminByNumber(Integer number);
    //login 核实employee 通过number & password
    @Select("SELECT active FROM `employee` where `employee`.number=#{username}")
    Integer getEmployeeActiveByNumber(Integer number);
}
 