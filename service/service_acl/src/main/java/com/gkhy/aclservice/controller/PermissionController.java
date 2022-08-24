package com.gkhy.aclservice.controller;

import com.gkhy.aclservice.entity.Permission;
import com.gkhy.aclservice.service.PermissionService;
import com.gkhy.servicebase.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * Permission
 * </p>
 *
 * @author leo
 * @since 2022-07-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
/*@CrossOrigin*/
public class PermissionController {

    private final PermissionService permissionService;
    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }


    @GetMapping
    public Result listPermission() {
        return Result.success();
    }

    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        permissionService.removeById(id);
        return Result.success();
    }

    @PostMapping("/doAssign")
    public Result doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRelationShip(roleId,permissionId);
        return Result.success();
    }

    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return Result.success().data("children", list);
    }

    @PostMapping("save")
    public Result save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.success();
    }

    @PutMapping("update")
    public Result updateById(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.success();
    }
}

