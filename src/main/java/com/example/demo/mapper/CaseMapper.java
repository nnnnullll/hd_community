package com.example.demo.mapper;
import com.example.demo.enity.Case;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CaseMapper { 
    // 插入case
    @Insert("insert into `case` (company,community,household,subject,description,type,created_role,created_by) values(#{case.company},#{case.community},#{case.household},#{case.subject},#{case.description},#{case.type},#{case.created_role},#{case.created_by})")
    @Options(useGeneratedKeys=true, keyProperty="case.number")
    Integer insertCase(@Param("case") Case newcase);
    // 查case 通过case number
    @Select("SELECT * FROM `case` where `case`.number=#{number}")
    Case getCaseByNumber(Integer number);
}
