package com.sampradaya.silks.security.service;

import com.sampradaya.silks.security.dao.request.SignUpRequest;
import com.sampradaya.silks.security.dao.request.SigninRequest;
import com.sampradaya.silks.security.dao.response.JwtAuthenticationResponse;
import com.sampradaya.silks.security.entity.User;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    User getLoggedInUser();
}

