package com.alcozone.application.usecase.dashboardwidget;

import com.alcozone.application.service.DashboardWidgetService;
import com.alcozone.domain.models.DashboardWidget;
import com.alcozone.domain.repository.DashboardWidgetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetWidgetsForDashboardUseCase {

    @Inject
    DashboardWidgetService dashboardWidgetService;

    public List<DashboardWidget> execute(String dashboardUuid) {
        return dashboardWidgetService.findByDashboardUuid(dashboardUuid);
    }
}
