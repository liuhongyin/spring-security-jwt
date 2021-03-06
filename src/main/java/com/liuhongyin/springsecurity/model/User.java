package com.liuhongyin.springsecurity.model;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author marcot
 * @since 1/5/20
 */
@Data
public class User {
    public Integer id;
    public String username;
    public String password;
    public Set<Authority> authorities;
}
