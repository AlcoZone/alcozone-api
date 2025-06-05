package com.alcozone.infrastructure.mapper.widgetdata;

import com.alcozone.domain.models.widgetdata.DailyAccidents;
import com.alcozone.infrastructure.dto.widget.DailyAccidentsDTO;

public class DailyAccidentsMapper {
    public static DailyAccidents toDomain (DailyAccidentsDTO dto){
        DailyAccidents dailyAccidents = new DailyAccidents();
        dailyAccidents.setTotal_accidents(dto.getTotal_accidents());
        dailyAccidents.setAccident_date(dto.getAccident_date());
        return dailyAccidents;
    }

    public static DailyAccidentsDTO toDTO(DailyAccidents dailyAccidents){
        DailyAccidentsDTO dto = new DailyAccidentsDTO();
        dto.setTotal_accidents(dailyAccidents.getTotal_accidents());
        dto.setAccident_date(dailyAccidents.getAccident_date());
        return dto;
    }
}
