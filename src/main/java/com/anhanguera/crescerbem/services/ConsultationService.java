package com.anhanguera.crescerbem.services;

import com.anhanguera.crescerbem.payloads.consultation.request.CreateConsultationDto;
import com.anhanguera.crescerbem.payloads.consultation.request.UpdateConsultationDto;
import com.anhanguera.crescerbem.payloads.consultation.response.ConsultationResponseDto;

public interface ConsultationService extends BaseService<CreateConsultationDto, UpdateConsultationDto, ConsultationResponseDto> {
}
