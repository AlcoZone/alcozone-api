package com.alcozone.application.usecase.dashboard;

import com.alcozone.application.dto.dashboard.UpdateDashboardDTO;
import com.alcozone.application.mapper.DashboardDTOMapper;
import com.alcozone.application.service.DashboardService;
import com.alcozone.domain.models.Dashboard;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateDashboardUseCase {

    @Inject
    DashboardService dashboardService;

    public Dashboard execute(UpdateDashboardDTO dto) {
        Dashboard existing = dashboardService.getDashboardByUuid(dto.getUuid())
                .orElseThrow(() -> new RuntimeException("Dashboard not found"));

        Dashboard updated = DashboardDTOMapper.fromUpdateDTO(dto, existing);
        return dashboardService.updateDashboard(updated);
    }
}
