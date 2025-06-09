package com.alcozone.IntegrationTests;

import com.alcozone.infrastructure.persistence.crash.CrashEntity;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class GetDataByTownRestTest {

    @Transactional
    void insertTestData() {
        RevisionEntity revision = new RevisionEntity();
        revision.setUuid("1");
        revision.persist();
        RevisionEntity.getEntityManager().flush();

        for (int i = 0; i < 10; i++) {
            CrashEntity crash = new CrashEntity();
            crash.setUuid("uuid_xoch_" + i);
            crash.setLatitude(19.4326);
            crash.setLongitude(-99.1332);
            crash.setDatetime("2025-06-03T12:00:00");
            crash.setNeighbourhood("Centro");
            crash.setTown("Xochimilco");
            crash.setType("Accidente");
            crash.setSubType("Choque con lesionados");
            crash.setReportedBy("LLAMADA DEL 911");
            crash.setRevisionEntity(revision);
            crash.persist();
        }

        for (int i = 0; i < 5; i++) {
            CrashEntity crash = new CrashEntity();
            crash.setUuid("uuid_xoch_2_" + i);
            crash.setLatitude(19.4326);
            crash.setLongitude(-99.1332);
            crash.setDatetime("2025-06-04T10:00:00");
            crash.setNeighbourhood("Centro");
            crash.setTown("Xochimilco");
            crash.setType("Accidente");
            crash.setSubType("Motociclista");
            crash.setReportedBy("RADIO");
            crash.setRevisionEntity(revision);
            crash.persist();
        }

        for (int i = 0; i < 15; i++) {
            CrashEntity crash = new CrashEntity();
            crash.setUuid("uuid_tl_" + i);
            crash.setLatitude(39.4354);
            crash.setLongitude(-76.1332);
            crash.setDatetime("2025-06-04T08:00:00");
            crash.setNeighbourhood("Centro");
            crash.setTown("Tlalpan");
            crash.setType("Accidente");
            crash.setSubType("Choque sin lesionados");
            crash.setReportedBy("CÁMARA");
            crash.setRevisionEntity(revision);
            crash.persist();
        }
    }

    @Test
    public void testAccidentsByReportSourceEndpoint_FilteredByTown_ReturnsCorrectData() {
        insertTestData();

        given()
                .header("Authorization", "Bearer testtoken")
                .queryParam("town", "Xochimilco")
                .when()
                .get("/api/v1/widgets/accidents-by-report-source")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("report_source", hasItems("LLAMADA DEL 911", "RADIO"))
                .body("total_accidents", hasItems(10, 5));
    }

}
