package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.crash.GetCrashesByTownUseCase;
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

    @Inject
    GetCrashesByTownUseCase getCrashesByTownUseCase;

    @GET
    public Response getCrashesByTown(@QueryParam("town") String town){
        return Response.ok(getCrashesByTownUseCase.execute(town)).build();
    }

}
