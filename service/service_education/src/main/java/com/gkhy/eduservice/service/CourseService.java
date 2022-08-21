package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.CourseEntity;
import com.gkhy.eduservice.entity.vo.CourseInfoVo;
import com.gkhy.eduservice.entity.vo.CoursePublishVo;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseEntity> findAll();

    Long saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(Long courseId);

    CoursePublishVo publishCourseInfo(Long id);

    void save(CourseEntity courseEntity);

    Optional<CourseEntity> findById(Long courseId);

    boolean existsById(Long courseId);

    void update(Object courseInfoVo, CourseEntity courseEntity);

    void removeById(Long courseId);

}
