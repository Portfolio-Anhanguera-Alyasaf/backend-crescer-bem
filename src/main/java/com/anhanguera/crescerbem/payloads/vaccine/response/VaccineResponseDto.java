package com.anhanguera.crescerbem.payloads.vaccine.response;

import java.util.UUID;

public record VaccineResponseDto(
        UUID id,
        String name,
        String description
) {
}
