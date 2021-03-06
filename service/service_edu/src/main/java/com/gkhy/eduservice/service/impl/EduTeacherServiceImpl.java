package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.repository.EduTeacherRepository;
import com.gkhy.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EduTeacherServiceImpl implements EduTeacherService {

    @Autowired
    private EduTeacherRepository eduTeacherRepository;

    @Override
    public List<EduTeacher> list() {
        return eduTeacherRepository.findAll();
    }

    @Override
    public List<EduTeacher> list(Pageable pageable) {
        return eduTeacherRepository.findAll(pageable).toList();
    }

    @Override
    public List<EduTeacher> list(Example<EduTeacher> eduTeacherExample) {
        return eduTeacherRepository.findAll(eduTeacherExample);
    }

    @Override
    public Optional<EduTeacher> findById(Long id) {
        return eduTeacherRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        eduTeacherRepository.deleteById(id);
    }

    @Override
    public void update(EduTeacher eduTeacher) {
        eduTeacherRepository.save(eduTeacher);
    }

    @Override
    public void save(EduTeacher eduTeacher) {
        eduTeacherRepository.save(eduTeacher);
    }

    @Override
    public void removeTeacher(EduTeacher eduTeacher) {
        eduTeacher.setIsDeleted(true);
        eduTeacherRepository.save(eduTeacher);
    }

    @Override
    public Optional<EduTeacher> findOne(Specification<EduTeacher> spec) {
        return eduTeacherRepository.findOne(spec);
    }

    @Override
    public List<EduTeacher> findAll(Specification<EduTeacher> spec) {
        return eduTeacherRepository.findAll(spec);
    }

    @Override
    public Page<EduTeacher> findAll(Specification<EduTeacher> spec, Pageable pageable) {
        return eduTeacherRepository.findAll(spec, pageable);
    }

    @Override
    public List<EduTeacher> findAll(Specification<EduTeacher> spec, Sort sort) {
        return eduTeacherRepository.findAll(spec, sort);
    }

    @Override
    public Page<EduTeacher> findAll(Pageable pageable) {
        return eduTeacherRepository.findAll(pageable);
    }
}
