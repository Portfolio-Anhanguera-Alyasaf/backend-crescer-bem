package com.anhanguera.crescerbem.services;

import com.anhanguera.crescerbem.payloads.auth.request.AuthRefreshRequestDto;
import com.anhanguera.crescerbem.payloads.auth.request.AuthRequestDto;
import com.anhanguera.crescerbem.payloads.auth.response.AuthResponseDto;

public interface AuthService {
    AuthResponseDto login(AuthRequestDto credentials);

    AuthResponseDto generateRefreshToken(AuthRefreshRequestDto refreshTokenDto);
}
