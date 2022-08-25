package com.gkhy.servicesecurity.controller;

import com.gkhy.commonutils.encryption.MD5;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicesecurity.entity.RoleEntity;
import com.gkhy.servicesecurity.entity.UserEntity;
import com.gkhy.servicesecurity.service.RoleService;
import com.gkhy.servicesecurity.service.UserService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
@Value
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        UserEntity userQueryVo) {
        return Result.success();
    }

    @PostMapping("save")
    public Result save(@RequestBody UserEntity user) {
        user.setPassword(MD5.encrypt(user.getPassword()));
        userService.save(user);
        return Result.success();
    }

    @PutMapping("update")
    public Result updateById(@RequestBody UserEntity user) {
        userService.save(user);
        return Result.success();
    }

    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        userService.removeById(id);
        return Result.success();
    }

    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        userService.removeByIds(idList);
        return Result.success();
    }

    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId) {
        Optional<RoleEntity> roleMap = roleService.findById(userId);
        return Result.success();
    }

    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam Long userId,@RequestParam Long[] roleId) {
        roleService.saveUserRoleRelationShip(userId,roleId);
        return Result.success();
    }
}
