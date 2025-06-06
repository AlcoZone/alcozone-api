package com.alcozone.infrastructure.mapper.widgetdata;

import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.domain.models.widgetdata.WidgetFilters;

public class WidgetFiltersMapper {
    public static WidgetFilters toDomain(WidgetFiltersDTO dto){
        WidgetFilters filters = new WidgetFilters();
        filters.setTown(dto.getTown());
        return filters;
    }

    public static WidgetFiltersDTO toDTO(WidgetFilters filters){
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(filters.getTown());
        return dto;
    }
}
