package com.anhanguera.crescerbem.services;

import com.anhanguera.crescerbem.payloads.children.request.CreateChildrenDto;
import com.anhanguera.crescerbem.payloads.children.request.UpdateChildrenDto;
import com.anhanguera.crescerbem.payloads.children.response.ChildrenResponseDto;

import java.util.UUID;

public interface ChildrenService extends BaseService<CreateChildrenDto, UpdateChildrenDto, ChildrenResponseDto> {
    void applyVaccine(UUID idChildren);
}
