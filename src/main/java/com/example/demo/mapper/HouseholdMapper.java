package com.example.demo.mapper;

import com.example.demo.enity.Household;
import com.example.demo.enity.option;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HouseholdMapper {
    //插入 household
    @Insert("INSERT INTO `household`(community,building,room_number,password) VALUES(#{community},#{building},#{room_number},#{password})")
    Integer insertHousehold(@Param("community") Integer community,@Param("building") Integer building,@Param("room_number") String room_number,@Param("password") String password);

    @Update("update `household` set `household`. email = #{email}, `household`. phone = #{phone} where `household`. number = #{number}")
    Integer updateHousehold( @Param("number") Integer number, @Param("email") String building,@Param("phone") String phone);
    @Update("update `household` set `household`. password = #{password} where `household`. number = #{number}")
    Integer updateHouseholdPassword( @Param("number") Integer number, @Param("password") String password);
    @Update("update `household` set `household`. password = '123456' where `household`. number = #{number}")
    Integer updateHouseholdResetPassword( @Param("number") Integer number);

    //查household 通过number(唯一一条记录)
    @Select("SELECT * FROM `household` where `household`.number=#{number}")
    Household getHouseholdByNumber(Integer number);
    @Select("SELECT count(*) FROM `household` where `household`.number=#{number}")
    Integer getCountHouseholdByNumber(Integer number);

    //查household 通过community number(多条记录)
    @Select("SELECT * FROM `household` where `household`.community=#{community}")
    Household[] getHouseholdByCommunity(Integer community);

    //查household 通过company number(多条记录)
    @Select("SELECT * FROM `household` where `household`.company=#{company}")
    Household[] getHouseholdByCompany(String company);
    //查household 通过company number(多条记录)
    @Select("SELECT community FROM `household` where `household`.number=#{number}")
    Integer getCommunityByNumber(Integer number);


    //查household 通过community number(多条记录)
    @Select("SELECT count(*) FROM `household` where `household`.community=#{community}")
    Integer getHouseholdAmountByCommunity(Integer community);
    //查household 通过community number(多条记录)
    @Select("SELECT count(distinct building) FROM `household` where `household`.community=#{community}")
    Integer getBuildingAmountByCommunity(Integer community);

    //login核实household 通过number & password
    @Select("SELECT count(*) FROM `household` where `household`.number=#{username} and `household`.password=#{password}")
    Integer validateHouseholdByPassword(Integer username, String password);

    @Results(value = {
        @Result(id = true, property = "value", column = "number"),
        @Result(property = "label", column = "name")
    })
    @Select("SELECT `household`.number,CONCAT(`household`.building,'楼',`household`.room_number,'室') as name FROM `household` WHERE `household`.active=0 and `household`.community=#{community}")
    option[] getHouseholdOption(Integer community);
} 
