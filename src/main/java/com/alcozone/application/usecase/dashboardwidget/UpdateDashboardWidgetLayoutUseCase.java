package com.alcozone.application.usecase.dashboardwidget;

import com.alcozone.domain.models.DashboardWidget;
import com.alcozone.domain.repository.DashboardWidgetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UpdateDashboardWidgetLayoutUseCase {

    @Inject
    DashboardWidgetRepository dashboardWidgetRepository;

    public List<DashboardWidget> execute(List<DashboardWidget> widgets) {
        return dashboardWidgetRepository.saveWidgets(widgets);
    }
}
