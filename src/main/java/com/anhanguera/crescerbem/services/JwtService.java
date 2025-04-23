package com.anhanguera.crescerbem.services;

import com.anhanguera.crescerbem.payloads.user.response.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token);

    UserResponseDto getUserDetails(String token);
}
