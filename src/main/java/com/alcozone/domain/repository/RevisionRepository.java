package com.alcozone.domain.repository;

import com.alcozone.domain.models.Revision;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

public interface RevisionRepository {
    RevisionEntity saveRevision(Revision revision);
}
