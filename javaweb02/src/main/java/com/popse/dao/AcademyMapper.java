package com.popse.dao;

import com.popse.pojo.Academy;
import org.apache.ibatis.annotations.*;
import java.util.ArrayList;

/**
 * @Description : TODO
 * @Author : PoPse
 * @Date : 2022/4/10 23:17
 * @Version : 1.0
 **/
public interface AcademyMapper<E> {

    /**
     * @Description: 根据学院ID获取学院信息
     * @Param academyId:
     * @Return: com.popse.pojo.Academy
     **/
    @Select("select * from s_academy where id = #{academyId}")
    @Results(id = "academyMap", value = {
            @Result(property = "academyId", column = "id"),
            @Result(property = "academyName", column = "name")
    })
    public Academy findAcademyById(int academyId);

    /**
     * @Description: 据学院名模糊查询学院信息列表 或 查询所有学院信息列表
     * @Param academyName:
     * @Return: com.popse.pojo.Academy[]
     **/
    @ResultMap("academyMap")
    @Select("select * from s_academy where name like concat('%',#{academyName},'%')")
    public ArrayList<E> findAcademyByName(String academyName);

    /**
     * @Description: 添加学院信息
     * @Param academy:
     * @Return: int
     **/
    @Insert("insert into s_academy(id, name) values(#{academyId}, #{academyName})")
    public int addAcademy(Academy academy);

    /**
     * @Description: 修改学院信息
     * @Param academy:
     * @Return: int
     **/
    @Update("update s_academy set name = #{academyName} where id = #{academyId}")
    public int updateAcademy(Academy academy);

    /**
     * @Description: 删除学院
     * @Param academyId:
     * @Return: int
     **/
    @Delete("delete from s_academy where id = #{academyId}")
    public int deleteAcademy(int academyId);
}
