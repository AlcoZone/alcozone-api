package com.alcozone.application.usecase.revision;

import com.alcozone.application.service.RevisionService;
import jakarta.inject.Inject;
import com.alcozone.domain.Revision;

public class GenerateRevisionUseCase {

    @Inject
    RevisionService revisionService;

    public Revision execute(){
        return revisionService.createRevision();
    }
}
