package com.anhanguera.crescerbem.controllers;

import com.anhanguera.crescerbem.payloads.consultation.request.CreateConsultationDto;
import com.anhanguera.crescerbem.payloads.consultation.request.UpdateConsultationDto;
import com.anhanguera.crescerbem.payloads.consultation.response.ConsultationResponseDto;
import com.anhanguera.crescerbem.services.ConsultationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/consultations")
public class ConsultationController extends BaseController<CreateConsultationDto, UpdateConsultationDto, ConsultationResponseDto> {
    private final ConsultationService service;

    public ConsultationController(ConsultationService service) {
        super(service);
        this.service = service;
    }
}
