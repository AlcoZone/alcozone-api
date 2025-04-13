package com.alcozone.application.service;

import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.Revision;
import com.alcozone.domain.repository.RevisionRepository;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

@ApplicationScoped
public class RevisionService {

    @Inject RevisionRepository revisionRepository;

    public Revision getRevision(String uuid) {
        return revisionRepository.getRevision(uuid);
    }

    public RevisionEntity getRevisionEntity(String uuid) {
        return revisionRepository.getRevisionEntity(uuid);
    }

    public Revision saveRevision(String name){
        Revision revision = new Revision();
        revision.setUuid(UUID.randomUUID().toString());
        revision.setName(name);
        return revisionRepository.saveRevision(revision);
    }
}