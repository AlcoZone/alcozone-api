package com.alcozone.application.service;

import com.alcozone.domain.models.Dashboard;
import com.alcozone.domain.repository.DashboardRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DashboardService {

    @Inject
    DashboardRepository dashboardRepository;

    public Dashboard createDashboard(Dashboard dashboard) {
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
