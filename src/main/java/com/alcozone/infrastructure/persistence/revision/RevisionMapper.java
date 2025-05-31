package com.alcozone.infrastructure.persistence.revision;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.alcozone.domain.model.Crash;
import com.alcozone.domain.model.MinifiedCrash;
import com.alcozone.domain.model.MinifiedRevision;
import com.alcozone.domain.model.Revision;
import com.alcozone.infrastructure.dto.revision.response.RevisionListItemDTO;
import com.alcozone.infrastructure.persistence.crash.CrashEntity;
import com.alcozone.infrastructure.persistence.crash.CrashMapper;
import com.alcozone.infrastructure.persistence.minifiedCrash.MinifiedCrashEntity;
import com.alcozone.infrastructure.persistence.minifiedRevision.MinifiedRevisionEntity;

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

        public static MinifiedRevision toMinifiedDomain(MinifiedRevisionEntity minifiedRevision) {
        MinifiedRevision revision = new MinifiedRevision();
        if (minifiedRevision.getCrashes() != null) {
            List<MinifiedCrash> crashes = new ArrayList<>();
            for (MinifiedCrashEntity crashEntity : minifiedRevision.getCrashes()) {
                crashes.add(CrashMapper.toMinifiedDomain(crashEntity));
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

    public static RevisionListItemDTO toListItemDTO(RevisionEntity entity) {
        return new RevisionListItemDTO(
                entity.getUuid(),
                entity.getName(),
                entity.getCrashes() != null ? entity.getCrashes().size() : 0,
                entity.getCreated_at().toLocalDate().toString()
        );
    }

    public static List<RevisionListItemDTO> toListItemDTOList(List<RevisionEntity> entities) {
        return entities.stream()
                .map(RevisionMapper::toListItemDTO)
                .collect(Collectors.toList());
    }
}