package com.anhanguera.crescerbem.controllers;

import com.anhanguera.crescerbem.payloads.vaccine.request.CreateVaccineDto;
import com.anhanguera.crescerbem.payloads.vaccine.request.UpdateVaccineDto;
import com.anhanguera.crescerbem.payloads.vaccine.response.VaccineResponseDto;
import com.anhanguera.crescerbem.services.VaccineService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/vaccines")
public class VaccineController extends BaseController<CreateVaccineDto, UpdateVaccineDto, VaccineResponseDto> {
    private final VaccineService service;

    public VaccineController(VaccineService service) {
        super(service);
        this.service = service;
    }
}
