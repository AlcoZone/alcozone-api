package com.alcozone.application.service;

import com.alcozone.domain.model.Cluster;
import com.alcozone.domain.model.MinifiedCrash;
import com.alcozone.utils.CalculateCentroid;
import com.alcozone.utils.DbscanRunner;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ClusterizeService {

    @Inject DbscanRunner dbscanRunner;

    @ConfigProperty(name = "clusterize.epsilon.meters")
    int epsilonMeters;

    @ConfigProperty(name = "clusterize.points.min")
    int minPoints;

    public List<Cluster> clusterizeRevision(List<MinifiedCrash> crashes){
        Map<Integer, List<MinifiedCrash>> unprocessedClusters = dbscanRunner.generateClusters(crashes, epsilonMeters, minPoints);

        return unprocessedClusters.values().stream()
            .map(cluster -> {
                Double[] centroid = CalculateCentroid.execute(cluster);
                return new Cluster(centroid[0], centroid[1], cluster.size());
            })
        .toList();
    }
}
