package com.anhanguera.crescerbem.services.impl;

import com.anhanguera.crescerbem.entities.Vaccine;
import com.anhanguera.crescerbem.exceptions.EntityNotFoundException;
import com.anhanguera.crescerbem.mappers.VaccineMapper;
import com.anhanguera.crescerbem.payloads.CreatedResponseDto;
import com.anhanguera.crescerbem.payloads.PagerResponseDto;
import com.anhanguera.crescerbem.payloads.PaginationDto;
import com.anhanguera.crescerbem.payloads.UpdatedResponseDto;
import com.anhanguera.crescerbem.payloads.vaccine.request.CreateVaccineDto;
import com.anhanguera.crescerbem.payloads.vaccine.request.UpdateVaccineDto;
import com.anhanguera.crescerbem.payloads.vaccine.response.VaccineResponseDto;
import com.anhanguera.crescerbem.repositories.VaccineRepository;
import com.anhanguera.crescerbem.services.VaccineService;
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
public class VaccineServiceImpl implements VaccineService {
    private final VaccineRepository repository;
    private final VaccineMapper mapper;

    @Override
    public PaginationDto<VaccineResponseDto> getAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vaccine> pager = repository.findAll(pageable);
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
    public CreatedResponseDto create(CreateVaccineDto createVaccineDto) {
        Vaccine vaccine = repository.save(mapper.requestDtoToEntity(createVaccineDto));
        return new CreatedResponseDto(vaccine.getVaccineId());
    }

    @Override
    @Transactional
    public UpdatedResponseDto update(UUID id, UpdateVaccineDto updateVaccineDto) {
        Vaccine vaccineExists = repository.findById(id)
                .orElseThrow();

        BeanUtils.copyProperties(updateVaccineDto, vaccineExists, "vaccineId");
        repository.save(vaccineExists);
        return new UpdatedResponseDto(vaccineExists.getVaccineId());
    }

    @Override
    public VaccineResponseDto getById(UUID id) {
        return repository.findById(id)
                .map(mapper::entityToResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Ops! Vacina não encntrada."));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
