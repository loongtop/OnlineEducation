package com.gkhy.aclservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.aclservice.entity.Permission;
import com.gkhy.aclservice.repository.PermissionRepository;
import com.gkhy.aclservice.service.PermissionService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<Permission, Long, PermissionRepository>
        implements PermissionService {

    @Autowired
    public PermissionServiceImpl(PermissionRepository iRepository) {
        super(iRepository);
    }

    public void saveRolePermissionRelationShip(String roleId, String[] permissionId) {

    }

    @Override
    public List<Permission> selectAllMenu(Long roleId) {
        return null;
    }


    public List<JSONObject> findByIdJSON(Long id) {
        return new ArrayList<JSONObject>();
    }
}
