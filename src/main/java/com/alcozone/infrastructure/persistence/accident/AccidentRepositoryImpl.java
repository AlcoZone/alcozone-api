package com.alcozone.infrastructure.persistence.accident;

import java.util.List;
import java.util.ArrayList;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import com.alcozone.domain.models.Accident;
import com.alcozone.domain.repository.AccidentRepository;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

@ApplicationScoped
public class AccidentRepositoryImpl implements AccidentRepository, PanacheRepositoryBase<AccidentEntity, Integer> {

    @Override
    @Transactional
    public Accident saveAccident(Accident accident, RevisionEntity revisionEntity){
        AccidentEntity accidentEntity = AccidentMapper.toEntity(accident);
        accidentEntity.setRevisionEntity(revisionEntity);
        accidentEntity.persist();
        return AccidentMapper.toDomain(accidentEntity);
    }

    @Override
    public List<Accident> findAccidentsByRevisionUuid(String revisionUuid) {
        List<Accident> accidents = new ArrayList<>();
        for (AccidentEntity accidentEntity : list("revisionEntity.uuid", revisionUuid)) {
            accidents.add(AccidentMapper.toDomain(accidentEntity));
        }
        return accidents;
    }
}
