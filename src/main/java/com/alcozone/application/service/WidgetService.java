package com.alcozone.application.service;

import com.alcozone.application.dto.widget.CreateWidgetDTO;
import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.application.mapper.WidgetDTOMapper;
import com.alcozone.domain.models.Widget;
import com.alcozone.domain.repository.WidgetRepository;
import com.alcozone.infrastructure.dto.widget.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class WidgetService {

    @Inject
    WidgetRepository widgetRepository;

    public List<Widget> findAllWidgets() {
        return widgetRepository.findAllWidgets();
    }

    public Widget save(CreateWidgetDTO dto) {
        Widget widget = WidgetDTOMapper.fromCreateDTO(dto);
        return widgetRepository.save(widget);
    }

    public void deleteByUuid(String uuid) {
        widgetRepository.deleteByUuid(uuid);
    }

    public List <AccidentNumberDTO> getAccidentsNumber (WidgetFiltersDTO filters){
        return widgetRepository.getAccidentsNumber(filters);
    }

    //widget 2 - Accident Percentage
    public List<AccidentPercentageDTO> getAccidentPercentage(WidgetFiltersDTO filters) {
        return widgetRepository.getAccidentsPercentage(filters);
    }

    //widget 3 - Dangerous Town
    public List<DangerousTownDTO> getDangerousTown(WidgetFiltersDTO filters){
        return widgetRepository.getDangerousTown(filters);
    }

    //widget 4 - Monthly Accident
    public List<MonthlyAccidentsDTO> getMonthlyAccident(WidgetFiltersDTO filters){
        return widgetRepository.getMonthlyAccident(filters);
    }


    //widget 5 - Dangerous Towns per month
    public List<DangerousTownMonthDTO> getDangerousTownMonth(WidgetFiltersDTO filters){
        return widgetRepository.getDangerousTownMonth(filters);
    }

    //widget 6 - Daily Accidents
    public List<DailyAccidentsDTO> getDailyAccidents(WidgetFiltersDTO filters){
        return widgetRepository.getDailyAccidents(filters);
    }

    //widget 7 - Accidents By Report Source
    public List<AccidentsByReportSourceDTO> getAccidentsByReportSource(WidgetFiltersDTO filters){
        return widgetRepository.getAccidentsByReportSource(filters);
    }

}
