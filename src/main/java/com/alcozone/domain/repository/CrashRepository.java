package com.alcozone.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.alcozone.domain.model.Crash;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

public interface CrashRepository {
    Crash saveCrash(Crash crash, RevisionEntity revisionEntity);
    List<Crash> findCrashesByRevisionUuid(String revisionUuid);
    List<Crash> findCrashesBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
}
