package com.alcozone.application.usecase.widget;

import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.application.service.WidgetService;
import com.alcozone.infrastructure.dto.widget.AccidentsByReportSourceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class AccidentsByReportSourceUseCase {
    @Inject
    WidgetService widgetService;

    public List<AccidentsByReportSourceDTO> getAccidentsByReportSource(WidgetFiltersDTO filters){
        return widgetService.getAccidentsByReportSource(filters);
    }
}
