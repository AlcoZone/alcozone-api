package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.crash.GetCrashesByRevisionUuidUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
