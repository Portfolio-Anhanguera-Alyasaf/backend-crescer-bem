package com.anhanguera.crescerbem.payloads;

public record PagerResponseDto(
        Integer page,
        Integer pageSize,
        Long totalElements,
        Integer totalPages
) {
}
