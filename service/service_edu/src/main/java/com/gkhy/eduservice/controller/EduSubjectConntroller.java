package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.result.Result;
import com.gkhy.eduservice.entity.subject.OneSubject;
import com.gkhy.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectConntroller {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("add")
    public Result addSubject(MultipartFile file) {
        eduSubjectService.saveSubject(file, eduSubjectService);
        return Result.success();
    }

    @GetMapping("list")
    public Result list() {
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return Result.success().data("list", list);
    }
}
