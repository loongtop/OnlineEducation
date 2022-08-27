package com.gkhy.aclservice.service.impl;

import com.gkhy.servicebase.service.ServiceImpl;
import com.gkhy.aclservice.entity.UserEntity;
import com.gkhy.aclservice.repository.UserRepository;
import com.gkhy.aclservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl
        extends ServiceImpl<UserEntity, Long, UserRepository>
        implements UserService {

    public UserServiceImpl(UserRepository iRepository) {
        super(iRepository);
    }

    @Override
    public void removeByIds(List<Long> idList) {

    }

    @Override
    public UserEntity selectByUsername(String username) {
        return null;
    }
}
