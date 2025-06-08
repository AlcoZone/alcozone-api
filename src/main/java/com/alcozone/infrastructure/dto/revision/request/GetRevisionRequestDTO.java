package com.alcozone.infrastructure.dto.revision.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.ws.rs.QueryParam;

@Getter
@Setter
public class GetRevisionRequestDTO {
    @QueryParam("uuid")
    private String uuid;

    @QueryParam("withData")
    private boolean withData;
}
