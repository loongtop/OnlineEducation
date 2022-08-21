package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.TeacherEntity;
import com.gkhy.servicebase.service.repository.IRepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends IRepositoryBase<TeacherEntity, Long> {
}
