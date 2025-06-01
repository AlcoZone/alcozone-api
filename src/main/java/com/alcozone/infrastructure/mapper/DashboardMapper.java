package com.alcozone.infrastructure.mapper;

import com.alcozone.domain.models.Dashboard;
import com.alcozone.infrastructure.entity.DashboardEntity;

public class DashboardMapper {

    public static Dashboard toDomain(DashboardEntity entity) {
        Dashboard dashboard = new Dashboard();
        dashboard.setId(entity.getId());
        dashboard.setUuid(entity.getUuid());
        dashboard.setUserUuid(entity.getUserUuid());
        dashboard.setName(entity.getName());
        dashboard.setCreatedAt(entity.getCreatedAt());
        dashboard.setUpdatedAt(entity.getUpdatedAt());
        return dashboard;
    }

    public static DashboardEntity toEntity(Dashboard dashboard) {
        DashboardEntity entity = new DashboardEntity();
        entity.setId(dashboard.getId());
        entity.setUuid(dashboard.getUuid());
        entity.setUserUuid(dashboard.getUserUuid());
        entity.setName(dashboard.getName());
        entity.setCreatedAt(dashboard.getCreatedAt());
        entity.setUpdatedAt(dashboard.getUpdatedAt());
        return entity;
    }
}
