package com.popse.controller;

import com.popse.pojo.Course;
import com.popse.service.CourseService;
import com.popse.util.ImgFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @Description : TODO
 * @Author : PoPse
 * @Date : 2022/4/21 15:38
 * @Version : 1.0
 **/
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    @Qualifier("CourseServiceImpl")
    private CourseService courseService;


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> findCourseAndAcademyAll() {
        Map<String, Object> res = new HashMap<>();
        List<Object> obj = new ArrayList<>();
        List<Map<String, Object>> courses = courseService.findCourseAndAcademyAll();
        for(Map<String, Object> course : courses) {
            obj.add(course);
        }
        res.put("length", courses.size());
        res.put("value", obj);
        return res;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/search")
    @ResponseBody
    public Map<String, Object> findCourseInfoByCourseName(String courseName) {
        System.out.println(courseName);
        Map<String, Object> res = new HashMap<>();
        List<Object> obj = new ArrayList<>();
        List<Map<String, Object>> courses = courseService.findCourseInfoByCourseName(courseName);
        for(Map<String, Object> course : courses) {
            obj.add(course);
        }
        res.put("length", courses.size());
        res.put("value", obj);
        return res;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/courseImg/{fileName}.{suffix}")
    public void showCourseImg(@PathVariable("fileName") String fileName, @PathVariable("suffix") String suffix, HttpServletResponse response) {
        File courseImg = new File(ImgFile.IMG_PATH + fileName + "." + suffix);
        responseFile(response, courseImg);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteCourse(Integer courseId) {
        int i = courseService.deleteCourse(courseId);
        int j = courseService.deleteCourseImg(courseId);
        return i+"";
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/update")
    @ResponseBody
    public void updateCourse(Course course, MultipartFile file, HttpServletResponse response) throws IOException {
        ArrayList<Course> courseList = courseService.findCourseByIdAndName(course.getCourseId(), course.getCourseName());
        if(courseList.size()!=0) {
            response.getWriter().print(false);
            return;
        }
        boolean b = courseService.updateCourseInfo(course, file);
        response.getWriter().print(true);
        return;
    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/add")
    @ResponseBody
    public void addCourse(Course course, MultipartFile file, HttpServletResponse response) throws IOException {
        ArrayList<Course> courseList = courseService.findCourseByName(course.getCourseName());
        if(courseList.size()!=0) {
            response.getWriter().print(false);
            return;
        }
        boolean b = courseService.addCourseInfo(course, file);
        response.getWriter().print(true);
        return ;
    }
    /**
     * 响应输出图片文件
     * @param response
     * @param imgFile
     */
    private void responseFile(HttpServletResponse response, File imgFile) {
        try(InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream();){
            byte [] buffer = new byte[1024]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
