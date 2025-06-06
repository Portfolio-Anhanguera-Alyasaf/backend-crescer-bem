package com.anhanguera.crescerbem.payloads.children.request;

import java.time.LocalDateTime;

public record UpdateChildrenDto(
        String name,
        LocalDateTime birthday,
        double weight,
        double height
) {
}
