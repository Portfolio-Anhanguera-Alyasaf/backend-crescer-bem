package com.anhanguera.crescerbem.services;

import com.anhanguera.crescerbem.payloads.CreatedResponseDto;
import com.anhanguera.crescerbem.payloads.PaginationDto;
import com.anhanguera.crescerbem.payloads.UpdatedResponseDto;

import java.util.UUID;

public interface BaseService<CreateRequestDto, UpdateRequestDto, ResponseDto> {
    PaginationDto<ResponseDto> getAll(int page, int pageSize);

    CreatedResponseDto create(CreateRequestDto requestDto);

    UpdatedResponseDto update(UUID id, UpdateRequestDto requestDto);

    ResponseDto getById(UUID id);

    void deleteById(UUID id);
}
