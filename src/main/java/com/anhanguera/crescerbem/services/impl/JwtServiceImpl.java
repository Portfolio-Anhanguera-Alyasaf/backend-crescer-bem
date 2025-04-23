package com.anhanguera.crescerbem.services.impl;

import com.anhanguera.crescerbem.payloads.user.response.UserResponseDto;
import com.anhanguera.crescerbem.repositories.UserRepository;
import com.anhanguera.crescerbem.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final UserRepository repository;

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private long expiration;

    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            return getClaims(token)
                    .getExpiration()
                    .after(new Date());
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    @Override
    public UserResponseDto getUserDetails(String token) {
        return repository.findByEmail(getClaims(token).getSubject())
                .map(user -> new UserResponseDto(user.getUserId(),
                        user.getEmail(),
                        user.getName(),
                        user.getChildrenNumber(),
                        user.getKind()))
                .orElseThrow(() -> new UsernameNotFoundException("Ops! Usuário não encontrado."));
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
