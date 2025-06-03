package com.alcozone.infrastructure.repository;

import com.alcozone.domain.model.User;
import com.alcozone.domain.repository.UserRepository;
import com.alcozone.infrastructure.entity.RoleEntity;
import com.alcozone.infrastructure.entity.UserEntity;
import com.alcozone.infrastructure.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findUserByFireBaseId(String fireBaseId) {
        UserEntity entity = UserEntity.find("uuid", fireBaseId).firstResult();
        return entity != null ? UserMapper.toDomain(entity) : null;
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

    @Override
    @Transactional
    public User updateDisplayName(String firebaseUid, String newDisplayName) {
        UserEntity entity = UserEntity.find("uuid", firebaseUid).firstResult();
        if (entity == null) {
            throw new RuntimeException("User not found in database with Firebase UID: " + firebaseUid);
        }

        entity.setUsername(newDisplayName);
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return UserMapper.toDomain(entity);
    }





    public User createUser(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        entity.persist();
        return user;
    }

    @Override
    public User save(User user, int roleId) {
        UserEntity entity = UserMapper.toEntity(user);
        entity.setRole(RoleEntity.findById(roleId));
        entity.persist();
        return user;
    }

    @Override
    public List<User> findAll() {
        return UserEntity.<UserEntity>listAll().stream()
                .map(UserMapper::toDomain)
                .toList();
    }

}
