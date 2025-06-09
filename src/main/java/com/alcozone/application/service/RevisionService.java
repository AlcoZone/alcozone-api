package com.alcozone.application.service;

import java.util.*;
import java.util.stream.Collectors;

import com.alcozone.domain.model.Revision;
import com.alcozone.infrastructure.dto.revision.response.RevisionListItemDTO;
import com.alcozone.infrastructure.persistence.revision.RevisionListEntity;
import com.alcozone.infrastructure.persistence.revision.RevisionMapper;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

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
        revision.setStatus("Cargando Datos");
        return revisionRepository.saveRevision(revision);
    }

    public RevisionListEntity getLatestLightweightRevision(){
        return revisionRepository.getLatestLightweightRevision();
    }

    public List<RevisionListItemDTO> getAllRevisions() {
        List<RevisionListEntity> entities = revisionRepository.getLightweightRevisions();
        return entities.stream()
                .map(RevisionMapper::toListItemDTO)
                .collect(Collectors.toList());
    }

    public Revision deleteRevision(String uuid){
        return revisionRepository.deleteRevision(uuid);
    }

    public void markAsCompleted(String uuid){
        revisionRepository.markAsSuccess(uuid);
    }
}