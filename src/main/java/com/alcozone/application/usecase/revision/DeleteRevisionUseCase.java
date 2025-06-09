package com.alcozone.application.usecase.revision;

import com.alcozone.application.service.RevisionService;
import com.alcozone.domain.model.Revision;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteRevisionUseCase {
    @Inject
    RevisionService revisionService;

    public Revision execute(String uuid) {
        return revisionService.deleteRevision(uuid);
    }
}
