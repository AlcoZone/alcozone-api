package com.alcozone.application.usecase.widget;

import com.alcozone.application.service.WidgetService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteWidgetsByDashboardUseCase {

    @Inject
    WidgetService widgetService;

    public void execute(String dashboardUuid) {
        widgetService.deleteWidgetsByDashboardUuid(dashboardUuid);
    }
}
