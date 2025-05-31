package com.alcozone.application.mapper;

import com.alcozone.application.dto.widget.CreateWidgetDTO;
import com.alcozone.domain.models.Widget;

import java.time.LocalDateTime;
import java.util.UUID;

public class WidgetDTOMapper {

    public static Widget fromCreateDTO(CreateWidgetDTO dto) {
        return new Widget(
                null,
                UUID.randomUUID().toString(),
                dto.getName(),
                dto.getDescription(),
                dto.getMinWidth(),
                dto.getMinHeight(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
