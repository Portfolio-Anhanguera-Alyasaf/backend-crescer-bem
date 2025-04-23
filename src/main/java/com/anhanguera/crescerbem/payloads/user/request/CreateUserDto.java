package com.anhanguera.crescerbem.payloads.user.request;

import com.anhanguera.crescerbem.entities.enums.Kind;

public record CreateUserDto(
        String email,
        String password,
        String name,
        int childrenNumber,
        Kind kind
) {
}
