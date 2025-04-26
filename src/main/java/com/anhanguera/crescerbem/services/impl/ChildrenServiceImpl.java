package com.anhanguera.crescerbem.services.impl;

import com.anhanguera.crescerbem.entities.*;
import com.anhanguera.crescerbem.exceptions.EntityNotFoundException;
import com.anhanguera.crescerbem.payloads.CreatedResponseDto;
import com.anhanguera.crescerbem.payloads.PagerResponseDto;
import com.anhanguera.crescerbem.payloads.PaginationDto;
import com.anhanguera.crescerbem.payloads.UpdatedResponseDto;
import com.anhanguera.crescerbem.payloads.children.request.CreateChildrenDto;
import com.anhanguera.crescerbem.payloads.children.request.UpdateChildrenDto;
import com.anhanguera.crescerbem.payloads.children.response.ChildrenResponseDto;
import com.anhanguera.crescerbem.repositories.*;
import com.anhanguera.crescerbem.services.ChildrenService;
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

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChildrenServiceImpl implements ChildrenService {
    private final ChildrenRepository repository;
    private final UserRepository userRepository;
    private final ConsultationRepository consultationRepository;
    private final ChildVaccineRepository childVaccineRepository;
    private final VaccineRepository vaccinesRepository;

    @Override
    public PaginationDto<ChildrenResponseDto> getAll(int page, int pageSize) {
        User user = getUserLogged();

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Children> pager = repository.findAll(pageable);
        return new PaginationDto<>(
                pager.getContent()
                        .stream()
                        .filter((children -> children.getUser().getUserId().equals(user.getUserId())))
                        .map(children -> new ChildrenResponseDto(children.getChildrenId(), children.getName(), children.getBirthday(), children.getWeight(), children.getHeight())).toList(),
                new PagerResponseDto(
                        pager.getNumber(), pager.getSize(),
                        pager.getTotalElements(), pager.getTotalPages()
                )
        );
    }

    @Override
    @Transactional
    public CreatedResponseDto create(CreateChildrenDto createChildrenDto) {
        User user = getUserLogged();
        Set<Consultation> consultations = getConsultations(createChildrenDto.consultationsId());
        Set<Vaccine> vaccines = getVaccines(createChildrenDto.vaccinesId());

        Children childrenToSaved = new Children(
                createChildrenDto.name(),
                createChildrenDto.birthday(),
                createChildrenDto.weight(),
                createChildrenDto.height(),
                user);

        childrenToSaved.setConsultations(consultations);
        Children childrenSaved = repository.save(childrenToSaved);

        Set<ChildVaccine> childVaccines = getChildVaccines(vaccines, childrenSaved);
        childrenSaved.setChildVaccines(childVaccines);

        return new CreatedResponseDto(childrenSaved.getChildrenId());
    }

    @Override
    @Transactional
    public UpdatedResponseDto update(UUID id, UpdateChildrenDto updateChildrenDto) {
        Children childrenExists = repository.findById(id)
                .orElseThrow();

        BeanUtils.copyProperties(updateChildrenDto, childrenExists, "childrenId", "consultations", "user", "childVaccines");
        repository.save(childrenExists);
        return new UpdatedResponseDto(childrenExists.getChildrenId());
    }

    @Override
    public ChildrenResponseDto getById(UUID id) {
        return repository.findById(id)
                .map(children -> new ChildrenResponseDto(children.getChildrenId(), children.getName(), children.getBirthday(), children.getWeight(), children.getHeight()))
                .orElseThrow(() -> new EntityNotFoundException("Ops! Consulta não marcada."));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void applyVaccine(UUID idChildren) {
        var childVaccine = childVaccineRepository.findByChildId(idChildren)
                .orElseThrow(() -> new EntityNotFoundException("Ops! Não foi possível localizar alguma vacina nessa criança."));

        childVaccine.setApplied(true);
        childVaccineRepository.save(childVaccine);
    }

    private Set<Consultation> getConsultations(Set<UUID> ids) {
        return ids.stream()
                .map(id -> consultationRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Ops! Consulta não encontrada"))).collect(Collectors.toSet());
    }

    private Set<Vaccine> getVaccines(Set<UUID> ids) {
        return ids.stream()
                .map(id -> vaccinesRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Ops! vacina não encontrada"))).collect(Collectors.toSet());
    }

    private Set<ChildVaccine> getChildVaccines(Set<Vaccine> vaccines, Children children) {
        return vaccines.stream()
                .map(vaccine -> childVaccineRepository.save(new ChildVaccine(children, vaccine, false)))
                .collect(Collectors.toSet());
    }

    private User getUserLogged() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Ops! Usuário não existe."));
    }
}
