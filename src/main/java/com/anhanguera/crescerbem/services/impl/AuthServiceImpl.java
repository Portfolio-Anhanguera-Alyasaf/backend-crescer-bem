package com.anhanguera.crescerbem.services.impl;

import com.anhanguera.crescerbem.payloads.auth.request.AuthRequestDto;
import com.anhanguera.crescerbem.payloads.auth.response.AuthResponseDto;
import com.anhanguera.crescerbem.services.AuthService;
import com.anhanguera.crescerbem.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthResponseDto login(AuthRequestDto credentials) {
        var token = new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password());
        var auth = authenticationManager.authenticate(token);

        return new AuthResponseDto(jwtService.generateToken((UserDetails) auth.getPrincipal()));
    }
}
