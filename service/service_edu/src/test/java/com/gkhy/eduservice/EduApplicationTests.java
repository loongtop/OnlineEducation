package com.gkhy.eduservice;

import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.service.impl.EduTeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class EduApplicationTests {

    @Autowired
    private EduTeacherServiceImpl eduTeacherService;

    @Test
    void findAll() {
        List<EduTeacher> eduTeacherList =  eduTeacherService.list();
        System.out.println(eduTeacherList);
    }

    @Test
    public void findOne() {
        Specification<EduTeacher> spe = new Specification<EduTeacher>() {
            /**
             * return Predicate: Defined query criteria .
             * param "Root<EduTeacher>" root: Root object , The object that encapsulates the query criteria .
             * param "CriteriaQuery<?>" query: Define a basic query , Generally not used .
             * param "CriteriaBuilder" cb: Create a query condition .
             */
            @Override
            public Predicate toPredicate(Root<EduTeacher> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("name"), "王二");
            }
        };
        Optional<EduTeacher> eduTeacher = eduTeacherService.findOne(spe);
        System.out.println(eduTeacher);
    }

    @Test
    public void findOneByNameAndLevel() {
        Specification<EduTeacher> spe = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(cb.equal(root.get("name"), "王五"));
            list.add(cb.equal(root.get("level"), 1));
            int length = list.size();
            Predicate[] arr = new Predicate[length];
            return cb.and(list.toArray(arr));
        };
        Optional<EduTeacher> eduTeacher = eduTeacherService.findOne(spe);
        System.out.println(eduTeacher);
    }

    @Test
    public void findByNameOr() {
        Specification<EduTeacher> spe = (root, query, cb) -> cb.or(cb.equal(root.get("name"), "李四"), cb.equal(root.get("name"), "张三"));
        List<EduTeacher> list = eduTeacherService.findAll(spe);
        for (EduTeacher user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void findByNameLike() {
        Specification<EduTeacher> spe = (root, query, cb) -> cb.like(root.get("name"), "李%");
        List<EduTeacher> list = eduTeacherService.findAll(spe);
        for (EduTeacher user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void pageable() {
        Specification<EduTeacher> spe = (root, query, cb) -> cb.between(root.get("id"), 47, 51);
        Pageable pageable = PageRequest.of(0, 5);
        Page<EduTeacher> list = eduTeacherService.findAll(spe, pageable);
        for (EduTeacher user : list) System.out.println(user);
    }

    @Test
    public void sort() {
        Specification<EduTeacher> spe = (root, query, cb) -> cb.greaterThan(root.get("id"), 51);
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<EduTeacher> list = eduTeacherService.findAll(spe, sort);
        for (EduTeacher user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void pageableAndSort() {
        Specification<EduTeacher> spe = (root, query, cb) -> cb.ge(root.get("id"), 51);
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 3, sort);
        Page<EduTeacher> list = eduTeacherService.findAll(spe, pageable);
        for (EduTeacher user : list) System.out.println(user);
    }
}