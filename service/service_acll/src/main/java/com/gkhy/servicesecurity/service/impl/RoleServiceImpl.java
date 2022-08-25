package com.gkhy.servicesecurity.service.impl;

import com.gkhy.servicebase.service.ServiceImpl;
import com.gkhy.servicesecurity.entity.RoleEntity;
import com.gkhy.servicesecurity.repository.RoleRepository;
import com.gkhy.servicesecurity.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl
        extends ServiceImpl<RoleEntity, Long, RoleRepository>
        implements RoleService {

    public RoleServiceImpl(RoleRepository iRepository) {
        super(iRepository);
    }

    @Override
    public void saveUserRoleRelationShip(Long userId, Long[] roleId) {

    }

    @Override
    public Optional<RoleEntity> findById(Long userId) {
        return Optional.empty();
    }

    @Override
    public void update(RoleEntity role) {

    }

    @Override
    public void removeByIds(List<Long> idList) {

    }

    @Override
    public List<RoleEntity> findRolesById(Long id) {
        return null;
    }
}
