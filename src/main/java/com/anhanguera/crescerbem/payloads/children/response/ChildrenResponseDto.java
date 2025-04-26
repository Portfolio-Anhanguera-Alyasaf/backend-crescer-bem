package com.anhanguera.crescerbem.payloads.children.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChildrenResponseDto(
        UUID id,
        String name,
        LocalDateTime birthday,
        double weight,
        double height
) {
}
