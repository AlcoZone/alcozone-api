package com.alcozone.application.usecase.dashboardwidget;

import com.alcozone.application.service.DashboardWidgetService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteDashboardAndWidgetsUseCase {

    @Inject
    DashboardWidgetService dashboardWidgetService;

    @Transactional
    public void execute(String dashboardUuid) {
        dashboardWidgetService.deleteByDashboardUuid(dashboardUuid);
    }
}
