package com.gkhy.aclservice.service.impl;

import com.gkhy.aclservice.entity.Role;
import com.gkhy.aclservice.repository.RoleRepository;
import com.gkhy.aclservice.service.RoleService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl extends ServiceImpl<Role, Long, RoleRepository>
        implements RoleService {
    @Autowired
    public RoleServiceImpl(RoleRepository iRepository) {
        super(iRepository);
    }

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
}
