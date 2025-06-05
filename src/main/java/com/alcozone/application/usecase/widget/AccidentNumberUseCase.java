package com.alcozone.application.usecase.widget;

import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.application.service.WidgetService;
import com.alcozone.domain.models.Crash;
import com.alcozone.domain.models.widgetdata.AccidentNumber;
import com.alcozone.domain.models.widgetdata.WidgetFilters;
import com.alcozone.infrastructure.dto.crash.DefaultCrashesResponseDTO;
import com.alcozone.infrastructure.dto.widget.AccidentNumberDTO;

import com.alcozone.infrastructure.mapper.widgetdata.AccidentNumberMapper;
import com.alcozone.infrastructure.mapper.widgetdata.WidgetFiltersMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped

public class AccidentNumberUseCase {
    @Inject
    WidgetService widgetService;

    public List<AccidentNumber> execute(WidgetFiltersDTO filtersDto) {
        WidgetFilters filters = WidgetFiltersMapper.toDomain(filtersDto);
        return widgetService.getAccidentsNumber(filters);
    }
}

