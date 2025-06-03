package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.user.EmailExistUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublicController {
    @Inject
    EmailExistUseCase emailExistUseCase;

    @GET
    @Path("/email-exists")
    public Response checkIfEmailExists(@QueryParam("email") String email) {
        if (email == null || email.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El correo es requerido").build();
        }

        boolean exists = emailExistUseCase.emailExists(email.trim());
        return Response.ok().entity(exists).build();
    }
}