package com.alcozone.application.usecase.clusterize;

import java.util.*;

import com.alcozone.application.service.ClusterizeService;
import com.alcozone.application.service.MinifiedRevisionService;
import com.alcozone.application.service.RevisionService;
import com.alcozone.domain.model.MinifiedRevision;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.model.Cluster;
import com.alcozone.infrastructure.dto.clusterize.ClusterizeRequestDTO;
import com.alcozone.infrastructure.dto.revision.response.ClusterizeRevisionResponseDTO;

@ApplicationScoped
public class ClusterizeRevisionUseCase {

    @Inject MinifiedRevisionService minifiedRevisionService;
    @Inject ClusterizeService clusterizeService;
    @Inject RevisionService revisionService;

    public ClusterizeRevisionResponseDTO execute(ClusterizeRequestDTO dto) {
        String revision_uuid = dto.getUuid();
        if(Objects.equals(dto.getUuid(), "latest")){
            revision_uuid = revisionService.getLatestLightweightRevision().getUuid();
        }

        MinifiedRevision revision = minifiedRevisionService.getMinifiedRevision(revision_uuid);
        List<Cluster> clusters = clusterizeService.clusterizeRevision(
                revision.getCrashes()
        );

        ClusterizeRevisionResponseDTO responseDTO = new ClusterizeRevisionResponseDTO();
        responseDTO.setCount(clusters.size());
        responseDTO.setClusters(clusters);
        return responseDTO;
    }
}
