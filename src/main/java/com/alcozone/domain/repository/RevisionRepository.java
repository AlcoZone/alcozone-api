package com.alcozone.domain.repository;

import com.alcozone.domain.classes.Revision;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

public interface RevisionRepository {
    RevisionEntity saveRevision(Revision revision);
}
