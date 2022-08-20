package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.EduCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EduCourseRepository extends
        JpaSpecificationExecutor<EduCourse>,
        JpaRepository<EduCourse, String> {
}