package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.crash.GetAvailableDatesUseCase;
import com.alcozone.application.usecase.crash.GetCrashesByRevisionUuidUseCase;
import com.alcozone.application.usecase.date.GetCrashesBetweenDatesUseCase;
import com.alcozone.domain.model.Crash;
import com.alcozone.infrastructure.dto.crash.DefaultCrashesResponseDTO;
import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/crashes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CrashController {

    @Inject GetCrashesByRevisionUuidUseCase getCrashesByRevisionUuidUseCase;
    @Inject GetCrashesBetweenDatesUseCase getCrashesBetweenDatesUseCase;
    @Inject GetAvailableDatesUseCase getAvailableDatesUseCase;

    @GET
    public Response getCrashesByRevisionUuid(@QueryParam("revision") String uuid) {
        return Response.ok(getCrashesByRevisionUuidUseCase.execute(uuid)).build();
    }
    
    @GET
    @Path("/date")
    public Response getCrashesBetweenDates(@QueryParam("start") String startDate, @QueryParam("end") String endDate) {
        try {
            List<Crash> crashes = getCrashesBetweenDatesUseCase.execute(startDate, endDate);

            DefaultCrashesResponseDTO responseDTO = new DefaultCrashesResponseDTO();
            responseDTO.setCount(crashes.size());
            responseDTO.setData(crashes);

            return Response.ok(responseDTO).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid date format. Use format: dd-MM-yyyy")
                    .build();
        }
    }

    @GET
    @Path("/availableDates")
    public Response getAvailableDates() {
        return Response.ok(getAvailableDatesUseCase.execute()).build();
    }
}

