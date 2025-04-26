package com.anhanguera.crescerbem.services.impl;

import com.anhanguera.crescerbem.entities.Consultation;
import com.anhanguera.crescerbem.entities.User;
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
import com.anhanguera.crescerbem.repositories.UserRepository;
import com.anhanguera.crescerbem.services.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository repository;
    private final UserRepository userRepository;
    private final ConsultationMapper mapper;

    @Override
    public PaginationDto<ConsultationResponseDto> getAll(int page, int pageSize) {
        User user = getUserLogged();

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Consultation> pager = repository.findAll(pageable);
        return new PaginationDto<>(
                pager.getContent()
                        .stream()
                        .filter(consultation -> consultation.getUser().getUserId().equals(user.getUserId()))
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
        User user = getUserLogged();
        Consultation consultationToSaved = mapper.requestDtoToEntity(createConsultationDto);

        consultationToSaved.setUser(user);
        Consultation consultation = repository.save(consultationToSaved);
        return new CreatedResponseDto(consultation.getConsultationId());
    }

    @Override
    @Transactional
    public UpdatedResponseDto update(UUID id, UpdateConsultationDto updateConsultationDto) {
        Consultation consultationExists = repository.findById(id)
                .orElseThrow();

        BeanUtils.copyProperties(updateConsultationDto, consultationExists, "consultationId", "user");
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

    private User getUserLogged() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Ops! Usuário não existe."));
    }
}
