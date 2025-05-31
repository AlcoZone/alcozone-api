package com.alcozone.application.usecase.prediction;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.alcozone.application.service.PredictionService;
import com.alcozone.domain.model.MinifiedRevision;
import com.alcozone.domain.model.Roadblock;
import com.alcozone.infrastructure.dto.prediction.PredictionRequestDTO;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.model.Revision;
import com.alcozone.application.service.RevisionService;

@ApplicationScoped
public class GenerateRoadblockPredictionUseCase {

    @Inject RevisionService revisionService;
    @Inject PredictionService predictionService;

    public Map<String, List<Roadblock>> execute(PredictionRequestDTO requestDTO) {
        MinifiedRevision revision;
        if(Objects.equals(requestDTO.getRevision(), "latest")){
            revision = revisionService.getLatestMinifiedRevision();
        } else {
            revision = revisionService.getMinifiedRevision(requestDTO.getRevision());
        }

        return predictionService.predictRoadblocks(revision.getCrashes(), 450, 3, 0, 23);
    }
}
