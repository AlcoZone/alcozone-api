package com.alcozone.infrastructure.rest;

import com.alcozone.application.usecase.prediction.GenerateRoadblockPredictionUseCase;
import com.alcozone.infrastructure.dto.prediction.PredictionRequestDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/predict")
@Produces(MediaType.APPLICATION_JSON)
public class PredictionController {

    @Inject GenerateRoadblockPredictionUseCase generateRoadblockPredictionUseCase;

    @GET
    public Response getPredictions(@BeanParam PredictionRequestDTO requestDTO) {
        return Response.ok(generateRoadblockPredictionUseCase.execute(requestDTO)).build();
    }
}
