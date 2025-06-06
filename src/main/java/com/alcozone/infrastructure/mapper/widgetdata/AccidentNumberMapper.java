package com.alcozone.infrastructure.mapper.widgetdata;

import com.alcozone.domain.models.widgetdata.AccidentNumber;
import com.alcozone.infrastructure.dto.widget.AccidentNumberDTO;

public class AccidentNumberMapper {
    public static AccidentNumber toDomain (AccidentNumberDTO dto){
        AccidentNumber accidentNumber = new AccidentNumber();
        accidentNumber.setAccidentCount(dto.getAccidentCount());
        accidentNumber.setSubType(dto.getSubType());
        return accidentNumber;
    }

    public static AccidentNumberDTO toDTO(AccidentNumber accidentNumber){
        AccidentNumberDTO dto = new AccidentNumberDTO();
        dto.setAccidentCount(accidentNumber.getAccidentCount());
        dto.setSubType(accidentNumber.getSubType());
        return dto;
    }
}
