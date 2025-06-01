package com.alcozone.application.service;

import com.alcozone.domain.model.MinifiedRevision;
import com.alcozone.domain.repository.MinifiedRevisionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MinifiedRevisionService {

    @Inject MinifiedRevisionRepository minifiedRevisionRepository;

    public MinifiedRevision getMinifiedRevision(String uuid){
        return minifiedRevisionRepository.getMinifiedRevision(uuid);
    }
}
