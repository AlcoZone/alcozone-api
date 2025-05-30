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
    public List<DashboardWidget> saveWidgets(List<DashboardWidget> widgets) {
        List<DashboardWidgetEntity> entities = widgets.stream()
                .map(widget -> {
                    WidgetEntity widgetEntity = WidgetEntity.find("uuid", widget.getWidgetUuid())
                            .firstResult();
                    return DashboardWidgetMapper.toEntity(widget, widgetEntity);
                })
                .collect(Collectors.toList());


        for (DashboardWidgetEntity entity : entities) {
            entity.setUpdatedAt(LocalDateTime.now());

            if (entity.getId() == null) {
                entity.setCreatedAt(LocalDateTime.now());
                entity.persist();
            } else {
                entity = DashboardWidgetEntity.getEntityManager().merge(entity);
            }
        }

        return entities.stream()
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
