package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.EduTeacher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;

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
}
