package com.alcozone.infrastructure.mapper.widgetdata;

import com.alcozone.domain.models.widgetdata.AccidentPercentage;
import com.alcozone.infrastructure.dto.widget.AccidentPercentageDTO;

public class AccidentPercentageMapper {
    public static AccidentPercentage toDomain (AccidentPercentageDTO dto){
        AccidentPercentage accidentPercentage = new AccidentPercentage();
        accidentPercentage.setPercentage(dto.getPercentage());
        accidentPercentage.setSubType(dto.getSubType());
        return accidentPercentage;
    }

    public static AccidentPercentageDTO toDTO(AccidentPercentage accidentPercentage){
        AccidentPercentageDTO dto = new AccidentPercentageDTO();
        dto.setPercentage(accidentPercentage.getPercentage());
        dto.setSubType(accidentPercentage.getSubType());
        return dto;
    }
}
