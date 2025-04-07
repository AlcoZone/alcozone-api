package com.alcozone.application.service;

import com.alcozone.domain.Revision;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class RevisionService {

    public Revision createRevision() {
        return new Revision();
    }

    public Revision saveRevision(Revision revision) {
        UUID uuid = UUID.randomUUID();
        revision.setUuid(uuid.toString());
        return revision;
    }
}
