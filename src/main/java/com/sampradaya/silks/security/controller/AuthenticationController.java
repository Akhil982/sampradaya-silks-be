package com.thespot.bookings.security.controller;

import com.thespot.bookings.api.bean.ResponseBean;
import com.thespot.bookings.security.dao.request.SignUpRequest;
import com.thespot.bookings.security.dao.request.SigninRequest;
import com.thespot.bookings.security.dao.response.JwtAuthenticationResponse;
import com.thespot.bookings.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseBean<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return new ResponseBean<>(200, "User registered successfully", authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseBean<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return new ResponseBean<>(200, "User loggedin successfully", authenticationService.signin(request));
    }

}
