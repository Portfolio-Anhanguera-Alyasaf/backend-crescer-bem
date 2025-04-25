package com.anhanguera.crescerbem.payloads.children.response;

import java.time.LocalDateTime;

public record ChildrenResponseDto(
        String name,
        LocalDateTime birthday,
        double weight,
        double height
) {
}
