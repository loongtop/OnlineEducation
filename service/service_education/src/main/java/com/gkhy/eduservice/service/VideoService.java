package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.VideoEntity;
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
public interface VideoService {

    Long count(Long chapterId);

    void deleteById(Long chapterId);

    List<VideoEntity> findAll(Specification<VideoEntity> specEduVideo);

    void save(VideoEntity eduVideo);

    void removeById(Long id);
}
