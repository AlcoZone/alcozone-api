package com.alcozone.application.usecase.crash;

import com.alcozone.application.service.CrashService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetAvailableDatesUseCase {

    @Inject CrashService crashServiceservice;

    public List<String> execute() {
        return crashServiceservice.getAvailableDates();
    }
}
