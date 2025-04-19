package com.alcozone.domain.repository;
import com.alcozone.domain.model.User;

public interface UserRepository {
    User findByEmail(String email);
}
