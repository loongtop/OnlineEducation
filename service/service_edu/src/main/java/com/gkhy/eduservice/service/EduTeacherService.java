package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.EduTeacher;

import java.util.List;
import java.util.Optional;

public interface EduTeacherService {
    List<EduTeacher> list();

    Optional<EduTeacher> findById(Long id);
}
