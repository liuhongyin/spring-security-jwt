package com.liuhongyin.springsecurity.mapper;

import com.liuhongyin.springsecurity.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author marcot
 * @since 1/5/20
 */
@Repository
public interface UserMapper {
    User findByUsername(String username);
}
