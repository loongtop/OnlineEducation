package com.gkhy.aclservice.service;

import com.gkhy.aclservice.entity.UserEntity;

import java.util.List;

public interface UserService {
    void save(UserEntity user);

    void removeById(Long id);

    void removeByIds(List<Long> idList);

    UserEntity selectByUsername(String username);
}
