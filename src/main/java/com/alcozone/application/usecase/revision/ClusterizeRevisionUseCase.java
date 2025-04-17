package com.alcozone.application.usecase.revision;

import java.util.*;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.Cluster;
import com.alcozone.domain.models.Revision;
import com.alcozone.application.service.RevisionService;
import com.alcozone.infrastructure.dto.revision.request.ClusterizeRevisionRequestDTO;
import com.alcozone.infrastructure.dto.revision.response.ClusterizeRevisionResponseDTO;

@ApplicationScoped
public class ClusterizeRevisionUseCase {

    @Inject RevisionService revisionService;

    public ClusterizeRevisionResponseDTO execute(ClusterizeRevisionRequestDTO dto) {
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
