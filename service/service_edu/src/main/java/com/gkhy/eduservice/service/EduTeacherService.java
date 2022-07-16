package com.gkhy.eduservice.service;

import com.gkhy.commonutils.JPAExt.paging.PageUtil;
import com.gkhy.eduservice.entity.EduTeacher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface EduTeacherService {
    List<EduTeacher> list();

    List<EduTeacher> list(Pageable pageable);

    List<EduTeacher> list(Example<EduTeacher> eduTeacherExample);

    Optional<EduTeacher> findById(Long id);

    void deleteById(Long id);

    void update(EduTeacher eduTeacherIn);

    void save(EduTeacher eduTeacherIn);

    void removeTeacher(EduTeacher eduTeacher);

    PageUtil<EduTeacher> getTeacherListPage(int current, int limit);

    Optional<EduTeacher> findOne(Specification<EduTeacher> spec);

    List<EduTeacher> findAll(Specification<EduTeacher> spec);

    Page<EduTeacher> findAll(Pageable pageable);

    Page<EduTeacher> findAll(Specification<EduTeacher> spec, Pageable pageable);

    List<EduTeacher> findAll(Specification<EduTeacher> spec, Sort sort);
}
