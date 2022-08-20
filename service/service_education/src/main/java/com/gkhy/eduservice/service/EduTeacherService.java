package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.entity.vo.TeacherVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface EduTeacherService {
    
    Optional<EduTeacher> findById(String id);

    void deleteById(String id);

    void removeById(String id);

    Optional<EduTeacher> findOne(Specification<EduTeacher> spec);

    List<EduTeacher> findAll();

    List<EduTeacher> findAll(Specification<EduTeacher> spec);

    Page<EduTeacher> findAll(Pageable pageable);

    Page<EduTeacher> findAll(Specification<EduTeacher> spec, Pageable pageable);

    List<EduTeacher> findAll(Specification<EduTeacher> spec, Sort sort);

    Page<EduTeacher> findAll(TeacherVo teacherQuery, int current, int limit);

    void save(EduTeacher eduTeacher);
}
