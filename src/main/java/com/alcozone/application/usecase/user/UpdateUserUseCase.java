package com.alcozone.application.usecase.user;

import com.alcozone.application.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class UpdateUserUseCase {

    @Inject
    UserService userService;

    @Transactional
    public void updatePassword(String firebaseUid, String newPassword) {
        userService.updatePassword(firebaseUid, newPassword);
    }

    @Transactional
    public void updateDisplayName(String firebaseUid, String newDisplayName) {
        userService.updateDisplayName(firebaseUid, newDisplayName);
    }
}