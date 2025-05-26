package com.alcozone.application.usecase.register;

import com.alcozone.domain.model.User;
import com.alcozone.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class GetAllUsersUseCase {
    @Inject
    UserRepository userRepository;

    public List<User> execute() {
        return userRepository.findAll();
    }
}
