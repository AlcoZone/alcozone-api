package com.alcozone.infrastructure.mapper;

import com.alcozone.domain.model.User;
import com.alcozone.infrastructure.dto.register.userDTO;
import com.alcozone.infrastructure.entity.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setUuid(entity.getUuid());
        user.setEmail(entity.getEmail());
        user.setDeleted(entity.isDeleted());
        user.setCreatedAt(entity.getCreatedAt());
        user.setUpdatedAt(entity.getUpdatedAt());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        if (entity.getRole() != null) {
            user.setRole(RoleMapper.toDomain(entity.getRole()));
        }
        return user;
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setUuid(user.getUuid());
        entity.setEmail(user.getEmail());
        entity.setUsername(user.getUsername());
        if (user.getRole() != null) {
            entity.setRole(RoleMapper.toEntity(user.getRole()));
        }
        return entity;
    }

    public static userDTO toDTO(User user) {
        userDTO dto = new userDTO();
        dto.setUuid(user.getUuid());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        if (user.getRole() != null) {
            dto.setRole_id(user.getRole().getId());
            dto.setRole(user.getRole().getName());
        }
        return dto;
    }
}


