package com.alcozone.application.usecase.widget;

import com.alcozone.domain.repository.WidgetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteWidgetUseCase {

    @Inject
    WidgetRepository widgetRepository;

    public void execute(String uuid) {
        widgetRepository.deleteByUuid(uuid);
    }
}
