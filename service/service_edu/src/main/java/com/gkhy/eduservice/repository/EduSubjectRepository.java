package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.EduSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EduSubjectRepository extends
        JpaSpecificationExecutor<EduSubject>,
        JpaRepository<EduSubject, Long> {

}

