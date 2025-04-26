package com.anhanguera.crescerbem.services.impl;

import com.anhanguera.crescerbem.exceptions.TokenJwtInvalidException;
import com.anhanguera.crescerbem.payloads.auth.request.AuthRefreshRequestDto;
import com.anhanguera.crescerbem.payloads.auth.request.AuthRequestDto;
import com.anhanguera.crescerbem.payloads.auth.response.AuthResponseDto;
import com.anhanguera.crescerbem.services.AuthService;
import com.anhanguera.crescerbem.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Value("${security.jwt.expiration}")
    private long expiration;

    @Value("${security.jwt.refresh.expiration}")
    private long expirationRefreshToken;

    @Override
    public AuthResponseDto login(AuthRequestDto credentials) {
        var token = new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password());
        var auth = authenticationManager.authenticate(token);

        var tokenAndExpires = jwtService.generateToken((UserDetails) auth.getPrincipal(), expiration);
        return new AuthResponseDto((String) tokenAndExpires.get("token"),
                jwtService.generateRefreshToken((UserDetails) auth.getPrincipal(), expirationRefreshToken), (Date) tokenAndExpires.get("expiresToken"));
    }

    @Override
    public AuthResponseDto generateRefreshToken(AuthRefreshRequestDto refreshTokenDto) {
        var token = refreshTokenDto.refreshToken();
        if (!jwtService.isTokenValid(token) || !jwtService.isRefreshTokenValid(token))
            throw new TokenJwtInvalidException("Ops! Token inválido ou expirado.");

        var userDetails = jwtService.getUserDetails(token);
        var user = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(user);

        var tokenAndExpires = jwtService.generateToken((UserDetails) user.getPrincipal(), expiration);
        return new AuthResponseDto((String) tokenAndExpires.get("token"),
                jwtService.generateRefreshToken((UserDetails) user.getPrincipal(), expirationRefreshToken), (Date) tokenAndExpires.get("expiresToken"));
    }
}
