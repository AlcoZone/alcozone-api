package com.alcozone.application.usecase.widget;

import com.alcozone.application.service.WidgetService;
import com.alcozone.infrastructure.dto.widget.DangerousTownMonthDTO;
import com.alcozone.infrastructure.dto.widget.MonthlyAccidentsDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DangerousTownMonthUseCase {
    @Inject
    WidgetService widgetService;

    public List<DangerousTownMonthDTO> getDangerousTownMonth(){
        return widgetService.getDangerousTownMonth();
    }

}
