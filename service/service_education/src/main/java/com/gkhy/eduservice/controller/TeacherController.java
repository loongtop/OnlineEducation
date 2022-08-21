package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.result.Result;
import com.gkhy.servicebase.utils.ItemFound;

import com.gkhy.eduservice.entity.TeacherEntity;
import com.gkhy.eduservice.entity.vo.TeacherVo;
import com.gkhy.eduservice.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
public final class TeacherController {

    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService eduTeacherService) {
        this.teacherService = eduTeacherService;
    }

    //Query all data in the lecturer table
    @GetMapping("all")
    public Result findAllTeacher() {
        //Call the method of service to query all operations
        List<TeacherEntity> teachers = teacherService.findAll();
        return Result.success().data("teachers", teachers);
    }

    //Add the method of the lecturer interface
    @PostMapping("add")
    public Result addTeacher(@RequestBody TeacherEntity eduTeacher) {
        teacherService.save(eduTeacher);
        return Result.success();
    }

    //Query by instructor id
    @GetMapping("get/{id}")
    public Result getTeacher(@PathVariable Long id) {
        Optional<TeacherEntity> teacher = teacherService.findById(id);
        if (teacher.isEmpty()) {
            return ItemFound.fail();
        }

        return Result.success().data("teacher", teacher.get());
    }

    //Teacher modification function
    @PostMapping("update")
    public Result updateTeacher(@PathVariable Long id, @RequestBody TeacherEntity teacherIn) {
        Optional<TeacherEntity> teacher = teacherService.findById(id);
        if (teacher.isEmpty()) return ItemFound.fail();

        teacherService.save(teacherIn);
        return Result.success().data("teacher", teacherIn);
    }

    //logically delete a teacher (IsDeleted = true)
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@PathVariable Long id) {
        Optional<TeacherEntity> teacher = teacherService.findById(id);
        if (teacher.isEmpty()) return ItemFound.fail();

        teacherService.removeById(id);
        return Result.success();
    }

    // delete a teacher from database
    @DeleteMapping("delete/{id}")
    public Result deleteTeacher(@PathVariable Long id) {
        Optional<TeacherEntity> teacher = teacherService.findById(id);
        if (teacher.isEmpty()) return ItemFound.fail();

        teacherService.deleteById(id);
        return Result.success();
    }

    //Method for querying lecturers by page
    @GetMapping("page/{current}/{limit}")
    public Result getTeacherListPage(@PathVariable int current, @PathVariable int limit) {
        Pageable pageable = PageRequest.of(current-1, limit);
        Page<TeacherEntity> eduTeacherList = teacherService.findAll(pageable);
        long total = eduTeacherList.getNumberOfElements ();

        return Result.success().data("total",total).data("rows",eduTeacherList.getContent());
        }

    //Method of conditional query with pagination
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable int current, @PathVariable int limit,
                                       @RequestBody(required = false) TeacherVo teacherQuery) {

        Page<TeacherEntity> eduTeacherList = teacherService.findAll(teacherQuery, current, limit);

        long total = eduTeacherList.getNumberOfElements ();

        return Result.success().data("total",total).data("rows",eduTeacherList.getContent());
   }
}


