package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.user.UpdateUserUseCase;
import com.alcozone.infrastructure.dto.user.UpdateDisplayNameDTO;
import com.alcozone.infrastructure.dto.user.UpdatePasswordDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UpdateController {

    @Inject
    UpdateUserUseCase updateUserUseCase;

    @PUT
    @Path("/password")
    public Response updatePassword(@Context ContainerRequestContext containerRequestContext, UpdatePasswordDTO updateUserDTO) {
        try {
            updateUserUseCase.updatePassword(containerRequestContext.getProperty("userUuid").toString(), updateUserDTO.getPassword());
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating password: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/display-name")
    public Response updateDisplayName(@Context ContainerRequestContext containerRequestContext, UpdateDisplayNameDTO updateUserDTO) {
        try {
            updateUserUseCase.updateDisplayName(containerRequestContext.getProperty("userUuid").toString(), updateUserDTO.getDisplayName());
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating display name: " + e.getMessage()).build();
        }
    }
}