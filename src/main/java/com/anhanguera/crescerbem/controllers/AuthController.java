package com.anhanguera.crescerbem.controllers;

import com.anhanguera.crescerbem.payloads.auth.request.AuthRefreshRequestDto;
import com.anhanguera.crescerbem.payloads.auth.request.AuthRequestDto;
import com.anhanguera.crescerbem.payloads.auth.response.AuthResponseDto;
import com.anhanguera.crescerbem.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto credentials) {
        return ResponseEntity.ok().body(service.login(credentials));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refreshToken(@RequestBody AuthRefreshRequestDto refreshTokenDto) {
        return ResponseEntity.ok().body(service.generateRefreshToken(refreshTokenDto));
    }
}
