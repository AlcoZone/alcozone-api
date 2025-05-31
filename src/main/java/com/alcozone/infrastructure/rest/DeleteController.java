package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.user.DeleteUserUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/users")

public class DeleteController {

    @Inject
    DeleteUserUseCase deleteUserUseCase;

    @DELETE
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        try {
            deleteUserUseCase.execute(id);

            Map<String, String> response = new HashMap<>();
            response.put("message", "User deleted successfully");

            return Response.ok(response).build();

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to delete user: " + e.getMessage());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }
}

