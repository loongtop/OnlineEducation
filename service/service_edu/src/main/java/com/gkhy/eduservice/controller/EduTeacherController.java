package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.R;

import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.entity.vo.TeacherQuery;
import com.gkhy.eduservice.service.EduTeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * teacher controller
 * </p>
 *
 * @author testjava
 * @since 2022-07-11
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //access site： http://localhost:8081/eduservice/teacher/findAll
    //Query all data in the lecturer table
    @GetMapping("findAll")
    public R findAllTeacher() {
        //Call the method of service to query all operations
        List<EduTeacher> list = eduTeacherService.list();
        return R.ok().data("items", list);
    }

    //Add the method of the lecturer interface
    @PostMapping("add")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        eduTeacherService.save(eduTeacher);
        boolean save = true;
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //Query by instructor id
    @GetMapping("get/{id}")
    public R getTeacher(@PathVariable Long id) {

        Optional<EduTeacher> eduTeacher = eduTeacherService.findById(id);
        if (eduTeacher.isPresent()) {
            return R.ok().data("teacher", eduTeacher);
        }
        return R.error();
    }


    //Teacher modification function
    @PostMapping("update")
    public R updateTeacher(@RequestBody EduTeacher eduTeacherIn) {
        Long id = eduTeacherIn.getId();
        Optional<EduTeacher> eduTeacher = eduTeacherService.findById(id);
        if (eduTeacher.isPresent()) {
            eduTeacherService.update(eduTeacherIn);
            return R.ok().data("teacher", eduTeacher);
        }
        return R.error();
    }

    //logically delete a teacher (IsDeleted = true)
    @DeleteMapping("remove/{id}")
    public R removeTeacher(@PathVariable Long id) {
        Optional<EduTeacher> eduTeacher = eduTeacherService.findById(id);
        if (eduTeacher.isPresent()) {
            eduTeacherService.removeTeacher(eduTeacher.get());
            return R.ok();
        }
        return R.error().data("teacher", "Can not find it!");
    }

    // delete a teacher from database
    @DeleteMapping("delete/{id}")
    public R deleteTeacher(@PathVariable Long id) {
        eduTeacherService.deleteById(id);
        boolean flag = true;
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //Method for querying lecturers by page
    @GetMapping("page/{current}/{limit}")
    public R pageListTeacher(@PathVariable int current, @PathVariable int limit) {
        /*
        *   current : current page
            limit : records per page
        * */
        Pageable pageable = PageRequest.of(current, limit);

        List<EduTeacher> eduTeacherList = eduTeacherService.list(pageable);
        long total = eduTeacherList.size();
        return R.ok().data("total", total).data("rows",eduTeacherList);
    }

    //Method of conditional query with pagination
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable int current, @PathVariable int limit,
                                  @RequestBody(required = false)  TeacherQuery teacherQuery) {
        //Create page object
        Pageable pageable = PageRequest.of(current, limit);

        EduTeacher eduTeacher = new EduTeacher(teacherQuery);

       // Multi-condition combination query

        //Create matchers, i.e. how to use query conditions
        ExampleMatcher matcher = ExampleMatcher.matching() //build object
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("level", ExampleMatcher.GenericPropertyMatchers.contains())
                //Ignore attribute: whether to care. Because it is a basic type, it needs to be ignored
                .withIgnorePaths("focus");
        //Create instance
        Example<EduTeacher> eduTeacherExample = Example.of(eduTeacher, matcher);

        List<EduTeacher> eduTeacherList = eduTeacherService.list(eduTeacherExample);
        long total = eduTeacherList.size();

        return R.ok().data("total",total).data("rows",eduTeacherList);
   }
}


