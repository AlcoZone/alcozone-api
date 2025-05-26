package com.alcozone.domain.repository;

import java.util.List;
import java.util.Optional;
import com.alcozone.domain.models.Dashboard;

public interface DashboardRepository {

    Dashboard saveDashboard(Dashboard dashboard);

    Optional<Dashboard> findDashboardByUuid(String uuid);

    List<Dashboard> findDashboardsByUserUuid(String userUuid);

    void deleteDashboardByUuid(String uuid);
}
