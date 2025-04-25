package com.anhanguera.crescerbem.services;

import com.anhanguera.crescerbem.payloads.vaccine.request.CreateVaccineDto;
import com.anhanguera.crescerbem.payloads.vaccine.request.UpdateVaccineDto;
import com.anhanguera.crescerbem.payloads.vaccine.response.VaccineResponseDto;

public interface VaccineService extends BaseService<CreateVaccineDto, UpdateVaccineDto, VaccineResponseDto> {
}
