package com.alcozone.application.mapper;

import com.alcozone.application.dto.widget.CreateWidgetDTO;
import com.alcozone.domain.models.Widget;

public class WidgetDTOMapper {

    public static Widget fromCreateDTO(CreateWidgetDTO dto) {
        return new Widget(
                null,
                null,
                dto.getName(),
                dto.getDescription(),
                dto.getMinWidth(),
                dto.getMinHeight(),
                null,
                null
        );
    }
}
