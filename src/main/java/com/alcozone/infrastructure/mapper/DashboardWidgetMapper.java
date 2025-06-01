package com.alcozone.infrastructure.mapper;

import com.alcozone.domain.models.DashboardWidget;
import com.alcozone.infrastructure.entity.DashboardWidgetEntity;
import com.alcozone.infrastructure.entity.WidgetEntity;

public class DashboardWidgetMapper {

    public static DashboardWidget toDomain(DashboardWidgetEntity entity) {
        DashboardWidget widget = new DashboardWidget();
        widget.setUuid(entity.getUuid());
        widget.setWidgetUuid(entity.getWidgetUuid());
        widget.setDashboardUuid(entity.getDashboardUuid());
        widget.setGridPositionX(entity.getGridPositionX());
        widget.setGridPositionY(entity.getGridPositionY());
        widget.setGridWidth(entity.getGridWidth());
        widget.setGridHeight(entity.getGridHeight());
        widget.setMinWidth(entity.getWidget().getMinWidth());
        widget.setMinHeight(entity.getWidget().getMinHeight());
        widget.setName(entity.getWidget().getName());
        widget.setCreatedAt(entity.getCreatedAt());
        widget.setUpdatedAt(entity.getUpdatedAt());
        return widget;
    }

    public static DashboardWidgetEntity toEntity(DashboardWidget widget, WidgetEntity widgetEntity) {
        DashboardWidgetEntity entity = new DashboardWidgetEntity();
        entity.setId(widget.getId());
        entity.setUuid(widget.getUuid());
        entity.setDashboardUuid(widget.getDashboardUuid());
        entity.setWidgetUuid(widget.getWidgetUuid());
        entity.setWidget(widgetEntity);
        entity.setGridPositionX(widget.getGridPositionX());
        entity.setGridPositionY(widget.getGridPositionY());
        entity.setGridWidth(widget.getGridWidth());
        entity.setGridHeight(widget.getGridHeight());
        entity.setCreatedAt(widget.getCreatedAt());
        entity.setUpdatedAt(widget.getUpdatedAt());
        return entity;
    }
}
