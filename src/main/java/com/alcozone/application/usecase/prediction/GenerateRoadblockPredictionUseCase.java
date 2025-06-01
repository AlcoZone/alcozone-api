package com.alcozone.application.usecase.prediction;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.alcozone.application.service.MinifiedRevisionService;
import com.alcozone.application.service.PredictionService;
import com.alcozone.domain.model.MinifiedRevision;
import com.alcozone.domain.model.Roadblock;
import com.alcozone.infrastructure.dto.prediction.PredictionRequestDTO;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.application.service.RevisionService;

@ApplicationScoped
public class GenerateRoadblockPredictionUseCase {

    @Inject RevisionService revisionService;
    @Inject PredictionService predictionService;
    @Inject MinifiedRevisionService minifiedRevisionService;

    public Map<String, List<Roadblock>> execute(PredictionRequestDTO requestDTO) {
        String revision_uuid = requestDTO.getRevision();
        if(Objects.equals(requestDTO.getRevision(), "latest")) {
            revision_uuid = revisionService.getLatestLightweightRevision().getUuid();
        }

        MinifiedRevision revision = minifiedRevisionService.getMinifiedRevision(revision_uuid);
        return predictionService.predictRoadblocks(revision.getCrashes());
    }
}
