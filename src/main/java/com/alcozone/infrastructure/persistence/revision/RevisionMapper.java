package com.alcozone.infrastructure.persistence.revision;

import com.alcozone.domain.classes.Revision;

public class RevisionMapper {
    public static Revision toDomain(RevisionEntity revisionEntity) {
        return new Revision(revisionEntity.getUuid(), revisionEntity.getName(), revisionEntity.getStatus());
    }

    public static RevisionEntity toEntity(Revision revision) {
        return new RevisionEntity(revision.getUuid(), revision.getName(), revision.getStatus());
    }
}
