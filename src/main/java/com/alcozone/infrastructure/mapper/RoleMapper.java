package com.alcozone.infrastructure.mapper;

import com.alcozone.domain.model.Role;
import com.alcozone.infrastructure.entity.RoleEntity;

public class RoleMapper {

    public static Role toDomain(RoleEntity entity) {
        Role role = new Role();
        role.setId(entity.getId());
        role.setUuid(entity.getUuid());
        role.setName(entity.getName());
        role.setCreatedAt(entity.getCreatedAt());
        role.setUpdatedAt(entity.getUpdatedAt());
        return role;
    }

    public static RoleEntity toEntity(Role role) {
        RoleEntity entity = new RoleEntity();
        entity.setId(role.getId());
        entity.setUuid(role.getUuid());
        entity.setName(role.getName());
        entity.setCreatedAt(role.getCreatedAt());
        entity.setUpdatedAt(role.getUpdatedAt());
        return entity;
    }
}

