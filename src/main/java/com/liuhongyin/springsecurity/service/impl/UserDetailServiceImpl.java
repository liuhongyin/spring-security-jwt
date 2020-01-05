package com.liuhongyin.springsecurity.service.impl;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author marcot
 * @since 1/5/20
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        // 如果是邮箱登录
        if (new EmailValidator().isValid(login, null)) {
            // TODO  user = findUserByEmail()
            return null;
        }
        // TODO 如果是手机号登录 user = findUserByPhoneNumber()

        // TODO user = findByUsername()

        // TODO return new UserDetail(user);
        return null;
    }
}
