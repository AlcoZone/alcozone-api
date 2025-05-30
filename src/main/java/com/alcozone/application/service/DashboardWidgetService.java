package com.alcozone.application.service;

import com.alcozone.domain.models.DashboardWidget;
import com.alcozone.domain.repository.DashboardWidgetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DashboardWidgetService {

    @Inject
    DashboardWidgetRepository dashboardWidgetRepository;

    public List<DashboardWidget> saveWidgets(String dashboardUuid, List<DashboardWidget> widgets) {
        return dashboardWidgetRepository.saveWidgets(dashboardUuid, widgets);
    }

    public List<DashboardWidget> findByDashboardUuid(String dashboardUuid) {
        return dashboardWidgetRepository.findByDashboardUuid(dashboardUuid);
    }

    public void deleteByDashboardUuid(String dashboardUuid) {
        dashboardWidgetRepository.deleteByDashboardUuid(dashboardUuid);
    }
}
