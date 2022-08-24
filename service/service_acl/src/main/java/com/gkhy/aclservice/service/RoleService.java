package com.gkhy.aclservice.service;

import com.gkhy.aclservice.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findById(Long id);

    void save(Role role);

    void removeById(Long id);

    void removeByIds(List<String> idList);

    void saveUserRoleRealtionShip(String userId, String[] roleId);
}
