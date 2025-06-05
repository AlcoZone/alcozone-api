package com.alcozone.application.usecase.widget;

import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.application.service.WidgetService;
import com.alcozone.domain.models.widgetdata.DangerousTown;
import com.alcozone.domain.models.widgetdata.WidgetFilters;
import com.alcozone.infrastructure.dto.widget.DangerousTownDTO;
import com.alcozone.infrastructure.mapper.widgetdata.DangerousTownMapper;
import com.alcozone.infrastructure.mapper.widgetdata.WidgetFiltersMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DangerousTownUseCase {
    @Inject
    WidgetService widgetService;

    public List<DangerousTown> execute(WidgetFiltersDTO filtersDto){
        WidgetFilters filters = WidgetFiltersMapper.toDomain(filtersDto);
        return widgetService.getDangerousTown(filters);
    }
}
