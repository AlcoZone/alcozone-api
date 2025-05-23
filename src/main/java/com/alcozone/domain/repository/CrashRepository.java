package com.alcozone.domain.repository;

import java.util.List;

import com.alcozone.domain.models.Crash;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

public interface CrashRepository {
    Crash saveCrash(Crash crash, RevisionEntity revisionEntity);
    List<Crash> findCrashesByRevisionUuid(String revisionUuid);
    List<Crash> getCrashesForClustering(String revisionUuid);
    List<Crash> findCrashesByTown(String town);
}
