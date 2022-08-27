package com.gkhy.aclservice.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.gkhy.servicebase.redis.RedisService;
import com.gkhy.aclservice.entity.RoleEntity;
import com.gkhy.aclservice.entity.UserEntity;
import com.gkhy.aclservice.service.IndexService;
import com.gkhy.aclservice.service.PermissionService;
import com.gkhy.aclservice.service.RoleService;
import com.gkhy.aclservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {

    private UserService userService;
    private RoleService roleService;
    private PermissionService permissionService;
    private RedisService redisService;

    @Autowired
    public IndexServiceImpl(UserService userService, RoleService roleService, PermissionService permissionService, RedisService redisTemplate) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.redisService = redisTemplate;
    }

    /**
     * 根据用户名获取用户登录信息
     *
     * @param :username
     * @return
     */
    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> result = new HashMap<>();
        UserEntity user = userService.selectByUsername(username);
        if (null == user) {
            //throw new GuliException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }

        //根据用户id获取角色
        List<RoleEntity> roleList = roleService.findRolesById(user.getId());
        List<String> roleNameList = roleList.stream().map(item -> item.getRoleName()).collect(Collectors.toList());
        if (roleNameList.size() == 0) {
            //前端框架必须返回一个角色，否则报错，如果没有角色，返回一个空角色
            roleNameList.add("");
        }

        //根据用户id获取操作权限值
        List<Long> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        redisService.set(username, permissionValueList);

        result.put("name", user.getUsername());
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    /**
     * 根据用户名获取动态菜单
     *
     * @param username
     * @return
     */
    @Override
    public List<JSONObject> getMenu(String username) {
        UserEntity user = userService.selectByUsername(username);

        //根据用户id获取用户菜单权限
        List<JSONObject> permissionList = permissionService.selectPermissionByUserId(user.getId());
        return permissionList;
    }

}
