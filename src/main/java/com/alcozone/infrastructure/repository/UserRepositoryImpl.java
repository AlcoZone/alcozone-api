package com.alcozone.infrastructure.repository;

import com.alcozone.domain.model.User;
import com.alcozone.domain.repository.UserRepository;
import com.alcozone.infrastructure.entity.UserEntity;
import com.alcozone.infrastructure.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findByEmail(String email) {
        UserEntity entity = UserEntity.find("email", email).firstResult();
        if(entity == null) {
            return null;
        }
        return UserMapper.toDomain(entity);
    }
}
