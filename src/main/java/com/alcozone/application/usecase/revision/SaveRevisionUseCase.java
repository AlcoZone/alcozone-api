package com.alcozone.application.usecase.revision;

import com.alcozone.application.service.RevisionService;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SaveRevisionUseCase {

    @Inject
    RevisionService revisionService;

    public RevisionEntity execute(String name){
        return revisionService.saveRevision(name);
    }
}
