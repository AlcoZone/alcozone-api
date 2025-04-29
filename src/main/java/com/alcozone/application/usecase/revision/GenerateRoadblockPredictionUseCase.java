package com.alcozone.application.usecase.revision;

import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.Revision;
import com.alcozone.domain.models.Cluster;
import com.alcozone.application.service.RevisionService;

@ApplicationScoped
public class GenerateRoadblockPredictionUseCase {
    //TODO This UseCase and the ClusterizeRevisionUseCase has the same inputs | Check how can be standardized the DTOs for this cases

    @Inject RevisionService revisionService;

    public Map<String, List<Cluster>> execute(){
        Revision revision = revisionService.getRevision("3e4c8076-3626-406f-8dd2-72d7e1103118");
        return revisionService.predictRoadblocks(revision.getCrashes(), 100, 3, 0, 23);
    }
}
