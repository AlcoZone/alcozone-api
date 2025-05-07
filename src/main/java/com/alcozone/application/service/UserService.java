package com.alcozone.application.service;

import com.alcozone.domain.model.User;
import com.alcozone.domain.repository.UserRepository;
import com.alcozone.infrastructure.mapper.UserMapper;
import com.alcozone.infrastructure.dto.user.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public UserDTO findUserByFirebaseUid(String firebaseUid) {
        User user = userRepository.findUserByFireBaseId(firebaseUid);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return UserMapper.toDTO(user);
    }
}