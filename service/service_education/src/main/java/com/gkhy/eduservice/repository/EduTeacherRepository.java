package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.servicebase.service.repository.IJpaRepository;
import com.gkhy.servicebase.service.repository.IRepositoryBase;

public interface EduTeacherRepository extends
        IRepositoryBase<EduTeacher, String>,
        IJpaRepository<EduTeacher, String> {
}
