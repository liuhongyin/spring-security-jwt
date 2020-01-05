package com.liuhongyin.springsecurity.service;

import com.liuhongyin.springsecurity.dto.LoginDTO;
import com.liuhongyin.springsecurity.dto.RegisterDTO;
import com.liuhongyin.springsecurity.model.User;

/**
 * @author marcot
 * @since 1/4/20
 */
public interface IUserService {

    public String register(RegisterDTO registerDTO);

    String login(LoginDTO loginDto);

    User findByUsername(String login);
}
