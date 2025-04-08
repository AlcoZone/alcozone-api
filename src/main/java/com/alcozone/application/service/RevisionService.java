package com.alcozone.application.service;

import com.alcozone.domain.classes.Revision;
import com.alcozone.domain.repository.RevisionRepository;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class RevisionService {

    @Inject
    RevisionRepository revisionRepository;

    public RevisionEntity saveRevision(String name){
        Revision revision = new Revision();
        revision.setUuid(UUID.randomUUID().toString());
        revision.setName(name);

        return revisionRepository.saveRevision(revision);
    }
}
