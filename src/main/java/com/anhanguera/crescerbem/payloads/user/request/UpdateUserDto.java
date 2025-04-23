package com.anhanguera.crescerbem.payloads.user.request;

import com.anhanguera.crescerbem.entities.enums.Kind;

public record UpdateUserDto(
        String email,
        String name,
        int childrenNumber,
        Kind kind
) {
}
