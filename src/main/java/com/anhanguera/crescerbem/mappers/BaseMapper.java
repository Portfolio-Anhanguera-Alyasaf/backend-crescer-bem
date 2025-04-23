package com.anhanguera.crescerbem.mappers;

public interface BaseMapper<Entity, RequestDto, ResponseDto> {
    Entity requestDtoToEntity(RequestDto request);

    ResponseDto entityToResponseDto(Entity entity);

    RequestDto entityToRequestDto(Entity entity);
}
