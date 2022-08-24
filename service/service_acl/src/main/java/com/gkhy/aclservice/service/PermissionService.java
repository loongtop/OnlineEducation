package com.gkhy.aclservice.service;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.aclservice.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<JSONObject> findByIdJSON(Long id);

    void removeById(Long id);

    void saveRolePermissionRelationShip(String roleId, String[] permissionId);

    List<Permission> selectAllMenu(Long roleId);

    void save(Permission permission);

}
