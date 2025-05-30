package com.alcozone.domain.repository;

import java.util.List;
import com.alcozone.domain.models.DashboardWidget;

public interface DashboardWidgetRepository {

    List<DashboardWidget> saveWidgets(List<DashboardWidget> widgets);

    List<DashboardWidget> findByDashboardUuid(String dashboardUuid);

    void deleteByDashboardUuid(String dashboardUuid);
}
