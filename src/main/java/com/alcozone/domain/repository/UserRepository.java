package com.alcozone.domain.repository;
import com.alcozone.domain.model.User;

import java.util.List;

public interface UserRepository {
    User findUserByFireBaseId(String fireBaseId);
    User findById(String id);
    User deleteUser(String id);
    User createUser(User user);
    User save(User user, int roleId);
    User updateDisplayName(String firebaseUid, String newDisplayName);
    boolean existsByEmail(String email);
    List<User> findAll();
}