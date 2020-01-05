package com.liuhongyin.springsecurity.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * @author marcot
 * @since 1/4/20
 */
@Getter
public class RegisterDTO {
    @NotNull(message = "username不能为空")
    public String username;
    @NotNull(message = "password不能为空")
    public String password;
    @NotNull(message = "手机号不能为空")
    public String phoneNumber;
}
