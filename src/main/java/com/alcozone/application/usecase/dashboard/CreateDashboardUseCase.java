package com.alcozone.application.usecase.dashboard;

import com.alcozone.application.dto.dashboard.CreateDashboardDTO;
import com.alcozone.application.mapper.DashboardDTOMapper;
import com.alcozone.application.service.DashboardService;
import com.alcozone.domain.models.Dashboard;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateDashboardUseCase {

    @Inject
    DashboardService dashboardService;

    public Dashboard execute(CreateDashboardDTO dto) {
        Dashboard dashboard = DashboardDTOMapper.fromCreateDTO(dto);
        return dashboardService.createDashboard(dashboard);
    }
}
