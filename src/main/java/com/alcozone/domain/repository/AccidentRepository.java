package com.alcozone.domain.repository;

import java.util.List;

import com.alcozone.domain.models.Accident;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

public interface AccidentRepository {
    Accident saveAccident(Accident accident, RevisionEntity revisionEntity);
    List<Accident> findAccidentsByRevisionUuid(String revisionUuid);
}
