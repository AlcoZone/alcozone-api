package com.alcozone.infrastructure.persistence.minifiedRevision;

import com.alcozone.domain.model.MinifiedRevision;
import com.alcozone.domain.repository.MinifiedRevisionRepository;
import com.alcozone.infrastructure.persistence.revision.RevisionMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MinifiedRepositoryImpl implements MinifiedRevisionRepository, PanacheRepositoryBase<MinifiedRevisionEntity, Integer> {
    //TODO fetch latest without getting all of the crashes

    @Override
    public MinifiedRevision getLatestMinifiedRevision() {
        return RevisionMapper.toMinifiedDomain(find("ORDER BY created_at DESC").firstResult());
    }

    @Override
    public MinifiedRevision getMinifiedRevision(String uuid) {
        MinifiedRevisionEntity revisionEntity = find("uuid", uuid).firstResult();
        return RevisionMapper.toMinifiedDomain(revisionEntity);
    }
}
