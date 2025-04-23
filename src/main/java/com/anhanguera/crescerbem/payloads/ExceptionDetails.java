package com.anhanguera.crescerbem.payloads;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExceptionDetails(
        String title,
        int status,
        String details,
        LocalDateTime timestamp
) {
}
