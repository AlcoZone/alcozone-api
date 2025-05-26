package com.alcozone.application.usecase.widget;

import com.alcozone.application.service.WidgetService;
import com.alcozone.domain.models.Widget;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UpdateDashboardWidgetLayoutUseCase {

    @Inject
    WidgetService widgetService;

    public List<Widget> execute(List<Widget> widgets) {
        return widgetService.saveWidgets(widgets);
    }
}
