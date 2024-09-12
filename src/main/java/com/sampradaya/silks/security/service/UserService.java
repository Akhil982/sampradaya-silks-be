package com.sampradaya.silks.security.service;

import com.sampradaya.silks.security.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    User createUser(User user);
}
