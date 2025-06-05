package com.alcozone.UnitTests;

import com.alcozone.application.service.RevisionService;
import com.alcozone.application.service.WidgetService;
import com.alcozone.infrastructure.dto.widget.AccidentNumberDTO;
import com.alcozone.infrastructure.persistence.crash.CrashEntity;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

@QuarkusTest
public class GetDataTest {

    @Inject
    WidgetService widgetService;

    @Test
    @Transactional
    public void testInsertData() {
        RevisionEntity revision = new RevisionEntity();
        revision.setUuid("1"); // <- usamos "1" en lugar de UUID aleatorio
        revision.persist();
        RevisionEntity.getEntityManager().flush();

        for (int i = 0; i < 25279; i++) {
            CrashEntity crash = new CrashEntity();
            crash.setUuid("uuid_" + i);
            crash.setDatetime("2025-06-01T12:00:00");
            crash.setType("Colisión");
            crash.setSubType("Choque con lesionados");
            crash.setReportedBy("Test User");
            crash.setTown("CDMX");
            crash.setNeighbourhood("Centro");
            crash.setLatitude(19.4326);
            crash.setLongitude(-99.1332);
            crash.setRevisionEntity(revision); // <- relación con la revisión "1"
            crash.persist();
        }

        List<AccidentNumberDTO> accidentStats = widgetService.getAccidentsNumber();
        AccidentNumberDTO firstResult = accidentStats.getFirst();

        System.out.println("SubType: " + firstResult.getSubType() + " - Count: " + firstResult.getAccidentCount());

        assert firstResult.getSubType().equals("Choque con lesionados");
        assert firstResult.getAccidentCount() == 25279;
    }




    @Test
    @Transactional
    public void testInsertCrash_Failed_EmptyList() {
        List<AccidentNumberDTO> accidentStats = widgetService.getAccidentsNumber();

        if (accidentStats == null || accidentStats.isEmpty()) {
            System.out.println("Could not enter data");
            assert true;
        } else {

            AccidentNumberDTO firstResult = accidentStats.getFirst();
            System.out.println("Unexpected data was found: SubType: " + firstResult.getSubType() + " - Count: " + firstResult.getAccidentCount());
            assert false : "Were not expected data, but found data";
        }
    }

}

