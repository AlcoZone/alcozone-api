package com.alcozone.IntegrationTests;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

@QuarkusTest
public class CrashDateRestTest {

    @Test
    public void testGetCrashesBetweenDates() {

        given()
                .header("Authorization", "Bearer testtoken")
                .queryParam("start", "01-01-2022")
                .queryParam("end", "10-01-2022")
                .when()
                .get("/api/v1/crashes/date")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetCrashesBetweenDates_invalidFormat() {
        given()
                .header("Authorization", "Bearer testtoken")
                .queryParam("start", "2022/01/14")  // formato incorrecto
                .queryParam("end", "2022-01-21")
                .when()
                .get("/api/v1/crashes/date")
                .then()
                .statusCode(400);
    }
}

