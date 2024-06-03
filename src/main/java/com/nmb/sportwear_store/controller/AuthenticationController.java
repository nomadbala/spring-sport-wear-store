package com.nmb.sportwear_store.controller;

import com.nmb.sportwear_store.dto.JwtToken;
import com.nmb.sportwear_store.dto.UserDTO;
import com.nmb.sportwear_store.dto.requests.LoginRequest;
import com.nmb.sportwear_store.dto.requests.RegistrationRequest;
import com.nmb.sportwear_store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final UserService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO register(@RequestBody RegistrationRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtToken login(@RequestBody LoginRequest request) {
        return service.authenticate(request);
    }
}
