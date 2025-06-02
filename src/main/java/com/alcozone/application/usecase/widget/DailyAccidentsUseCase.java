package com.alcozone.application.usecase.widget;

import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.application.service.WidgetService;
import com.alcozone.infrastructure.dto.widget.DailyAccidentsDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DailyAccidentsUseCase {
    @Inject
    WidgetService widgetService;

    public List<DailyAccidentsDTO> getDailyAccidents(WidgetFiltersDTO filters){
        return widgetService.getDailyAccidents(filters);
    }
}
