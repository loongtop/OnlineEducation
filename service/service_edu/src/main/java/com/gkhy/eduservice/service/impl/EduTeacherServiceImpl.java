package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.repository.EduTeacherRepository;
import com.gkhy.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EduTeacherServiceImpl implements EduTeacherService {
    @Autowired
    private EduTeacherRepository eduTeacherRepository;

    public List<EduTeacher> list() {
        return eduTeacherRepository.findAll();
    }
}
