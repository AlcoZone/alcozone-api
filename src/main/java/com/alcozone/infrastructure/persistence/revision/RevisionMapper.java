package com.alcozone.infrastructure.persistence.revision;

import com.alcozone.domain.models.Revision;

public class RevisionMapper {
    public static Revision toDomain(RevisionEntity revisionEntity) {
        return new Revision(revisionEntity.getId(), revisionEntity.getUuid(), revisionEntity.getName());
    }

    public static RevisionEntity toEntity(Revision revision) {
        return new RevisionEntity(revision.getUuid(), revision.getName());
    }
}
