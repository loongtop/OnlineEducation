package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.EduCourseDescription;

public interface EduCourseDescriptionService {
    void save(EduCourseDescription courseDescription);

    void removeById(String courseId);
}
