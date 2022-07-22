package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.EduCourseDescription;
import com.gkhy.eduservice.repository.EduCourseDescriptionRepository;
import com.gkhy.eduservice.service.EduCourseDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Course Introduction Service Implementation Class
 * </p>
 *
 * @author leo
 * @since 2022-07-17
 */
@Service
public class EduCourseDescriptionServiceImpl implements EduCourseDescriptionService {
    @Autowired
    private EduCourseDescriptionRepository eduCourseDescriptionRepository;

    @Override
    public void save(EduCourseDescription courseDescription) {
        eduCourseDescriptionRepository.save(courseDescription);
    }

    @Override
    public void removeById(String courseId) {
        eduCourseDescriptionRepository.deleteById(courseId);
    }
}
