package com.gkhy.aclservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.aclservice.entity.Role;
import com.gkhy.aclservice.entity.User;
import com.gkhy.aclservice.service.IndexService;
import com.gkhy.aclservice.service.PermissionService;
import com.gkhy.aclservice.service.RoleService;
import com.gkhy.aclservice.service.UserService;
import com.gkhy.servicebase.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {

    private final UserService userService;
    private final RoleService roleService;
    private final PermissionService permissionService;
    private final RedisService redisService;

    @Autowired
    public IndexServiceImpl(UserService userService, RoleService roleService, PermissionService permissionService, RedisService redisService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.redisService = redisService;
    }

    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.findByUsername(username);
        if (null == user) {
            //throw new GuliException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }

        //根据用户id获取角色
        Optional<Role> roleList = roleService.findById(user.getId());
        List<String> roleNameList = roleList.stream().map(item -> item.getRoleName()).collect(Collectors.toList());
        if(roleNameList.size() == 0) {
            //前端框架必须返回一个角色，否则报错，如果没有角色，返回一个空角色
            roleNameList.add("");
        }

        //根据用户id获取操作权限值
        List<JSONObject> permissionValueList = permissionService.findByIdJSON(user.getId());
        redisService.set(username, permissionValueList);

        result.put("name", user.getUsername());
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    @Override
    public List<JSONObject> getMenu(String username) {
        User user = userService.findByUsername(username);

        //根据用户id获取用户菜单权限
        List<JSONObject> permissionList = permissionService.findByIdJSON(user.getId());
        return permissionList;
    }


}
