package com.alcozone.infrastructure.persistence.crash;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import com.alcozone.domain.model.Crash;
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
        for (CrashEntity crashEntity : list("revisionEntity.revision", revisionUuid)) {
            crashes.add(CrashMapper.toDomain(crashEntity));
        }
        return crashes;
    }
  
    @Override
    public List<Crash> findCrashesBetweenDates(LocalDateTime start, LocalDateTime end){
        List<Crash> crashes = new ArrayList<>();
        DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String startStr = start.format(dbFormatter);
        String endStr = end.format(dbFormatter);

        for (CrashEntity entity : list("datetime BETWEEN ?1 AND ?2", startStr, endStr)) {
            crashes.add(CrashMapper.toDomain(entity));
        }
        return crashes;
    }

    @Override
    public List<String> getAvailableDates() {
        return getEntityManager()
        .createNativeQuery("SELECT DISTINCT SUBSTRING(datetime, 1, 10) FROM Crashes")
        .getResultList();
    }
}
