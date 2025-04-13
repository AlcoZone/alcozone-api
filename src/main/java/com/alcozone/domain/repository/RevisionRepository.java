package com.alcozone.domain.repository;

import com.alcozone.domain.models.Revision;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

public interface RevisionRepository {
    Revision getRevision(String uuid);
    RevisionEntity getRevisionEntity(String uuid);
    Revision saveRevision(Revision revision);
}
