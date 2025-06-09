package com.alcozone.infrastructure.dto.clusterize;

import jakarta.ws.rs.QueryParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClusterizeRequestDTO {
    @QueryParam("revision")
    private String uuid;

    @QueryParam("startDate")
    String startDate;

    @QueryParam("endDate")
    String endDate;
}
