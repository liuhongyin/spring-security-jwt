package com.liuhongyin.springsecurity.controller;

import com.liuhongyin.springsecurity.dto.LoginDTO;
import com.liuhongyin.springsecurity.dto.RegisterDTO;
import com.liuhongyin.springsecurity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author marcot
 * @since 1/4/20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public String test() {
        return "test";
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterDTO registerDto
    ) {

        return userService.register(registerDto);
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginDTO loginDto
    ){
        return userService.login(loginDto);
    }
}
