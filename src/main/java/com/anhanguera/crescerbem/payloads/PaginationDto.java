package com.anhanguera.crescerbem.payloads;

import java.util.List;

public record PaginationDto<Response>(
        List<Response> data,
        PagerResponseDto pagination
) {
}
