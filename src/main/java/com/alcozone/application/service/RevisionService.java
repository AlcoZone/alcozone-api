package com.alcozone.application.service;

import java.util.*;

import com.alcozone.domain.model.*;
import com.alcozone.domain.repository.MinifiedRevisionRepository;
import com.alcozone.infrastructure.dto.revision.response.RevisionListItemDTO;
import com.alcozone.infrastructure.persistence.revision.RevisionMapper;
import com.alcozone.utils.DbscanRunner;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.repository.RevisionRepository;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

@ApplicationScoped
public class RevisionService {

    @Inject RevisionRepository revisionRepository;
    @Inject MinifiedRevisionRepository minifiedRevisionRepository;

    private Double[] calculateCentroid(List<Crash> crashes) {
        double averageLatitude = crashes.stream().mapToDouble(Crash::getLatitude).average().orElse(0);
        double averageLongitude = crashes.stream().mapToDouble(Crash::getLongitude).average().orElse(0);
        return new Double[]{averageLatitude, averageLongitude};
    }

    public Revision getRevision(String uuid) {
        return revisionRepository.getRevision(uuid);
    }

    public  MinifiedRevision getMinifiedRevision(String uuid) {
        return minifiedRevisionRepository.getMinifiedRevision(uuid);
    }

    public MinifiedRevision getLatestMinifiedRevision() {
        return minifiedRevisionRepository.getLatestMinifiedRevision();
    }

    public RevisionEntity getRevisionEntity(String uuid) {
        return revisionRepository.getRevisionEntity(uuid);
    }

    public Revision saveRevision(String name){
        Revision revision = new Revision();
        revision.setUuid(UUID.randomUUID().toString());
        revision.setName(name);
        return revisionRepository.saveRevision(revision);
    }

    public List<RevisionListItemDTO> getAllRevisions() {
        return RevisionMapper.toListItemDTOList(revisionRepository.getAllRevisions());
    }

    public List<Cluster> clusterizeRevision(List<Crash> crashes, double epsilonMeters, int minPoints){
        //Map<Integer, List<Crash>> unprocessedClusters = DbscanRunner.generateClusters(crashes, epsilonMeters, minPoints);
        Map<Integer, List<Crash>> unprocessedClusters = new HashMap<>();

        return unprocessedClusters.values().stream()
            .map(cluster -> {
                Double[] centroid = calculateCentroid(cluster);
                return new Cluster(centroid[0], centroid[1], cluster.size());
            })
        .toList();
    }
}