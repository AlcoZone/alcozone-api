package com.alcozone.infrastructure.rest;

import com.alcozone.application.service.UserService;
import com.alcozone.domain.model.User;
import com.alcozone.infrastructure.dto.login.UserDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.*;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    public Response getUser(@Context ContainerRequestContext requestContext) {
        // Recupera el uuid que puso el filtro en la request
        String uuid = (String) requestContext.getProperty("userUuid");
        if (uuid == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("No userUuid found in request context")
                    .build();
        }

        User user = userService.findByFirebaseUidRaw(uuid);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found")
                    .build();
        }

        Integer roleId = user.getRole() != null ? user.getRole().getId() : null;
        UserDTO dto = new UserDTO(user.getUuid(), roleId);

        return Response.ok(dto).build();
    }
}
