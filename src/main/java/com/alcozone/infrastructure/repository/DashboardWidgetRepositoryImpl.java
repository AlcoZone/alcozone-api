package com.alcozone.infrastructure.repository;

import com.alcozone.domain.models.DashboardWidget;
import com.alcozone.domain.repository.DashboardWidgetRepository;
import com.alcozone.infrastructure.entity.DashboardWidgetEntity;
import com.alcozone.infrastructure.entity.WidgetEntity;
import com.alcozone.infrastructure.mapper.DashboardWidgetMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DashboardWidgetRepositoryImpl implements DashboardWidgetRepository {

    @Override
    @Transactional
    public List<DashboardWidget> saveWidgets(String dashboardUuid,
                                             List<DashboardWidget> widgets) {

        DashboardWidgetEntity.delete("dashboardUuid", dashboardUuid);

        List<DashboardWidgetEntity> persisted = widgets.stream()
                .map(w -> {

                    w.setDashboardUuid(dashboardUuid);

                    WidgetEntity we = WidgetEntity.find("uuid", w.getWidgetUuid())
                            .firstResult();

                    DashboardWidgetEntity e = DashboardWidgetMapper.toEntity(w, we);
                    e.setId(null);
                    e.setCreatedAt(LocalDateTime.now());
                    e.setUpdatedAt(LocalDateTime.now());
                    e.persist();
                    return e;
                })
                .collect(Collectors.toList());

        return persisted.stream()
                .map(DashboardWidgetMapper::toDomain)
                .collect(Collectors.toList());
    }


    @Override
    public List<DashboardWidget> findByDashboardUuid(String dashboardUuid) {
        return DashboardWidgetEntity.find("dashboardUuid", dashboardUuid).list().stream()
                .map(e -> DashboardWidgetMapper.toDomain((DashboardWidgetEntity) e))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteByDashboardUuid(String dashboardUuid) {
        DashboardWidgetEntity.delete("dashboardUuid", dashboardUuid);
    }
}
