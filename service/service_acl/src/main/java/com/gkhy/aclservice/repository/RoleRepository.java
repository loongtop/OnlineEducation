package com.gkhy.aclservice.repository;

import com.gkhy.aclservice.entity.Role;
import com.gkhy.servicebase.service.repository.IRepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends IRepositoryBase<Role, Long> {
}
