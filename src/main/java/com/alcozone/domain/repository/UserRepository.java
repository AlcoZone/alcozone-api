package com.alcozone.domain.repository;
import com.alcozone.domain.model.User;

public interface UserRepository {
    User findUserByFireBaseId(String fireBaseId);
    User findById(String id);
    User deleteUser(String id); //mandarle solo el id

}
