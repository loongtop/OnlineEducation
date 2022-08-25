package com.gkhy.servicesecurity.repository;

import com.gkhy.servicebase.service.IRepositoryBase;
import com.gkhy.servicesecurity.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IRepositoryBase<UserEntity, Long> {
}
