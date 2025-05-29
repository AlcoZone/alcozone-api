package com.alcozone.infrastructure.repository;

import com.alcozone.domain.model.User;
import com.alcozone.domain.repository.UserRepository;
import com.alcozone.infrastructure.entity.UserEntity;
import com.alcozone.infrastructure.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;


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

    @Override
    public User findById(String id) {
        UserEntity entity = UserEntity.findById(id);
        return entity != null ? UserMapper.toDomain(entity) : null;
    }

    @Override
    @Transactional
    public User deleteUser(String id) {
        UserEntity entity = UserEntity.find("uuid", id).firstResult();
        if (entity == null) {
            return null;
        }

        entity.setDeleted(true);
        return UserMapper.toDomain(entity);
    }

    public User createUser(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        entity.persist();
        return user;
    }
}
