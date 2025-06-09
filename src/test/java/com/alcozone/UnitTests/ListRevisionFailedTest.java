package com.alcozone.UnitTests;
import com.alcozone.application.service.RevisionService;
import com.alcozone.infrastructure.dto.revision.response.RevisionListItemDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ListRevisionFailedTest {

    @Inject
    RevisionService revisionService;

    @Test
    public void testInsertRevision_failed() {
        List<RevisionListItemDTO> revisions = revisionService.getAllRevisions();

        if (revisions.isEmpty()) {
            System.err.println("Advertencia: no se enlistó ninguna revisión");
        }

        assertEquals(0, revisions.size(), "Se esperaba que no hubiera revisiones enlistadas");
    }
}

