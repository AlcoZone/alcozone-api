package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.accident.GetAccidentsByRevisionUuidUseCase;
import com.alcozone.application.usecase.accident.SaveAccidentsUseCase;
import com.alcozone.application.usecase.revision.SaveRevisionUseCase;
import com.alcozone.domain.classes.Accident;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestForm;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Path("/revision")
@Produces(MediaType.APPLICATION_JSON)
public class RevisionController {

    @Inject
    SaveRevisionUseCase saveRevisionUseCase;

    @Inject
    SaveAccidentsUseCase saveAccidentsUseCase;

    @Inject
    GetAccidentsByRevisionUuidUseCase getAccidentsByRevisionUuidUseCase;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public RevisionEntity saveRevision(@RestForm("name") String revisionName, @RestForm("file") InputStream csvFile) throws IOException {
        RevisionEntity revisionEntity = saveRevisionUseCase.execute(revisionName);
        saveAccidentsUseCase.execute(revisionEntity, csvFile);
        return revisionEntity;
    }

    @GET
    public List<Accident> getAccidentsByRevisionUuid(String revisionUuid){
        return getAccidentsByRevisionUuidUseCase.execute(revisionUuid);
    }
}
