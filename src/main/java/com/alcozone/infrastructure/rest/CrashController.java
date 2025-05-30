package com.alcozone.infrastructure.rest;

import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.alcozone.application.usecase.crash.GetCrashesByRevisionUuidUseCase;

@Path("/crashes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CrashController {

    @Inject
    GetCrashesByRevisionUuidUseCase getCrashesByRevisionUuidUseCase;

    @GET
    public Response getCrashesByRevisionUuid(@QueryParam("revision") String uuid) {
        return Response.ok(getCrashesByRevisionUuidUseCase.execute(uuid)).build();
    }
}
