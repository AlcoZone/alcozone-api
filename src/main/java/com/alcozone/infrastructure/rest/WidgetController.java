package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.widget.*;
import com.alcozone.infrastructure.dto.widget.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/widgets")
@Produces(MediaType.APPLICATION_JSON)
public class WidgetController {

    @Inject
    AccidentPercentageUseCase accidentPercentageUseCase;

    @Inject
    AccidentNumberUseCase accidentNumberUseCase;

    @Inject
    DangerousTownUseCase dangerousTownUseCase;

    @Inject
    MonthlyAccidentsUseCase monthlyAccidentUseCase;

    @Inject
    DangerousTownMonthUseCase dangerousTownMonthUseCase;

    // porcentaje de accidentes
    @GET
    @Path("/accidents-percentage")
    public Response getAccidentsPercentage() {
        List<AccidentPercentageDTO> result = accidentPercentageUseCase.getAccidentPercentage();
        return Response.ok(result).build();
    }

    // número de accidentes
    @GET
    @Path("/accidents-count")
    public Response getAccidentsNumber() {
        List<AccidentNumberDTO> result = accidentNumberUseCase.getAccidentsNumber();
        return Response.ok(result).build();
    }

    // delegaciones más peligrosas
    @GET
    @Path("/dangerous-town")
    public Response getDangerousTown() {
        List<DangerousTownDTO> result = dangerousTownUseCase.getDangerousTown();
        return Response.ok(result).build();
    }

    // accidentes mensuales
    @GET
    @Path("/monthly-accidents")
    public Response getMonthlyAccident() {
        List<MonthlyAccidentsDTO> result = monthlyAccidentUseCase.getMonthlyAccident();
        return Response.ok(result).build();
    }

    // delegaciones peligrosas por mes
    @GET
    @Path("/dangerous-town-month")
    public Response getDangerousTownMonth() {
        List<DangerousTownMonthDTO> result = dangerousTownMonthUseCase.getDangerousTownMonth();
        return Response.ok(result).build();
    }
}


