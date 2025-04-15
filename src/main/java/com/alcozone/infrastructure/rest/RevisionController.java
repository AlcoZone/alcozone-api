package com.alcozone.infrastructure.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alcozone.application.service.AccidentService;
import com.alcozone.application.service.CrashClusteringService;
import com.alcozone.application.usecase.revision.ClusterizeRevisionUseCase;
import com.alcozone.domain.models.Accident;
import com.alcozone.domain.models.GeoPoint;
import com.alcozone.infrastructure.dto.revision.request.ClusterizeRevisionRequestDTO;
import com.alcozone.infrastructure.dto.revision.request.CreateRevisionRequestDTO;

import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.alcozone.application.usecase.revision.GetRevisionUseCase;
import com.alcozone.infrastructure.dto.revision.request.GetRevisionRequestDTO;
import com.alcozone.application.usecase.revision.CreateRevisionUseCase;

@Path("/revision")
@Produces(MediaType.APPLICATION_JSON)
public class RevisionController {

    @Inject CreateRevisionUseCase createRevisionUseCase;
    @Inject GetRevisionUseCase getRevisionUseCase;
    @Inject ClusterizeRevisionUseCase clusterizeRevisionUseCase;

    //TODO Delete accidentService & ClusteringService
    @Inject AccidentService accidentService;
    @Inject CrashClusteringService crashClusteringService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response saveRevision(@BeanParam CreateRevisionRequestDTO requestDTO) throws IOException {
        return Response.ok(createRevisionUseCase.execute(requestDTO)).build();
    }

    @GET
    public Response getRevision(@BeanParam GetRevisionRequestDTO requestDTO) {
        return Response.ok(getRevisionUseCase.execute(requestDTO)).build();
    }

    @GET
    @Path("/clusterize")
    public Response clusterize(@BeanParam ClusterizeRevisionRequestDTO requestDTO) {
        return Response.ok(clusterizeRevisionUseCase.execute(requestDTO)).build();
    }

    @GET
    @Path("/predict")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPredictions() {
        List<Accident> accidents = accidentService.getAccidentsByRevisionUuid("49d5c049-b580-4cff-8767-e2e48c865843");
        List<GeoPoint> geoPoints = new ArrayList<>();
        for (Accident accident : accidents) {
            geoPoints.add(new GeoPoint(accident.getLatitude(), accident.getLongitude(), accident.getDate() + " " + accident.getHour()));
        }

        return Response.ok(crashClusteringService.predictHotspotSummaries(geoPoints, 100, 3, 0, 23)).build();
    }
}
