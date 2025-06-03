package com.alcozone.infrastructure.rest;

import com.alcozone.application.service.UserService;
import com.alcozone.application.usecase.register.RegisterUserUseCase;
import com.alcozone.domain.model.User;
import com.alcozone.infrastructure.dto.register.userDTO;
import com.alcozone.infrastructure.dto.login.UserDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;
    @Inject
    RegisterUserUseCase registerUserUseCase;


    @GET
    public Response getUser(@Context ContainerRequestContext requestContext) {
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
        UserDTO dto = new UserDTO(
                user.getUuid(),
                roleId,
                user.getEmail(),
                user.getUsername()
        );

        return Response.ok(dto).build();
    }

    @POST
    @Path("/register")
    public Response register(userDTO UserDTO) {
        try {
            userDTO result = registerUserUseCase.execute(UserDTO);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/all")
    public List<userDTO> getAllUsers() {
        return userService.getAll();
    }

}