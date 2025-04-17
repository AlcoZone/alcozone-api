package com.alcozone.infrastructure.rest;

import java.io.IOException;

import com.alcozone.application.usecase.revision.ClusterizeRevisionUseCase;
import com.alcozone.application.usecase.revision.GenerateRoadblockPredictionUseCase;
import com.alcozone.infrastructure.dto.revision.request.ClusterizeRevisionRequestDTO;
import com.alcozone.infrastructure.dto.revision.request.CreateRevisionRequestDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.alcozone.application.usecase.revision.GetRevisionUseCase;
import com.alcozone.infrastructure.dto.revision.request.GetRevisionRequestDTO;
import com.alcozone.application.usecase.revision.CreateRevisionUseCase;

@Path("/revision")
@Produces(MediaType.APPLICATION_JSON)
public class RevisionController {

    @Inject CreateRevisionUseCase createRevisionUseCase;
    @Inject GetRevisionUseCase getRevisionUseCase;
    @Inject ClusterizeRevisionUseCase clusterizeRevisionUseCase;
    @Inject GenerateRoadblockPredictionUseCase generateRoadblockPredictionUseCase;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response saveRevision(@BeanParam CreateRevisionRequestDTO requestDTO) throws IOException {
        return Response.ok(createRevisionUseCase.execute(requestDTO)).build();
    }

    @GET
    public Response getRevision(@BeanParam GetRevisionRequestDTO requestDTO) {
        return Response.ok(getRevisionUseCase.execute(requestDTO)).build();
    }

    @GET
    @Path("/clusterize")
    public Response clusterize(@Valid @BeanParam ClusterizeRevisionRequestDTO requestDTO) {
        return Response.ok(clusterizeRevisionUseCase.execute(requestDTO)).build();
    }

    @GET
    @Path("/predict")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPredictions() {
        return Response.ok(generateRoadblockPredictionUseCase.execute()).build();
    }
}
