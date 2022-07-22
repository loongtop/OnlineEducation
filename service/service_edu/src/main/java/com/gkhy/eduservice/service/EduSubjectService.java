package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.EduSubject;
import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.entity.subject.OneSubject;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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

    Optional<EduSubject> findOne(Specification<EduSubject> specification);
}
