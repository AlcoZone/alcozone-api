package com.alcozone.application.usecase.revision;

import com.alcozone.application.service.RevisionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.alcozone.domain.classes.Revision;

@ApplicationScoped
public class SaveRevisionUseCase {

    @Inject
    RevisionService revisionService;

    public Revision execute(String name){
        return revisionService.saveRevision(name);
    }
}
