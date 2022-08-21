package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.CourseEntity;
import com.gkhy.servicebase.service.repository.IRepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends IRepositoryBase<CourseEntity, Long> {
}
