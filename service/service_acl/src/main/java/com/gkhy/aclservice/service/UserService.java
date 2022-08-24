package com.gkhy.aclservice.service;

import com.gkhy.aclservice.entity.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    void save(User user);

    void removeById(String id);

    void removeByIds(List<String> idList);
}
