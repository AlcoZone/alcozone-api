package com.alcozone.infrastructure.persistence.revision;

import com.alcozone.domain.classes.Revision;
import com.alcozone.domain.repository.RevisionRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RevisionRepositoryImpl implements RevisionRepository, PanacheRepositoryBase<RevisionEntity, Integer> {

    @Override
    @Transactional
    public Revision saveRevision(Revision revision) {
        RevisionEntity revisionEntity = RevisionMapper.toEntity(revision);
        persist(revisionEntity);
        return revision;
    }
}
