package com.alcozone.application.usecase.accident;

import com.alcozone.application.service.AccidentService;
import com.alcozone.domain.models.Accident;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetAccidentsByRevisionUuidUseCase {
    @Inject
    AccidentService accidentService;

    public List<Accident> execute(String name){
        return accidentService.getAccidentsByRevisionUuid(name);
    }
}
