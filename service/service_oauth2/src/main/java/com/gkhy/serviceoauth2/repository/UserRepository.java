package com.gkhy.serviceoauth2.repository;

import com.gkhy.servicebase.service.IRepositoryBase;
import com.gkhy.serviceoauth2.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends IRepositoryBase<User, Long> {

}
