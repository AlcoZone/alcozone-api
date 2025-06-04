package com.alcozone.application.usecase.dashboard;

import com.alcozone.application.service.DashboardService;
import com.alcozone.domain.models.Dashboard;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetDashboardsForUserUseCase {

    @Inject
    DashboardService dashboardService;

    public List<Dashboard> execute(String userUuid) {
        return dashboardService.getDashboardsByUserUuid(userUuid);
    }
}
