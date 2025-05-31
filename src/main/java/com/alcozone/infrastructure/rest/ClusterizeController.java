package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.clusterize.ClusterizeRevisionUseCase;
import com.alcozone.infrastructure.dto.clusterize.ClusterizeRequestDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clusterize")
@Produces(MediaType.APPLICATION_JSON)
public class ClusterizeController {

    @Inject ClusterizeRevisionUseCase clusterizeRevisionUseCase;

    @GET
    public Response clusterize(@Valid @BeanParam ClusterizeRequestDTO requestDTO) {
        return Response.ok(clusterizeRevisionUseCase.execute(requestDTO)).build();
    }

}
