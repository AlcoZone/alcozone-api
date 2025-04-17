package com.alcozone.application.usecase.revision;

import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.GeoPoint;
import com.alcozone.domain.models.Roadblock;
import com.alcozone.application.service.RevisionService;
import com.alcozone.application.usecase.crash.GenerateGeoPointsUseCase;

@ApplicationScoped
public class GenerateRoadblockPredictionUseCase {

    @Inject RevisionService revisionService;
    @Inject GenerateGeoPointsUseCase generateGeoPointsUseCase;

    public Map<String, List<Roadblock>> execute(){
        List<GeoPoint> geoPoints = generateGeoPointsUseCase.execute("4f2334a4-8255-4188-8c38-97598cf82881");
        return revisionService.predictRoadblocks(geoPoints, 100, 3, 0, 23);
    }
}
