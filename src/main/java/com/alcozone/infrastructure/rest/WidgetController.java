package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.widget.*;
import com.alcozone.infrastructure.dto.widget.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/widgets")
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


    //porcentaje de accidentes
    @GET
    @Path("/accidents-percentage")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccidentPercentageDTO> getAccidentsPercentage() {
        return accidentPercentageUseCase.getAccidentPercentage();
    }

    //número de accidentes
    @GET
    @Path("/accidents-count")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccidentNumberDTO> getAccidentsNumber() {
        return accidentNumberUseCase.getAccidentsNumber();

    }

    //delegaciones más peligrosas
    @GET
    @Path("/dangerous-town")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DangerousTownDTO> getDangerousTown() {
        return dangerousTownUseCase.getDangerousTown();
    }

    //accidentes mensuales
    @GET
    @Path("/monthly-accidents")
    public List<MonthlyAccidentsDTO> getMonthlyAccident() {
        return monthlyAccidentUseCase.getMonthlyAccident();
    }

    //delegaciones peligrosas por mes
    @GET
    @Path("/dangerous-town-month")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DangerousTownMonthDTO> getDangerousTownMonth() {
        return dangerousTownMonthUseCase.getDangerousTownMonth();
    }


}

