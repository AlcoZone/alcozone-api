package com.alcozone.application.service;

import com.alcozone.application.dto.widget.CreateWidgetDTO;
import com.alcozone.application.mapper.WidgetDTOMapper;
import com.alcozone.domain.models.Widget;
import com.alcozone.domain.models.widgetdata.*;
import com.alcozone.domain.repository.WidgetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class WidgetService {

    @Inject
    WidgetRepository widgetRepository;

    public List<Widget> findAllWidgets() {
        return widgetRepository.findAllWidgets();
    }

    public Widget save(CreateWidgetDTO dto) {
        Widget widget = WidgetDTOMapper.fromCreateDTO(dto);
        widget.setUuid(UUID.randomUUID().toString());
        widget.setCreatedAt(LocalDateTime.now());
        widget.setUpdatedAt(LocalDateTime.now());
        return widgetRepository.save(widget);
    }

    public void deleteByUuid(String uuid) {
        widgetRepository.deleteByUuid(uuid);
    }

    public List <AccidentNumber> getAccidentsNumber (WidgetFilters filters){
        return widgetRepository.getAccidentsNumber(filters);
    }

    public List<AccidentPercentage> getAccidentPercentage(WidgetFilters filters) {
        return widgetRepository.getAccidentsPercentage(filters);
    }

    public List<DangerousTown> getDangerousTown(WidgetFilters filters){
        return widgetRepository.getDangerousTown(filters);
    }

    public List<MonthlyAccidents> getMonthlyAccident(WidgetFilters filters){
        return widgetRepository.getMonthlyAccident(filters);
    }

    public List<DangerousTownMonth> getDangerousTownMonth(WidgetFilters filters){
        return widgetRepository.getDangerousTownMonth(filters);
    }

    public List<DailyAccidents> getDailyAccidents(WidgetFilters filters){
        return widgetRepository.getDailyAccidents(filters);
    }

    public List<AccidentsByReportSource> getAccidentsByReportSource(WidgetFilters filters){
        return widgetRepository.getAccidentsByReportSource(filters);
    }

}
