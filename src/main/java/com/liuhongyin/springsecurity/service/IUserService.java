package com.liuhongyin.springsecurity.service;

import com.liuhongyin.springsecurity.dto.LoginDTO;
import com.liuhongyin.springsecurity.dto.RegisterDTO;

/**
 * @author marcot
 * @since 1/4/20
 */
public interface IUserService {

    public String register(RegisterDTO registerDTO);

    String login(LoginDTO loginDto);
}
