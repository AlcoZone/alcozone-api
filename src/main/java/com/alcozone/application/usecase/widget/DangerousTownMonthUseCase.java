package com.alcozone.application.usecase.widget;

import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.application.service.WidgetService;
import com.alcozone.domain.models.widgetdata.DangerousTownMonth;
import com.alcozone.domain.models.widgetdata.WidgetFilters;
import com.alcozone.infrastructure.dto.widget.DangerousTownMonthDTO;
import com.alcozone.infrastructure.dto.widget.MonthlyAccidentsDTO;
import com.alcozone.infrastructure.mapper.widgetdata.DangerousTownMonthMapper;
import com.alcozone.infrastructure.mapper.widgetdata.WidgetFiltersMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DangerousTownMonthUseCase {
    @Inject
    WidgetService widgetService;

    public List<DangerousTownMonth> execute(WidgetFiltersDTO filtersDto){
        WidgetFilters filters = WidgetFiltersMapper.toDomain(filtersDto);
        return widgetService.getDangerousTownMonth(filters);
    }

}
