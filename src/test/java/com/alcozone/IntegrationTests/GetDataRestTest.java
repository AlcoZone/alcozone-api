package com.alcozone.IntegrationTests;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class GetDataRestTest {

    @Test
    public void testGetAccidentsNumberEndpoint_basicCheck() {
        given()
                .header("Authorization", "Bearer testtoken")
                .when()
                .get("/api/v1/widgets/accidents-count")
                .then()
                .statusCode(200)
                .body("$", notNullValue());
        System.out.println("Accidents count was retrieved correctly - 200 OK.");
    }
}


