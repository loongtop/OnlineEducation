package com.gkhy.aclservice.controller;

import com.gkhy.aclservice.entity.Role;
import com.gkhy.aclservice.service.RoleService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  Role
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/role")
/*@CrossOrigin*/
public class RoleController {

    private final RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit, Role role) {
        return Result.success();
    }

    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        if (role.isEmpty()) return ItemFound.fail();
        return Result.success().data("item", role);
    }

    @PostMapping("save")
    public Result save(@RequestBody Role role) {
        roleService.save(role);
        return Result.success();
    }

    @PutMapping("update")
    public Result updateById(@RequestBody Role role) {
        roleService.save(role);
        return Result.success();
    }

    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        roleService.removeById(id);
        return Result.success();
    }

    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        roleService.removeByIds(idList);
        return Result.success();
    }
}

