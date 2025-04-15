package com.alcozone.infrastructure.dto.revision.request;

import jakarta.ws.rs.QueryParam;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ClusterizeRevisionRequestDTO {
    @NonNull
    @QueryParam("uuid")
    private String uuid;

    @NonNull
    @QueryParam("epsilonMeters")
    private Double epsilonMeters;

    @NonNull
    @QueryParam("minAccidents")
    private Integer minAccidents;

    @QueryParam("centroids")
    private boolean centroids;

    @QueryParam("accidents")
    private boolean accidents;
}
