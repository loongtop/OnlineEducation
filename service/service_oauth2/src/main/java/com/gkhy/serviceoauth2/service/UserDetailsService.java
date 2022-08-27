package com.gkhy.serviceoauth2.service;


import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService
        extends org.springframework.security.core.userdetails.UserDetailsService{
    UserDetails loadUserById(Long userId);
}
