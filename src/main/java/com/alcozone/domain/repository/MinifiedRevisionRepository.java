package com.alcozone.domain.repository;

import com.alcozone.domain.model.MinifiedRevision;

public interface MinifiedRevisionRepository {
    MinifiedRevision getLatestMinifiedRevision();
    MinifiedRevision getMinifiedRevision(String uuid);
}
