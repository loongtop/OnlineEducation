package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.result.Result;
import com.gkhy.eduservice.entity.subject.OneSubject;
import com.gkhy.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * EduSubject controller
 * </p>
 *
 * @author leo
 * @since 2022-07-11
 */

@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file) {
        eduSubjectService.saveSubject(file, eduSubjectService);
        return Result.success();
    }

    @GetMapping("getAllSubject")
    // first class category (Tree)
    public Result list() {
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return Result.success().data("list", list);
    }
}
