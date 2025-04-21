package com.alcozone.application.usecase.crash;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.Crash;
import com.alcozone.application.service.CrashService;
import com.alcozone.application.dto.crash.CreateCrashDTO;

@ApplicationScoped
public class CreateCrashUseCase {

    @Inject
    CrashService crashService;

    public Crash execute(CreateCrashDTO createCrashDTO) {
        return crashService.saveCrash(createCrashDTO);
    }
}