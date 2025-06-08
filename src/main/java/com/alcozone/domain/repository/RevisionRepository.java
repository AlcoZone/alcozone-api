package com.alcozone.domain.repository;

import com.alcozone.domain.model.Revision;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import com.alcozone.infrastructure.persistence.revision.RevisionListEntity;
import java.util.List;

public interface RevisionRepository {
    Revision getRevision(String uuid);
    RevisionEntity getRevisionEntity(String uuid);
    Revision saveRevision(Revision revision);
    List<RevisionListEntity> getLightweightRevisions();
    Revision deleteRevision(String uuid);
    RevisionListEntity getLatestLightweightRevision();
}
