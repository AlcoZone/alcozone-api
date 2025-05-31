package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.widget.*;
import com.alcozone.infrastructure.dto.widget.*;
import com.alcozone.domain.models.Widget;
import com.alcozone.application.usecase.widget.GetAllWidgetDefinitionsUseCase;
import com.alcozone.application.usecase.widget.SaveWidgetUseCase;
import com.alcozone.application.usecase.widget.DeleteWidgetUseCase;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/widgets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WidgetController {

    @Inject GetAllWidgetDefinitionsUseCase getAllWidgetDefinitionsUseCase;
    @Inject SaveWidgetUseCase saveWidgetUseCase;
    @Inject DeleteWidgetUseCase deleteWidgetUseCase;
    @Inject AccidentPercentageUseCase accidentPercentageUseCase;
    @Inject AccidentNumberUseCase accidentNumberUseCase;
    @Inject DangerousTownUseCase dangerousTownUseCase;
    @Inject MonthlyAccidentsUseCase monthlyAccidentUseCase;
    @Inject DangerousTownMonthUseCase dangerousTownMonthUseCase;

    @GET
    public Response getAllWidgets() {
        List<Widget> widgets = getAllWidgetDefinitionsUseCase.execute();
        return Response.ok(widgets).build();
    }

    @POST
    public Response createWidget(@Valid Widget widget) {
        Widget saved = saveWidgetUseCase.execute(widget);
        return Response.ok(saved).build();
    }

    @DELETE
    @Path("/{uuid}")
    public Response deleteWidget(@PathParam("uuid") String uuid) {
        deleteWidgetUseCase.execute(uuid);
        return Response.noContent().build();
    }

    @GET
    @Path("/accidents-percentage")
    public Response getAccidentsPercentage() {
        List<AccidentPercentageDTO> result = accidentPercentageUseCase.getAccidentPercentage();
        return Response.ok(result).build();
    }

    @GET
    @Path("/accidents-count")
    public Response getAccidentsNumber() {
        List<AccidentNumberDTO> result = accidentNumberUseCase.getAccidentsNumber();
        return Response.ok(result).build();
    }

    @GET
    @Path("/dangerous-town")
    public Response getDangerousTown() {
        List<DangerousTownDTO> result = dangerousTownUseCase.getDangerousTown();
        return Response.ok(result).build();
    }


    @Path("/monthly-accidents")
    public Response getMonthlyAccident() {
        List<MonthlyAccidentsDTO> result = monthlyAccidentUseCase.getMonthlyAccident();
        return Response.ok(result).build();
    }

    @GET
    @Path("/dangerous-town-month")
    public Response getDangerousTownMonth() {
        List<DangerousTownMonthDTO> result = dangerousTownMonthUseCase.getDangerousTownMonth();
        return Response.ok(result).build();
    }
}
