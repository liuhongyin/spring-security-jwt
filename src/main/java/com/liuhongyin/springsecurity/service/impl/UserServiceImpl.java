package com.liuhongyin.springsecurity.service.impl;

import com.liuhongyin.springsecurity.dto.LoginDTO;
import com.liuhongyin.springsecurity.dto.RegisterDTO;
import com.liuhongyin.springsecurity.mapper.UserMapper;
import com.liuhongyin.springsecurity.model.User;
import com.liuhongyin.springsecurity.service.IUserService;
import com.liuhongyin.springsecurity.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author marcot
 * @since 1/4/20
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    public String register(RegisterDTO registerDTO) {
        /**
         *TODO 检查用户手机号是否重复
         * TODO 保存到数据库 密码已加密
         */
        String encodePassword = passwordEncoder.encode(registerDTO.password);
        return "encodePassword: " + encodePassword;
    }

    @Override
    public String login(LoginDTO loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.username, loginDto.password);
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
//        return null;
        return jwtTokenUtil.generateToken(authenticate,loginDto.rememberMe);
    }

    @Override
    public User findByUsername(String login) {
        return userMapper.findByUsername(login);
    }
}
