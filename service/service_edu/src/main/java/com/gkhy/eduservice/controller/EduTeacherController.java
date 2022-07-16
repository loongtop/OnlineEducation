package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.Result;

import com.gkhy.commonutils.JPAExt.paging.PageUtil;
import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.entity.vo.TeacherVo;
import com.gkhy.eduservice.service.EduTeacherService;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.BooleanUtils.*;

/**
 * <p>
 * teacher controller
 * </p>
 *
 * @author leo
 * @since 2022-07-11
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //access site： http://localhost:8081/eduservice/teacher/findAll
    //Query all data in the lecturer table
    @GetMapping("list")
    public Result findAllTeacher() {
        //Call the method of service to query all operations
        List<EduTeacher> list = eduTeacherService.list();
        return Result.success().data("items", list);
    }

    //Add the method of the lecturer interface
    @PostMapping("add")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher) {
        eduTeacherService.save(eduTeacher);
        boolean save = true;
        if (save) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    //Query by instructor id
    @GetMapping("get/{id}")
    public Result getTeacher(@PathVariable Long id) {

        Optional<EduTeacher> eduTeacher = eduTeacherService.findById(id);
        if (eduTeacher.isPresent()) {
            return Result.success().data("teacher", eduTeacher);
        }
        return Result.fail();
    }


    //Teacher modification function
    @PostMapping("update")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacherIn) {
        Long id = eduTeacherIn.getId();
        Optional<EduTeacher> eduTeacher = eduTeacherService.findById(id);
        if (eduTeacher.isPresent()) {
            eduTeacherService.update(eduTeacherIn);
            return Result.success().data("teacher", eduTeacher);
        }
        return Result.fail();
    }

    //logically delete a teacher (IsDeleted = true)
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@PathVariable Long id) {
        Optional<EduTeacher> eduTeacher = eduTeacherService.findById(id);
        if (eduTeacher.isPresent()) {
            eduTeacherService.removeTeacher(eduTeacher.get());
            return Result.success();
        }
        return Result.fail().data("teacher", "Can not find it!");
    }

    // delete a teacher from database
    @DeleteMapping("delete/{id}")
    public Result deleteTeacher(@PathVariable Long id) {
        eduTeacherService.deleteById(id);
        boolean flag = true;
        if (flag) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    //Method for querying lecturers by page
    @GetMapping("page/{current}/{limit}")
    public Result getTeacherListPage(@PathVariable int current, @PathVariable int limit) {
        /*
        *   current : current page
            limit : records per page
        * */
        PageUtil<EduTeacher> teacherPageUtil = eduTeacherService.getTeacherListPage(current-1, limit);

        List<EduTeacher> eduTeacherList = teacherPageUtil.getContent();
        long total = teacherPageUtil.getTotalElements();

        return Result.success().data("total",total).data("rows",eduTeacherList);
    }

    //Method of conditional query with pagination
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable int current, @PathVariable int limit,
                                       @RequestBody(required = false) TeacherVo teacherQuery) {

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        Date begin = teacherQuery.getBegin();
        Date end = teacherQuery.getEnd();

        if (!BooleanUtils.and(new Boolean[]{
                StringUtils.hasLength(name),
                StringUtils.hasLength(Integer.toString(level))})) {
            return Result.fail().message("Missing parameter!");
        }

        //Create Specification object
        Specification<EduTeacher> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(cb.equal(root.get("name"), name));
            list.add(cb.equal(root.get("level"), level));
//                list.add(cb.greaterThan(root.get("gmtCreate"), begin));
//                list.add(cb.lessThanOrEqualTo(root.get("gmtModified"), end));

            Predicate[] arr = new Predicate[list.size()];
            return cb.and(list.toArray(arr));
        };

        Pageable pageable = PageRequest.of(current-1, limit);

        Page<EduTeacher> eduTeacherList = eduTeacherService.findAll(specification, pageable);

        long total = eduTeacherList.getTotalElements();

        return Result.success().data("total",total).data("rows",eduTeacherList);
   }
}


