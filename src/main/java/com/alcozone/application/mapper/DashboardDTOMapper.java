package com.alcozone.application.mapper;

import com.alcozone.application.dto.dashboard.CreateDashboardDTO;
import com.alcozone.domain.models.Dashboard;

public class DashboardDTOMapper {

    public static Dashboard fromCreateDTO(CreateDashboardDTO dto) {
        return new Dashboard(
                null,
                null,
                dto.getUserUuid(),
                dto.getName(),
                null,
                null
        );
    }
}
