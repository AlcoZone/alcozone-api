package com.alcozone.application.usecase.revision;

import java.util.*;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.Cluster;
import com.alcozone.application.service.RevisionService;
import com.alcozone.application.usecase.crash.GenerateGeoPointsUseCase;
import com.alcozone.infrastructure.dto.revision.response.ClusterizeRevisionResponseDTO;
import com.alcozone.infrastructure.dto.revision.request.ClusterizeRevisionRequestDTO;

@ApplicationScoped
public class ClusterizeRevisionUseCase {

    @Inject RevisionService revisionService;
    @Inject GenerateGeoPointsUseCase generateGeoPointsUseCase;

    public ClusterizeRevisionResponseDTO execute(ClusterizeRevisionRequestDTO dto) {
        List<Cluster> clusters = revisionService.clusterizeRevision(
                generateGeoPointsUseCase.execute(dto.getUuid()),
                dto.getEpsilonMeters(),
                dto.getMinCrashes()
        );

        ClusterizeRevisionResponseDTO responseDTO = new ClusterizeRevisionResponseDTO();
        responseDTO.setCount(clusters.size());
        responseDTO.setClusters(clusters);
        return responseDTO;
    }
}
