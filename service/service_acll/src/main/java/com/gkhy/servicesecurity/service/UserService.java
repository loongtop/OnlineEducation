package com.gkhy.servicesecurity.service;

import com.gkhy.servicesecurity.entity.UserEntity;

import java.util.List;

public interface UserService {
    void save(UserEntity user);

    void removeById(Long id);

    void removeByIds(List<Long> idList);

    UserEntity selectByUsername(String username);
}
