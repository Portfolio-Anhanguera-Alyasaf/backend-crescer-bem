package com.anhanguera.crescerbem.payloads.consultation.request;

import java.time.LocalDateTime;

public record CreateConsultationDto(
        String doctorName,
        String hospitalName,
        LocalDateTime dateConsult
) {
}
