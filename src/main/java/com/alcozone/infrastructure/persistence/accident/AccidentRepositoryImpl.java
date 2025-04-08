package com.alcozone.infrastructure.persistence.accident;

import com.alcozone.domain.classes.Accident;
import com.alcozone.domain.repository.AccidentRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AccidentRepositoryImpl implements AccidentRepository, PanacheRepositoryBase<AccidentEntity, Integer> {

    @Override
    @Transactional
    public void saveAccident(Accident accident){
        AccidentEntity accidentEntity = AccidentMapper.toEntity(accident);
        persist(accidentEntity);
    }

}
