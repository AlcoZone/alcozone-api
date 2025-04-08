package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.revision.SaveRevisionUseCase;
import com.alcozone.domain.Revision;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestForm;

import java.io.IOException;
import java.io.InputStream;

@Path("/revision")
@Produces(MediaType.APPLICATION_JSON)
public class RevisionController {

    @Inject
    SaveRevisionUseCase saveRevisionUseCase;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void saveRevision(@RestForm("name") String revisionName, @RestForm("file") InputStream csvFile) throws IOException {
        Revision revision = saveRevisionUseCase.execute(revisionName);
    }
}
