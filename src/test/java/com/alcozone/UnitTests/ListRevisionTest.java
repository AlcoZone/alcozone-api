package com.alcozone.UnitTests;

import com.alcozone.application.service.RevisionService;
import com.alcozone.infrastructure.dto.revision.response.RevisionListItemDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class ListRevisionTest {

    @Inject
    RevisionService revisionService;

    @Test
    public void testInsertRevision() {
        revisionService.saveRevision("Revision Test");

        List<RevisionListItemDTO> revisions = revisionService.getAllRevisions();
        RevisionListItemDTO revisionInDb = revisions.getFirst();

        assert revisionInDb.getName().equals("Revision Test");
        assert revisionInDb.getDate() != null;
        assert revisionInDb.getDataQuantity() >= 0;
        assert revisions.size() == 1;
    }
}