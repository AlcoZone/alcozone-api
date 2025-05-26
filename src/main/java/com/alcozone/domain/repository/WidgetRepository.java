package com.alcozone.domain.repository;

import java.util.List;
import java.util.Optional;
import com.alcozone.domain.models.Widget;

public interface WidgetRepository {

    List<Widget> saveWidgets(List<Widget> widgets);

    List<Widget> findWidgetsByDashboardUuid(String dashboardUuid);

    void deleteWidgetsByDashboardUuid(String dashboardUuid);
}
