package com.gkhy.serviceoauth2.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "ucentet_member",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_NAME" }) })
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String username;

    @Email
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    private String imageUrl;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACCOUNT_EXPIRED")
    private boolean accountNonExpired;

    @Column(name = "ACCOUNT_LOCKED")
    private boolean accountNonLocked;

    @Column(name = "CREDENTIALS_EXPIRED")
    private boolean credentialsNonExpired;

    @Column(name = "ENABLED")
    private boolean enabled;

    private String providerId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public User() {}
}
