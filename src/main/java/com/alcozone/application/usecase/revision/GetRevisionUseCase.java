package com.alcozone.application.usecase.revision;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.model.Revision;
import com.alcozone.application.service.RevisionService;
import com.alcozone.infrastructure.dto.crash.DefaultCrashesResponseDTO;
import com.alcozone.infrastructure.dto.revision.request.GetRevisionRequestDTO;
import com.alcozone.infrastructure.dto.revision.response.DefaultRevisionResponseDTO;

@ApplicationScoped
public class GetRevisionUseCase {
    //TODO Check if this types of UseCases can return the Domain Entity and Transform it to DTO in Controller

    @Inject RevisionService revisionService;

    public DefaultRevisionResponseDTO execute(GetRevisionRequestDTO requestDTO) {
        Revision revision = revisionService.getRevision(requestDTO.getUuid());

        DefaultCrashesResponseDTO wrapperDTO = new DefaultCrashesResponseDTO();
        wrapperDTO.setCount(revision.getCrashes().size());
        if(requestDTO.isWithData()){
            wrapperDTO.setData(revision.getCrashes());
        }

        DefaultRevisionResponseDTO dto = new DefaultRevisionResponseDTO();
        dto.setUuid(revision.getUuid());
        dto.setName(revision.getName());
        dto.setCrashes(wrapperDTO);
        return dto;
    }
}
