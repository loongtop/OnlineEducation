package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.EduCourse;
import com.gkhy.eduservice.entity.vo.CourseInfoVo;
import com.gkhy.eduservice.entity.vo.CoursePublishVo;

import java.util.List;

public interface EduCourseService {
    List<EduCourse> findAll();

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    void updateById(EduCourse eduCourse);

    void removeCourse(String courseId);

    CoursePublishVo publishCourseInfo(String id);
}
