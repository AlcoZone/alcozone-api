package com.alcozone.infrastructure.repository;

import com.alcozone.domain.models.Dashboard;
import com.alcozone.domain.repository.DashboardRepository;
import com.alcozone.infrastructure.entity.DashboardEntity;
import com.alcozone.infrastructure.mapper.DashboardMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class DashboardRepositoryImpl implements DashboardRepository {

    @Override
    @Transactional
    public Dashboard saveDashboard(Dashboard dashboard) {
        DashboardEntity entity = DashboardMapper.toEntity(dashboard);

        if (entity.getId() == null) {
            entity.persist();
        } else {
            entity = DashboardEntity.getEntityManager().merge(entity);
        }

        return DashboardMapper.toDomain(entity);
    }

    @Override
    public Optional<Dashboard> findDashboardByUuid(String uuid) {
        return DashboardEntity.find("uuid", uuid)
                .firstResultOptional()
                .map(e -> DashboardMapper.toDomain((DashboardEntity) e));
    }

    @Override
    public List<Dashboard> findDashboardsByUserUuid(String userUuid) {
        return DashboardEntity.find("userUuid", userUuid).list().stream()
                .map(e -> (DashboardEntity) e)
                .map(DashboardMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteDashboardByUuid(String uuid) {
        DashboardEntity.delete("uuid", uuid);
    }
}
