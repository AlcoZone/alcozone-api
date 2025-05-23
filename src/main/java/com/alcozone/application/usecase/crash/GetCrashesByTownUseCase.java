package com.alcozone.application.usecase.crash;

import java.util.List;

import com.alcozone.application.service.CrashService;
import com.alcozone.domain.models.Crash;
import com.alcozone.infrastructure.dto.crash.DefaultCrashesResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetCrashesByTownUseCase {
    @Inject
    CrashService crashService;

    public DefaultCrashesResponseDTO execute (String town){
        List<Crash> crashes = crashService.getCrashesByTown(town);
        DefaultCrashesResponseDTO dto = new DefaultCrashesResponseDTO();
        dto.setCount(crashes.size());
        dto.setData(crashes);
        return dto;
    }

}
