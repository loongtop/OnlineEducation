package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.SubjectEntity;
import com.gkhy.servicebase.service.repository.IJpaRepository;
import com.gkhy.servicebase.service.repository.IRepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends
        IRepositoryBase<SubjectEntity, Long>,
        IJpaRepository<SubjectEntity, Long> {
}

