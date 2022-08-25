package com.gkhy.servicesecurity.service;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.servicebase.service.ServiceImpl;
import com.gkhy.servicesecurity.entity.PermissionEntity;
import com.gkhy.servicesecurity.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl
        extends ServiceImpl<PermissionEntity, Long, PermissionRepository>
        implements PermissionService {
    public PermissionServiceImpl(PermissionRepository iRepository) {
        super(iRepository);
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(Long id) {
        return null;
    }

    @Override
    public List<Long> selectPermissionValueByUserId(Long id) {
        return null;
    }

    @Override
    public List<PermissionEntity> queryAllMenu() {
        return null;
    }

    @Override
    public void removeChildById(Long id) {

    }

    @Override
    public void saveRolePermissionEntityRelationShip(Long roleId, Long[] permissionEntityId) {

    }

    @Override
    public List<PermissionEntity> selectAllMenu(Long roleId) {
        return null;
    }

    @Override
    public void save(PermissionEntity permissionEntity) {

    }
}
