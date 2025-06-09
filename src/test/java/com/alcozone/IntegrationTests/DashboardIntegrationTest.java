package com.alcozone.IntegrationTests;

import com.alcozone.application.dto.dashboard.CreateDashboardDTO;
import com.alcozone.application.service.DashboardService;
import com.alcozone.domain.models.Dashboard;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class DashboardIntegrationTest {

    @Inject
    DashboardService dashboardService;

    @Test
    public void testDashboardListAuthorized() {
        String userUuid = "123e4567-e89b-12d3-a456-426614174000";

        CreateDashboardDTO dto = new CreateDashboardDTO();
        dto.setUserUuid(userUuid);
        dto.setName("Dashboard CDMX");

        dashboardService.createDashboard(dto);

        given()
                .header("Authorization", "Bearer testtoken")
                .queryParam("userUuid", userUuid)
                .when()
                .get("/api/v1/dashboards")
                .then()
                .statusCode(200)
                .body("name", hasItem("Dashboard CDMX"));
    }

    @Test
    public void testDashboardListUnauthorized() {
        String userUuid = "123e4567-e89b-12d3-a456-426614174000";

        CreateDashboardDTO dto = new CreateDashboardDTO();
        dto.setUserUuid(userUuid);
        dto.setName("Dashboard CDMX");

        dashboardService.createDashboard(dto);

        given()
                .queryParam("userUuid", userUuid)
                .when()
                .get("/api/v1/dashboards")
                .then()
                .statusCode(401);
    }
}
