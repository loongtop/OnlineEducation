package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.result.Result;
import com.gkhy.eduservice.entity.EduCourse;
import com.gkhy.eduservice.entity.vo.CourseInfoVo;
import com.gkhy.eduservice.entity.vo.CoursePublishVo;
import com.gkhy.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * Course Front Controller
 * </p>
 *
 * @author leo
 * @since 2020-07-18
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    //TODO  Perfect conditional query with pagination
    @GetMapping
    public Result getCourseList() {
        List<EduCourse> list = courseService.findAll();
        return Result.success().data("list",list);
    }

    @PostMapping("addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {

        String id = courseService.saveCourseInfo(courseInfoVo);
        return Result.success().data("courseId",id);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return Result.success().data("courseInfoVo",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return Result.success();
    }

    @GetMapping("getPublishCourseInfo/{id}")
    public Result getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return Result.success().data("publishCourse",coursePublishVo);
    }

    //The course is finally published   Modify the course status
    @PostMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);

        //Set Course Publishing Status
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return Result.success();
    }

    @DeleteMapping("{courseId}")
    public Result deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return Result.success();
    }
}

