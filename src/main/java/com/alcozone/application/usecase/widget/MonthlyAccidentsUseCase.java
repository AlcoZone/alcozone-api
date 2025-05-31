package com.alcozone.application.usecase.widget;

import com.alcozone.application.service.WidgetService;
import com.alcozone.domain.repository.WidgetRepository;
import com.alcozone.infrastructure.dto.widget.MonthlyAccidentsDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class MonthlyAccidentsUseCase {
    @Inject
    WidgetService widgetService;

    public List<MonthlyAccidentsDTO> getMonthlyAccident() {
        return widgetService.getMonthlyAccident();
    }
}
