package com.liuhongyin.springsecurity.dto;

import lombok.Data;
import lombok.Getter;

/**
 * @author marcot
 * @since 1/4/20
 */
@Getter
public class LoginDTO {
    public String username;
    public String password;
    public Boolean rememberMe;
}
