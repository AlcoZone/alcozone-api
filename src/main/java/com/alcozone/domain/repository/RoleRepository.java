package com.alcozone.domain.repository;
import com.alcozone.domain.model.Role;

public interface RoleRepository {
    Role findRoleById(Integer id);
}
