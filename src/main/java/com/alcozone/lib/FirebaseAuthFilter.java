package com.alcozone.lib;

import com.alcozone.application.service.UserService;
import com.alcozone.domain.model.Role;
import com.alcozone.domain.model.User;
import com.alcozone.domain.repository.RoleRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import jakarta.annotation.Priority;
import com.alcozone.domain.model.RoleType;

import io.smallrye.common.annotation.Blocking;

import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
@Blocking
public class FirebaseAuthFilter implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Inject
    RoleRepository roleRepository;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Authorization header must be provided")
                    .build());
            return;
        }

        String token = authHeader.substring("Bearer".length()).trim();
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token, true);
            String firebaseUid = decodedToken.getUid();

            var user = userService.findByFirebaseUidRaw(firebaseUid);
            if (user == null) {
                System.out.println("[DEBUG] UID: " + firebaseUid);
                System.out.println("[DEBUG] Email desde token: " + decodedToken.getEmail());
                User newUser = new User();
                newUser.setUuid(firebaseUid);
                newUser.setEmail(decodedToken.getEmail());
                newUser.setUsername(decodedToken.getName());

                Role datavisualizerRole = roleRepository.findRoleById(RoleType.DATA_VISUALIZER.getId());
                if (datavisualizerRole == null) {
                    throw new IllegalStateException("No existe el rol datavisualizer (id=3)");
                }
                newUser.setRole(datavisualizerRole);
                System.out.println("[DEBUG] New user: " + newUser);
                userService.createUser(newUser);
            }
            String path = requestContext.getUriInfo().getPath();
            if (path.startsWith("/user/register")) {
                if (user.getRole().getId() != 1) {
                    requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                            .entity("Access denied: only ADMINISTRATOR can access this endpoint")
                            .build());
                    return;
                }
            }
            requestContext.setProperty("userUuid", firebaseUid);

        } catch (FirebaseAuthException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid token")
                    .build());
        }
    }
}