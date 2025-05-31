package com.alcozone.domain.repository;

import com.alcozone.domain.model.MinifiedRevision;
import com.alcozone.domain.model.Revision;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

import java.util.List;

public interface RevisionRepository {
    Revision getRevision(String uuid);
    RevisionEntity getRevisionEntity(String uuid);
    Revision saveRevision(Revision revision);
    List<RevisionEntity> getAllRevisions();
}
