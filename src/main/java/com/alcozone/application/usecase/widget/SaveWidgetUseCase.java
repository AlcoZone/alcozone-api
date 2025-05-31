package com.alcozone.application.usecase.widget;

import com.alcozone.application.dto.widget.CreateWidgetDTO;
import com.alcozone.application.service.WidgetService;
import com.alcozone.domain.models.Widget;
import com.alcozone.domain.repository.WidgetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SaveWidgetUseCase {

    @Inject
    WidgetService widgetService;

    public Widget execute(CreateWidgetDTO dto) {
        return widgetService.save(dto);
    }}
