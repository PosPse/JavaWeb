package Test;

import com.popse.pojo.Course;
import com.popse.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Description : TODO
 * @Author : PoPse
 * @Date : 2022/4/10 20:22
 * @Version : 1.0
 **/
public class CourseTest {

    /**
     * @Description: 根据课程名模糊查询课程信息列表 或 查询所有课程信息列表
     * @Param : null
     * @Return: void
     **/
    @Test
    public void findCourseByName() {
        SqlSession session = MyBatisUtils.getSession();
        List<Course> courseList = session.selectList("findCourseByName","");
        for(Course course : courseList) {
            System.out.println(course);
        }
    }

    /**
     * @Description: 根据课程ID获取课程信息
     * @Param : null
     * @Return: void
     **/
    @Test
    public void findCourseById() {
        SqlSession session = MyBatisUtils.getSession();
        Course course = session.selectOne("findCourseById", 2);
        System.out.println(course);
    }

    /**
     * @Description: 根据学院ID获取课程信息列表
     * @Param : null
     * @Return: void
     **/
    @Test
    public void findCourseByAcademyId() {
        SqlSession session = MyBatisUtils.getSession();
        List<Course> courseList = session.selectList("findCouserByAcademyId", 1);
        for(Course course : courseList) {
            System.out.println(course);
        }
    }

    /**
     * @Description: 添加课程信息
     * @Param : null
     * @Return: void
     **/
    @Test
    public void addCourse() {
        SqlSession session = MyBatisUtils.getSession();
        Course course = new Course("高等数学", 50, 3);
        int count = session.insert("addCourse", course);
        if(count > 0){
            System.out.println("您成功插入了"+count+"条数据！");
        }else{
            System.out.println("执行插入操作失败！！！");
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
    public void updateCourse() {
        SqlSession session = MyBatisUtils.getSession();
        Course course = new Course(5,"线性代数", 50, 3);
        int count = session.update("updateCourse", course);
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
    public void deleteCourse() {
        SqlSession session = MyBatisUtils.getSession();
        int count = session.delete("deleteCourse", 5);
        if(count > 0){
            System.out.println("您成功删除了"+count+"条数据！");
        }else{
            System.out.println("执行删除操作失败！！！");
        }
        session.commit();
        session.close();
    }
}
