package com.alcozone.infrastructure.dto.clusterize;

import jakarta.ws.rs.QueryParam;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClusterizeRequestDTO {
    @NotNull
    @QueryParam("revision")
    private String uuid;
}
