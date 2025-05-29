package com.alcozone.infrastructure.mapper;

import com.alcozone.domain.model.User;
import com.alcozone.infrastructure.entity.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());

        user.setEmail(entity.getEmail());
        user.setUuid(entity.getUuid());
        user.setDeleted(entity.isDeleted());
        user.setCreatedAt(entity.getCreatedAt());
        user.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getRole() != null) {
            user.setRole(RoleMapper.toDomain(entity.getRole()));
        }
        return user;
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setUuid(user.getUuid());
        if (user.getRole() != null) {
            entity.setRole(RoleMapper.toEntity(user.getRole()));
        }
        return entity;
    }
}