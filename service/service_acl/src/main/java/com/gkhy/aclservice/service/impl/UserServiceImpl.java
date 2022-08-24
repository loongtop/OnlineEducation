package com.gkhy.aclservice.service.impl;

import com.gkhy.aclservice.entity.User;
import com.gkhy.aclservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public void removeById(String id) {

    }

    @Override
    public void removeByIds(List<String> idList) {

    }

    @Override
    public void save(User user) {

    }
}
