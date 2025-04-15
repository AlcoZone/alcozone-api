package com.alcozone.application.usecase.revision;

import java.util.*;

import com.alcozone.infrastructure.dto.revision.response.ClusterizeRevisionResponseDTO;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.GeoPoint;
import com.alcozone.domain.models.Cluster;
import com.alcozone.application.service.RevisionService;
import com.alcozone.application.usecase.accident.GenerateGeoPointsUseCase;
import com.alcozone.infrastructure.dto.revision.request.ClusterizeRevisionRequestDTO;

@ApplicationScoped
public class ClusterizeRevisionUseCase {

    @Inject RevisionService revisionService;
    @Inject GenerateGeoPointsUseCase generateGeoPointsUseCase;

    public ClusterizeRevisionResponseDTO execute(ClusterizeRevisionRequestDTO dto) {
        Map<Integer, List<GeoPoint>> geoPoints = revisionService.clusterizeRevision(
                generateGeoPointsUseCase.execute(dto.getUuid()),
                dto.getEpsilonMeters(),
                dto.getMinAccidents()
        );

        List<Cluster> clusters = geoPoints.entrySet().stream()
            .map(entry -> new Cluster(entry.getKey(), entry.getValue()))
            .toList();

        ClusterizeRevisionResponseDTO responseDTO = new ClusterizeRevisionResponseDTO();
        responseDTO.setCount(clusters.size());
        responseDTO.setClusters(clusters);
        return responseDTO;
    }
}
