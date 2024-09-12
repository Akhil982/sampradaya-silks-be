package com.thespot.bookings.security.service;

import com.thespot.bookings.security.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    User createUser(User user);
}
