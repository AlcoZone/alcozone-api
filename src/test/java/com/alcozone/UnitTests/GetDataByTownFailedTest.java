package com.alcozone.UnitTests;

import com.alcozone.application.service.WidgetService;
import com.alcozone.domain.models.widgetdata.AccidentsByReportSource;
import com.alcozone.domain.models.widgetdata.WidgetFilters;
import com.alcozone.infrastructure.persistence.crash.CrashEntity;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class GetDataByTownFailedTest {

    @Inject
    WidgetService widgetService;

    @Test
    @Transactional
    public void testGetAccidentsByReportSource_InvalidTown() {
        RevisionEntity revision = new RevisionEntity();
        revision.setUuid("1");
        revision.persist();
        RevisionEntity.getEntityManager().flush();

        for (int i = 0; i < 18; i++) {
            CrashEntity crash = new CrashEntity();
            crash.setUuid("uuid_izt_" + i);
            crash.setLatitude(19.4326);
            crash.setLongitude(-99.1332);
            crash.setDatetime("2025-06-03T12:00:00");
            crash.setNeighbourhood("Centro");
            crash.setTown("Iztacalco");
            crash.setType("Accidente");
            crash.setSubType("Choque con lesionados");
            crash.setReportedBy("LLAMADA DEL 911");
            crash.setRevisionEntity(revision);
            crash.persist();
        }

        WidgetFilters filters = new WidgetFilters();
        filters.setTown("Chicago");

        List<AccidentsByReportSource> accidentsByReportSource = widgetService.getAccidentsByReportSource(filters);
        if(accidentsByReportSource.isEmpty()) {
            System.out.println("Empty data for an invalid town");
            assert true;
        } else {
            AccidentsByReportSource result = accidentsByReportSource.getFirst();
            System.out.println("Error: Data found for an invalid town." + "Report Source: " + result.getReport_source() + " - Total Accidents: " + result.getTotal_accidents());
            assert false;
        }
    }
}
