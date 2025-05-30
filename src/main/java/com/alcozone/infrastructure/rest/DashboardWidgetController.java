package com.alcozone.infrastructure.rest;

import com.alcozone.domain.models.DashboardWidget;
import com.alcozone.application.usecase.dashboardwidget.GetWidgetsForDashboardUseCase;
import com.alcozone.application.usecase.dashboardwidget.UpdateDashboardWidgetLayoutUseCase;
import com.alcozone.application.usecase.dashboardwidget.DeleteDashboardAndWidgetsUseCase;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/dashboards/{dashboardUuid}/widgets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DashboardWidgetController {

    @Inject GetWidgetsForDashboardUseCase getWidgetsForDashboardUseCase;
    @Inject UpdateDashboardWidgetLayoutUseCase updateDashboardWidgetLayoutUseCase;
    @Inject DeleteDashboardAndWidgetsUseCase deleteDashboardAndWidgetsUseCase;

    @GET
    public Response getWidgets(@PathParam("dashboardUuid") String dashboardUuid) {
        List<DashboardWidget> widgets = getWidgetsForDashboardUseCase.execute(dashboardUuid);
        return Response.ok(widgets).build();
    }

    @POST
    public Response saveOrUpdateLayout(@PathParam("dashboardUuid") String dashboardUuid,
                                       @Valid List<DashboardWidget> widgets) {
        widgets.forEach(w -> w.setDashboardUuid(dashboardUuid));
        List<DashboardWidget> saved = updateDashboardWidgetLayoutUseCase.execute(widgets);
        return Response.ok(saved).build();
    }

    @DELETE
    public Response deleteWidgets(@PathParam("dashboardUuid") String dashboardUuid) {
        deleteDashboardAndWidgetsUseCase.execute(dashboardUuid);
        return Response.noContent().build();
    }
}
