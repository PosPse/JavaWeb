package Test;

import com.popse.dao.CourseMapper;
import com.popse.pojo.Course;
import com.popse.utils.MyBatisUtils;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @Description : TODO
 * @Author : PoPse
 * @Date : 2022/4/11 19:47
 * @Version : 1.0
 **/
public class CourseTest {

    /**
     * @Description: 根据课程ID获取课程信息
     * @Param : null
     * @Return: void
     **/
    @Test
    public void findCourseByIdTest() {
        SqlSession session = MyBatisUtils.getSession();
        CourseMapper mapper = session.getMapper(CourseMapper.class);
        Course course = mapper.findCourseById(1);
        System.out.println(course);
        session.close();
    }

    /**
     * @Description: 根据课程名模糊查询课程信息列表 或 查询所有课程信息列表
     * @Param : null
     * @Return: void
     **/
    @Test
    public void findCourseByNameTest () {
        SqlSession session = MyBatisUtils.getSession();
        CourseMapper mapper = session.getMapper(CourseMapper.class);
        ArrayList<Course> courseList = mapper.findCourseByName("");
        for (Course course : courseList) {
            System.out.println(course);
        }
        session.close();
    }

    /**
     * @Description: 根据学院ID获取课程信息列表
     * @Param : null
     * @Return: void
     **/
    @Test
    public void findCourseByAcademyIdTest() {
        SqlSession session = MyBatisUtils.getSession();
        CourseMapper mapper = session.getMapper(CourseMapper.class);
        ArrayList<Course> courseList = mapper.findCourseByAcademyId(1);
        for (Course course : courseList) {
            System.out.println(course);
        }
        session.close();
    }

    /**
     * @Description: 添加课程信息
     * @Param : null
     * @Return: void
     **/
    @Test
    public void addCourseTest() {
        SqlSession session = MyBatisUtils.getSession();
        CourseMapper mapper = session.getMapper(CourseMapper.class);
        Course course = new Course("高等数学", 70, 3);
        int count = mapper.addCourse(course);
        if(count > 0){
            System.out.println("您成功添加了"+count+"条数据！");
        }else{
            System.out.println("执行添加操作失败！！！");
        }
        session.commit();
        session.close();
    }

    /**
     * @Description: 更新课程信息
     * @Param : null
     * @Return: void
     **/
    @Test
    public void updateCourseTest() {
        SqlSession session = MyBatisUtils.getSession();
        CourseMapper mapper = session.getMapper(CourseMapper.class);
        Course course = new Course(6,"高等数学", 60, 3);
        int count = mapper.updateCourse(course);
        if(count > 0){
            System.out.println("您成功修改了"+count+"条数据！");
        }else{
            System.out.println("执行修改操作失败！！！");
        }
        session.commit();
        session.close();
    }

    /**
     * @Description: 删除课程信息
     * @Param : null
     * @Return: void
     **/
    @Test
    public void deleteCourseTest() {
        SqlSession session = MyBatisUtils.getSession();
        CourseMapper mapper = session.getMapper(CourseMapper.class);
        int count = mapper.deleteCourse(4);
        if(count > 0){
            System.out.println("您成功删除了"+count+"条数据！");
        }else{
            System.out.println("执行删除操作失败！！！");
        }
        session.commit();
        session.close();
    }
}
