package com.anhanguera.crescerbem.controllers;

import com.anhanguera.crescerbem.payloads.user.request.CreateUserDto;
import com.anhanguera.crescerbem.payloads.user.request.UpdateUserDto;
import com.anhanguera.crescerbem.payloads.user.response.UserResponseDto;
import com.anhanguera.crescerbem.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController extends BaseController<CreateUserDto, UpdateUserDto, UserResponseDto> {
    private final UserService service;

    public UserController(UserService service) {
        super(service);
        this.service = service;
    }
}
