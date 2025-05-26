package com.alcozone.application.service;

import com.alcozone.domain.models.Widget;
import com.alcozone.domain.repository.WidgetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class WidgetService {

    @Inject
    WidgetRepository widgetRepository;

    public List<Widget> saveWidgets(List<Widget> widgets) {
        return widgetRepository.saveWidgets(widgets);
    }

    public List<Widget> getWidgetsByDashboardUuid(String dashboardUuid) {
        return widgetRepository.findWidgetsByDashboardUuid(dashboardUuid);
    }

    public void deleteWidgetsByDashboardUuid(String dashboardUuid) {
        widgetRepository.deleteWidgetsByDashboardUuid(dashboardUuid);
    }
}
