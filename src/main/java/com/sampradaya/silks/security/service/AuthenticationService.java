package com.thespot.bookings.security.service;

import com.thespot.bookings.security.dao.request.SignUpRequest;
import com.thespot.bookings.security.dao.request.SigninRequest;
import com.thespot.bookings.security.dao.response.JwtAuthenticationResponse;
import com.thespot.bookings.security.entity.User;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    User getLoggedInUser();
}

