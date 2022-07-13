package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.repository.EduTeacherRepository;
import com.gkhy.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EduTeacherServiceImpl implements EduTeacherService {
    @Autowired
    private EduTeacherRepository eduTeacherRepository;

    public List<EduTeacher> list() {
        return eduTeacherRepository.findAll();
    }

    @Override
    public Optional<EduTeacher> findById(Long id) {
        return eduTeacherRepository.findById(id);
    }
}
