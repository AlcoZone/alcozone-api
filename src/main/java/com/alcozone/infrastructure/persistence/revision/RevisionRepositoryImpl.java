package com.alcozone.infrastructure.persistence.revision;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import com.alcozone.domain.model.Revision;
import com.alcozone.domain.repository.RevisionRepository;

import java.util.List;

@ApplicationScoped
public class RevisionRepositoryImpl implements RevisionRepository, PanacheRepositoryBase<RevisionEntity, Integer> {

    @Override
    public Revision getRevision(String uuid) {
        RevisionEntity revisionEntity = find("uuid", uuid).firstResult();
        return RevisionMapper.toDomain(revisionEntity);
    }

    @Override
    public RevisionEntity getRevisionEntity(String uuid) {
        return find("uuid", uuid).firstResult();
    }

    @Override
    @Transactional
    public Revision saveRevision(Revision revision) {
        RevisionEntity revisionEntity = RevisionMapper.toEntity(revision);
        revisionEntity.persist();
        return RevisionMapper.toDomain(revisionEntity);
    }

    @Override
    public List<RevisionEntity> getAllRevisions() {
        return findAll().list();
    }

}
