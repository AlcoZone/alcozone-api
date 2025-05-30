package com.alcozone.application.usecase.dashboardwidget;

import com.alcozone.domain.repository.DashboardWidgetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteDashboardAndWidgetsUseCase {

    @Inject
    DashboardWidgetRepository dashboardWidgetRepository;

    @Transactional
    public void execute(String dashboardUuid) {
        dashboardWidgetRepository.deleteByDashboardUuid(dashboardUuid);
    }
}
