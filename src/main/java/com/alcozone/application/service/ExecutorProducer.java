package com.alcozone.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class ExecutorProducer {

    @Produces
    @Named("customExecutor")
    ExecutorService produceExecutor() {
        return Executors.newFixedThreadPool(10);
    }
}