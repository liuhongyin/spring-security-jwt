package com.liuhongyin.springsecurity.service.impl;

import com.liuhongyin.springsecurity.model.User;
import com.liuhongyin.springsecurity.model.UserDetail;
import com.liuhongyin.springsecurity.service.IUserService;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        // 如果是邮箱登录
        if (new EmailValidator().isValid(login, null)) {
            // TODO  user = findUserByEmail()
            return null;
        }
        // TODO 如果是手机号登录 user = findUserByPhoneNumber()

        User user = userService.findByUsername(login);
        return new UserDetail(user);
    }
}
