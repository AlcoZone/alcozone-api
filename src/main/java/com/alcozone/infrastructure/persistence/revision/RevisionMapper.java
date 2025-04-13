package com.alcozone.infrastructure.persistence.revision;

import java.util.List;
import java.util.ArrayList;

import com.alcozone.domain.models.Accident;
import com.alcozone.domain.models.Revision;
import com.alcozone.infrastructure.persistence.accident.AccidentEntity;
import com.alcozone.infrastructure.persistence.accident.AccidentMapper;

public class RevisionMapper {
    public static Revision toDomain(RevisionEntity revisionEntity) {
        Revision revision = new Revision();
        revision.setId(revisionEntity.getId());
        revision.setUuid(revisionEntity.getUuid());
        revision.setName(revisionEntity.getName());
        if (revisionEntity.getAccidents() != null) {
            List<Accident> accidents = new ArrayList<>();
            for (AccidentEntity accidentEntity : revisionEntity.getAccidents()) {
                accidents.add(AccidentMapper.toDomain(accidentEntity));
            }
            revision.setAccidents(accidents);
        }

        return revision;
    }

    public static RevisionEntity toEntity(Revision revision) {
        RevisionEntity revisionEntity = new RevisionEntity();
        revisionEntity.setId(revision.getId());
        revisionEntity.setUuid(revision.getUuid());
        revisionEntity.setName(revision.getName());
        if(revision.getAccidents() != null) {
            List<AccidentEntity> accidentEntities = new ArrayList<>();
            for (Accident accident : revision.getAccidents()) {
                accidentEntities.add(AccidentMapper.toEntity(accident));
            }
            revisionEntity.setAccidents(accidentEntities);
        }

        return revisionEntity;
    }
}
