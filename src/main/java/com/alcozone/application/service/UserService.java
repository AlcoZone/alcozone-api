package com.alcozone.application.service;

import com.alcozone.application.usecase.register.GetAllUsersUseCase;
import com.alcozone.domain.model.User;
import com.alcozone.domain.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.alcozone.infrastructure.dto.register.userDTO;
import com.alcozone.infrastructure.mapper.UserMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import jakarta.enterprise.context.ApplicationScoped;
import com. google.firebase.auth.UserRecord.UpdateRequest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    // Metodo para encontrar al usuario por firebaseUid
    public UserRecord findUserByFirebaseUid(String firebaseUid) {
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUser(firebaseUid);
            if (userRecord == null) {
                throw new RuntimeException("User not found with Firebase UID: " + firebaseUid);
            }
            return userRecord;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user from Firebase: " + e.getMessage());
        }
    }


    //actualizar password
    public void updatePassword(String firebaseUid, String newPassword) {
        try {
            findUserByFirebaseUid(firebaseUid);
            UserRecord userRecord;

            UpdateRequest request = new UpdateRequest(firebaseUid)
                    .setPassword(newPassword);

            userRecord = FirebaseAuth.getInstance().updateUser(request);
            System.out.println("Password successfully updated for user: " + userRecord.getUid());
        } catch (Exception e) {
            throw new RuntimeException("Error updating password in Firebase: " + e.getMessage());
        }
    }

    //actualizar displayName
    public void updateDisplayName(String firebaseUid, String newDisplayName) {
        try {
            findUserByFirebaseUid(firebaseUid);
            UserRecord userRecord;

            UpdateRequest request = new UpdateRequest(firebaseUid)
                    .setDisplayName(newDisplayName);

            userRecord = FirebaseAuth.getInstance().updateUser(request);
            System.out.println("Display name successfully updated for user: " + userRecord.getUid());
        } catch (Exception e) {
            throw new RuntimeException("Error updating display name in Firebase: " + e.getMessage());
        }
    }


    public void deleteUser(String id) {
        try {
            // Buscar el usuario en la base de datos por su ID interno
            User user = userRepository.findById(id);

            if (user == null) {
                System.out.println("No user found with internal ID: " + id);
                return;
            }

            // Obtener el Firebase UID del usuario
            String firebaseUid = user.getUuid();

            // Buscar el usuario en Firebase
            UserRecord firebaseUser = findUserByFirebaseUid(firebaseUid);

            if (!firebaseUser.getUid().equals(firebaseUid)) {
                System.out.println("Firebase UID mismatch.");
                return;
            }

            // Eliminar usuario en Firebase
            FirebaseAuth.getInstance().deleteUser(firebaseUid);
            System.out.println("Successfully deleted user from Firebase.");

            // Eliminar usuario en la base de datos
            userRepository.deleteUser(firebaseUid);
            System.out.println("Successfully deleted user from database.");

        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
            throw new RuntimeException("Failed to delete user: " + e.getMessage());
        }
    }

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

}