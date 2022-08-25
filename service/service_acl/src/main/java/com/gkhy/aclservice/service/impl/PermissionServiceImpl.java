package com.gkhy.aclservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.aclservice.entity.Permission;
import com.gkhy.aclservice.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    public void saveRolePermissionRelationShip(String roleId, String[] permissionId) {

    }

    @Override
    public List<Permission> selectAllMenu(Long roleId) {
        return null;
    }


    public List<JSONObject> findByIdJSON(Long id) {
        return new ArrayList<JSONObject>();
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void save(Permission permission) {

    }
}
