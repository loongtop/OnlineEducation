package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.entity.vo.TeacherVo;
import com.gkhy.eduservice.repository.EduTeacherRepository;
import com.gkhy.eduservice.service.EduTeacherService;
import com.gkhy.servicebase.service.ServiceImpl;
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
public class EduTeacherServiceImpl extends
        ServiceImpl<EduTeacher, String, EduTeacherRepository>
        implements EduTeacherService {

    @Autowired
    public EduTeacherServiceImpl(EduTeacherRepository eduTeacherRepository) {
        super(eduTeacherRepository);
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

        return this.findAll(specification, pageable);
    }
    
}
