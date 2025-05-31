package com.alcozone.application.service;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.model.Crash;
import com.alcozone.domain.repository.CrashRepository;
import com.alcozone.application.dto.crash.CreateCrashDTO;
//TODO Refactor -> Infra Layer

@ApplicationScoped
public class CrashService {

    @Inject
    CrashRepository crashRepository;

    public Crash saveCrash(CreateCrashDTO createCrashDTO) {
        Crash crash = createCrashDTO.toDomain();
        crash.setUuid(UUID.randomUUID().toString());
        return crashRepository.saveCrash(crash, createCrashDTO.getRevisionEntity());
    }

    public List<Crash> getCrashesByRevisionUuid(String revisionUuid) {
        return crashRepository.findCrashesByRevisionUuid(revisionUuid);
    }
}
