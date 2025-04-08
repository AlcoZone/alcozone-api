package com.alcozone.application.service;

import com.alcozone.domain.Revision;
import com.alcozone.domain.repository.RevisionRepository;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class RevisionService {

    @Inject
    RevisionRepository revisionRepository;

    public Revision saveRevision(String name){
        Revision revision = new Revision();
        revision.setUuid(UUID.randomUUID().toString());
        revision.setName(name);
        revision.setStatus("PENDING");

        revisionRepository.saveRevision(revision);
        return revision;
    }
}
