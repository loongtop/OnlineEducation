package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.Result;
import com.gkhy.eduservice.entity.EduCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
//@CrossOrigin
public class EduLoginController {
    //login
    @PostMapping("login")
    public Result login() {
        return Result.success().data("token","admin-cy");
    }
    //info
    @GetMapping("info")
    public Result info() {
        return Result.success()
                .data("name","admin_info")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("logout")
    public Result logout() {
        return Result.success()
                .data("name","admin_logout")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
