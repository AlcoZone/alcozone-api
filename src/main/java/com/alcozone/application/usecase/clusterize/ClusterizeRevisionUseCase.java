package com.alcozone.application.usecase.clusterize;

import java.util.*;

import com.alcozone.application.service.ClusterizeService;
import com.alcozone.application.service.MinifiedRevisionService;
import com.alcozone.application.service.RevisionService;
import com.alcozone.application.usecase.date.GetCrashesBetweenDatesUseCase;
import com.alcozone.domain.model.Crash;
import com.alcozone.domain.model.MinifiedCrash;
import com.alcozone.domain.model.MinifiedRevision;
import com.alcozone.infrastructure.persistence.crash.CrashMapper;
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
    @Inject GetCrashesBetweenDatesUseCase getCrashesBetweenDatesUseCase;

    public ClusterizeRevisionResponseDTO execute(ClusterizeRequestDTO dto) {
        List<Cluster> clusters;
        if(dto.getStartDate() == null || dto.getEndDate() == null) {
            String revision_uuid = dto.getUuid();
            if(Objects.equals(dto.getUuid(), "latest")){
                revision_uuid = revisionService.getLatestLightweightRevision().getUuid();
            }

            MinifiedRevision revision = minifiedRevisionService.getMinifiedRevision(revision_uuid);
            clusters = clusterizeService.clusterizeRevision(revision.getCrashes());
        } else {
            List<Crash> crashes = getCrashesBetweenDatesUseCase.execute(dto.getStartDate(), dto.getEndDate());
            List<MinifiedCrash> minifiedCrashes = new ArrayList<>();
            for (Crash crash : crashes) {
                minifiedCrashes.add(CrashMapper.toMinifiedDomain(crash));
            }
            clusters = clusterizeService.clusterizeRevision(minifiedCrashes);
        }

        ClusterizeRevisionResponseDTO responseDTO = new ClusterizeRevisionResponseDTO();
        responseDTO.setCount(clusters.size());
        responseDTO.setClusters(clusters);
        return responseDTO;
    }
}
