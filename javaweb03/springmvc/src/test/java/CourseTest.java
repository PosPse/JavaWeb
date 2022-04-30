import com.popse.pojo.Course;
import com.popse.service.CourseService;
import com.popse.util.ImgFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description : TODO
 * @Author : PoPse
 * @Date : 2022/4/21 22:33
 * @Version : 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CourseTest {

    @Autowired
    @Qualifier("CourseServiceImpl")
    private CourseService courseService;

    @Test
    public void findCourseByNameTest() {
        ArrayList<Course> courseList = courseService.findCourseByName("");
        for(Course course : courseList) {
            System.out.println(course);
        }
    }

    @Test
    public void deleteCourseTest() {
        int i = courseService.deleteCourse(6);
        System.out.println(i);
    }

    @Test
    public void deleteCourseImgTest() {
        int i = courseService.deleteCourseImg(1);
        System.out.println(i);
    }

    @Test
    public void findCourseByIdAndNameTest() {
        ArrayList<Course> courseList = courseService.findCourseByIdAndName(9, "线性代数");
        for(Course course :courseList) {
            System.out.println(course);
        }
    }

    @Test
    public void findCourseAndAcademyByIdTest() {
        Map<String, Object> a = courseService.findCourseAndAcademyByCourseId(1);
        System.out.println(a);
    }

    @Test
    public void findCourseAndAcademyAllTest() {
//        List<Map<String, Object>> courses = courseService.findCourseAndAcademyAll();
//        for(Map<String, Object> course : courses) {
//            System.out.println(course);
//        }
//        System.out.println(courses.size());
        List<Object> obj = new ArrayList<>();
        List<Map<String, Object>> courses = courseService.findCourseAndAcademyAll();
        for(Map<String, Object> course : courses) {
            System.out.println(course);
        }
    }

    @Test
    public void deleteImg() {
        File file = new File(ImgFile.IMG_PATH+"abc.jpg");
        ImgFile.deleteImg(file);
    }

    @Test
    public void addCourseImgTest() {
        int i = courseService.addCourseImg("sss", 100);
        System.out.println(i);
    }

    @Test
    public void addCourseTest() {
        Course course = new Course("dd", 10, 1);
        int dd = courseService.addCourse(course);
        System.out.println(dd);
        System.out.println(course.getCourseId());
    }

    @Test
    public void updateCourseImgTest() {
        int i = courseService.updateCourseImg("http://localhost:8080/course/courseImg/ddf65084-f420-42a8-9019-5bd4d3c99b1c.jpg", 23);
        System.out.println(i);
    }

    @Test
    public void findCourseInfoByCourseName() {
        List<Map<String, Object>> courseList = courseService.findCourseInfoByCourseName("1");
        for(Map<String, Object> course : courseList) {
            System.out.println(course);
        }
    }
}
