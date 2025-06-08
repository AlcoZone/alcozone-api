package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.crash.GetCrashesByRevisionUuidUseCase;
import com.alcozone.application.usecase.date.GetCrashesBetweenDatesUseCase;
import com.alcozone.domain.model.Crash;
import com.alcozone.infrastructure.dto.crash.DefaultCrashesResponseDTO;
import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @Inject
    GetCrashesBetweenDatesUseCase getCrashesBetweenDatesUseCase;
    
    @GET
    @Path("/date")
    public Response getCrashesBetweenDates(@QueryParam("start") String startDate,
                                           @QueryParam("end") String endDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);

            LocalDateTime startDateTime = start.atStartOfDay();
            LocalDateTime endDateTime = end.atTime(23, 59, 59);

            List<Crash> crashes = getCrashesBetweenDatesUseCase.execute(startDateTime, endDateTime);

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
}

