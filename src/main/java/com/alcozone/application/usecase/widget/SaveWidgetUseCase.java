package com.alcozone.application.usecase.widget;

import com.alcozone.domain.models.Widget;
import com.alcozone.domain.repository.WidgetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SaveWidgetUseCase {

    @Inject
    WidgetRepository widgetRepository;

    public Widget execute(Widget widget) {
        return widgetRepository.save(widget);
    }
}
