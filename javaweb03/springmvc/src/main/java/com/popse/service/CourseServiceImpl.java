package com.popse.service;

import com.popse.dao.CourseMapper;
import com.popse.pojo.Course;
import com.popse.util.ImgFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description : TODO
 * @Author : PoPse
 * @Date : 2022/4/20 23:39
 * @Version : 1.0
 **/
public class CourseServiceImpl implements CourseService{

    private CourseMapper courseMapper;

    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public ArrayList<Course> findCourseByName(String courseName) {
        return courseMapper.findCourseByName(courseName);
    }

    @Override
    public ArrayList<Course> findCourseByIdAndName(int courseId, String courseName) {
        return courseMapper.findCourseByIdAndName(courseId, courseName);
    }

    @Override
    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    @Override
    public boolean addCourseInfo(Course course, MultipartFile file) {
        if(file != null) {
            // 原始文件名
            String originalFilename = file.getOriginalFilename();
            // 获取文件后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 生成图片名称
            String fileName = UUID.randomUUID().toString() + suffix;
            // 生成图片存储路径
            String filePath = ImgFile.IMG_PATH + fileName;
            File saveFile = new File(filePath);
            try {
                file.transferTo(saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int count = addCourse(course);
            int court1 = addCourseImg("http://localhost:8080/course/courseImg/" + fileName, course.getCourseId());
        }
        return true;
    }

    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public int deleteCourse(int courseId) {
        return courseMapper.deleteCourse(courseId);
    }

    @Override
    public int addCourseImg(String courseImg, int courseId) {
        return courseMapper.addCourseImg(courseImg, courseId);
    }

    @Override
    public int deleteCourseImg(int courseId) {
        return courseMapper.deleteCourseImg(courseId);
    }

    @Override
    public int updateCourseImg(String courseImg, int courseId) {
        return courseMapper.updateCourseImg(courseImg, courseId);
    }

    @Override
    public boolean updateCourseInfo(Course course, MultipartFile file) {
        if(file != null) {
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + suffix;
            String filePath = ImgFile.IMG_PATH + fileName;
            File saveFile = new File(filePath);
            try {
                file.transferTo(saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int i = updateCourse(course);
            int i1 = updateCourseImg("http://localhost:8080/course/courseImg/" + fileName, course.getCourseId());
        }
        return true;
    }

    @Override
    public Map<String, Object> findCourseAndAcademyByCourseId(int courseId) {
        return courseMapper.findCourseAndAcademyByCourseId(courseId);
    }

    @Override
    public List<Map<String, Object>> findCourseAndAcademyAll() {
        return courseMapper.findCourseAndAcademyAll();
    }

    @Override
    public List<Map<String, Object>> findCourseInfoByCourseName(String courseName) {
        return courseMapper.findCourseInfoByCourseName(courseName);
    }
}
