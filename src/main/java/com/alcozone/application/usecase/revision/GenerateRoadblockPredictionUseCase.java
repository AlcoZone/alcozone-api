package com.alcozone.application.usecase.revision;

import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.Revision;
import com.alcozone.domain.models.Roadblock;
import com.alcozone.application.service.RevisionService;

@ApplicationScoped
public class GenerateRoadblockPredictionUseCase {

    @Inject RevisionService revisionService;

    public Map<String, List<Roadblock>> execute(){
        Revision revision = revisionService.getRevision("ad2ce437-3d9b-4296-a266-530411e1dc83");
        return revisionService.predictRoadblocks(revision.getCrashes(), 100, 3, 0, 23);
    }
}
