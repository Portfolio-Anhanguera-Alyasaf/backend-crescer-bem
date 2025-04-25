package com.anhanguera.crescerbem.services;

import com.anhanguera.crescerbem.payloads.children.request.CreateChildrenDto;
import com.anhanguera.crescerbem.payloads.children.request.UpdateChildrenDto;
import com.anhanguera.crescerbem.payloads.children.response.ChildrenResponseDto;

public interface ChildrenService extends BaseService<CreateChildrenDto, UpdateChildrenDto, ChildrenResponseDto> {
}
