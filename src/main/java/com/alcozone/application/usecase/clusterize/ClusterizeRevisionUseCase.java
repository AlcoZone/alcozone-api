package com.alcozone.application.usecase.clusterize;

import java.util.*;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.model.Cluster;
import com.alcozone.domain.model.Revision;
import com.alcozone.application.service.RevisionService;
import com.alcozone.infrastructure.dto.clusterize.ClusterizeRequestDTO;
import com.alcozone.infrastructure.dto.revision.response.ClusterizeRevisionResponseDTO;

@ApplicationScoped
public class ClusterizeRevisionUseCase {

    @Inject RevisionService revisionService;

    public ClusterizeRevisionResponseDTO execute(ClusterizeRequestDTO dto) {
        Revision revision = revisionService.getRevision(dto.getUuid());
        List<Cluster> clusters = revisionService.clusterizeRevision(
                revision.getCrashes(),
                dto.getEpsilonMeters(),
                dto.getMinCrashes()
        );

        ClusterizeRevisionResponseDTO responseDTO = new ClusterizeRevisionResponseDTO();
        responseDTO.setCount(clusters.size());
        responseDTO.setClusters(clusters);
        return responseDTO;
    }
}
