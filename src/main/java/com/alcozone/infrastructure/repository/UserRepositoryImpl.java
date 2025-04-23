package com.alcozone.infrastructure.repository;

import com.alcozone.domain.model.User;
import com.alcozone.domain.repository.UserRepository;
import com.alcozone.infrastructure.entity.UserEntity;
import com.alcozone.infrastructure.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findUserByFireBaseId(String fireBaseId) {
        UserEntity entity = UserEntity.find("uuid", fireBaseId).firstResult();
        if (entity == null) {
            return null;
        }
        return UserMapper.toDomain(entity);
    }
}


