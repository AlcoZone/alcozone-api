package com.alcozone.application.usecase.widget;

import com.alcozone.application.service.WidgetService;
import com.alcozone.infrastructure.dto.widget.DangerousTownDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DangerousTownUseCase {
    @Inject
    WidgetService widgetService;

    public List<DangerousTownDTO> getDangerousTown(){
        return widgetService.getDangerousTown();
    }
}
