package com.alcozone.application.service;

import com.alcozone.application.usecase.register.GetAllUsersUseCase;
import com.alcozone.domain.model.User;
import com.alcozone.domain.repository.UserRepository;
import com.alcozone.infrastructure.dto.register.userDTO;
import com.alcozone.infrastructure.mapper.UserMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

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

    @Transactional
    public userDTO register(userDTO dto) {
        try {
            UserRecord firebaseUser = FirebaseAuth.getInstance().createUser(
                    new UserRecord.CreateRequest()
                            .setEmail(dto.getEmail())
                            .setPassword(dto.getPassword())
                            .setDisplayName(dto.getUsername())
            );

            User user = new User();
            user.setUuid(firebaseUser.getUid());
            user.setEmail(dto.getEmail());
            user.setUsername(dto.getUsername());
            userRepository.save(user, dto.getRole_id());
            return UserMapper.toDTO(user);

        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Error creando usuario en Firebase", e);
        }
    }

    @Inject
    GetAllUsersUseCase getAllUsersUseCase;
    public List<userDTO> getAll() {
        return getAllUsersUseCase.execute()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

}
