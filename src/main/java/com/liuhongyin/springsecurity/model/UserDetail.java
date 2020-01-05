package com.liuhongyin.springsecurity.model;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author marcot
 * @since 1/5/20
 */
public class UserDetail implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toSet());
    }

    public UserDetail() {
    }

    public UserDetail(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.password;
    }

    @Override
    public String getUsername() {
        return user.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
