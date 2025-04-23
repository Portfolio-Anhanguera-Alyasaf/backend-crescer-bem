package com.anhanguera.crescerbem.payloads.consultation.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultationResponseDto(
        UUID id,
        String doctorName,
        String hospitalName,
        LocalDateTime dateConsult
) {
}
