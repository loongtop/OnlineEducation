package com.gkhy.aclservice.service.impl;

import com.gkhy.aclservice.entity.Role;
import com.gkhy.aclservice.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void removeByIds(List<String> idList) {

    }

    @Override
    public void saveUserRoleRealtionShip(String userId, String[] roleId) {

    }

    @Override
    public void save(Role role) {

    }
}
