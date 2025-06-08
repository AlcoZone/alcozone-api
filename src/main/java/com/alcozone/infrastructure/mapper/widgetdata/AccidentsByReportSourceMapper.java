package com.alcozone.infrastructure.mapper.widgetdata;

import com.alcozone.domain.models.widgetdata.AccidentsByReportSource;
import com.alcozone.infrastructure.dto.widget.AccidentsByReportSourceDTO;

public class AccidentsByReportSourceMapper {
    public static AccidentsByReportSource toDomain (AccidentsByReportSourceDTO dto){
        AccidentsByReportSource accidentsByReportSource = new AccidentsByReportSource();
        accidentsByReportSource.setTotal_accidents(dto.getTotal_accidents());
        accidentsByReportSource.setReport_source(dto.getReport_source());
        return accidentsByReportSource;
    }

    public static AccidentsByReportSourceDTO toDTO(AccidentsByReportSource accidentsByReportSource){
        AccidentsByReportSourceDTO dto = new AccidentsByReportSourceDTO();
        dto.setTotal_accidents(accidentsByReportSource.getTotal_accidents());
        dto.setReport_source(accidentsByReportSource.getReport_source());
        return dto;
    }
}
