package com.alcozone.infrastructure.rest;

import java.io.IOException;

import com.alcozone.infrastructure.dto.revision.CreateRevisionRequestDTO;

import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.alcozone.application.usecase.revision.GetRevisionUseCase;
import com.alcozone.infrastructure.dto.revision.GetRevisionRequestDTO;
import com.alcozone.application.usecase.revision.CreateRevisionUseCase;

@Path("/revision")
@Produces(MediaType.APPLICATION_JSON)
public class RevisionController {

    @Inject CreateRevisionUseCase createRevisionUseCase;
    @Inject GetRevisionUseCase getRevisionUseCase;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response saveRevision(@BeanParam CreateRevisionRequestDTO requestDTO) throws IOException {
        return Response.ok(createRevisionUseCase.execute(requestDTO)).build();
    }

    @GET
    public Response getRevision(@BeanParam GetRevisionRequestDTO requestDTO) {
        return Response.ok(getRevisionUseCase.execute(requestDTO)).build();
    }
}
