package com.gkhy.servicesecurity.service;

import com.gkhy.servicesecurity.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    void saveUserRoleRelationShip(Long userId, Long[] roleId);

    Optional<RoleEntity> findById(Long userId);

    void save(RoleEntity role);

    void update(RoleEntity role);

    void removeById(Long id);

    void removeByIds(List<Long> idList);

    List<RoleEntity> findRolesById(Long id);
}
