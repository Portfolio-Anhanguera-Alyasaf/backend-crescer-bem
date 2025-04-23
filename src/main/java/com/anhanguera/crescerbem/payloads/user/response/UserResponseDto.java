package com.anhanguera.crescerbem.payloads.user.response;

import com.anhanguera.crescerbem.entities.enums.Kind;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String email,
        String name,
        int childrenNumber,
        Kind kind
) {
}
