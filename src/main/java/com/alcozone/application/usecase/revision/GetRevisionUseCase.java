package com.alcozone.application.usecase.revision;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.Revision;
import com.alcozone.application.service.RevisionService;
import com.alcozone.infrastructure.dto.revision.request.GetRevisionRequestDTO;
import com.alcozone.infrastructure.dto.revision.response.DefaultRevisionResponseDTO;
import com.alcozone.infrastructure.dto.accident.DefaultAccidentsResponseDTO;

@ApplicationScoped
public class GetRevisionUseCase {

    @Inject RevisionService revisionService;

    public DefaultRevisionResponseDTO execute(GetRevisionRequestDTO requestDTO) {
        Revision revision = revisionService.getRevision(requestDTO.getUuid());

        DefaultAccidentsResponseDTO wrapperDTO = new DefaultAccidentsResponseDTO();
        wrapperDTO.setCount(revision.getAccidents().size());
        if(requestDTO.isWithData()){
            wrapperDTO.setData(revision.getAccidents());
        }

        DefaultRevisionResponseDTO dto = new DefaultRevisionResponseDTO();
        dto.setUuid(revision.getUuid());
        dto.setName(revision.getName());
        dto.setAccidents(wrapperDTO);
        return dto;
    }
}
