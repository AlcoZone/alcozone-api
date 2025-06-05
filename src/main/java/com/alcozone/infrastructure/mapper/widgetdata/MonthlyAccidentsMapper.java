package com.alcozone.infrastructure.mapper.widgetdata;

import com.alcozone.domain.models.widgetdata.MonthlyAccidents;
import com.alcozone.infrastructure.dto.widget.MonthlyAccidentsDTO;

public class MonthlyAccidentsMapper {
    public static MonthlyAccidents toDomain(MonthlyAccidentsDTO dto){
        MonthlyAccidents monthlyAccidents = new MonthlyAccidents();
        monthlyAccidents.setMonth_name(dto.getMonth_name());
        monthlyAccidents.setAccidents(dto.getAccidents());
        return monthlyAccidents;
    }

    public static MonthlyAccidentsDTO toDTO(MonthlyAccidents monthlyAccidents){
        MonthlyAccidentsDTO dto = new MonthlyAccidentsDTO();
        dto.setMonth_name(monthlyAccidents.getMonth_name());
        dto.setAccidents(monthlyAccidents.getAccidents());
        return dto;
    }
}
