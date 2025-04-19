package com.alcozone.infrastructure.mapper;

import com.alcozone.domain.model.User;
import com.alcozone.infrastructure.dto.user.UserDTO;
import com.alcozone.infrastructure.entity.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setUuid(entity.getUuid());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setDeleted(entity.isDeleted());
        user.setCreatedAt(entity.getCreatedAt());
        user.setUpdatedAt(entity.getUpdatedAt());
        user.setRole(RoleMapper.toDomain(entity.getRole()));
        return user;
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUuid(user.getUuid());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setDeleted(user.isDeleted());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());
        entity.setRole(RoleMapper.toEntity(user.getRole()));
        return entity;
    }

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().getName());
        return dto;
    }
}


