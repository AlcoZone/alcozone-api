package com.alcozone.application.usecase.widget;

import com.alcozone.domain.models.Widget;
import com.alcozone.domain.repository.WidgetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetAllWidgetDefinitionsUseCase {

    @Inject
    WidgetRepository widgetRepository;

    public List<Widget> execute() {
        return widgetRepository.findAll();
    }
}
