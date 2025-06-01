package com.alcozone.infrastructure.rest;

import com.alcozone.application.service.CrashService;
import com.alcozone.application.usecase.date.GetCrashesBetweenDatesUseCase;
import com.alcozone.infrastructure.dto.crash.DefaultCrashesResponseDTO;
import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.alcozone.application.usecase.crash.GetCrashesByRevisionUuidUseCase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.arjuna.ats.arjuna.tools.osb.mbean.StateManagerWrapper.formatter;

@Path("/crashes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CrashController {

    @Inject
    GetCrashesByRevisionUuidUseCase getCrashesByRevisionUuidUseCase;
    @Inject
    CrashService crashService;

    @GET
    public Response getCrashesByRevisionUuid(@QueryParam("revision") String uuid) {
        return Response.ok(getCrashesByRevisionUuidUseCase.execute(uuid)).build();
    }

    @Inject
    GetCrashesBetweenDatesUseCase getCrashesBetweenDatesUseCase;

    @GET
    @Path("/date")
    public Response getCrashesBetweenDates(@QueryParam("start") String startDate, @QueryParam("end") String endDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);

            LocalDateTime startDateTime = start.atStartOfDay();                // 00:00:00
            LocalDateTime endDateTime = end.atTime(23, 59, 59);               // 23:59:59

            DefaultCrashesResponseDTO responseDTO = getCrashesBetweenDatesUseCase.execute(startDateTime, endDateTime);
            return Response.ok(responseDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid date format. Use format: dd-MM-yyyy")
                    .build();
        }
    }


}

