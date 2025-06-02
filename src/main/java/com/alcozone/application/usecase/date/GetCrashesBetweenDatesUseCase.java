package com.alcozone.application.usecase.date;

import java.time.LocalDateTime;
import java.util.List;

import com.alcozone.application.service.CrashService;
import com.alcozone.domain.models.Crash;

import com.alcozone.infrastructure.dto.crash.DefaultCrashesResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetCrashesBetweenDatesUseCase {

    @Inject
    CrashService crashService;

    public List<Crash> execute(LocalDateTime startDate, LocalDateTime endDate) {
        return crashService.getCrashesBetweenDates(startDate, endDate);
    }
}

