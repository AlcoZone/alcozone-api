package com.alcozone.application.usecase.revision;

import com.alcozone.application.service.RevisionService;
import com.alcozone.infrastructure.dto.revision.response.RevisionListItemDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ListRevisionsUseCase {

    @Inject RevisionService revisionService;

    public List<RevisionListItemDTO> execute() {
        return revisionService.getAllRevisions();
    }
}
