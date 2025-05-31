package com.alcozone.domain.model;

import lombok.Getter;

@Getter
public enum RoleType {
    ADMIN(1),
    DATA_MANAGER(2),
    DATA_VISUALIZER(3);

    private final int id;

    RoleType(int id) {
        this.id = id;
    }

}
