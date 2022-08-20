package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.EduSubject;
import com.gkhy.eduservice.entity.EduTeacher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EduSubjectRepository extends
        JpaSpecificationExecutor<EduSubject>,
        JpaRepository<EduSubject, Long> {
}

