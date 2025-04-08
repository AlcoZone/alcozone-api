package com.alcozone.infrastructure.persistence.accident;

import com.alcozone.domain.classes.Accident;
import com.alcozone.domain.repository.AccidentRepository;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AccidentRepositoryImpl implements AccidentRepository, PanacheRepositoryBase<AccidentEntity, Integer> {

    @Override
    @Transactional
    public void saveAccident(Accident accident, RevisionEntity revisionEntity){
        AccidentEntity accidentEntity = AccidentMapper.toEntity(accident);
        accidentEntity.setRevisionEntity(revisionEntity);
        persist(accidentEntity);
    }

    @Override
    public List<Accident> findByRevisionUuid(String revisionUuid) {
        List<AccidentEntity> accidentsEntity = list("revisionEntity.uuid", revisionUuid);
        List<Accident> accidents = new ArrayList<>();
        for (AccidentEntity accidentEntity : accidentsEntity) {
            accidents.add(AccidentMapper.toDomain(accidentEntity));
        }
        return accidents;
    }

}
