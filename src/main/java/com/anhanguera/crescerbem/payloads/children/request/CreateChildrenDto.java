package com.anhanguera.crescerbem.payloads.children.request;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record CreateChildrenDto(
        String name,
        LocalDateTime birthday,
        double weight,
        double height,
        Set<UUID> consultationsId,
        Set<UUID> vaccinesId
) {
}
