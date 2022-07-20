package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.EduSubject;
import com.gkhy.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * Course subjects Service
 * </p>
 *
 * @author leo
 * @since 2022-07-19
 */

public interface EduSubjectService {
    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    //tree
    List<OneSubject> getAllOneTwoSubject();

    void save(EduSubject existOneSubject);
}
