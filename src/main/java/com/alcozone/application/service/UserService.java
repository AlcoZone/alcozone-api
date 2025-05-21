package com.alcozone.application.service;

import com.alcozone.domain.model.User;
import com.alcozone.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public User findByFirebaseUidRaw(String firebaseUid) {
        return userRepository.findUserByFireBaseId(firebaseUid);
    }

    @Transactional
    public void createUser(User user) {
        userRepository.createUser(user);
    }
}

