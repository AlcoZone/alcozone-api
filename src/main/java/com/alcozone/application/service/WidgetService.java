package com.alcozone.application.service;

import com.alcozone.domain.repository.WidgetRepository;
import com.alcozone.infrastructure.dto.widget.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class WidgetService {

    @Inject
    WidgetRepository widgetRepository;

    //widget 1 - Accident Number
    public List <AccidentNumberDTO> getAccidentsNumber (){
        return widgetRepository.getAccidentsNumber();
    }

    //widget 2 - Accident Percentage
    public List<AccidentPercentageDTO> getAccidentPercentage() {
        return widgetRepository.getAccidentsPercentage();
    }


    //widget 3 - Dangerous Town
    public List<DangerousTownDTO> getDangerousTown(){
        return widgetRepository.getDangerousTown();
    }


    //widget 4 - Monthly Accident
    public List<MonthlyAccidentsDTO> getMonthlyAccident(){
        return widgetRepository.getMonthlyAccident();
    }


    //widget 5 - Dangerous Towns per month
    public List<DangerousTownMonthDTO> getDangerousTownMonth(){
        return widgetRepository.getDangerousTownMonth();
    }


}
