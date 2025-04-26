package com.anhanguera.crescerbem.controllers;

import com.anhanguera.crescerbem.payloads.children.request.CreateChildrenDto;
import com.anhanguera.crescerbem.payloads.children.request.UpdateChildrenDto;
import com.anhanguera.crescerbem.payloads.children.response.ChildrenResponseDto;
import com.anhanguera.crescerbem.services.ChildrenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/children")
public class ChildrenController extends BaseController<CreateChildrenDto, UpdateChildrenDto, ChildrenResponseDto> {
    private final ChildrenService service;

    public ChildrenController(ChildrenService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/apply/{id}")
    public ResponseEntity<Void> applyVaccine(@PathVariable UUID id) {
        service.applyVaccine(id);
        return ResponseEntity.noContent().build();
    }
}
