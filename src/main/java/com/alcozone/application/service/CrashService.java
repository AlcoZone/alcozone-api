package com.alcozone.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.model.Crash;
import com.alcozone.domain.repository.CrashRepository;
import com.alcozone.application.dto.crash.CreateCrashDTO;

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

    public List<Crash> getCrashesBetweenDates(LocalDateTime start, LocalDateTime end) {
        return crashRepository.findCrashesBetweenDates(start, end);
    }
}
