package com.alcozone.infrastructure.mapper.widgetdata;

import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.domain.models.widgetdata.WidgetFilters;

public class WidgetFiltersMapper {
    public static WidgetFilters toDomain(WidgetFiltersDTO dto){
        WidgetFilters filters = new WidgetFilters();
        filters.setTown(dto.getTown());
        filters.setStartDate(dto.getStartDate());
        filters.setEndDate(dto.getEndDate());
        return filters;
    }

    public static WidgetFiltersDTO toDTO(WidgetFilters filters){
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(filters.getTown());
        dto.setStartDate(filters.getStartDate());
        dto.setEndDate(filters.getEndDate());
        return dto;
    }
}
