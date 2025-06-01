package com.alcozone.application.service;

import com.alcozone.application.dto.dashboard.CreateDashboardDTO;
import com.alcozone.application.mapper.DashboardDTOMapper;
import com.alcozone.domain.models.Dashboard;
import com.alcozone.domain.repository.DashboardRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class DashboardService {

    @Inject
    DashboardRepository dashboardRepository;

    public Dashboard createDashboard(CreateDashboardDTO dto) {
        Dashboard dashboard = DashboardDTOMapper.fromCreateDTO(dto);
        dashboard.setUuid(UUID.randomUUID().toString().replace("-", ""));
        dashboard.setCreatedAt(LocalDateTime.now());
        dashboard.setUpdatedAt(LocalDateTime.now());
        return dashboardRepository.saveDashboard(dashboard);
    }

    public Dashboard updateDashboard(Dashboard dashboard) {
        return dashboardRepository.saveDashboard(dashboard);
    }

    public Optional<Dashboard> getDashboardByUuid(String uuid) {
        return dashboardRepository.findDashboardByUuid(uuid);
    }

    public List<Dashboard> getDashboardsByUserUuid(String userUuid) {
        return dashboardRepository.findDashboardsByUserUuid(userUuid);
    }

    public void deleteDashboard(String uuid) {
        dashboardRepository.deleteDashboardByUuid(uuid);
    }
}
