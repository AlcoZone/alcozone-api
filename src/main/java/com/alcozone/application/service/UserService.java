package com.alcozone.application.service;

import com.alcozone.infrastructure.mapper.UserMapper;
import com.alcozone.domain.model.User;
import com.alcozone.domain.repository.UserRepository;
import com.alcozone.infrastructure.dto.user.UserDTO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public UserDTO loginWithFirebase(String idToken){
        try{
            // Check the token w/ firebase
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String email = decodedToken.getEmail();

            // Search for the user in the db
            User user = userRepository.findByEmail(email);

            if(user == null){
                throw new RuntimeException("User not found");
            }

            return UserMapper.toDTO(user);

        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Invalid Firebase Token",e);
        }
    }
}
