package com.anhanguera.crescerbem.payloads.auth.response;

import java.util.Date;

public record AuthResponseDto(
        String token,
        String refreshToken,
        Date expirationToken
) {
}
