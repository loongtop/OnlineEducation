package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.CourseEntity;
import com.gkhy.eduservice.entity.vo.CourseInfoVo;
import com.gkhy.eduservice.entity.vo.CoursePublishVo;

import java.util.List;

public interface CourseService {
    List<CourseEntity> findAll();

    Long saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(Long courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    void updateById(CourseEntity eduCourse);

    void removeCourse(Long courseId);

    CoursePublishVo publishCourseInfo(Long id);

    void save(CourseEntity courseEntity);
}
