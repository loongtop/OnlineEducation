package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.TeacherEntity;
import com.gkhy.servicebase.service.repository.IJpaRepository;
import com.gkhy.servicebase.service.repository.IRepositoryBase;

public interface TeacherRepository extends
        IRepositoryBase<TeacherEntity, Long>,
        IJpaRepository<TeacherEntity, Long> {
}
