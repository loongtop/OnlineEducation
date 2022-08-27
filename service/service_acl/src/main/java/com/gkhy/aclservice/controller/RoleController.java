package com.gkhy.aclservice.controller;

import com.gkhy.servicebase.result.Result;
import com.gkhy.aclservice.entity.RoleEntity;
import com.gkhy.aclservice.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/acl/role")
public class RoleController {

    private final RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        RoleEntity role) {

        if(!StringUtils.isEmpty(role.getRoleName())) {

        }
        return Result.success();
    }

    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Optional<RoleEntity> role = roleService.findById(id);
        return Result.success().data("item", role);
    }

    @PostMapping("save")
    public Result save(@RequestBody RoleEntity role) {
        roleService.save(role);
        return Result.success();
    }

    @PutMapping("update")
    public Result updateById(@RequestBody RoleEntity role) {
        roleService.update(role);
        return Result.success();
    }

    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        roleService.removeById(id);
        return Result.success();
    }

    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        roleService.removeByIds(idList);
        return Result.success();
    }
}
