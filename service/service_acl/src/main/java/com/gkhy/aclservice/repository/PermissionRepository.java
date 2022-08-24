package com.gkhy.aclservice.repository;

import com.gkhy.aclservice.entity.Permission;
import com.gkhy.servicebase.service.repository.IRepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends IRepositoryBase<Permission, Long>{
}
