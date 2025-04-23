package com.anhanguera.crescerbem.mappers;

import com.anhanguera.crescerbem.entities.Consultation;
import com.anhanguera.crescerbem.payloads.consultation.request.CreateConsultationDto;
import com.anhanguera.crescerbem.payloads.consultation.request.UpdateConsultationDto;
import com.anhanguera.crescerbem.payloads.consultation.response.ConsultationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ConsultationMapper implements BaseMapper<Consultation, CreateConsultationDto, ConsultationResponseDto> {
    @Override
    public Consultation requestDtoToEntity(CreateConsultationDto request) {
        return new Consultation(request.doctorName(), request.hospitalName(), request.dateConsult());
    }

    @Override
    public ConsultationResponseDto entityToResponseDto(Consultation consultation) {
        return new ConsultationResponseDto(consultation.getConsultationId(), consultation.getDoctorName(), consultation.getHospitalName(), consultation.getDateConsult());
    }

    @Override
    public CreateConsultationDto entityToRequestDto(Consultation consultation) {
        return new CreateConsultationDto(consultation.getDoctorName(), consultation.getHospitalName(), consultation.getDateConsult());
    }

    public Consultation updateDtoToEntity(UpdateConsultationDto updateRequest) {
        return new Consultation(updateRequest.doctorName(), updateRequest.hospitalName(), updateRequest.dateConsult());
    }

    public UpdateConsultationDto updateDtoToEntity(Consultation consultation) {
        return new UpdateConsultationDto(consultation.getDoctorName(), consultation.getHospitalName(), consultation.getDateConsult());
    }
}
