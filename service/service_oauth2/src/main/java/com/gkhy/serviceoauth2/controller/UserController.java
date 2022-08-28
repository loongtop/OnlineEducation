package com.gkhy.serviceoauth2.controller;

import com.gkhy.serviceoauth2.entity.User;
import com.gkhy.serviceoauth2.entity.UserFactory;
import com.gkhy.serviceoauth2.repository.UserRepository;
import com.gkhy.serviceoauth2.service.UserDetailsService;
import com.gkhy.serviceoauth2.token.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final UserDetailsService userDetailsService;
    @Autowired
    public UserController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public Optional<User> getCurrentUser(@CurrentUser UserFactory userPrincipal) {

        return userDetailsService.findById(userPrincipal.getId());
    }
}
