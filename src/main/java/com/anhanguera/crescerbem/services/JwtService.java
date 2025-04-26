package com.anhanguera.crescerbem.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    Map<String, Object> generateToken(UserDetails userDetails, long expiration);

    boolean isTokenValid(String token);

    UserDetails getUserDetails(String token);

    boolean isRefreshTokenValid(String token);

    String generateRefreshToken(UserDetails userDetails, long expiration);
}
