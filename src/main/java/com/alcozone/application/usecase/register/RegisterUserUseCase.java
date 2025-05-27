package com.alcozone.application.usecase.register;

import com.alcozone.application.service.UserService;
import com.alcozone.infrastructure.dto.register.userDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class RegisterUserUseCase {

    @Inject
    UserService userService;

    public userDTO execute(userDTO UserDTO) {
        return userService.register(UserDTO);
    }
}

