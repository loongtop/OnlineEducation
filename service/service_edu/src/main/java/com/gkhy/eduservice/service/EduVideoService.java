package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.EduVideo;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * <p>
 * eduVideo service
 * </p>
 *
 * @author testjava
 * @since 2022-07-18
 */
public interface EduVideoService {

    // Remove subsections based on course id
    void removeVideoByCourseId(String courseId);

    Long count(String chapterId);

    void deleteById(String chapterId);

    List<EduVideo> findAll(Specification<EduVideo> specEduVideo);

    void save(EduVideo eduVideo);

    void removeById(String id);
}
