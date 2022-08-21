package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.CourseDescriptionEntity;

public interface CourseDescriptionService {
    void save(CourseDescriptionEntity courseDescription);

    void removeById(Long courseId);
}
