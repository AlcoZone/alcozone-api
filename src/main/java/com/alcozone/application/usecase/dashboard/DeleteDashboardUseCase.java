package com.alcozone.application.usecase.dashboard;

import com.alcozone.application.service.DashboardService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteDashboardUseCase {

    @Inject
    DashboardService dashboardService;

    public void execute(String dashboardUuid) {
        dashboardService.deleteDashboard(dashboardUuid);
    }
}
