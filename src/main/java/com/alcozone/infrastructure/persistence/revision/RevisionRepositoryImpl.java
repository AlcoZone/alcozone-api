package com.alcozone.infrastructure.persistence.revision;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import com.alcozone.domain.models.Revision;
import com.alcozone.domain.repository.RevisionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RevisionRepositoryImpl implements RevisionRepository, PanacheRepositoryBase<RevisionEntity, Integer> {

    @Inject
    EntityManager entityManager;

    @Override
    public Revision getRevision(String uuid) {
        RevisionEntity revisionEntity = find("uuid", uuid).firstResult();
        return RevisionMapper.toDomain(revisionEntity);
    }

    @Override
    public RevisionEntity getRevisionEntity(String uuid) {
        return find("uuid", uuid).firstResult();
    }

    @Override
    @Transactional
    public Revision saveRevision(Revision revision) {
        RevisionEntity revisionEntity = RevisionMapper.toEntity(revision);
        revisionEntity.persist();
        return RevisionMapper.toDomain(revisionEntity);
    }

    @Override
    public List<RevisionListEntity> getLightweightRevisions() {
        String sql = """
        SELECT r.uuid, r.name, r.created_at, SIZE(r.crashes)
        FROM RevisionEntity r
    """;

        List<Object[]> rows = entityManager
                .createQuery(sql, Object[].class)
                .getResultList();

        List<RevisionListEntity> result = new ArrayList<>();

        for (Object[] row : rows) {
            RevisionListEntity item = new RevisionListEntity();
            item.setUuid((String) row[0]);
            item.setName((String) row[1]);
            item.setCreated_at((LocalDateTime) row[2]);
            item.setDataQuantity(((Number) row[3]).intValue());

            result.add(item);
        }

        return result;
    }
}
