package com.alcozone.infrastructure.mapper.widgetdata;

import com.alcozone.domain.models.widgetdata.DangerousTownMonth;
import com.alcozone.infrastructure.dto.widget.DangerousTownMonthDTO;

public class DangerousTownMonthMapper {
    public static DangerousTownMonth toDomain(DangerousTownMonthDTO dto){
        DangerousTownMonth dangerousTownMonth = new DangerousTownMonth();
        dangerousTownMonth.setMonth_name(dto.getMonth_name());
        dangerousTownMonth.setTown(dto.getTown());
        dangerousTownMonth.setTotal_accidents(dto.getTotal_accidents());
        return dangerousTownMonth;
    }

    public static DangerousTownMonthDTO toDTO(DangerousTownMonth dangerousTownMonth){
        DangerousTownMonthDTO dto = new DangerousTownMonthDTO();
        dto.setMonth_name(dangerousTownMonth.getMonth_name());
        dto.setTown(dangerousTownMonth.getTown());
        dto.setTotal_accidents(dangerousTownMonth.getTotal_accidents());
        return dto;
    }
}
