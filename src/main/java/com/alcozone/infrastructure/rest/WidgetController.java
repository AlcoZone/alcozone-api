package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.widget.GetWidgetsForDashboardUseCase;
import com.alcozone.application.usecase.widget.UpdateDashboardWidgetLayoutUseCase;
import com.alcozone.application.usecase.widget.DeleteWidgetsByDashboardUseCase;
import com.alcozone.domain.models.Widget;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/dashboards/{dashboardUuid}/widgets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WidgetController {

    @Inject GetWidgetsForDashboardUseCase getWidgetsForDashboardUseCase;
    @Inject UpdateDashboardWidgetLayoutUseCase updateDashboardWidgetLayoutUseCase;
    @Inject DeleteWidgetsByDashboardUseCase deleteWidgetsByDashboardUseCase;

    @GET
    public Response getWidgets(@PathParam("dashboardUuid") String dashboardUuid) {
        List<Widget> widgets = getWidgetsForDashboardUseCase.execute(dashboardUuid);
        return Response.ok(widgets).build();
    }

    @POST
    public Response saveOrUpdateLayout(@PathParam("dashboardUuid") String dashboardUuid,
                                       @Valid List<Widget> widgets) {
        widgets.forEach(w -> w.setDashboardUuid(dashboardUuid));
        List<Widget> saved = updateDashboardWidgetLayoutUseCase.execute(widgets);
        return Response.ok(saved).build();
    }

    @DELETE
    public Response deleteWidgets(@PathParam("dashboardUuid") String dashboardUuid) {
        deleteWidgetsByDashboardUseCase.execute(dashboardUuid);
        return Response.noContent().build();
    }
}
