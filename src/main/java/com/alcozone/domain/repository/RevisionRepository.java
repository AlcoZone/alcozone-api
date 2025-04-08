package com.alcozone.domain.repository;

import com.alcozone.domain.Revision;


public interface RevisionRepository {
    Revision saveRevision(Revision revision);
}
