package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.result.Result;
import com.gkhy.eduservice.entity.CourseEntity;
import com.gkhy.eduservice.entity.vo.CourseInfoVo;
import com.gkhy.eduservice.entity.vo.CoursePublishVo;
import com.gkhy.eduservice.service.CourseService;
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
public final class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //TODO  Perfect conditional query with pagination
    @GetMapping
    public Result getCourseList() {
        List<CourseEntity> list = courseService.findAll();
        return Result.success().data("list",list);
    }

    @PostMapping("addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {

        Long id = courseService.saveCourseInfo(courseInfoVo);
        return Result.success().data("courseId",id);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable Long courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return Result.success().data("courseInfoVo",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return Result.success();
    }

    @GetMapping("getPublishCourseInfo/{id}")
    public Result getPublishCourseInfo(@PathVariable Long id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return Result.success().data("publishCourse",coursePublishVo);
    }

    //The course is finally published   Modify the course status
    @PostMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable Long id) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(id);

        //Set Course Publishing Status
        courseEntity.setStatus("Normal");
        courseService.save(courseEntity);
        return Result.success();
    }

    @DeleteMapping("{courseId}")
    public Result deleteCourse(@PathVariable Long courseId) {
        courseService.removeCourse(courseId);
        return Result.success();
    }
}

