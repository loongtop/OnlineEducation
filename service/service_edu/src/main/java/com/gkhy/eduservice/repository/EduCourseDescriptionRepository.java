package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.EduCourseDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface EduCourseDescriptionRepository extends
        JpaSpecificationExecutor<EduCourseDescription>,
        JpaRepository<EduCourseDescription, String> {
}
