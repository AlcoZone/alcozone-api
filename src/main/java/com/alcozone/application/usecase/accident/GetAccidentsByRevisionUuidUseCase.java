package com.alcozone.application.usecase.accident;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.Accident;
import com.alcozone.application.service.AccidentService;
import com.alcozone.infrastructure.dto.accident.DefaultAccidentsResponseDTO;


@ApplicationScoped
public class GetAccidentsByRevisionUuidUseCase {
    @Inject AccidentService accidentService;

    public DefaultAccidentsResponseDTO execute(String name){
        List<Accident> accidents = accidentService.getAccidentsByRevisionUuid(name);

        DefaultAccidentsResponseDTO dto = new DefaultAccidentsResponseDTO();
        dto.setCount(accidents.size());
        dto.setData(accidents);
        return dto;
    }
}
