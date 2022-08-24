package com.gkhy.aclservice.controller;

import com.gkhy.aclservice.entity.Role;
import com.gkhy.aclservice.entity.User;
import com.gkhy.aclservice.service.RoleService;
import com.gkhy.aclservice.service.UserService;
import com.gkhy.commonutils.encryption.MD5;
import com.gkhy.servicebase.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * User
 * </p>
 *
 * @author leo
 * @since 2022-07-08
 */
@RestController
@RequestMapping("/admin/acl/user")
/*@CrossOrigin*/
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        User userQueryVo) {
        return Result.success();
    }

    @PostMapping("save")
    public Result save(@RequestBody User user) {
        user.setPassword(MD5.encrypt(user.getPassword()));
        userService.save(user);
        return Result.success();
    }

    @PutMapping("update")
    public Result updateById(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        userService.removeById(id);
        return Result.success();
    }

    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return Result.success();
    }

    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId) {
//        Optional<Role> roleMap = roleService.findById(userId);
        return Result.success();
    }

    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam String userId,@RequestParam String[] roleId) {
        roleService.saveUserRoleRealtionShip(userId,roleId);
        return Result.success();
    }
}

