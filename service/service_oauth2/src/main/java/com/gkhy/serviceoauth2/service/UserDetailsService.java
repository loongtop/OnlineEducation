package com.gkhy.serviceoauth2.service;


import com.gkhy.serviceoauth2.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserDetailsService
        extends org.springframework.security.core.userdetails.UserDetailsService{

    UserDetails loadUserById(Long userId);

    Optional<User> findByEmail(String email);

    void save(User user);

    boolean existsByEmail(String email);

    Optional<User> findById(Long id);
}
