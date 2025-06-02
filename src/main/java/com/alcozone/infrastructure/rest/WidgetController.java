package com.alcozone.infrastructure.rest;

import com.alcozone.application.dto.widget.CreateWidgetDTO;
import com.alcozone.application.dto.widget.WidgetFiltersDTO;
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
    @Inject DailyAccidentsUseCase dailyAccidentsUseCase;
    @Inject AccidentsByReportSourceUseCase accidentsByReportSourceUseCase;

    @GET
    public Response getAllWidgets() {
        List<Widget> widgets = getAllWidgetDefinitionsUseCase.execute();
        return Response.ok(widgets).build();
    }

    @POST
    public Response saveWidget(@Valid CreateWidgetDTO dto) {
        Widget saved = saveWidgetUseCase.execute(dto);
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
    public Response getAccidentsPercentage(@QueryParam("town") String town) {
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(town);
        List<AccidentPercentageDTO> result = accidentPercentageUseCase.getAccidentPercentage(dto);
        return Response.ok(result).build();
    }

    @GET
    @Path("/accidents-count")
    public Response getAccidentsNumber(@QueryParam("town") String town) {
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(town);
        List<AccidentNumberDTO> result = accidentNumberUseCase.getAccidentsNumber(dto);
        return Response.ok(result).build();
    }

    @GET
    @Path("/dangerous-town")
    public Response getDangerousTown( WidgetFiltersDTO dto) {
        List<DangerousTownDTO> result = dangerousTownUseCase.getDangerousTown(dto);
        return Response.ok(result).build();
    }

    @GET
    @Path("/monthly-accidents")
    public Response getMonthlyAccident(@QueryParam("town") String town) {
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(town);
        List<MonthlyAccidentsDTO> result = monthlyAccidentUseCase.getMonthlyAccident(dto);
        return Response.ok(result).build();
    }

    @GET
    @Path("/dangerous-town-month")
    public Response getDangerousTownMonth(WidgetFiltersDTO dto) {
        List<DangerousTownMonthDTO> result = dangerousTownMonthUseCase.getDangerousTownMonth(dto);
        return Response.ok(result).build();
    }

    @GET
    @Path("/daily-accidents")
    public Response getDailyAccidents(@QueryParam("town") String town) {
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(town);
        List<DailyAccidentsDTO> result = dailyAccidentsUseCase.getDailyAccidents(dto);
        return Response.ok(result).build();
    }

    @GET
    @Path("/accidents-by-report-source")
    public Response getAccidentsByReportSource(@QueryParam("town") String town) {
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(town);
        List<AccidentsByReportSourceDTO> result = accidentsByReportSourceUseCase.getAccidentsByReportSource(dto);
        return Response.ok(result).build();
    }
}