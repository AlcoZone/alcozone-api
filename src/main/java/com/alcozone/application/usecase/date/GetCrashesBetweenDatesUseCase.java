package com.alcozone.application.usecase.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.alcozone.application.service.CrashService;
import com.alcozone.domain.model.Crash;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetCrashesBetweenDatesUseCase {

    @Inject
    CrashService crashService;

    public List<Crash> execute(String startDate, String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(23, 59, 59);

        return crashService.getCrashesBetweenDates(startDateTime, endDateTime);
    }
}

