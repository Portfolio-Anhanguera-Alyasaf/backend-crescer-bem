package com.anhanguera.crescerbem.mappers;

import com.anhanguera.crescerbem.entities.User;
import com.anhanguera.crescerbem.payloads.user.request.CreateUserDto;
import com.anhanguera.crescerbem.payloads.user.request.UpdateUserDto;
import com.anhanguera.crescerbem.payloads.user.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<User, CreateUserDto, UserResponseDto> {
    @Override
    public User requestDtoToEntity(CreateUserDto request) {
        return new User(request.email(), request.password(), request.name(), request.childrenNumber(), request.kind());
    }

    @Override
    public UserResponseDto entityToResponseDto(User user) {
        return new UserResponseDto(user.getUserId(), user.getEmail(), user.getName(), user.getChildrenNumber(), user.getKind());
    }

    @Override
    public CreateUserDto entityToRequestDto(User user) {
        return new CreateUserDto(user.getEmail(), user.getPassword(), user.getName(), user.getChildrenNumber(), user.getKind());
    }

    public User updateDtoToEntity(UpdateUserDto updateRequest) {
        return new User(updateRequest.email(), updateRequest.name(), updateRequest.childrenNumber(), updateRequest.kind());
    }

    public UpdateUserDto updateDtoToEntity(User user) {
        return new UpdateUserDto(user.getEmail(), user.getName(), user.getChildrenNumber(), user.getKind());
    }
}
