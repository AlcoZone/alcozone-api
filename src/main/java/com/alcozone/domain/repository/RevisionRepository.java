package com.alcozone.domain.repository;

import com.alcozone.domain.classes.Revision;

public interface RevisionRepository {
    Revision saveRevision(Revision revision);
}
