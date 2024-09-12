package com.sampradaya.silks.security.controller;

import com.sampradaya.silks.api.bean.ResponseBean;
import com.sampradaya.silks.security.dao.request.SignUpRequest;
import com.sampradaya.silks.security.dao.request.SigninRequest;
import com.sampradaya.silks.security.dao.response.JwtAuthenticationResponse;
import com.sampradaya.silks.security.service.AuthenticationService;
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
