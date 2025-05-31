package com.alcozone.infrastructure.mapper;

import com.alcozone.domain.models.Widget;
import com.alcozone.infrastructure.entity.WidgetEntity;

public class WidgetMapper {

    public static Widget toDomain(WidgetEntity entity) {
        Widget widget = new Widget();
        widget.setId(entity.getId());
        widget.setUuid(entity.getUuid());
        widget.setName(entity.getName());
        widget.setDescription(entity.getDescription());
        widget.setMinWidth(entity.getMinWidth());
        widget.setMinHeight(entity.getMinHeight());
        widget.setCreatedAt(entity.getCreatedAt());
        widget.setUpdatedAt(entity.getUpdatedAt());
        return widget;
    }

    public static WidgetEntity toEntity(Widget widget) {
        WidgetEntity entity = new WidgetEntity();
        entity.setId(widget.getId());
        entity.setUuid(widget.getUuid());
        entity.setName(widget.getName());
        entity.setDescription(widget.getDescription());
        entity.setMinWidth(widget.getMinWidth());
        entity.setMinHeight(widget.getMinHeight());
        entity.setCreatedAt(widget.getCreatedAt());
        entity.setUpdatedAt(widget.getUpdatedAt());
        return entity;
    }
}
