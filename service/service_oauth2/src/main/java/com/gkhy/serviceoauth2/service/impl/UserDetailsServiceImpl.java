package com.gkhy.serviceoauth2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.gkhy.servicebase.service.ServiceImpl;
import com.gkhy.serviceoauth2.exception.ResourceNotFoundException;
import com.gkhy.serviceoauth2.entity.User;
import com.gkhy.serviceoauth2.entity.UserFactory;
import com.gkhy.serviceoauth2.repository.UserRepository;
import com.gkhy.serviceoauth2.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl
        extends ServiceImpl<User, Long, UserRepository>
        implements UserDetailsService {

    @Autowired
    public UserDetailsServiceImpl(UserRepository iRepository) {
        super(iRepository);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Optional<User> user = this.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email : " + email);
        }
        return UserFactory.of(user.get());
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = this.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserFactory.of(user);
    }

}