package com.anhanguera.crescerbem.mappers;

import com.anhanguera.crescerbem.entities.Vaccine;
import com.anhanguera.crescerbem.payloads.vaccine.request.CreateVaccineDto;
import com.anhanguera.crescerbem.payloads.vaccine.request.UpdateVaccineDto;
import com.anhanguera.crescerbem.payloads.vaccine.response.VaccineResponseDto;
import org.springframework.stereotype.Component;

@Component
public class VaccineMapper implements BaseMapper<Vaccine, CreateVaccineDto, VaccineResponseDto> {
    @Override
    public Vaccine requestDtoToEntity(CreateVaccineDto request) {
        return new Vaccine(request.name(), request.description());
    }

    @Override
    public VaccineResponseDto entityToResponseDto(Vaccine vaccine) {
        return new VaccineResponseDto(vaccine.getVaccineId(), vaccine.getName(), vaccine.getDescription());
    }

    @Override
    public CreateVaccineDto entityToRequestDto(Vaccine vaccine) {
        return new CreateVaccineDto(vaccine.getName(), vaccine.getDescription());
    }

    public Vaccine updateDtoToEntity(UpdateVaccineDto updateRequest) {
        return new Vaccine(updateRequest.name(), updateRequest.description());
    }

    public UpdateVaccineDto updateDtoToEntity(Vaccine vaccine) {
        return new UpdateVaccineDto(vaccine.getName(), vaccine.getDescription());
    }
}
