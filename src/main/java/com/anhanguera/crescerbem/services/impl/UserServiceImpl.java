package com.anhanguera.crescerbem.services.impl;

import com.anhanguera.crescerbem.entities.User;
import com.anhanguera.crescerbem.exceptions.BusinessException;
import com.anhanguera.crescerbem.exceptions.EntityNotFoundException;
import com.anhanguera.crescerbem.mappers.UserMapper;
import com.anhanguera.crescerbem.payloads.CreatedResponseDto;
import com.anhanguera.crescerbem.payloads.PagerResponseDto;
import com.anhanguera.crescerbem.payloads.PaginationDto;
import com.anhanguera.crescerbem.payloads.UpdatedResponseDto;
import com.anhanguera.crescerbem.payloads.user.request.CreateUserDto;
import com.anhanguera.crescerbem.payloads.user.request.UpdateUserDto;
import com.anhanguera.crescerbem.payloads.user.response.UserResponseDto;
import com.anhanguera.crescerbem.repositories.UserRepository;
import com.anhanguera.crescerbem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    @Override
    public PaginationDto<UserResponseDto> getAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<User> pager = repository.findAll(pageable);
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
    public CreatedResponseDto create(CreateUserDto createUserDto) {
        var userExists = repository.findByEmail(createUserDto.email());
        if (userExists.isPresent()) throw new BusinessException("Ops! Esse email já está em uso.");

        var newDto = CreateUserDto.builder()
                .name(createUserDto.name())
                .email(createUserDto.email())
                .childrenNumber(createUserDto.childrenNumber())
                .kind(createUserDto.kind())
                .password(encoder.encode(createUserDto.password()))
                .build();

        User user = repository.save(mapper.requestDtoToEntity(newDto));
        return new CreatedResponseDto(user.getUserId());
    }

    @Override
    @Transactional
    public UpdatedResponseDto update(UUID id, UpdateUserDto updateUserDto) {
        User userExists = repository.findById(id)
                .orElseThrow();

        BeanUtils.copyProperties(updateUserDto, userExists, "userId", "password");
        repository.save(userExists);
        return new UpdatedResponseDto(userExists.getUserId());
    }

    @Override
    public UserResponseDto getById(UUID id) {
        return repository.findById(id)
                .map(mapper::entityToResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Ops! Usuário não encontrado."));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
