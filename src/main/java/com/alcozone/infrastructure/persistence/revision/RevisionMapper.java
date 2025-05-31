package com.alcozone.infrastructure.persistence.revision;

import java.util.List;
import java.util.ArrayList;

import com.alcozone.domain.models.Crash;
import com.alcozone.domain.models.Revision;
import com.alcozone.infrastructure.dto.revision.response.RevisionListItemDTO;
import com.alcozone.infrastructure.persistence.crash.CrashEntity;
import com.alcozone.infrastructure.persistence.crash.CrashMapper;

public class RevisionMapper {
    public static Revision toDomain(RevisionEntity revisionEntity) {
        Revision revision = new Revision();
        revision.setId(revisionEntity.getId());
        revision.setUuid(revisionEntity.getUuid());
        revision.setName(revisionEntity.getName());
        if (revisionEntity.getCrashes() != null) {
            List<Crash> crashes = new ArrayList<>();
            for (CrashEntity crashEntity : revisionEntity.getCrashes()) {
                crashes.add(CrashMapper.toDomain(crashEntity));
            }
            revision.setCrashes(crashes);
        }

        return revision;
    }

    public static RevisionEntity toEntity(Revision revision) {
        RevisionEntity revisionEntity = new RevisionEntity();
        revisionEntity.setId(revision.getId());
        revisionEntity.setUuid(revision.getUuid());
        revisionEntity.setName(revision.getName());
        if(revision.getCrashes() != null) {
            List<CrashEntity> crashEntities = new ArrayList<>();
            for (Crash crash : revision.getCrashes()) {
                crashEntities.add(CrashMapper.toEntity(crash));
            }
            revisionEntity.setCrashes(crashEntities);
        }
        return revisionEntity;
    }

    public static RevisionListItemDTO toListItemDTO(RevisionListEntity entity) {
        RevisionListItemDTO dto = new RevisionListItemDTO();
        dto.setUuid(entity.getUuid());
        dto.setName(entity.getName());
        dto.setDataQuantity(entity.getDataQuantity());
        dto.setDate(entity.getCreated_at().toLocalDate().toString());
        return dto;
    }
}