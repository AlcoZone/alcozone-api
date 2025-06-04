package com.alcozone.application.usecase.user;

import com.alcozone.application.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;

@AllArgsConstructor

@ApplicationScoped
public class EmailExistUseCase {

    @Inject
    UserService userService;

    public boolean emailExists(String email) {
        return userService.emailExists(email);
    }
}

