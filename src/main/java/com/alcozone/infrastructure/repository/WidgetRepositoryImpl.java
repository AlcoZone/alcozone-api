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
    public List<Widget> findAll() {
        return WidgetEntity.listAll().stream()
                .map(e -> WidgetMapper.toDomain((WidgetEntity) e))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Widget save(Widget widget) {
        WidgetEntity entity = WidgetMapper.toEntity(widget);
        entity.setUpdatedAt(LocalDateTime.now());

        if (entity.getId() == null) {
            entity.setCreatedAt(LocalDateTime.now());
            entity.persist();
        } else {
            entity = WidgetEntity.getEntityManager().merge(entity);
        }

        return WidgetMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public void deleteByUuid(String uuid) {
        WidgetEntity.delete("uuid", uuid);
    }
}
