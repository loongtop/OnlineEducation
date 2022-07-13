package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.R;

import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.entity.vo.TeacherQuery;
import com.gkhy.eduservice.service.EduTeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
        return R.ok().data("items",list);
    }

    //Add the method of the lecturer interface
//    @PostMapping("addTeacher")
//    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
//        eduTeacherService.save(eduTeacher);
//        boolean save = true;
//        if(save) {
//            return R.ok();
//        } else {
//            return R.error();
//        }
//    }

    //Query by instructor id
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable Long id) {
        Optional<EduTeacher> eduTeacher = eduTeacherService.findById(id);
        if(eduTeacher.equals(Optional.empty())) {
            return R.error();
        }
        return R.ok().data("teacher",eduTeacher);
    }
//
//    //Teacher modification function
//    @PostMapping("updateTeacher")
//    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
//        boolean flag = eduTeacherService.updateById(eduTeacher);
//        if(flag) {
//            return R.ok();
//        } else {
//            return R.error();
//        }
//    }

//    //logically delete a teacher
//    @DeleteMapping("{id}")
//    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
//                                     @PathVariable String id) {
//        boolean flag = eduTeacherService.removeById(id);
//        if(flag) {
//            return R.ok();
//        } else {
//            return R.error();
//        }
//    }
//
//    //Method for querying lecturers by page
//    @GetMapping("pageTeacher/{current}/{limit}")
//    public R pageListTeacher(@PathVariable long current,
//                             @PathVariable long limit) {
//        /*
//        *   current : current page
//            limit : records per page
//        * */
//        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
//
//        int i = 10/0;
//
//        //Call the method to implement paging
//        //When calling the method,
//        // the bottom layer encapsulates all the data of the paging into the pageTeacher object
//        eduTeacherService.page(pageTeacher,null);
//
//        long total = pageTeacher.getTotal();
//        List<EduTeacher> records = pageTeacher.getRecords();
//
//        return R.ok().data("total",total).data("rows",records);
//    }
//
//    //Method of conditional query with pagination
//    @PostMapping("pageTeacherCondition/{current}/{limit}")
//    public R pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
//                                  @RequestBody(required = false)  TeacherQuery teacherQuery) {
//        //Create page object
//        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
//
//        //build condition
//        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

//       // Multi-condition combination query
//        String name = teacherQuery.getName();
//        Integer level = teacherQuery.getLevel();
//        String begin = teacherQuery.getBegin();
//        String end = teacherQuery.getEnd();
//
// Determine whether the condition value is empty,
//        //if not empty concatenate the condition
//        if(!StringUtils.isEmpty(name)) {
//            //build condition
//            wrapper.like("name",name);
//        }
//        if(!StringUtils.isEmpty(level)) {
//            wrapper.eq("level",level);
//        }
//        if(!StringUtils.isEmpty(begin)) {
//            wrapper.ge("gmt_create",begin);
//        }
//        if(!StringUtils.isEmpty(end)) {
//            wrapper.le("gmt_create",end);
//        }
//
//        //Call method to implement conditional query paging
//        eduTeacherService.page(pageTeacher,wrapper);
//
//        long total = pageTeacher.getTotal();
//        List<EduTeacher> records = pageTeacher.getRecords();
//        return R.ok().data("total",total).data("rows",records);
//    }
//
}

