package com.alcozone.application.usecase.dashboard;

import com.alcozone.application.dto.dashboard.UpdateDashboardDTO;
import com.alcozone.application.mapper.DashboardDTOMapper;
import com.alcozone.application.service.DashboardService;
import com.alcozone.domain.models.Dashboard;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

@ApplicationScoped
public class UpdateDashboardUseCase {

    @Inject
    DashboardService dashboardService;

    public Dashboard execute(UpdateDashboardDTO dto) {
        Dashboard existing = dashboardService.getDashboardByUuid(dto.getUuid())
                .orElseThrow(() -> new RuntimeException("Dashboard not found"));

        existing.setName(dto.getName());
        existing.setUpdatedAt(LocalDateTime.now());

        return dashboardService.updateDashboard(existing);
    }
}
