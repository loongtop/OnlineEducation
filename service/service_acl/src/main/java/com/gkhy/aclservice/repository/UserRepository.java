package com.gkhy.aclservice.repository;

import com.gkhy.servicebase.service.IRepositoryBase;
import com.gkhy.aclservice.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IRepositoryBase<UserEntity, Long> {
}
