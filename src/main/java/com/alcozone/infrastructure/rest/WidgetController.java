package com.alcozone.infrastructure.rest;

import com.alcozone.domain.models.Widget;
import com.alcozone.application.usecase.widget.GetAllWidgetDefinitionsUseCase;
import com.alcozone.application.usecase.widget.SaveWidgetUseCase;
import com.alcozone.application.usecase.widget.DeleteWidgetUseCase;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
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
}
