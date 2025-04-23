package com.alcozone.infrastructure.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@ApplicationScoped
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {

    @GET
    @Path("/check")
    public Response check() {
        return Response.ok("Usuario autenticado correctamente").build();
    }
}

