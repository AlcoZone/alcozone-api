package com.alcozone.application.usecase.dashboardwidget;

import com.alcozone.application.service.DashboardWidgetService;
import com.alcozone.domain.models.DashboardWidget;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UpdateDashboardWidgetLayoutUseCase {

    @Inject
    DashboardWidgetService dashboardWidgetService;

    public List<DashboardWidget> execute(String dashboardUuid, List<DashboardWidget> widgets) {
        return dashboardWidgetService.saveWidgets(dashboardUuid, widgets);
    }
}
