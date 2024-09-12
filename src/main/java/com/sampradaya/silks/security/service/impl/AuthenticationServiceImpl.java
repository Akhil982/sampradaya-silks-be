package com.sampradaya.silks.security.service.impl;

import com.sampradaya.silks.security.dao.request.SignUpRequest;
import com.sampradaya.silks.security.dao.request.SigninRequest;
import com.sampradaya.silks.security.dao.response.JwtAuthenticationResponse;
import com.sampradaya.silks.security.entity.Role;
import com.sampradaya.silks.security.entity.User;
import com.sampradaya.silks.security.exception.UnauthenticatedException;
import com.sampradaya.silks.security.repo.UserRepo;
import com.sampradaya.silks.security.service.AuthenticationService;
import com.sampradaya.silks.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static com.sampradaya.silks.security.constant.errors.AuthErrorMessage.UN_AUTHENTICATED;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().userId(UUID.randomUUID().toString()).role(Role.valueOf(request.getRole())).firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).mobileNumber(request.getMobileNumber()).country(request.getCountry()).password(passwordEncoder.encode(request.getPassword())).build();
        User userSaved = userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().userId(userSaved.getUserId()).email(userSaved.getEmail())
                .role(user.getRole()).firstName(user.getFirstName()).lastName(user.getLastName())
                .token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().userId(user.getUserId()).email(user.getEmail())
                .role(user.getRole()).firstName(user.getFirstName()).lastName(user.getLastName()).token(jwt).build();
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication) || !authentication.isAuthenticated()) {
            throw new UnauthenticatedException(UN_AUTHENTICATED);
        }
        return (User)authentication.getPrincipal();
    }
}

