package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.CourseDescriptionEntity;
import org.springframework.stereotype.Repository;
import com.gkhy.servicebase.service.repository.IJpaRepository;
import com.gkhy.servicebase.service.repository.IRepositoryBase;


@Repository
public interface CourseDescriptionRepository extends
        IRepositoryBase<CourseDescriptionEntity, Long>,
        IJpaRepository<CourseDescriptionEntity, Long> {
}

