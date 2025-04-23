package com.anhanguera.crescerbem.services;

import com.anhanguera.crescerbem.payloads.user.request.CreateUserDto;
import com.anhanguera.crescerbem.payloads.user.request.UpdateUserDto;
import com.anhanguera.crescerbem.payloads.user.response.UserResponseDto;

public interface UserService extends BaseService<CreateUserDto, UpdateUserDto, UserResponseDto> {
}
