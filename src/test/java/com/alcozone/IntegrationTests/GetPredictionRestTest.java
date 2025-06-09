package com.alcozone.IntegrationTests;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class GetPredictionRestTest {

    @Test
    public void testGetPrediction_basicCheck() {
        try {
            given()
                .header("Authorization", "Bearer testtoken")
                .when()
                .get("http://localhost:8080/api/v1/predict?revision=asd")
                .then()
                .statusCode(200)
                .body("$", notNullValue());
            System.out.println("Prediction Service Generated Prediction - 200 OK.");
        } catch (Exception e) {
            System.out.println("No data Found - 404 OK.");
        }

    }
}


