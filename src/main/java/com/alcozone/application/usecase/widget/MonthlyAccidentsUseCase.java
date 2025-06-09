package com.alcozone.application.usecase.widget;

import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.application.service.WidgetService;
import com.alcozone.domain.models.widgetdata.MonthlyAccidents;
import com.alcozone.domain.models.widgetdata.WidgetFilters;
import com.alcozone.infrastructure.mapper.widgetdata.WidgetFiltersMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class MonthlyAccidentsUseCase {
    @Inject
    WidgetService widgetService;

    public List<MonthlyAccidents> execute(WidgetFiltersDTO filtersDto) {
        WidgetFilters filters = WidgetFiltersMapper.toDomain(filtersDto);
        return widgetService.getMonthlyAccident(filters);
    }
}
