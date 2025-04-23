package com.anhanguera.crescerbem.services.impl;

import com.anhanguera.crescerbem.entities.Consultation;
import com.anhanguera.crescerbem.exceptions.EntityNotFoundException;
import com.anhanguera.crescerbem.mappers.ConsultationMapper;
import com.anhanguera.crescerbem.payloads.CreatedResponseDto;
import com.anhanguera.crescerbem.payloads.PagerResponseDto;
import com.anhanguera.crescerbem.payloads.PaginationDto;
import com.anhanguera.crescerbem.payloads.UpdatedResponseDto;
import com.anhanguera.crescerbem.payloads.consultation.request.CreateConsultationDto;
import com.anhanguera.crescerbem.payloads.consultation.request.UpdateConsultationDto;
import com.anhanguera.crescerbem.payloads.consultation.response.ConsultationResponseDto;
import com.anhanguera.crescerbem.repositories.ConsultationRepository;
import com.anhanguera.crescerbem.services.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository repository;
    private final ConsultationMapper mapper;

    @Override
    public PaginationDto<ConsultationResponseDto> getAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Consultation> pager = repository.findAll(pageable);
        return new PaginationDto<>(
                pager.getContent()
                        .stream()
                        .map(mapper::entityToResponseDto).toList(),
                new PagerResponseDto(
                        pager.getNumber(), pager.getSize(),
                        pager.getTotalElements(), pager.getTotalPages()
                )
        );
    }

    @Override
    @Transactional
    public CreatedResponseDto create(CreateConsultationDto createConsultationDto) {
        Consultation consultation = repository.save(mapper.requestDtoToEntity(createConsultationDto));
        return new CreatedResponseDto(consultation.getConsultationId());
    }

    @Override
    @Transactional
    public UpdatedResponseDto update(UUID id, UpdateConsultationDto updateConsultationDto) {
        Consultation consultationExists = repository.findById(id)
                .orElseThrow();

        BeanUtils.copyProperties(updateConsultationDto, consultationExists, "consultationId");
        repository.save(consultationExists);
        return new UpdatedResponseDto(consultationExists.getConsultationId());
    }

    @Override
    public ConsultationResponseDto getById(UUID id) {
        return repository.findById(id)
                .map(mapper::entityToResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Ops! Consulta não marcada."));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
