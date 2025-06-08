package com.alcozone.infrastructure.mapper.widgetdata;

import com.alcozone.domain.models.widgetdata.DangerousTown;
import com.alcozone.infrastructure.dto.widget.DangerousTownDTO;

public class DangerousTownMapper {
    public static DangerousTown toDomain (DangerousTownDTO dto){
        DangerousTown dangerousTown = new DangerousTown();
        dangerousTown.setTown(dto.getTown());
        dangerousTown.setTotal_accidents(dto.getTotal_accidents());
        return dangerousTown;
    }

    public static DangerousTownDTO toDTO(DangerousTown dangerousTown){
        DangerousTownDTO dto = new DangerousTownDTO();
        dto.setTown(dangerousTown.getTown());
        dto.setTotal_accidents(dangerousTown.getTotal_accidents());
        return dto;
    }
}
