package com.alcozone.infrastructure.rest;

import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.alcozone.application.usecase.accident.GetAccidentsByRevisionUuidUseCase;

@Path("/accidents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccidentController {

    @Inject GetAccidentsByRevisionUuidUseCase getAccidentsByRevisionUuidUseCase;

    @GET
    public Response getAccidentsByRevisionUuid(@QueryParam("revision") String uuid) {
        return Response.ok(getAccidentsByRevisionUuidUseCase.execute(uuid)).build();
    }
}
