package com.alcozone.infrastructure.dto.prediction;

import jakarta.ws.rs.QueryParam;
import lombok.Data;

@Data
public class PredictionRequestDTO {
    @QueryParam("revision")
    String revision;

    @QueryParam("startDate")
    String startDate;

    @QueryParam("endDate")
    String endDate;
}
