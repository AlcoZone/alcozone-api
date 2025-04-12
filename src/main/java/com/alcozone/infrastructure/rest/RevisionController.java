package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.revision.CreateRevisionUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestForm;

import java.io.IOException;
import java.io.InputStream;

@Path("/revision")
@Produces(MediaType.APPLICATION_JSON)
public class RevisionController {

    @Inject
    CreateRevisionUseCase createRevisionUseCase;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response saveRevision(@RestForm("name") String revisionName, @RestForm("file") InputStream csvFile) throws IOException {
        return Response.ok(createRevisionUseCase.execute(revisionName, csvFile)).build();
    }
}
