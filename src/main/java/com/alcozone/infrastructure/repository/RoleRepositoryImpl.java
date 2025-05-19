package com.alcozone.infrastructure.repository;

import com.alcozone.domain.model.Role;
import com.alcozone.domain.repository.RoleRepository;
import com.alcozone.infrastructure.entity.RoleEntity;
import com.alcozone.infrastructure.mapper.RoleMapper;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoleRepositoryImpl implements RoleRepository {

    @Override
    public Role findRoleById(Integer id) {
        RoleEntity entity = RoleEntity.findById(id);
        return RoleMapper.toDomain(entity);
    }
}
