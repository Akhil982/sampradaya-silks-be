package com.thespot.bookings.security.service.impl;

import com.thespot.bookings.security.dao.request.SignUpRequest;
import com.thespot.bookings.security.dao.request.SigninRequest;
import com.thespot.bookings.security.dao.response.JwtAuthenticationResponse;
import com.thespot.bookings.security.entity.Role;
import com.thespot.bookings.security.entity.User;
import com.thespot.bookings.security.exception.UnauthenticatedException;
import com.thespot.bookings.security.repo.UserRepo;
import com.thespot.bookings.security.service.AuthenticationService;
import com.thespot.bookings.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static com.thespot.bookings.security.constant.errors.AuthErrorMessage.UN_AUTHENTICATED;


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

