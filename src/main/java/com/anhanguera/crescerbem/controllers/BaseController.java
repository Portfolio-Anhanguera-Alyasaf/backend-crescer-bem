package com.anhanguera.crescerbem.controllers;

import com.anhanguera.crescerbem.payloads.CreatedResponseDto;
import com.anhanguera.crescerbem.payloads.PaginationDto;
import com.anhanguera.crescerbem.payloads.UpdatedResponseDto;
import com.anhanguera.crescerbem.services.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
public class BaseController<CreateRequestDto, UpdateRequestDto, ResponseDto> {
    private final BaseService<CreateRequestDto, UpdateRequestDto, ResponseDto> service;

    @GetMapping
    public ResponseEntity<PaginationDto<ResponseDto>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                             @RequestParam(value = "perPage", defaultValue = "10") int pageSize) {
        return ResponseEntity.ok().body(service.getAll(page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<CreatedResponseDto> create(@RequestBody CreateRequestDto requestDto) {
        CreatedResponseDto dto = service.create(requestDto);
        return ResponseEntity.created(URI.create("/" + dto.id())).body(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdatedResponseDto> update(@PathVariable UUID id, @RequestBody UpdateRequestDto requestDto) {
        UpdatedResponseDto dto = service.update(id, requestDto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
