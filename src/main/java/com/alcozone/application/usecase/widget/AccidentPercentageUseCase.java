package com.alcozone.application.usecase.widget;

import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.application.service.WidgetService;
import com.alcozone.infrastructure.dto.widget.AccidentPercentageDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class AccidentPercentageUseCase {

    @Inject
    WidgetService widgetService;

    public List<AccidentPercentageDTO> getAccidentPercentage(WidgetFiltersDTO filters) {
        return widgetService.getAccidentPercentage(filters);
    }
}
