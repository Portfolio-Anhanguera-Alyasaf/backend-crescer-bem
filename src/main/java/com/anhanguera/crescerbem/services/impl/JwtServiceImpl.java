package com.anhanguera.crescerbem.services.impl;

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

    @Override
    public String generateToken(UserDetails userDetails, long expiration) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(generateExpirationToken(expiration))
                .claim("type", "access")
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
    public UserDetails getUserDetails(String token) {
        return repository.findByEmail(getClaims(token).getSubject())
                .orElseThrow(() -> new UsernameNotFoundException("Ops! Usuário não encontrado."));
    }

    @Override
    public boolean isRefreshTokenValid(String token) {
        var claims = getClaims(token);
        var type = (String) claims.get("type");

        return type.equals("refresh");
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails, long expiration) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(generateExpirationToken(expiration))
                .claim("type", "refresh")
                .signWith(getSecretKey())
                .compact();
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

    private Date generateExpirationToken(long expiration) {
        return new Date(System.currentTimeMillis() + expiration);
    }
}
