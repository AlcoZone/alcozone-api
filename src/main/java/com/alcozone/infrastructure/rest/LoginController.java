package com.alcozone.infrastructure.rest;

import com.alcozone.application.service.UserService;
import com.alcozone.infrastructure.dto.user.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.container.ContainerRequestContext;

@ApplicationScoped
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {

    @Inject
    UserService userService;

    @GET
    @Path("/login")
    public Response login(@Context ContainerRequestContext ctx) {
        String uuid = (String) ctx.getProperty("userUuid");
        UserDTO user = userService.findUserByFirebaseUid(uuid);
        return Response.ok(user).build();
    }
}
