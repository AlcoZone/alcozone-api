package com.alcozone.application.usecase.revision;

import java.util.List;
import java.util.Map;

import com.alcozone.application.service.PredictionService;
import com.alcozone.domain.models.Roadblock;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.Revision;
import com.alcozone.application.service.RevisionService;

@ApplicationScoped
public class GenerateRoadblockPredictionUseCase {
    //TODO This UseCase and the ClusterizeRevisionUseCase has the same inputs | Check how can be standardized the DTOs for this cases

    @Inject RevisionService revisionService;
    @Inject PredictionService predictionService;

    public Map<String, List<Roadblock>> execute(){
        Revision revision = revisionService.getRevision("ad2ce437-3d9b-4296-a266-530411e1dc83");
        return predictionService.predictRoadblocks(revision.getCrashes(), 450, 3, 0, 23);
    }
}
