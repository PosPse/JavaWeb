package com.popse.dao;

import com.popse.pojo.Course;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * @Description : TODO
 * @Author : PoPse
 * @Date : 2022/4/11 19:28
 * @Version : 1.0
 **/
public interface CourseMapper<E> {

    /**
     * @Description: 根据课程ID获取课程信息
     * @Param courseId:
     * @Return: com.popse.pojo.Course
     **/
    @Select("select * from s_course where id = #{courseId}")
    @Results(id = "courseMap", value = {
            @Result(property = "courseId", column = "id"),
            @Result(property = "courseName", column = "name"),
            @Result(property = "courseHours", column = "hours"),
            @Result(property = "academyId", column = "academy_id")
    })
    public Course findCourseById(int courseId);

    /**
     * @Description: 根据课程名模糊查询课程信息列表 或 查询所有课程信息列表
     * @Param courseName:
     * @Return: java.util.ArrayList<com.popse.pojo.Course>
     **/
    @Select("select * from s_course where name like concat('%',#{courseName},'%')")
    @ResultMap("courseMap")
    public ArrayList<E> findCourseByName(String courseName);

    /**
     * @Description: 根据学院ID获取课程信息列表
     * @Param academyId:
     * @Return: java.util.ArrayList<com.popse.pojo.Course>
     **/
    @Select("select * from s_course where academy_id = #{academyId}")
    @ResultMap("courseMap")
    public ArrayList<E> findCourseByAcademyId(int academyId);

    /**
     * @Description: 添加课程信息
     * @Param course:
     * @Return: int
     **/
    @Insert("insert into s_course(id, name, hours, academy_id) values (#{courseId}, #{courseName}, #{courseHours}, #{academyId})")
    public int addCourse(Course course);

    /**
     * @Description: 更新课程信息
     * @Param course:
     * @Return: int
     **/
    @Update("update s_course set name = #{courseName}, hours = #{courseHours}, academy_id = #{academyId} where id = #{courseId}")
    public int updateCourse(Course course);

    /**
     * @Description: 删除课程信息
     * @Param courseId:
     * @Return: int
     **/
    @Delete(" delete from s_course where id = #{courseId}")
    public int deleteCourse(int courseId);
}
