package com.alcozone.infrastructure.dto.revision.request;

import jakarta.ws.rs.QueryParam;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClusterizeRevisionRequestDTO {
    @NotNull
    @QueryParam("uuid")
    private String uuid;

    @NotNull
    @QueryParam("epsilonMeters")
    private Double epsilonMeters;

    @NotNull
    @QueryParam("minCrashes")
    private Integer minCrashes;

    @QueryParam("withData")
    private boolean withData;
}
