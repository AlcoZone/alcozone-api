package com.alcozone.application.usecase;

import com.alcozone.application.service.UserService;
import com.alcozone.infrastructure.dto.user.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LoginUserUseCase {

    @Inject
    UserService userService;

    public UserDTO execute(String idToken) {
        return userService.loginWithFirebase(idToken);
    }
}
