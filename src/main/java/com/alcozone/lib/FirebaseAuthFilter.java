package com.alcozone.lib;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import javax.annotation.Priority;


@Provider

@Priority(Priorities.AUTHENTICATION)
public class FirebaseAuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();
        if (path.contains("auth/login")) {
            return;
        }

        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Authorization header must be provided")
                    .build());
            return;
        }
        String token = authHeader.substring("Bearer".length()).trim();
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            requestContext.setProperty("userId", decodedToken.getUid());
            System.out.println(decodedToken.getUid());
        } catch (FirebaseAuthException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid token")
                    .build());
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid firebase token")
                    .build());
        }
    }
}
