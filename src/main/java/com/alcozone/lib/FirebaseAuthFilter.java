package com.alcozone.lib;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import jakarta.annotation.Priority;

import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
@Blocking
public class FirebaseAuthFilter implements ContainerRequestFilter {

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

            requestContext.setProperty("userUuid", firebaseUid);

        } catch (FirebaseAuthException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid token")
                    .build());
        }
    }
}
