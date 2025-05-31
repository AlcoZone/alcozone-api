package com.alcozone.application.usecase.user;

import com.alcozone.application.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteUserUseCase {

    @Inject
    UserService userService;

    public void execute(String id) {
        userService.deleteUser(id);
    }
}
