package com.alcozone.infrastructure.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.container.ContainerRequestContext;

@ApplicationScoped
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {
    @GET
    @Path("/login")
    public Response me(@Context ContainerRequestContext ctx) {
        return Response.ok().build();
    }
}