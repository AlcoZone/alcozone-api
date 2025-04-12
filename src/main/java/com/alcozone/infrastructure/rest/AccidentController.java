package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.accident.GetAccidentsByRevisionUuidUseCase;
import com.alcozone.domain.models.Accident;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/accidents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccidentController {

    @Inject
    GetAccidentsByRevisionUuidUseCase getAccidentsByRevisionUuidUseCase;

    //TODO Fix Empty array being returned
    @GET
    public List<Accident> getAccidentsByRevisionUuid(String revisionUuid){
        return getAccidentsByRevisionUuidUseCase.execute(revisionUuid);
    }
}
