package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.EduTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EduTeacherRepository extends
        JpaSpecificationExecutor<EduTeacher>,
        JpaRepository<EduTeacher, Long> {
}
