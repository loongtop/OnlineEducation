package com.gkhy.servicesecurity.service;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.servicesecurity.entity.PermissionEntity;

import java.util.List;

public interface PermissionService {
    List<PermissionEntity> queryAllMenu();

    void removeChildById(Long id);

    void saveRolePermissionEntityRelationShip(Long roleId, Long[] permissionEntityId);

    List<PermissionEntity> selectAllMenu(Long roleId);

    void save(PermissionEntity permissionEntity);

    List<JSONObject> selectPermissionByUserId(Long id);

    List<Long> selectPermissionValueByUserId(Long id);
}
