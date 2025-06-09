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
public class GetDataByTownTest {

    @Inject
    WidgetService widgetService;

    @Test
    @Transactional
    public void testGetAccidentsByReportSource_ValidTown() {
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
            crash.setTown("Iztapalapa");
            crash.setType("Accidente");
            crash.setSubType("Choque con lesionados");
            crash.setReportedBy("LLAMADA DEL 911");
            crash.setRevisionEntity(revision);
            crash.persist();
        }

        for (int i = 0; i < 32; i++) {
            CrashEntity crash = new CrashEntity();
            crash.setUuid("uuid_azt_2_" + i);
            crash.setLatitude(19.4326);
            crash.setLongitude(-99.1332);
            crash.setDatetime("2025-06-08T12:00:00");
            crash.setNeighbourhood("Centro");
            crash.setTown("Azcapotzalco");
            crash.setType("Accidente");
            crash.setSubType("Choque con lesionados");
            crash.setReportedBy("BOTÓN DE AUXILIO");
            crash.setRevisionEntity(revision);
            crash.persist();
        }

        WidgetFilters filters = new WidgetFilters();
        filters.setTown("Iztapalapa");

        List<AccidentsByReportSource> accidentsByReportSource = widgetService.getAccidentsByReportSource(filters);
        AccidentsByReportSource result = accidentsByReportSource.getFirst();
        assert accidentsByReportSource.size() == 1;
        assert result.getReport_source().equals("LLAMADA DEL 911");
        assert result.getTotal_accidents() == 18;
    }
}
