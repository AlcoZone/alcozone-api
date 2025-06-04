package com.alcozone.infrastructure.rest;

import com.alcozone.application.dto.dashboard.CreateDashboardDTO;
import com.alcozone.application.dto.dashboard.UpdateDashboardDTO;
import com.alcozone.application.usecase.dashboard.CreateDashboardUseCase;
import com.alcozone.application.usecase.dashboard.DeleteDashboardUseCase;
import com.alcozone.application.usecase.dashboard.GetDashboardsForUserUseCase;
import com.alcozone.application.usecase.dashboard.UpdateDashboardUseCase;
import com.alcozone.domain.models.Dashboard;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/dashboards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DashboardController {

    @Inject CreateDashboardUseCase createDashboardUseCase;
    @Inject UpdateDashboardUseCase updateDashboardUseCase;
    @Inject GetDashboardsForUserUseCase getDashboardsForUserUseCase;
    @Inject DeleteDashboardUseCase deleteDashboardUseCase;

    @POST
    public Response createDashboard(@Valid CreateDashboardDTO dto) {
        Dashboard created = createDashboardUseCase.execute(dto);
        return Response.ok(created).build();
    }

    @PUT
    @Path("/{uuid}")
    public Response updateDashboard(@PathParam("uuid") String uuid, @Valid UpdateDashboardDTO dto) {
        dto.setUuid(uuid);
        Dashboard updated = updateDashboardUseCase.execute(dto);
        return Response.ok(updated).build();
    }

    @GET
    public Response getDashboards(@QueryParam("userUuid") String userUuid) {
        List<Dashboard> dashboards = getDashboardsForUserUseCase.execute(userUuid);
        return Response.ok(dashboards).build();
    }

    @DELETE
    @Path("/{uuid}")
    public Response deleteDashboard(@PathParam("uuid") String uuid) {
        deleteDashboardUseCase.execute(uuid);
        return Response.noContent().build();
    }
}
