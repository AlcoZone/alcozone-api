package com.alcozone.domain.repository;

import com.alcozone.domain.models.Accident;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

import java.util.List;

public interface AccidentRepository {
    void saveAccident(Accident accident, RevisionEntity revisionEntity);
    List<Accident> findByRevisionUuid(String revisionUuid);
}
