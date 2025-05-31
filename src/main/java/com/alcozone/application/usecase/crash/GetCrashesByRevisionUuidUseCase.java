package com.alcozone.application.usecase.crash;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.model.Crash;
import com.alcozone.application.service.CrashService;
import com.alcozone.infrastructure.dto.crash.DefaultCrashesResponseDTO;


@ApplicationScoped
public class GetCrashesByRevisionUuidUseCase {
    // TODO ? Check if its viable to maintain this UseCase instead of fetching the whole Revision with the Crash Object


    @Inject
    CrashService crashService;

    public DefaultCrashesResponseDTO execute(String uuid){
        List<Crash> crashes = crashService.getCrashesByRevisionUuid(uuid);

        DefaultCrashesResponseDTO dto = new DefaultCrashesResponseDTO();
        dto.setCount(crashes.size());
        dto.setData(crashes);
        return dto;
    }
}
