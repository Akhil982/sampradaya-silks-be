package com.sampradaya.silks.security.service.impl;


import com.sampradaya.silks.security.entity.User;
import com.sampradaya.silks.security.repo.UserRepo;
import com.sampradaya.silks.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
