package com.alcozone.application.usecase.widget;

import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.application.service.WidgetService;
import com.alcozone.infrastructure.dto.widget.AccidentNumberDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped

public class AccidentNumberUseCase {
    @Inject
    WidgetService widgetService;


    public List<AccidentNumberDTO> getAccidentsNumber(WidgetFiltersDTO filters){
        return widgetService.getAccidentsNumber(filters);
    }

}

