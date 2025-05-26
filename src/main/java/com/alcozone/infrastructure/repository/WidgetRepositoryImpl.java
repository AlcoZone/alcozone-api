package com.alcozone.infrastructure.repository;

import com.alcozone.domain.models.Widget;
import com.alcozone.domain.repository.WidgetRepository;
import com.alcozone.infrastructure.entity.WidgetEntity;
import com.alcozone.infrastructure.mapper.WidgetMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class WidgetRepositoryImpl implements WidgetRepository {

    @Override
    @Transactional
    public List<Widget> saveWidgets(List<Widget> widgets) {
        List<WidgetEntity> entities = widgets.stream()
                .map(WidgetMapper::toEntity)
                .collect(Collectors.toList());

        for (WidgetEntity entity : entities) {
            entity.setUpdatedAt(LocalDateTime.now());

            if (entity.getId() == null) {
                entity.setCreatedAt(LocalDateTime.now());
                entity.persist();
            } else {
                entity = WidgetEntity.getEntityManager().merge(entity);
            }
        }


        return entities.stream()
                .map(WidgetMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Widget> findWidgetsByDashboardUuid(String dashboardUuid) {
        return WidgetEntity.find("dashboardUuid", dashboardUuid).list().stream()
                .map(e -> WidgetMapper.toDomain((WidgetEntity) e))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteWidgetsByDashboardUuid(String dashboardUuid) {
        WidgetEntity.delete("dashboardUuid", dashboardUuid);
    }
}
