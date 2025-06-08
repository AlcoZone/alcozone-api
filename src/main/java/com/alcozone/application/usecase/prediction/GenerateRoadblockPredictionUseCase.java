package com.alcozone.application.usecase.prediction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.alcozone.application.service.MinifiedRevisionService;
import com.alcozone.application.service.PredictionService;
import com.alcozone.application.usecase.date.GetCrashesBetweenDatesUseCase;
import com.alcozone.domain.model.Crash;
import com.alcozone.domain.model.MinifiedCrash;
import com.alcozone.domain.model.MinifiedRevision;
import com.alcozone.domain.model.Roadblock;
import com.alcozone.infrastructure.dto.prediction.PredictionRequestDTO;
import com.alcozone.infrastructure.persistence.crash.CrashMapper;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.application.service.RevisionService;

@ApplicationScoped
public class GenerateRoadblockPredictionUseCase {

    @Inject RevisionService revisionService;
    @Inject PredictionService predictionService;
    @Inject MinifiedRevisionService minifiedRevisionService;
    @Inject GetCrashesBetweenDatesUseCase getCrashesBetweenDatesUseCase;

    public Map<String, List<Roadblock>> execute(PredictionRequestDTO requestDTO) {

        if(requestDTO.getStartDate() == null || requestDTO.getEndDate() == null) {
            String revision_uuid = requestDTO.getRevision();
            if(Objects.equals(requestDTO.getRevision(), "latest")) {
                revision_uuid = revisionService.getLatestLightweightRevision().getUuid();
            }

            MinifiedRevision revision = minifiedRevisionService.getMinifiedRevision(revision_uuid);
            return predictionService.predictRoadblocks(revision.getCrashes());
        } else {
            List<Crash> crashes = getCrashesBetweenDatesUseCase.execute(requestDTO.getStartDate(), requestDTO.getEndDate());
            List<MinifiedCrash> minifiedCrashes = new ArrayList<>();
            for (Crash crash : crashes) {
                minifiedCrashes.add(CrashMapper.toMinifiedDomain(crash));
            }
            return predictionService.predictRoadblocks(minifiedCrashes);
        }

    }
}
