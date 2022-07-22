package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.entity.vo.TeacherVo;
import com.gkhy.eduservice.repository.EduTeacherRepository;
import com.gkhy.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * EduTeacher Service Implementation Class
 * </p>
 *
 * @author leo
 * @since 2022-07-11
 */

@Service
@Transactional
public class EduTeacherServiceImpl implements EduTeacherService {

    @Autowired
    private EduTeacherRepository eduTeacherRepository;

    @Override
    public Page<EduTeacher> findAll(Specification<EduTeacher> spec, Pageable pageable) {
        return eduTeacherRepository.findAll(spec, pageable);
    }

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
    public Page<EduTeacher> findAll(TeacherVo teacherQuery, int current, int limit) {

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime beginLocal = LocalDateTime.parse(begin,df);
        LocalDateTime endLocal = LocalDateTime.parse(end,df);

        //Create Specification object
        Specification<EduTeacher> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if ((name != null) && ( StringUtils.hasLength(name))) {
                list.add(cb.like(root.get("name"), "%"+ name +"%"));
            }

            if (level != null) {
                list.add(cb.equal(root.get("level"), level));
            }

            if (StringUtils.hasLength(begin)) {
                list.add(cb.greaterThanOrEqualTo(root.get("gmtCreate"), beginLocal));
            }

            if (StringUtils.hasLength(end)) {
                list.add(cb.lessThanOrEqualTo(root.get("gmtModified"), endLocal));
            }

            Predicate[] arr = new Predicate[list.size()];
            return cb.and(list.toArray(arr));
        };

        Pageable pageable = PageRequest.of(current-1, limit);

        return eduTeacherRepository.findAll(specification, pageable);
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
