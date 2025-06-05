package com.alcozone.infrastructure.persistence.crash;

import java.util.List;
import java.util.ArrayList;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import com.alcozone.domain.models.Crash;
import com.alcozone.domain.repository.CrashRepository;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

@ApplicationScoped
public class CrashRepositoryImpl implements CrashRepository, PanacheRepositoryBase<CrashEntity, Integer> {

    @Override
    @Transactional
    public Crash saveCrash(Crash crash, RevisionEntity revisionEntity){
        CrashEntity crashEntity = CrashMapper.toEntity(crash);
        crashEntity.setRevisionEntity(revisionEntity);
        crashEntity.persist();
        return CrashMapper.toDomain(crashEntity);
    }

    @Override
    public List<Crash> findCrashesByRevisionUuid(String revisionUuid) {
        List<Crash> crashes = new ArrayList<>();
        for (CrashEntity crashEntity : list("revisionEntity.uuid", revisionUuid)) {
            crashes.add(CrashMapper.toDomain(crashEntity));
        }
        return crashes;
    }

    @Override
    public List<Crash> getCrashesForClustering(String revisionUuid) {
        return List.of();
    }

}
