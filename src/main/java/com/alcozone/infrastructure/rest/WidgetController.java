package com.alcozone.infrastructure.rest;

import com.alcozone.application.dto.widget.CreateWidgetDTO;
import com.alcozone.application.dto.widget.WidgetFiltersDTO;
import com.alcozone.application.usecase.widget.*;
import com.alcozone.domain.models.widgetdata.*;
import com.alcozone.infrastructure.dto.widget.*;
import com.alcozone.domain.models.Widget;
import com.alcozone.application.usecase.widget.GetAllWidgetDefinitionsUseCase;
import com.alcozone.application.usecase.widget.SaveWidgetUseCase;
import com.alcozone.application.usecase.widget.DeleteWidgetUseCase;

import com.alcozone.infrastructure.mapper.widgetdata.*;
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
    public Response getAccidentsPercentage(@QueryParam("town") String town, @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(town);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        List<AccidentPercentage> accidentPercentage = accidentPercentageUseCase.execute(dto);
        List<AccidentPercentageDTO> responseDto = accidentPercentage.stream()
                .map(AccidentPercentageMapper::toDTO)
                .toList();
        return Response.ok(responseDto).build();
    }

    @GET
    @Path("/accidents-count")
    public Response getAccidentsNumber(@QueryParam("town") String town, @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(town);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        List<AccidentNumber> accidentNumber = accidentNumberUseCase.execute(dto);
        List<AccidentNumberDTO> responseDto = accidentNumber.stream()
                .map(AccidentNumberMapper::toDTO)
                .toList();
        return Response.ok(responseDto).build();
    }

    @GET
    @Path("/dangerous-town")
    public Response getDangerousTown(@QueryParam("town") String town,@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(town);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        List<DangerousTown> result = dangerousTownUseCase.execute(dto);
        return Response.ok(result).build();
    }

    @GET
    @Path("/monthly-accidents")
    public Response getMonthlyAccident(@QueryParam("town") String town, @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(town);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        List<MonthlyAccidents> monthlyAccidents = monthlyAccidentUseCase.execute(dto);
        List<MonthlyAccidentsDTO> responseDto = monthlyAccidents.stream()
                .map(MonthlyAccidentsMapper::toDTO)
                .toList();
        return Response.ok(responseDto).build();
    }

    @GET
    @Path("/dangerous-town-month")
    public Response getDangerousTownMonth(@QueryParam("town") String town, @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(town);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        List<DangerousTownMonth> result = dangerousTownMonthUseCase.execute(dto);
        return Response.ok(result).build();
    }

    @GET
    @Path("/daily-accidents")
    public Response getDailyAccidents(@QueryParam("town") String town, @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(town);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        List<DailyAccidents> dailyAccidents = dailyAccidentsUseCase.execute(dto);
        List<DailyAccidentsDTO> responseDto = dailyAccidents.stream()
                .map(DailyAccidentsMapper::toDTO)
                .toList();
        return Response.ok(responseDto).build();
    }

    @GET
    @Path("/accidents-by-report-source")
    public Response getAccidentsByReportSource(@QueryParam("town") String town, @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        WidgetFiltersDTO dto = new WidgetFiltersDTO();
        dto.setTown(town);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        List<AccidentsByReportSource> accidentsByReportSource = accidentsByReportSourceUseCase.execute(dto);
        List<AccidentsByReportSourceDTO> responseDto = accidentsByReportSource.stream()
                .map(AccidentsByReportSourceMapper::toDTO)
                .toList();
        return Response.ok(responseDto).build();
    }
}