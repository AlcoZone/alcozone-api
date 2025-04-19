package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.LoginUserUseCase;
import com.alcozone.infrastructure.dto.firebase.FirebaseLoginDTO;
import com.alcozone.infrastructure.dto.user.UserDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class LoginController {
    @Inject
    LoginUserUseCase loginUserUseCase;

    @POST
    @Path("/login")
    public Response login(FirebaseLoginDTO dto){
        try{
            UserDTO user = loginUserUseCase.execute(dto.getIdToken());
            return Response.ok(user).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Login failded: " + e.getMessage())
                    .build();
        }
    }
}
