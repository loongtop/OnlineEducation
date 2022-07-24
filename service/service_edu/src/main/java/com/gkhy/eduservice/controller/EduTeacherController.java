package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.result.Result;

import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.entity.vo.TeacherVo;
import com.gkhy.eduservice.service.EduTeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.BooleanUtils.and;

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
        String message = "Did not find Teacher with ID %s";
        return Result.fail().data("Teacher", String.format(message, id));
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
        Pageable pageable = PageRequest.of(current-1, limit);

        Page<EduTeacher> eduTeacherList = eduTeacherService.findAll(pageable);

        long total = eduTeacherList.getNumberOfElements ();

        return Result.success().data("total",total).data("rows",eduTeacherList.getContent());
    }

    //Method of conditional query with pagination
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable int current, @PathVariable int limit,
                                       @RequestBody(required = false) TeacherVo teacherQuery) {

        Page<EduTeacher> eduTeacherList = eduTeacherService.findAll(teacherQuery, current, limit);

        long total = eduTeacherList.getNumberOfElements ();

        return Result.success().data("total",total).data("rows",eduTeacherList.getContent());
   }
}


